package com.dl.shop.order.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Table(name = "dl_user")
public class User {
    /**
     * 用户ID
     */
    @Id
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 手机号码
     */
    private String mobile;

    private String email;

    /**
     * 登录密码
     */
    private String password;

    private String salt;

    /**
     * 昵称
     */
    @Column(name = "nickname")
    private String nickName;

    /**
     * 性别:0-未知,1-男,2-女
     */
    private Boolean sex;

    /**
     * 出生日期
     */
    private Integer birthday;

    /**
     * 详细地址
     */
    @Column(name = "detail_address")
    private String detailAddress;

    /**
     * 头像
     */
    @Column(name = "headimg")
    private String headImg;

    /**
     * 可提现余额(中的钱)
     */
    @Column(name = "user_money")
    private BigDecimal userMoney;

    /**
     * 不可提现余额(充值的钱)
     */
    @Column(name = "user_money_limit")
    private BigDecimal userMoneyLimit;

    /**
     * 冻结金额
     */
    @Column(name = "frozen_money")
    private BigDecimal frozenMoney;

    /**
     * 消费积分
     */
    @Column(name = "pay_point")
    private Integer payPoint;

    /**
     * 成长值
     */
    @Column(name = "rank_point")
    private Integer rankPoint;

    /**
     * 注册时间
     */
    @Column(name = "reg_time")
    private Integer regTime;

    /**
     * 注册IP地址
     */
    @Column(name = "reg_ip")
    private String regIp;

    /**
     * 最近登录时间
     */
    @Column(name = "last_time")
    private Integer lastTime;

    /**
     * 最近登录IP地址
     */
    @Column(name = "last_ip")
    private String lastIp;

    /**
     * 手机运营商
     */
    @Column(name = "mobile_supplier")
    private String mobileSupplier;

    /**
     * 归属省
     */
    @Column(name = "mobile_province")
    private String mobileProvince;

    /**
     * 归属市
     */
    @Column(name = "mobile_city")
    private String mobileCity;

    /**
     * 注册来源
     */
    @Column(name = "reg_from")
    private String regFrom;

    /**
     * 余额支付密码
     */
    @Column(name = "surplus_password")
    private String surplusPassword;

    /**
     * 支付密码盐
     */
    @Column(name = "pay_pwd_salt")
    private String payPwdSalt;

    /**
     * 用户状态:0-正常,1-被锁定,2-被冻结
     */
    @Column(name = "user_status")
    private Integer userStatus;

    /**
     * 密码错误次数
     */
    @Column(name = "pass_wrong_count")
    private Integer passWrongCount;

    /**
     * 用户类型
     */
    @Column(name = "user_type")
    private Boolean userType;

    /**
     * 是否通过实名认证
     */
    @Column(name = "is_real")
    private String isReal;

    /**
     * 会员备注
     */
    @Column(name = "user_remark")
    private String userRemark;
    
    /**
     * 消息推送key
     */
    @Column(name = "push_key")
    private String pushKey;
    
    @Column(name = "device_channel")
    private String deviceChannel;
    
    @Column(name = "is_business")
    private Integer isBusiness;
    
    
}