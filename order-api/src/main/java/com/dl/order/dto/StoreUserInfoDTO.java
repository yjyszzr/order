package com.dl.order.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("业务配置信息")
@Data
public class StoreUserInfoDTO {
	@ApiModelProperty(value = "是否是超级白名单")
	private String isSuperWhite;
	@ApiModelProperty(value = "用户余额")
	private String money;
	@ApiModelProperty(value = "可用优惠券数量")
	private Integer bonusNum;
	@ApiModelProperty(value = "实扣金额")
	private String actualAmount;
	@ApiModelProperty(value = "代金券名称")
	private String bonusName;
	@ApiModelProperty(value = "代金券金额")
	private String bonusPrice;
}
