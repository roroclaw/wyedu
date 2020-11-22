package com.cloud9.biz.models.vo;

/**
 * Created by roroclaw on 2017/12/10.
 * 分数查询实体类
 */
public class VScoreQuery {

    private String subjectName;

    private Long middleScore;

    private Long endScore;

    private Long usualScore;

    private String middleScoreText;

    private String endScoreText;

    private String usualScoreText;

    private String totalScore;

    private String schoolYear;

    private String termText;

    private String scoresStatus;

    private String subjectCode;

    private String remark;

    public String getScoresStatus() {
        return scoresStatus;
    }

    public void setScoresStatus(String scoresStatus) {
        this.scoresStatus = scoresStatus;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getTermText() {
        return termText;
    }

    public void setTermText(String termText) {
        this.termText = termText;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Long getMiddleScore() {
        return middleScore;
    }

    public void setMiddleScore(Long middleScore) {
        this.middleScore = middleScore;
    }

    public Long getEndScore() {
        return endScore;
    }

    public void setEndScore(Long endScore) {
        this.endScore = endScore;
    }

    public Long getUsualScore() {
        return usualScore;
    }

    public void setUsualScore(Long usualScore) {
        this.usualScore = usualScore;
    }

    public String getMiddleScoreText() {
        return middleScoreText;
    }

    public void setMiddleScoreText(String middleScoreText) {
        this.middleScoreText = middleScoreText;
    }

    public String getEndScoreText() {
        return endScoreText;
    }

    public void setEndScoreText(String endScoreText) {
        this.endScoreText = endScoreText;
    }

    public String getUsualScoreText() {
        return usualScoreText;
    }

    public void setUsualScoreText(String usualScoreText) {
        this.usualScoreText = usualScoreText;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
