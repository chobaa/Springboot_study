package hello.hello_spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.PostRepository;
import hello.hello_spring.service.MemberService;
import hello.hello_spring.service.PostService;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public SpringConfig(MemberRepository memberRepository, PostRepository postRepository) {
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    @Bean
    public PostService postService() {
        return new PostService(postRepository);
    }
}
