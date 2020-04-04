package com.dl.shop.order.model;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "dl_goods_order")
public class GoodsOrder {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商品Id
     */
    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * 订单图片
     */
    @Column(name = "order_pic")
    private String orderPic;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 总价
     */
    @Column(name = "total_price")
    private BigDecimal totalPrice;

    /**
     * 电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 描述
     */
    private String description;

    /**
     * 联系人
     */
    @Column(name = "contacts_name")
    private String contactsName;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取商品Id
     *
     * @return goods_id - 商品Id
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品Id
     *
     * @param goodsId 商品Id
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取订单图片
     *
     * @return order_pic - 订单图片
     */
    public String getOrderPic() {
        return orderPic;
    }

    /**
     * 设置订单图片
     *
     * @param orderPic 订单图片
     */
    public void setOrderPic(String orderPic) {
        this.orderPic = orderPic;
    }

    /**
     * 获取单价
     *
     * @return price - 单价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置单价
     *
     * @param price 单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取类型 (1:详情,2轮播)
     *
     * @return num - 类型 (1:详情,2轮播)
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置类型 (1:详情,2轮播)
     *
     * @param num 类型 (1:详情,2轮播)
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取总价
     *
     * @return total_price - 总价
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * 设置总价
     *
     * @param totalPrice 总价
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * 获取电话
     *
     * @return phone - 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取联系人
     *
     * @return contacts_name - 联系人
     */
    public String getContactsName() {
        return contactsName;
    }

    /**
     * 设置联系人
     *
     * @param contactsName 联系人
     */
    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }
}