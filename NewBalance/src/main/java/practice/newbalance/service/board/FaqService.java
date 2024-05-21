package practice.newbalance.service.board;

import org.springframework.data.domain.Page;
import practice.newbalance.dto.board.FaqDto;

import java.util.List;

public interface FaqService {
    Page<FaqDto> findAll(int page, int limit);
    Page<FaqDto> findAll(int page, int limit, String condition, String tag);
}
