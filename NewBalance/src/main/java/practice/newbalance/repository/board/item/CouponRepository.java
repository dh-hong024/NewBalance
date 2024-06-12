package practice.newbalance.repository.board.item;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import practice.newbalance.domain.item.Coupon;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM Coupon c WHERE c.id = :couponId")
    Optional<Coupon> findByCouponId(@Param("couponId") Long couponId);

}
