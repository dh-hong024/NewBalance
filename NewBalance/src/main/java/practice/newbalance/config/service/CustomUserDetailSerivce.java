package practice.newbalance.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import practice.newbalance.domain.member.Member;
import practice.newbalance.config.security.CustomUserDetail;
import practice.newbalance.repository.MemberRepository;

//UserDetailService를 구현한 클래스
//Spring Security가 로그인 요청을 가로챌 때, username, password 변수 2개를 가로채는데
//password 부분 처리는 알아서 함
@Service
public class CustomUserDetailSerivce implements UserDetailsService {

    //DI (의존성 주입)
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member memberData = memberRepository.findByUserId(username);

        if(memberData == null) {
            throw new UsernameNotFoundException(username + "을 찾을 수 없습니다.");
        }

        return new CustomUserDetail(memberData);
    }
}
