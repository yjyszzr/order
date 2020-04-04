package com.dl.shop.order.model;

import javax.persistence.*;

@Table(name = "dl_goods_pic")
public class GoodsPic {
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
     * 图片名称
     */
    @Column(name = "image_name")
    private String imageName;

    /**
     * 图片
     */
    private String image;

    /**
     * 类型 (1:详情,2轮播)
     */
    @Column(name = "image_type")
    private Integer imageType;

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
     * 获取图片名称
     *
     * @return image_name - 图片名称
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * 设置图片名称
     *
     * @param imageName 图片名称
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     * 获取图片
     *
     * @return image - 图片
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置图片
     *
     * @param image 图片
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取类型 (1:详情,2轮播)
     *
     * @return image_type - 类型 (1:详情,2轮播)
     */
    public Integer getImageType() {
        return imageType;
    }

    /**
     * 设置类型 (1:详情,2轮播)
     *
     * @param imageType 类型 (1:详情,2轮播)
     */
    public void setImageType(Integer imageType) {
        this.imageType = imageType;
    }
}