package com.dl.shop.order.exception;

import com.dl.base.exception.ServiceException;
import com.dl.shop.order.enums.OrderExceptionEnum;

public class PaymentOrderException extends ServiceException {

	private static final long serialVersionUID = 6841484692625689210L;

	public PaymentOrderException(OrderExceptionEnum orderExceptionEnum) {
		super(orderExceptionEnum.getCode(), orderExceptionEnum.getMsg());
	}

}
