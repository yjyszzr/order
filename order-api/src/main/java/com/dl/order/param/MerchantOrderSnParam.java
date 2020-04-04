package com.dl.order.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MerchantOrderSnParam implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("商户号")
	private String merchant;

	@ApiModelProperty("商户订单编号")
	private String merchantOrderSn;

}
