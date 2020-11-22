package com.cloud9.biz.controllers;

import com.cloud9.biz.dao.mybatis.*;
import com.cloud9.biz.models.*;
import com.cloud9.biz.models.vo.ClassScoreStaticVo;
import com.cloud9.biz.models.vo.StatisticsSectionVo;
import com.cloud9.biz.models.vo.VRecordConfig;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.services.*;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.annotation.ApiParam;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.controller.BaseController;
import com.roroclaw.base.handler.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by roroclaw on 2017/10/29.
 */
@Controller
@RequestMapping(value = "/scoExamScores")
public class ScoExamScoresController extends BaseController {

    @Autowired
    private ScoExamScoresService scoExamScoresService;

    @Autowired
    private TchCourseOpenService tchCourseOpenService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private SysTeacherService sysTeacherService;

    @Autowired
    private ArcStudentInfoMapper arcStudentInfoMapper;

    @Autowired
    private TchCourseService courseService;

    @Autowired
    private SysGradesMapper sysGradesMapper;

    @Autowired
    private ScoClassStuTotalMapper scoClassStuTotalMapper;

    @Autowired
    private ScoClassScoreStaticMapper scoClassScoreStaticMapper;

    @Autowired
    private ExaExamInfoMapper exaExamInfoMapper;

