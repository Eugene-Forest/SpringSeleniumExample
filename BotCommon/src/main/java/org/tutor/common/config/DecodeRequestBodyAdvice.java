package org.tutor.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.tutor.common.anno.AesRequest;
import org.tutor.common.anno.RsaRequest;
import org.tutor.common.entity.DecodeHttpInputMessage;
import org.tutor.common.enums.HttpEncodingType;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * 处理请求体
 *
 * @author Eugene-Forest
 * {@code @date} 2024/12/9
 */
@ControllerAdvice(basePackages = {"org.tutor.*.control"})
@Slf4j
public class DecodeRequestBodyAdvice implements RequestBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        //用于判断是否应该执行接口的方法
        log.debug("DecodeRequestBodyAdvice: supports");
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        //在请求体转换之前执行
        log.debug("DecodeRequestBodyAdvice: beforeBodyRead");
        HttpHeaders httpHeaders = inputMessage.getHeaders();
        log.debug("DecodeRequestBodyAdvice: httpHeaders: {}", httpHeaders.toString());
        boolean isDecode = false;
        Method method = parameter.getMethod();
        if(method == null) {
            log.error("method is null");
            throw new RuntimeException("method is null");
        }
        HttpEncodingType type = null;
        if(method.getAnnotation(RsaRequest.class) != null){
            RsaRequest annotation = method.getAnnotation(RsaRequest.class);
            isDecode = annotation.param();
            type = HttpEncodingType.RSA;
        } else if(method.isAnnotationPresent(AesRequest.class)){
            AesRequest annotation = method.getAnnotation(AesRequest.class);
            isDecode = annotation.param();
            type = HttpEncodingType.AES;
        }
        if(isDecode) {
            return new DecodeHttpInputMessage(inputMessage, type);
        }else {
            return inputMessage;
        }
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        //在请求体转换之后执行
        log.debug("DecodeRequestBodyAdvice: afterBodyRead");

        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 处理请求体为空的情况
        log.debug("DecodeRequestBodyAdvice: handleEmptyBody");

        return body;
    }
}
