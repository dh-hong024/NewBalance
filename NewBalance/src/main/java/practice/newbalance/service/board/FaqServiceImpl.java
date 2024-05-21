package practice.newbalance.service.board;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import practice.newbalance.dto.board.FaqDto;
import practice.newbalance.repository.board.query.FaqRepository;
import practice.newbalance.repository.board.query.FaqRepositoryImpl;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService{

    private final FaqRepositoryImpl faqRepositoryImpl;
    private final FaqRepository faqRepository;

    public Page<FaqDto> findAll(int page, int limit){
        Pageable pageable = PageRequest.of(page, limit);
        return faqRepositoryImpl.findAll(pageable);
    }

    public Page<FaqDto> findAll(int page, int limit, String condition, String tag){
        Pageable pageable = PageRequest.of(page, limit);
        return faqRepositoryImpl.findAll(pageable, condition, tag);
    }

    public long getFaqCount(){
        return faqRepository.count();
    }

    public Long getSearchCount(String condition, String tag){
        return faqRepositoryImpl.getSearchCount(condition, tag);
    }

}
