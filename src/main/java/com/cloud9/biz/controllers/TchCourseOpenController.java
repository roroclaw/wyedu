package com.cloud9.biz.controllers;

/**
 * Created by zl on 2017/9/3.
 */
import com.cloud9.biz.models.*;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.services.CommonService;
import com.cloud9.biz.services.SysTeacherService;
import com.cloud9.biz.services.TchCourseOpenService;
import com.cloud9.biz.services.TchClassService;
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

import java.util.List;

@Controller
@RequestMapping(value = "/courseOpen")
public class TchCourseOpenController extends BaseController{

    @Autowired
    private TchCourseOpenService courseOpenService;

    @Autowired
    private TchClassService tchClassService;

    @Autowired
    private SysTeacherService sysTeacherService;

    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/doGetCourseOpensPageData.infc")
    @ResponseBody
    public Object doGetCourseOpensPageData(PageBean pageBean,WebRequest request,VUserInfo userInfo)
            throws Exception {
        pageBean = this.courseOpenService.getCourseOpensPageData(pageBean);
        return pageBean;
    }

    @RequestMapping(value = "/doGetCourseOpensByTeacherPageData.infc")
    @ResponseBody
    public Object doGetCourseOpensByTeacherPageData(PageBean pageBean,WebRequest request,VUserInfo userInfo)
            throws Exception {
        SysTeacherInfo sysTeacherInfo=this.sysTeacherService.getTeacherInfoByUserId(userInfo.getId());
        if(this.commonService.isTheRoleForCheck(userInfo.getId(), BizConstants.SYS_ROLE_ID.ADMIN) || userInfo.getId().equals(BizConstants.SUPER_ADMIN_ID)
                || this.commonService.isTheRoleForCheck(userInfo.getId(), BizConstants.SYS_ROLE_ID.XIAOWUHUI)){///是管理员、超级管理员
            pageBean.addQueryparam("teacherId", "");
        }else{
            pageBean.addQueryparam("teacherId", sysTeacherInfo.getId());
        }
        pageBean = this.courseOpenService.getCourseOpensPageData(pageBean);
        return pageBean;
    }

    @RequestMapping(value = "/doGetTchCourseOpenList.infc")
    @ResponseBody
    public Object doGetTchCourseOpenList(TchCourseOpen tchCourseOpen,WebRequest request,VUserInfo vUserInfo) throws BizException{
        List<TchCourseOpen> courseOpenList = this.courseOpenService.getCourseOpenList(tchCourseOpen);
        return courseOpenList;
    }

    @RequestMapping(value = "/doAddCourseOpen.infc")
    @ResponseBody
    public Object doAddCourseOpen(WebRequest request,VUserInfo userInfo,TchCourseOpen courseOpenInfo) throws Exception {
        boolean bol =false;
        String newCourseOpenId = BizConstants.ID_PRECODE.COURSEOPEN_ID_PRECODE+BizConstants.generatorPid();
        courseOpenInfo.setId(newCourseOpenId);
        TchClassInfo classInfo=this.tchClassService.getClassInfoById(courseOpenInfo.getClassId());
        courseOpenInfo.setGrade(classInfo.getGrade());
        if(this.courseOpenService.addCourseOpen(courseOpenInfo)){
            bol =true;
        }else{
            bol =false;
        }
        return bol;
    }

    @RequestMapping(value = "/doModCourseOpenInfo.infc")
    @ResponseBody
    public Object doModCourseOpenInfo(VUserInfo userInfo,TchCourseOpen courseOpenInfo) throws Exception {
        boolean bol = true;
        String courseOpenId = courseOpenInfo.getId();
        if(courseOpenId == null || "".equals(courseOpenId)){
            throw new BizException("修改目标缺失!");
        }
        if (!this.courseOpenService.modifyCourseOpenInfo(courseOpenInfo)) {
            bol = false;
        }
        return bol;
    }

    /**
     * 删除
     *
     * @return
     */
    @RequestMapping(value = "/doDelTchCourseOpenById.infc")
    @ResponseBody
    public Object doDelTchCourseOpenById(String id) throws BizException {
        boolean bol = this.courseOpenService.delTchCourseOpenById(id);
        return bol;
    }

    @RequestMapping(value = "/initEdit.do")
    public ModelAndView initEdit(String id){
        ModelAndView mv = new ModelAndView("tch/courseOpen/courseOpenModForm");
        //获取学籍信息
        TchCourseOpen tchCourseOpen = this.courseOpenService.getCourseOpenById(id);
        mv.addObject("courseOpenInfo",tchCourseOpen);
        return mv;
    }
    @RequestMapping(value = "/initEditForCourseStudentRel.do")
    public ModelAndView initEditForCourseStudentRel(String id){
        ModelAndView mv = new ModelAndView("tch/courseOpen/courseStudentRelModForm");
        //获取计划信息
        TchCourseOpen tchCourseOpen = this.courseOpenService.getCourseOpenById(id);
        mv.addObject("courseOpenInfo",tchCourseOpen);
        return mv;
    }

    @RequestMapping(value = "/doAddCourseStudentRel.infc")
    @ResponseBody
    public Object doAddCourseStudentRel(TchStuCourseOpenRel tchStuCourseOpenRel,VUserInfo userInfo) throws Exception {
        boolean bol =true;
        this.courseOpenService.addStuCourseOpenRelInfo(tchStuCourseOpenRel);
        return bol;
    }

    @RequestMapping(value = "/doGetTchCourseOpenStudentsPageData.infc")
    @ResponseBody
    public Object doGetTchCourseOpenStudentsPageData(PageBean pageBean, WebRequest request)
            throws Exception {
        pageBean = this.courseOpenService.getTchCourseOpenStudentsPageData(pageBean);
        return pageBean;
    }

    @RequestMapping(value = "/doGetTchCourseOpenStudentsList.infc")
    @ResponseBody
    public Object doGetFoldersList(TchStuCourseOpenRel tchStuCourseOpenRel,WebRequest request,VUserInfo vUserInfo) throws BizException{
        List<TchStuCourseOpenRel> stuCourseOpenRelList = this.courseOpenService.getCourseOpenStudentsList(tchStuCourseOpenRel);
        return stuCourseOpenRelList;
    }


    /**
     * 删除开课学生
     *
     * @return
     */
    @RequestMapping(value = "/doDelTchStuCourseOpenRelById.infc")
    @ResponseBody
    public Object doDelTchStuCourseOpenRelById(String id) throws BizException {
        boolean bol = this.courseOpenService.delTchStuCourseOpenRelById(id);
        return bol;
    }

}
