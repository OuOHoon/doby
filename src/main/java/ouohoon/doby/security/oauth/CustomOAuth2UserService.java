package ouohoon.doby.security.oauth;


import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import ouohoon.doby.member.MemberRepository;
import ouohoon.doby.member.MemberSocialLoginRepository;
import ouohoon.doby.member.model.Member;
import ouohoon.doby.member.model.MemberSocialLogin;
import ouohoon.doby.security.Role;
import ouohoon.doby.security.auth.CustomUserDetails;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private MemberRepository memberRepository;
    private MemberSocialLoginRepository memberSocialLoginRepository;

    public CustomOAuth2UserService(MemberRepository memberRepository, MemberSocialLoginRepository memberSocialLoginRepository) {
        this.memberRepository = memberRepository;
        this.memberSocialLoginRepository = memberSocialLoginRepository;
    }

    /**
     * 유저 정보 DB에서 찾고 처리하는 메소드
     * @param userRequest the user request
     * @return
     * @throws OAuth2AuthenticationException
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        return process(userRequest, oAuth2User);
    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuth2UserInfo userInfo = OAuth2UserFactory.createOAuth2UserInfo(registrationId,
                oAuth2User.getAttributes());

        MemberSocialLogin socialLogin = memberSocialLoginRepository.findByProviderAndProviderId(userInfo.getProvider(), userInfo.getProviderId());

        if (socialLogin != null) { // 이미 가입된 회원이라면 정보 찾아서 SecurityContextHolder에 저장
            Member member = memberRepository.findBySocialLoginId(socialLogin.getId());
            return new CustomUserDetails(member, oAuth2User.getAttributes());
        }

        // 신규 회원이라면 정보 생성
        Member member = Member.builder()
                .role(Role.USER)
                .build();
        MemberSocialLogin memberSocialLogin = MemberSocialLogin.builder()
                .provider(userInfo.getProvider())
                .providerId(userInfo.getProviderId())
                .build();
        memberRepository.save(member);
        memberSocialLoginRepository.save(memberSocialLogin);
        member.changeSocialLogin(memberSocialLogin);

        return new CustomUserDetails(member, oAuth2User.getAttributes());
    }
}
