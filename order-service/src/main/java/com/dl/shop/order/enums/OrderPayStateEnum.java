package com.dl.shop.order.enums;

public enum OrderPayStateEnum {
	PS_UNPAYED(0,"未支付"),
	PS_PAYED(1,"已付款");
	
	private Integer code;

	private String msg;

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	OrderPayStateEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public static String getStateName(int code) {
		String name = "";
		switch (code) {
			case 0:
				name = PS_UNPAYED.msg;
				break;
			case 1:
				name = PS_PAYED.msg;
				break;
			default:
				break;
		}
		return name;
	}
}
