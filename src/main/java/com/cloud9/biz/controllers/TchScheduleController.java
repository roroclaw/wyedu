package com.cloud9.biz.controllers;

import com.cloud9.biz.models.*;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.services.*;
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

import java.util.*;

/**
 * Created by zl on 2017/9/8.
 */
@Controller
@RequestMapping(value = "/schedule")
public class TchScheduleController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(TchClassController.class);

    @Autowired
    private TchScheduleService tchScheduleService;

    @Autowired
    private TchCourseOpenService courseOpenService;

    @Autowired
    private ArcStudentService arcStudentService;

    @Autowired
    private SysTeacherService sysTeacherService;

    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/doGetSchedulePageData.infc")
    @ResponseBody
    public Object doGetSchedulePageData(PageBean pageBean, WebRequest request)
            throws Exception {
        pageBean = this.tchScheduleService.getSchedulePageData(pageBean);
        return pageBean;
    }

    @RequestMapping(value = "/doGetScheduleById.infc")
    @ResponseBody
    public Object doGetScheduleById(String scheduleId, WebRequest request)
            throws Exception {
        TchSchedule tchScheduleInfo = this.tchScheduleService.getScheduleById(scheduleId);
        return tchScheduleInfo;
    }


    @RequestMapping(value = "/doAddSchedule.infc")
    @ResponseBody
    public Object doAddSchedule(TchSchedule tchScheduleInfo,VUserInfo vUserInfo)
            throws Exception {
        boolean bol = this.tchScheduleService.addSchedule(tchScheduleInfo);
        return bol;
    }

    @RequestMapping(value = "/doModScheduleInfo.infc")
    @ResponseBody
    public Object doModScheduleInfo(VUserInfo userInfo,TchSchedule tchScheduleInfo) throws Exception {
        boolean bol = true;
        String ScheduleId = tchScheduleInfo.getId();
        if(ScheduleId == null || "".equals(ScheduleId)){
            throw new BizException("修改目标缺失!");
        }
        TchScheduleCourseOpen tchScheduleCourseOpenRel=new TchScheduleCourseOpen();
        tchScheduleCourseOpenRel.setScheduleId(ScheduleId);
        List<TchScheduleCourseOpen> resList = this.tchScheduleService.getScheduleCourseOpenRelList(tchScheduleCourseOpenRel);
        if(resList.size()>0){///////检验数据依赖
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_DATA_REL_EXIST);///存在数据依赖，无法完成操作！
        }
        if (!this.tchScheduleService.modifyScheduleInfo(tchScheduleInfo)) {
            bol = false;
        }
        return bol;
    }


    @RequestMapping(value = "/initEdit.do")
    public ModelAndView initEdit(String id){
        ModelAndView mv = new ModelAndView("tch/schedule/scheduleModForm");
        //获取学籍信息
        TchSchedule tchScheduleInfo = this.tchScheduleService.getScheduleById(id);
        mv.addObject("scheduleInfo",tchScheduleInfo);
        return mv;
    }

    @RequestMapping(value = "/getScheduleSectionList.infc")
    @ResponseBody
    public Object getScheduleSectionList() throws BizException {
        List<TchScheduleSection> resList = this.tchScheduleService.getScheduleSectionList();
        return resList;
    }

    @RequestMapping(value = "/getScheduleCourseOpenRelList.infc")
    @ResponseBody
    public Object getScheduleCourseOpenRelList(TchScheduleCourseOpen tchScheduleCourseOpenRel) throws BizException {
        List<TchScheduleCourseOpen> resList = this.tchScheduleService.getScheduleCourseOpenRelList(tchScheduleCourseOpenRel);
        return resList;
    }

    @RequestMapping(value = "/getScheduleCourseOpenRelListShowForStu.infc")
    @ResponseBody
    public Object getScheduleCourseOpenRelListShowForStu(String schoolYear,String term,VUserInfo vUserInfo) throws BizException {
       /* List<TchScheduleCourseOpen> resList=new ArrayList<TchScheduleCourseOpen>();
        if(this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.ADMIN) || vUserInfo.getId().equals(BizConstants.SUPER_ADMIN_ID)){////管理员
        }else if(this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.TEACHER)){//////教师
        }else if(this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.CLASSTEACHER)){ //班主任
        }else if(this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.STUDENT)){//学生
        }*/
        String orderParam=" order by  a.PERIOD_SEQ,a.WEEK_SEQ";     ////学生课表
        ArcStudentInfo arcStudentInfo=this.arcStudentService.getStudentInfoByUserId(vUserInfo.getId());
        List<TchScheduleCourseOpen> resList = this.tchScheduleService.getScheduleCourseOpenRelListForShow(arcStudentInfo.getId(),schoolYear,term,"",orderParam);
        return resList;
    }

    @RequestMapping(value = "/getScheduleCourseOpenRelListShowForTeacher.infc")
    @ResponseBody
    public Object getScheduleCourseOpenRelListShowForTeacher(String schoolYear,String term,VUserInfo vUserInfo) throws BizException {
        String orderParam=" order by  a.PERIOD_SEQ,a.WEEK_SEQ";     ////教师课表
        SysTeacherInfo sysTeacherInfo = this.sysTeacherService.getTeacherInfoByUserId(vUserInfo.getId());
        List<TchScheduleCourseOpen> resList = this.tchScheduleService.getScheduleCourseOpenRelListForShow("",schoolYear,term,sysTeacherInfo.getId(),orderParam);
        return resList;
    }

    @RequestMapping(value = "/getScheduleCourseOpenRelListShowForClass.infc")
    @ResponseBody
    public Object getScheduleCourseOpenRelListShowForClass(String schoolYear,String term,String classId,VUserInfo vUserInfo) throws BizException {
        String orderParam=" order by  a.PERIOD_SEQ,a.WEEK_SEQ";     ////班级课表
        SysTeacherInfo sysTeacherInfo = this.sysTeacherService.getTeacherInfoByUserId(vUserInfo.getId());
        List<TchScheduleCourseOpen> resList = this.tchScheduleService.getScheduleCourseOpenRelListForClassShow(schoolYear,term,classId, orderParam);
        return resList;
    }
    @RequestMapping(value = "/getScheduleCourseOpenRelListShowForCollecting.infc")
    @ResponseBody
    public Object getScheduleCourseOpenRelListShowForCollecting(String schoolYear,String term,String period,VUserInfo vUserInfo) throws BizException {
        if(!this.commonService.isTheRoleForCheck(vUserInfo.getId(), BizConstants.SYS_ROLE_ID.ADMIN) && !vUserInfo.getId().equals(BizConstants.SUPER_ADMIN_ID)){////管理员
            throw new BizException("无此权限!");
        }
        //String orderParam=" order by  a.WEEK_SEQ，a.PERIOD_SEQ,";     ////总课表
        List<TchScheduleCourseOpen> resList = this.tchScheduleService.getScheduleCourseOpenRelListForCollecting(schoolYear, term, period);
        return resList;
    }

    /**
     * 汇总成绩查询下拉.
     */
    @RequestMapping(value = "/getClassInfoForCollectingShow.infc")
    @ResponseBody
    public Object getClassInfoForCollectingShow(String schoolYear,String term,String period,VUserInfo vUserInfo) throws BizException {
        String graduateYears="";
        int num=0;
        int schoolYearInt=Integer.parseInt(schoolYear);
        if(!period.equals("1")){
            num=  Integer.parseInt(BizConstants.periodGradeNum(period))-Integer.parseInt(BizConstants.periodGradeNum(String.valueOf(Integer.parseInt(period)-1)));///学段年级总数
        }else{
            num=  Integer.parseInt(BizConstants.periodGradeNum(period));///学段年级总数
        }
        for(int i=0;i<num;i++){
            if(i==0){
                graduateYears= "'"+String.valueOf(schoolYearInt+1+i)+"'";
            }else{
                graduateYears=graduateYears+",'"+ String.valueOf(schoolYearInt+1+i)+"'";
            }
        }
        List<TchScheduleCourseOpen> resList = this.tchScheduleService.getClassInfoForCollectingShow(graduateYears, term, period);
        return resList;
    }



    @RequestMapping(value = "/doSetSchedule.infc")
    @ResponseBody
    public Object doSetSchedule(WebRequest request,VUserInfo vUserInfo)
            throws Exception {
        boolean bol =true;
        String scheduleId=request.getParameter("scheduleId");
        TchSchedule tchScheduleInfo=this.tchScheduleService.getScheduleById(scheduleId);
        if(tchScheduleInfo==null){
            throw new BizException("参数出错!");
        }
        Map paramMap=request.getParameterMap();
        List<Map<String,Object>> returnErrorInfo = new ArrayList<Map<String,Object>>();
        List<TchScheduleCourseOpen> paramListSelected = new ArrayList<TchScheduleCourseOpen>();
        Set keSet=paramMap.entrySet();
        for(Iterator itr=keSet.iterator();itr.hasNext();){
            Map.Entry params=(Map.Entry)itr.next();
            Object paramkeyName=params.getKey();
            Object paramVal=params.getValue();
            String[] value=new String[1];
            if(paramVal instanceof String[]){
                value=(String[])paramVal;
            }else{
                value[0]=paramVal.toString();
            }
            String paramkeyNameStr=(String)paramkeyName;
           // System.out.println("-----------------doSetSchedule:222::" + paramkeyNameStr+"|||"+value[0]);
            String paramkeyNameStr_1=Character.toString(paramkeyNameStr.charAt(1));
           // System.out.println("-----------------doSetSchedule:222::" + paramkeyNameStr.charAt(0)+"|||"+paramkeyNameStr.charAt(1)+"|||"+paramkeyNameStr.charAt(2)+"|||");
           // System.out.println("-----------------doSetSchedule:333::" +  Character.isDigit(paramkeyNameStr.charAt(0))+"|||"+"_".equals(paramkeyNameStr.charAt(1))+"|||"+ Character.isDigit(paramkeyNameStr.charAt(2))
           //         +"|||"+paramkeyNameStr.equals("interfaceUrl")+"|||"+paramkeyNameStr.equals("accToken")+"|||");
            if ( Character.isDigit(paramkeyNameStr.charAt(0)) && paramkeyNameStr_1.equals("_") && Character.isDigit(paramkeyNameStr.charAt(2))
                    && !paramkeyNameStr.equals("interfaceUrl") && !paramkeyNameStr.equals("accToken")  && !paramkeyNameStr.equals("scheduleId")){
                int tempWeek;
                int tempPeriod;
                String tempClassRoomId="";

                    String[] arrayParam = paramkeyNameStr.split("_");
                    if(arrayParam.length!=3){
                        bol =false;
                        throw new BizException("参数出错!");
                    }
                    tempWeek=Integer.parseInt(arrayParam[0]);
                    tempPeriod=Integer.parseInt(arrayParam[1]);
                    tempClassRoomId=arrayParam[2];
                    if(value[0]!="" && !value[0].equals("")){
                        TchScheduleCourseOpen TempParamMapSelected = new TchScheduleCourseOpen();
                        TempParamMapSelected.setWeekSeq(tempWeek);
                        TempParamMapSelected.setPeriodSeq(tempPeriod);
                        TempParamMapSelected.setClassroomId(tempClassRoomId);
                        TempParamMapSelected.setCourseOpenId(value[0]);
                   // System.out.println("-----------------doSetSchedule:333::" + tempWeek+"|||"+tempPeriod+"|||");
                        paramListSelected.add(TempParamMapSelected);
                    }
            }

        }
        for (int i=0; i < paramListSelected.size();i++){
            TchScheduleCourseOpen TempScheduleCourseOpenMap=paramListSelected.get(i);
           // System.out.println("-----------------doSetSchedule:444::" + TempScheduleCourseOpenMap.getWeekSeq()+"|||"+TempScheduleCourseOpenMap.getPeriodSeq()+"|||");

            //////////////////////////////////////////////教师排重//同一学年的开课，跨年级教师比对
            Map tempErrorInfoMapTeac=new HashMap();
            TchScheduleCourseOpen ScheduleCourseOpenRelForCheck=new TchScheduleCourseOpen();
            ScheduleCourseOpenRelForCheck.setWeekSeq(TempScheduleCourseOpenMap.getWeekSeq());
            ScheduleCourseOpenRelForCheck.setPeriodSeq(TempScheduleCourseOpenMap.getPeriodSeq());
            ScheduleCourseOpenRelForCheck.setClassroomId(TempScheduleCourseOpenMap.getClassroomId());
            ScheduleCourseOpenRelForCheck.setCourseOpenId(TempScheduleCourseOpenMap.getCourseOpenId());
            ScheduleCourseOpenRelForCheck.setScheduleId(scheduleId);
            ScheduleCourseOpenRelForCheck.setPeriod(tchScheduleInfo.getPeriod());///////////////加入学段参数，不同学段分开排重
            List<TchScheduleCourseOpen> ForCheckResList_Tec = this.tchScheduleService.checkScheduleCourseOpenRelForTeacher(ScheduleCourseOpenRelForCheck);
            if(ForCheckResList_Tec.size()>0){
                for (int l=0; l < ForCheckResList_Tec.size();l++){
                    tempErrorInfoMapTeac.put("tea_"+TempScheduleCourseOpenMap.getWeekSeq()+"_"+TempScheduleCourseOpenMap.getPeriodSeq()+"_"+TempScheduleCourseOpenMap.getClassroomId()
                            ,"教师"+ForCheckResList_Tec.get(l).getTeacherName()+"冲突。");
                    returnErrorInfo.add(tempErrorInfoMapTeac);
                }

                bol=false;
            }
            //////////////////////////////////////////////学生排重
            Map tempErrorInfoMapStu=new HashMap();
            TchStuCourseOpenRel paramStuCourseOpenRel=new TchStuCourseOpenRel();
            paramStuCourseOpenRel.setCourseOpenId(TempScheduleCourseOpenMap.getCourseOpenId());
            List<TchStuCourseOpenRel> tchStuCourseOpenRelList=this.courseOpenService.getCourseOpenStudentsList(paramStuCourseOpenRel);/////排课表相关学生
            int conflictTotal=0;
            for (int j=0; j < tchStuCourseOpenRelList.size();j++){///////////////////////////////检验每个学生是否排课表有冲突
                ScheduleCourseOpenRelForCheck.setStuId(tchStuCourseOpenRelList.get(j).getStuId());
                int conflictNum=this.tchScheduleService.checkScheduleCourseOpenRelForStu(ScheduleCourseOpenRelForCheck);
                if(conflictNum>0){
                    conflictTotal+=1;
                    bol=false;
                }
            }
            if(conflictTotal>0){
                tempErrorInfoMapStu.put("stu_"+TempScheduleCourseOpenMap.getWeekSeq()+"_"+TempScheduleCourseOpenMap.getPeriodSeq()+"_"+TempScheduleCourseOpenMap.getClassroomId()
                         ,conflictTotal+"名学生编排冲突。");
                 returnErrorInfo.add(tempErrorInfoMapStu);
            }

            if(bol){
                //////////////////////////////////////////////以课表、周期、节次、教室为参数，清除排课表数据TchScheduleCourseOpen
                TchScheduleCourseOpen targetScheduleCourseOpenRel=new TchScheduleCourseOpen();
                targetScheduleCourseOpenRel.setWeekSeq(TempScheduleCourseOpenMap.getWeekSeq());
                targetScheduleCourseOpenRel.setClassroomId(TempScheduleCourseOpenMap.getClassroomId());
                targetScheduleCourseOpenRel.setPeriodSeq(TempScheduleCourseOpenMap.getPeriodSeq());
                targetScheduleCourseOpenRel.setScheduleId(scheduleId);
                this.tchScheduleService.cleanScheduleCourseOpenRelInfo(targetScheduleCourseOpenRel);
                /////////////////////////////////////////////////添加新数据
                TchScheduleCourseOpen newScheduleCourseOpenRel=targetScheduleCourseOpenRel;
                String tempCourseOpenId=TempScheduleCourseOpenMap.getCourseOpenId();
                newScheduleCourseOpenRel.setCourseOpenId(tempCourseOpenId);
                if(tempCourseOpenId!=null && !tempCourseOpenId.equals("")){
                    if(!this.tchScheduleService.addScheduleCourseOpenRelInfo(targetScheduleCourseOpenRel)){
                        bol =false;
                        throw new BizException("新增数据出错!");
                    }
                }
            }
        }
        Map bolInfoMap=new HashMap();
        bolInfoMap.put("bol",bol);
        returnErrorInfo.add(0,bolInfoMap);
        return returnErrorInfo;
    }
}
