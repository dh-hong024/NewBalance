package practice.newbalance.dto.member;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import practice.newbalance.domain.member.Member;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@AllArgsConstructor
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

    @NotBlank(message = "전화번호를 입력해주세요.")
    private String prefixNumber;

    @NotBlank(message = "전화번호를 입력해주세요.")
    @Pattern(regexp = "\\d{3,4}", message = "전화번호 3자리 또는 4자리만 입력해주세요.")
    private String number;

    @NotBlank(message = "전화번호를 입력해주세요.")
    @Pattern(regexp = "\\d{4}", message = "전화번호 뒷자리는 4자리 입니다.")
    private String suffixNumber;

    private String phoneNumber;

    @Column(name = "role")
    private String role;

    //성별정보
    private String gender;

    //생년월일 정보
    private String year;
    private String month;
    private String day;

    //select box 정보 리스트
    private Collection<String> genderList;

    private Collection<String> prefixNumberList;
    private Collection<String> telecoms;

    public MemberDto(){

        //전화정보 리스트 세팅
        Map<String, String> prefixNumber = new LinkedHashMap<>();
        prefixNumber.put("010", "010");
        prefixNumber.put("011", "011");
        prefixNumber.put("017", "017");
        prefixNumber.put("016", "016");
        prefixNumber.put("019", "019");

        //통신사 정보 리스트 세팅
        Map<String, String> telecoms = new LinkedHashMap<>();
        telecoms.put("SKT", "SKT");
        telecoms.put("KT", "KT");
        telecoms.put("LG", "LG U+");

        //성별 리스트 세팅
        Map<String, String> genders = new LinkedHashMap<>();
        genders.put("male", "남자");
        genders.put("female", "여자");

        this.telecoms = telecoms.values();
        this.prefixNumberList = prefixNumber.values();
        this.genderList = genders.values();
    }

    public String getPhoneNumber(){
        return prefixNumber + "-" + number + "-" + suffixNumber;
    }

    public String getYearOfBirth(){
        return year + month + day;
    }

    public Member toEntity(BCryptPasswordEncoder bCryptPasswordEncoder, String role) {
        Member member = Member.builder()
                .userId(userId)
                .password(bCryptPasswordEncoder.encode(password))
                .name(name)
                .sex(sex)
                .email(email)
                .phoneNumber(getPhoneNumber())
                .role(role)
                .build();
        return member;
    }

    @QueryProjection
    public MemberDto(String userId, String name, String sex, String email, String role){
        this.userId = userId;
        this.name = name;
        this.sex = sex;
        this.email = email;
        this. role = role;
    }
}
