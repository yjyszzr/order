package com.dl.shop.order.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
@Data
@Table(name = "dl_order")
public class Order {
    /**
     * 订单id
     */
    @Id
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 订单号
     */
    @Column(name = "order_sn")
    private String orderSn;

    /**
     * app 标识
     */
    @Column(name = "app_code_name")
    private String appCodeName;

    /**
     * 混合投注时候存储的具体的玩法,以逗号分隔:1-让球胜平负,2-胜平负,3-比分,4-总进球,5-半全场
     */
    @Column(name = "play_type_detail")
    private String playTypeDetail;


    /**
     * 商户订单号
     */
    @Column(name = "merchant_order_sn")
    private String merchantOrderSn;

    /**
     * 商户号
     */
    @Column(name = "merchant_no")
    private String merchantNo;

    /**
     * 父订单号
     */
    @Column(name = "parent_sn")
    private String parentSn;

    /**
     */
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "mobile")
    private String mobile;

    /**
     * 订单状态:0-待开奖,1-未中奖,2-已中奖
     */
    @Column(name = "order_status")
    private Integer orderStatus;
    
    /**
     * 订单出票状态:1-待出票,2-部分出票失败已退款 3-全部出票失败已退款 4 出票成功
     */
    @Column(name = "print_lottery_status")
    private Integer printLotteryStatus;
    
    /**
     * 订单出票失败退款金额
     */
    @Column(name = "print_lottery_refund_amount")
    private BigDecimal printLotteryRefundAmount;
    
    

    /**
     * 支付状态
     */
    @Column(name = "pay_status")
    private Integer payStatus;

    /**
     * 支付id
     */
    @Column(name = "pay_id")
    private Integer payId;

    /**
     * 支付代码
     */
    @Column(name = "pay_code")
    private String payCode;

    /**
     * 支付名称
     */
    @Column(name = "pay_name")
    private String payName;

    @Column(name = "pay_sn")
    private String paySn;

    /**
     * 订单实付金额
     */
    @Column(name = "money_paid")
    private BigDecimal moneyPaid;

    /**
     * 彩票总金额
     */
    @Column(name = "ticket_amount")
    private BigDecimal ticketAmount;

    /**
     * 余额支付
     */
    private BigDecimal surplus;

    /**
     * 可提现余额支付
     */
    @Column(name = "user_surplus")
    private BigDecimal userSurplus;

    /**
     * 不可提现余额支付
     */
    @Column(name = "user_surplus_limit")
    private BigDecimal userSurplusLimit;
    
    /**
     * 第三方支付金额
     */
    @Column(name = "third_party_paid")
    private BigDecimal thirdPartyPaid;

    /**
     * 用户红包id
     */
    @Column(name = "user_bonus_id")
    private Integer userBonusId;

    /**
     * 用户红包金额
     */
    private BigDecimal bonus;

    /**
     * 订单赠送的积分
     */
    @Column(name = "give_integral")
    private Integer giveIntegral;

    /**
     * 订单来源
     */
    @Column(name = "order_from")
    private String orderFrom;
    
    /**
     * 彩票照片url
     */
    @Column(name = "pic")
    private String pic;

    /**
     * 订单生成时间
     */
    @Column(name = "add_time")
    private Integer addTime;

    /**
     * 订单支付时间
     */
    @Column(name = "pay_time")
    private Integer payTime;

    /**
     * 订单类型
     */
    @Column(name = "order_type")
    private Integer orderType;
    
    /**
     * 彩票种类id
     */
    @Column(name = "lottery_classify_id")
    private Integer lotteryClassifyId;
    
    
    @Column(name = "device_channel")
    private String deviceChannel;
    
    /**
     * 彩票子分类
     */
    @Column(name = "lottery_play_classify_id")
    private Integer lotteryPlayClassifyId;
    
    
    @Column(name = "award_time")
    private Integer awardTime;
    
    @Column(name = "max_level")
    private Integer maxLevel;
    
    
    
    
    public Integer getAwardTime() {
		return awardTime;
	}

	public void setAwardTime(Integer awardTime) {
		this.awardTime = awardTime;
	}

	public String getDeviceChannel() {
		return deviceChannel;
	}

	public void setDeviceChannel(String deviceChannel) {
		this.deviceChannel = deviceChannel;
	}

	public Integer getLotteryPlayClassifyId() {
		return lotteryPlayClassifyId;
	}

	public void setLotteryPlayClassifyId(Integer lotteryPlayClassifyId) {
		this.lotteryPlayClassifyId = lotteryPlayClassifyId;
	}
	
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}



	/**
     * 比赛时间
     */
    @Column(name = "match_time")
    private Date matchTime;
    
    /**
     * 中奖金额
     */
    @Column(name = "winning_money")
    private BigDecimal winningMoney;
    
    /**
     * 过关方式
     */
    @Column(name = "pass_type")
    private String passType;
    
    /**
     * 玩法
     */
    @Column(name = "play_type")
    private String playType;
    
    /**
     * 投注倍数
     */
    @Column(name = "cathectic")
    private Integer cathectic; 
    
    /**
     * 投注倍数
     */
    @Column(name = "bet_num")
    private Integer betNum;
    
    
    
    /**
     * 店铺接单时间（出票开始时间）
     */
    @Column(name = "accept_time")
    private Integer acceptTime;
    
    /**
     * 出票时间
     */
    @Column(name = "ticket_time")
    private Integer ticketTime;
    
    /**
     * 预测奖金
     */
    @Column(name = "forecast_money")
    private String forecastMoney;
    
    /**
     * 投注最后一场比赛期次
     */
    @Column(name = "issue")
    private String issue;

    /**
     * 是否彻底删除
     */
    @Column(name = "is_delete")
    private Integer isDelete;
    @Column(name = "ticket_num")
    private Integer ticketNum;
    
    @Column(name = "store_id")
    private Integer storeId;

    public String getPlayTypeDetail() {
        return playTypeDetail;
    }

    public void setPlayTypeDetail(String playTypeDetail) {
        this.playTypeDetail = playTypeDetail;
    }
}