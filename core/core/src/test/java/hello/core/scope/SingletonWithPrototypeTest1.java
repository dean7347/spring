package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind()
    {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean p1 = ac.getBean(PrototypeBean.class);
        p1.addCount();
        System.out.println("p1 = " + p1);
        PrototypeBean p2 = ac.getBean(PrototypeBean.class);
        p1.addCount();
        System.out.println("p2 = " + p2);


    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount()
        {
            count++;
        }

        public int getCount()
        {
            return count;
        }

        @PostConstruct
        public void init()
        {
            System.out.println("PrototypeBean.init"+this);
        }

        @PreDestroy
        public void destory(){
            System.out.println(".destroy");
        }
    }
}
