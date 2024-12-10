package org.tutor.auth.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法体上添加此注解将不会进入签名验证
 *
 * @author Eugene-Forest
 * {@code @date} 2024/11/28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NoToken {
}
