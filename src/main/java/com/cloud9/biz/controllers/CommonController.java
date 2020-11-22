package com.cloud9.biz.controllers;

import com.cloud9.biz.models.*;
import com.cloud9.biz.models.vo.MajorItem;
import com.cloud9.biz.models.vo.VFileObj;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.services.CommonService;
import com.cloud9.biz.services.SysTeacherService;
import com.cloud9.biz.services.TchClassService;
import com.cloud9.biz.services.TchCourseService;
import com.cloud9.biz.util.BizConstants;
import com.cloud9.biz.util.EduKit;
import com.roroclaw.base.bean.MemoryCache;
import com.roroclaw.base.controller.BaseController;
import com.roroclaw.base.handler.BizException;
import com.roroclaw.base.utils.FileKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by roroclaw on 2017/1/26.
 */
@Controller
@RequestMapping(value = "/common")
public class CommonController extends BaseController {

    protected static Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private CommonService commonService;

    @Autowired
    private SysTeacherService sysTeacherService;

    @Autowired
    private TchCourseService courseService;

    @Autowired
    private TchClassService tchClassService;

    @RequestMapping(value = "/doGetClassInfoStatus.infc")
    @ResponseBody
    public Object doGetClassInfoStatus(String keyText) throws BizException {
        List resList = null;
        resList = this.commonService.getItemsByType(
                BizConstants.DICT_TYPE.CLASS_STATUS, keyText);
        return resList;
    }

    @RequestMapping(value = "/doGetUserStatus.infc")
    @ResponseBody
    public Object doGetUserStatus(String keyText) throws BizException {
        List resList = null;
        resList = this.commonService.getItemsByType(
                BizConstants.DICT_TYPE.COM_STATUS, keyText);
        return resList;
    }


    @RequestMapping(value = "/doGetUserInfoStatus.infc")
    @ResponseBody
    public Object doGetUserInfoStatus(String keyText) throws BizException {
        List resList = null;
        resList = this.commonService.getItemsByType(
                BizConstants.DICT_TYPE.INFO_STATUS, keyText);
        return resList;
    }

    @RequestMapping(value = "/doGetUserTypes.infc")
    @ResponseBody
    public Object doGetUserTypes(String keyText) throws BizException {
        List resList = null;
        resList = this.commonService.getItemsByType(
                BizConstants.DICT_TYPE.USER_TYPE, keyText);
        return resList;
    }

