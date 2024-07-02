package practice.newbalance.repository.item.query;

import com.querydsl.jpa.impl.JPAQuery;
import practice.newbalance.dto.item.CategoryDto;

import java.util.List;

public interface CustomCategoryRepository {

    List<CategoryDto> findByCategory(String title);

    List<CategoryDto> findDetailedCategories(String parentTitle, Integer subCategoryRef);
}
