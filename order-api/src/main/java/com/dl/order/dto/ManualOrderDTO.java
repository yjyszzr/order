package com.dl.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ManualOrderDTO {
	
	@ApiModelProperty(value = "订单详情DTO")
	private OrderDetailDTO orderDetailDto;
	
	@ApiModelProperty(value = "出票方案DTO")
	private TicketSchemeDTO ticketSchemeDTO;
}
