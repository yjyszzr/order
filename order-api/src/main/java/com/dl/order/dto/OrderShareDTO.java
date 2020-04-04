package com.dl.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderShareDTO {
	@ApiModelProperty(value = "足彩详情")
	public OrderDetailDTO orderDetailDTO;
	@ApiModelProperty(value = "乐透")
	public LottoOrderDetailDTO lottoDetailDTO;
	@ApiModelProperty(value = "分类 1足彩  2大乐透")
	public Integer lotteryClassifyId;
}
