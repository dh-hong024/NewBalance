package practice.newbalance.dto.item;

import lombok.Data;

@Data
public class CartDto {
    private Long productId;
    private String color;
    private String size;
    private int count;
}
