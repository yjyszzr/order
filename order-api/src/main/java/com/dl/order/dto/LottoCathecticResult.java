package com.dl.order.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LottoCathecticResult implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
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
