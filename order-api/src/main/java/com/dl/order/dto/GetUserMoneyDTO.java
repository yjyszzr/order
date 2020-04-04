package com.dl.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetUserMoneyDTO {

	@ApiModelProperty("用户总购彩实付金额")
	private Double moneyPaid;
}
