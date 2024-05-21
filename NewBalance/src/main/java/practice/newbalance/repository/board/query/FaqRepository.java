package practice.newbalance.repository.board.query;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.newbalance.domain.board.Faqs;

public interface FaqRepository extends JpaRepository<Faqs, Long> {

}
