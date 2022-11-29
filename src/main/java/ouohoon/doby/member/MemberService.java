package ouohoon.doby.member;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ouohoon.doby.member.dto.MemberDTO;
import ouohoon.doby.member.dto.MemberFormLoginDTO;
import ouohoon.doby.member.model.Member;
import ouohoon.doby.member.model.MemberFormLogin;
import ouohoon.doby.security.Role;

@Service
public class MemberService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private MemberRepository memberRepository;
    private MemberFormLoginRepository memberFormLoginRepository;

    public MemberService(BCryptPasswordEncoder bCryptPasswordEncoder, MemberRepository memberRepository, MemberFormLoginRepository memberFormLoginRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.memberRepository = memberRepository;
        this.memberFormLoginRepository = memberFormLoginRepository;
    }

    public void login(MemberFormLoginDTO loginDTO) {

    }

    public MemberDTO join(MemberFormLoginDTO memberFormLoginDTO) {
        Member member = Member.builder()
                .role(Role.GUEST)
                .build();
        MemberFormLogin memberFormLogin = MemberFormLogin.builder()
                .username(memberFormLoginDTO.getUsername())
                .password(bCryptPasswordEncoder.encode(memberFormLoginDTO.getPassword()))
                .build();
        memberFormLoginRepository.save(memberFormLogin);
        member.changeFormLogin(memberFormLogin);
        memberRepository.save(member);

        return new MemberDTO();
    }
}
