package com.dl.shop.order.enums;

public enum OrderRollBackErrorType {
	ACCOUNT_ROLLBACK_ERROR(0, "账户回滚失败");

	private Integer code;

	private String msg;

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	OrderRollBackErrorType(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
