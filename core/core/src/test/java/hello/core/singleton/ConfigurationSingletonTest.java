package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest
{

    @Test
    void configurationTest()
    {
        ApplicationContext ac= new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService =ac.getBean("memberService",MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository m1 = memberService.getMemberRepository();
        MemberRepository m2 = orderService.getMemberRepository();
        System.out.println("m2 = " + m2);
        System.out.println("m1 = " + m1);

    }

    @Test
    void ConfigurationDeep()
    {
        ApplicationContext ac= new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean.getClass() = " + bean.getClass());
    }
}
