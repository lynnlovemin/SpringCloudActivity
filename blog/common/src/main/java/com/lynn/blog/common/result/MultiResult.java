package com.lynn.blog.common.result;

import lombok.Data;

import java.util.Collection;

/**
 * @author liyi
 */
@Data
public class MultiResult<T> extends Result {

	private Collection<T> data;

	private long total;

	/**
	 * 请求成功调用
	 * @param data
	 * @param total
	 * @param <T>
	 * @return
	 */
	public static <T> MultiResult<T> buildSuccess(Collection<T> data,long total){
		MultiResult<T> result = new MultiResult<>();
		result.setCode(Code.SUCCESS);
		result.setData(data);
		result.setTotal(total);
		return result;
	}

	/**
	 * 请求成功调用
	 * @param data
	 * @return
	 */
	public static <T> MultiResult<T> buildSuccessWithoutTotal(Collection<T> data){
		return buildSuccess(data,0);
	}

	/**
	 * 请求失败调用
	 * @param message
	 * @return
	 */
	public static <T> MultiResult<T> buildFailure(String message){
		MultiResult<T> result = new MultiResult<>();
		result.setCode(Code.ERROR);
		result.setMessage(message);
		return result;
	}

	/**
	 * 请求失败调用
	 * 使用默认的消息字符串
	 * @return
	 */
	public static <T> MultiResult<T> buildFailure(){
		MultiResult<T> result = new MultiResult<>();
		result.setCode(Code.ERROR);
		return result;
	}
	
}
