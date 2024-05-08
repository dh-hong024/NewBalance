package practice.newbalance.web.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import practice.newbalance.dto.member.MemberDto;
import practice.newbalance.repository.MemberRepository;

@RequiredArgsConstructor
@Component
public class CheckUserIdValidator extends AbstractValidator<MemberDto>{

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void doValidate(MemberDto memberDto, Errors errors) {
        if(memberRepository.existsByUserId(memberDto.toEntity(bCryptPasswordEncoder,"ROLE_USER").getUserId())) {
            // 중복인 경우
            errors.rejectValue("userId", "아이디 중복 오류", "이미 사용 중인 아이디 입니다.");
        }
    }
}
