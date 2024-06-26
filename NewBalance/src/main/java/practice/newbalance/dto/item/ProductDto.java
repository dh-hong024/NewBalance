package practice.newbalance.dto.item;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import practice.newbalance.domain.item.Category;
import practice.newbalance.domain.item.Product;
import practice.newbalance.domain.item.ProductOption;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Setter @Getter
public class ProductDto {

    private Long id;

    private String title;

    private String content;

    private List<ProductOptionDto> productOptions = new ArrayList<>();

    private String code;

    private String contry;

    private String material;

    private String features;

    private int price;

    private LocalDateTime manufactureDate;

    private Category category;

    private String option;

    @QueryProjection
    public ProductDto(Long id, String title, String content, String code,
                      String contry, String material, String features, int price,
                      LocalDateTime manufactureDate, Category category, List<ProductOptionDto> productOptions){
        this.id = id;
        this.title = title;
        this.content = content;
        this.code = code;
        this.contry = contry;
        this.material = material;
        this.features = features;
        this.price = price;
        this.manufactureDate = manufactureDate;
        this.category = category;
        this.productOptions = productOptions;
    }


    public Product toEntity(){
        return Product.builder()
                .id(id)
                .title(title)
                .content(content)
                .code(code)
                .contry(contry)
                .material(material)
                .features(features)
                .price(price)
                .manufactureDate(manufactureDate)
                .category(category)
                .build();
    }
}
