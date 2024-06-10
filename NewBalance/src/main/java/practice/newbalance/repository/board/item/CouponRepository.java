package practice.newbalance.repository.board.item;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.newbalance.domain.item.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

}
