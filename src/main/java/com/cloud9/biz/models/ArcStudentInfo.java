package com.cloud9.biz.models;

import com.roroclaw.base.bean.MemoryCache;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ArcStudentInfo {
    private String id;

    private String docId;

    private String stuNumber;

    private String userId;

    private String realName;

    private String imgUrl;

    private String realImgUrl;

    private String sex;

    private String usedName;

    private String identityCard;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birth;

    private String nation;

    private String politicalStatus;

    private String postCode;

    private String classId;

    private String className;

    private String type;

    private String grade;

    @DateTimeFormat(pattern="yyyy-MM")
    private Date enrolDate;

    private String phone;

    private String majorId;

    private String registryProvinceCode;

    private String registryProvinceText;

    private String registryDistrictCode;

    private String registryDistrictText;

    private String registryCityCode;

    private String registryCityText;

    private String registryType;

    private String nativePlace;

    private String fromHmt;

    private String eduType;

    private String basicAllowances;

    private String transRegistry;

    private String eduGrant;

    private String eduGrantAmount;

    private String bankAccounts;

    private String learnType;

    private String recruitType;

    private String teachPlace;

    private String studentFrom;

    private String studentSort;

    private String officialRegCode;

    private String parentsName;

    private String parentsPhone;

    private String faName;

    private String faPhone;

    private String faWork;

    private String maName;

    private String maPhone;

    private String maWork;

    private Date createTime;

    private String creator;

    private Date updateTime;

    private String updater;

    private String status;

    private String statusText;

    private String typeText;

    private String sexText;

    private String majorText;

    private String gradeText;

    private TchTeachingPlan tchTeachingPlan;

    private String tchPlanId;

    private String tchPlanText;

    private String classText;

    private String eduTypeText;

    private String classSeq;

    private String extType;

    private Date extStarTime;

    private Date extEndTime;

    private String extInfo;

    private String period;

    private String graduateYear;

    public String getTchPlanText() {
        return tchPlanText;
    }

    public void setTchPlanText(String tchPlanText) {
        this.tchPlanText = tchPlanText;
    }

    public String getEduTypeText() {
        return eduTypeText;
    }

    public void setEduTypeText(String eduTypeText) {
        this.eduTypeText = eduTypeText;
    }

    public String getGradeText() {
        return gradeText;
    }

    public void setGradeText(String gradeText) {
        this.gradeText = gradeText;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getGraduateYear() {
        return graduateYear;
    }

    public void setGraduateYear(String graduateYear) {
        this.graduateYear = graduateYear;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    public String getExtType() {
        return extType;
    }

    public void setExtType(String extType) {
        this.extType = extType;
    }

    public Date getExtStarTime() {
        return extStarTime;
    }

    public void setExtStarTime(Date extStarTime) {
        this.extStarTime = extStarTime;
    }

    public Date getExtEndTime() {
        return extEndTime;
    }

    public void setExtEndTime(Date extEndTime) {
        this.extEndTime = extEndTime;
    }

    public String getTchPlanId() {
        return tchPlanId;
    }

    public void setTchPlanId(String tchPlanId) {
        this.tchPlanId = tchPlanId;
    }

    public TchTeachingPlan getTchTeachingPlan() {
        return tchTeachingPlan;
    }

    public void setTchTeachingPlan(TchTeachingPlan tchTeachingPlan) {
        this.tchTeachingPlan = tchTeachingPlan;
    }

    public String getRealImgUrl() {
        if(realImgUrl == null || "".equals(realImgUrl)){
            realImgUrl = MemoryCache.getSysConfigKey("fileUpload.accDir")+"/"+this.imgUrl;
        }
        return realImgUrl;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setRealImgUrl(String realImgUrl) {
        this.realImgUrl = realImgUrl;
    }

    public String getMajorText() {
        return majorText;
    }

    public void setMajorText(String majorText) {
        this.majorText = majorText;
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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
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

    public String getRegistryProvinceCode() {
        return registryProvinceCode;
    }

    public void setRegistryProvinceCode(String registryProvinceCode) {
        this.registryProvinceCode = registryProvinceCode;
    }

    public String getRegistryProvinceText() {
        return registryProvinceText;
    }

    public void setRegistryProvinceText(String registryProvinceText) {
        this.registryProvinceText = registryProvinceText;
    }

    public String getRegistryDistrictCode() {
        return registryDistrictCode;
    }

    public void setRegistryDistrictCode(String registryDistrictCode) {
        this.registryDistrictCode = registryDistrictCode;
    }

    public String getRegistryDistrictText() {
        return registryDistrictText;
    }

    public void setRegistryDistrictText(String registryDistrictText) {
        this.registryDistrictText = registryDistrictText;
    }

    public String getRegistryCityCode() {
        return registryCityCode;
    }

    public void setRegistryCityCode(String registryCityCode) {
        this.registryCityCode = registryCityCode;
    }

    public String getRegistryCityText() {
        return registryCityText;
    }

    public void setRegistryCityText(String registryCityText) {
        this.registryCityText = registryCityText;
    }

    public String getRegistryType() {
        return registryType;
    }

    public void setRegistryType(String registryType) {
        this.registryType = registryType;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getFromHmt() {
        return fromHmt;
    }

    public void setFromHmt(String fromHmt) {
        this.fromHmt = fromHmt;
    }

    public String getEduType() {
        return eduType;
    }

    public void setEduType(String eduType) {
        this.eduType = eduType;
    }

    public String getBasicAllowances() {
        return basicAllowances;
    }

    public void setBasicAllowances(String basicAllowances) {
        this.basicAllowances = basicAllowances;
    }

    public String getTransRegistry() {
        return transRegistry;
    }

    public void setTransRegistry(String transRegistry) {
        this.transRegistry = transRegistry;
    }

    public String getEduGrant() {
        return eduGrant;
    }

    public void setEduGrant(String eduGrant) {
        this.eduGrant = eduGrant;
    }

    public String getEduGrantAmount() {
        return eduGrantAmount;
    }

    public void setEduGrantAmount(String eduGrantAmount) {
        this.eduGrantAmount = eduGrantAmount;
    }

    public String getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(String bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public String getLearnType() {
        return learnType;
    }

    public void setLearnType(String learnType) {
        this.learnType = learnType;
    }

    public String getRecruitType() {
        return recruitType;
    }

    public void setRecruitType(String recruitType) {
        this.recruitType = recruitType;
    }

    public String getTeachPlace() {
        return teachPlace;
    }

    public void setTeachPlace(String teachPlace) {
        this.teachPlace = teachPlace;
    }

    public String getStudentFrom() {
        return studentFrom;
    }

    public void setStudentFrom(String studentFrom) {
        this.studentFrom = studentFrom;
    }

    public String getStudentSort() {
        return studentSort;
    }

    public void setStudentSort(String studentSort) {
        this.studentSort = studentSort;
    }

    public String getOfficialRegCode() {
        return officialRegCode;
    }

    public void setOfficialRegCode(String officialRegCode) {
        this.officialRegCode = officialRegCode;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
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
}