package java8to11;

import java.lang.annotation.*;


//언제까지 유지할것인가
@Retention(RetentionPolicy.RUNTIME)
//사용할곳
//   static class FeelsLikeChicken<T> 에선언할 수 있도록
//@Target(ElementType.TYPE_PARAMETER)
@Target(ElementType.TYPE_USE) //모든곳에 사용가능

//중복 에노테이션이 되게하려면?
//컨테이너의 리텐션과 타겟은 같거나 더 넓어야한다
@Repeatable(ChickContainer.class)
public @interface Chicken {
    String value();
}
