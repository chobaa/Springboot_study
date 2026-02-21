package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import hello.hello_spring.service.PostService;
import hello.hello_spring.domain.Post;
import jakarta.servlet.http.HttpSession;
import hello.hello_spring.domain.Member;
import java.util.NoSuchElementException;

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
    public String create(Post post, HttpSession session, RedirectAttributes redirectAttributes) {
        Member loginmember = (Member) session.getAttribute("loginMember");

        if (loginmember == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
            return "redirect:/login";
        }

        post.setAuthorLoginId(loginmember.getLoginId());
        postService.register(post);
        redirectAttributes.addFlashAttribute("successMessage", "글이 등록되었습니다.");
        return "redirect:/posts";
    }

    @GetMapping("/posts/{postid}")
    public String detail(@PathVariable Long postid, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("post", postService.findOne(postid));
            return "posts/postDetail";
        } catch (NoSuchElementException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "게시글을 찾을 수 없습니다.");
            return "redirect:/posts";
        }
    }

    @GetMapping("/posts/{postid}/edit")
    public String editForm(@PathVariable Long postid, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("post", postService.findOne(postid));
            return "posts/editPostForm";
        } catch (NoSuchElementException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "게시글을 찾을 수 없습니다.");
            return "redirect:/posts";
        }
    }

    @PostMapping("/posts/{postid}/edit")
    public String edit(@PathVariable Long postid, Post post, HttpSession session, RedirectAttributes redirectAttributes) {
        Member loginmember = (Member) session.getAttribute("loginMember");

        if (loginmember == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
            return "redirect:/login";
        }

        try {
            postService.update(postid, loginmember.getLoginId(), post.getTitle(), post.getContent());
            redirectAttributes.addFlashAttribute("successMessage", "수정되었습니다.");
            return "redirect:/posts/" + postid;
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/posts/" + postid;
        } catch (NoSuchElementException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "게시글을 찾을 수 없습니다.");
            return "redirect:/posts";
        }
    }

    @PostMapping("/posts/{postid}/delete")
    public String delete(@PathVariable Long postid, HttpSession session, RedirectAttributes redirectAttributes) {
        Member loginmember = (Member) session.getAttribute("loginMember");

        if (loginmember == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
            return "redirect:/login";
        }

        try {
            postService.delete(postid, loginmember.getLoginId());
            redirectAttributes.addFlashAttribute("successMessage", "글이 삭제되었습니다.");
            return "redirect:/posts";
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/posts/" + postid;
        } catch (NoSuchElementException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "게시글을 찾을 수 없습니다.");
            return "redirect:/posts";
        }
    }
}
