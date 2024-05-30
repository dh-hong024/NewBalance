package practice.newbalance.dto.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import practice.newbalance.domain.item.Category;
import practice.newbalance.domain.item.Product;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class ProductDto {

    private long id;

    private String title;

    private String content;

    private int price;

    private String color;

    private String size;

    private String code;

    private String contry;

    private String material;

    private String features;

    private LocalDateTime manufactureDate;

    private Category category;

    private int quantity;

    public Product toEntity(){
        return Product.builder()
                .id(id)
                .title(title)
                .content(content)
                .price(price)
                .color(color)
                .size(size)
                .code(code)
                .contry(contry)
                .material(material)
                .features(features)
                .manufactureDate(manufactureDate)
                .category(category)
                .quantity(quantity)
                .build();
    }
}
