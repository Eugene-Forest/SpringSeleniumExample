package org.tutor.auth.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.tutor.auth.anno.RasCheck;
import org.tutor.auth.units.AnnoUnits;
import org.tutor.auth.verify.DefaultVerifySign;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 网络请求拦截器
 * @author Eugene-Forest
 * {@code @date} 2024/11/6
 * {@code @project} TuTorSelenium
 */
public class RequestInterceptor implements HandlerInterceptor {

    private final Logger log = LoggerFactory.getLogger(RequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在请求处理之前执行，允许您在请求被传递给控制器之前进行一些预处理操作，
        // 比如鉴权、日志记录等。如果返回true，则继续执行请求处理链；如果返回false，则中断请求处理。
        String servletPath = request.getServletPath();
        Map<String, String[]> parameterMap = request.getParameterMap();
        log.warn("拦截处理URL:" + request.getRequestURL().toString());
        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RasCheck rasCheck = AnnoUnits.getAnno(handlerMethod, RasCheck.class);
            if(rasCheck != null){
                //需要签名校验
                log.debug("签名校验[{}]请求。", handlerMethod.getMethod().getName());
            } else{
                log.debug("不需要签名校验[{}]请求。", handlerMethod.getMethod().getName());
            }
        }
        return true; // 返回true继续执行，返回false中断执行
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 在请求处理之后、视图渲染之前执行。您可以在这里对模型和视图进行操作，但不能改变视图。通常用于修改响应内容、添加额外的模型数据等。
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 在整个请求完成后执行，包括视图渲染。可以用于清理资源、记录请求处理时间等操作。
    }
}