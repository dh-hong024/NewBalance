package practice.newbalance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.newbalance.domain.member.Member;
import practice.newbalance.dto.member.MemberDto;
import practice.newbalance.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(MemberDto memberDto) {
        return memberRepository.save(memberDto.toEntity()).getId();
    }

    public Member login(MemberDto memberDto) {
        return memberRepository.findUser(memberDto.getUserId(), memberDto.getPassword());
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
