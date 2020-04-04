package com.dl.shop.order.model;

import javax.persistence.*;

@Table(name = "dl_order_log")
public class OrderLog {
    /**
     * 操作记录id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单表主键id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 操作人userid
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 操作人
     */
    @Column(name = "show_name")
    private String showName;

    /**
     * 订单或者发货单状态
     */
    private Integer status;

    /**
     * 支付状态
     */
    @Column(name = "pay_status")
    private Integer payStatus;

    /**
     * 操作备注
     */
    private String content;

    /**
     * 操作时间
     */
    @Column(name = "add_time")
    private Integer addTime;

    /**
     * 操作IP
     */
    @Column(name = "add_ip")
    private String addIp;

    /**
     * 获取操作记录id
     *
     * @return id - 操作记录id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置操作记录id
     *
     * @param id 操作记录id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取订单表主键id
     *
     * @return order_id - 订单表主键id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单表主键id
     *
     * @param orderId 订单表主键id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取操作人userid
     *
     * @return user_id - 操作人userid
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置操作人userid
     *
     * @param userId 操作人userid
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取操作人
     *
     * @return show_name - 操作人
     */
    public String getShowName() {
        return showName;
    }

    /**
     * 设置操作人
     *
     * @param showName 操作人
     */
    public void setShowName(String showName) {
        this.showName = showName;
    }

    /**
     * 获取订单或者发货单状态
     *
     * @return status - 订单或者发货单状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置订单或者发货单状态
     *
     * @param status 订单或者发货单状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取支付状态
     *
     * @return pay_status - 支付状态
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     * 设置支付状态
     *
     * @param payStatus 支付状态
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取操作备注
     *
     * @return content - 操作备注
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置操作备注
     *
     * @param content 操作备注
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取操作时间
     *
     * @return add_time - 操作时间
     */
    public Integer getAddTime() {
        return addTime;
    }

    /**
     * 设置操作时间
     *
     * @param addTime 操作时间
     */
    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取操作IP
     *
     * @return add_ip - 操作IP
     */
    public String getAddIp() {
        return addIp;
    }

    /**
     * 设置操作IP
     *
     * @param addIp 操作IP
     */
    public void setAddIp(String addIp) {
        this.addIp = addIp;
    }
}