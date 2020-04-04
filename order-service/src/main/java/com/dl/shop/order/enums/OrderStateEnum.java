package com.dl.shop.order.enums;

import java.io.Serializable;

public enum OrderStateEnum implements Serializable {

	ORDER_CONFIRM(0, "订单已确认"), 
	ORDER_FINISHED(1, "交易成功"), 
	ORDER_DISABLE(2, "交易关闭-卖家取消"),
	ORDER_CANCEL(3,"交易关闭-买家取消"),
	ORDER_SYS_CANCEL(4, "交易关闭-系统自动取消");

	private int code;

	private String msg;

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	OrderStateEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public static String getStateName(int code) {
		String name = "";
		switch (code) {
			case 0:
				name = ORDER_CONFIRM.msg;
				break;
			case 1:
				name = ORDER_FINISHED.msg;
				break;
			case 2:
				name = ORDER_DISABLE.msg;
				break;
			case 3:
				name = ORDER_CANCEL.msg;
				break;
			case 4:
				name = ORDER_SYS_CANCEL.msg;
				break;
			default:
				break;
		}
		return name;
	}
	
}
