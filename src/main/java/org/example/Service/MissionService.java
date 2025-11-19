package org.example.Service;

import lombok.RequiredArgsConstructor;
import org.example.Dto.MissionSimpleResponse;
import org.example.Entity.Mission;
import org.example.Repository.MissionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    public Page<MissionSimpleResponse> getMissionsByStore(Long storeId, Pageable pageable) {

        Page<Mission> missions = missionRepository.findByStoreId(storeId, pageable);

        return missions.map(mission -> MissionSimpleResponse.builder()
                .missionId(mission.getMission_id())
                .title(mission.getTitle())
                .description(mission.getDescription())
                .build());
    }
}
