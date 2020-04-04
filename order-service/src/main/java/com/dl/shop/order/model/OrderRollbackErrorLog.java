package com.dl.shop.order.model;

import javax.persistence.*;

@Table(name = "dl_order_rollback_error_log")
public class OrderRollbackErrorLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单主键
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 1 下单
     */
    @Column(name = "biz_type")
    private Integer bizType;

    /**
     * 失败类型 0 回滚账户失败
     */
    @Column(name = "error_type")
    private Integer errorType;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取订单主键
     *
     * @return order_id - 订单主键
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单主键
     *
     * @param orderId 订单主键
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取1 下单
     *
     * @return biz_type - 1 下单
     */
    public Integer getBizType() {
        return bizType;
    }

    /**
     * 设置1 下单
     *
     * @param bizType 1 下单
     */
    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    /**
     * 获取失败类型 0 回滚账户失败
     *
     * @return error_type - 失败类型 0 回滚账户失败
     */
    public Integer getErrorType() {
        return errorType;
    }

    /**
     * 设置失败类型 0 回滚账户失败
     *
     * @param errorType 失败类型 0 回滚账户失败
     */
    public void setErrorType(Integer errorType) {
        this.errorType = errorType;
    }
}