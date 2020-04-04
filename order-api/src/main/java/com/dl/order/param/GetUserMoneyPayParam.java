package com.dl.order.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("")
@Data
public class GetUserMoneyPayParam {

	@ApiModelProperty("用户id")
	private Integer userId;
}
