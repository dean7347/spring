package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

        //AppConfig appconfig = new AppConfig();

        //대체된다
        //MemberService memberService =new MemberServiceImpl(memberRepository);
        //MemberService memberService = appconfig.memberService();

        //스프링 생성코드
        //모든 객체들을 관리한다
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //찾을것과 타입
        MemberService memberService= applicationContext.getBean("memberService",MemberService.class);
        Member member = new Member(1L, "memberA",Grade.VIP);
        memberService.join(member);

        Member member1 = memberService.findMember(1L);

        System.out.println("member = " + member);
        System.out.println("member1 = " + member1);
    }
}
