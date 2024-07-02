package practice.newbalance.domain.item;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_opt")
public class ProductOption {

    @Id
    @GeneratedValue
    @Column(name = "product_opt_id")
    private Long id;

    private String color;
    private String size;
    private int quantity;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

}
