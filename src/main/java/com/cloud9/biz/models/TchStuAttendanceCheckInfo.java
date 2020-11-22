package com.cloud9.biz.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TchStuAttendanceCheckInfo {
    private String id;

    private String docId;

    private String stuNumber;

    private String userId;

    private String realName;

    private String imgUrl;

    private String sex;

    private String usedName;

    private String identityCard;

    private String classId;

    private String type;

    private String grade;

    @DateTimeFormat(pattern="yyyy-MM")
    private Date enrolDate;

    private String phone;

    private String majorId;

    private String parentsName;

    private String parentsPhone;

    private String faName;

    private String faPhone;

    private String faWork;

    private String maName;

    private String maPhone;

    private String maWork;

    private String status;

    private String statusText;

    private String typeText;

    private String sexText;

    private String majorText;

    private String classText;

    private String classSeq;

    private String period;

    private String assessmentCode;

    private String stuId;

    private String graduateYear;

    private Integer total;

    private String term;

    private String schoolYear;

    private String termText;

    private String assessmentCodeText;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUsedName() {
        return usedName;
    }

    public void setUsedName(String usedName) {
        this.usedName = usedName;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Date getEnrolDate() {
        return enrolDate;
    }

    public void setEnrolDate(Date enrolDate) {
        this.enrolDate = enrolDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getParentsName() {
        return parentsName;
    }

    public void setParentsName(String parentsName) {
        this.parentsName = parentsName;
    }

    public String getParentsPhone() {
        return parentsPhone;
    }

    public void setParentsPhone(String parentsPhone) {
        this.parentsPhone = parentsPhone;
    }

    public String getFaName() {
        return faName;
    }

    public void setFaName(String faName) {
        this.faName = faName;
    }

    public String getFaPhone() {
        return faPhone;
    }

    public void setFaPhone(String faPhone) {
        this.faPhone = faPhone;
    }

    public String getFaWork() {
        return faWork;
    }

    public void setFaWork(String faWork) {
        this.faWork = faWork;
    }

    public String getMaName() {
        return maName;
    }

    public void setMaName(String maName) {
        this.maName = maName;
    }

    public String getMaPhone() {
        return maPhone;
    }

    public void setMaPhone(String maPhone) {
        this.maPhone = maPhone;
    }

    public String getMaWork() {
        return maWork;
    }

    public void setMaWork(String maWork) {
        this.maWork = maWork;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getTypeText() {
        return typeText;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }

    public String getSexText() {
        return sexText;
    }

    public void setSexText(String sexText) {
        this.sexText = sexText;
    }

    public String getMajorText() {
        return majorText;
    }

    public void setMajorText(String majorText) {
        this.majorText = majorText;
    }

    public String getClassText() {
        return classText;
    }

    public void setClassText(String classText) {
        this.classText = classText;
    }

    public String getClassSeq() {
        return classSeq;
    }

    public void setClassSeq(String classSeq) {
        this.classSeq = classSeq;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getAssessmentCode() {
        return assessmentCode;
    }

    public void setAssessmentCode(String assessmentCode) {
        this.assessmentCode = assessmentCode;
    }

    public String getGraduateYear() {
        return graduateYear;
    }

    public void setGraduateYear(String graduateYear) {
        this.graduateYear = graduateYear;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getTermText() {
        return termText;
    }

    public void setTermText(String termText) {
        this.termText = termText;
    }

    public String getAssessmentCodeText() {
        return assessmentCodeText;
    }

    public void setAssessmentCodeText(String assessmentCodeText) {
        this.assessmentCodeText = assessmentCodeText;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }
}