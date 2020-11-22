package com.cloud9.biz.models;

import java.math.BigDecimal;
import java.util.List;

public class SysMenus {
    private String id;

    private String name;

    private String url;

    private BigDecimal sort;

    private String type;

    private String status;

    private String parentCode;

    private String imgUrl;

    private String sysTag;

    List<SysMenus> subMenus;

    boolean isCur = false;

    public boolean isCur() {
        return isCur;
    }

    public void setCur(boolean isCur) {
        this.isCur = isCur;
    }

    public List<SysMenus> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<SysMenus> subMenus) {
        this.subMenus = subMenus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public BigDecimal getSort() {
        return sort;
    }

    public void setSort(BigDecimal sort) {
        this.sort = sort;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getSysTag() {
        return sysTag;
    }

    public void setSysTag(String sysTag) {
        this.sysTag = sysTag == null ? null : sysTag.trim();
    }
}