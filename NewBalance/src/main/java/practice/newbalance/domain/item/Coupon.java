package practice.newbalance.domain.item;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import practice.newbalance.domain.ModifierEntity;
import practice.newbalance.domain.member.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon extends ModifierEntity {

    @Id
    @GeneratedValue
    @Column(name = "coupon_id")
    private Long id;

    @Column(name = "benefit")
    private String benefit;

    @Column(name = "title")
    private String title;

    @Column(name = "peroid")
    private LocalDateTime period;

    @Column(name = "code")
    private String code;

    @Column(name = "status")
    private CouponEnum status;

    @Column(name = "quantity")
    private int quantity; // 쿠폰 수량

    //    @JsonIgnore // 양방향 걸린 곳은 꼭 한곳을 설정
    @OneToMany(mappedBy = "member")
    private List<Member> members = new ArrayList<>();
}
