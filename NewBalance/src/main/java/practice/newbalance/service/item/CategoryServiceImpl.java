package practice.newbalance.service.item;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.newbalance.domain.item.Category;
import practice.newbalance.dto.item.CategoryDto;
import practice.newbalance.repository.board.item.CategoryRepository;
import practice.newbalance.repository.board.item.CustomCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private final CustomCategoryRepository customCategoryRepository;
    private final CategoryRepository categoryRepository;


    public CategoryServiceImpl(CustomCategoryRepository customCategoryRepository,CategoryRepository categoryRepository) {
        this.customCategoryRepository = customCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> findByCategory(String title) {
        return customCategoryRepository.findByCategory(title);
    }

    @Override
    public CategoryDto addItem(CategoryDto categoryDto) {

//        boolean isChecked = categoryRepository.existsByTitle(String.valueOf(categoryDto.getTitle()));
//        중복체크 로직
//        if(isChecked){
//
//        }
        Category saveCategory = categoryRepository.save(categoryDto.toEntity());
        return saveCategory.toDto();
    }

    @Override
    @Transactional
    public void editItem(Long categoryId, CategoryDto categoryDto) {

        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if (optionalCategory.isPresent()) {
            Category findCategory = optionalCategory.get();


            findCategory.setName(categoryDto.getName());
            findCategory.setTitle(categoryDto.getTitle());
            findCategory.setRef(categoryDto.getRef());
            findCategory.setStep(categoryDto.getStep());

            categoryRepository.save(findCategory);
        }
    }
    @Override
    public void deleteItem(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
