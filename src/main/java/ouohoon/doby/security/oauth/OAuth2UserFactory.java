package ouohoon.doby.security.oauth;

import java.util.Map;

public class OAuth2UserFactory {

    private OAuth2UserFactory() {
    }

    public static OAuth2UserInfo createOAuth2UserInfo(String provider, Map<String, Object> attributes) {
        if (provider.equals(OAuth2Provider.KAKAO.name())) {
            return new KakaoUserInfo(attributes);
        } else if (provider.equals(OAuth2Provider.NAVER.name())) {
            return new NaverUserInfo((Map<String, Object>) attributes.get("response"));
        }
        return null;
    }
}
