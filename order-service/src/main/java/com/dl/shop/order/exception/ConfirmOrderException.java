package com.dl.shop.order.exception;

import com.dl.base.exception.ServiceException;
import com.dl.shop.order.enums.OrderExceptionEnum;

public class ConfirmOrderException extends ServiceException {
	
	private static final long serialVersionUID = 1L;

	public ConfirmOrderException(OrderExceptionEnum orderExceptionEnum) {
		super(orderExceptionEnum.getCode(), orderExceptionEnum.getMsg());
	}

	public ConfirmOrderException(Integer code, String msg) {
		super(code, msg);
	}
}