    /**
     * 获取登分成绩分页信息查询
     * @param pageBean
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doGetExamScoresPageData.infc")
    @ResponseBody
    public Object doGetExamScoresPageData(PageBean pageBean, WebRequest request,String openCourseId,VUserInfo vUserInfo)
            throws Exception {
        if(openCourseId == null || "".equals(openCourseId.trim())){
            throw new BizException("丢失课程信息!");
        }
        pageBean.getQueryparam().put("openCourseId",openCourseId);
        pageBean = this.scoExamScoresService.getRecordScoresPageData(pageBean,vUserInfo.getId());
        return pageBean;
    }

    /**
     * 获取登分成绩分页信息查询
     * @param pageBean
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doGetOtherExamScoresPageData.infc")
    @ResponseBody
    public Object doGetOtherExamScoresPageData(PageBean pageBean, WebRequest request,String examId,VUserInfo vUserInfo)
            throws Exception {
        if(examId == null || "".equals(examId.trim())){
            throw new BizException("丢失考试信息!");
        }
        pageBean.getQueryparam().put("examId",examId);
        pageBean = this.scoExamScoresService.getOtherRecordScoresPageData(pageBean,vUserInfo.getId());
        return pageBean;
    }

    /**
     * 获取学生成绩分页信息查询
     * @param pageBean
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doStuQueryScorePageData.infc")
    @ResponseBody
    public Object doStuQueryScorePageData(PageBean pageBean, WebRequest request,VUserInfo vUserInfo)
            throws Exception {
        String userId = vUserInfo.getId();
        ArcStudentInfo arcStudentInfo = this.arcStudentInfoMapper.selectStuInfoByUserId(userId);
        if(arcStudentInfo != null){
            pageBean = this.scoExamScoresService.stuQueryScorePageData(pageBean,arcStudentInfo.getId(), BizConstants.EXAM_STU_STATUS.NORMALE,BizConstants.RECORD_STATUS.END,BizConstants.SCORES_SUBJECT_STATUS.NORMAL);
        }
        return pageBean;
    }

    /**
     * 获取登分成绩分页信息查询
     * @param pageBean
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doGetExamScores4StatusPageData.infc")
    @ResponseBody
    public Object doGetExamScores4StatusPageData(PageBean pageBean,WebRequest request, VUserInfo vUserInfo)
            throws Exception {
        pageBean = this.scoExamScoresService.doGetExamScores4StatusPageData(pageBean);
        return pageBean;
    }

    /**
     * 获取登分成绩分页信息查询
     * @param pageBean
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doGetOtherExamScores4StatusPageData.infc")
    @ResponseBody
    public Object doGetOtherExamScores4StatusPageData(PageBean pageBean,WebRequest request, VUserInfo vUserInfo)
            throws Exception {
        pageBean = this.scoExamScoresService.doGetOtherExamScores4StatusPageData(pageBean);
        return pageBean;
    }

    /**
     * 获取管理员修改信息查询
     * @param pageBean
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doGetExamScores4AdminModPageData.infc")
    @ResponseBody
    public Object doGetExamScores4AdminModPageData(PageBean pageBean,WebRequest request, VUserInfo vUserInfo)
            throws Exception {
        pageBean = this.scoExamScoresService.getExamScores4AdminModPageData(pageBean);
        return pageBean;
    }

    /**
     * 获取管理员修改信息查询
     * @param pageBean
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doGetOtherExamScores4AdminModPageData.infc")
    @ResponseBody
    public Object doGetOtherExamScores4AdminModPageData(PageBean pageBean,WebRequest request, VUserInfo vUserInfo)
            throws Exception {
        pageBean = this.scoExamScoresService.getOtherExamScores4AdminModPageData(pageBean);
        return pageBean;
    }

//    /**
//     * 登分
//     * @param id 成绩记录id
//     * @param status 状态
//     * @param score 分数
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping(value = "/doRecordScores.infc")
//    @ResponseBody
//    public Object doRecordScores(String id,String status,String score,VUserInfo vUserInfo)
//            throws Exception {
//        //验证是否登分老师
//        boolean bol = this.scoExamScoresService.validateRecordTeacherByUserId(vUserInfo.getId(),id);
//        if(!bol){
//            throw new BizException("非登分教师,不可以登分!");
//        }
//        bol = this.scoExamScoresService.recordScores(id,status,score);
//        return bol;
//    }

    /**
     * 提交成绩
     * ids 考试成绩id串,逗号分割
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doSubRecordScores.infc")
    @ResponseBody
    public Object doSubRecordScores(String ids)
            throws Exception {
        boolean bol = this.scoExamScoresService.recordScores(ids);
        return bol;
    }

    //获取登分设置分页列表
    @RequestMapping(value = "/doGetRecordConfigPageData.infc")
    @ResponseBody
    public Object doGetRecordConfigPageData(PageBean pageBean, WebRequest request)
            throws Exception {
        pageBean = this.scoExamScoresService.getRecordConfigPageData(pageBean);
        return pageBean;
    }

    //获取临时登分设置分页列表
    @RequestMapping(value = "/doGetOtherRecordConfigPageData.infc")
    @ResponseBody
    public Object doGetOtherRecordConfigPageData(PageBean pageBean, WebRequest request)
            throws Exception {
        pageBean = this.scoExamScoresService.getOtherRecordConfigPageData(pageBean);
        return pageBean;
    }

    //获取登分设置分页列表
    @RequestMapping(value = "/doOtherTeacherSet.infc")
    @ResponseBody
    public Object doOtherTeacherSet(String examId,String teacherId)
            throws Exception {
        boolean bol = this.scoExamScoresService.doOtherTeacherSet(examId, teacherId);
        return bol;
    }

    //获取登分设置分页列表
    @RequestMapping(value = "/doTeacherSet.infc")
    @ResponseBody
    public Object doTeacherSet(String openCourseId,String teacherId,@ApiParam(name = "登分类型", required = true, value = "scoresType")String scoresType)
            throws Exception {
        boolean bol = this.scoExamScoresService.doTeacherSet(openCourseId,teacherId,scoresType);
        return bol;
    }


    //开始登分
    @RequestMapping(value = "/doStartRecord.infc")
    @ResponseBody
    public Object doStartRecord(String openCourseId,String scoresType,VUserInfo vUserInfo)
            throws Exception {
        boolean bol = this.scoExamScoresService.startRecord(openCourseId,scoresType,vUserInfo);
        return bol;
    }

    //开始登分
    @RequestMapping(value = "/doOtherStartRecord.infc")
    @ResponseBody
    public Object doOtherStartRecord(String examId,VUserInfo vUserInfo)
            throws Exception {
        boolean bol = this.scoExamScoresService.otherStartRecord(examId, vUserInfo);
        return bol;
    }

    //开始教师登分
    @RequestMapping(value = "/doStartTeacher.infc")
    @ResponseBody
    public Object doStartTeacher(String openCourseId,String scoresType,VUserInfo vUserInfo)
            throws Exception {
        boolean bol = this.scoExamScoresService.startTeacher(openCourseId, scoresType);
        return bol;
    }

    //开始教师登分
    @RequestMapping(value = "/doOtherStartTeacher.infc")
    @ResponseBody
    public Object doStartTeacher(String examId,VUserInfo vUserInfo)
            throws Exception {
        boolean bol = this.scoExamScoresService.otherStartTeacher(examId);
        return bol;
    }

    /**
     * 提交成绩
     * ids 考试成绩id串,逗号分割
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doSetScoresStatus.infc")
    @ResponseBody
    public Object doSetScoresStatus(String id,String status)
            throws Exception {
        boolean bol = this.scoExamScoresService.setScoresStatus(id, status);
        return bol;
    }

    @RequestMapping(value = "/doSetOtherScoresStatus.infc")
    @ResponseBody
    public Object doSetOtherScoresStatus(String id,String status)
            throws Exception {
        boolean bol = this.scoExamScoresService.setOtherScoresStatus(id, status);
        return bol;
    }

    @RequestMapping(value = "/doOtherSetScoresStatus.infc")
    @ResponseBody
    public Object doOtherSetScoresStatus(String id,String status)
            throws Exception {
        boolean bol = this.scoExamScoresService.setOtherScoresStatus(id, status);
        return bol;
    }

    @RequestMapping(value = "/doGetScoStatusCoursePageData.infc")
    @ResponseBody
    public Object doGetScoStatusCoursePageData(PageBean pageBean, WebRequest request)
            throws Exception {
        pageBean.getQueryparam().put("status", BizConstants.RECORD_CONFIG_STATUS.SET_STATUS);
        pageBean = this.scoExamScoresService.getRecordConfigPageData(pageBean);
        return pageBean;
    }

    @RequestMapping(value = "/doGetOtherScoStatusCoursePageData.infc")
    @ResponseBody
    public Object doGetOtherScoStatusCoursePageData(PageBean pageBean,String subjectId)
            throws Exception {
        pageBean.getQueryparam().put("configStatus", BizConstants.RECORD_CONFIG_STATUS.SET_STATUS);
        pageBean.getQueryparam().put("subjectId",subjectId);
        pageBean = this.scoExamScoresService.getOtherRecordConfigPageData(pageBean);
        return pageBean;
    }

    @RequestMapping(value = "/doGetScoRecordCoursePageData.infc")
    @ResponseBody
    public Object doGetScoRecordCoursePageData(PageBean pageBean,String subjectId,VUserInfo vUserInfo)
            throws Exception {
        pageBean.getQueryparam().put("isRecord",true);
        pageBean.getQueryparam().put("subjectId",subjectId);
        pageBean = this.scoExamScoresService.getRecordConfigPageData(pageBean, vUserInfo.getId());
        return pageBean;
    }

    @RequestMapping(value = "/doGetOtherScoRecordCoursePageData.infc")
    @ResponseBody
    public Object doGetOtherScoRecordCoursePageData(PageBean pageBean,VUserInfo vUserInfo)
            throws Exception {
        pageBean.getQueryparam().put("isRecord",true);
        pageBean = this.scoExamScoresService.getOtherRecordConfigPageData(pageBean, vUserInfo.getId());
        return pageBean;
    }

    @RequestMapping(value = "/subScoresConfig.infc")
    @ResponseBody
    public Object subScoresConfig(String openCourseId,String scoresType)
            throws Exception {
        this.scoExamScoresService.updateStatusByOpenCourseId(openCourseId,scoresType,BizConstants.RECORD_CONFIG_STATUS.RECORDING_SUB);
        return true;
    }

    @RequestMapping(value = "/subOtherScoresConfig.infc")
    @ResponseBody
    public Object subOtherScoresConfig(String examId)
            throws Exception {
        this.scoExamScoresService.updateOtherStatusByExamId(examId,BizConstants.RECORD_CONFIG_STATUS.RECORDING_SUB);
        return true;
    }

    @RequestMapping(value = "/doRebackStatusSet.infc")
    @ResponseBody
    public Object doRebackStatusSet(String openCourseId,String scoresType)
            throws Exception {
        this.scoExamScoresService.updateStatusByOpenCourseId(openCourseId,scoresType,BizConstants.RECORD_CONFIG_STATUS.SET_STATUS);
        return true;
    }

    @RequestMapping(value = "/doOtherRebackStatusSet.infc")
    @ResponseBody
    public Object doOtherRebackStatusSet(String examId)
            throws Exception {
        this.scoExamScoresService.updateOtherStatusByExamId(examId, BizConstants.RECORD_CONFIG_STATUS.SET_STATUS);
        return true;
    }

    @RequestMapping(value = "/doRebackRecord.infc")
    @ResponseBody
    public Object doRebackRecord(String openCourseId,String scoresType)
            throws Exception {
        this.scoExamScoresService.updateStatusByOpenCourseId(openCourseId,scoresType,BizConstants.RECORD_CONFIG_STATUS.RECORDING);
        return true;
    }

    @RequestMapping(value = "/doOtherRebackRecord.infc")
    @ResponseBody
    public Object doOtherRebackRecord(String examId)
            throws Exception {
        this.scoExamScoresService.updateOtherStatusByExamId(examId,BizConstants.RECORD_CONFIG_STATUS.RECORDING);
        return true;
    }

    @RequestMapping(value = "/doEndRecord.infc")
    @ResponseBody
    public Object doEndRecord(String openCourseId,String scoresType)
            throws Exception {
        this.scoExamScoresService.endScoreConfig(openCourseId,scoresType);
        return true;
    }

    @RequestMapping(value = "/doOtherEndRecord.infc")
    @ResponseBody
    public Object doOtherEndRecord(String examId)
            throws Exception {
        this.scoExamScoresService.endOtherScoreConfig(examId);
        return true;
    }

    @RequestMapping(value = "/initScoStatus.do")
    public ModelAndView initScoStatus(String openCourseId,String scoreType)
            throws Exception {
        ModelAndView mv = new ModelAndView("sco/scoStatus/scoStatus");
        TchCourseOpen tchCourseOpen = this.tchCourseOpenService.getCourseOpenById(openCourseId);
        mv.addObject("openCourse",tchCourseOpen);
        mv.addObject("scoreType",scoreType);
        return mv;
    }

    @RequestMapping(value = "/initOtherScoStatus.do")
    public ModelAndView initOtherScoStatus(String examId)
            throws Exception {
        ModelAndView mv = new ModelAndView("sco/scoStatus/otherScoStatus");
        VRecordConfig vRecordConfig = this.exaExamInfoMapper.selectInfosById(examId);
//        TchCourseOpen tchCourseOpen = this.tchCourseOpenService.getCourseOpenById(openCourseId);
        mv.addObject("vRecordConfig",vRecordConfig);
        mv.addObject("examId",examId);
        return mv;
    }

    @RequestMapping(value = "/initRecordScores.do")
    public ModelAndView initRecordScores(String openCourseId,String scoreType)
            throws Exception {
        ModelAndView mv = new ModelAndView("sco/recordScores/recordScores");
        TchCourseOpen tchCourseOpen = this.tchCourseOpenService.getCourseOpenById(openCourseId);
        mv.addObject("openCourse",tchCourseOpen);
        mv.addObject("scoreType",scoreType);
        return mv;
    }

    @RequestMapping(value = "/initOtherRecordScores.do")
    public ModelAndView initOtherRecordScores(String examId)
            throws Exception {
        ModelAndView mv = new ModelAndView("sco/recordScores/otherRecordScores");
        VRecordConfig vRecordConfig = this.exaExamInfoMapper.selectInfosById(examId);
        mv.addObject("vRecordConfig",vRecordConfig);
        mv.addObject("examId",examId);
        return mv;
    }


    ////////////by zl
    @RequestMapping(value = "/doGetScoreCousesForQueryList.infc")
    @ResponseBody
    public Object doGetScoreCousesForQueryList(ScoExamScoresForQuery scoExamScoresForQuery, WebRequest request, VUserInfo vUserInfo) throws BizException {
        List<ScoExamScoresForQuery> ScoExamScoresCousesForQueryList= this.scoExamScoresService.getScoreCousesForQueryListByParam(scoExamScoresForQuery);
        return ScoExamScoresCousesForQueryList;
    }

    @RequestMapping(value = "/doGetScoreStuForQueryList.infc")
    @ResponseBody
    public Object doGetScoreStuForQueryList(ScoExamScoresForQuery scoExamScoresForQuery, WebRequest request, VUserInfo vUserInfo) throws BizException {
        String teacherId="";
        if(!this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.ADMIN) && !vUserInfo.getId().equals(BizConstants.SUPER_ADMIN_ID)
                && !this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.CLASSTEACHER)
            && !this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.XIAOWUHUI)){///不是管理员、超级管理员、班主任、校务会成员
            SysTeacherInfo sysTeacherInfo = this.sysTeacherService.getTeacherInfoByUserId(vUserInfo.getId());
            teacherId=sysTeacherInfo.getId();
        }
      //  if(scoExamScoresForQuery.getCourseId()==null || scoExamScoresForQuery.getCourseId().equals("")){
      //      throw new BizException(BizConstants.HTML_VAL.ERROR_MES_PARAM_ERROR);
      //  }
        TchCourseWithBLOBs tchCourse = this.courseService.getCourseById(scoExamScoresForQuery.getCourseId());
        scoExamScoresForQuery.setSubjectId(tchCourse.getSubjectId());
        scoExamScoresForQuery.setTeacherId(teacherId);
        scoExamScoresForQuery.setNormaleStatus(BizConstants.EXAM_STU_STATUS.NORMALE);
        scoExamScoresForQuery.setRecordeStatus(BizConstants.RECORD_STATUS.END);
        List<ScoExamScoresForQuery> ScoExamScoreStuForQueryForQueryList= this.scoExamScoresService.getScoreStuForQueryListByParam(scoExamScoresForQuery);
        return ScoExamScoreStuForQueryForQueryList;
    }


    @RequestMapping(value = "/doGetScoreStuForScoreCollectingQuery.infc")
    @ResponseBody
    public Object doGetScoreStuForScoreCollectingQuery(ScoExamScoresForQuery scoExamScoresForQuery, WebRequest request, VUserInfo vUserInfo) throws BizException {
        String teacherId="";
        if(!this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.ADMIN) && !vUserInfo.getId().equals(BizConstants.SUPER_ADMIN_ID)
                && !this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.CLASSTEACHER) &&
                !this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.XIAOWUHUI)){///不是管理员、超级管理员、班主任
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_REFUSE);
        }
        //  if(scoExamScoresForQuery.getCourseId()==null || scoExamScoresForQuery.getCourseId().equals("")){
          //   throw new BizException(BizConstants.HTML_VAL.ERROR_MES_PARAM_ERROR);
        //  }
       // TchCourseWithBLOBs tchCourse = this.courseService.getCourseById(scoExamScoresForQuery.getCourseId());
        //scoExamScoresForQuery.setSubjectId(tchCourse.getSubjectId());
      //  scoExamScoresForQuery.setTeacherId(teacherId);
        scoExamScoresForQuery.setNormaleStatus(BizConstants.EXAM_STU_STATUS.NORMALE);
        scoExamScoresForQuery.setConfigStatus(BizConstants.RECORD_CONFIG_STATUS.END);
        scoExamScoresForQuery.setRecordeStatus(BizConstants.RECORD_STATUS.END);
        List<ScoExamScoresForQuery> ScoExamScoreStuForQueryForQueryList= this.scoExamScoresService.getScoreStuForScoreCollectingQueryByParam(scoExamScoresForQuery);
        return ScoExamScoreStuForQueryForQueryList;
    }

    /**
     * 批量成绩登分
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/batchRecordScores.infc")
    @ResponseBody
    public Object batchRecordScores(@RequestParam(value = "scoreArr[]") String[] scoreArr,VUserInfo vUserInfo)
            throws Exception {
        //验证是否教师角色
        boolean bol  = this.scoExamScoresService.batchRecordScores(scoreArr, vUserInfo.getId());
        return bol;
    }

    /**
     * 批量成绩登分
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/otherBatchRecordScores.infc")
    @ResponseBody
    public Object otherBatchRecordScores(@RequestParam(value = "scoreArr[]") String[] scoreArr,VUserInfo vUserInfo)
            throws Exception {
        //验证是否教师角色
        boolean bol  = this.scoExamScoresService.otherBatchRecordScores(scoreArr, vUserInfo.getId());
        return bol;
    }

    @RequestMapping(value = "/calStatisticsDatas.infc")
    @ResponseBody
    public Object doCalExamGrade(String schoolYear,String term,String scoreType,String grade)
            throws Exception {
        boolean bol = true;
        this.scoExamScoresService.calStatisticsDatas(schoolYear, term, scoreType, grade);
        return bol;
    }

    @RequestMapping(value = "/calExamClassStatisticsDatas.infc")
    @ResponseBody
    public Object calExamClassStatisticsDatas(String schoolYear,String term,String scoreType,String grade)
            throws Exception {
        boolean bol = true;
        this.scoExamScoresService.calExamClassStatisticsDatas(schoolYear, term, scoreType, grade);
        return bol;
    }

    /**
     * 成绩证明-1
     */
    @RequestMapping(value = "/stuScoreCertificatePrint.do")
    public ModelAndView stuScoreCertificatePrint(String id)
            throws Exception {
        if(id==null || id.equals("")){
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_PARAM_ERROR);
        }
        ModelAndView mv = new ModelAndView("sco/scoreQuery/stuScoreCertificatePrint");
        ArcStudentInfo tempStudentInfo=new ArcStudentInfo();
        tempStudentInfo.setId(id);
        ArcStudentInfo arcStudentInfo = this.arcStudentInfoMapper.selectStudentInfoByParams(tempStudentInfo);
        mv.addObject("studentInfo",arcStudentInfo);
        return mv;
    }

    /**
     * 成绩证明-2
     */
    @RequestMapping(value = "/stuScoreCertificatePrintCO.do")
    public ModelAndView stuScoreCertificatePrintCO(String id)
            throws Exception {
        if(id==null || id.equals("")){
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_PARAM_ERROR);
        }
        ModelAndView mv = new ModelAndView("sco/scoreQuery/stuScoreCertificatePrintCO");
        ArcStudentInfo tempStudentInfo=new ArcStudentInfo();
        tempStudentInfo.setId(id);
        ArcStudentInfo arcStudentInfo = this.arcStudentInfoMapper.selectStudentInfoByParams(tempStudentInfo);
        mv.addObject("studentInfo",arcStudentInfo);
        return mv;
    }


    @RequestMapping(value = "/initPrint.do")
    public ModelAndView initPrint(String openCourseId,String scoreType,VUserInfo vUserInfo)
            throws Exception {
        ModelAndView mv = new ModelAndView("sco/print/scoPrint");

        if(openCourseId == null || "".equals(openCourseId.trim())){
            throw new BizException("丢失课程信息!");
        }
        //获取开课信息
        TchCourseOpen tchCourseOpen = this.tchCourseOpenService.getCourseOpenById(openCourseId);
        String scoreTypeText = BizConstants.DICT_MAP.get(BizConstants.DICT_TYPE.SCORES_TYPE).get(scoreType);
        List<ScoExamScores> scoList = this.scoExamScoresService.getScoresDatas(openCourseId, scoreType, vUserInfo.getId());
        String className = "";
        if(scoList.size() > 0){
            ScoExamScores scoExamScores = scoList.get(0);
            className = scoExamScores.getClassName();
        }
        mv.addObject("courseName", tchCourseOpen.getCourseName());
        mv.addObject("schoolYear", tchCourseOpen.getSchoolYear());
        mv.addObject("className",className);
        mv.addObject("courseName", tchCourseOpen.getCourseName());
        mv.addObject("termText", tchCourseOpen.getTermText());
        mv.addObject("scoreTypeText", scoreTypeText);
        mv.addObject("scoList",scoList);
        return mv;
    }

    @RequestMapping(value = "/initOtherPrint.do")
    public ModelAndView initOtherPrint(String examId,VUserInfo vUserInfo)
            throws Exception {
        ModelAndView mv = new ModelAndView("sco/print/scoOtherPrint");

        if(examId == null || "".equals(examId.trim())){
            throw new BizException("丢失考试信息!");
        }
        //获取开课信息
        VRecordConfig vRecordConfig = this.exaExamInfoMapper.selectInfosById(examId);
        List<ScoOtherExamScores> scoList = this.scoExamScoresService.getOtherScoresDatas(examId,vUserInfo.getId());
        mv.addObject("planText", vRecordConfig.getPlanText());
        mv.addObject("subjectText", vRecordConfig.getSubjectText());
        mv.addObject("scoList",scoList);
        return mv;
    }

    @RequestMapping(value = "/initPrintExamGrade.do")
    public ModelAndView initPrintExamGrade(String schoolYear,String term,String scoreType,String grade)
            throws Exception {
        ModelAndView mv = new ModelAndView("sco/print/examGradeStatistics");

        String scoreTypeText = BizConstants.DICT_MAP.get(BizConstants.DICT_TYPE.SCORES_TYPE).get(scoreType);
        String termText = BizConstants.DICT_MAP.get(BizConstants.DICT_TYPE.TERM).get(term);
        SysGrades sysGrades = this.sysGradesMapper.selectByPrimaryKey(grade);
        List<Map> staList = this.scoClassStuTotalMapper.select4ExamGradePrint(schoolYear, term, scoreType, grade);
        mv.addObject("schoolYear",schoolYear);
        mv.addObject("termText",termText);
        mv.addObject("scoreTypeText", scoreTypeText);
        mv.addObject("gradeText", sysGrades.getName());
        mv.addObject("staList", staList);
        return mv;
    }

    @RequestMapping(value = "/initPrintExamClass.do")
    public ModelAndView initPrintExamClass(String schoolYear,String term,String scoreType,String grade)
            throws Exception {
        ModelAndView mv = new ModelAndView("sco/print/examClassStatistics");

        String scoreTypeText = BizConstants.DICT_MAP.get(BizConstants.DICT_TYPE.SCORES_TYPE).get(scoreType);
        String termText = BizConstants.DICT_MAP.get(BizConstants.DICT_TYPE.TERM).get(term);
        SysGrades sysGrades = this.sysGradesMapper.selectByPrimaryKey(grade);
        List<ScoClassScoreStatic> scoClassScoreStaticList = this.scoClassScoreStaticMapper.select4ExamClassPrint(schoolYear, term, scoreType, grade);
        List<ClassScoreStaticVo> staList = new ArrayList<ClassScoreStaticVo>();
        Map<String,ClassScoreStaticVo> keyMap = new HashMap();
        for (int i= 0; i < scoClassScoreStaticList.size() ;i++){
            ScoClassScoreStatic scoClassScoreStatic = scoClassScoreStaticList.get(i);
            String classId = scoClassScoreStatic.getClassId();
            ClassScoreStaticVo classScoreStaticVo = keyMap.get(classId);
            if(classScoreStaticVo != null){
                classScoreStaticVo.addScoreStatic(scoClassScoreStatic);
            }else{
                classScoreStaticVo = new ClassScoreStaticVo();
                classScoreStaticVo.setClassName(scoClassScoreStatic.getClassName());
                classScoreStaticVo.addScoreStatic(scoClassScoreStatic);
                keyMap.put(classId,classScoreStaticVo);
                staList.add(classScoreStaticVo);
            }
        }
        StatisticsSectionVo statisticsSectionVo = this.scoExamScoresService.getSectionByGrade(grade);
        mv.addObject("section",statisticsSectionVo);
        mv.addObject("schoolYear",schoolYear);
        mv.addObject("schoolYear",schoolYear);
        mv.addObject("termText",termText);
        mv.addObject("scoreTypeText", scoreTypeText);
        mv.addObject("gradeText", sysGrades.getName());
        mv.addObject("staList", staList);
        return mv;
    }

    @RequestMapping(value = "/initAdminModScores.do")
    public ModelAndView initAdminModScores(String openCourseId,String scoreType)
            throws Exception {
        ModelAndView mv = new ModelAndView("tch/recordConfig/modScores");
        TchCourseOpen tchCourseOpen = this.tchCourseOpenService.getCourseOpenById(openCourseId);
        mv.addObject("openCourse",tchCourseOpen);
        mv.addObject("scoreType",scoreType);
        return mv;
    }

    @RequestMapping(value = "/initOtherAdminModScores.do")
    public ModelAndView initOtherAdminModScores(String examId)
            throws Exception {
        ModelAndView mv = new ModelAndView("tch/recordConfig/modOtherScores");
        VRecordConfig vRecordConfig = this.exaExamInfoMapper.selectInfosById(examId);
        mv.addObject("vRecordConfig",vRecordConfig);
        mv.addObject("examId",examId);
        return mv;
    }
}
