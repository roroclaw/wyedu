package com.cloud9.biz.models;

import java.math.BigDecimal;
import java.util.Date;

public class ScoSubjectScores {

    private String id;

    private String subjectId;

    private String stuId;

    private BigDecimal score;

    private String classId;

    private String gradeId;

    private String className;

    private Date createTime;

    private String creator;

    private Date updateTime;

    private String updater;

    private String term;

    private String schoolYear;

    private Integer flag;

    private String remark = "";

    private String stuNumber;

    private String stuName;

    private String subjectName;

    private String status;

    private String statusText;

    private boolean isHasMidScore = false;

    private boolean isHasEndScore = false;

    private boolean isHasUsualScore = false;

    private String termText;

    private String grade;

    private String stuNo;

    private String majorText;

    private String subjectText;

    private String subjectTextEn;

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getTermText() {
        return termText;
    }

    public void setTermText(String termText) {
        this.termText = termText;
    }

    public void addScore(BigDecimal newScore){
        if(this.score == null){
            this.score = new BigDecimal(0);
        }
        this.score = this.score.add(newScore);
    }

    public void addRemark(String remarker){
        this.remark += "|"+remarker;
    }

    public boolean isHasMidScore() {
        return isHasMidScore;
    }

    public void setHasMidScore(boolean isHasMidScore) {
        this.isHasMidScore = isHasMidScore;
    }

    public boolean isHasEndScore() {
        return isHasEndScore;
    }

    public void setHasEndScore(boolean isHasEndScore) {
        this.isHasEndScore = isHasEndScore;
    }

    public boolean isHasUsualScore() {
        return isHasUsualScore;
    }

    public void setHasUsualScore(boolean isHasUsualScore) {
        this.isHasUsualScore = isHasUsualScore;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setTerm(Integer term) {
        this.term = term.toString();
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
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

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public BigDecimal getScore() {
        return this.score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void intScore() {
        this.score = this.score.divide(new BigDecimal(1),0,BigDecimal.ROUND_HALF_UP);
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getMajorText() {
        return majorText;
    }

    public void setMajorText(String majorText) {
        this.majorText = majorText;
    }

    public String getSubjectText() {
        return subjectText;
    }

    public void setSubjectText(String subjectText) {
        this.subjectText = subjectText;
    }

    public String getSubjectTextEn() {
        return subjectTextEn;
    }

    public void setSubjectTextEn(String subjectTextEn) {
        this.subjectTextEn = subjectTextEn;
    }
}