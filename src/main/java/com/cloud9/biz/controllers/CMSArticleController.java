package com.cloud9.biz.controllers;

import com.cloud9.biz.dao.mybatis.ArcStudentInfoMapper;
import com.cloud9.biz.models.*;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.services.*;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.controller.BaseController;
import com.roroclaw.base.handler.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/article")
public class CMSArticleController extends BaseController {

    @Autowired
    private CmsFolderService folderService;

    @Autowired
    private CmsArticleService articleService;

    @Autowired
    private TchClassService tchClassService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private SysTeacherService sysTeacherService;

    @Autowired
    private ArcStudentInfoMapper arcStudentInfoMapper;

    @RequestMapping(value = "/doGetNoticesPageDataByFolderId.infc")
    @ResponseBody
    public Object doGetNoticesPageDataByFolderId(PageBean pageBean,WebRequest request,VUserInfo vUserInfo)
            throws Exception {
        String folderType= request.getParameter("fType");
        Map paramNew=pageBean.getQueryparam();
     // System.out.println("-------------doGetNoticesPageDataByFolderId|||"+folderType);

        if(folderType.equals("1") || folderType=="1"){/////////班级通知
            paramNew.put("folder_id",BizConstants.ARTICLE_NOTICE_FOLDER.stuNotice);
             if(vUserInfo.getType().equals(BizConstants.USER_TYPE.SYSTEM_STUDENT)){//学生
                 ArcStudentInfo arcStudentInfoParam =new ArcStudentInfo();
                 arcStudentInfoParam.setUserId(vUserInfo.getId());
                 ArcStudentInfo stuInfo = this.arcStudentInfoMapper.selectStudentInfoByParams(arcStudentInfoParam);
                paramNew.put("keyWord",stuInfo.getClassId());
            }else if(vUserInfo.getType().equals(BizConstants.USER_TYPE.SYSTEM_TEACHER)){//教工
                // paramNew.put("folder_id",BizConstants.ARTICLE_NOTICE_FOLDER.teacherNotice);
             }
        }else if(folderType.equals("2") || folderType=="2") {/////////教师通知
            paramNew.put("folder_id",BizConstants.ARTICLE_NOTICE_FOLDER.teacherNotice);
            if(vUserInfo.getType().equals(BizConstants.USER_TYPE.SUPER) || vUserInfo.getType().equals(BizConstants.USER_TYPE.SYSTEM_ADMIN)){//管理员

            }else if(vUserInfo.getType().equals(BizConstants.USER_TYPE.SYSTEM_TEACHER)){//教工

            }else{
                throw new BizException(BizConstants.HTML_VAL.ERROR_MES_REFUSE);//"拒绝请求！";
            }
        }else if(folderType.equals("3") || folderType=="3") {/////////全校通知
            paramNew.put("folder_id",BizConstants.ARTICLE_NOTICE_FOLDER.allNotice);
            //System.out.println("-------------doGetNoticesPageDataByFolderId||222|"+folderType);
        }
        paramNew.put("order_param","a.UPDATE_TIME");
        paramNew.put("status",BizConstants.ARTI_STATUS.ACTIVE);
        pageBean.setQueryparam(paramNew);
        pageBean = this.articleService.selectNoticesPageDataByFolderId(pageBean);
        return pageBean;
    }

    @RequestMapping(value = "/doGetArticlesPageDataByFolderId.infc")
    @ResponseBody
    public Object doGetArticlesPageDataByFolderId(PageBean pageBean,WebRequest request,VUserInfo vUserInfo)
            throws Exception {
        Map paramNew=pageBean.getQueryparam();
        if(BizConstants.USER_TYPE.SUPER == vUserInfo.getType()){//超级管理员
            paramNew.put("locked",vUserInfo.getAccToken());
        }else{
            paramNew.put("userId",vUserInfo.getId());
        }
        pageBean.setQueryparam(paramNew);
        pageBean = this.articleService.getArticlesPageDataByFolderId(pageBean);
        return pageBean;
    }


