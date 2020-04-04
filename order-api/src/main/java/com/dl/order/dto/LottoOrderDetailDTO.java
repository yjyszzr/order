package com.dl.order.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LottoOrderDetailDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "彩种id")
    private String lotteryClassifyId;
	
	@ApiModelProperty(value = "彩种名称")
    private String lotteryClassifyName;
	
	@ApiModelProperty(value = "彩种图片")
    private String lotteryClassifyImg;
	
	@ApiModelProperty(value = "期次")
	private String termNum;
	
	@ApiModelProperty(value = "订单状态:0-待付款,1-待出票,2-出票失败3-待开奖4-未中将5-已中奖6-派奖中7-审核中8-支付失败")
	private String orderStatus;
	
	@ApiModelProperty(value = "订单状态描述")
	private String orderStatusDesc;
	
	@ApiModelProperty(value = "处理结果")
    private String processResult;
	
	@ApiModelProperty(value = "处理状态描述")
    private String processStatusDesc;
	
	@ApiModelProperty(value = "订单中奖金额")
	private String winningMoney;
	
	@ApiModelProperty(value="彩票购买总金额")
	private String ticketAmount;
	
	@ApiModelProperty(value = "实际消费金额")
    private String moneyPaid;
	
	@ApiModelProperty(value="余额支付总金额")
	private String surplus;

	@ApiModelProperty(value="第三方支付金额")
	private String thirdPartyPaid;
	
	@ApiModelProperty(value="支付名称")
	private String payName;

	@ApiModelProperty(value="红包金额")
	private String bonus;

	@ApiModelProperty(value="可提现金额")
	private String userSurplus;

	@ApiModelProperty(value="不可提现金额")
	private String userSurplusLimit;
	
	@ApiModelProperty(value="开奖号码")
	private List<String> prizeNum;
	
	@ApiModelProperty(value="开奖前提示")
	private String prePrizeInfo;
	
	@ApiModelProperty(value = "方案编号")
    private String programmeSn;
	
	@ApiModelProperty(value = "创建时间")
    private String createTime;
	
	@ApiModelProperty(value = "接单时间")
    private String acceptTime;
	
	@ApiModelProperty(value = "出票时间")
    private String ticketTime;
	
	@ApiModelProperty(value = "订单编号")
    private String orderSn;
	
	@ApiModelProperty(value = "订单投注详情")
	private List<LottoCathecticResult>  cathecticResults;
	
	@ApiModelProperty(value = "添加好友URL")
	private String addFriendsQRBarUrl;
	
	@ApiModelProperty(value = "附加信息")
	private List<OrderAppendInfoDTO> appendInfoList;
	
	@ApiModelProperty(value = "是否展示店铺 0隐藏 1展示")
	private int showStore;
	
	@ApiModelProperty(value = "支付相关信息")
	private StoreUserInfoDTO userInfo;

	@ApiModelProperty(value = "店铺ID")
	private int storeId;
	
	@ApiModelProperty(value = "分享链接")
	private String orderShareUrl;
	
	@ApiModelProperty(value = "是否追加")
	private int isAppend;
	
	@ApiModelProperty(value = "投注注数")
	private int betNum;
	
	@ApiModelProperty(value = "投注倍数")
	private Integer cathectic; 
}
