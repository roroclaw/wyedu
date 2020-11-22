package com.cloud9.biz.models;

import java.util.Date;

public class ArcStudentPlanRel {
    private String id;

    private String stuId;

    private String planId;

    private Date createTime;

    private String creator;

    private Date updateTime;

    private String updater;

    private String status;

    private String planName;

    private String planMajorText;

    private String realName;

    private String identityCard;

    private String stuNum;

    private String stuMajorText;

    private String planPeriodText;

    private String className;

    private String statusText;

    private String sexText;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getPlanMajorText() {
        return planMajorText;
    }

    public void setPlanMajorText(String planMajorText) {
        this.planMajorText = planMajorText;
    }

    public String getStuMajorText() {
        return stuMajorText;
    }

    public void setStuMajorText(String stuMajorText) {
        this.stuMajorText = stuMajorText;
    }

    public String getPlanPeriodText() {
        return planPeriodText;
    }

    public void setPlanPeriodText(String planPeriodText) {
        this.planPeriodText = planPeriodText;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getSexText() {
        return sexText;
    }

    public void setSexText(String sexText) {
        this.sexText = sexText;
    }
}