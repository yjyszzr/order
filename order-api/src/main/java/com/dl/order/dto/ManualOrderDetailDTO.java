package com.dl.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ManualOrderDetailDTO {

	@ApiModelProperty(value = "彩种id")
	private String LotteryClassifyId;

	@ApiModelProperty(value = "足彩订单详情DTO")
	private OrderDetailDTO orderDetailDto;

	@ApiModelProperty(value = "大乐透订单详情DTO")
	private LottoOrderDetailDTO lottoOrderDetailDTO;
	
	@ApiModelProperty(value = "出票方案DTO")
	private TicketSchemeDTO ticketSchemeDTO;
	
	@ApiModelProperty(value = "彩票照片详情DTO")
	private TicketPicDTO ticketPicDTO;
}
