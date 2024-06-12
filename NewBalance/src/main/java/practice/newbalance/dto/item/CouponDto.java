package practice.newbalance.dto.item;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import practice.newbalance.domain.item.CouponEnum;
import practice.newbalance.domain.member.Member;

import java.time.LocalDateTime;
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CouponDto {
    private Long id;
    private String benefit;
    private String title;
    private LocalDateTime period;
    private String code;
    private CouponEnum status;
    private int quantity;

    private Member member;

    @QueryProjection
    public CouponDto(Long id, String benefit, String title, LocalDateTime period, String code, CouponEnum status, int quantity) {
        this.id = id;
        this.benefit = benefit;
        this.title = title;
        this.period = period;
        this.code = code;
        this.status = status;
        this.quantity = quantity;
    }
}
