package com.cloud9.biz.models.vo;

import com.cloud9.biz.models.ScoClassScoreStatic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roroclaw on 2018/1/17.
 */
public class ClassScoreStaticVo {

    private String classId;

    private String className;

    private double totalAvg = 0d;

    List<ScoClassScoreStatic> scoClassScoreStaticList ;

    public double getTotalAvg() {
        return totalAvg;
    }

    public void setTotalAvg(double totalAvg) {
        this.totalAvg = totalAvg;
    }

    public List<ScoClassScoreStatic> getScoClassScoreStaticList() {
        return scoClassScoreStaticList;
    }

    public void setScoClassScoreStaticList(List<ScoClassScoreStatic> scoClassScoreStaticList) {
        this.scoClassScoreStaticList = scoClassScoreStaticList;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void addScoreStatic(ScoClassScoreStatic scoClassScoreStatic) {
        if(this.scoClassScoreStaticList == null){
            this.scoClassScoreStaticList = new ArrayList<ScoClassScoreStatic>();
        }
        this.scoClassScoreStaticList.add(scoClassScoreStatic);
        totalAvg += scoClassScoreStatic.getAvgScore().doubleValue();
    }
}
