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
//        return memberRepository.save(Member.toDTO(memberDto)).getId();
        return memberRepository.save(memberDto.toEntity()).getId();
    }

//    public Member login(MemberDto memberDto) {
//        return memberRepository.findUser(memberDto.getUserId(), memberDto.getPassword());
//    }

    public MemberDto login(MemberDto memberDto) { //entity 객체는 service에서만 사용
        Optional<Member> idCheck = memberRepository.findUser(memberDto.getUserId());
        if(idCheck.isPresent()){
            //조회 결과가 있다
            Member memberId = idCheck.get(); // Optional에서 꺼냄
            if(memberId.getPassword().equals(memberDto.getPassword())){
                //비밀번호 일치
                //entity -> dto 변환 후 리턴
                MemberDto dto = memberDto.toEntity().toDTO();
                return dto;

            }else {
                //비밀번호 불일치
                return null;
            }
        }else {
            //조회 결과가 없다
            return null;
        }
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
