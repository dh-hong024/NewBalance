package practice.newbalance.repository.item.query;

import practice.newbalance.dto.item.CouponDto;

import java.util.List;

public interface CustomCouponRepository {

    List<CouponDto> findCouponAll(int offset, int limit);


}
