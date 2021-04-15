package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration

//@Component가 붙은클래스를 모두 등록한다 충돌방지를 위한 어노테이션
@ComponentScan(excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes=Configuration.class))
public class AutoAppConfig {

    @Bean(name="memoryMemberRepository")
    MemberRepository memberRepository()
    {
        return new MemoryMemberRepository();
    }
}
