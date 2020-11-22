package com.cloud9.biz.controllers;


import com.cloud9.biz.models.SysMajor;
import com.cloud9.biz.models.SysDictItem;
import com.cloud9.biz.models.SysSubjectInfo;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.services.SysMajorService;
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
/**
 * Created by zl on 2017/11/13.
 */
@Controller
@RequestMapping(value = "/major")
public class SysMajorController extends BaseController {
    @Autowired
    private SysMajorService majorService;

    @RequestMapping(value = "/getFatherMajorItems.infc")
    @ResponseBody
    public Object getFatherMajorItems(String type) throws BizException {
        List<SysDictItem> resList = null;
        String FatherMajorType="1";
        resList = this.majorService.getFatherMajorItems(FatherMajorType);
        return resList;
    }

    @RequestMapping(value = "/doGetMajorPageData.infc")
    @ResponseBody
    public Object doGetMajorPageData(PageBean pageBean, WebRequest request)
            throws Exception {
        pageBean = this.majorService.getMajorPageData(pageBean);
        return pageBean;
    }

    @RequestMapping(value = "/doAddMajor.infc")
    @ResponseBody
    public Object doAddMajor(SysMajor sysMajorInfo,VUserInfo userInfo) throws Exception {
        boolean bol =true;

        sysMajorInfo.setId(BizConstants.generatorPid());
        this.majorService.addMajorInfo(sysMajorInfo);
        return bol;
    }

    @RequestMapping(value = "/doModMajorInfo.infc")
    @ResponseBody
    public Object doModMajorInfo(VUserInfo userInfo,SysMajor sysMajorInfo) throws Exception {
        boolean bol = true;
        String majorInfo = sysMajorInfo.getId();
        if(majorInfo == null || "".equals(majorInfo)){
            throw new BizException("修改目标缺失!");
        }
        if (!this.majorService.modifyMajorInfo(sysMajorInfo)) {
            bol = false;
        }
        return bol;
    }

    @RequestMapping(value = "/initEdit.do")
    public ModelAndView initEdit(String id){
        ModelAndView mv = new ModelAndView("sys/major/majorModForm");
        SysMajor sysMajorInfo = this.majorService.getMajorById(id);
        mv.addObject("majorInfo",sysMajorInfo);
        return mv;
    }

}
