package com.cloud9.biz.models;

public class ExaStuExamInfo {
    private String id;

    private String examNum;

    private String stuId;

    private String examId;

    private String examPlanId;

    private String subjectId;

    private String examRoomId;

    private Integer seatNum;

    private String seatAb;

    private String status;

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

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

    public String getExamPlanId() {
        return examPlanId;
    }

    public void setExamPlanId(String examPlanId) {
        this.examPlanId = examPlanId;
    }
}