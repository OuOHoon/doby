package ouohoon.doby.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ouohoon.doby.member.model.MemberProfile;

@Repository
public interface MemberProfileRepository extends JpaRepository<MemberProfile, Long> {
}
