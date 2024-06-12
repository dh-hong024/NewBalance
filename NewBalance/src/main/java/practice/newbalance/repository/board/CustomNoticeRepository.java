package practice.newbalance.repository.board;

import practice.newbalance.dto.board.NoticeDto;

import java.util.List;

public interface CustomNoticeRepository {
    List<NoticeDto> findNoticeAll(int offset, int limit);

}
