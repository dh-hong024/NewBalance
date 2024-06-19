package practice.newbalance.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.newbalance.domain.item.Cart;
import practice.newbalance.domain.item.ProductOption;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
    public Cart findByColorAndSize(String color, String size);
}
