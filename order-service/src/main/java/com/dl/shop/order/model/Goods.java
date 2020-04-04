package com.dl.shop.order.model;

import java.math.BigDecimal;
import javax.persistence.*;

import lombok.Data;
@Data
@Table(name = "dl_goods")
public class Goods {
    /**
     * 主键Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 商品编码
     */
    @Column(name = "goods_code")
    private String goodsCode;

    /**
     * 描述
     */
    private String description;

    /**
     * 付款人数
     */
    @Column(name = "paid_num")
    private Integer paidNum;

    /**
     * 历史价格
     */
    @Column(name = "history_price")
    private BigDecimal historyPrice;

    /**
     * 现在价格
     */
    @Column(name = "present_price")
    private BigDecimal presentPrice;

    /**
     * 基本属性
     */
    @Column(name = "base_attribute")
    private String baseAttribute;

    /**
     * 列表页主图
     */
    @Column(name = "main_pic")
    private String mainPic;

    /**
     * 下单主图
     */
    @Column(name = "order_pic")
    private String orderPic;
    
    /**
     * 添加时间
     */
    @Column(name = "add_time")
    private Integer addTime;
     
}