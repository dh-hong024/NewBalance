package practice.newbalance.domain.board;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import practice.newbalance.domain.ModifierEntity;

@Entity
@Getter @Setter
@Table(name = "FAQ")
public class Faqs extends ModifierEntity {

    @Id @GeneratedValue
    @Column(name = "faq_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private FaqTag tag;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;
}
