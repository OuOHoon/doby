package ouohoon.doby.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import ouohoon.doby.security.oauth.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private CustomOAuth2UserService oAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable(); // JWT로 인증할거니까 비활성화
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole(Role.ADMIN.name())
                .antMatchers("/chat/**").access("hasRole('ADMIN') or hasRole('USER')")
                .anyRequest().hasRole(Role.GUEST_USER.name());

        http.formLogin() // 폼 로그인 설정
                .loginPage("/login")
                .loginProcessingUrl("/loginProc")
                .defaultSuccessUrl("/");

        http.oauth2Login() // 소셜 로그인 설정
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(oAuth2UserService);
        return http.build();
    }


}
