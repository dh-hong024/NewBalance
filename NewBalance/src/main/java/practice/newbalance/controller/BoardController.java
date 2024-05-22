package practice.newbalance.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import practice.newbalance.domain.board.Notice;
import practice.newbalance.dto.board.NoticeDto;
import practice.newbalance.service.board.NoticeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final NoticeService noticeService;

    /**
     * 공지사항 리스트
     * https://jojoldu.tistory.com/528 더보기페이징참고
     * https://velog.io/@arnold_99/%ED%8E%98%EC%9D%B4%EC%A7%95-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84
     * https://cattaku.tistory.com/10
     *
     */
    @GetMapping(value = "/notice")
    public String getNotices(Model model,
                             @RequestParam(value = "offset", defaultValue = "0") int offset,
                             @RequestParam(value = "limit", defaultValue = "10") int limit) {

        List<NoticeDto> notices = noticeService.getNotice(offset, limit);
        long totalNotices = noticeService.getNoticeCount();

        model.addAttribute("notices", notices);
        model.addAttribute("offset", offset);
        model.addAttribute("limit", limit);
        model.addAttribute("totalNotices", totalNotices);

        return "/board/noticeList"; // Thymeleaf template name
    }

    @GetMapping(value = "/api/notices")
    @ResponseBody
    public Map<String, Object> getNoticesJson(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                              @RequestParam(value = "limit", defaultValue = "10") int limit) {

        List<NoticeDto> notices = noticeService.getNotice(offset, limit);
        long totalNotices = noticeService.getNoticeCount();

        Map<String, Object> response = new HashMap<>();
        response.put("notices", notices);
        response.put("totalNotices", totalNotices);

        return response;
    }

    /**
     * 공지사항 등록 페이지 이동
     * @param model
     * @return
     */
//    @GetMapping(value = "/notice/noticeForm")
    @GetMapping(value = "/notice/notice-form")
    public String noticeForm(Authentication authentication,
                             Model model) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("author",userDetails.getUsername());
        model.addAttribute("noticeDto", new NoticeDto());

        return "/board/noticeForm";
    }

    /**
     * 공지사항 등록
     * @param noticeDto
     * @return
     */
    @PostMapping(value = "/notice/add-notice")
    public String noticeAdd(NoticeDto noticeDto){

        noticeService.saveNotice(noticeDto);

        return "redirect:/notice";
    }

    /**
     * 공지사항 상세 폼
     * 조회수  증가
     */
    @GetMapping(value = "/notice/notice-detail/{noticeId}")
    public String detailNoticeForm(@PathVariable("noticeId") Long noticeId,
                                   HttpServletRequest request,
                                   HttpServletResponse response,
                                   Model model){

        Notice noticeDto = noticeService.findNoticeById(noticeId);
        noticeService.updateCount(noticeId, request, response);
        model.addAttribute("noticeDto", noticeDto);

        return "/board/noticeDetail";
    }

    /**
     * 공지사항 수정 폼
     */
    @GetMapping(value = "/notice/edit-form/{noticeId}")
    public String editNoticeForm(@PathVariable("noticeId") Long noticeId,Model model){

        Notice noticeDto = noticeService.findNoticeById(noticeId);
        model.addAttribute("noticeDto", noticeDto);

        return "/board/noticeEditForm";
    }

    /**
     *공지사항 글 수정
     */
    @PostMapping(value = "/notice/edit/{noticeId}")
    public String updateNotice(@PathVariable("noticeId") Long noticeId, @ModelAttribute("noticeDto") NoticeDto noticeDto){

        noticeService.updateNotice(noticeId, noticeDto);

        return "redirect:/notice";
    }

    /**
     * 공지사항 글 삭제
     */
    @GetMapping(value = "/notice/delete/{noticeId}")
    public String deleteNotice(@PathVariable("noticeId") Long noticeId){

        noticeService.deleteNotice(noticeId);

        return "redirect:/notice";
    }
}
