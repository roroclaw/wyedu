package com.cloud9.biz.controllers;

import com.cloud9.biz.models.TchCourse;
import com.cloud9.biz.models.TchCourseWithBLOBs;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.services.TchCourseService;
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
@RequestMapping(value = "/course")
public class TchCourseController extends BaseController {

    @Autowired
    private TchCourseService courseService;

    @RequestMapping(value = "/doGetCoursesPageData.infc")
    @ResponseBody
    public Object doGetCoursesPageData(PageBean pageBean,WebRequest request,VUserInfo userInfo)
            throws Exception {
        int userType =userInfo.getType();
        pageBean.addQueryparam("userId",userInfo.getId());
        pageBean = this.courseService.getCoursesPageData(pageBean);
        return pageBean;
    }

  /*  @RequestMapping(value = "/doGetCoursesGroupPageData.infc")
    @ResponseBody
    public Object doGetCoursesGroupPageData(PageBean pageBean,WebRequest request,VUserInfo userInfo)
            throws Exception {
        int userType =userInfo.getType();
        pageBean.addQueryparam("userId",userInfo.getId());
        pageBean.addQueryparam("cgCourseId", request.getParameter("father_id"));
        pageBean = this.courseService.getCoursesGroupPageData(pageBean);
        return pageBean;
    }
*/

    @RequestMapping(value = "/doGetCoursesList.infc")
    @ResponseBody
    public Object doGetFoldersList(TchCourse courseInfo,WebRequest request,VUserInfo vUserInfo) throws BizException{
        List<TchCourse> courseInfosList = this.courseService.getCoursesList(courseInfo);
        return courseInfosList;
    }

    @RequestMapping(value = "/doAddCourse.infc")
    @ResponseBody
    public Object doAddCourse(WebRequest request,VUserInfo userInfo,TchCourseWithBLOBs courseInfo) throws Exception {
        boolean bol =false;
        String newCourseId = BizConstants.ID_PRECODE.COURSE_ID_PRECODE+BizConstants.generatorPid();
        courseInfo.setId(newCourseId);
        if(this.courseService.addCourse(courseInfo)){
            bol =true;
        }else{
            bol =false;
        }
        return bol;
    }

    @RequestMapping(value = "/doModCourseInfo.infc")
    @ResponseBody
    public Object doModCourseInfo(VUserInfo userInfo,TchCourseWithBLOBs courseInfo) throws Exception {
        boolean bol = true;
        String courseId = courseInfo.getId();
        if(courseId == null || "".equals(courseId)){
            throw new BizException("修改目标缺失!");
        }
        if (!this.courseService.modifyCourseInfo(courseInfo)) {
            bol = false;
        }
        return bol;
    }

    @RequestMapping(value = "/initEdit.do")
    public ModelAndView initEdit(String id){
        ModelAndView mv = new ModelAndView("tch/course/courseModForm");
        //获取学籍信息
        TchCourseWithBLOBs tchCourse = this.courseService.getCourseById(id);
        mv.addObject("courseInfo",tchCourse);
        return mv;
    }
}
