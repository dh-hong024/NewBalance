package practice.newbalance.service.item;

import org.springframework.stereotype.Service;
import practice.newbalance.repository.board.item.CouponRepository;

@Service
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    public CouponServiceImpl(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }
}