package org.tutor.sign.anno;

import java.lang.annotation.*;

/**
 * 方法上加入这个注解，会在进入方法前执行签名校验
 *
 * @author Eugene-Forest
 * {@code @date} 2024/11/27
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface RasCheck {
}

//理论上，自定义注解需要通过切面控制注解行为，但是这里的注解似乎只有标识的作用，
// 真正起作用的是拦截器里面通过方法体是否含有此注解来进行签名验证