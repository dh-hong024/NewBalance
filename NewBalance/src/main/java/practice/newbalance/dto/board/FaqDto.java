package practice.newbalance.dto.board;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import practice.newbalance.domain.board.FaqTag;

@Data
@NoArgsConstructor
public class FaqDto {
    private Long id;
    private String question;
    private String answer;
    private FaqTag tag;

    @QueryProjection
    public FaqDto(Long id, String question, String answer, FaqTag tag){
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.tag = tag;
    }
}
