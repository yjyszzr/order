package com.dl.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CalculateDTO {
	
	@ApiModelProperty(value = "总价")
	private String totalPrice;
}
