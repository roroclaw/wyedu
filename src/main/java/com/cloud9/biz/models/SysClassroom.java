package com.cloud9.biz.models;

import java.math.BigDecimal;

public class SysClassroom {
    private String id;

    private String name;

    private String type;

    private BigDecimal number;

    private String buildingNo;

    private Integer floor;

    private Integer siteColNum;

    private Integer siteRowNum;

    private String siteStr;

    private String status;

    private String address;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getSiteColNum() {
        return siteColNum;
    }

    public void setSiteColNum(Integer siteColNum) {
        this.siteColNum = siteColNum;
    }

    public Integer getSiteRowNum() {
        return siteRowNum;
    }

    public void setSiteRowNum(Integer siteRowNum) {
        this.siteRowNum = siteRowNum;
    }

    public String getSiteStr() {
        return siteStr;
    }

    public void setSiteStr(String siteStr) {
        this.siteStr = siteStr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String statusText;

    private String classroomTypeText;

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getClassroomTypeText() {
        return classroomTypeText;
    }

    public void setClassroomTypeText(String classroomTypeText) {
        this.classroomTypeText = classroomTypeText;
    }

    private String buildingNoText;

    public String getBuildingNoText() {
        return buildingNoText;
    }

    public void setBuildingNoText(String buildingNoText) {
        this.buildingNoText = buildingNoText;
    }
}