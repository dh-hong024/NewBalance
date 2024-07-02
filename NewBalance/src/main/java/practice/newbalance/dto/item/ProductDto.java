package practice.newbalance.dto.item;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import practice.newbalance.domain.item.Category;
import practice.newbalance.domain.item.Product;
import practice.newbalance.domain.item.ProductOption;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private long id;

    private String title;

    private String content;

    private List<ProductOption> productOptions;

    private String code;

    private String contry;

    private String material;

    private String features;

    private int price;

    private LocalDate manufactureDate;

    private Category category;

    private String option;

    private List<String> imageUrls;

    public Product toEntity(){
        return Product.builder()
                .id(id)
                .title(title)
                .content(content)
                .productOptions(productOptions)
                .code(code)
                .contry(contry)
                .material(material)
                .features(features)
                .price(price)
                .manufactureDate(manufactureDate)
                .category(category)
                .imageUrls(imageUrls)
                .build();
    }
}
