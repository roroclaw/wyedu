package com.cloud9.biz.models.vo;

/**
 * Created by roroclaw on 2017/8/14.
 */
public class AutoInputSuggestion {
    private String value;
    private String data;
    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
