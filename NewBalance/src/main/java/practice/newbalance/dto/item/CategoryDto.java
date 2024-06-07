package practice.newbalance.dto.item;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import practice.newbalance.domain.item.Category;
import practice.newbalance.domain.item.CategoryEnum;

@NoArgsConstructor
@Builder
@Getter
@Setter
public class CategoryDto {

    private Long id;
    private String name;
    private CategoryEnum title;
    private int ref;
    private int step;

    public Category toEntity(){
        Category.CategoryBuilder categoryBuilder = Category.builder()
                .name(name)
                .title(title)
                .ref(ref)
                .step(step);
        if(id != null){
            categoryBuilder.id(id);
        }
        return categoryBuilder.build();
    }

    @QueryProjection
    public CategoryDto(Long id, String name, CategoryEnum title, int ref, int step) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.ref = ref;
        this.step = step;
    }
}
