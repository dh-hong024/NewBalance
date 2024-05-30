package practice.newbalance.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter @Getter
public class Category {
    @Id @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "title")
    private CategoryEnum title;

    @Column(name = "ref")
    private int ref;

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();
}
