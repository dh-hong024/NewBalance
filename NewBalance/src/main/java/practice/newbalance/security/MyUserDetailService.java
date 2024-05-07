package practice.newbalance.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import practice.newbalance.domain.member.Member;
import practice.newbalance.repository.MemberRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MyUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        Member member = memberRepository.findUser(userId).orElseThrow(() ->
                new UsernameNotFoundException("사용자가 존재하지 않습니다."));
        return User.builder().username(member.getUserId()).password(member.getPassword()).build();
    }
}
