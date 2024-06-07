package practice.newbalance.repository.item;

import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import practice.newbalance.domain.item.Category;
import practice.newbalance.domain.item.CategoryEnum;
import practice.newbalance.dto.item.CategoryDto;
import practice.newbalance.repository.board.item.CategoryRepository;
import practice.newbalance.repository.board.item.CategoryRepositoryImpl;
import practice.newbalance.repository.board.item.CustomCategoryRepository;

import java.util.List;

import static practice.newbalance.domain.item.CategoryEnum.MEN;
import static practice.newbalance.domain.item.CategoryEnum.WOMEN;

@SpringBootTest
@ActiveProfiles("local")
@Transactional
class CategoryRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryRepositoryImpl customCategoryRepository;

    @Test
    public void findByCategory(){
        Category category1 = new Category();
        category1.setTitle(CategoryEnum.valueOf("MEN"));
        category1.setName("Best");
        category1.setRef(1);
        category1.setStep(1);

        em.persist(category1);

        Category save = categoryRepository.save(category1);

        Category category2 = new Category();
        category2.setTitle(CategoryEnum.valueOf("WOMEN"));
        category2.setName("라이프스타일");
        category2.setRef(2);
        category2.setStep(1);

        em.persist(category2);

        em.flush();
        em.clear();
        List<CategoryDto> byCategory1 = customCategoryRepository.findByCategory(String.valueOf(category1.getTitle()));
        List<CategoryDto> byCategory2 = customCategoryRepository.findByCategory(String.valueOf(category2.getTitle()));

        List<CategoryDto> categoryDto = byCategory1;
        List<CategoryDto> categoryDto2 = byCategory2;

        for(CategoryDto categoryDtoList : categoryDto) {
            Assertions.assertThat(categoryDtoList.toEntity().getTitle()).isEqualTo(MEN);
        }
        for(CategoryDto categoryDtoList : categoryDto2) {
            Assertions.assertThat(categoryDtoList.toEntity().getTitle()).isEqualTo(MEN);
        }

    }
}