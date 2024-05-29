package practice.newbalance.domain.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public enum FaqTag {
    ALL("전체"),
    WEBSITE("웹사이트"),
    MILEAGE("통합 마일리지"),
    REPAIR("수선"),
    STORE("매장 관련"),
    PRODUCT("제품"),
    MEMBERS("멤버스"),
    RETURN("교환/반품");

    private final String tagName;
    FaqTag(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }
}
