package practice.newbalance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import practice.newbalance.dto.member.MemberDto;
import practice.newbalance.service.MemberService;

@Controller
@RequiredArgsConstructor
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

}
