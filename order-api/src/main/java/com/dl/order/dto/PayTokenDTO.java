package com.dl.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PayTokenDTO {
	
	@ApiModelProperty(value = "payToken")
	private String payTokenDTO;
}
