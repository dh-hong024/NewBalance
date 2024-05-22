package practice.newbalance.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import practice.newbalance.domain.board.Notice;
import practice.newbalance.domain.member.Member;
import practice.newbalance.repository.board.NoticeRepository;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
@ActiveProfiles("local")
public class NoticeRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private NoticeRepository noticeRepository;


    /**
     * 등록 조회
     */
    @Test
    public void noticeTest(){
        Notice notice = new Notice();
        Member member = new Member();
        member.setUserId("admin");

        em.persist(member);

        notice.setNoticeTitle("공지사항");
        notice.setNoticeContent("공지사항 내용");
        notice.setNoticeCount(0);
        notice.setCreatedDate(LocalDateTime.now());
        notice.setCreatedBy(member.getUserId());


        Notice notice2 = new Notice();
        notice2.setNoticeTitle("공지사항");
        notice2.setNoticeContent("공지사항 내용");
        notice2.setNoticeCount(0);
        notice2.setCreatedDate(LocalDateTime.now());
        notice2.setCreatedBy(member.getUserId());

        em.persist(notice);
        em.persist(notice2);

        em.flush();
        em.clear();

        List<Notice> all = noticeRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);
    }

    @Test
    @Transactional
    public void countTest(){
        Notice notice = new Notice();
        notice.setNoticeTitle("제목1");
        notice.setNoticeContent("공지사항 내용");

        Notice save1 = noticeRepository.save(notice);

        Notice noticeById = noticeRepository.findNoticeById(save1.getId());
        int count = noticeRepository.updateCount(save1.getId());

        Assertions.assertThat(noticeById).isEqualTo(save1);
        Assertions.assertThat(count).isEqualTo(1);
    }

}
