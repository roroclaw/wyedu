package com.cloud9.biz.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SysTeacherInfo {
    private String id;

    private String userId;

    private String code;

    private String name;

    private String sex;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birth;

    private Integer age;

    private String picUrl;

    private String namePy;

    private String phone;

    private String identityCard;

    private String colleg;

    private String studyMajor;

    private String teachMajorId;

    private String proTitle;

    private String post;

    private String departId;

    private String nativePlace;

    private String type;

    private String specialty;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date inPartyDate;

    private String politicalStatus;

    private String nation;

    private String bachSchool;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date bachDate;

    private String bachMajor;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date masterDate;

    private String masterSchool;

    private String masterMajor;

    private String doctorSchool;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date doctorDate;

    private String doctorMajor;

    private String degree;

    private String education;

    private Integer beTeacherYears;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date beTeacherDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date inworkDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date inschoolDate;

    private String mainCourse;

    private String doubleDuty;

    private String spouseName;

    private String spousePhone;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getNamePy() {
        return namePy;
    }

    public void setNamePy(String namePy) {
        this.namePy = namePy;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getColleg() {
        return colleg;
    }

    public void setColleg(String colleg) {
        this.colleg = colleg;
    }

    public String getStudyMajor() {
        return studyMajor;
    }

    public void setStudyMajor(String studyMajor) {
        this.studyMajor = studyMajor;
    }

    public String getTeachMajorId() {
        return teachMajorId;
    }

    public void setTeachMajorId(String teachMajorId) {
        this.teachMajorId = teachMajorId;
    }

    public String getProTitle() {
        return proTitle;
    }

    public void setProTitle(String proTitle) {
        this.proTitle = proTitle;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Date getInPartyDate() {
        return inPartyDate;
    }

    public void setInPartyDate(Date inPartyDate) {
        this.inPartyDate = inPartyDate;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBachSchool() {
        return bachSchool;
    }

    public void setBachSchool(String bachSchool) {
        this.bachSchool = bachSchool;
    }

    public Date getBachDate() {
        return bachDate;
    }

    public void setBachDate(Date bachDate) {
        this.bachDate = bachDate;
    }

    public String getBachMajor() {
        return bachMajor;
    }

    public void setBachMajor(String bachMajor) {
        this.bachMajor = bachMajor;
    }

    public Date getMasterDate() {
        return masterDate;
    }

    public void setMasterDate(Date masterDate) {
        this.masterDate = masterDate;
    }

    public String getMasterSchool() {
        return masterSchool;
    }

    public void setMasterSchool(String masterSchool) {
        this.masterSchool = masterSchool;
    }

    public String getMasterMajor() {
        return masterMajor;
    }

    public void setMasterMajor(String masterMajor) {
        this.masterMajor = masterMajor;
    }

    public String getDoctorSchool() {
        return doctorSchool;
    }

    public void setDoctorSchool(String doctorSchool) {
        this.doctorSchool = doctorSchool;
    }

    public Date getDoctorDate() {
        return doctorDate;
    }

    public void setDoctorDate(Date doctorDate) {
        this.doctorDate = doctorDate;
    }

    public String getDoctorMajor() {
        return doctorMajor;
    }

    public void setDoctorMajor(String doctorMajor) {
        this.doctorMajor = doctorMajor;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Integer getBeTeacherYears() {
        return beTeacherYears;
    }

    public void setBeTeacherYears(Integer beTeacherYears) {
        this.beTeacherYears = beTeacherYears;
    }

    public Date getBeTeacherDate() {
        return beTeacherDate;
    }

    public void setBeTeacherDate(Date beTeacherDate) {
        this.beTeacherDate = beTeacherDate;
    }

    public Date getInworkDate() {
        return inworkDate;
    }

    public void setInworkDate(Date inworkDate) {
        this.inworkDate = inworkDate;
    }

    public Date getInschoolDate() {
        return inschoolDate;
    }

    public void setInschoolDate(Date inschoolDate) {
        this.inschoolDate = inschoolDate;
    }

    public String getMainCourse() {
        return mainCourse;
    }

    public void setMainCourse(String mainCourse) {
        this.mainCourse = mainCourse;
    }

    public String getDoubleDuty() {
        return doubleDuty;
    }

    public void setDoubleDuty(String doubleDuty) {
        this.doubleDuty = doubleDuty;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public String getSpousePhone() {
        return spousePhone;
    }

    public void setSpousePhone(String spousePhone) {
        this.spousePhone = spousePhone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfoStatusText() {
        return statusText;
    }

    public void setInfoStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getSexText() {
        return sexText;
    }

    public void setSexText(String sexText) {
        this.sexText = sexText;
    }

    public String getTypeText() {
        return typeText;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getStudyMajorText() {
        return studyMajorText;
    }

    public void setStudyMajorText(String studyMajorText) {
        this.studyMajorText = studyMajorText;
    }

    private String statusText;

    private String departName;

    private String sexText;

    private String typeText;

    private String studyMajorText;
}