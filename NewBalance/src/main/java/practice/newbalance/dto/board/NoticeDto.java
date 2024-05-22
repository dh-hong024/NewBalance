package practice.newbalance.dto.board;


import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import practice.newbalance.domain.BaseEntity;
import practice.newbalance.domain.ModifierEntity;
import practice.newbalance.domain.board.Notice;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class NoticeDto {

    private Long id;
    private String noticeTitle;
    private String noticeContent;
    private int noticeCount;
    private String createdBy;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    public Notice toEntity() {
        Notice member = Notice.builder()
                .id(id)
                .noticeTitle(noticeTitle)
                .noticeContent(noticeContent)
                .noticeCount(noticeCount)
                .build();
        return member;
    }

    @QueryProjection
    public NoticeDto(Long id, String noticeTitle, int noticeCount, LocalDateTime modifiedDate) {
        this.id = id;
        this.noticeTitle = noticeTitle;
        this.noticeCount = noticeCount;
        this.modifiedDate = modifiedDate;
    }
}
