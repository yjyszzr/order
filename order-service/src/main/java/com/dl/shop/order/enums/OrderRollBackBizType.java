package com.dl.shop.order.enums;

public enum OrderRollBackBizType {
	SUMBIT_ORDER(1,"下单");

	private Integer code;

	private String msg;

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	OrderRollBackBizType(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
