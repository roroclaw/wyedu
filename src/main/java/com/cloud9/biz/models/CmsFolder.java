package com.cloud9.biz.models;

public class CmsFolder {

    private String folder_id;

    private String folder_name;

    private String status;
    private String father_id;
    private String father_name;
    private String author;

    private String path;
    private String folder_level;
    private String folder_type;
    private String folder_sequence;
    private String statusText;
    private String locked;

    public String getFolder_id() {
        return folder_id;
    }

    public String getFolder_name() {
        return folder_name;
    }

    public void setFolder_name(String folder_name) {
        this.folder_name = folder_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFather_id() {
        return father_id;
    }

    public void setFather_id(String father_id) {
        this.father_id = father_id;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFolder_level() {
        return folder_level;
    }

    public void setFolder_level(String folder_level) {
        this.folder_level = folder_level;
    }

    public String getFolder_type() {
        return folder_type;
    }

    public void setFolder_type(String folder_type) {
        this.folder_type = folder_type;
    }

    public String getFolder_sequence() {
        return folder_sequence;
    }

    public void setFolder_sequence(String folder_sequence) {
        this.folder_sequence = folder_sequence;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setFolder_id(String folder_id) {
        this.folder_id = folder_id;

    }


}