package com.cloud9.biz.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SysImportLog {
    private Integer id;

    private String type;

    private String srcFile;

    private String file;

    private String creator;

    private String creatorText;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;

    private String comment;

    public String getCreatorText() {
        return creatorText;
    }

    public void setCreatorText(String creatorText) {
        this.creatorText = creatorText;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSrcFile() {
        return srcFile;
    }

    public void setSrcFile(String srcFile) {
        this.srcFile = srcFile;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}