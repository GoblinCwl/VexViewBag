package goblincwl.vexviewbag;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ☪wl
 * @program MinecrafProject
 * @description 数据注解映射
 * @create 2020-07-22 9:59
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataAnnotation {

    String value();

}
