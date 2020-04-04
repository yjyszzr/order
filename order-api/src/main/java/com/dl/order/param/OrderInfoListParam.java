package com.dl.order.param;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderInfoListParam implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@ApiModelProperty("订单状态 -1-所有订单 3-待开奖 5-已中奖")
	private String orderStatus;
	
	@ApiModelProperty("店铺Id")
	private String storeId;
 
	@ApiModelProperty("彩票种类id:1-足彩 2-大乐透 3-篮彩")
	private String lotteryClassifyId;
	
	@ApiModelProperty("页数")
	private String pageNum;
	
	@ApiModelProperty("条数")
	private String pageSize;

}
