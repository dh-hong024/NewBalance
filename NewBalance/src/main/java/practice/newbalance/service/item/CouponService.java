package practice.newbalance.service.item;

import org.springframework.stereotype.Service;
import practice.newbalance.domain.item.Coupon;
import practice.newbalance.dto.item.CouponDto;

import java.util.List;

public interface CouponService {

    List<CouponDto> findCouponAll(int offset, int limit);

    long getCouponCount();

    Coupon getCoupon();
}
