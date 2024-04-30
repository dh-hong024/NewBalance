package practice.newbalance.web.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import practice.newbalance.dto.member.MemberDto;
import practice.newbalance.repository.MemberRepository;

@RequiredArgsConstructor
@Component
public class CheckEmailValidator extends AbstractValidator<MemberDto> {

    private final MemberRepository memberRepository;

    @Override
    protected void doValidate(MemberDto memberDto, Errors errors) {
        if(memberRepository.existsByEmail(memberDto.toEntity().getEmail())){
            // 중복인 경우
            errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일 입니다." );
        }
    }
}
