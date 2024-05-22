package practice.newbalance.service.board;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.newbalance.domain.board.Notice;
import practice.newbalance.dto.board.NoticeDto;
import practice.newbalance.repository.board.NoticeRepositoryImpl;
import practice.newbalance.repository.board.NoticeRepository;

import java.util.List;

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeRepositoryImpl noticeQueryRepository;
    private final static String VIEWCOOKIENAME = "aleradyViewCookie";

    public NoticeService(NoticeRepository noticeRepository, NoticeRepositoryImpl noticeQueryRepository) {
        this.noticeRepository = noticeRepository;
        this.noticeQueryRepository = noticeQueryRepository;
    }


    public Notice saveNotice(NoticeDto noticeDto){
        return noticeRepository.save(noticeDto.toEntity());
    }

    public Notice findNoticeById(Long noticeId){
        return noticeRepository.findNoticeById(noticeId);
    }

    @Transactional
    public void updateNotice(Long noticeId, NoticeDto noticeDto){
        Notice notice = noticeRepository.findNoticeById(noticeId);

        notice.setNoticeTitle(noticeDto.getNoticeTitle());
        notice.setNoticeContent(noticeDto.getNoticeContent());
        notice.setNoticeCount(noticeDto.getNoticeCount());
    }

    public void deleteNotice(Long noticeId){
        noticeRepository.deleteById(noticeId);
    }

    /**
     * 조회수 중복방지
     */
    @Transactional
    public void updateCount(Long id, HttpServletRequest request, HttpServletResponse response) {
        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("overView")) {
                    oldCookie = cookie;
                }
            }
        }

        if(oldCookie != null) { //overView가 존재한다면 해당 쿠키의 value가 현재 접근한 게시글의 id를 포함하는지 검사
            if(!oldCookie.getValue().contains("[" + id + "]")){ // oldCookie의 vlaue값을 확인하여 [게시물번호 id]를 포함하는지 확인 가지고있다면 조회수 증가하지 않음
                noticeRepository.updateCount(id); // 포함하지 않는다면 증가
                oldCookie.setValue(oldCookie.getValue() + "[" + id + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 *24);
                response.addCookie(oldCookie);
            }
        } else {
            noticeRepository.updateCount(id);
            Cookie newCookie = new Cookie("overView", "[" + id + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(newCookie);
        }
    }

    public List<NoticeDto> getNotice(int offset, int limit) {
        return noticeRepository.findNoticeAll(offset, limit);
    }

    public long getNoticeCount(){
        return noticeRepository.count();
    }
}
