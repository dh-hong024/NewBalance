package practice.newbalance.dto;

import lombok.*;
import practice.newbalance.domain.member.Member;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MemberDto {

    private String userId;
    private String password;
    private String name;
    private String sex;
    private String email;
    private String phoneNumber;

    public Member toEntity() {
        Member member = Member.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .sex(sex)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
        return member;
    }

}
