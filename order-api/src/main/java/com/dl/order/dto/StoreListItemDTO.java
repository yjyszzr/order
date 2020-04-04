package com.dl.order.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StoreListItemDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "店铺id")
	public Integer storeId;
	@ApiModelProperty(value = "店铺logo")
	public String logo;
	@ApiModelProperty(value = "店铺名称")
	public String name;
	@ApiModelProperty(value = "收藏用户数")
	public Integer collNum;
	@ApiModelProperty(value = "是否是合作店铺 0非合作店铺  1合作店铺")
	public Integer cooperAuth;
	@ApiModelProperty(value = "跳转url")
	public String jumpUrl;
}
