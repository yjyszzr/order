package com.dl.shop.order.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "dl_serv")
public class Serv {
	/**
	 * 主键自增ID
	 */
    @Id
    @Column(name = "id")
    private Integer servId;
    
    /**
     * logo
     */
    @Column(name = "logo")
    private String logo;

    /**
     * 展示名称
     */
    @Column(name = "name")
    private String name;
    
    
    /**
     * 跳转url
     */
    @Column(name = "url")
    private String url;

    /**
     * 跳转url
     */
    @Column(name = "is_show")
    private Integer isShow;
    
    /**
     * 添加时间
     */
    @Column(name = "add_time")
    private Long addTime;

	public Integer getServId() {
		return servId;
	}

	public void setServId(Integer servId) {
		this.servId = servId;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public Long getAddTime() {
		return addTime;
	}

	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
}
