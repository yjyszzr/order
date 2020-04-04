package com.dl.order.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CalculateParam implements Serializable{

	@ApiModelProperty(value="订单id")
	private Integer orderId;
	
	@ApiModelProperty(value="数量")
    private String num;
}
