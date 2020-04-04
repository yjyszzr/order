package com.dl.order.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderDetailByOrderSnPara implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("订单id")
	private String orderSn;
}
