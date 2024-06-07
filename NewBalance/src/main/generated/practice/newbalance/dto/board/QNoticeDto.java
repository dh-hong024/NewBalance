package practice.newbalance.dto.board;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * practice.newbalance.dto.board.QNoticeDto is a Querydsl Projection type for NoticeDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QNoticeDto extends ConstructorExpression<NoticeDto> {

    private static final long serialVersionUID = 1896709137L;

    public QNoticeDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> noticeTitle, com.querydsl.core.types.Expression<String> noticeContent, com.querydsl.core.types.Expression<Integer> noticeCount, com.querydsl.core.types.Expression<java.time.LocalDateTime> modifiedDate) {
        super(NoticeDto.class, new Class<?>[]{long.class, String.class, String.class, int.class, java.time.LocalDateTime.class}, id, noticeTitle, noticeContent, noticeCount, modifiedDate);
    }

}

