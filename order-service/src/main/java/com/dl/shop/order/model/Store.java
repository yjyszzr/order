package com.dl.shop.order.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "dl_store")
public class Store {

	/**
	 * 主键自增ID
	 */
    @Id
    @Column(name = "id")
    private Integer storeId;

    /**
     * logo
     */
    @Column(name = "logo")
    private String logo;

    /**
     * 店铺名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 店主微信号
     */
    @Column(name = "wechat")
    private String wechat;
    
    /**
     * 店主手机号
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 微信图片
     */
    @Column(name = "img_wechat")
    private String imgWechat;

    /***
     * 关注数目
     */
    @Column(name = "coll_num")
    private Integer collNum;

    /**
     * 是否有营业资质
     */
    @Column(name = "biz_permit")
    private Integer bizPermit;
    
    /**
     * 是否是合作店铺
     */
    @Column(name = "cooper_auth")
    private Integer cooperAuth;

    /***
     * 营业执照图片地址
     */
    @Column(name = "biz_permit_url")
    private String bizPermitUrl;
    
    /**
     * 添加时间
     */
    @Column(name = "add_time")
    private Integer addTime;
    
	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImgWechat() {
		return imgWechat;
	}

	public void setImgWechat(String imgWechat) {
		this.imgWechat = imgWechat;
	}

	public Integer getCollNum() {
		return collNum;
	}

	public void setCollNum(Integer collNum) {
		this.collNum = collNum;
	}

	public Integer getBizPermit() {
		return bizPermit;
	}

	public void setBizPermit(Integer bizPermit) {
		this.bizPermit = bizPermit;
	}

	public Integer getCooperAuth() {
		return cooperAuth;
	}

	public void setCooperAuth(Integer cooperAuth) {
		this.cooperAuth = cooperAuth;
	}

	public Integer getAddTime() {
		return addTime;
	}

	public void setAddTime(Integer addTime) {
		this.addTime = addTime;
	}

	public String getBizPermitUrl() {
		return bizPermitUrl;
	}

	public void setBizPermitUrl(String bizPermitUrl) {
		this.bizPermitUrl = bizPermitUrl;
	}
}
