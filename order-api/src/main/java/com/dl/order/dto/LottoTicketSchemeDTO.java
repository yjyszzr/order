package com.dl.order.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LottoTicketSchemeDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "方案编号")
    private String programmeSn;
	
	@ApiModelProperty(value = "投注信息")
    private List<LottoTicketSchemeDetailDTO> LottoTicketSchemeDetailDTOs;
	
	@Data
	public static class LottoTicketSchemeDetailDTO {
		
		@ApiModelProperty(value = "序号")
	    private String number;
		
		@ApiModelProperty(value = "出票编号")
	    private String ticketSn;
		
		@ApiModelProperty("出票状态， 0-待出票 1-已出票 2-出票失败 3-出票中")
		private Integer status;
		
		@ApiModelProperty(value = "红球投注列表")
		private List<LottoCathecticCell> redCathectics;
		
		@ApiModelProperty(value = "蓝球投注列表")
		private List<LottoCathecticCell> blueCathectics;
		
		@ApiModelProperty(value = "红胆投注列表")
		private List<LottoCathecticCell> redDanCathectics;
		
		@ApiModelProperty(value = "蓝胆投注列表")
		private List<LottoCathecticCell> blueDanCathectics;
		
		@ApiModelProperty(value = "红拖投注列表")
		private List<LottoCathecticCell> redTuoCathectics;
		
		@ApiModelProperty(value = "蓝拖投注列表")
		private List<LottoCathecticCell> blueTuoCathectics;

		@ApiModelProperty(value = "投注倍数")
		private Integer cathectic;

		@ApiModelProperty(value = "投注注数")
		private Integer betNum;

		@ApiModelProperty(value="彩票购买金额")
		private String amount;

		@ApiModelProperty(value="是否追加，0否1是")
		private Integer isAppend;

		@ApiModelProperty(value = "玩法:0单式，1复式，2胆拖")
		private Integer playType;
	}
}
