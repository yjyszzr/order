package com.dl.shop.order.exception;

import com.dl.base.exception.ServiceException;
import com.dl.shop.order.enums.OrderExceptionEnum;

public class SubmitOrderValidException extends ServiceException {

	private static final long serialVersionUID = -2101924925214333468L;

	public SubmitOrderValidException(OrderExceptionEnum orderExceptionEnum) {
		super(orderExceptionEnum.getCode(), orderExceptionEnum.getMsg());
	}
	
	public SubmitOrderValidException(Integer code, String msg) {
		super(code, msg);
	}
	
}
