package hello.core.scan.filter;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan()
    {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        //BeanA beanA = annotationConfigApplicationContext.getBean("BeanA",BeanA.class);

        //annotationConfigApplicationContext.getBeanDefinition("BeanB", BeanB.class);

    }


    @Configuration
    @ComponentScan(includeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION,classes = MyIncludeComponent.class),
    excludeFilters = @ComponentScan.Filter(type =FilterType.ANNOTATION,classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig
    {

    }
}
