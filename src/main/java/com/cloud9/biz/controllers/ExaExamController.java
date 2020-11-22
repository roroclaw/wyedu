package com.cloud9.biz.controllers;
import com.cloud9.biz.models.*;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.services.ExmExamRoomService;
import com.cloud9.biz.services.ExmExamService;
import com.cloud9.biz.services.ScoExamScoresService;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.handler.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by zl on 2017/9/20.
 */
@Controller
@RequestMapping(value = "/exam")
public class ExaExamController {

    @Autowired
    private ExmExamService exmExamService;

    @Autowired
    private ExmExamRoomService exmExamRoomService;

    @Autowired
    private ScoExamScoresService examScoresService;

    @RequestMapping(value = "/doGetExamPlanPageData.infc")
    @ResponseBody
    public Object doGetExamPlanPageData(PageBean pageBean, WebRequest request)
            throws Exception {
        pageBean = this.exmExamService.getExamPlanPageData(pageBean);
        return pageBean;
    }

    @RequestMapping(value = "/getExamPlanItems.infc")
    @ResponseBody
    public Object getExamPlanItems(String type,String status) throws BizException {
        List<SysDictItem> resList = null;
        resList = this.exmExamService.getExamPlanItems(type,status);
        return resList;
    }

    @RequestMapping(value = "/getExamPlanItemsBySchoolYear.infc")
    @ResponseBody
    public Object getExamPlanItemsBySchoolYear(String schoolYear) throws BizException {
        List<SysDictItem> resList = null;
        resList = this.exmExamService.getExamPlanItemsBySchoolYear(schoolYear);
        return resList;
    }

    @RequestMapping(value = "/getExamItems.infc")
    @ResponseBody
    public Object getExamItems(String examPlanId,String status) throws BizException {
        List<SysDictItem> resList = null;
        resList = this.exmExamService.getExamItems(examPlanId, status);
        return resList;
    }


    @RequestMapping(value = "/doGetExamPageData.infc")
    @ResponseBody
    public Object doGetExamPageData(PageBean pageBean, WebRequest request)
            throws Exception {
        pageBean = this.exmExamService.getExamPageData(pageBean);
        return pageBean;
    }


    @RequestMapping(value = "/doAddExamPlan.infc")
    @ResponseBody
    public Object doAddExamPlan(ExaExamPlan exaExamPlanInfo,VUserInfo vUserInfo)
            throws Exception {
        boolean bol = this.exmExamService.addExamPlan(exaExamPlanInfo,vUserInfo.getId());
        return bol;
    }

    @RequestMapping(value = "/doAddExamInfo.infc")
    @ResponseBody
    public Object doAddExamInfo(ExaExamInfo exaExamInfo,VUserInfo vUserInfo)
            throws Exception {
        boolean bol = this.exmExamService.addExam(exaExamInfo,vUserInfo.getId());
        return bol;
    }


    @RequestMapping(value = "/doModExamPlan.infc")
    @ResponseBody
    public Object doModExamPlan(ExaExamPlan exaExamPlanInfo,VUserInfo vUserInfo)
            throws Exception {
        boolean bol = this.exmExamService.modExamPlan(exaExamPlanInfo, vUserInfo.getId());
        return bol;
    }


    @RequestMapping(value = "/doModExamInfo.infc")
    @ResponseBody
    public Object doModExamInfo(ExaExamInfo exaExamInfo,VUserInfo vUserInfo)
            throws Exception {
        if(exaExamInfo.getId()==null || exaExamInfo.getId().equals("")){
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_PARAM_ERROR);
        }
        boolean bol =false;
        exaExamInfo.setStatus(null);/////状态值不在此处修改
        ExaExamInfo exaExamInfoForCheck = this.exmExamService.getExamInfoById(exaExamInfo.getId());

