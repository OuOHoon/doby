package ouohoon.doby.security.auth;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import ouohoon.doby.member.model.Member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class CustomUserDetails implements UserDetails, OAuth2User {

    private Member member;
    private Map<String, Object> attributes;

    public CustomUserDetails(Member member) {
        this.member = member;
    }

    public CustomUserDetails(Member member, Map<String, Object> attributes) {
        this.member = member;
        this.attributes = attributes;
    }

    public Member getMember() {
        return member;
    }

    /**
     * Member의 Primary Key
     * @return
     */
    @Override
    public String getName() {
        return member.getId().toString();
    }

    /**
     * OAuth2 회원 정보
     * @return
     */
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add((GrantedAuthority) () -> member.getRole().name());
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return member.getFormLogin().getPassword();
    }

    @Override
    public String getUsername() {
        return member.getFormLogin().getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
