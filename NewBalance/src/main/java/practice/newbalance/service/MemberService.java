package practice.newbalance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.newbalance.dto.MemberDto;
import practice.newbalance.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(MemberDto memberDto) {

        return memberRepository.save(memberDto.toEntity()).getId();
    }


}
