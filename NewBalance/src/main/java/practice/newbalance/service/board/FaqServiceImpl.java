package practice.newbalance.service.board;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import practice.newbalance.dto.board.FaqDto;
import practice.newbalance.repository.board.query.FaqRepositoryImpl;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService{

    private final FaqRepositoryImpl faqRepository;

    public List<FaqDto> findAll(){
        return faqRepository.findAll();
    }

    public Page<FaqDto> search(String condition, String tag, int page){
        Pageable pageable = PageRequest.of(page, 3);
        return faqRepository.findPage(pageable, condition, tag);

    }

}
