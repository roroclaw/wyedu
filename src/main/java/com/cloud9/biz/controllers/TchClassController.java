package com.cloud9.biz.controllers;

import com.cloud9.biz.models.SysDictItem;
import com.cloud9.biz.models.SysTeacherInfo;
import com.cloud9.biz.models.TchClassInfo;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.services.CommonService;
import com.cloud9.biz.services.RoleService;
import com.cloud9.biz.services.SysTeacherService;
import com.cloud9.biz.services.TchClassService;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dxz on 2017/8/13.
 */
@Controller
@RequestMapping(value = "/class")
public class TchClassController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(TchClassController.class);

    @Autowired
    private TchClassService tchClassService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private SysTeacherService sysTeacherService;

    @RequestMapping(value = "/doGetClassPageData.infc")
    @ResponseBody
    public Object doGetClassPageData(PageBean pageBean, WebRequest request)
            throws Exception {
        pageBean = this.tchClassService.getClassPageData(pageBean);
        return pageBean;
    }

    @RequestMapping(value = "/doAddClass.infc")
    @ResponseBody
    public Object doAddClass(TchClassInfo tchClassInfo,VUserInfo vUserInfo)
            throws Exception {
        boolean bol = this.tchClassService.addClass(tchClassInfo,vUserInfo.getId());
        return bol;
    }

    @RequestMapping(value = "/doDelClassById.infc")
    @ResponseBody
    public Object doDelClassById(String id)
            throws Exception {
        boolean bol = this.tchClassService.delClassById(id);
        return bol;
    }

    @RequestMapping(value = "/doStopClassinfo.infc")
    @ResponseBody
    public Object doStopClassinfo(String id)
            throws Exception {
        boolean bol = this.tchClassService.stopClassinfo(id);
        return bol;
    }

    @RequestMapping(value = "/doActiveClassinfo.infc")
    @ResponseBody
    public Object doActiveClassinfo(String id) throws Exception {
        boolean bol = this.tchClassService.activeClassinfo(id);
        return bol;
    }

    @RequestMapping(value = "/initEdit.do")
    public ModelAndView initEdit(String id){
        ModelAndView mv = new ModelAndView("tch/class/classModForm");
        TchClassInfo tchClassInfo = this.tchClassService.getClassInfoById(id);
        mv.addObject("classInfo",tchClassInfo);
        return mv;
    }

    @RequestMapping(value = "/doModClass.infc")
    @ResponseBody
    public Object doModClass(TchClassInfo tchClassInfo,VUserInfo vUserInfo)
            throws Exception {
        boolean bol = this.tchClassService.modClass(tchClassInfo,vUserInfo.getId());
        return bol;
    }

    @RequestMapping(value = "/initIncreaseGrade.do")
    public ModelAndView initIncreaseGrade(String id){
        ModelAndView mv = new ModelAndView("tch/class/increaseGrade");
        TchClassInfo tchClassInfo = this.tchClassService.getClassInfoById(id);
        mv.addObject("classInfo",tchClassInfo);
        return mv;
    }

    @RequestMapping(value = "/doIncreaseGrade.infc")
    @ResponseBody
    public Object doIncreaseGrade(String classIDs ,VUserInfo vUserInfo)
            throws Exception {
        if(classIDs.equals("") || classIDs==null){
            throw new BizException("参数错误!");
        }
        boolean bol = this.tchClassService.increaseGrade(classIDs ,vUserInfo.getId());
        return bol;
    }

    /**
     * Created by zl.
     */
    @RequestMapping(value = "/getClassInfoItems.infc")
    @ResponseBody
    public Object getClassInfoItems(String grade) throws Exception {
        List<SysDictItem> ClassesInfosList = this.tchClassService.getClassItems(grade);
        return ClassesInfosList;
    }

    /**
     * 汇总成绩查询下拉.
     */
    @RequestMapping(value = "/getClassInfoItemsForPower.infc")
    @ResponseBody
    public Object getClassInfoItemsForPower(String grade,String schoolYear,String period,String term,VUserInfo vUserInfo) throws Exception {
        String teacherId="";
        List<SysDictItem> ClassesInfosList=new ArrayList<SysDictItem>();
        List<SysDictItem> tempClassesInfosList=new ArrayList<SysDictItem>();
        if(this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.ADMIN) || vUserInfo.getId().equals(BizConstants.SUPER_ADMIN_ID)
                || this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.XIAOWUHUI)){///是管理员、超级管理员、校务会成员
            tempClassesInfosList = this.tchClassService.getClassItemsForPower(grade, schoolYear, period, teacherId);
            ClassesInfosList.addAll(tempClassesInfosList);
        }
        if(this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.CLASSTEACHER)){/////班主任
            SysTeacherInfo sysTeacherInfo = this.sysTeacherService.getTeacherInfoByUserId(vUserInfo.getId());
            teacherId = sysTeacherInfo.getId();
            tempClassesInfosList = this.tchClassService.getClassItemsForPower(grade, schoolYear, period, teacherId);
            ClassesInfosList.addAll(tempClassesInfosList);
        }
        if(this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.TEACHER)){  ///////教师                                                                                            //////////////教师
            SysTeacherInfo sysTeacherInfo = this.sysTeacherService.getTeacherInfoByUserId(vUserInfo.getId());
            teacherId=sysTeacherInfo.getId();
            tempClassesInfosList = this.tchClassService.getClassItemsForTeachPower (grade, schoolYear, period, teacherId,term);
            ClassesInfosList.addAll(tempClassesInfosList);
        }
        return ClassesInfosList;
    }


    /**
     * 汇总成绩查询下拉.
     */
    @RequestMapping(value = "/getClassInfoItemsBySchoolYesr.infc")
    @ResponseBody
    public Object getClassInfoItemsBySchoolYesr(String schoolYear,String term,VUserInfo vUserInfo) throws Exception {
        String teacherId = "";
        List<SysDictItem> ClassesItemList = new ArrayList<SysDictItem>();
        List<TchClassInfo> ClassesInfosList = new ArrayList<TchClassInfo>();
        List<TchClassInfo> tempClassesInfosList = new ArrayList<TchClassInfo>();
        if(!schoolYear.equals("") && schoolYear!=null) {
            if (this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.ADMIN) || vUserInfo.getId().equals(BizConstants.SUPER_ADMIN_ID)
                    || this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.XIAOWUHUI)) {///是管理员、超级管理员、校务会成员
                tempClassesInfosList = this.tchClassService.getClassItemsBySchoolYear(schoolYear, teacherId);
                ClassesInfosList.addAll(tempClassesInfosList);
            }
            if (this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.CLASSTEACHER)) {/////班主任
                SysTeacherInfo sysTeacherInfo = this.sysTeacherService.getTeacherInfoByUserId(vUserInfo.getId());
                teacherId = sysTeacherInfo.getId();
                tempClassesInfosList = this.tchClassService.getClassItemsBySchoolYear(schoolYear, teacherId);
                ClassesInfosList.addAll(tempClassesInfosList);
            }
            if (this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.TEACHER)) {  ///////教师                                                                                            //////////////教师
                SysTeacherInfo sysTeacherInfo = this.sysTeacherService.getTeacherInfoByUserId(vUserInfo.getId());
                teacherId = sysTeacherInfo.getId();
                tempClassesInfosList = this.tchClassService.getClassItemsBySchoolYearForTeacher(schoolYear, teacherId, term);
                ClassesInfosList.addAll(tempClassesInfosList);
            }
        }else{
            throw new BizException("参数错误!");
        }
        for( int i = 0 ; i < ClassesInfosList.size() ; i++) {
            TchClassInfo tempTchClassInfo=ClassesInfosList.get(i);

           // int period=Integer.parseInt(tempTchClassInfo.getPeriod());
           // int graduateYear=Integer.parseInt(tempTchClassInfo.getGraduateYear());
           // int tempGrade=period*6-(graduateYear-Integer.parseInt(schoolYear))+1;
           // String tempClassName=BizConstants.GRADE_NAME[tempGrade];
            //String oldClassName=tempTchClassInfo.getName();
           // tempTchClassInfo.setName(tempGradeName+oldGradeName.substring(oldGradeName.length()-4));
            tempTchClassInfo.setName(commonService.getPresentGradeName(tempTchClassInfo.getPeriod(),tempTchClassInfo.getGraduateYear(),schoolYear,tempTchClassInfo.getName()));
            SysDictItem tempSysDictItem=new SysDictItem();
            tempSysDictItem.setCode(tempTchClassInfo.getId());
            tempSysDictItem.setText(tempTchClassInfo.getName());
            ClassesItemList.add(tempSysDictItem);
        }


        return ClassesItemList;
    }
}
