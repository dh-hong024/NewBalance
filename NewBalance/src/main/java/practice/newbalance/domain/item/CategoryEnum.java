package practice.newbalance.domain.item;

import lombok.Getter;

@Getter
public enum CategoryEnum {
    MEN("Men"), WOMEN("Women"), KIDS("Kids");
    private final String value;

    private CategoryEnum(String value){
        this.value = value;
    }
}
