package com.cloud9.biz.controllers;

import com.cloud9.biz.models.ArcStudentInfo;
import com.cloud9.biz.models.ScoSubjectScores;
import com.cloud9.biz.models.TchTchplanSubjectRel;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.services.*;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.bean.MemoryCache;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.controller.BaseController;
import com.roroclaw.base.handler.BizException;
import com.roroclaw.base.utils.FileKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by roroclaw on 2017/8/16.
 */
@Controller
@RequestMapping(value = "/subjectScores")
public class ScoSubjectScoresController extends BaseController {

    @Autowired
    private ScoSubjectScoresService scoSubjectScoresService;

    @Autowired
    private ImpLogService impLogService;

    @Autowired
    private ArcStudentService studentService;

    @Autowired
    private TchPlanSubjectRelService tchPlanSubjectRelService;

    @Autowired
    private SysUserService userService;

    @Autowired
    private CommonService commonService;

    /**
     * 科目成绩分页信息查询
     * @param pageBean
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doGetSubjectScoresPageData.infc")
    @ResponseBody
    public Object doGetSubjectScoresPageData(PageBean pageBean, WebRequest request)
            throws Exception {
        pageBean = this.scoSubjectScoresService.getSubjectScoresPageData(pageBean);
        return pageBean;
    }

//    /**
//     * 查询科目班级成绩信息
//     * @param pageBean
//     * @param request
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping(value = "/doGetSubjectScoreClassPageData.infc")
//    @ResponseBody
//    public Object doGetSubjectScoreClassPageData(PageBean pageBean, WebRequest request)
//            throws Exception {
//        pageBean = this.scoSubjectScoresService.getSubjectScoreClassPageData(pageBean);
//        return pageBean;
//    }

    /**
     * 导入科目成绩信息
     * @param file
     * @param resp
     * @return
     * @throws com.roroclaw.base.handler.BizException
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/importSubjectScores.infc", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Object importSubjectScores(MultipartFile file, HttpServletResponse resp,String accToken)
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
            importInfo = this.scoSubjectScoresService.importSubjectScores(filename,file,vUserInfo);
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

    /**
     * 查询科目成绩信息分页信息
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
        pageBean = this.impLogService.getImpLogInfoPageData(pageBean, BizConstants.IMP_TYPE.SUBJECT_SCORES_INFO);
        return pageBean;
    }

    /**
     * 新增科目成绩信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doAddSubjectScore.infc")
    @ResponseBody
    public Object doAddSubjectScore(ScoSubjectScores subjectScores,VUserInfo userInfo) throws Exception {
          boolean bol = this.scoSubjectScoresService.addSubjectScore(subjectScores,userInfo.getId());
          return bol;
    }

    /**
     * 发布成绩信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/batchPublish.infc")
    @ResponseBody
    public Object batchPublishScore(@RequestParam(value = "idArr[]") String[] idArr) throws Exception {
          boolean bol = this.scoSubjectScoresService.batchPublishScore(idArr);
        return bol;
    }

    /**
     * 整体发布科目成绩信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/batchPublishAllByParam.infc")
    @ResponseBody
    public Object batchPublishAllByParam(ScoSubjectScores subjectScores,VUserInfo userInfo) throws Exception {
        boolean bol = this.scoSubjectScoresService.batchPublishAllScoreByParam(subjectScores);
        return bol;
    }

    /**
     * 修改科目成绩信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doModSubjectScore.infc")
    @ResponseBody
    public Object doModSubjectScore(ScoSubjectScores subjectScores,VUserInfo userInfo) throws Exception {
          boolean bol = this.scoSubjectScoresService.modSubjectScore(subjectScores,userInfo.getId());
          return bol;
    }

    /**
     * 新增科目成绩信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doDelSubjectScores.infc")
    @ResponseBody
    public Object doDelSubjectScores(String id) throws Exception {
          boolean bol = this.scoSubjectScoresService.delSubjectScores(id);
        return bol;
    }

    @RequestMapping(value = "/initEdit.do")
    public ModelAndView initEdit(String id){
        ModelAndView mv = new ModelAndView("sco/score/subjectScoreModForm");
        ScoSubjectScores scoSubjectScores = this.scoSubjectScoresService.selectSubjectSocresById(id);
        mv.addObject("scoSubjectScores",scoSubjectScores);
        return mv;
    }
    /**
     * by zl
     *
     */



