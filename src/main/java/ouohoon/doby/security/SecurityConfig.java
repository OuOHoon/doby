package ouohoon.doby.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import ouohoon.doby.security.oauth.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

    private CustomOAuth2UserService oAuth2UserService;

    public SecurityConfig(CustomOAuth2UserService oAuth2UserService) {
        this.oAuth2UserService = oAuth2UserService;
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(HttpSecurity http) throws Exception {
        return (web) -> web.ignoring()
                // Spring Security should completely ignore URLs starting with /resources/
                .antMatchers("/resources/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return sessionFilterChain(http);
    }

    private SecurityFilterChain sessionFilterChain(HttpSecurity http) throws Exception {
        // 기능 개발을 우선하기 위해서 csrf 잠시 꺼둠. 리팩토링 필요
        //http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole(Role.ADMIN.name())
                .antMatchers("/chat/**").access("hasRole('ADMIN') or hasRole('USER')")
                .antMatchers("/game/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name(), Role.GUEST.name())
                .antMatchers("/test/**").hasRole(Role.GUEST.name())
                .antMatchers("/loginForm").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/join").permitAll()
                .anyRequest().authenticated()
                    .and()
                .formLogin()// 폼 로그인 설정
                .loginPage("/loginForm")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/loginForm")
                    .and()
                .logout() // 로그아웃 설정
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                    .and()
                .oauth2Login() // 소셜 로그인 설정
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(oAuth2UserService);
        return http.build();
    }


    private SecurityFilterChain jwtFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().disable();

        return http.build();
    }

}
