package com.cloud9.biz.models;

public class TchTeachingPlan {
    private String id;

    private String name;

    private String majorId;

    private String enrolYear;

    private String eduType;

    private String status;

    private String period;

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

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getEnrolYear() {
        return enrolYear;
    }

    public void setEnrolYear(String enrolYear) {
        this.enrolYear = enrolYear;
    }

    public String getEduType() {
        return eduType;
    }

    public void setEduType(String eduType) {
        this.eduType = eduType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getPeriodText() {
        return periodText;
    }

    public void setPeriodText(String periodText) {
        this.periodText = periodText;
    }

    public String getEduTypeText() {
        return eduTypeText;
    }

    public void setEduTypeText(String eduTypeText) {
        this.eduTypeText = eduTypeText;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    private String statusText;

    private String periodText;

    private String eduTypeText;

    private String majorText;

    public String getMajorText() {
        return majorText;
    }

    public void setMajorText(String majorText) {
        this.majorText = majorText;
    }
}