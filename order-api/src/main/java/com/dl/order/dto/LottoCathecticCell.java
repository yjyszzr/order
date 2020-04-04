package com.dl.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LottoCathecticCell implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "投注号码")
	private String cathectic;

	@ApiModelProperty(value = "是否猜中 0-没猜中 1-猜中")
	private String isGuess;

}
