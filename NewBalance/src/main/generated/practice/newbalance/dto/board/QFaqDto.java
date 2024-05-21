package practice.newbalance.dto.board;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * practice.newbalance.dto.board.QFaqDto is a Querydsl Projection type for FaqDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QFaqDto extends ConstructorExpression<FaqDto> {

    private static final long serialVersionUID = -385184001L;

    public QFaqDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> question, com.querydsl.core.types.Expression<String> answer, com.querydsl.core.types.Expression<practice.newbalance.domain.board.FaqTag> tag) {
        super(FaqDto.class, new Class<?>[]{long.class, String.class, String.class, practice.newbalance.domain.board.FaqTag.class}, id, question, answer, tag);
    }

}

