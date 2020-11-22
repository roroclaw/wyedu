package com.cloud9.biz.controllers;

import com.cloud9.biz.dao.mybatis.ArcStudentInfoMapper;
import com.cloud9.biz.dao.mybatis.ArcStudentPlanRelMapper;
import com.cloud9.biz.models.TchStuAttendanceCheckInfo;
import com.cloud9.biz.models.ArcStudentInfo;
import com.cloud9.biz.models.ArcStudentInfoWithBLOBs;
import com.cloud9.biz.models.ArcStudentPlanRel;
import com.cloud9.biz.models.TchTeachingPlan;
import com.cloud9.biz.models.vo.AutoInputSuggestion;
import com.cloud9.biz.models.vo.AutoInputVO;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.services.*;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.annotation.NativeInfc;
import com.roroclaw.base.bean.MemoryCache;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.controller.BaseController;
import com.roroclaw.base.handler.BizException;
import com.roroclaw.base.utils.FileKit;
import com.roroclaw.base.utils.POIExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping(value = "/student")
public class ArcStudentController extends BaseController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private ArcStudentService studentService;

    @Autowired
    private ImpLogService impLogService;

    @Autowired
    private ArcStudentInfoMapper arcStudentInfoMapper;

    @Autowired
    private ArcStudentPlanRelMapper arcStudentPlanRelMapper;

    @Autowired
    private CommonService commonService;

    @Autowired
    private TchTeachingPlanService TchTeachingPlanService;

    @Autowired
    private TchCourseOpenService tchCourseOpenService;

    /**
            * 查询学生信息分页信息
    *
            * @param pageBean
    * @param request
    * @return
            * @throws Exception
    */
    @RequestMapping(value = "/doGetStudentInfoPageData.infc")
    @ResponseBody
    public Object doGetStudentInfoPageData(PageBean pageBean, WebRequest request)
            throws Exception {
        pageBean = this.studentService.getUserInfoPageData(pageBean);
        return pageBean;
    }


    /**
            * 查询学生信息分页信息
    *
            * @param pageBean
    * @param request
    * @return
            * @throws Exception
    */
    @RequestMapping(value = "/doGetStudentChangeInfoPageData.infc")
    @ResponseBody
    public Object doGetStudentChangeInfoPageData(PageBean pageBean, WebRequest request)
            throws Exception {
        pageBean = this.studentService.doGetStudentChangeInfoPageData(pageBean);
        return pageBean;
    }

    @RequestMapping(value = "/doAddStudent.infc")
    @ResponseBody
    public Object doAddStudent(ArcStudentInfoWithBLOBs arcStudentInfo,VUserInfo userInfo) throws Exception {
        boolean bol = true;
        this.studentService.addStudentInfo(arcStudentInfo,userInfo);
        return bol;
    }


    @RequestMapping(value = "/doModStudentInfo.infc")
    @ResponseBody
    public Object doUpdateStudentInfo(VUserInfo userInfo,ArcStudentInfoWithBLOBs arcStudentInfo) throws Exception {
        boolean bol = true;
        String stuId = arcStudentInfo.getId();
        if(stuId == null || "".equals(stuId)){
            throw new BizException("修改目标缺失!");
        }
        arcStudentInfo.setUpdater(userInfo.getId());
        arcStudentInfo.setUpdateTime(new Date());
        if (!this.studentService.modifyStudentInfo(arcStudentInfo,userInfo)) {
            bol = false;
        }
        return bol;
    }

    /**
     * 修改学生信息状态
     */
    @RequestMapping(value = "/doModStudentInfoStatus.infc")
    @ResponseBody
    public Object doModStudentInfoStatus(VUserInfo userInfo,ArcStudentInfo arcStudentInfo) throws Exception {
        boolean bol = true;
        ArcStudentInfo newStudentInfoStatus=new ArcStudentInfo();
        String stuId = arcStudentInfo.getId();
        if(stuId == null || "".equals(stuId)){
            throw new BizException("修改目标缺失!");
        }
        newStudentInfoStatus.setId(stuId);
        newStudentInfoStatus.setUpdater(userInfo.getId());
        newStudentInfoStatus.setUpdateTime(new Date());
        newStudentInfoStatus.setStatus(arcStudentInfo.getStatus());
        if (!this.studentService.modifyStudentInfoStatus(newStudentInfoStatus)) {
            bol = false;
        }
        return bol;
    }

    /**
     * 休学
     */
    @RequestMapping(value = "/doModStudentSuspension.infc")
    @ResponseBody
    public Object doModStudentSuspension(VUserInfo userInfo,ArcStudentInfo arcStudentInfo) throws Exception {
        boolean bol = true;
        ArcStudentInfo newStudentInfoStatus=new ArcStudentInfo();
        String stuId = arcStudentInfo.getId();
        if(stuId == null || "".equals(stuId)){
            throw new BizException("修改目标缺失!");
        }
        newStudentInfoStatus.setId(stuId);
        newStudentInfoStatus.setUpdater(userInfo.getId());
        newStudentInfoStatus.setUpdateTime(new Date());
        newStudentInfoStatus.setStatus(BizConstants.INFO_STATUS.SUSPERNSION);
        if (!this.studentService.modifyStudentInfoStatus(newStudentInfoStatus)) {
            bol = false;
        }
        return bol;
    }

    /**
     * 退学
     */
    @RequestMapping(value = "/doModStudentDrop.infc")
    @ResponseBody
    public Object doModStudentDrop(VUserInfo userInfo,ArcStudentInfo arcStudentInfo) throws Exception {
        boolean bol = true;
        ArcStudentInfo newStudentInfoStatus=new ArcStudentInfo();
        String stuId = arcStudentInfo.getId();
        if(stuId == null || "".equals(stuId)){
            throw new BizException("修改目标缺失!");
        }
        newStudentInfoStatus.setId(stuId);
        newStudentInfoStatus.setUpdater(userInfo.getId());
        newStudentInfoStatus.setUpdateTime(new Date());
        newStudentInfoStatus.setStatus(BizConstants.INFO_STATUS.DROP);
        if (!this.studentService.modifyStudentInfoStatus(newStudentInfoStatus)) {
            bol = false;
        }
        return bol;
    }

    /**
     * 退学信息
     */
    @RequestMapping(value = "/doModStudentDropInfo.infc")
    @ResponseBody
    public Object doModStudentDropInfo(VUserInfo userInfo,String stuId,String starTime) throws Exception {
        boolean bol = true;
        bol = this.studentService.modStudentDropInfo(stuId,starTime,userInfo.getId());
        return bol;
    }

    /**
     * 休学信息
     */
    @RequestMapping(value = "/doModStudentSuspentInfo.infc")
    @ResponseBody
    public Object doModStudentSuspentInfo(VUserInfo userInfo,String stuId,String starTime,String endTime) throws Exception {
        boolean bol = true;
        bol = this.studentService.doModStudentSuspentInfo(stuId,starTime,endTime,userInfo.getId());
        return bol;
    }

    /**
     * 复学
     */
    @RequestMapping(value = "/doModStudentGoBack.infc")
    @ResponseBody
    public Object doModStudentGoBack(VUserInfo userInfo,ArcStudentInfo arcStudentInfo) throws Exception {
        boolean bol = true;
        ArcStudentInfo newStudentInfoStatus=new ArcStudentInfo();
        String stuId = arcStudentInfo.getId();
        if(stuId == null || "".equals(stuId)){
            throw new BizException("修改目标缺失!");
        }
        newStudentInfoStatus.setId(stuId);
        newStudentInfoStatus.setUpdater(userInfo.getId());
        newStudentInfoStatus.setUpdateTime(new Date());
        newStudentInfoStatus.setStatus(BizConstants.INFO_STATUS.CHECKED);
        if (!this.studentService.modifyStudentInfoStatus(newStudentInfoStatus)) {
            bol = false;
        }
        return bol;
    }

    /**
     * 删除用户
     *
     * @return
     */
    @RequestMapping(value = "/doDelStuById.infc")
    @ResponseBody
    public Object doDelStuById(String id) throws BizException {
        boolean bol = this.studentService.doDelStuById(id);
        return bol;
    }



    /**
     * 导入学籍信息信息
     * @param file
     * @param resp
     * @return
     * @throws BizException
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/importStudents.infc", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Object importStudents(MultipartFile file, HttpServletResponse resp,String accToken)
            throws BizException, IOException {
        String importInfo = "";

        VUserInfo vUserInfo = (VUserInfo) userService.perfectUserBean(accToken);

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

        if (!checkFileType(exName)) {
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
            importInfo = this.studentService.importStudents(filename,file,vUserInfo);
        } else {
            throw new BizException("文件上传失败：上传文件不能为空");
        }

        return importInfo;
    }

    /**
     * 检查文件类型
     *
     * @param fileType
     * @return
     */
    private boolean checkFileType(String fileType) {
        boolean bol = false;
        String typeStr = "xls,xlsx,";
        if (typeStr.indexOf(fileType.toLowerCase() + ",") > -1) {
            bol = true;
        }
        return bol;
    }

    @RequestMapping(value = "/initEdit.do")
    public ModelAndView initEdit(String id){
        ModelAndView mv = null;
        //判断是否开课
//        int openCourseNum = this.tchCourseOpenService.getStuOpenCourseNumByStuId(id);
//        if(openCourseNum == 0){//未开课
            mv = new ModelAndView("arc/student/studentModForm");
            //获取学籍信息
            ArcStudentInfoWithBLOBs arcStudentInfo = this.studentService.getStudentInfoById(id);
            //获取教学计划关系信息
            ArcStudentPlanRel arcStudentPlanRel = this.arcStudentPlanRelMapper.selectByStuId(arcStudentInfo.getId());
            if(arcStudentPlanRel != null){
                arcStudentInfo.setTchPlanId(arcStudentPlanRel.getPlanId());
            }
            mv.addObject("stuInfo",arcStudentInfo);
//        }else{//已开课
//            mv = new ModelAndView("arc/student/arcInfo4Tch");
//            ArcStudentInfoWithBLOBs arcStudentInfo = initAllStuInfoByStuId(id);
//            mv.addObject("stuInfo",arcStudentInfo);
//        }
        return mv;
    }

    /**
     * 学员自己修改学籍信息
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/initStuEdit.do")
    public ModelAndView initStuEdit(VUserInfo userInfo){
        ModelAndView mv = new ModelAndView("arc/student/arcInfo4Stu");
        ArcStudentInfoWithBLOBs arcStudentInfo = initAllStuInfoByUserId(userInfo.getId());
        mv.addObject("stuInfo",arcStudentInfo);
        return mv;
    }

    private ArcStudentInfoWithBLOBs initAllStuInfoByUserId(String userId){
        //获取学籍信息
        ArcStudentInfoWithBLOBs arcStudentInfo = this.studentService.getAllStudentInfoByUserId(userId);
        if(arcStudentInfo == null){
            throw new BizException("无学籍信息,联系管理员!");
        }
        //获取教学计划关系信息
        ArcStudentPlanRel arcStudentPlanRel = this.arcStudentPlanRelMapper.selectByStuId(arcStudentInfo.getId());
        if(arcStudentPlanRel != null){
            String planId = arcStudentPlanRel.getPlanId();
            //获取教学计划名称
            TchTeachingPlan tchTeachingPlan = TchTeachingPlanService.getTeachingPlanById(planId);
            arcStudentInfo.setTchPlanText(tchTeachingPlan.getName());
            arcStudentInfo.setTchPlanId(planId);
        }

        return arcStudentInfo;
    }

    private ArcStudentInfoWithBLOBs initAllStuInfoByStuId(String stuId){
        //获取学籍信息
        ArcStudentInfoWithBLOBs arcStudentInfo = this.studentService.getAllStudentInfoByStuId(stuId);
        if(arcStudentInfo == null){
            throw new BizException("无学籍信息,联系管理员!");
        }
        //获取教学计划关系信息
        ArcStudentPlanRel arcStudentPlanRel = this.arcStudentPlanRelMapper.selectByStuId(arcStudentInfo.getId());
        if(arcStudentPlanRel != null){
            String planId = arcStudentPlanRel.getPlanId();
            //获取教学计划名称
            TchTeachingPlan tchTeachingPlan = TchTeachingPlanService.getTeachingPlanById(planId);
            arcStudentInfo.setTchPlanText(tchTeachingPlan.getName());
            arcStudentInfo.setTchPlanId(planId);
        }

        return arcStudentInfo;
    }

    /**
     * 查询学生信息分页信息
     *
     * @param pageBean
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doGetImpLogInfoPageData.infc")
    @ResponseBody
    public Object doGetImpLogInfoPageData(PageBean pageBean, WebRequest request)
            throws Exception {
        pageBean = this.impLogService.getImpLogInfoPageData(pageBean,BizConstants.IMP_TYPE.STUDENT_INFO);
        return pageBean;
    }

    @NativeInfc
    @RequestMapping(value = "/getStuInfoByKey.infc")
    @ResponseBody
    public Object getStuInfoByKey(AutoInputVO autoInputVO) throws Exception {
        String key = autoInputVO.getQuery();
        //获取教师信息
        List<ArcStudentInfo> stuInfoList = this.arcStudentInfoMapper.selectStudentInfoByKey(key,10);
        List<AutoInputSuggestion> autoInputSuggestions = new ArrayList<AutoInputSuggestion>();
        for(int i=0 ;i < stuInfoList.size(); i++){
            ArcStudentInfo arcStudentInfo = stuInfoList.get(i);
            AutoInputSuggestion suggestion = new AutoInputSuggestion();
            suggestion.setData(arcStudentInfo.getId());
            suggestion.setValue(arcStudentInfo.getRealName());
            suggestion.setObject(arcStudentInfo);
            autoInputSuggestions.add(suggestion);
        }
        autoInputVO.setSuggestions(autoInputSuggestions);
        return autoInputVO;
    }


    /**by zl
     * 查询学生信息分页信息带参数
     *
     * @param pageBean
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doGetStudentInfoPageDataByParam.infc")
    @ResponseBody
    public Object doGetStudentInfoPageDataByParam(PageBean pageBean, WebRequest request)
            throws Exception {
        pageBean.getQueryparam().put("stuInfoStatus","3");
        pageBean = this.studentService.getUserInfoPageDataByParam(pageBean);
        return pageBean;
    }


    @RequestMapping(value = "/doModStuTeachingPlan.infc")
    @ResponseBody
    public Object doModStuTeachingPlan(VUserInfo userInfo,String planId,String relId) throws Exception {
        boolean bol = true;
        ArcStudentPlanRel newStudentPlanRel=new ArcStudentPlanRel();
        newStudentPlanRel.setId(relId);
        newStudentPlanRel.setPlanId(planId);

        if (!this.studentService.modifyStuTeachingPlanInfo(newStudentPlanRel)) {
            bol = false;
        }
        return bol;
    }


    /**
     * 备课检查导出
     * @throws Exception
     */
    @RequestMapping(value = "/exportStuInfo.do")
    public void exportStuInfo(HttpServletResponse response,String realName,String classId,String stuNo,String status,String enrolDateYearStart,String enrolDateYearEnd ) throws Exception{
        logger.info("开始执行导出学籍信息列表数据");
        ServletOutputStream outputStream = response.getOutputStream();
        HSSFWorkbook workbook = new HSSFWorkbook(); // 创建工作簿对象
        POIExcelUtil poiExcelUtil = new POIExcelUtil(workbook);
        poiExcelUtil.setColWidth(6000);//设置单元格宽度
        //获取列表数据
        List<ArcStudentInfo> stuList = null;
        Map paramMap = new HashMap();
        paramMap.put("realName",realName);
        paramMap.put("classId",classId);
        paramMap.put("stuNo",stuNo);
        paramMap.put("status",status);
        paramMap.put("enrolDateYearStart",enrolDateYearStart);
        paramMap.put("enrolDateYearEnd",enrolDateYearEnd);
        stuList = this.studentService.doGetStudentChangeInfo(paramMap);

        //组装excel
        Map colNameMap = new LinkedHashMap();
        colNameMap.put("学号","stuNumber");
        colNameMap.put("姓名","realName");
        colNameMap.put("性别","sexText");
        colNameMap.put("方向","majorText");
        colNameMap.put("年级班级","classText");
        colNameMap.put("休学时间","extInfo");
        workbook = poiExcelUtil.getExcelByData(stuList,colNameMap,"休/退信息导出",ArcStudentInfo.class);

        //组装输出信息
        response.setContentType("application/binary;charset=utf-8");
        String fileName = "休/退信息导出";
        String outfileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
        response.setHeader("Content-disposition", "attachment; filename=" + outfileName + ".xls");// 组装附件名称和格式

        try {
            workbook.write(outputStream);
            outputStream.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try {
                outputStream.close();
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**by zl
     * 查询学生考勤信息分页信息
     *
     * @param pageBean

     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doGetStuAttendanceCheckInfoPageData.infc")
    @ResponseBody
    public Object doGetStuAttendanceCheckInfoPageData(PageBean pageBean,TchStuAttendanceCheckInfo tchStuAttendanceCheckInfo)
            throws Exception {
        boolean bol = true;
        pageBean.getQueryparam().put("classId",tchStuAttendanceCheckInfo.getClassId());
        pageBean.getQueryparam().put("assessmentCode",tchStuAttendanceCheckInfo.getAssessmentCode());
        pageBean.getQueryparam().put("schoolYear",tchStuAttendanceCheckInfo.getSchoolYear());
        pageBean.getQueryparam().put("term",tchStuAttendanceCheckInfo.getTerm());

        ArcStudentInfo tempStudentInfo=new ArcStudentInfo();
        tempStudentInfo.setClassId(tchStuAttendanceCheckInfo.getClassId());
        tempStudentInfo.setStatus(BizConstants.INFO_STATUS.ACTIVE);
        List<ArcStudentInfo> studentInfoList = this.studentService.selectStudentInfoByParam(tempStudentInfo);
        for (int i=0 ;i < studentInfoList.size(); i++){//////////////////检验初始考勤数据是否存在
            TchStuAttendanceCheckInfo tempTchStuAttendanceCheckInfo=new TchStuAttendanceCheckInfo();
            tempTchStuAttendanceCheckInfo.setStuId(studentInfoList.get(i).getId());
            tempTchStuAttendanceCheckInfo.setAssessmentCode(tchStuAttendanceCheckInfo.getAssessmentCode());
            tempTchStuAttendanceCheckInfo.setSchoolYear(tchStuAttendanceCheckInfo.getSchoolYear());
            tempTchStuAttendanceCheckInfo.setTerm(tchStuAttendanceCheckInfo.getTerm());
            int attendanceCheckInfoNum=this.arcStudentInfoMapper.selectStuAttendanceCheckInfoCountByParam(tempTchStuAttendanceCheckInfo);
            if(attendanceCheckInfoNum==0){
                tempTchStuAttendanceCheckInfo.setTotal(0);
                this.studentService.addStuattendanceCheckInfo(tempTchStuAttendanceCheckInfo);
            }else{

            }
        }
        //TchStuAttendanceCheckInfo tempTchStuAttendanceCheckInfo=new TchStuAttendanceCheckInfo();
        // System.out.println("-------------doGetStuAttendanceCheckInfoPageData||222|"+tempTchStuAttendanceCheckInfo.getAssessmentCode()+"|||"+tempTchStuAttendanceCheckInfo.getClassId()+"||"+tempTchStuAttendanceCheckInfo.getSchoolYear());

        pageBean = this.studentService.getStuAttendanceCheckInfoPageData(pageBean);
        return pageBean;
    }

    @RequestMapping(value = "/doModStuAttendanceCheckTotal.infc")
    @ResponseBody
    public Object doModStuAttendanceCheckTotal(VUserInfo vUserInfo,String Id,String incrementStr) throws Exception {
        boolean bol = true;
        if(!this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.ADMIN) && !vUserInfo.getId().equals(BizConstants.SUPER_ADMIN_ID)
                && !this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.CLASSTEACHER)){///不是管理员、超级管理员、班主任
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_REFUSE);
        }else{
            if(incrementStr!=null && incrementStr.equals("1")){
                int i = this.arcStudentInfoMapper.modStuAttendanceCheckTotal(Id,1);
                if (i > 0) {
                    bol = true;
                }else{
                    bol =false;
                    throw new BizException(BizConstants.HTML_VAL.ERROR_MES_DATA_PROCESSING);
                }
            }else if(incrementStr!=null && incrementStr.equals("2")){
                int i = this.arcStudentInfoMapper.modStuAttendanceCheckTotal(Id,-1);
                if (i > 0) {
                    bol = true;
                }else{
                    bol =false;
                    throw new BizException(BizConstants.HTML_VAL.ERROR_MES_DATA_PROCESSING);
                }
            }else{
                throw new BizException(BizConstants.HTML_VAL.ERROR_MES_PARAM_ERROR);
            }
        }
        return bol;
    }
}
