package practice.newbalance.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import practice.newbalance.dto.board.FaqDto;
import practice.newbalance.service.board.FaqServiceImpl;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final FaqServiceImpl faqService;

    @GetMapping("/faqs")
    public String FaqList(Model model){
        List<FaqDto> faqList = faqService.findAll();
        model.addAttribute("contents", faqList);
        return "board/faqs";
    }

    @PostMapping("/search")
    public String faqSearchContent(
            @RequestParam(value = "condition", required = false) String condition,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam("page") Integer page,
            Model model

    ){

        log.info("condition = {}, page = {}, tag = {}", condition, page, tag);

        Page<FaqDto> search = faqService.search(condition, tag, page);

        model.addAttribute("contents", search.getContent());
        model.addAttribute("condition", condition);
        model.addAttribute("tag", tag);
        log.info("search = {} ", search.getContent());

        return "board/faqs";
    }

}
