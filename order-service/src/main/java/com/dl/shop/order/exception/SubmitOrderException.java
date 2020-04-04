package com.dl.shop.order.exception;

import com.dl.base.exception.ServiceException;
import com.dl.shop.order.enums.OrderExceptionEnum;

public class SubmitOrderException extends ServiceException {

	private static final long serialVersionUID = 7660513083199778997L;

	public SubmitOrderException(OrderExceptionEnum orderExceptionEnum) {
		super(orderExceptionEnum.getCode(), orderExceptionEnum.getMsg());
	}
	
	public SubmitOrderException(Integer code, String msg) {
		super(code, msg);
	}
}
