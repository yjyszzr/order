package com.dl.order.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ManualLottoOrderDetailDTO {
	
	@ApiModelProperty(value = "订单详情DTO")
	private LottoOrderDetailDTO orderDetailDto;

	
	
//	@ApiModelProperty(value = "出票方案DTO")
//	private LottoTicketSchemeDTO ticketSchemeDTO;
//	
//	@ApiModelProperty(value = "彩票照片详情DTO")
//	private TicketPicDTO ticketPicDTO;
}
