package com.dl.order.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderIdParam implements Serializable{

	@ApiModelProperty(value="订单id")
	private Integer orderId;
 
}
