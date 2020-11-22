package com.cloud9.biz.models.vo;

import com.roroclaw.base.bean.UserBean;

import java.util.Date;

/**
 * Created by dengxianzhi on 2017/1/29.
 */
public class VUserInfo extends UserBean {
    private String id;

    private String loginName;

    private String weixinOpenid;

    private String encryptPass;

    private String realName;

    private Date registerDate;

    private String imgUrl;

    private String accToken;

    private Date tokenTime;

    private Integer regChannel;

    private Integer type;

    private Integer status;

    private String statusText;

    private String typeText;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getWeixinOpenid() {
        return weixinOpenid;
    }

    public void setWeixinOpenid(String weixinOpenid) {
        this.weixinOpenid = weixinOpenid;
    }

    public String getEncryptPass() {
        return encryptPass;
    }

    public void setEncryptPass(String encryptPass) {
        this.encryptPass = encryptPass;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAccToken() {
        return accToken;
    }

    public void setAccToken(String accToken) {
        this.accToken = accToken;
    }

    public Date getTokenTime() {
        return tokenTime;
    }

    public void setTokenTime(Date tokenTime) {
        this.tokenTime = tokenTime;
    }

    public Integer getRegChannel() {
        return regChannel;
    }

    public void setRegChannel(Integer regChannel) {
        this.regChannel = regChannel;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getTypeText() {
        return typeText;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }
}
