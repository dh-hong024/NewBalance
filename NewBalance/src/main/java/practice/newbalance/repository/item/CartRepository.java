package practice.newbalance.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import practice.newbalance.domain.item.Cart;
import practice.newbalance.domain.item.Product;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("select c from Cart c join fetch c.product")
    public List<Cart> findByMemberId(Long memberId);

    public void deleteByMemberId(Long memberId);
}
