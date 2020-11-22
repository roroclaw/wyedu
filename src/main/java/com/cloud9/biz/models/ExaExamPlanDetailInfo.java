package com.cloud9.biz.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ExaExamPlanDetailInfo {
    private String id;

    private String examNum;

    private String stuId;

    private String examId;

    private String examRoomId;

    private Integer seatNum;

    private String seatAb;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExamNum() {
        return examNum;
    }

    public void setExamNum(String examNum) {
        this.examNum = examNum;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getExamRoomId() {
        return examRoomId;
    }

    public void setExamRoomId(String examRoomId) {
        this.examRoomId = examRoomId;
    }

    public Integer getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
    }

    public String getSeatAb() {
        return seatAb;
    }

    public void setSeatAb(String seatAb) {
        this.seatAb = seatAb;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /////////////////////////

    private String statusText;

    private String stuNumber;

    private String realName;

    private String identityCard;

    private String className;

    private String gradeName;

    private String courseOpenIDs;

    private String examRoomName;

    private Integer stuNum;

    private String grade;

    private String examStatus;

    private String subjectName;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date date;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getCourseOpenIDs() {
        return courseOpenIDs;
    }

    public void setCourseOpenIDs(String courseOpenIDs) {
        this.courseOpenIDs = courseOpenIDs;
    }

    public String getExamRoomName() {
        return examRoomName;
    }

    public void setExamRoomName(String examRoomName) {
        this.examRoomName = examRoomName;
    }

    public Integer getStuNum() {
        return stuNum;
    }

    public void setStuNum(Integer stuNum) {
        this.stuNum = stuNum;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getExamStatus() {
        return examStatus;
    }

    public void setExamStatus(String examStatus) {
        this.examStatus = examStatus;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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
}