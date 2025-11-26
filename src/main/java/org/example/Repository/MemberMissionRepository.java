package org.example.Repository;

import org.example.Entity.Member;
import org.example.Entity.MemberMission;
import org.example.Entity.Mission;
import org.example.Entity.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Page<MemberMission> findByMemberAndStateIn(Member member, List<State> states, Pageable pageable);
    List<MemberMission> findByMemberAndStateIn(Member member, List<State> states);

    Optional<MemberMission> findByMemberAndMission(Member member, Mission mission);
}
