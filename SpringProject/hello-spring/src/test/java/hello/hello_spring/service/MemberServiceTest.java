package hello.hello_spring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import hello.hello_spring.domain.Member;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

public class MemberServiceTest {
    MemberService memberService;
    MemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @Test
    public void join() {
        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test");
        member.setName("test");

        Long savedId = memberService.join(member);
        Optional<Member> foundMember = memberService.findOne(savedId);
        assertThat(foundMember.get().getName()).isEqualTo(member.getName());
    }

    @Test
    public void duplicateJoin() {
        Member member1 = new Member();
        member1.setLoginId("test");
        member1.setPassword("test");
        member1.setName("test");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setLoginId("test");
        member2.setPassword("test");
        member2.setName("test");
        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    public void login() {
        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test");
        member.setName("test");
        memberService.join(member);
        Optional<Member> foundMember = memberService.login("test", "test");
        assertThat(foundMember.get().getName()).isEqualTo(member.getName());
    }
}
