package com.cloud9.biz.models;

import java.util.Date;

public class SysTchScoresRuleConf {
    private String id;

    private String name;

    private String teacherId;

    private String subjectId;

    private String subjectName;

    private String gradeScopeId;

    private Double middelRatio;

    private Double endRatio;

    private Double usualRatio;

    private Date createTime;

    private String creator;

    private Date updateTime;

    private String updater;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getGradeScopeId() {
        return gradeScopeId;
    }

    public void setGradeScopeId(String gradeScopeId) {
        this.gradeScopeId = gradeScopeId;
    }

    public Double getMiddelRatio() {
        return middelRatio;
    }

    public void setMiddelRatio(Double middelRatio) {
        this.middelRatio = middelRatio;
    }

    public Double getEndRatio() {
        return endRatio;
    }

    public void setEndRatio(Double endRatio) {
        this.endRatio = endRatio;
    }

    public Double getUsualRatio() {
        return usualRatio;
    }

    public void setUsualRatio(Double usualRatio) {
        this.usualRatio = usualRatio;
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
}