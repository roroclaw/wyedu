package com.cloud9.biz.models;

public class TchCourseWithBLOBs extends TchCourse {
    private String introduction;

    private String remark;

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}