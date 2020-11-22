package com.cloud9.biz.models;

import java.util.Date;

public class SysScoresRuleConfig {
    private String id;

    private String subjectId = null;

    private Double middelRatio = 0d;

    private Double endRatio = 0d;

    private Double usualRatio = 0d;

    private Date createTime = null;

    private String creator = null;

    private Date updateTime = null;

    private String updater = null;

    private String subjectName = null;

    private String schoolYear;

    private String courseName;

    private String termText;

    private String className;

    private String teacherName;

    private String scopeName;

    private String gradeScopeId;

    private String scope;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getGradeScopeId() {
        return gradeScopeId;
    }

    public void setGradeScopeId(String gradeScopeId) {
        this.gradeScopeId = gradeScopeId;
    }

    public String getScopeName() {
        return scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTermText() {
        return termText;
    }

    public void setTermText(String termText) {
        this.termText = termText;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

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

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
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