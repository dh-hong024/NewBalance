package practice.newbalance.domain.item;

import lombok.Getter;

@Getter
public enum CategoryEnum {
    MEN("MEN"), WOMEN("Women"), KIDS("Kids");

    private final String value;

    private CategoryEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
