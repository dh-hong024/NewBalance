package practice.newbalance.dto.item;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import practice.newbalance.domain.item.Product;

import java.util.List;

@Data
public class ProductOptionDto {


    private String color;
    private List<ProductOptionDtoDetails> productOptionDtoDetailsList;

//    private String size;
//    private int quantity;

}
