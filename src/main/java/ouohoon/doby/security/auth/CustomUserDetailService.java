package ouohoon.doby.security.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ouohoon.doby.member.MemberFormLoginRepository;
import ouohoon.doby.member.MemberRepository;
import ouohoon.doby.member.model.Member;
import ouohoon.doby.member.model.MemberFormLogin;

import java.util.Optional;

@Slf4j
@Service
public class CustomUserDetailService implements UserDetailsService {

    private MemberRepository memberRepository;
    private MemberFormLoginRepository memberFormLoginRepository;

    public CustomUserDetailService(MemberRepository memberRepository, MemberFormLoginRepository memberFormLoginRepository) {
        this.memberRepository = memberRepository;
        this.memberFormLoginRepository = memberFormLoginRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MemberFormLogin> optionalMemberFormLogin = memberFormLoginRepository.findByUsername(username);
        if (optionalMemberFormLogin.isPresent()) {
            MemberFormLogin memberFormLogin = optionalMemberFormLogin.get();
            Member member = memberFormLogin.getMember();
            return new CustomUserDetails(member);
        }
        return null;
    }
}
