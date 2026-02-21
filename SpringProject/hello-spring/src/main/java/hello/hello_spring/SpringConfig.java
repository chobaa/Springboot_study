package hello.hello_spring;

import hello.hello_spring.service.MemberService;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import hello.hello_spring.service.PostService;
import hello.hello_spring.repository.PostRepository;
import hello.hello_spring.repository.MemoryPostRepository;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public PostService postService() {
        return new PostService(postRepository());
    }

    @Bean
    public PostRepository postRepository() {
        return new MemoryPostRepository();
    }
}
