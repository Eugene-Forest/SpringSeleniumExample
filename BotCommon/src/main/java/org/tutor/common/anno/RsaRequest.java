package org.tutor.common.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/12/9
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RsaRequest {

    /**
     * 入参是否解密，默认解密
     */
    boolean param() default true;

    /**
     * 出参是否加密，默认加密
     */
    boolean result() default true;

}
