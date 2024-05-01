package practice.newbalance.domain.member;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import practice.newbalance.dto.member.MemberDto;

@Entity
@Table(name = "member")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private String sex;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    public MemberDto toDTO() {
        MemberDto memberDto = MemberDto.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .sex(sex)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
        return memberDto;
    }
}
