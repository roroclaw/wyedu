package com.cloud9.biz.models;

public class TchScoresConfig {
    private String id;

    private String courseOpenId;

    private String teacherId;

    private String scoresType;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseOpenId() {
        return courseOpenId;
    }

    public void setCourseOpenId(String courseOpenId) {
        this.courseOpenId = courseOpenId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getScoresType() {
        return scoresType;
    }

    public void setScoresType(String scoresType) {
        this.scoresType = scoresType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}