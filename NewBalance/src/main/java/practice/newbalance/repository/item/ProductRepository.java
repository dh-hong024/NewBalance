package practice.newbalance.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import practice.newbalance.domain.item.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional(readOnly = true)
    @Query("select p from Product p LEFT JOIN FETCH p.category")
    List<Product> findAllProducts();

    @Transactional(readOnly = true)
    @Query("select p from Product p LEFT JOIN FETCH p.productOptions po LEFT JOIN FETCH p.category WHERE p.id = :productId")
    List<Product> findProductWithProductOptionsById(@Param("productId") Long productId);
}
