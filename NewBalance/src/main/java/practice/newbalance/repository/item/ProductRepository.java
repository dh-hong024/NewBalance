package practice.newbalance.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.newbalance.domain.item.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
