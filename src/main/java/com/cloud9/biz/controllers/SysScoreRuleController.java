package com.cloud9.biz.controllers;

import com.cloud9.biz.dao.mybatis.SysSubjectInfoMapper;
import com.cloud9.biz.models.SysScoresRuleConfig;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.services.SysScoreRuleService;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

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

     @RequestMapping(value = "/scoreRuleConfig.do")
     public ModelAndView initRuleEdit(){
         ModelAndView mv = new ModelAndView();
         mv.setViewName("sys/scoreRule/scoreRuleConfig");
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

    @RequestMapping(value = "/addScoreRule.infc")
     @ResponseBody
     public Object addScoreRule(SysScoresRuleConfig sysScoresRuleConfig,VUserInfo vUserInfo){
         boolean bol = false;
         sysScoresRuleConfig.setCreator(vUserInfo.getId());
         sysScoresRuleConfig.setCreateTime(new Date());
         this.sysScoreRuleService.addScoreRule(sysScoresRuleConfig);
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

     @RequestMapping(value = "/calSocresBySubjectId.infc")
     @ResponseBody
     public Object calSocresBySubjectId(VUserInfo vUserInfo,String ruleId,String schoolYear,String term){
         this.sysScoreRuleService.calSocresByRuleId(ruleId,vUserInfo.getId(),schoolYear,term);
         return true;
     }
}
