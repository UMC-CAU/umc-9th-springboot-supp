package Repository;

import Entity.Member;
import Entity.MemberMission;
import Entity.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 진행 중/완료 상태 구분한거
    Page<MemberMission> findByMemberAndStateIn(Member member, List<State> states, Pageable pageable);
    // 걍 다가져오고 싶을떄
    List<MemberMission> findByMemberAndStateIn(Member member, List<State> states);

@Query("SELECT m.title, m.description" +
            " FROM Mission m " +
            " WHERE m.address = :address " +
            " ORDER BY m.mission_id DESC")
    Page<Object[]> findMissionsByAddress(@Param("address") int address, Pageable pageable);
//이정도면 직접 짜는거랑 뭐가 다르지..싶은 복잡도인데 원래 이런건지 제가 잘못짠건지 고민이 되는 파트
}
