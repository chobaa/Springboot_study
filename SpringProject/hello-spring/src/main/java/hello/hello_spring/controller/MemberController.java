package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import hello.hello_spring.service.MemberService;
import hello.hello_spring.domain.Member;
import org.springframework.ui.Model;
import java.util.List;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(Member member, RedirectAttributes redirectAttributes) {
        try {
            memberService.join(member);
            redirectAttributes.addFlashAttribute("successMessage", "회원가입이 완료되었습니다. 로그인해 주세요.");
            return "redirect:/login";
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/members/new";
        }
    }

    @GetMapping("/login")
    public String loginForm() {
        return "members/loginForm";
    }

    @PostMapping("/login")
    public String login(String loginId, String password, HttpSession session, RedirectAttributes redirectAttributes) {
        return memberService.login(loginId, password)
                .map(m -> {
                    session.setAttribute("loginMember", m);
                    redirectAttributes.addFlashAttribute("successMessage", "로그인 성공");
                    return "redirect:/";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("errorMessage", "아이디 또는 비밀번호가 맞지 않습니다.");
                    return "redirect:/login";
                });
    }

    @PostMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("successMessage", "로그아웃되었습니다.");
        return "redirect:/";
    }

    @GetMapping("/members")
    public String memberList(Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
