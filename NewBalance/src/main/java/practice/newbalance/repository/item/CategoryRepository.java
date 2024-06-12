package practice.newbalance.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.newbalance.domain.item.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByTitle(String title);
}
