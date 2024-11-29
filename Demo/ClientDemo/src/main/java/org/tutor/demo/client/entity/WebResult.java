package org.tutor.demo.client.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/11/29
 */
@Getter
@Setter
public class WebResult<T> implements Serializable {
    private int code;
    private String message;
    private T data;

    public WebResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public WebResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> WebResult<T> success(){
        WebResult<T> webResult = new WebResult<T>(200, "success", null);
        return webResult;
    }

    public static <T> WebResult<T> success(T data){
        WebResult<T> webResult = new WebResult<T>(200, "success", data);
        return webResult;
    }

    public static <T> WebResult<T> failure(){
        WebResult<T> webResult = new WebResult<T>(500, "failure", null);
        return webResult;
    }

    public static <T> WebResult<T> failure(int code, String message){
        WebResult<T> webResult = new WebResult<T>(code, message);
        return webResult;
    }

}
