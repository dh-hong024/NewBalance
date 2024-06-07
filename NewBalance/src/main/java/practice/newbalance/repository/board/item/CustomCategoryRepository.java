package practice.newbalance.repository.board.item;

import com.querydsl.jpa.impl.JPAQuery;
import practice.newbalance.dto.item.CategoryDto;

import java.util.List;

public interface CustomCategoryRepository {

    List<CategoryDto> findByCategory(String title);
}
