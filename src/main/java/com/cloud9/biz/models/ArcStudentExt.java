package com.cloud9.biz.models;

import java.util.Date;

public class ArcStudentExt {
    private String id;

    private String stuId;

    private String type;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private String ctrator;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCtrator() {
        return ctrator;
    }

    public void setCtrator(String ctrator) {
        this.ctrator = ctrator;
    }
}