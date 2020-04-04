package com.dl.shop.order.exception;

import com.dl.base.exception.ServiceException;
import com.dl.shop.order.enums.OrderExceptionEnum;

public class SendMsgServiceException extends ServiceException {

	private static final long serialVersionUID = -7128528854670908938L;

	public SendMsgServiceException(Integer code, String msg) {
		super(code, msg);
	}
	
	public SendMsgServiceException(OrderExceptionEnum orderExceptionEnum) {
		super(orderExceptionEnum.getCode(), orderExceptionEnum.getMsg());
	}

}
