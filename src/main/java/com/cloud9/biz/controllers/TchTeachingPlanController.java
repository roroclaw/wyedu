package com.cloud9.biz.controllers;

import com.cloud9.biz.models.TchTchplanSubjectRel;
import com.cloud9.biz.models.vo.VTeachPlan;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.models.TchTeachingPlan;
import com.cloud9.biz.services.TchPlanSubjectRelService;
import com.cloud9.biz.services.TchTeachingPlanService;
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
 * Created by zl on 2017/7/3.
 */
@Controller
@RequestMapping(value = "/teachingPlan")
public class TchTeachingPlanController extends BaseController  {
    @Autowired
    private TchTeachingPlanService tchTeachingPlanService;

    @Autowired
    private TchPlanSubjectRelService tchPlanSubjectRelService;


    @RequestMapping(value = "/doGetTeachingPlanPageData.infc")
    @ResponseBody
    public Object doGetTeachingPlanPageData(PageBean pageBean, WebRequest request)
            throws Exception {
        pageBean = this.tchTeachingPlanService.getTeachingPlanPageData(pageBean);
        return pageBean;
    }

    @RequestMapping(value = "/doAddTeachingPlan.infc")
    @ResponseBody
    public Object doAddTeachingPlan(TchTeachingPlan tchTeachingPlanInfo,VUserInfo userInfo) throws Exception {
        boolean bol =true;
        this.tchTeachingPlanService.addTeachingPlanInfo(tchTeachingPlanInfo);
        return bol;
    }

    @RequestMapping(value = "/doModTeachingPlan.infc")
    @ResponseBody
    public Object doModTeachingPlan(VUserInfo userInfo,TchTeachingPlan tchTeachingPlanInfo) throws Exception {
        boolean bol = true;
        String subjectId = tchTeachingPlanInfo.getId();
        if(subjectId == null || "".equals(subjectId)){
            throw new BizException("修改目标缺失!");
        }
        if (!this.tchTeachingPlanService.modifyTeachingPlanInfo(tchTeachingPlanInfo)) {
            bol = false;
        }
        return bol;
    }

    /**
     * 获取教学计划下拉框
     * @param subMajorId
     * @param period
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doGetTeachingPlanItems.infc")
    @ResponseBody
    public Object doGetTeachingPlanItems(String subMajorId,String period) throws Exception {
        List<VTeachPlan> planList = this.tchTeachingPlanService.getTeachingPlanItems(subMajorId,period);
        return planList;
    }

    @RequestMapping(value = "/initEdit.do")
    public ModelAndView initEdit(String id){
        ModelAndView mv = new ModelAndView("tch/teachingPlan/teachingPlanModForm");
        //获取计划信息
        TchTeachingPlan tchTeachingPlan = this.tchTeachingPlanService.getTeachingPlanById(id);
        mv.addObject("teachingPlanInfo",tchTeachingPlan);
        return mv;
    }

    @RequestMapping(value = "/initEditForTchplanSubjectRel.do")
    public ModelAndView initEditForTchplanSubjectRel(String id){
        ModelAndView mv = new ModelAndView("tch/tchplanSubjectRel/tchplanSubjectRelModForm");
        //获取计划信息
        TchTeachingPlan tchTeachingPlan = this.tchTeachingPlanService.getTeachingPlanById(id);
        mv.addObject("teachingPlanInfo",tchTeachingPlan);
        return mv;
    }

    @RequestMapping(value = "/initDetail.do")
    public ModelAndView initDetail(String id){
        ModelAndView mv = new ModelAndView("tch/teachingPlan/tchTeachingPlanDetail");
        //获取计划信息
        TchTeachingPlan tchTeachingPlan = this.tchTeachingPlanService.getTeachingPlanById(id);
        mv.addObject("teachingPlanInfo",tchTeachingPlan);
        return mv;
    }
}
