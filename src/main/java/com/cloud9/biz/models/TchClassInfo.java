package com.cloud9.biz.models;

import com.mchange.v2.codegen.bean.ClassInfo;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TchClassInfo {
    private String id;

    private String name;

    private String period;

    private String grade;

    private Integer seq;

    private String teacherId;

    private String teacherText;

    private Integer number;

    private String classroomId;

    @DateTimeFormat(pattern="yyyy")
    private Date enrolYear;

    private String graduateYear;

    private Date createTime;

    private String creator;

    private Date updateTime;

    private String updater;

    private String status;

    private String statusText;

    private String periodText;

    private String roomText;

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getRoomText() {
        return roomText;
    }

    public void setRoomText(String roomText) {
        this.roomText = roomText;
    }

    public String getPeriodText() {
        return periodText;
    }

    public void setPeriodText(String periodText) {
        this.periodText = periodText;
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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public Date getEnrolYear() {
        return enrolYear;
    }

    public void setEnrolYear(Date enrolYear) {
        this.enrolYear = enrolYear;
    }

    public String getGraduateYear() {
        return graduateYear;
    }

    public void setGraduateYear(String graduateYear) {
        this.graduateYear = graduateYear;
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

    public String getTeacherText() {
        return teacherText;
    }

    public void setTeacherText(String teacherText) {
        this.teacherText = teacherText;
    }

}