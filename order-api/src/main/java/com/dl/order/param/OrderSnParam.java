package com.dl.order.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderSnParam implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("订单编号")
	private String orderSn;
	
	@ApiModelProperty("店铺ID")
	private Integer storeId;
}
