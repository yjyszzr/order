package com.dl.order.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateOrderPayStatusParam {

	@ApiModelProperty("订单编码")
	private String orderSn;
	
	@ApiModelProperty("订单支付时间")
	private Integer payTime;
	
	@ApiModelProperty("支付状态 1-已支付")
	private Integer payStatus;
	
	@ApiModelProperty("支付id")
	private Integer payId;
	
	@ApiModelProperty("支付代码")
	private String payCode;
	
	@ApiModelProperty("支付名称")
	private String payName;
	
	@ApiModelProperty("支付编码")
	private String paySn;
}
