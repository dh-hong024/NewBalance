package practice.newbalance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.newbalance.domain.member.Member;
import practice.newbalance.dto.member.MemberDto;
import practice.newbalance.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long join(MemberDto memberDto) {

        //db에 이미 동일한 userId를 가진 회원이 존재하는지 검증
        boolean isUser = memberRepository.existsByUserId(memberDto.getUserId());
        if(isUser) {
            return null;
        }

        return memberRepository.save(memberDto.toEntity(bCryptPasswordEncoder, "ROLE_USER")).getId();
    }

    @Transactional(readOnly = true)
    public boolean checkUserId(String userId){
        return memberRepository.existsByUserId(userId);
    }

    @Transactional(readOnly = true)
    public boolean checkEmail(String email){
        return memberRepository.existsByEmail(email);
    }

}
