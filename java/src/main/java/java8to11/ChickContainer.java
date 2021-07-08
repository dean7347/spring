package java8to11;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE_USE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ChickContainer {

    //감싸고있을 어노테이션을 배열로 가지고있는다
    Chicken[] value();
}
