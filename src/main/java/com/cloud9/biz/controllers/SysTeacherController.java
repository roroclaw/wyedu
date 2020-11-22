package com.cloud9.biz.controllers;

import com.cloud9.biz.models.*;
import com.cloud9.biz.models.SysTeacherInfo;
import com.cloud9.biz.models.vo.AutoInputSuggestion;
import com.cloud9.biz.models.vo.AutoInputVO;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.services.RoleService;
import com.cloud9.biz.services.SysUserService;
import com.cloud9.biz.services.SysTeacherService;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.annotation.NativeInfc;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.controller.BaseController;
import com.roroclaw.base.handler.BizException;
import com.roroclaw.base.utils.Constants;
import com.roroclaw.base.utils.HttpKit;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/teacher")
public class SysTeacherController extends BaseController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysTeacherService teacherService;
    @Autowired
    private RoleService roleService;



    @RequestMapping(value = "/doGetTeacherPageData.infc")
    @ResponseBody
    public Object doGetTeacherPageData(PageBean pageBean, WebRequest request)
            throws Exception {
        pageBean = this.teacherService.getTeacherInfoPageData(pageBean);
        return pageBean;
    }

    @RequestMapping(value = "/doGetTeacherListsByParam.infc")
    @ResponseBody
    public Object doGetTeacherListsByParam(SysTeacherInfo sysTeacherInfo, WebRequest request, VUserInfo vUserInfo) throws BizException {

        List<SysTeacherInfo> TeacherInfosList = this.teacherService.getTeachersListByParam(sysTeacherInfo);

        return TeacherInfosList;
    }

    @RequestMapping(value = "/doAddTeacher.infc")
    @ResponseBody
    public Object doAddTeacher(SysTeacherInfo sysTeacherInfo,VUserInfo userInfo) throws Exception {
        boolean bol = true;
        this.teacherService.addTeacher(sysTeacherInfo, userInfo);
        return bol;
    }

    @RequestMapping(value = "/doModTeacherInfo.infc")
    @ResponseBody
    public Object doUpdateTeacherInfo(VUserInfo userInfo,SysTeacherInfo sysTeacherInfo) throws Exception {
        boolean bol = true;
        String teacherId = sysTeacherInfo.getId();
        if(teacherId == null || "".equals(teacherId)){
            throw new BizException("修改目标缺失!");
        }
        if (!this.teacherService.modifyTeacherInfo(sysTeacherInfo)) {
            bol = false;
        }
        return bol;
    }

    @RequestMapping(value = "/initEdit.do")
    public ModelAndView initEdit(String id){
        ModelAndView mv = new ModelAndView("sys/teacher/teacherModForm");
        //获取学籍信息
        SysTeacherInfo sysTeacherInfo = this.teacherService.getTeavherInfoById(id);
        mv.addObject("teacherInfo",sysTeacherInfo);
        return mv;
    }

    @NativeInfc
    @RequestMapping(value = "/getTeacherItems.infc")
    @ResponseBody
    public Object getTeacherItems(AutoInputVO autoInputVO) throws Exception {
        String key = autoInputVO.getQuery();
        //获取教师信息
        List<SysTeacherInfo> sysTeacherInfoList = this.teacherService.getTeacherListByKey(key);
        List<AutoInputSuggestion> autoInputSuggestions = new ArrayList<AutoInputSuggestion>();
        for(int i=0 ;i < sysTeacherInfoList.size(); i++){
            SysTeacherInfo sysTeacherInfo = sysTeacherInfoList.get(i);
            AutoInputSuggestion suggestion = new AutoInputSuggestion();
            suggestion.setData(sysTeacherInfo.getId());
            suggestion.setValue(sysTeacherInfo.getName());
            autoInputSuggestions.add(suggestion);
        }
        autoInputVO.setSuggestions(autoInputSuggestions);
        return autoInputVO;
    }
}
