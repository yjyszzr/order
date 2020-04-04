package com.dl.order.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StoreInfoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "店铺列表")
	public List<StoreListItemDTO> list;
	@ApiModelProperty(value = "什么是合作店铺协议,空不展示，非空展示 ")
	public String protocalUrl;
}
