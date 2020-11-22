package com.cloud9.biz.models;

public class SysRole {
    private String id;

    private String roleName;

    private String type;

    private boolean isOwn;

    private String typeText;

    public String getTypeText() {
        return typeText;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }

    public void setOwn(boolean isOwn) {
        this.isOwn = isOwn;
    }

    public boolean getIsOwn() {
        return isOwn;
    }

    public void setIsOwn(boolean isOwn) {
        this.isOwn = isOwn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}