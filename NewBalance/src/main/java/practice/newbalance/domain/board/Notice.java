package practice.newbalance.domain.board;

import jakarta.persistence.*;
import lombok.*;
import practice.newbalance.domain.BaseEntity;
import practice.newbalance.domain.ModifierEntity;

@Entity
@Table(name = "notice")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notice extends ModifierEntity {

    @Id @GeneratedValue
    @Column(name = "notice_id")
    private Long id;

    private String noticeTitle;
    private String noticeContent;

    @Column(name = "notice_count" ,columnDefinition = "int default 0")
    private int noticeCount;

}
