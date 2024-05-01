package practice.newbalance.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import practice.newbalance.domain.member.Member;
import practice.newbalance.dto.member.MemberDto;
import practice.newbalance.service.MemberService;
import practice.newbalance.web.validator.CheckEmailValidator;
import practice.newbalance.web.validator.CheckUserIdValidator;


@Controller
@SessionAttributes("member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    //중복 체크 유효성 검사
    private final CheckUserIdValidator checkUserIdValidator;
    private final CheckEmailValidator checkEmailValidator;

    /**
     * 커스텀 유효성 검증
     */
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkUserIdValidator);
        binder.addValidators(checkEmailValidator);
    }

    /**
     * 회원가입 폼 화면 이동
     */
    @GetMapping("/members/join")
    public String members(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "/member/join";
    }

    /**
     * 회원가입 처리
     */
    @PostMapping("/members/new")
    public String memberJoin(@Valid MemberDto memberDto, BindingResult bindingResult, Model model) {

        // 검증
        if(bindingResult.hasErrors()){
            //회원가입 실패 시 입력 데이터 값 유지
            model.addAttribute("memberDto", memberDto);
            //회원 가입 페이지로 리턴
            return "/member/join";
        }
        //회원 가입 성공
        memberService.join(memberDto);
        return "redirect:/members/login";
    }

    /**
     * 로그인 폼 화면 이동
     */
    @GetMapping("/members/login")
    public String memberLogin(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "/member/login";
    }

    /**
     * 로그인 처리
     */
    @PostMapping("/members/loginCheck")
    public String memberLoginChecked(@ModelAttribute MemberDto memberDto, HttpSession session) {

        //jwt참고
        //https://sepang2.tistory.com/81

        MemberDto loginResult = memberService.login(memberDto);
        log.info("login : {}", loginResult);
        if(loginResult != null) {
            //login 성공
            session.setAttribute("loginId", loginResult.getUserId());
            return "/home";
        }else{
            return "/member/login";
        }
    }
/**
 * 아이디, 이메일 중복체크 없이 유효성 검증 클래스 생성하여 처리함
 */
    /**
     * 아이디 중복체크
     */
    @GetMapping("/members/{userId}/exists")
    public ResponseEntity<Boolean> checkUserIdDuplicate(@PathVariable(value = "userId") String userId){
        return ResponseEntity.ok(memberService.checkUserId(userId));
    }
    /**
     * 이메일 중복체크
     */
    @GetMapping("/members/{email}/exists")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable(value = "email") String email) {
        return ResponseEntity.ok(memberService.checkEmail(email));
    }

}
