package practice.newbalance.dto.item;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class ProductOptionDto {

    private String color;
    private List<ProductOptionDtoDetails> productOptionDtoDetailsList;

    @QueryProjection
    public ProductOptionDto(String color, List<ProductOptionDtoDetails> productOptionDtoDetailsList) {
        this.color = color;
        this.productOptionDtoDetailsList = productOptionDtoDetailsList;
    }
}
