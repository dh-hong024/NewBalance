package practice.newbalance.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import practice.newbalance.domain.item.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p join fetch p.category join fetch p.productOptions where p.id = :productId")
    Optional<Product> findProductById(@Param("productId") Long productId);
}
