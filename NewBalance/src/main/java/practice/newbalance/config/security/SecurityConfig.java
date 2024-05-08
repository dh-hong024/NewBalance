package practice.newbalance.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import practice.newbalance.config.handler.MemberAuthFailHandler;
import practice.newbalance.config.handler.MemberAuthSuccessHandler;
import practice.newbalance.config.provider.MemberAuthenticationProvider;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/","/members/**").permitAll() //해당 경로는 인증 없이 접근 가능
                        .requestMatchers("/admin").hasRole("ADMIN") //해당 경로는 인증이 필요 ROLE이 ADMIN이 포함된 경우에만 인증 가능
                        .requestMatchers("/my/**").hasAnyRole("ADMIN","USER") //마이페이지 ROLE이 ADMIN과 USER 일 경우 가능
                        .anyRequest().authenticated()
                );
        http
                .formLogin((auth) -> auth
                        .loginPage("/members/login") //로그인 페이지 설정
                        .usernameParameter("userId") //Spring Security default Parameter = username 이기 때문에 userId 명시해줘야함
                        .passwordParameter("password")
                        .loginProcessingUrl("/login/loginProc") // 로그인 처리 URL 설정
//                        .defaultSuccessUrl("/") // 로그인 성공 후 이동할 페이지 successHandler로 우선순위 밀려 주석처리
                        .successHandler(new MemberAuthSuccessHandler()) //로그인 성공 후 처리할 핸들러
                        .failureHandler(new MemberAuthFailHandler()) // 로그인 실패 후 처리할 핸들러
                        .permitAll()
                );
        http.logout((auth) -> auth
                .logoutUrl("/members/logout") //로그아웃 처리 URL 설정
                .logoutSuccessUrl("/") //로그아웃 성공 후 이동할 페이지
                .deleteCookies("JESSIONID") // 로그아웃 후 쿠키 삭제
        );
        //세션 다중 로그인
        http.sessionManagement((auth) -> auth
                .maximumSessions(1) //동시 접속 중복 수
                .maxSessionsPreventsLogin(true) // true 새로운 로그인 차단 / false 기존 로그인 세션 삭제 후 새로 로그인
        );

        //세션 고정 보호
        http.sessionManagement((auth) -> auth
                .sessionFixation().changeSessionId()
        );

        http
                .csrf((auth) -> auth.disable());

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
