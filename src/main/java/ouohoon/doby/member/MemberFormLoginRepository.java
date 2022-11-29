package ouohoon.doby.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ouohoon.doby.member.model.MemberFormLogin;

import java.util.Optional;

@Repository
public interface MemberFormLoginRepository extends JpaRepository<MemberFormLogin, Long> {

    Optional<MemberFormLogin> findByUsername(String username);
}
