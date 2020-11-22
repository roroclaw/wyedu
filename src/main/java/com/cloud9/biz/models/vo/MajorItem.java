package com.cloud9.biz.models.vo;

/**
 * Created by roroclaw on 2017/11/18.
 * 专业下拉对象
 */
public class MajorItem {
    private String code;

    private String text;

    private String orgText;

    private String seq;

    private String pname;

    public String getOrgText() {
        return orgText;
    }

    public void setOrgText(String orgText) {
        this.orgText = orgText;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.orgText = text;
        this.text = text;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
        if(pname != null && !"".equals(pname)){
            this.text = this.text+"("+pname+")";
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
