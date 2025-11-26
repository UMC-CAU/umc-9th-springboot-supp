package org.example.Service;

import org.example.Dto.MemberMissionResponse;
import org.example.Dto.MissionChallengeRequest;
import org.example.Repository.MemberMissionRepository;
import org.example.Repository.MemberRepository;
import org.example.Repository.MissionRepository;
import org.example.Entity.Member;
import org.example.Entity.MemberMission;
import org.example.Entity.Mission;
import org.example.Entity.State;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;       // ✅ 추가
import org.springframework.data.domain.Pageable;   // ✅ 추가
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;                        // ✅ 추가

@Service
@RequiredArgsConstructor
public class MemberMissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    @Transactional
    public MemberMissionResponse challengeMission(MissionChallengeRequest request) {

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다. id=" + request.getMemberId()));
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다. id=" + request.getMissionId()));

        memberMissionRepository.findByMemberAndMission(member, mission)
                .ifPresent(mm -> {
                    if (mm.getState() == State.ACTIVE) {
                        throw new IllegalStateException("이미 도전 중인 미션입니다.");
                    }
                });

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .state(State.ACTIVE)
                .updatedAt(LocalDateTime.now())
                .build();

        MemberMission saved = memberMissionRepository.save(memberMission);

        return MemberMissionResponse.builder()
                .memberMissionId(saved.getId())
                .memberId(member.getId())
                .missionId(mission.getMission_id())
                .state(saved.getState())
                .updatedAt(saved.getUpdatedAt())
                .build();
    }

    @Transactional(readOnly = true)
    public Page<MemberMissionResponse> getMyInProgressMissions(Long memberId, Pageable pageable) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다. id=" + memberId));

        List<State> states = List.of(State.ACTIVE);

        Page<MemberMission> page =
                memberMissionRepository.findByMemberAndStateIn(member, states, pageable);

        return page.map(mm -> MemberMissionResponse.builder()
                .memberMissionId(mm.getId())
                .memberId(mm.getMember().getId())
                .missionId(mm.getMission().getMission_id())
                .state(mm.getState())
                .updatedAt(mm.getUpdatedAt())
                .build());
    }
}
