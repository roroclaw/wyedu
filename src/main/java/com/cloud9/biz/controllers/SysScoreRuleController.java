package com.cloud9.biz.controllers;

import com.cloud9.biz.dao.mybatis.SysSubjectInfoMapper;
import com.cloud9.biz.dao.mybatis.SysTeacherInfoMapper;
import com.cloud9.biz.models.SysScoresRuleConfig;
import com.cloud9.biz.models.SysTchScoresRuleConf;
import com.cloud9.biz.models.SysTeacherInfo;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.services.SysScoreRuleService;
import com.cloud9.biz.util.BizConstants;
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

import java.util.Date;
import java.util.List;

/**
 * Created by roroclaw on 2017/11/8.
 */
@Controller
@RequestMapping(value = "/sysScoreRule")
public class SysScoreRuleController extends BaseController {

     private static Logger logger = LoggerFactory.getLogger(SysScoreRuleController.class);

     @Autowired
     private SysScoreRuleService sysScoreRuleService;

     @Autowired
     private SysSubjectInfoMapper sysSubjectInfoMapper;

    @Autowired
    private SysTeacherInfoMapper sysTeacherInfoMapper;

     @RequestMapping(value = "/scoreRuleConfig.do")
     public ModelAndView initRuleEdit(){
         ModelAndView mv = new ModelAndView();
         mv.setViewName("sys/scoreRule/scoreRuleConfig");
         return mv;
     }

     @RequestMapping(value = "/tchScoreRuleConfig.do")
     public ModelAndView initTchRuleEdit(){
         ModelAndView mv = new ModelAndView();
         mv.setViewName("sys/scoreRule/tchScoreRuleConfig");
         return mv;
     }

     @RequestMapping(value = "/iniTchRuleEdit.do")
     public ModelAndView iniTchRuleEdit(String ruleId){
         ModelAndView mv = new ModelAndView();
         //获取分数配置信息
         SysTchScoresRuleConf tchScoresRuleConf = this.sysScoreRuleService.getTchScoreRuleById(ruleId);
         mv.addObject("tchScoresRuleConf",tchScoresRuleConf);
         mv.setViewName("sys/scoreRule/tchScoreRuleMod");
         return mv;
     }

     @RequestMapping(value = "/initEdit.do")
     public ModelAndView initEdit(String ruleId){
         ModelAndView mv = new ModelAndView();
         mv.setViewName("sys/scoreRule/scoreRuleMod");
         //获取分数配置信息
         SysScoresRuleConfig scoresRuleConfig = this.sysScoreRuleService.getScoreRuleById(ruleId);
//         if(scoresRuleConfig == null){
//             SysSubjectInfo sysSubjectInfo = this.sysSubjectInfoMapper.selectByPrimaryKey(subjectId);
//             scoresRuleConfig = new SysScoresRuleConfig();
//             scoresRuleConfig.setCreateTime(new Date());
//             scoresRuleConfig.setCreator(vUserInfo.getId());
//             scoresRuleConfig.setSubjectId(sysSubjectInfo.getId());
//             scoresRuleConfig.setSubjectName(sysSubjectInfo.getName());
//             sysScoreRuleService.addScoreRule(scoresRuleConfig);
//         }
         mv.addObject("scoresRuleConfig",scoresRuleConfig);
         return mv;
     }

     @RequestMapping(value = "/modScoreRule.infc")
     @ResponseBody
     public Object modScoreRule(SysScoresRuleConfig sysScoresRuleConfig,VUserInfo vUserInfo){
         boolean bol = false;
         sysScoresRuleConfig.setUpdater(vUserInfo.getId());
         sysScoresRuleConfig.setUpdateTime(new Date());
         bol = this.sysScoreRuleService.modScoreRule(sysScoresRuleConfig);
         return bol;
     }

     @RequestMapping(value = "/modTchScoreRule.infc")
     @ResponseBody
     public Object modTchScoreRule(SysTchScoresRuleConf tchScoresRuleConf,VUserInfo vUserInfo){
         boolean bol = false;
         tchScoresRuleConf.setUpdater(vUserInfo.getId());
         tchScoresRuleConf.setUpdateTime(new Date());
         bol = this.sysScoreRuleService.modTchScoreRule(tchScoresRuleConf);
         return bol;
     }

    @RequestMapping(value = "/addScoreRule.infc")
     @ResponseBody
     public Object addScoreRule(SysScoresRuleConfig sysScoresRuleConfig,VUserInfo vUserInfo){
         boolean bol = false;
         sysScoresRuleConfig.setCreator(vUserInfo.getId());
         sysScoresRuleConfig.setCreateTime(new Date());
         this.sysScoreRuleService.addScoreRule(sysScoresRuleConfig);
         return true;
     }

    @RequestMapping(value = "/addTchScoreRule.infc")
     @ResponseBody
     public Object addTchScoreRule(SysTchScoresRuleConf tchScoresRuleConf, VUserInfo vUserInfo){
         boolean bol = false;
         String userId = vUserInfo.getId();
        SysTeacherInfo sysTeacherInfo = this.sysTeacherInfoMapper.selectTeacherInfoByUserId(userId);
        if(sysTeacherInfo == null){
            throw new BizException("当前非教师用户,不可使用此功能!");
        }
        tchScoresRuleConf.setTeacherId(sysTeacherInfo.getId());
        tchScoresRuleConf.setCreator(userId);
        tchScoresRuleConf.setCreateTime(new Date());
         this.sysScoreRuleService.addTchScoreRule(tchScoresRuleConf);
         return true;
     }

    @RequestMapping(value = "/doDelScoreRuleById.infc")
     @ResponseBody
     public Object doDelScoreRuleById(String id){
         boolean bol = false;
         this.sysScoreRuleService.delScoreRuleById(id);
         return true;
     }

     @RequestMapping(value = "/doScoreRulePageData.infc")
     @ResponseBody
     public Object doScoreRulePageData(PageBean pageBean, WebRequest request){
         pageBean = this.sysScoreRuleService.getScoreRulePageData(pageBean);
         return pageBean;
     }

     @RequestMapping(value = "/doTchScoreRulePageData.infc")
     @ResponseBody
     public Object doTchScoreRulePageData(PageBean pageBean, WebRequest request,VUserInfo vUserInfo){
         pageBean = this.sysScoreRuleService.getTchScoreRulePageData(pageBean,vUserInfo.getId());
         return pageBean;
     }

     @RequestMapping(value = "/calSocresBySubjectId.infc")
     @ResponseBody
     public Object calSocresBySubjectId(VUserInfo vUserInfo,String ruleId,String schoolYear,String term){
         this.sysScoreRuleService.calSocresByRuleId(ruleId,vUserInfo.getId(),schoolYear,term);
         return true;
     }

     @RequestMapping(value = "/calTchSocresByRuleId.infc")
     @ResponseBody
     public Object calTchSocresByRuleId(VUserInfo vUserInfo,String ruleId, String openCourseId,String scoreType){
         this.sysScoreRuleService.calTchSocresByRuleId(ruleId,vUserInfo.getId(),openCourseId,scoreType);
         return true;
     }

}
