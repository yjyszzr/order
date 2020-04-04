package com.dl.shop.order.enums;

import java.io.Serializable;

public enum OrderExceptionEnum implements Serializable {

    SUBMIT_ERROR_TIMEOUT(205001, "超时未结算，请重新下单！"),
    SUBMIT_ERROR_SEND_MSG(205002,"消息发送异常！"),
    SUBMIT_ERROR_ACCOUNT(205003,"扣减账户异常！"),
    SUBMIT_ERROR_PARAM(205004,"您的订单已经失效，请重新下单！"),
    SUBMIT_ERROR_VALID(205005,"参数异常！"),
    SUBMIT_ERROR_FORMIT(205006,"您提交的动作过快，请稍后重试！"),
	SUBMIT_ERROR(205007,"付款失败，请重新下单！"),

	PAYMENT_NOTORDER(204001,"没有需要支付的订单！"),
	PAYMENT_CALL_ERROR(204002,"支付更新订单状态失败！"),
	PAYMENT_ISPAID_ERROR(204003,"订单已经支付成功！无需再次支付！"),
	
	CONFIRM_ERROR_NOT_FOUND(207001,"确认订单失败，订单不存在"),
	CONFIRM_ERROR_STATUS(207002,"确认订单失败，订单状态异常"),
	CONFIRM_ERROR_SEND_MSG(207003,"消息发送异常！"),
	
	BACK_ERROR_STATUS(208001,"该订单已关闭，无法继续申请退款"),
	BACK_ERROR_REPEAT(208002,"该商品已申请退款！"),
	BACK_ERROR(208009,"退款申请失败！"),

    DETAIL_ERROR_PROMOTION(209001,"营销活动服务异常!"),
	SUBMIT_ERROR_ACCOUNT_NOT(209002,"红包退回异常！");

	private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    OrderExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
