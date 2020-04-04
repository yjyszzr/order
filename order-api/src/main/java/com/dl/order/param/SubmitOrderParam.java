package com.dl.order.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class SubmitOrderParam implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "订单实付金额", required = true)
    private BigDecimal moneyPaid;
	
	@ApiModelProperty(value = "商品总金额", required = true)
    private BigDecimal ticketAmount;
	
	@ApiModelProperty("余额支付金额")
	private BigDecimal surplus;
	
	@ApiModelProperty("第三方支付金额金额")
	private BigDecimal thirdPartyPaid;
	@ApiModelProperty("第三方支付方式名称")
	private String payName;
	@ApiModelProperty("第三方支付方式编码")
	private String payCode;
	@ApiModelProperty(value = "用户红包id", required = true)
    private Integer userBonusId;
	
	@ApiModelProperty(value = "用户红包金额", required = true)
    private BigDecimal bonusAmount;
	
	@ApiModelProperty(value = "订单来源 1-android,2-ios,3-h5", required = true)
    private String orderFrom;
	
	@ApiModelProperty("彩票种类id")
	private Integer lotteryClassifyId;
	
	@ApiModelProperty("彩票子分类id")
	private Integer lotteryPlayClassifyId;

	@ApiModelProperty(value = "比赛时间", required = true)
    private Date matchTime;
	
	@ApiModelProperty("过关方式")
	private String passType;
	
	@ApiModelProperty("玩法")
	private String playType;
	
	@ApiModelProperty("投注倍数")
	private Integer cathectic;
	
	@ApiModelProperty("投注倍数")
	private Integer betNum;
	
	@ApiModelProperty("预测奖金")
	private String forecastMoney;
	
	@ApiModelProperty("投注最后一场比赛期次")
	private String issue;
	
	@ApiModelProperty(value = "商品详情列表", required = true)
    private List<TicketDetail> ticketDetails;
	
	@ApiModelProperty("投注票数")
	private Integer ticketNum;
	
	@ApiModelProperty("店铺id")
	private Integer storeId;
	
	@ApiModelProperty("商户订单号 注：对应您方彩票的唯一标识")
	private String merchantOrderSn = "";
	@ApiModelProperty("代理商户号 示例：test")
	private String merchantNo = "";
	@ApiModelProperty("用户ID")
	private String _userId;
	@ApiModelProperty("订单SN")
	private String _orderSn;
	@ApiModelProperty("元订单SN")
	private String _orderSn_new;
	@ApiModelProperty("订单是否有效")
	private Integer isDelete;
	@ApiModelProperty("混合投注时候存储的具体的玩法:1-让球胜平负,2-胜平负,3-比分,4-总进球,5-半全场")
	private String playTypeDetail;
	private String payToken;
	@Data
	public static class TicketDetail {
		
		@ApiModelProperty(value = "赛事id", required = true)
	    private Integer match_id;
		
		@ApiModelProperty(value = "场次", required = true)
	    private String changci;
		
		@ApiModelProperty(value = "期次", required = true)
	    private String issue;
		
		@ApiModelProperty(value = "比赛双方球队", required = true)
	    private String matchTeam;
		
		@ApiModelProperty(value = "比赛结果", required = true)
	    private String matchResult;
		
		@ApiModelProperty(value = "彩票种类", required = true)
	    private Integer lotteryClassifyId;
		
		@ApiModelProperty(value = "彩票子分类", required = true)
	    private Integer lotteryPlayClassifyId;
		
		@ApiModelProperty(value = "商品数据投注内容", required = true)
	    private String ticketData;
		
		@ApiModelProperty(value = "是否有胆 0-否 1-是", required = true)
	    private Integer isDan;
		
		@ApiModelProperty(value = "比赛时间", required = true)
	    private Date matchTime;
		
		@ApiModelProperty(value = "让球数", required = true)
		private String fixedodds;
		
		@ApiModelProperty(value = "预设分", required = true)
		private String forecastScore;
		
		@ApiModelProperty(value = "投注方式：大乐透:0单式，1复式，2胆拖")
		private String betType;
	}
}
