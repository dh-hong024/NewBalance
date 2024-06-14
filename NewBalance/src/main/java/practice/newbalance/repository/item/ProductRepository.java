package practice.newbalance.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import practice.newbalance.domain.item.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.size = :size and p.color = :color and p.title = :title")
    public Product findProductByOption(@Param("size") String size, @Param("color")String color,
                                       @Param("title") String title);
}
