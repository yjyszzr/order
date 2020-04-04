package com.dl.order.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderInfoListDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("订单id")
	private String orderId;
	
	@ApiModelProperty("订单编号")
	private String orderSn;
	
	@ApiModelProperty("彩票种类名称")
	private String lotteryName;
	
	@ApiModelProperty("订单状态 2-出票失败 3-待开奖 4-未中奖 5-已中奖")
	private String orderStatus;
	@ApiModelProperty("支付状态0待支付1已支付2支付失败")
	private Integer orderPayStatus;
	
	@ApiModelProperty("订单状态描述")
	private String orderStatusDesc;
	
	@ApiModelProperty("订单状态信息")
	private String orderStatusInfo;
	
	@ApiModelProperty("订单实付金额")
	private String moneyPaid;
	
	@ApiModelProperty("彩票总金额")
	private String ticketAmount;
	
	@ApiModelProperty("订单支付时间")
	private String payTime;
	
	@ApiModelProperty("订单下单时间")
	private String addTime;
	
	@ApiModelProperty("比赛时间")
	private String matchTime;
	
	@ApiModelProperty("中奖金额")
	private String winningMoney;
	
	@ApiModelProperty("玩法")
	private Integer lotteryPlayClassifyId;
	
	@ApiModelProperty("彩种")
	private Integer lotteryClassifyId;
	
	@ApiModelProperty("投注类型:0竞彩1世界杯")
	private Integer orderType;
	
	@ApiModelProperty("中奖级别")
	private Integer maxLevel;
}
