package practice.newbalance.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import practice.newbalance.dto.member.MemberDto;
import practice.newbalance.service.MemberService;
import practice.newbalance.web.validator.CheckEmailValidator;
import practice.newbalance.web.validator.CheckUserIdValidator;


import java.util.Map;

@Controller
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
     * 아이디 찾기 폼 화면 이동
     */
    @GetMapping("/members/inquiry")
    public String findIdForm(Model model){
        model.addAttribute("memberDto", new MemberDto());
        return "member/inquiryForm";
    }

    /**
     * 아이디 찾기
     * @param memberDto
     * @return
     */
    @ResponseBody
    @PostMapping("/members/inquiry")
    public Map<String, Object> inquiryFindId(@ModelAttribute MemberDto memberDto){
        log.info("start memberDto = {}", memberDto);
        return memberService.inquiryFindId(memberDto);
    }

    @ResponseBody
    @PostMapping("/members/inquiry/reset-pw")
    public Map<String, Object> inquiryResetPw(
            @RequestParam("userId") String userId,
            @RequestParam("name") String name,
            @RequestParam("phoneNumber") String phoneNumber
    ){
        return memberService.inquiryResetPw(userId, name, phoneNumber);
    }



}