    @RequestMapping(value = "/doGetStuScoreCertificateTableInfoByParam.infc")
    @ResponseBody
    public Object doGetStuScoreCertificateTableInfoByParam(String periodSection,String stuId,WebRequest request, VUserInfo vUserInfo) throws BizException {
        String grades="";
        ////////////////////////////////学段转换成年级
        if(periodSection.equals("1")){
            grades="4,5,6";
        }else if(periodSection.equals("2")){
            grades="7,8,9";
        }else if(periodSection.equals("3")){
            grades="10,11,12";
        }else{
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_PARAM_ERROR);
        }
        if(stuId==null || stuId.equals("")){///参数检验
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_PARAM_ERROR);
        }
        List list=new ArrayList();
        String status=BizConstants.SCORES_SUBJECT_STATUS.NORMAL;//////有效科目（总评）总成绩
        List<ScoSubjectScores> scStuScoreCertificateHeadInfoList = this.scoSubjectScoresService.getStuScoreCertificateHeadInfoByParam(grades, stuId,status);
        list.add(0,scStuScoreCertificateHeadInfoList);
        ArcStudentInfo arcStudentInfo = this.studentService.getStudentInfoById(stuId);
        List<TchTchplanSubjectRel> TchPlanRelInfosList=new ArrayList();
        if(arcStudentInfo!=null){
            TchTchplanSubjectRel tchTchplanSubjectRel=new TchTchplanSubjectRel();
            tchTchplanSubjectRel.setPlanId(arcStudentInfo.getTchPlanId());
            TchPlanRelInfosList = this.tchPlanSubjectRelService.getTchPlanRelSubjectsListByTchPlanId(tchTchplanSubjectRel);
        }else{
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_DATA_REL_EXIST);
        }
       // List<ScoSubjectScores> scStuScoreCertificateInfoList = this.scoSubjectScoresService.getStuScoreCertificateInfoByParam(grades,stuId,status);
        list.add(1,TchPlanRelInfosList);
        return list;
    }

    @RequestMapping(value = "/doGetStuScoreCertificateTableInfoCOByParam.infc")
    @ResponseBody
    public Object doGetStuScoreCertificateTableInfoCOByParam(String periodSection,String stuId,WebRequest request, VUserInfo vUserInfo) throws BizException {
        String grades="";
        ////////////////////////////////学段转换成年级
        if(periodSection.equals("1")){
            grades="4,5,6";
        }else if(periodSection.equals("2")){
            grades="7,8,9";
        }else if(periodSection.equals("3")){
            grades="10,11,12";
        }else{
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_PARAM_ERROR);
        }
        if(stuId==null || stuId.equals("")){///参数检验
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_PARAM_ERROR);
        }
        List list=new ArrayList();
        String status=BizConstants.SCORES_SUBJECT_STATUS.NORMAL;//////有效科目（总评）总成绩
        List<ScoSubjectScores> scStuScoreCertificateHeadInfoList = this.scoSubjectScoresService.getStuScoreCertificateHeadInfoByParam(grades, stuId,status);
        list.add(0,scStuScoreCertificateHeadInfoList);

       /* ArcStudentInfo arcStudentInfo = this.studentService.getStudentInfoById(stuId);
        List<TchTchplanSubjectRel> TchPlanRelInfosList=new ArrayList();
        if(arcStudentInfo!=null){
            TchTchplanSubjectRel tchTchplanSubjectRel=new TchTchplanSubjectRel();
            tchTchplanSubjectRel.setPlanId(arcStudentInfo.getTchPlanId());
            TchPlanRelInfosList = this.tchPlanSubjectRelService.getTchPlanRelSubjectsListByTchPlanId(tchTchplanSubjectRel);
        }else{
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_DATA_REL_EXIST);
        }
        */
        List<ScoSubjectScores> scStuScoreCertificateInfoList = this.scoSubjectScoresService.getStuScoreCertificateInfoByParam(grades,stuId,status);

        // List<ScoSubjectScores> scStuScoreCertificateInfoList = this.scoSubjectScoresService.getStuScoreCertificateInfoByParam(grades,stuId,status);
        list.add(1,scStuScoreCertificateInfoList);
        return list;
    }

    @RequestMapping(value = "/doGetStuScoreCertificateInfoByParam.infc")
    @ResponseBody
    public Object doGetStuScoreCertificateInfoByParam(String periodSection,String stuId,WebRequest request, VUserInfo vUserInfo) throws BizException {
        String grades="";
        ////////////////////////////////学段转换成年级
        if(periodSection.equals("1")){
            grades="4,5,6";
        }else if(periodSection.equals("2")){
            grades="7,8,9";
        }else if(periodSection.equals("3")){
            grades="10,11,12";
        }else{
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_PARAM_ERROR);
        }
        List list=new ArrayList();
        String status=BizConstants.SCORES_SUBJECT_STATUS.NORMAL;//////有效科目（总评）总成绩
        List<ScoSubjectScores> scStuScoreCertificateInfoList = this.scoSubjectScoresService.getStuScoreCertificateInfoByParam(grades,stuId,status);
        return scStuScoreCertificateInfoList;
    }

    @RequestMapping(value = "/doGetStuSubjectScoreInfoByParam.infc")
    @ResponseBody
    public Object doGetStuSubjectScoreInfoByParam(ScoSubjectScores subjectScores,WebRequest request, VUserInfo vUserInfo) throws BizException {
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
        subjectScores.setStatus(BizConstants.SCORES_SUBJECT_STATUS.NORMAL);
        List<ScoSubjectScores> ScoSubjectScoresForQueryList= this.scoSubjectScoresService.getStuSubjectScoreByParam(subjectScores);
        return ScoSubjectScoresForQueryList;
    }

    @RequestMapping(value = "/doGetStuSubjectInfoListByParam.infc")
    @ResponseBody
    public Object doGetStuSubjectInfoListByParam(ScoSubjectScores subjectScores,WebRequest request, VUserInfo vUserInfo) throws BizException {
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
        subjectScores.setStatus(BizConstants.SCORES_SUBJECT_STATUS.NORMAL);
        List<ScoSubjectScores> ScoSubjectScoresForQueryList= this.scoSubjectScoresService.getStuSubjectInfoListByParam(subjectScores);
        return ScoSubjectScoresForQueryList;
    }
}
