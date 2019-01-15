package com.lynn.blog.common.result;

import lombok.Data;

/**
 * @param <T>
 * @author liyi
 */
@Data
public class SingleResult<T> extends Result {

    private T data;

    /**
     * 请求成功调用
     *
     * @param data
     * @return
     */
    public static <T> SingleResult<T> buildSuccess(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setCode(Code.SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 请求失败调用
     *
     * @param message
     * @return
     */
    public static <T> SingleResult<T> buildSuccess(Code code, String message) {
        SingleResult result = new SingleResult();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 请求成功调用
     *
     * @return
     */
    public static <T> SingleResult<T> buildSuccessWithoutData() {
        return buildSuccess(null);
    }

    /**
     * 请求成功调用
     *
     * @param message
     * @return
     */
    public static <T> SingleResult<T> buildSuccessWithMessage(String message) {
        SingleResult result = new SingleResult();
        result.setCode(Code.SUCCESS);
        result.setMessage(message);
        return result;
    }

    public static <T> SingleResult<T> buildFailure( String message) {
        SingleResult result = new SingleResult();
        result.setCode(Code.ERROR);
        result.setMessage(message);
        return result;
    }

    /**
     * 请求失败调用
     * 使用默认的消息字符串
     *
     * @return
     */
    public static <T> SingleResult<T> buildFailure() {
        SingleResult result = new SingleResult();
        result.setCode(Code.ERROR);
        return result;
    }

}
