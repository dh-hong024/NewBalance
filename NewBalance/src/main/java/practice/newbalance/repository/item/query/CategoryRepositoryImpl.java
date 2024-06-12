package practice.newbalance.repository.item.query;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import practice.newbalance.domain.item.CategoryEnum;
import practice.newbalance.domain.item.QCategory;
import practice.newbalance.dto.item.CategoryDto;
import practice.newbalance.repository.item.query.CustomCategoryRepository;

import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CustomCategoryRepository {

    private final JPAQueryFactory queryFactory;

    public CategoryRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public BooleanExpression titleChecked(String title){
        QCategory category = QCategory.category;
        return category.title.eq(CategoryEnum.valueOf(title));
    }

    @Override
    public List<CategoryDto> findByCategory(String title) {
        QCategory category = QCategory.category;
        return queryFactory.select(Projections.constructor(
                        CategoryDto.class,
                        category.id,
                        category.name,
                        category.title,
                        category.ref,
                        category.step))
                .from(category)
                .where(titleChecked(title))
                .orderBy(category.id.asc())
                .fetch();
    }
}
