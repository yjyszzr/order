package com.dl.order.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderDetailParam implements Serializable{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("订单id")
	private String orderId;
	
	@ApiModelProperty("店铺id")
	private Integer storeId;
	
	@ApiModelProperty("代金券id")
	private Integer bonusId;
	
	@ApiModelProperty("订单sn")
	private String orderSn;
}
