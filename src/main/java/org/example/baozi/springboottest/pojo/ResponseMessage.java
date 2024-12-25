package org.example.baozi.springboottest.pojo;

import org.springframework.http.HttpStatus;

//* 一个接口测试的返回信息
// 因为数据类型多变，需要使用泛型
public class ResponseMessage<T> {
    private Integer code;
    private String message;
    private T data;

    public ResponseMessage(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    //* 接口请求成功的返回信息---使用静态的原因是：无需实例化可以直接调用，便于维护
    public static <T> ResponseMessage<T> success(T data) {
        return new ResponseMessage<T>(HttpStatus.OK.value(), data, "success");
        // 在http协议内，成功的代码对应为 200，即 HttpStatus.OK.value()，如 404 not find
    }

    //* 此处需要有setter和getter方法，因为在调用时底层需要使用，否则会报错
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
