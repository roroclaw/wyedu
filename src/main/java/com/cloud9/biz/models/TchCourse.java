package com.cloud9.biz.models;

import java.math.BigDecimal;

public class TchCourse {
    private String id;

    private String code;

    private String subjectId;

    private String name;

    private String nameEn;

    private String teacherId;

    private BigDecimal courseTimes;

    private String type;

    private String teachType;

    private String classroomType;

    private String seq;

    private String status;

    private String periodText;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public BigDecimal getCourseTimes() {
        return courseTimes;
    }

    public void setCourseTimes(BigDecimal courseTimes) {
        this.courseTimes = courseTimes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTeachType() {
        return teachType;
    }

    public void setTeachType(String teachType) {
        this.teachType = teachType;
    }

    public String getClassroomType() {
        return classroomType;
    }

    public void setClassroomType(String classroomType) {
        this.classroomType = classroomType;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    //////////////////////////自定义
    private String subjectName;
    private String teacherName;
    private String teachTypeText;
    private String classroomTypeText;
    private String statusText;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeachTypeText() {
        return teachTypeText;
    }

    public void setTeachTypeText(String teachTypeText) {
        this.teachTypeText = teachTypeText;
    }

    public String getClassroomTypeText() {
        return classroomTypeText;
    }

    public void setClassroomTypeText(String classroomTypeText) {
        this.classroomTypeText = classroomTypeText;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getPeriodText() {
        return periodText;
    }

    public void setPeriodText(String periodText) {
        this.periodText = periodText;
    }
}