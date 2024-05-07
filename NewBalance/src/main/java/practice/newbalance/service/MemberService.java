package practice.newbalance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.newbalance.domain.member.Member;
import practice.newbalance.dto.member.MemberDto;
import practice.newbalance.repository.MemberRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Long join(MemberDto memberDto) {
//        return memberRepository.save(Member.toDTO(memberDto)).getId();
        return memberRepository.save(memberDto.toEntity(passwordEncoder)).getId();
    }


    @Transactional(readOnly = true)
    public boolean checkUserId(String userId){
        return memberRepository.existsByUserId(userId);
    }

    @Transactional(readOnly = true)
    public boolean checkEmail(String email){
        return memberRepository.existsByEmail(email);
    }






//    public Member login(MemberDto memberDto) {
//        return memberRepository.findUser(memberDto.getUserId(), memberDto.getPassword());
//    }

//https://velog.io/@woosim34/Spring-Spring-Security-%EC%84%A4%EC%A0%95-%EB%B0%8F-%EA%B5%AC%ED%98%84SessionSpring-boot3.0-%EC%9D%B4%EC%83%81
//https://shoney.tistory.com/entry/Spring-%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0-Security-Form-Login-%EC%9D%B8%EC%A6%9D-%EA%B8%B0%EB%B3%B8-%EC%84%A4%EC%A0%95-%EC%8A%A4%ED%94%84%EB%A7%81-3-%EB%B2%84%EC%A0%84-%EC%9D%B4%EC%83%81%EC%97%90%EC%84%9C-%EC%82%AC%EC%9A%A9-WebSecurityConfigurerAdapter-%EC%97%86%EC%9D%8C


//    @Override
//    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
//        Member member = memberRepository.findUser(userId).orElseThrow(() -> new UsernameNotFoundException("없는 아이디 입니다."));
//        return User.builder().username(member.getUserId()).password(member.getPassword()).build();
//    }

//    public MemberDto login(MemberDto memberDto) { //entity 객체는 service에서만 사용
//        Optional<Member> idCheck = memberRepository.findUser(memberDto.getUserId());
//        if(idCheck.isPresent()){
//            //조회 결과가 있다
//            Member memberId = idCheck.get(); // Optional에서 꺼냄
//            if(memberId.getPassword().equals(memberDto.getPassword())){
//                //비밀번호 일치
//                //entity -> dto 변환 후 리턴
//                MemberDto dto = memberDto.toEntity(passwordEncoder).toDTO(passwordEncoder);
//                return dto;
//
//            }else {
//                //비밀번호 불일치
//                return null;
//            }
//        }else {
//            //조회 결과가 없다
//            return null;
//        }
//    }

}
