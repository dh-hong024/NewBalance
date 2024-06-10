package practice.newbalance.dto.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import practice.newbalance.domain.item.CategoryEnum;
import practice.newbalance.domain.member.Member;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class CouponDto {
    private Long id;
    private String benefit;
    private String title;
    private LocalDateTime period;
    private String code;
    private CategoryEnum status;
    private int quantity;

    private Member member;

    public CouponDto(Long id, String benefit, String title, LocalDateTime period, String code, CategoryEnum status, int quantity, Member member) {
        this.id = id;
        this.benefit = benefit;
        this.title = title;
        this.period = period;
        this.code = code;
        this.status = status;
        this.quantity = quantity;
        this.member = member;
    }
}
