package practice.newbalance.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import practice.newbalance.dto.member.MemberDto;

@Controller
public class LoginController {

    @GetMapping("/members/login")
    public String loginPage(HttpServletRequest request,
                            Model model){

        HttpSession session = request.getSession();
        String msg = (String) session.getAttribute("exception");
        session.setAttribute("exception", msg != null ? msg : "");

        model.addAttribute("memberDto", new MemberDto());

        return "/member/login";
    }

    @GetMapping("/members/logout")
    public String logoutPage(){

        return "redirect:/";
    }
}
