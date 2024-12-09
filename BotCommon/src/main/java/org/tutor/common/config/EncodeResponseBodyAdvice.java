package org.tutor.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.tutor.common.entity.WebResult;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/12/9
 */
@Slf4j
@ControllerAdvice(basePackages = {"org.tutor.*.control"})
public class EncodeResponseBodyAdvice implements ResponseBodyAdvice<WebResult> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        log.debug("EncodeResponseBodyAdvice supports");
        return true;
    }

    @Override
    public WebResult beforeBodyWrite(WebResult body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        log.debug("EncodeResponseBodyAdvice beforeBodyWrite");
        return body;
    }
}
