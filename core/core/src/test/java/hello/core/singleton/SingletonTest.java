package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI컨테이너")
    void pureContainer(){
        AppConfig ac = new AppConfig();

        //1. 조회 호출할때마다 객체를 생성성
        MemberService ms1 = ac.memberService();

        //2. 조회 호출할때마다 객체를 생성성
        MemberService ms2 = ac.memberService();

        //참조값이 다른것을 확인

        System.out.println("ms1 = " + ms1);
        System.out.println("ms2 = " + ms2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService s1 = SingletonService.getInstace();
        SingletonService s2 = SingletonService.getInstace();

        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);
    }


    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void singletonContainer(){
        //AppConfig ac = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //1. 조회 호출할때마다 객체를 생성성
        MemberService ms1 = ac.getBean("memberService",MemberService.class);

        //2. 조회 호출할때마다 객체를 생성성
        MemberService ms2 = ac.getBean("memberService",MemberService.class);

        //참조값이 다른것을 확인

        System.out.println("ms1 = " + ms1);
        System.out.println("ms2 = " + ms2);
    }
}
