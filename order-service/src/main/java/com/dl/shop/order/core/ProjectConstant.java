package com.dl.shop.order.core;

/**
 * 项目常量
 */
public class ProjectConstant {
	public static final String BASE_PACKAGE = "com.dl.shop.order";//项目基础包名称，根据自己公司的项目修改

    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".model";//Model所在包
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao";//Mapper所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";//Service所在包
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";//ServiceImpl所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".web";//Controller所在包

    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".mapper.Mapper";//Mapper插件基础接口的完全限定名
    public static final String MAPPER_BASE = "com.dl.base.mapper.Mapper";//Mapper插件基础接口的完全限定名
    
    public static final Integer ORDER_STATUS_NOT_PAY = 0;         //待付款
    public static final Integer ORDER_STATUS_PAY_FAIL_LOTTERY = 1;//待出票
    public static final Integer ORDER_STATUS_FAIL_LOTTERY = 2;    //出票失败
    public static final Integer ORDER_STATUS_STAY = 3;            //待开奖
    public static final Integer ORDER_STATUS_NOT = 4;             //未中奖
    public static final Integer ORDER_STATUS_ALREADY = 5;         //已中奖
    public static final Integer ORDER_STATUS_REWARDING = 6;       //派奖审核中
    public static final Integer ORDER_STATUS_REWARDED = 7;        //大件派奖中
    public static final Integer ORDER_STATUS_AWARD_SENDED = 9;	  //已派奖
    
    
    public static final Integer PAY_STATUS_STAY = 0;   //待支付
    public static final Integer PAY_STATUS_ALREADY = 1;//已支付
    
    public static final Integer TICKET_STATUS_NOT = 1;    //未中奖
    public static final Integer TICKET_STATUS_ALREADY = 2;//已中奖
    
    public static final Integer MATCH_TIME_LONG = 2;//比赛时长（小时）
    
    /**
     * 取消赛事的比赛结果
     */
    public static final String ORDER_MATCH_RESULT_CANCEL = "-1";
    
	// 订单支付payToken缓存时长,单位分
	public final static long ORDER_INFO_EXPIRE_TIME = 10;
	// 商城下单跳转业务Id
	public final static Integer ORDER_INFO_SKIP_BISSION_ID = 30;
}
