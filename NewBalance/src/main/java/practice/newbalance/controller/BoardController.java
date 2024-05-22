package practice.newbalance.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import practice.newbalance.dto.board.FaqDto;
import practice.newbalance.service.board.FaqServiceImpl;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final FaqServiceImpl faqService;

    @GetMapping("/faqs")
    public String FaqList(
            @RequestParam(value = "condition", required = false) String condition,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "page", defaultValue = "0") int page,
            Model model
    ){
        //todo: 설정 값으로 대체 예정
        int limit = 3;
        boolean isSearch = tag != null;

        Page<FaqDto> faqList = isSearch ?
                faqService.findAll(page, limit, condition, tag) :
                faqService.findAll(page, limit);

        long dataCnt = (
                isSearch ?
                        faqService.getSearchCount(condition, tag) - ((page + 1) * limit) :
                        faqService.getFaqCount() - ((page + 1) * limit)
        );

        log.info("contents = {}, page = {}, count = {}", faqList.getContent(), page, dataCnt);

        model.addAttribute("contents", faqList.getContent());
        model.addAttribute("page", page);
        model.addAttribute("count", dataCnt <= 0 ? 0 : dataCnt);
        model.addAttribute("tag", tag);
        model.addAttribute("condition", condition);

        return "board/faqs";
    }

    @GetMapping("/api/faqs")
    @ResponseBody
    public Map<String, Object> getContents(
            @RequestParam(value = "condition", required = false) String condition,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "page", defaultValue = "0") int page,
            Model model
    ){
        //todo: 설정 값으로 대체 예정
        int limit = 3;
        boolean isSearch = tag != null;
        Page<FaqDto> faqList = isSearch ?
                faqService.findAll(page, limit, condition, tag) :
                faqService.findAll(page, limit);

        long dataCnt = (
                isSearch ?
                        faqService.getSearchCount(condition, tag) - ((page + 1) * limit) :
                        faqService.getFaqCount() - ((page + 1) * limit)
        );

        log.info("contents = {}, page = {}, count = {}", faqList.getContent(), page, dataCnt);

        Map<String, Object> data = new HashMap<>();
        data.put("contents", faqList.getContent());
        data.put("page", page);
        data.put("count", dataCnt <= 0 ? 0 : dataCnt);
        data.put("tag", tag);
        data.put("condition", condition);

        return data;
    }

}
