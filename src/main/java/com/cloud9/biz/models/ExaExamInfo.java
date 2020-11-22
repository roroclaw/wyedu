package com.cloud9.biz.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ExaExamInfo {
    private String id;

    private String examPlanId;

    private String subjectId;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date date;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private Date createTime;

    private String creator;

    private Date updateTime;

    private String updater;

    private String status;

    private String statusText;

    private String examTypeText;

    private String examPlanName;

    private String subjectName;

    private String orderParam;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExamPlanId() {
        return examPlanId;
    }

    public void setExamPlanId(String examPlanId) {
        this.examPlanId = examPlanId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getExamTypeText() {
        return examTypeText;
    }

    public void setExamTypeText(String examTypeText) {
        this.examTypeText = examTypeText;
    }

    public String getExamPlanName() {
        return examPlanName;
    }

    public void setExamPlanName(String examPlanName) {
        this.examPlanName = examPlanName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getOrderParam() {
        return orderParam;
    }

    public void setOrderParam(String orderParam) {
        this.orderParam = orderParam;
    }
}