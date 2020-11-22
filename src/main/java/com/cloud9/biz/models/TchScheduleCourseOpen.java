package com.cloud9.biz.models;

public class TchScheduleCourseOpen {
    private String id;

    private String scheduleId;

    private String courseOpenId;

    private Integer periodSeq;

    private Integer weekSeq;

    private String classroomId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getCourseOpenId() {
        return courseOpenId;
    }

    public void setCourseOpenId(String courseOpenId) {
        this.courseOpenId = courseOpenId;
    }

    public Integer getPeriodSeq() {
        return periodSeq;
    }

    public void setPeriodSeq(Integer periodSeq) {
        this.periodSeq = periodSeq;
    }

    public Integer getWeekSeq() {
        return weekSeq;
    }

    public void setWeekSeq(Integer weekSeq) {
        this.weekSeq = weekSeq;
    }

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    private String courseName;

    private String className;

    private String teacherId;

    private String stuId;

    private String teacherName;

    private String classroomText;

    private String period;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClassroomText() {
        return classroomText;
    }

    public void setClassroomText(String classroomText) {
        this.classroomText = classroomText;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}