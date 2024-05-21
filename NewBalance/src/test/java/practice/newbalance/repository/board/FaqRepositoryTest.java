package practice.newbalance.repository.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import practice.newbalance.domain.board.FaqTag;
import practice.newbalance.domain.board.Faqs;
import practice.newbalance.dto.board.FaqDto;
import practice.newbalance.repository.board.query.FaqRepositoryImpl;

import java.util.List;

@SpringBootTest
@Transactional
@ActiveProfiles("local")
class FaqRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private FaqRepositoryImpl faqRepository;

    @Test
    @Rollback(value = false)
    public void 테스트1(){
        Faqs faqs1 = new Faqs();
        faqs1.setQuestion("레디1");
        faqs1.setAnswer("액션");
        faqs1.setTag(FaqTag.WEBSITE);

        Faqs faqs2 = new Faqs();
        faqs2.setQuestion("1레디");
        faqs2.setAnswer("액션");
        faqs2.setTag(FaqTag.WEBSITE);

        em.persist(faqs1);
        em.persist(faqs2);

        em.flush();
        em.clear();
        Pageable pageable = PageRequest.of(0, 5);
        Page<FaqDto> allList = faqRepository.findAll(pageable);

        Assertions.assertThat(allList.getContent().size()).isEqualTo(10);

        String keyword = "레디";
        String tag = "WEBSITE";

        Page<FaqDto> page = faqRepository.findPage(pageable, keyword, tag);
        Assertions.assertThat(page.getContent().size()).isEqualTo(2);


    }

}