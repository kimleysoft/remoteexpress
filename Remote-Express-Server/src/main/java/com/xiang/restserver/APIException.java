package com.xiang.restserver;

import java.util.ArrayList;
import java.util.List;

/**
 * API Exception
 *
 * @author xiang
 * @version 1.0.0
 * @date 2018/11/22 19:59
 */
public class APIException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private ErrorCodes err;
	private int errorCode;
	private String errorMessage;
	private Object data;

	public APIException(ErrorCodes err) {
		super(err.getErrorMessage());
//		this.err = err;
		this.errorCode=err.getErrorCode();
		this.errorMessage=err.getErrorMessage();
	}

	public APIException(int errorCode, String msg) {
		super(msg);
//		this.err = err;
		this.errorCode=errorCode;
		this.errorMessage=msg;
		List<String> data=new ArrayList<>(1);
		data.add(msg);
		this.data = data;
	}
	public APIException(ErrorCodes err, Object data) {
		super(err.getErrorMessage());
		this.errorCode=err.getErrorCode();
		this.errorMessage=err.getErrorMessage();
		this.data = data;
	}
	
//	public ErrorCodes getErr() {
//		return err;
//	}
//
//	public void setErr(ErrorCodes err) {
//		this.err = err;
//	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