    @RequestMapping(value = "/getSexItems.infc")
    @ResponseBody
    public Object doGetSexItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getItemsByType(BizConstants.DICT_TYPE.USER_SEX, "");
        return resList;
    }

    @RequestMapping(value = "/getGradeItems.infc")
    @ResponseBody
    public Object doGetGradeItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getAllGradeItems();
        return resList;
    }

    @RequestMapping(value = "/getGradeItemsByPeriod.infc")
    @ResponseBody
    public Object getGradeItemsByPeriod(String period) throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getGradeItemsByPeriod(period);
        return resList;
    }


    @RequestMapping(value = "/getNationItems.infc")
    @ResponseBody
    public Object doGetNationItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getItemsByType(BizConstants.DICT_TYPE.MINZU, "");
        return resList;
    }

    @RequestMapping(value = "/getEduTypeItems.infc")
    @ResponseBody
    public Object doGetEduTypeItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getItemsByType(BizConstants.DICT_TYPE.EDU_TYPE, "");
        return resList;
    }

    /**
     * 获取课程下拉
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/getCourseItems.infc")
    @ResponseBody
    public Object getCourseItems(TchCourse courseInfo) throws BizException {
        List<SysDictItem> resList =  new ArrayList<SysDictItem>();
        List<TchCourse> courseInfosList = this.courseService.getCoursesList(courseInfo);
        for(int i=0;i<courseInfosList.size();i++){
            TchCourse tempTchCourse=courseInfosList.get(i);
            SysDictItem tempSysDictItem=new SysDictItem();
            tempSysDictItem.setCode(tempTchCourse.getId());
            tempSysDictItem.setText(tempTchCourse.getName()+"|"+tempTchCourse.getTeacherName());
           // System.out.println("-----------getCourseItems||"+tempTchCourse.getId()+"||"+tempTchCourse.getName()+"||"+tempSysDictItem.getCode()+"|"+tempTchCourse.getTeacherName());
            resList.add(tempSysDictItem);
        }
        return resList;
    }

    /**
     * 获取专业下拉
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/getMajorItems.infc")
    @ResponseBody
    public Object doGetMajorItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getAllMajorItems();
        return resList;
    }

    /**
     * 获取方向下拉数据
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/getSubMajorItems.infc")
    @ResponseBody
    public Object doGetSubMajorItems() throws BizException {
        List<MajorItem> resList = null;
        resList = this.commonService.getAllSubMajorItems();
        return resList;
    }

    @RequestMapping(value = "/getPoliticalStatusItems.infc")
    @ResponseBody
    public Object doGetPoliticalStatusItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getItemsByType(BizConstants.DICT_TYPE.POLITICAL_STATUS, "");
        return resList;
    }

    @RequestMapping(value = "/getStudentSortItems.infc")
    @ResponseBody
    public Object doGetStudentSortItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getItemsByType(BizConstants.DICT_TYPE.STU_SORT, "");
        return resList;
    }

    @RequestMapping(value = "/getStudentTypeItems.infc")
    @ResponseBody
    public Object doGetStudentTypeItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getItemsByType(BizConstants.DICT_TYPE.STU_TYPE, "");
        return resList;
    }

    @RequestMapping(value = "/getLearnTypeItems.infc")
    @ResponseBody
    public Object doGetLearnTypeItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getItemsByType(BizConstants.DICT_TYPE.LEARN_TYPE, "");
        return resList;
    }

    @RequestMapping(value = "/getRecruitTypeItems.infc")
    @ResponseBody
    public Object doGetRecruitTypeItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getItemsByType(BizConstants.DICT_TYPE.RECRUIT_TYPE, "");
        return resList;
    }

    @RequestMapping(value = "/getTeachPlaceItems.infc")
    @ResponseBody
    public Object doGetTeachPlaceItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getItemsByType(BizConstants.DICT_TYPE.TEACH_PLACE, "");
        return resList;
    }

    @RequestMapping(value = "/getStudentFromItems.infc")
    @ResponseBody
    public Object doGetStudentFromItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getItemsByType(BizConstants.DICT_TYPE.STUDENT_FROM, "");
        return resList;
    }

    @RequestMapping(value = "/getProvinceItems.infc")
    @ResponseBody
    public Object doGetProvinceItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getAreaByType(BizConstants.AREA_TYPE.PROVINCE);
        return resList;
    }

    @RequestMapping(value = "/getAreaItemsByPid.infc")
    @ResponseBody
    public Object doGetAreaItemsByPid(String pid) throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getAreaItemsByPid(pid);
        return resList;
    }

    @RequestMapping(value = "/getUserTypeItems.infc")
    @ResponseBody
    public Object doGetUserTypeItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getItemsByType(BizConstants.DICT_TYPE.USER_TYPE, "");
        return resList;
    }

    @RequestMapping(value = "/getRegistryTypeItems.infc")
    @ResponseBody
    public Object doGetRegistryTypeItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getItemsByType(BizConstants.DICT_TYPE.REGISTRY_TYPE, "");
        return resList;
    }


    ///////////by zl
    @RequestMapping(value = "/doGetTeacherTypeItems.infc")
    @ResponseBody
    public Object doGetTeacherTypeItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getItemsByType(BizConstants.DICT_TYPE.TEACHER_TYPE, "");
        return resList;
    }

    @RequestMapping(value = "/doGetTeachTypeItems.infc")
    @ResponseBody
    public Object doGetTeachTypeItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getItemsByType(BizConstants.DICT_TYPE.TEACH_TYPE, "");
        return resList;
    }

    @RequestMapping(value = "/doGetClassroomTypeItems.infc")
    @ResponseBody
    public Object doGetClassroomTypeItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getItemsByType(BizConstants.DICT_TYPE.CLASSROOM_TYPE, "");
        return resList;
    }

    @RequestMapping(value = "/doGetTeacherTitleItems.infc")
    @ResponseBody
    public Object doGetTeacherTitleItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getItemsByType(BizConstants.DICT_TYPE.TEACHER_TITLE, "");
        return resList;
    }

    @RequestMapping(value = "/doGetTeacherPostItems.infc")
    @ResponseBody
    public Object doGetTeacherPostItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getItemsByType(BizConstants.DICT_TYPE.TEACHER_POST, "");
        return resList;
    }

    @RequestMapping(value = "/doGetEducationItems.infc")
    @ResponseBody
    public Object doGetEducationItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getItemsByType(BizConstants.DICT_TYPE.TEACHER_EDUCATION, "");
        return resList;
    }

    @RequestMapping(value = "/doGetDegreeItems.infc")
    @ResponseBody
    public Object doGetDegreeItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getItemsByType(BizConstants.DICT_TYPE.TEACHER_DEGREE, "");
        return resList;
    }

    @RequestMapping(value = "/doGetPeriodItems.infc")
    @ResponseBody
    public Object doGetPeriodItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getItemsByType(BizConstants.DICT_TYPE.PERIOD, "");
        return resList;
    }

    @RequestMapping(value = "/doGetSubjectTypeItems.infc")
    @ResponseBody
    public Object doGetSubjectTypeItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getItemsByType(BizConstants.DICT_TYPE.SUBJECT_TYPE, "");
        return resList;
    }

    @RequestMapping(value = "/doGetTermItems.infc")
    @ResponseBody
    public Object doGetTermItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getItemsByType(BizConstants.DICT_TYPE.TERM, "");
        return resList;
    }

    @RequestMapping(value = "/getbuildingNoItems.infc")
    @ResponseBody
    public Object getbuildingNoItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getItemsByType(BizConstants.DICT_TYPE.BUILDING_NO, "");
        return resList;
    }



    @RequestMapping(value = "/doGetRecentSchoolYearItems.infc")
    @ResponseBody
    public Object doGetRecentSchoolYearItems() throws BizException {
        Date d = new Date();
//        System.out.println(d);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String yearNowStr = sdf.format(d);
        int yearNow = Integer.parseInt(yearNowStr);
        ArrayList<String> temp=new ArrayList<String>();
        temp.add(String.valueOf(yearNow-1));
        temp.add(String.valueOf(yearNow));
        temp.add(String.valueOf(yearNow+1));
        return temp;
    }

    @RequestMapping(value = "/doGetExamType.infc")
    @ResponseBody
    public Object doGetExamType(String keyText) throws BizException {
        List resList = null;
        resList = this.commonService.getItemsByType(
                BizConstants.DICT_TYPE.EXAM_TYPE, keyText);
        return resList;
    }

    @RequestMapping(value = "/doGetExamStatus.infc")
    @ResponseBody
    public Object doGetExamStatus(String keyText) throws BizException {
        List resList = null;
        resList = this.commonService.getItemsByType(
                BizConstants.DICT_TYPE.EXAM_STATUS, keyText);
        return resList;
    }

    @RequestMapping(value = "/doGetScoresTypeItems.infc")
    @ResponseBody
    public Object doGetScoresTypeItems(String keyText) throws BizException {
        List resList = null;
        resList = this.commonService.getItemsByType(
                BizConstants.DICT_TYPE.SCORES_TYPE, keyText);
        return resList;
    }

    @RequestMapping(value = "/doGetSubjectScoresStatusItems.infc")
    @ResponseBody
    public Object doGetSubjectScoresStatusItems(String keyText) throws BizException {
        List resList = null;
        resList = this.commonService.getItemsByType(
                BizConstants.DICT_TYPE.SCORES_SUBJECT_STATUS, keyText);
        return resList;
    }

    @RequestMapping(value = "/doGetExamStuStatus.infc")
    @ResponseBody
    public Object doGetExamStuStatus(String keyText) throws BizException {
        List resList = null;
        resList = this.commonService.getItemsByType(BizConstants.DICT_TYPE.EXAM_STU_STATUS, keyText);
        return resList;
    }

    @RequestMapping(value = "/doGetExamStuStatusShort.infc")
    @ResponseBody
    public Object doGetExamStuStatusShort(String code) throws BizException {
        List resList = null;
        code = "1,2,5";
        resList= this.commonService.getItemsByTypeAndCode(BizConstants.DICT_TYPE.EXAM_STU_STATUS, code);

        return resList;
    }


    @RequestMapping(value = "/doGetArticleStatus.infc")
    @ResponseBody
    public Object doGetArticleStatus(String dictType) throws BizException {
        List resList = null;
        resList = this.commonService.getItemsByType(
                BizConstants.DICT_TYPE.ARTI_STATUS, dictType);
        return resList;
    }
    /**
     * 导入学籍信息信息
     * @param file
     * @param resp
     * @return
     * @throws BizException
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/uploadImg.infc", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Object uploadImg(MultipartFile file, HttpServletResponse resp)
            throws BizException, IOException {
        VFileObj fileObj = null;

        resp.setHeader("content-type", "text/plain");

        if (file == null) {
            throw new BizException("文件上传失败：文件为空");
        }

        // 得到文件名
        String filename = file.getOriginalFilename();
        String exName = FileKit.getExtensionName(filename);

        if ("".equals(filename)) {
            throw new BizException("上传文件不能为空!");
        }

        if (!checkFileImg(exName)) {
            throw new BizException("文件类型错误!");
        }

        long maxSize = 5000000;
        try {
            String maxSizeStr = MemoryCache.getSysConfigKey("fileUpload.maxSize");
            maxSize = Long.valueOf(maxSizeStr);
        } catch (NumberFormatException e) {
            throw new BizException("上传文件大小错误!");
        }
        if (file.getSize() > maxSize) {
            Long size4m = maxSize/1000000;
            throw new BizException("文件上传失败：文件大小不能超过"+size4m+"M");
        }

        // 保存文件
        if (file.getSize() > 0) {
            //解析excel数据导入学籍信息
            fileObj = commonService.writeFile(file,"stuImgs");
        } else {
            throw new BizException("文件上传失败：上传文件不能为空");
        }

        return fileObj;
    }

    private boolean checkFileImg(String fileType) {
        boolean bol = false;
        String typeStr = "gif,jpg,jpeg,bmp,png,";
        if (typeStr.indexOf(fileType.toLowerCase() + ",") > -1) {
            bol = true;
        }
        return bol;
    }

    /**
     * 获取学年下拉框
     * @return
     */
    @RequestMapping(value = "/getSchoolYearItems.infc")
    @ResponseBody
    public Object getSchoolYearItems(){
        int firstSchoolYear = Integer.valueOf(MemoryCache.getSysConfigKey("schoolYear.first")).intValue();
        List<SysDictItem> schoolYearList = EduKit.genrateStudyYears(firstSchoolYear);
        return schoolYearList;
    }

    /**
     * 获取毕业时间下拉框
     * @return
     */
    @RequestMapping(value = "/getGraduateYearItems.infc")
    @ResponseBody
    public Object getGraduateYearItems(){
        DateFormat df = new SimpleDateFormat("yyyy");
        String firstSchoolYear = df.format(new Date());
        int star = Integer.valueOf(firstSchoolYear);
        List<SysDictItem> schoolYearList = new ArrayList<SysDictItem>();
        for (int i = 0 ; i < 12 ; i++){
            String code = String.valueOf(star+i);
            SysDictItem sysDictItem = new SysDictItem();
            sysDictItem.setCode(code);
            sysDictItem.setText(code);
            schoolYearList.add(sysDictItem);
        }
        return schoolYearList;
    }

    /**
     * 获取当前学年
     * @return
     */
    @RequestMapping(value = "/getCurSchoolYear.infc")
    @ResponseBody
    public Object getCurSchoolYear(){
        return EduKit.getCurStudyYear();
    }

    /**
     * 获取当前学年(单年份)
     * @return
     */
    @RequestMapping(value = "/getCurSchoolYearSig.infc")
    @ResponseBody
    public Object getCurSchoolYearSig(){
        return EduKit.getCurStarYearByCurTime();
    }

    /**
     * 获取当前学年
     * @return
     */
    @RequestMapping(value = "/getEduYearItems.infc")
    @ResponseBody
    public Object getEduYearItems(){
        Calendar now = Calendar.getInstance();
//        int curYear = now.get(Calendar.YEAR);
        int maxYear = now.get(Calendar.YEAR)+1;
        int size = 13;
        List<SysDictItem> yearList = new ArrayList<SysDictItem>();
        for(int i=0;i < size;i++){
            SysDictItem sysDictItem = new SysDictItem();
            int year = maxYear - i ;
            String yearStr = String.valueOf(year);
            sysDictItem.setCode(String.valueOf(yearStr));
            sysDictItem.setText(yearStr);
//            if(year == curYear){
//                sysDictItem.setIsCheck(true);
//            }
            yearList.add(sysDictItem);
        }
        return yearList;
    }

    /**
     * 获取当前学年，显示双年份  by zl
     * @return
     */
    @RequestMapping(value = "/getFromToEduYearItems.infc")
    @ResponseBody
    public Object getFromToEduYearItems(){
        Calendar now = Calendar.getInstance();
//        int curYear = now.get(Calendar.YEAR);
        int maxYear = now.get(Calendar.YEAR)+1;
        int size = 13;
        List<SysDictItem> yearList = new ArrayList<SysDictItem>();
        for(int i=0;i < size;i++){
            SysDictItem sysDictItem = new SysDictItem();
            int year = maxYear - i ;
            String yearStr = String.valueOf(year);
            sysDictItem.setCode(String.valueOf(yearStr));
            String yearTextStr = String.valueOf(year)+"---"+String.valueOf(year+1);
            sysDictItem.setText(yearTextStr);
//            if(year == curYear){
//                sysDictItem.setIsCheck(true);
//            }
            yearList.add(sysDictItem);
        }
        return yearList;
    }


    @RequestMapping(value = "/getGradeInfoByCode.infc")
    @ResponseBody
    public Object getGradeInfoByCode(String code){
        SysGrades gradeInfo = this.commonService.getGradeInfoByCode(code);
        return gradeInfo;
    }


    /**
     * 单科成绩查询下拉.
     */
    @RequestMapping(value = "/getCourseInfoItemsForPower.infc")
    @ResponseBody
    public Object getCourseInfoItemsForPower(String classId,String schoolYear,String type,String term,String grade,VUserInfo vUserInfo) throws Exception {
        TchClassInfo classInfo=this.tchClassService.getClassInfoById(classId);
        //String grade=classInfo.getGrade();
        String teacherId="";
        if(!this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.ADMIN) && !vUserInfo.getId().equals(BizConstants.SUPER_ADMIN_ID)
                && !this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.CLASSTEACHER)
                && !this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.XIAOWUHUI)){///不是管理员、超级管理员、班主任、校务会成员
            SysTeacherInfo sysTeacherInfo = this.sysTeacherService.getTeacherInfoByUserId(vUserInfo.getId());
            teacherId=sysTeacherInfo.getId();
        }
        SysGrades gradeInfo = this.commonService.getGradeInfoByCode(grade);
        String period=gradeInfo.getPeriod();
        List<ScoExamScoresForQuery> CourseInfosList = this.commonService.getCourseItemsForPower(grade, schoolYear, type,term, teacherId,period);
       /* for(int i=0;i<ClassesInfosList.size();i++){///////////////////只显示班次
            String tempName=ClassesInfosList.get(i).getText();
            ClassesInfosList.get(i).setText(tempName.substring(tempName.length()-4));
        }*/
        for(int i=0;i<CourseInfosList.size();i++){///////////////////只显示班次
            String tempName=CourseInfosList.get(i).getCourseName()+"("+CourseInfosList.get(i).getTeacherName()+")";
            CourseInfosList.get(i).setCourseName(tempName);
        }
        return CourseInfosList;
    }

    /**
     * 获取有权限的班级下拉.

    @RequestMapping(value = "/getClassInfoItemsForPower.infc")
    @ResponseBody
    public Object getClassInfoItemsForPower(String grade,String schoolYear,VUserInfo vUserInfo) throws Exception {
        String teacherId="";
        if(!this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.ADMIN) && !vUserInfo.getId().equals(BizConstants.SUPER_ADMIN_ID)
                && !this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.CLASSTEACHER)){///不是管理员、超级管理员、班主任
            SysTeacherInfo sysTeacherInfo = this.sysTeacherService.getTeacherInfoByUserId(vUserInfo.getId());
            teacherId=sysTeacherInfo.getId();
        }
        SysGrades gradeInfo = this.commonService.getGradeInfoByCode(grade);
        String period=gradeInfo.getPeriod();
        List<ScoExamScoresForQuery> CourseInfosList = this.commonService.getClassItemsForPower(grade, schoolYear, type,term, teacherId,period);

        return CourseInfosList;
    }*/

    @RequestMapping(value = "/doGetTeachingPlanItems.infc")
    @ResponseBody
    public Object doGetTeachingPlanItems(String majorId) throws BizException {
        List resList = null;
        resList = this.commonService.getTeachingPlanItems(majorId);
        return resList;
    }

    @RequestMapping(value = "/getScopeSelItems.infc")
    @ResponseBody
    public Object getScopeSelItems() throws BizException {
        List resList = null;
        resList = this.commonService.getScopeSelItems();
        return resList;
    }

    @RequestMapping(value = "/getScoreTypeItems.infc")
    @ResponseBody
    public Object getScoreTypeItems() throws BizException {
        List resList = null;
        resList = this.commonService.getScoreTypeItems();
        return resList;
    }

    @RequestMapping(value = "/getRecordConfigStatusItems.infc")
    @ResponseBody
    public Object getRecordConfigStatusItems() throws BizException {
        List resList = null;
        resList = this.commonService.getRecordConfigStatusItems();
        return resList;
    }
    @RequestMapping(value = "/getAttendanceCheckItems.infc")
    @ResponseBody
    public Object getAttendanceCheckItems() throws BizException {
        List<SysDictItem> resList = null;
        resList = this.commonService.getItemsByType(BizConstants.DICT_TYPE.ATTENDANCE_CHECK_ITEMS, "");
        return resList;
    }

}
