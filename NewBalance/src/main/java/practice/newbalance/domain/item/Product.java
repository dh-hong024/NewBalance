package practice.newbalance.domain.item;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import practice.newbalance.domain.ModifierEntity;
import practice.newbalance.dto.item.ProductDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOption> productOptions = new ArrayList<>();

    @Column(name = "code")
    private String code;

    @Column(name = "contry")
    private String contry;

    @Column(name = "material")
    private String material;

    @Column(name = "features")
    private String features;

    @Column(name = "price")
    private int price;

    @Column(name = "manufacture_date")
    private LocalDateTime manufactureDate;

    public ProductDto toDTO(){
        return ProductDto.builder()
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

    // option 편의 메서드
    public void addOption(ProductOption productOption){
        productOption.setProduct(this);
        this.productOptions.add(productOption);
    }

}
