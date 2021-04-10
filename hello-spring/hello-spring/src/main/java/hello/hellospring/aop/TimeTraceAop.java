package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
    //어디에 적용할것인가 타개팅
    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("Start :"+joinPoint.toString());
        try{
            return joinPoint.proceed();//다음 메소드로 진행
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish -start;
            System.out.println("END :"+joinPoint.toString()+" " + timeMs+"ms");
        }

    }
}
