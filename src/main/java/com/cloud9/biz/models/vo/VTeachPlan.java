package com.cloud9.biz.models.vo;

/**
 * Created by roroclaw on 2017/11/29.
 */
public class VTeachPlan {

    private String text;

    private String orgText;

    private String code;

    private String year;

    private String periodText;

    public String getPeriodText() {
        return periodText;
    }

    public void setPeriodText(String periodText) {
        this.periodText = periodText;
        if(periodText != null && !"".equals(periodText)){
            this.text = periodText+"-"+this.text+"("+year+")";
        }
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;

    }

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
        this.text = text;
        this.orgText = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
