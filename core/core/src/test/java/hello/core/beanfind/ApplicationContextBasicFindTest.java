package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService ms = ac.getBean("memberService",MemberService.class);

        Assertions.assertThat(ms).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름없이 타입으로 조회")
    void findBeanByType(){
        MemberService ms = ac.getBean(MemberService.class);

        Assertions.assertThat(ms).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체타입으로 조회")
    void findBeanByName2(){
        MemberServiceImpl ms = ac.getBean("memberService",MemberServiceImpl.class);

        Assertions.assertThat(ms).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름 조회 x")
    void findBeanByNameX(){
       // MemberService xxxx = ac.getBean("xxxx", MemberService.class);
        //junit을사용
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,()->
                ac.getBean("xxxx", MemberService.class));
    }


}
