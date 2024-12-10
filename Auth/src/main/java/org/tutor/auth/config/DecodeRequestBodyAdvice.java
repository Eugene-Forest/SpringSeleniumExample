package org.tutor.auth.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
import org.tutor.auth.anno.AesRequest;
import org.tutor.auth.anno.RsaRequest;
import org.tutor.auth.entity.DecodeHttpInputMessage;
import org.tutor.auth.enums.HttpEncodingType;
import org.tutor.auth.units.AnnoUnits;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * 处理请求体
 *
 * @author Eugene-Forest
 * {@code @date} 2024/12/9
 */
@RestControllerAdvice(annotations = AesRequest.class)
//@RestControllerAdvice(basePackages = {"org.tutor"})
public class DecodeRequestBodyAdvice extends RequestBodyAdviceAdapter {

    private static final Logger log = LoggerFactory.getLogger(DecodeRequestBodyAdvice.class);


    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return AnnoUnits.ifExistAnno(methodParameter.getMethod(), AesRequest.class);
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        //在请求体转换之前执行
        log.debug("DecodeRequestBodyAdvice: beforeBodyRead");
        HttpHeaders httpHeaders = inputMessage.getHeaders();
        log.debug("DecodeRequestBodyAdvice: httpHeaders: {}", httpHeaders.toString());
        boolean isDecode = false;
        Method method = parameter.getMethod();
        if (method == null) {
            log.error("method is null");
            throw new RuntimeException("method is null");
        }
        HttpEncodingType type = null;
        if (method.getAnnotation(RsaRequest.class) != null) {
            RsaRequest annotation = method.getAnnotation(RsaRequest.class);
            isDecode = annotation.param();
            type = HttpEncodingType.RSA;
        } else if (method.isAnnotationPresent(AesRequest.class)) {
            AesRequest annotation = method.getAnnotation(AesRequest.class);
            isDecode = annotation.param();
            type = HttpEncodingType.AES;
        }
        if (isDecode) {
            return new DecodeHttpInputMessage(inputMessage, type);
        } else {
            return inputMessage;
        }
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        log.debug("DecodeRequestBodyAdvice: afterBodyRead");
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        log.debug("DecodeRequestBodyAdvice: handleEmptyBody");
        return super.handleEmptyBody(body, inputMessage, parameter, targetType, converterType);
    }
}
