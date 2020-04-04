package com.dl.order.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StoreDetailParam implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("店铺id")
	private Integer id;
}
