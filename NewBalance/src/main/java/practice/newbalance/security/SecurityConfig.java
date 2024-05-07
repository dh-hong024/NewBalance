package practice.newbalance.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

@Configuration //스프링 시큐리티 설정
@EnableWebSecurity //모든요청URL이 스프링시큐리티의 제어
@RequiredArgsConstructor
public class SecurityConfig {

    private final MyUserDetailService myUserDetailService;
    private final FailHandler failureHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //인증인가 json 예외처리 참고
        //https://non-stop.tistory.com/667

        //예외처리 참고
        //https://wonisdaily.tistory.com/272
        //https://dev-log.tistory.com/4


        http
                .csrf((csrfConfig) -> csrfConfig.disable()
                )//1번
                .headers((headerConfig) -> headerConfig.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()
                )
                )//2번
                .authorizeHttpRequests((authorizeRequests) -> authorizeRequests
                        .requestMatchers("/", "/members/**").permitAll()
                        .anyRequest().authenticated()
                ) //3번
//                .exceptionHandling((exceptionConfig) ->
//                        exceptionConfig
//                                .authenticationEntryPoint(unauthorizedEntryPoint)
//                                .accessDeniedHandler(accessDeniedHandler)
//
//                ) //401 403관련 예외처리
                .formLogin( (formLogin) -> formLogin
                        .loginPage("/members/login") // 로그인 페이지
                        .loginProcessingUrl("/login/loginCheck") //로그인 Form Action Url
                        .usernameParameter("userId")    //아이디 파라미터명 설정
                        .passwordParameter("password")  //패스워드 파라미터명 설정
                        .successHandler(new AuthenticationSuccessHandler() { //로그인 성공 후 핸들러
                            @Override
                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                System.out.println("authentication = " + authentication.getName());

                                HttpSession session = request.getSession();
                                System.out.println("session = " + session);

                                session.setMaxInactiveInterval(1800);
                                session.setAttribute("loginId", authentication.getName());
                                response.sendRedirect("/");
                            }
                        })
                        .failureHandler(failureHandler)//로그인 실패 후핸들러
                        .permitAll()
                )
                .logout((logoutConfig) -> logoutConfig
                        .logoutUrl("/members/loout")
                        .logoutSuccessUrl("/")
                        .addLogoutHandler(((request, response, authentication) -> {
                            HttpSession session = request.getSession();
                            session.invalidate();
                        }))

                )
                .userDetailsService(myUserDetailService);
        return http.build();
    }

//    private final AuthenticationEntryPoint unauthorizedEntryPoint = ((request, response, authException) -> {
//        ErrorResponse fail = new ErrorResponse(HttpStatus.UNAUTHORIZED, "Spring security unauthorized...123");
//        response.setStatus(HttpStatus.UNAUTHORIZED.value());
//        String json = new ObjectMapper().writeValueAsString(fail);
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter writer = response.getWriter();
//        writer.write(json);
//        writer.flush();
//    });
//
//    private final AccessDeniedHandler accessDeniedHandler = ((request, response, accessDeniedException) -> {
//       ErrorResponse fail = new ErrorResponse(HttpStatus.FORBIDDEN, "Spring security forbidden");
//       response.setStatus(HttpStatus.FORBIDDEN.value());
//       String json = new ObjectMapper().writeValueAsString(fail);
//       response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//       PrintWriter writer = response.getWriter();
//       writer.write(json);
//       writer.flush();
//    });



    @Getter
    @RequiredArgsConstructor
    public class ErrorResponse {
        private final HttpStatus status;
        private final String message;
    }



    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
