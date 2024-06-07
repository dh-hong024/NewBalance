package practice.newbalance.dto.board;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import practice.newbalance.domain.board.FaqTag;
import practice.newbalance.domain.board.Faqs;

@Data
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FaqDto {
    private Long id;

    @NotBlank(message = "질문은 필수입니다.")
    private String question;

    @NotBlank(message = "답변은 필수입니다.")
    private String answer;

    @NotBlank(message = "태그는 필수입니다.")
    private FaqTag tag; //Enum

    @QueryProjection
    public FaqDto(Long id, String question, String answer, FaqTag tag){
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.tag = tag;
    }

    public Faqs toEntity(){
        Faqs faqs = Faqs.builder()
                .id(id)
                .question(question)
                .answer(answer)
                .tag(tag)
                .build();
        return faqs;
    }
}
