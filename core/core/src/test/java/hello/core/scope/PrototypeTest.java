package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeBeanFind()
    {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(protoBean.class);

        System.out.println("find proto b1");
        protoBean p1 = ac.getBean(protoBean.class);


        System.out.println("find proto b2");
        protoBean p2 = ac.getBean(protoBean.class);
    }

    @Scope("prototype")
    static class protoBean
    {
        @PostConstruct
        public void init()
        {
            System.out.println("Single bean init");
        }

        @PreDestroy
        public void destroy()
        {
            System.out.println("sing.destory");
        }
    }
}
