package practice.newbalance.repository.board.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import practice.newbalance.dto.board.FaqDto;


public interface CustomRepository {
    Page<FaqDto> findPage(Pageable pageable, String condition, String tag);
}
