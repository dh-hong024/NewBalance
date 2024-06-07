package practice.newbalance.repository.board.item;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.newbalance.domain.item.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryById(Long title);
    boolean existsByTitle(String title);
}
