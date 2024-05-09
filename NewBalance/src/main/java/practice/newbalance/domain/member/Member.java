package practice.newbalance.domain.member;

import jakarta.persistence.*;
import lombok.*;
import practice.newbalance.dto.member.MemberDto;

import java.util.LinkedHashMap;
import java.util.Map;

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

    @Column(name = "role")
    private String role;

    public MemberDto toDTO() {
        MemberDto memberDto = MemberDto.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .sex(sex)
                .email(email)
                .phoneNumber(getPhoneNumber())
                .role(role)
                .build();
        return memberDto;
    }

}
