package practice.newbalance.dto.member;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import practice.newbalance.domain.member.Member;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MemberDto {

    @NotBlank(message = "아이디는 필수 입력값입니다.")
    @Pattern(regexp = "^[a-z0-9]{4,20}$", message = "아이디는 영어 소문자와 숫자만 사용하여 4~20자리여야 합니다.")
    private String userId;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$", message = "비밀번호는 8~16자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
    private String password;
    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String name;
    @NotBlank(message = "성별을 선택해주세요.")
    private String sex;
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email
    private String email;
    private String phoneNumber;

    @Column(name = "role")
    private String role;

    public Member toEntity(BCryptPasswordEncoder bCryptPasswordEncoder, String role) {
        Member member = Member.builder()
                .userId(userId)
                .password(bCryptPasswordEncoder.encode(password))
                .name(name)
                .sex(sex)
                .email(email)
                .phoneNumber(phoneNumber)
                .role(role)
                .build();
        return member;
    }

    public MemberDto(Member member, String password) {
        this.userId = getUserId();
        this.password = password;
    }
}
