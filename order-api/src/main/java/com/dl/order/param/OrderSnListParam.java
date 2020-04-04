package com.dl.order.param;

import java.util.List;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderSnListParam {
	@ApiModelProperty("订单号集合")
	private List<String> orderSnlist;
	@ApiModelProperty("店铺ID")
	private Integer storeId;
}
