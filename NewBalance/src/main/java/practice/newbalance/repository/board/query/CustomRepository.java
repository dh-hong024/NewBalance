package practice.newbalance.repository.board.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import practice.newbalance.dto.board.FaqDto;


public interface CustomRepository {
    Page<FaqDto> findAll(Pageable pageable);
    Page<FaqDto> findAll(Pageable pageable, String condition, String tag);
    Long getSearchCount(String condition, String tag);
}
