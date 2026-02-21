package hello.hello_spring.controller;

import hello.hello_spring.domain.Post;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import java.util.List;
import java.util.ArrayList;

@Controller
public class PostController {
    @GetMapping("/posts")
    public String list(Model model) {
        List<Post> posts = new ArrayList<>();
        
        Post post1 = new Post();
        post1.setId(1L);
        post1.setTitle("첫 번째 게시글입니다!");
        post1.setContent("반갑습니다!");
        posts.add(post1);

        model.addAttribute("posts", posts);
        return "posts/postList";
    }

    @GetMapping("/posts/new")
    public String createForm() {
        return "posts/createPostForm";
    }
}
