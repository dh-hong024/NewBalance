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

//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;

    private String noticeTitle;
    private String noticeContent;

    @Column(name = "notice_count" ,columnDefinition = "int default 0")
    private int noticeCount;


    // member 양방향 연관관계 매핑
//    public void setMember(Member member){
//        this.member = member;
//        member.getNotices().add(this);
//    }

}
