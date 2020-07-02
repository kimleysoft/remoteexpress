package com.xiang.restserver;

/**
 * @author xiang
 * @createDate 2018年10月19日 上午11:00:47
 */
public enum ErrorCodes {
	OK(10000, ""), 
	ERROR_PARAM(10001, "參數錯誤"), 
	TIME_OUT(10002, "請求交易所API超時"), 
	LOGIN(10003, "請先登錄,在進行操作!"),
	USER_NO_EXIST(10004, "賬號不存在"),
	ERROR(10005, "系統錯誤"), 
	FORBIDDEN(10006, "無權限操作！"),
	ADD_ERROR(10007, "添加失敗，請重試"),
	SECRETKEY_USER_PREMISSION_MAXNUM(10008, "未授權或已超出允許添加的用戶總數或爲測試版本已到期！"),
	SECRETKEY_ILLEGAL(10009, "非法密鑰！"),
	SECRETKEY_EMPTY(10010, "密鑰不能爲空"),
	SECRETKEY_NOT_EXIST(10011, "密鑰不存在，請在授權信息列表添加激活碼！"),
	SECRETKEY_PAST_DUE(10012, "測試版本，已到期！"),
	USER_HAD_DELETE(20001, "賬號已刪除！"),
	USER_EXIST(20002, "賬號已存在"),
	PASSWORD(20003, "原密碼不正確"), 
	AUTH(20004, "賬號或密碼錯誤!"),
	NONSUPPORT(20005, "不支持此操作"),
	ERROR_TENANT(20006, "租戶ID錯誤"),
	ERROR_PORT(20007,"遠程端口必須在33000-34000之間(除3389默認端口)"),
	ERROR_PORT_ALREADY(20007,"遠程端口號已佔用)"),
	ILLEGAL_REMOTE_IP(20008, "非法遠程ip"),
	ILLEGAL_LOCAL_IP(20009, "非法主機名或局域網ip"),
	NEWPASSWORD_NOT_EQUALS(20009, "新密碼不能與舊密碼相同"), 
	SORT_TOP(30001, "已經是頂部"),
	SORT_ERROR(30002, "排序失敗"),
	SORT_BOTTOM(30003, "已經是底部"),
	CATALOG_UPDATE_PARENT(30004, "父級不可選擇當前分類及所屬下級"),
	UPLOAD_ERROR(40001,"文件上傳失敗"),
	UPLOAD_EXT_ERROR(40002,"文件上傳失敗，不支持的文件類型"), 
	UPLOAD_OVER_SIZE(40003, "文件超过最大限制"),
	HAD_DATA_IN_GROUP(40004, "用戶組已存在數據，無法刪除"),
	;
	private int errorCode;
	private String errorMessage;


	private ErrorCodes(int errorCode, String errorMessage) {

		this.errorCode = errorCode;

		this.errorMessage = errorMessage;

	}

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
}
