package practice.newbalance.repository.board;

import practice.newbalance.domain.board.Notice;
import practice.newbalance.dto.board.NoticeDto;

import java.util.List;

public interface NoticeRepositoryCustom {
    List<NoticeDto> findNoticeAll(int offset, int limit);

}
