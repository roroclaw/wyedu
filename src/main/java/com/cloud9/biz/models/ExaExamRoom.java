package com.cloud9.biz.models;

import java.util.Date;

public class ExaExamRoom {
    private String id;

    private String name;

    private String examPlanId;

    private String classroomId;

    private Date date;

    private Date examStartTime;

    private Date examEndTime;

    private Date createTime;

    private Integer seatColNum;

    private Integer seatRowNum;

    private String seatAb;

    private String classroomText;

    private String examPlanText;

    private String buildingNoText;

    private String schoolYear;

    private String term;

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

    public String getExamPlanId() {
        return examPlanId;
    }

    public void setExamPlanId(String examPlanId) {
        this.examPlanId = examPlanId;
    }

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getExamStartTime() {
        return examStartTime;
    }

    public void setExamStartTime(Date examStartTime) {
        this.examStartTime = examStartTime;
    }

    public Date getExamEndTime() {
        return examEndTime;
    }

    public void setExamEndTime(Date examEndTime) {
        this.examEndTime = examEndTime;
    }

    public Integer getSeatColNum() {
        return seatColNum;
    }

    public void setSeatColNum(Integer seatColNum) {
        this.seatColNum = seatColNum;
    }

    public Integer getSeatRowNum() {
        return seatRowNum;
    }

    public void setSeatRowNum(Integer seatRowNum) {
        this.seatRowNum = seatRowNum;
    }

    public String getSeatAb() {
        return seatAb;
    }

    public void setSeatAb(String seatAb) {
        this.seatAb = seatAb;
    }

    public String getClassroomText() {
        return classroomText;
    }

    public void setClassroomText(String classroomText) {
        this.classroomText = classroomText;
    }

    public String getExamPlanText() {
        return examPlanText;
    }

    public void setExamPlanText(String examPlanText) {
        this.examPlanText = examPlanText;
    }

    public String getBuildingNoText() {
        return buildingNoText;
    }

    public void setBuildingNoText(String buildingNoText) {
        this.buildingNoText = buildingNoText;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}