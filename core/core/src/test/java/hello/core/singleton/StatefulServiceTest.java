package hello.core.singleton;


import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {
    @Test
    void statefulServieSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService s1 = ac.getBean(StatefulService.class);
        StatefulService s2 = ac.getBean(StatefulService.class);

        //TA :A사용자가 만원 주문
        int userA =s1.order("userA",10000);

        //TB: B사용자가 2만원 주문
        int userB =s1.order("userA",20000);
        // TA: 사용자 a가 주문 금액 조회

        System.out.println("price = " + userA);
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}
