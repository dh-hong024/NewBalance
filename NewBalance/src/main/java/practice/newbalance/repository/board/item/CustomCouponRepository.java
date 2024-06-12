package practice.newbalance.repository.board.item;

import practice.newbalance.dto.item.CouponDto;

import java.util.List;

public interface CustomCouponRepository {

    List<CouponDto> findCouponAll(int offset, int limit);


}
