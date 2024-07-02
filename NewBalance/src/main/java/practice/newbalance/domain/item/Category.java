package practice.newbalance.domain.item;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import practice.newbalance.dto.item.CategoryDto;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "title")
    private CategoryEnum title;

    @Column(name = "ref")
    private int ref;

    @Column(name = "step")
    private int step;

    @JsonBackReference
    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();

    public CategoryDto toDto() {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(id)
                .name(name)
                .title(title)
                .ref(ref)
                .step(step)
                .build();
        return categoryDto;
    }
    public Category(String id) {
        this.id = Long.parseLong(id);
    }

}
