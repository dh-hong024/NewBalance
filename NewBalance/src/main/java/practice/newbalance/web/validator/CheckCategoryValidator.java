package practice.newbalance.web.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import practice.newbalance.dto.item.CategoryDto;
import practice.newbalance.repository.board.item.CategoryRepository;


@RequiredArgsConstructor
@Component
public class CheckCategoryValidator extends AbstractValidator<CategoryDto> {

    private final CategoryRepository categoryRepository;

    @Override
    protected void doValidate(CategoryDto categoryDto, Errors errors) {
            if(categoryRepository.existsByTitle(String.valueOf(categoryDto.toEntity().getTitle()))){
            // 중복인 경우
            errors.rejectValue("category", "카테고리 중복 오류", "이미 사용중인 카테고리 입니다." );
        }
    }
}
