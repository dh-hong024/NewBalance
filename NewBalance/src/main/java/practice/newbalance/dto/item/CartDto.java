package practice.newbalance.dto.item;

import lombok.Data;

@Data
public class CartDto {
    private String title;
    private String color;
    private String size;
    private int count;
}
