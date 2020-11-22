package com.cloud9.biz.models.vo;

import java.util.List;

/**
 * Created by dxz on 2017/8/2.
 * 树节点对象
 */
public class ZTreeNode {

    private String id;

    private String name;

    private boolean isParent = false;

    private boolean checked = false;

    private boolean open = false;

    private String pId;

    private List<ZTreeNode> children;

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public List<ZTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<ZTreeNode> children) {
        this.children = children;
    }

    public void setParent(boolean isParent) {
        this.isParent = isParent;
    }


    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean getOpen() {
        return this.open;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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

    public boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }

}
