package com.dl.shop.order.model;

import javax.persistence.*;

@Table(name = "dl_user_auths")
public class DlUserAuths {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * h5店铺的user_id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 第三方用户id，例如彩小秘的user_id
     */
    @Column(name = "third_user_id")
    private Integer thirdUserId;

    /**
     * 第三方账号手机号
     */
    @Column(name = "third_mobile")
    private String thirdMobile;

    /**
     * 第三方账号密码
     */
    @Column(name = "third_pass")
    private String thirdPass;

    /**
     * 第三方账号盐
     */
    @Column(name = "third_salt")
    private String thirdSalt;

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
     * 获取h5店铺的user_id
     *
     * @return user_id - h5店铺的user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置h5店铺的user_id
     *
     * @param userId h5店铺的user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取第三方用户id，例如彩小秘的user_id
     *
     * @return third_user_id - 第三方用户id，例如彩小秘的user_id
     */
    public Integer getThirdUserId() {
        return thirdUserId;
    }

    /**
     * 设置第三方用户id，例如彩小秘的user_id
     *
     * @param thirdUserId 第三方用户id，例如彩小秘的user_id
     */
    public void setThirdUserId(Integer thirdUserId) {
        this.thirdUserId = thirdUserId;
    }

    /**
     * 获取第三方账号手机号
     *
     * @return third_mobile - 第三方账号手机号
     */
    public String getThirdMobile() {
        return thirdMobile;
    }

    /**
     * 设置第三方账号手机号
     *
     * @param thirdMobile 第三方账号手机号
     */
    public void setThirdMobile(String thirdMobile) {
        this.thirdMobile = thirdMobile;
    }

    /**
     * 获取第三方账号密码
     *
     * @return third_pass - 第三方账号密码
     */
    public String getThirdPass() {
        return thirdPass;
    }

    /**
     * 设置第三方账号密码
     *
     * @param thirdPass 第三方账号密码
     */
    public void setThirdPass(String thirdPass) {
        this.thirdPass = thirdPass;
    }

    /**
     * 获取第三方账号盐
     *
     * @return third_salt - 第三方账号盐
     */
    public String getThirdSalt() {
        return thirdSalt;
    }

    /**
     * 设置第三方账号盐
     *
     * @param thirdSalt 第三方账号盐
     */
    public void setThirdSalt(String thirdSalt) {
        this.thirdSalt = thirdSalt;
    }
}