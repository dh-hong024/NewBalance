package practice.newbalance.service.board;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.newbalance.domain.board.Faqs;
import practice.newbalance.domain.board.Notice;
import practice.newbalance.dto.board.FaqDto;
import practice.newbalance.dto.board.NoticeDto;
import practice.newbalance.repository.board.query.FaqRepository;
import practice.newbalance.repository.board.query.FaqRepositoryImpl;

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

    public Faqs saveFaq(FaqDto faqDto){
        return faqRepository.save(faqDto.toEntity());
    }

    @Transactional
    public void updateFaq(Long faqId, FaqDto faqDto){
        Faqs faqs = faqRepository.findFaqsById(faqId);

        faqs.setTag(faqDto.getTag());
        faqs.setQuestion(faqDto.getQuestion());
        faqs.setAnswer(faqDto.getAnswer());
    }

    public void deleteFaq(Long faqId){
        faqRepository.deleteById(faqId);
    }

}
