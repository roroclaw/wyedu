package com.cloud9.biz.controllers;


import com.cloud9.biz.models.SysDictItem;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.models.SysSubjectInfo;
import com.cloud9.biz.services.SysSubjectService;
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

/**
 * Created by zl on 2017/6/28.
 */

@Controller
@RequestMapping(value = "/subject")
public class SysSubjectController extends BaseController {
    @Autowired
    private SysSubjectService subjectService;

    @RequestMapping(value = "/doGetSubjectPageData.infc")
    @ResponseBody
    public Object doGetSubjectPageData(PageBean pageBean, WebRequest request)
            throws Exception {
        pageBean = this.subjectService.getSubjectPageData(pageBean);
        return pageBean;
    }
    @RequestMapping(value = "/doGetSubjectListsByParam.infc")
    @ResponseBody
    public Object doGetSubjectListsByParam(SysSubjectInfo sysSubjectInfo, WebRequest request, VUserInfo vUserInfo) throws BizException {

        List<SysSubjectInfo> SubjectsInfosList = this.subjectService.getSubjectsListByParam(sysSubjectInfo);

        return SubjectsInfosList;
    }



    @RequestMapping(value = "/doAddSubject.infc")
    @ResponseBody
    public Object doAddSubject(SysSubjectInfo sysSubjectInfo,VUserInfo userInfo) throws Exception {
        boolean bol =true;
        //验证账号是否存在
        List<SysSubjectInfo> tempSubjectsInfosList = this.subjectService.selectSubjectByParam(sysSubjectInfo);
        //if (tempSubjectsInfosList.size()>0)
            //throw new BizException("科目已存在");
        sysSubjectInfo.setId(BizConstants.ID_PRECODE.SYS_BASEINFO_ID_PRECODE + BizConstants.generatorPid());
        this.subjectService.addSubjectInfo(sysSubjectInfo);
        return bol;
    }

    @RequestMapping(value = "/doModSubjectInfo.infc")
    @ResponseBody
    public Object doModSubjectInfo(VUserInfo userInfo,SysSubjectInfo sysSubjectInfo) throws Exception {
        boolean bol = true;
        String subjectId = sysSubjectInfo.getId();
        if(subjectId == null || "".equals(subjectId)){
            throw new BizException("修改目标缺失!");
        }
        if (!this.subjectService.modifySubjectInfo(sysSubjectInfo)) {
            bol = false;
        }
        return bol;
    }

    @RequestMapping(value = "/getSubjectInfoItems.infc")
    @ResponseBody
    public Object getSubjectInfoItems(String type,String status,String name) throws Exception {
        List<SysDictItem> subjectList = this.subjectService.getSubjectItems(type,status,name);
        return subjectList;
    }

    @RequestMapping(value = "/initEdit.do")
    public ModelAndView initEdit(String id){
        ModelAndView mv = new ModelAndView("sys/subject/subjectModForm");
        //获取学籍信息
        SysSubjectInfo sysSubject = this.subjectService.getSubjectById(id);
        mv.addObject("subjectInfo",sysSubject);
        return mv;
    }
}
