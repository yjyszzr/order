package com.dl.order.param;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateOrderStatusByAnotherStatusParam {

	@ApiModelProperty("要更新的订单状态的订单号集合")
	@NotEmpty(message = "订单号集合不能为空")
	private List<String> orderSnlist;
	
	@ApiModelProperty("更新前的状态")
	@NotNull(message = "更新前的状态")
	private String orderStatusBefore;
	
	@ApiModelProperty("更新后的状态")
	@NotNull(message = "更新后的状态")
	private String orderStatusAfter;
	
}
