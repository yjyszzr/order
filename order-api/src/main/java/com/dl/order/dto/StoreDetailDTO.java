package com.dl.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StoreDetailDTO {
	@ApiModelProperty(value = "店铺id")
    private Integer storeId;
	@ApiModelProperty(value = "店铺logo")
    private String logo;
	@ApiModelProperty(value = "店铺名称")
    private String name;
	@ApiModelProperty(value = "店主微信")
    private String wechat;
	@ApiModelProperty(value = "店主微信二维码")
    private String imgWechat;
	@ApiModelProperty(value = "收藏个数")
    private Integer collNum;
	@ApiModelProperty(value = "是否有营业执照")
    private Integer bizPermit;
	@ApiModelProperty(value = "营业执照图片地址")
    private String bizPermitUrl;
	@ApiModelProperty(value = "是否是合作店铺 0非合作店铺 1合作店铺")
    private Integer cooperAuth;
	@ApiModelProperty(value = "点击跳转到店铺外链")
    private String jumpUrl;
}
