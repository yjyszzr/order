package com.dl.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class OrderAppendInfoDTO {
	
	@ApiModelProperty(value = "是否展示：0- 店主微信信息块 1-店铺信息块")
	private String type;


	@ApiModelProperty(value = "图片块")
	private String imgurl;


	@ApiModelProperty(value = "手机号")
	private String phone;

	@ApiModelProperty(value = "跳转连接")
	private String pushurl;


	@ApiModelProperty(value = "微信号")
	private String wechat;

}
