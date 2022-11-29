package ouohoon.doby.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ouohoon.doby.member.model.Member;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findBySocialLoginId(Long socialLoginId);
}
