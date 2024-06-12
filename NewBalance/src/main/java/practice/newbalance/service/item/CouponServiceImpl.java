package practice.newbalance.service.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.newbalance.domain.item.Coupon;
import practice.newbalance.dto.item.CouponDto;
import practice.newbalance.repository.board.item.CouponRepository;
import practice.newbalance.repository.board.item.CustomCouponRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final CustomCouponRepository customCouponRepository;

    @Autowired
    public CouponServiceImpl(CouponRepository couponRepository, CustomCouponRepository customCouponRepository) {
        this.couponRepository = couponRepository;
        this.customCouponRepository = customCouponRepository;
    }

    @Override
    public List<CouponDto> findCouponAll(int offset, int limit) {
        return customCouponRepository.findCouponAll(offset, limit);
    }

    @Override
    public long getCouponCount() {
        return couponRepository.count();
    }

    // test용
    @Override
    public Coupon getCoupon() {
        return couponRepository.findById(1L).get();
    }

    @Transactional
    public void issueCoupon(long couponId){
        Coupon coupon = couponRepository.findByCouponId(couponId).orElseThrow(() -> new IllegalArgumentException("등록되지 않은 쿠폰입니다."));

        if (coupon.getQuantity() <= 0) {
            throw new IllegalArgumentException("수량이 부족합니다");
        }

        coupon.isCoupon();
        couponRepository.save(coupon);
    }
}