    @RequestMapping(value = "/doGetArticlesPageDataByAuthorForEdit.infc")
    @ResponseBody
    public Object doGetArticlesPageDataByAuthorForEdit(PageBean pageBean,WebRequest request,VUserInfo vUserInfo)
            throws Exception {
        Map paramNew=pageBean.getQueryparam();
        if(BizConstants.USER_TYPE.SUPER == vUserInfo.getType() || BizConstants.USER_TYPE.SYSTEM_ADMIN== vUserInfo.getType()){//超级管理员或者系统管理员
            paramNew.put("locked",vUserInfo.getAccToken());
        }else{
            paramNew.put("userId",vUserInfo.getId());
            paramNew.put("author",vUserInfo.getId());
        }
        pageBean.setQueryparam(paramNew);
        pageBean = this.articleService.getArticlesPageDataByFolderId(pageBean);
        return pageBean;
    }

    @RequestMapping(value = "/doGetArticleInfosByID.infc")
    @ResponseBody
    public Object doGetArticleInfosByID(CmsArticle ArticleInfo,WebRequest request,VUserInfo vUserInfo)throws Exception{
        ArticleInfo = this.articleService.getArticleInfosByID(ArticleInfo);
        if(ArticleInfo==null){
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_NORESULT);//无匹配数据。!
        }
        return ArticleInfo;
    }

    @RequestMapping(value = "/initNoticeDetail.do")
    public ModelAndView initNoticeDetail(String id){
        ModelAndView mv = new ModelAndView("cms/article/noticeDetail");
        CmsArticle ArticleInfoParam=new CmsArticle();
        ArticleInfoParam.setArticle_id(id);
        CmsArticle ArticleInfo = this.articleService.getArticleInfosByID(ArticleInfoParam);
       // System.out.println("-------------initNoticeDetail||222|"+ArticleInfo.getUpdate_time());

        if(ArticleInfo==null){
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_NORESULT);//无匹配数据。!
        }
        mv.addObject("ArticleInfo",ArticleInfo);
        return mv;
    }

    @RequestMapping(value = "/doAddArticleInfo.infc")
    @ResponseBody
    public Object doAddArticleInfo(CmsArticle ArticleInfo,WebRequest request,VUserInfo vUserInfo) throws BizException{
        boolean bol =false;
            ArticleInfo.setStatus(BizConstants.ARTI_STATUS.NEW);
            ArticleInfo.setAuthor(vUserInfo.getId());
            bol =this.articleService.addArticleInfo(ArticleInfo);
        return bol;
    }

    @RequestMapping(value = "/doUpdateArticleInfo.infc")
    @ResponseBody
    public Object doUpdateArticleInfo(CmsArticle ArticleInfo,WebRequest request,VUserInfo vUserInfo,PageBean pageBean)throws Exception {
        boolean bol =false;
       // String statusToChange=ArticleInfo.getStatus();
        ArticleInfo.setStatus("");
        CmsArticle ArticleInfoCheck = this.articleService.getArticleInfosByID(ArticleInfo);
       // ArticleInfo.setStatus(statusToChange);
        if(ArticleInfoCheck.getArticle_id()==null || ArticleInfoCheck.getArticle_id().equals("")){                  ////////////参数为0
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_NORESULT);     //无匹配数据。!
        }else if(!ArticleInfoCheck.getAuthor().equals(vUserInfo.getId())){
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_REFUSE);     //拒绝请求!
        }
            bol =this.articleService.updateArticleInfo(ArticleInfo);
        return bol;
    }

    @RequestMapping(value = "/doUpdateArticleInfoStatus.infc")
    @ResponseBody
    public Object doUpdateArticleInfoStatus(CmsArticle ArticleInfo,WebRequest request,VUserInfo vUserInfo,PageBean pageBean)throws Exception {
        boolean bol =false;
        String statusToChange=ArticleInfo.getStatus();
        ArticleInfo.setStatus("");
        CmsArticle ArticleInfoCheck = this.articleService.getArticleInfosByID(ArticleInfo);
        if(ArticleInfoCheck.getArticle_id()==null || ArticleInfoCheck.getArticle_id().equals("")){                  ////////////参数为0
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_NORESULT);     //无匹配数据。!
        }else if(this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.ADMIN) || vUserInfo.getId().equals(BizConstants.SUPER_ADMIN_ID)
                || this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.INFO_ADMIN)){///是管理员、超级管理员、信息管理员

        }else if(!ArticleInfoCheck.getAuthor().equals(vUserInfo.getId())){
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_REFUSE);     //拒绝请求!
        }
        ArticleInfo.setStatus(statusToChange);
        CmsArticle tempArticleInfo=new CmsArticle();
        tempArticleInfo.setArticle_id(ArticleInfo.getArticle_id());
        tempArticleInfo.setStatus(ArticleInfo.getStatus());
        bol =this.articleService.updateArticleInfo(tempArticleInfo);
        return bol;
    }








    @RequestMapping(value = "/doDeleteArticleInfo.infc")
    @ResponseBody
    public Object doDeleteArticleInfo(CmsArticle ArticleInfo,WebRequest request,VUserInfo vUserInfo,PageBean pageBean)throws Exception {
        boolean bol =false;

        CmsArticle ArticleInfoCheck = this.articleService.getArticleInfosByID(ArticleInfo);

        if(ArticleInfoCheck.getArticle_id()==null || ArticleInfoCheck.getArticle_id().equals("")){                  ////////////参数为0
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_NORESULT);     //无匹配数据。!
        }else if(!ArticleInfoCheck.getAuthor().equals(vUserInfo.getId())){
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_REFUSE);     //拒绝请求!
        }
            bol =this.articleService.deleteArticleInfo(ArticleInfo);

        return bol;
    }

    @RequestMapping(value = "/initEditArticle.do")
    public ModelAndView initEditArticle(String id,VUserInfo vUserInfo){
        ModelAndView mv = new ModelAndView("cms/article/articleModForm");
        CmsArticle tempArticleInfo=new CmsArticle();
        tempArticleInfo.setArticle_id(id);
        CmsArticle ArticleInfo = this.articleService.getArticleInfosByID(tempArticleInfo);
        if(ArticleInfo==null){
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_NORESULT);//无匹配数据。!
        }
        if(!ArticleInfo.getAuthor().equals(vUserInfo.getId())){///作者检验
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_REFUSE);//拒绝请求！
        }
        mv.addObject("articleInfo",ArticleInfo);
        return mv;
    }
    /**
     * 公告发布班级关键字下拉.
     */
    @RequestMapping(value = "/getClassInfoItemsByPowerForArtiAdd.infc")
    @ResponseBody
    public Object getClassInfoItemsByPowerForArtiAdd(String grade,String schoolYear,String period,String term,VUserInfo vUserInfo) throws Exception {
        String teacherId="";
        List<SysDictItem> ClassesInfosList=new ArrayList<SysDictItem>();
        List<SysDictItem> tempClassesInfosList=new ArrayList<SysDictItem>();
        if(this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.ADMIN) || vUserInfo.getId().equals(BizConstants.SUPER_ADMIN_ID)
                || this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.INFO_ADMIN)){///是管理员、超级管理员、信息管理员
            tempClassesInfosList = this.tchClassService.getClassItemsByPowerForArtiAdd(teacherId);
            ClassesInfosList.addAll(tempClassesInfosList);
        }
        if(this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.CLASSTEACHER)){/////班主任
            SysTeacherInfo sysTeacherInfo = this.sysTeacherService.getTeacherInfoByUserId(vUserInfo.getId());
            teacherId = sysTeacherInfo.getId();
            tempClassesInfosList = this.tchClassService.getClassItemsByPowerForArtiAdd(teacherId);
            ClassesInfosList.addAll(tempClassesInfosList);
        }
       // if(this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.TEACHER)){  ///////教师                                                                                            //////////////教师
     //       SysTeacherInfo sysTeacherInfo = this.sysTeacherService.getTeacherInfoByUserId(vUserInfo.getId());
     //       teacherId=sysTeacherInfo.getId();
     //       tempClassesInfosList = this.tchClassService.getClassItemsForTeachPower (grade, schoolYear, period, teacherId,term);
     //       ClassesInfosList.addAll(tempClassesInfosList);
      //  }
        return ClassesInfosList;
    }

}
