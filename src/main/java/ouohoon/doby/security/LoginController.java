package ouohoon.doby.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ouohoon.doby.member.MemberFormLoginRepository;
import ouohoon.doby.member.MemberService;
import ouohoon.doby.member.dto.MemberFormLoginDTO;

@Controller
@Slf4j
public class LoginController {

    private MemberService memberService;
    private MemberFormLoginRepository memberFormLoginRepository;

    public LoginController(MemberService memberService, MemberFormLoginRepository memberFormLoginRepository) {
        this.memberService = memberService;
        this.memberFormLoginRepository = memberFormLoginRepository;
    }

    @GetMapping("/loginForm")
    public String login() {
        return "/auth/login";
    }

    @PostMapping("/login")
    public String login(MemberFormLoginDTO loginDTO) {
        //memberService.login(loginDTO);
        return "index";
    }

    @GetMapping("/join")
    public String joinForm() {
        return "/auth/join";
    }

    @PostMapping("/join")
    public String join(MemberFormLoginDTO loginDTO) {

        memberService.join(loginDTO);
        return "/auth/login";
    }

    @GetMapping("/api/hello")
    @ResponseBody
    public String react() {
        return "hello react";
    }
}
