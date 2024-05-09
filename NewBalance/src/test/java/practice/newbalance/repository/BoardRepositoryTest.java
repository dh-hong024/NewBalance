package practice.newbalance.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import practice.newbalance.domain.board.Faqs;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("local")
class BoardRepositoryTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @Rollback(value = false)
    public void test(){
        Faqs faqs = new Faqs();
        faqs.setQuestion("제품은");
        faqs.setAnswer("이겁니다.");

        em.persist(faqs);
        em.flush();
        em.clear();

        Faqs faqs1 = em.find(Faqs.class, 1);

        Assertions.assertThat(faqs1.getModifiedBy()).isNotNull();
        Assertions.assertThat(faqs1.getModifiedDate()).isNotNull();
        Assertions.assertThat(faqs1.getCreatedBy()).isNotNull();
        Assertions.assertThat(faqs1.getCreatedDate()).isNotNull();

    }

}