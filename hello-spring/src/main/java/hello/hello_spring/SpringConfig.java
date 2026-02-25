package hello.hello_spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.persistence.EntityManager;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.JpaMemberRepository;
import hello.hello_spring.service.MemberService;
import hello.hello_spring.app.TimeTraceAop;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
     * private final EntityManager em;
     * 
     * @Autowired
     * public SpringConfig(EntityManager em) {
     * this.em = em;
     * }
     */

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }
    // @Bean
    // public MemberRepository memberRepository() {
    // return new JdbcMemberRepository(dataSource);
    // return new JpaMemberRepository(em);
    // }
}
