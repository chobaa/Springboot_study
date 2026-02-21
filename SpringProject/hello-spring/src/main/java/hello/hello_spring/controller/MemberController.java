package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import hello.hello_spring.service.MemberService;
import hello.hello_spring.domain.Member;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Optional;
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
    public String create(String loginId, String password, String name) {
        Member member = new Member();
        member.setLoginId(loginId);
        member.setPassword(password);
        member.setName(name);
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "members/loginForm";
    }

    @PostMapping("/login")
    public String login(String loginId, String password, HttpSession session) {
        Optional<Member> member = memberService.login(loginId, password);
        if (member.isPresent()) {
            session.setAttribute("loginMember", member.get());
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/members")
    public String memberList(Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
