package com.youxuewen.wenxin.bo;

import java.io.Serializable;

/**
 * 返回值封装
 */
public class YouxueResult implements Serializable {

	private static final long serialVersionUID = 1L;

	public final static Integer SUCCESS = 200;
	public final static Integer ERROR = 500;
	public final static Integer PARAMERROR = 400;

	/**
	 * 返回状态值
	 */
	protected Integer code;
	/**
	 * 返回消息内容
	 */
	protected String msg;
	/**
	 * 返回数据
	 */
	protected Object data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static YouxueResult build(Integer code, String msg, Object data) {
		return new YouxueResult(code, msg, data);
	}

	public static YouxueResult ok(Object data) {
		return new YouxueResult(data);
	}

	public static YouxueResult ok() {
		return new YouxueResult(null);
	}

	public static YouxueResult ok(String msg){
		return build(SUCCESS,msg);
	};

	public static YouxueResult ok(String msg,Object data) {
		return new YouxueResult(SUCCESS,msg,data);
	}

	public static YouxueResult error(String msg){
		return build(ERROR,msg);
	};
	public YouxueResult() {

	}

	public static YouxueResult build(Integer code, String msg) {
		return new YouxueResult(code, msg, null);
	}

	public YouxueResult(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	@SuppressWarnings("static-access")
	public YouxueResult(Object data) {
		this.code = this.SUCCESS;
		this.msg = "请求成功";
		this.data = data;
	}
}
