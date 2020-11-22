package com.cloud9.biz.models;

public class ScoStuSubjctTotalAvg {
    private Integer id;

    private String classId;

    private String className;

    private String grade;

    private String type;

    private String subjectId;

    private String subjectName;

    private Long totalScore;

    private Long avgScore;

    private Short stuCount;

    private String term;

    private String schoolYear;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Long totalScore) {
        this.totalScore = totalScore;
    }

    public Long getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Long avgScore) {
        this.avgScore = avgScore;
    }

    public Short getStuCount() {
        return stuCount;
    }

    public void setStuCount(Short stuCount) {
        this.stuCount = stuCount;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }
}