package com.dl.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ServDTO {

	@ApiModelProperty(value = "服务item id")
	private Integer servId;
	@ApiModelProperty(value = "列表itemlogo")
    private String logo;
	@ApiModelProperty(value = "列表item名称")
    private String name;
	@ApiModelProperty(value = "列表item跳转url")
	private String url;
}
