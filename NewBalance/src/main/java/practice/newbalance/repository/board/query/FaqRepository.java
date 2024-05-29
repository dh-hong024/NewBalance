package practice.newbalance.repository.board.query;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.newbalance.domain.board.Faqs;
import practice.newbalance.domain.board.Notice;

public interface FaqRepository extends JpaRepository<Faqs, Long> {

    Faqs findFaqsById(Long id);

}
