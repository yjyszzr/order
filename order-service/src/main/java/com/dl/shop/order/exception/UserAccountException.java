package com.dl.shop.order.exception;

import com.dl.base.exception.ServiceException;
import com.dl.shop.order.enums.OrderExceptionEnum;

public class UserAccountException extends ServiceException {

	private static final long serialVersionUID = 1038934593021823059L;

	public UserAccountException(OrderExceptionEnum orderExceptionEnum) {
		super(orderExceptionEnum.getCode(), orderExceptionEnum.getMsg());
	}
	
	public UserAccountException(Integer code, String msg) {
		super(code, msg);
	}

}
