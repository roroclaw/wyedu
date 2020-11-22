package com.cloud9.biz.controllers;

import com.cloud9.biz.dao.mybatis.TchClassInfoMapper;
import com.cloud9.biz.models.*;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.services.SysClassroomService;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.controller.BaseController;
import com.roroclaw.base.handler.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
 * Created by zl on 2017/9/7.
 */
@Controller
@RequestMapping(value = "/classroom")

public class TchClassroomController  extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(TchClassController.class);

    @Autowired
    private SysClassroomService sysClassroomService;

    @RequestMapping(value = "/doGetClassroomPageData.infc")
    @ResponseBody
    public Object doGetClassroomPageData(PageBean pageBean, WebRequest request)
            throws Exception {
        pageBean = this.sysClassroomService.getClassPageData(pageBean);
        return pageBean;
    }



    @RequestMapping(value = "/getClassroomItems.infc")
    @ResponseBody
    public Object doGetClassroomItems(String buildingNo) throws BizException {
        List<SysDictItem> resList = null;
        resList = this.sysClassroomService.getClassroomItems(buildingNo);
        return resList;
    }

    @RequestMapping(value = "/getClassroomList.infc")
    @ResponseBody
    public Object getClassroomList(String buildingNo) throws BizException {
        List<SysClassroom> resList = this.sysClassroomService.getClassroomList(buildingNo);
        return resList;
    }
    @RequestMapping(value = "/doAddClassroom.infc")
    @ResponseBody
    public Object doAddClassroom(SysClassroom sysClassroomInfo,VUserInfo vUserInfo)
            throws Exception {
        boolean bol = this.sysClassroomService.addClassroom(sysClassroomInfo);
        return bol;
    }

    @RequestMapping(value = "/doModClassroom.infc")
    @ResponseBody
    public Object doModClassroom(SysClassroom sysClassroomInfo,VUserInfo vUserInfo)
            throws Exception {
        boolean bol = this.sysClassroomService.modClassroom(sysClassroomInfo, vUserInfo.getId());
        return bol;
    }

    @RequestMapping(value = "/initEdit.do")
    public ModelAndView initEdit(String id){
        ModelAndView mv = new ModelAndView("sys/classroom/classroomModForm");
        SysClassroom sysClassroomInfo = this.sysClassroomService.getClassroomInfoById(id);
        mv.addObject("classroomInfo",sysClassroomInfo);
        return mv;
    }

}
