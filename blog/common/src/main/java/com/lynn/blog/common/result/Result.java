package com.lynn.blog.common.result;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @author liyi
 */
@Data
public class Result {

	private Code code;

	private String message;

	public int getCode() {
		return code.getStatus();
	}

	public String getMessage() {
		return message != null?message:code.getMessage();
	}

	@Override
	public String toString(){
		return JSON.toJSONString(this);
	}
}
