package practice.newbalance.service.item;

import practice.newbalance.dto.item.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> findByCategory(String title);

    CategoryDto addItem(CategoryDto categoryDto);
    void editItem(Long categoryId, CategoryDto categoryDto);
    void deleteItem(Long categoryId);

}
