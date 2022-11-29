package ouohoon.doby.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ouohoon.doby.member.model.MemberSocialLogin;

@Repository
public interface MemberSocialLoginRepository extends JpaRepository<MemberSocialLogin, Long> {

    MemberSocialLogin findByProviderAndProviderId(String provider, String providerId);

}
