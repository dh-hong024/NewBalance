package practice.newbalance.domain.item;


import jakarta.persistence.*;
import lombok.*;
import practice.newbalance.domain.ModifierEntity;
import practice.newbalance.domain.member.Member;
import practice.newbalance.dto.item.CouponDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CouponEnum status;

    @Column(name = "quantity")
    private int quantity; // 쿠폰 수량

    //    @JsonIgnore // 양방향 걸린 곳은 꼭 한곳을 설정
    @OneToMany(mappedBy = "coupon")
    private List<Member> members = new ArrayList<>();

    public void addCoupon(Member member){
        members.add(member);
        member.setCoupon(this);
    }

    public void isCoupon() {
        if (quantity <= 0) {
            throw new IllegalStateException("수량이 이미 부족합니다");
        }
        quantity -= 1;
    }

    public CouponDto toDto(){
        CouponDto couponDto = CouponDto.builder()
                .id(id)
                .benefit(benefit)
                .title(title)
                .period(period)
                .code(code)
                .status(status)
                .quantity(quantity)
                .build();
        return couponDto;
    }

    public Coupon(String benefit, String title, LocalDateTime period, String code, CouponEnum status, int quantity) {
        this.benefit = benefit;
        this.title = title;
        this.period = period;
        this.code = code;
        this.status = status;
        this.quantity = quantity;
    }
}