        ExaExamRoom exaExamRoom = this.exmExamRoomService.getExamRoomInfoById(exaExamInfoForCheck.getId());
        String  exaExamPlanId=exaExamRoom.getExamPlanId();
        bol = this.exmExamService.cleanStuExamInfoByExamPlanId(exaExamPlanId);
        /*if(!exaExamInfo.getSubjectId().equals(exaExamInfoForCheck.getSubjectId())){ /////////需要修改考试科目
            ///////////修改考试科目前检查是否有关联数据
            ExaStuExamInfo exaStuExamInfo=new ExaStuExamInfo();
            exaStuExamInfo.setExamId(exaExamInfo.getId());
            List<ExaStuExamInfo> exaStuExamInfoListByExamId=this.exmExamService.selectExaStuByParam(exaStuExamInfo);
            if(exaStuExamInfoListByExamId.size()>0){ ////////////////考试已经被安排考生
                ExaStuExamInfo exaStuExamInfoToDel=new ExaStuExamInfo();
                exaStuExamInfoToDel.setExamId(exaExamInfo.getId());
                ScoExamScores scoExamScoresInfoToDel=new ScoExamScores();
                scoExamScoresInfoToDel.setExamId(exaExamInfo.getId());
                bol = this.exmExamService.cleanStuExamInfoByParam(exaStuExamInfoToDel);
                if(!this.exmExamService.cleanStuExamInfoByParam(exaStuExamInfoToDel) || //////删除相关准考数据
                   !this.examScoresService.cleanScoExamScoresInfoByParam(scoExamScoresInfoToDel)) {  //////删除相关成绩数据
                    throw new BizException(BizConstants.HTML_VAL.ERROR_MES_DATA_PROCESSING);
                }
            }else{
                bol =true;
            }
        }else{
            bol =true;
        }*/
        if(bol){
            bol = this.exmExamService.modExaminfo(exaExamInfo, vUserInfo.getId());
        }
        return bol;
    }

    @RequestMapping(value = "/doModExamInfoStatus.infc")
    @ResponseBody
    public Object doModExamInfoStatus(ExaExamInfo exaExamInfo,VUserInfo vUserInfo)
            throws Exception {
        ExaExamInfo exaExamInfoStatus=new ExaExamInfo();
        if(exaExamInfo.getStatus()!=null && !exaExamInfo.getStatus().equals("") && exaExamInfo.getId()!=null && !exaExamInfo.getId().equals("")){
            exaExamInfoStatus.setStatus(exaExamInfo.getStatus());
            exaExamInfoStatus.setId(exaExamInfo.getId());
        }else{
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_PARAM_ERROR);
        }
        if(!exaExamInfo.getStatus().equals(BizConstants.EXAM_STATUS.NEW)){
            /////////////检验相关考生是否排完
            ExaExamInfo exaExamInfoForCheck=new ExaExamInfo();
            exaExamInfoForCheck.setStatus(BizConstants.EXAM_STU_STATUS.NORMALE);
            exaExamInfoForCheck.setId(exaExamInfo.getId());
            int exaExamStuUnsettingNum = this.exmExamService.getExamStuUnsettingCount(exaExamInfoForCheck);
            if(exaExamStuUnsettingNum>0){
                throw new BizException("该考试还存在未排座的考生！");
            }
        }
        boolean bol = this.exmExamService.modExaminfoStatus(exaExamInfo, vUserInfo.getId());
        return bol;
    }


    @RequestMapping(value = "/initEditExamPlan.do")
    public ModelAndView initEditExamPlan(String id){
        ModelAndView mv = new ModelAndView("exa/examPlan/examPlanModForm");
        ExaExamPlan exaExamPlanInfo = this.exmExamService.getExamPlanInfoById(id);
        mv.addObject("exaExamPlanInfo",exaExamPlanInfo);
        return mv;
    }

    @RequestMapping(value = "/initEditExamPlanDetail.do")
    public ModelAndView initEditExamPlanDetail(String id){
        ModelAndView mv = new ModelAndView("exa/examPlan/examPlanDetail");
        ExaExamPlan exaExamPlanInfo = this.exmExamService.getExamPlanInfoById(id);
        mv.addObject("exaExamPlanInfo",exaExamPlanInfo);
        return mv;
    }

    @RequestMapping(value = "/initEditExam.do")
    public ModelAndView initEdit(String id){
        ModelAndView mv = new ModelAndView("exa/exam/examModForm");
        ExaExamInfo exaExamInfo = this.exmExamService.getExamInfoById(id);
        mv.addObject("exaExamInfo",exaExamInfo);
        return mv;
    }

    @RequestMapping(value = "/initEditForExamStudent.do")
    public ModelAndView initEditForExamStudent(String id){
        ModelAndView mv = new ModelAndView("exa/exam/exaExamStudents");
        ExaExamInfo exaExamInfo = this.exmExamService.getExamInfoById(id);
        mv.addObject("exaExamInfo",exaExamInfo);
        return mv;
    }
    @RequestMapping(value = "/initEditForExamExamRoom.do")
    public ModelAndView initEditForExamExamRoom(String id){
        ModelAndView mv = new ModelAndView("exa/exam/exaExamExamRoom");
        ExaExamInfo exaExamInfo = this.exmExamService.getExamInfoById(id);
        mv.addObject("exaExamInfo",exaExamInfo);
        return mv;
    }

  //  @RequestMapping(value = "/initShowForExamRoomStudent.do")
  //  public ModelAndView initShowForExamRoomStudent(ExaExamRoomStudent examRoomStudent){
   //     ModelAndView mv = new ModelAndView("exa/examRoom/examRoomSeatsDetail");
    //    List<ExaExamRoomStudent> exaExamRoomStudentList= this.exmExamService.getExamRoomStudentsListByParam(examRoomStudent);
   //     if(exaExamRoomStudentList.size()>0){
   //         ExaExamRoomStudent exaExamRoomStudent = exaExamRoomStudentList.get(0);
   //         mv.addObject("exaExamRoomStudentInfo",exaExamRoomStudent);
   //     }
   //     return mv;
   // }

    @RequestMapping(value = "/doGetExamRoomStudentsListByParam.infc")
    @ResponseBody
    public Object doGetExamRoomStudentsListByParam(ExaExamRoomStudent examRoomStudent, WebRequest request, VUserInfo vUserInfo) throws BizException {
        List<ExaExamRoomStudent> exaExamRoomStudentList= this.exmExamService.getExamRoomStudentsListByParam(examRoomStudent);
        return exaExamRoomStudentList;
    }

    @RequestMapping(value = "/doGetExamStudentsDetailList.infc")
    @ResponseBody
    public Object doGetExamStudentsDetailList(ExaExamRoomStudent examRoomStudent, WebRequest request, VUserInfo vUserInfo) throws BizException {
        List<ExaExamRoomStudent> exaExamRoomStudentList= this.exmExamService.getExamStudentsDetailList(examRoomStudent);
        return exaExamRoomStudentList;
    }

    /**
     * examStudents
     */

    @RequestMapping(value = "/doGetExamStudentsPageData.infc")
    @ResponseBody
    public Object doGetExamStudentsPageData(PageBean pageBean, WebRequest request)
            throws Exception {
        pageBean = this.exmExamService.getExamStudentsPageData(pageBean);
        return pageBean;
    }

    @RequestMapping(value = "/doGetExamStudentsListByParam.infc")
    @ResponseBody
    public Object doGetExamStudentsListByParam(ExaStuExamInfo exaStuExamInfo, WebRequest request, VUserInfo vUserInfo) throws BizException {

        List<ExaStuExamInfo> exaStuExamInfoList = this.exmExamService.getExamStudentsListByParam(exaStuExamInfo);

        return exaStuExamInfoList;
    }

    @RequestMapping(value = "/doGetExamStudentsListByGrade.infc")
    @ResponseBody
    public Object doGetExamStudentsListByGrade(ExaStuExamInfo exaStuExamInfo, WebRequest request, VUserInfo vUserInfo) throws BizException {
        exaStuExamInfo.setStatus(BizConstants.EXAM_STU_STATUS.NORMALE);
        List<ExaStuExamInfo> exaStuExamInfoList = this.exmExamService.getExamStudentsListByGrade(exaStuExamInfo);

        return exaStuExamInfoList;
    }

    @RequestMapping(value = "/doGetExamStudentsCountByExamId.infc")
    @ResponseBody
    public Object doGetExamStudentsCountByExamId(ExaStuExamInfo exaStuExamInfo, WebRequest request, VUserInfo vUserInfo) throws BizException {

        List<ExaStuExamInfo> exaStuExamInfoList = this.exmExamService.getExamStudentsCountByExamId(exaStuExamInfo);

        return exaStuExamInfoList;
    }


    @RequestMapping(value = "/doAddExamStudents.infc")
    @ResponseBody
    public Object doAddExamStudents(ExaStuExamInfo exaStuExamInfo,VUserInfo userInfo) throws Exception {
        boolean bol =true;
        this.exmExamService.addExamStudents(exaStuExamInfo,userInfo);
        return bol;
    }

    @RequestMapping(value = "/doModExamStudentsStatus.infc")
    @ResponseBody
    public Object doModExamStudentsStatus(ExaStuExamInfo exaStuExamInfo,VUserInfo userInfo) throws Exception {
        boolean bol =true;
        if(exaStuExamInfo.getId()!=null && !exaStuExamInfo.getId().equals("") && exaStuExamInfo.getStatus()!=null && !exaStuExamInfo.getStatus().equals("")){
            ExaStuExamInfo updateStuExamInfo=new ExaStuExamInfo();
            updateStuExamInfo.setId(exaStuExamInfo.getId());
            updateStuExamInfo.setStatus(exaStuExamInfo.getStatus());
            if(!updateStuExamInfo.getStatus().equals(BizConstants.EXAM_STU_STATUS.NORMALE)){//////不参加考试的，清理其考试信息
                ExaStuExamInfo exaStuExamInfoToClean=new ExaStuExamInfo();
                if(exaStuExamInfo.getId()!=null && !exaStuExamInfo.getId().equals("")){
                    exaStuExamInfoToClean.setId(exaStuExamInfo.getId());
                }else{
                    throw new BizException(BizConstants.HTML_VAL.ERROR_MES_PARAM_ERROR);
                }
                bol=this.exmExamService.cleanExamStuInfoForStu(exaStuExamInfoToClean);
            }
            if(bol){
                this.exmExamService.modExamStudentsStatus(updateStuExamInfo);/////////同时修改考试安排状态和考生成绩状态
            }
        }else{
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_PARAM_ERROR);
        }

        return bol;
    }

    @RequestMapping(value = "/doModAllExamStudentsStatusInExamPlan.infc")
    @ResponseBody
    public Object doModAllExamStudentsStatusInExamPlan(ExaStuExamInfo exaStuExamInfo,VUserInfo userInfo) throws Exception {
        boolean bol =true;
        if(exaStuExamInfo.getStuId()!=null && !exaStuExamInfo.getStuId().equals("") && exaStuExamInfo.getExamPlanId()!=null
                && !exaStuExamInfo.getExamPlanId().equals("") && exaStuExamInfo.getStatus()!=null && !exaStuExamInfo.getStatus().equals("")){
            if(bol){
                this.exmExamService.modAllExamStudentsStatusInExamPlan(exaStuExamInfo);
            }
        }else{
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_PARAM_ERROR);
        }

        return bol;
    }

    @RequestMapping(value = "/doAddExamExamRoomSetting.infc")
    @ResponseBody
    public Object doAddExamExamRoomSetting(WebRequest request, VUserInfo vUserInfo) throws BizException {
        Map readOnlyMap = new HashMap();
        readOnlyMap = request.getParameterMap();
        String tempGrade,tempExamRoom,tempABType,Grade="",ExamRoom="",ABType="";
        int examRoomStNum=0;///考场容量
        int examRoomStNumA=0;///考场容量A
        int examRoomStNumB=0;///考场容量B
        int tempExamRoomStSettedNum=0;///某考场已经设置人数
        Iterator<String> iter = readOnlyMap.keySet().iterator();
        //////////////////清除相关考试排位
        String examId= request.getParameter("examId");
        if(examId==null || examId.equals("")){
            throw new BizException("参数缺失!");
        }
        ExaExamInfo exaExamInfoToStatusCheck = this.exmExamService.getExamInfoById(examId);
        if(!exaExamInfoToStatusCheck.getStatus().equals(BizConstants.EXAM_STATUS.NEW)){///////////检验考试是否为新建状态
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_REFUSE+"考试非新建状态");
        }
        ExaStuExamInfo exaStuExamInfoToClean=new ExaStuExamInfo();
        exaStuExamInfoToClean.setExamId(examId);
        boolean bol_1=this.exmExamService.cleanExamRoomSettingInfo(exaStuExamInfoToClean);
        if(!bol_1){
            throw new BizException("清楚旧数据操作失败!");
        }
        while (iter.hasNext()) {
            String  key = iter.next();
            if(key.length()>30){///////////////////排除其他参数
                tempExamRoom=key.substring(0,32);
                String a [] = key.split("_");
                if(a.length<3){
                    throw new BizException("座位类型参数出错!");
                }
                tempGrade=a[1];
                // tempGrade=key.substring(33,34);
                tempABType=key.substring(key.length()-1,key.length());
                ABType= BizConstants.ABSeatVal(tempABType);
                if(ABType==null || ABType.equals("")){
                    throw new BizException("座位类型参数出错!");
                }
                String [] param = (String [])readOnlyMap.get(key);
                int stNumToSet=Integer.parseInt(param[0]);
                ExaExamRoom tempExaExamRoom=this.exmExamRoomService.getExamRoomInfoById(tempExamRoom);
                ///////////////////////////////////////////////////////////////组建座位类型map
                HashMap seatABmap = new HashMap();
                String[] seatABarray = tempExaExamRoom.getSeatAb().split("\\|");
                String[] seatAarray;
                String[] seatBarray;
                String tempAB="";
                for(int i = 0 ; i < seatABarray.length ; i++){

                    tempAB=seatABarray[i].substring(0,1);
                    seatABmap.put(i,tempAB);
                }
                //////////////////////////////////////////////////
                if(tempExaExamRoom!=null && !tempExaExamRoom.getSeatAb().equals("")){
                    String[] arrayA = tempExaExamRoom.getSeatAb().split("A#");
                    String[] arrayB = tempExaExamRoom.getSeatAb().split("B#");
                    if (arrayA != null){
                        examRoomStNumA=arrayA.length - 1;
                     }
                    if (arrayB != null){
                        examRoomStNumB=arrayB.length - 1;
                    }
                }
                if(tempABType.equals("A")){
                    if(examRoomStNumA<stNumToSet){//////////考场容量<预放入人数
                        throw new BizException("预设置考生人数A座大于考场容量!");
                    }
                }else if(tempABType.equals("B")){
                    if(examRoomStNumB<stNumToSet){//////////考场容量<预放入人数
                        throw new BizException("预设置考生人数B座大于考场容量!");
                    }
                }else{
                    throw new BizException("考场AB座参数错误!");
                }

                //////////////////////////获取未设置的考生list
                ExaStuExamInfo exaStuExamInfoBlank=new ExaStuExamInfo();
                exaStuExamInfoBlank.setExamId(examId);
                exaStuExamInfoBlank.setGrade(tempGrade);
                exaStuExamInfoBlank.setStatus(BizConstants.EXAM_STU_STATUS.NORMALE);
                List<ExaStuExamInfo> exaStuExamInfoBlankList = this.exmExamService.getExamStudentsBlankList(exaStuExamInfoBlank);
                if(exaStuExamInfoBlankList.size()<stNumToSet){
                    throw new BizException("考生人数出错!");
                }
                //////////////////////////获取某考场已经设置的考生数
                ExaStuExamInfo exaStuExamInfoSetted=new ExaStuExamInfo();
                exaStuExamInfoSetted.setExamId(examId);
                exaStuExamInfoSetted.setExamRoomId(tempExamRoom);
                exaStuExamInfoSetted.setStatus(BizConstants.EXAM_STU_STATUS.NORMALE);
                ////////////////////////////////////////////////////////////////////////////
                List<ExaStuExamInfo> exaStuExamInfoSettedList = this.exmExamService.getExamStudentsListByParam(exaStuExamInfoSetted);
                int settedSeatNum=0;/////////////////////已经完成排位的末尾座号
                int tempSeatANum=1;///A座指针位置
                int tempSeatBNum=1;///座指针位置
                for (int i = 0 ; i < exaStuExamInfoSettedList.size() ; i++ ){
                    if(exaStuExamInfoSettedList.get(i).getSeatNum()!=null && !exaStuExamInfoSettedList.get(i).getSeatNum().equals("") && Integer.valueOf(exaStuExamInfoSettedList.get(i).getSeatNum())>settedSeatNum){
                        settedSeatNum=Integer.valueOf(exaStuExamInfoSettedList.get(i).getSeatNum());
                    }
                    if(exaStuExamInfoSettedList.get(i).getSeatNum()!=null && !exaStuExamInfoSettedList.get(i).getSeatNum().equals("")
                            && Integer.valueOf(exaStuExamInfoSettedList.get(i).getSeatNum())>=tempSeatANum && exaStuExamInfoSettedList.get(i).getSeatAb().equals("1")){
                        tempSeatANum=Integer.valueOf(exaStuExamInfoSettedList.get(i).getSeatNum())+1;
                    }
                    if(exaStuExamInfoSettedList.get(i).getSeatNum()!=null && !exaStuExamInfoSettedList.get(i).getSeatNum().equals("")
                            && Integer.valueOf(exaStuExamInfoSettedList.get(i).getSeatNum())>=tempSeatBNum && exaStuExamInfoSettedList.get(i).getSeatAb().equals("2")){
                        tempSeatBNum=Integer.valueOf(exaStuExamInfoSettedList.get(i).getSeatNum())+1;
                    }
                }
                int seatANumForCheck=this.exmExamService.countSeatABTypeNum(0,settedSeatNum,"A",tempExaExamRoom.getSeatAb());
                int seatBNumForCheck=this.exmExamService.countSeatABTypeNum(0,settedSeatNum,"B",tempExaExamRoom.getSeatAb());
                int seat0NumForCheck=this.exmExamService.countSeatABTypeNum(0,settedSeatNum,"0#",tempExaExamRoom.getSeatAb());
                int exaStuExamInfoSettedNum = this.exmExamService.getExamStudentsSettedCount(exaStuExamInfoSetted);////已经排的考生数
                if(settedSeatNum!=seatANumForCheck+seatBNumForCheck+seat0NumForCheck){
                    throw new BizException("已排座号有空!");
                }
               // if(tempSeatANum==0 && tempSeatBNum==0){
               //     tempSeatANum=settedSeatNum+1;
               //     tempSeatBNum=settedSeatNum+1;
               // }

                for (int i = 0 ; i < stNumToSet ; i++ ){
                    ///////////////////////////加入一个考生考场信息
                    ExaStuExamInfo newExaStuExamInfo=new ExaStuExamInfo();
                    newExaStuExamInfo.setId(exaStuExamInfoBlankList.get(i).getId());
                    newExaStuExamInfo.setExamId(examId);
                    newExaStuExamInfo.setStuId(exaStuExamInfoBlankList.get(i).getStuId());
                    newExaStuExamInfo.setExamRoomId(tempExamRoom);
                    newExaStuExamInfo.setSeatAb(ABType);
                    int SeatNum=-1;////////////应排座号
                    if(ABType.equals("1")){
                        SeatNum=this.exmExamService.getFirstSeatABTypeNum(tempSeatANum-1,"A",tempExaExamRoom.getSeatAb())+1;
                        tempSeatANum=SeatNum+1;
                    }else if(ABType.equals("2")){
                        SeatNum=this.exmExamService.getFirstSeatABTypeNum(tempSeatBNum-1,"B",tempExaExamRoom.getSeatAb())+1;
                        tempSeatBNum=SeatNum+1;
                    }else{
                        throw new BizException("参数错误!");
                    }
                   // System.out.println("--------------------doAddExamExamRoomSetting|dd||"+SeatNum+"||"+tempSeatANum+"||"+tempSeatBNum+"||"+ABType+"||");
                    //int SeatNum=exaStuExamInfoSettedNum+1+i;
                    //SeatNum=SeatNum+1;
                    newExaStuExamInfo.setSeatNum(SeatNum);

                    boolean bol_3=this.exmExamService.modExamRoomStuInfo(newExaStuExamInfo);
                    if(!bol_3){
                        throw new BizException("数据新增操作失败!");
                    }
                }
            }
        }
        return true;
    }

    @RequestMapping(value = "/doCheckExamForUsed.infc")
    @ResponseBody
    public Object doCheckExamForUsed(ExaExamInfo exaExamInfo,VUserInfo vUserInfo)
            throws Exception {
        boolean bol =false;
        ExaStuExamInfo exaStuExamInfo=new ExaStuExamInfo();
        exaStuExamInfo.setExamId(exaExamInfo.getId());
        List<ExaStuExamInfo> exaStuExamInfoListByExamId=this.exmExamService.selectExaStuByParam(exaStuExamInfo);
        if(exaStuExamInfoListByExamId.size()>0){////////////////考试已经被安排考生
            bol=true;
        }
        return bol;
    }

    @RequestMapping(value = "/doGetExamPlanDetailByExaExamPlanId.infc")
    @ResponseBody
    public Object doGetExamPlanDetailByExaExamPlanId(ExaExamPlan ExaExamPlanInfo, WebRequest request, VUserInfo vUserInfo) throws BizException {

        List exaStuExamInfoList = this.exmExamService.getExamPlanDetailByExaExamPlanId(ExaExamPlanInfo);

        return exaStuExamInfoList;
    }

    @RequestMapping(value = "/doGetExamInfoByParam.infc")
    @ResponseBody
    public Object doGetExamInfoByParam(ExaExamInfo exaExamInfo, WebRequest request, VUserInfo vUserInfo) throws BizException {

        List <ExaExamInfo>exaExamInfoList = this.exmExamService.getExamInfoByParam(exaExamInfo);

        return exaExamInfoList;
    }

    @RequestMapping(value = "/doGetExamDateByParam.infc")
    @ResponseBody
    public Object doGetExamDateByParam(ExaExamInfo exaExamInfo, WebRequest request, VUserInfo vUserInfo) throws BizException {
        List <ExaExamInfo>exaExamInfoList = this.exmExamService.getExamDateByParam(exaExamInfo);
        return exaExamInfoList;
    }

    @RequestMapping(value = "/doCleanExamPlanRelInfoByExamRoomId.infc")
    @ResponseBody
    public Object doCleanExamPlanRelInfoByExamRoomId(ExaExamRoom exaExamRoomInfo,VUserInfo vUserInfo)
            throws Exception {
        boolean bol =false;
        if(exaExamRoomInfo.getId()==null || exaExamRoomInfo.getId().equals("")){
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_PARAM_ERROR);
        }
        ExaExamRoom exaExamRoom = this.exmExamRoomService.getExamRoomInfoById(exaExamRoomInfo.getId());
        String  exaExamPlanId=exaExamRoom.getExamPlanId();
        bol = this.exmExamService.cleanStuExamInfoByExamPlanId(exaExamPlanId);
        return bol;
    }

    @RequestMapping(value = "/doGetExamStudentsPageDataByExamPlan.infc")
    @ResponseBody
    public Object doGetExamStudentsPageDataByExamPlan(PageBean pageBean, WebRequest request)
            throws Exception {
        pageBean.getQueryparam().put("examStatus","4");
        pageBean = this.exmExamService.getExamStudentsPageDataByExamPlan(pageBean);
        return pageBean;
    }


}
