package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import hello.hello_spring.service.PostService;
import hello.hello_spring.domain.Post;
import jakarta.servlet.http.HttpSession;
import hello.hello_spring.domain.Member;

@Controller
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String list(Model model) {
        model.addAttribute("posts", postService.findPosts());
        return "posts/postList";
    }

    @GetMapping("/posts/new")
    public String createForm(HttpSession session) {
        Member loginmember = (Member) session.getAttribute("loginMember");

        if (loginmember == null) {
            return "redirect:/login";
        }
        
        return "posts/createPostForm";
    }

    @PostMapping("/posts/new")
    public String create(Post post, HttpSession session) {
        Member loginmember = (Member) session.getAttribute("loginMember");

        if (loginmember == null)
            return "redirect:/login";

        post.setAuthorLoginId(loginmember.getLoginId());
        postService.register(post);

        return "redirect:/posts";
    }
}
