package practice.newbalance.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import practice.newbalance.dto.member.MemberDto;
import practice.newbalance.dto.member.UserInfoDto;
import practice.newbalance.service.MemberService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입 폼 화면 이동
     */
    @GetMapping("/member/join")
    public String members(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "/member/join";
    }

    /**
     * 회원가입 처리
     */
    @PostMapping("/member/new")
    public String memberJoin(MemberDto memberDto, BindingResult result) {

        if(result.hasErrors()){
            return "/member/join";
        }

        memberService.join(memberDto);

        return "redirect:/member/join";
    }

    /**
     * 아이디 찾기 폼 화면 이동
     */
    @GetMapping("/members/inquiry")
    public String findIdForm(Model model){
        model.addAttribute("userInfoDto", new UserInfoDto());
        return "member/inquiryForm";
    }

    /**
     * 아이디 찾기
     * @param userInfoDto
     * @return
     */
    @ResponseBody
    @PostMapping("/members/inquiry")
    public Map<String, Object> inquiryFindId(@ModelAttribute UserInfoDto userInfoDto){
        log.info("start userInfoDto = {}", userInfoDto);
        return memberService.inquiryFindId(userInfoDto);
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
