package practice.newbalance.domain.item;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.HttpStatus;
import practice.newbalance.common.ErrorCode;
import practice.newbalance.common.exception.CustomException;
import practice.newbalance.domain.ModifierEntity;
import practice.newbalance.dto.item.ProductDto;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends ModifierEntity {

    @Id @GeneratedValue
    @Column(name = "product_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "price")
    private int price;

    @Column(name = "color")
    private String color;

    @Column(name = "size")
    private String size;

    @Column(name = "code")
    private String code;

    @Column(name = "contry")
    private String contry;

    @Column(name = "material")
    private String material;

    @Column(name = "features")
    private String features;

    @Column(name = "manufacture_date")
    private LocalDateTime manufactureDate;

    @Column(name = "quantity")
    private int quantity;

    /**
     * 상품 수량 증가
     * @param quantity
     */
    public void addStock(int quantity){
        this.quantity += quantity;
    }

    /**
     * 상품 수량 감소
     * @param quantity
     */
    public void removeStock(int quantity){
        int restStock = this.quantity - quantity;
        if(restStock < 0){
            throw new CustomException(HttpStatus.BAD_REQUEST, ErrorCode.OUT_OF_STOCK);
        }
        this.quantity = restStock;
    }

    public ProductDto toDTO(){
        return ProductDto.builder()
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
