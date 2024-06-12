package practice.newbalance.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import practice.newbalance.domain.board.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long>, CustomNoticeRepository {

    Notice findNoticeById(Long id);

    @Modifying
    @Query("update Notice n set n.noticeCount = n.noticeCount + 1 where n.id =:noticeId")
    int updateCount(@Param(value = "noticeId") Long noticeId);
}
