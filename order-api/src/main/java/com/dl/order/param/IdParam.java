package com.dl.order.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class IdParam implements Serializable{

	@ApiModelProperty(value="商品id")
	private Integer goodsId;
 
}
