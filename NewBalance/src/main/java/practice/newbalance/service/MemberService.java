package practice.newbalance.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import practice.newbalance.domain.member.Member;
import practice.newbalance.dto.member.MemberDto;
import practice.newbalance.repository.MemberRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long join(MemberDto memberDto) {

        //db에 이미 동일한 userId를 가진 회원이 존재하는지 검증
//        boolean isUser = memberRepository.existsByUserId(memberDto.getUserId());
//        if(isUser) {
//            return null;
//        }

        if(memberDto.getUserId().equals("admin")){
            return memberRepository.save(memberDto.toEntity(bCryptPasswordEncoder, "ROLE_ADMIN")).getId();
        }

        return memberRepository.save(memberDto.toEntity(bCryptPasswordEncoder, "ROLE_USER")).getId();
    }

    public Map<String, Object> inquiryFindId(MemberDto dto){
        Map<String, Object> result = new HashMap<>();
        String findUserId = memberRepository.findInquiryIdByNameAndPhoneNumber(
                dto.getName(),
                dto.getPhoneNumber()
        );
        if(findUserId == null){
            result.put("userId", null);
            return result;
        }

        result.put("userId", findUserId);
        result.put("name", dto.getName());
        result.put("phoneNumber", dto.getPhoneNumber());

        return result;
    }

    @Transactional
    public Map<String, Object> inquiryResetPw(String userId, String name, String phoneNumber){
        Map<String, Object> result = new HashMap<>();
        Optional<Member> findMember = memberRepository.findByUserId(userId, name, phoneNumber);

        if(findMember.isPresent()){
            String tempPw = UUID.randomUUID().toString().substring(0, 8);

            //todo:임시 비밀번호 이메일 전송 로직 필요
            findMember.get().setPassword(bCryptPasswordEncoder.encode(tempPw));
            result.put("result", true);
        }else{
            result.put("result", false);
        }

        return result;
    }




}
