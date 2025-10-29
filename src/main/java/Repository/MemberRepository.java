package Repository;

import first.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 유저 마이페이지 조회용
    Member findById(long id);
}
