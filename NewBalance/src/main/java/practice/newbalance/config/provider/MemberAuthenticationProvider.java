package practice.newbalance.config.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import practice.newbalance.config.security.CustomUserDetail;
import practice.newbalance.config.service.CustomUserDetailSerivce;

//AuthenticationProvider를 구현한 클래스
@Component
public class MemberAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailSerivce customUserDetailSerivce;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName(); //사용자가 입력한 id
        String password = (String) authentication.getCredentials(); //사용자가 입력한 password

        // 생성해둔 CustomUserDetailSerivce 에서 loadUserByUsername 메소드를 호출하여 사용자 정보를 가져옴
        CustomUserDetail customUserDetail = (CustomUserDetail) customUserDetailSerivce.loadUserByUsername(username);


        //사용자가 입력한 password와 DB에 저장된 password 비교

        //db에 저장된 password
        String dbPassword = customUserDetail.getPassword();
        //암호화 방식(BCryptPasswordEncoder)를 사용하여 비밀번호를 비교
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(password, dbPassword)) {
            System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
            throw new BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        //인증이 성공하면 UsernamePasswordAuthenticationToken 객체를 반환
        //해당 객체는 Authentication 객체로 추후 인증이 끝나고 SecurityContextHolder.getrContext()에 저장
        return new UsernamePasswordAuthenticationToken(customUserDetail, null, customUserDetail.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
