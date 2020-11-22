package com.cloud9.biz.services;

import com.cloud9.biz.dao.mybatis.*;
import com.cloud9.biz.models.*;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.handler.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zl on 2017/9/20.
 */
@Service("examService")
@Transactional
public class ExmExamService {

    @Autowired
    private ExaExamInfoMapper exaExamInfoMapper;

    @Autowired
    private ExaExamPlanMapper exaExamPlanMapper;

    @Autowired
    private ExaStuExamInfoMapper exaStuExamInfoMapper;

    @Autowired
    private ScoExamScoresMapper scoExamScoresMapper;

    @Autowired
    private SysDictItemMapper sysDictItemMapper;

    @Autowired
    private TchStuCourseOpenRelMapper tchStuCourseOpenRelMapper;

    public PageBean getExamPlanPageData(PageBean pageBean) {
        List resList = this.exaExamPlanMapper.selectExamPlanPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    /**
     * 获取考试计划下拉数据
     * @return
     */
    public List<SysDictItem> getExamPlanItems(String type,String status) {
        List<SysDictItem> itemList = null;
        List<ExaExamPlan> eaxmPlanList = this.exaExamPlanMapper.selectExamPlanByParam(type, status,"");
        int Size = eaxmPlanList.size();
        if(eaxmPlanList.size() > 0){
            itemList = new ArrayList<SysDictItem>();
        }
        for (int i=0; i < Size;i++){
            SysDictItem sysDictItem = new SysDictItem();
            ExaExamPlan exaExamPlanInfo = eaxmPlanList.get(i);

            sysDictItem.setText(exaExamPlanInfo.getName());
            sysDictItem.setCode(exaExamPlanInfo.getId());
            itemList.add(sysDictItem);
        }
        return itemList;
    }

    public List<SysDictItem> getExamPlanItemsBySchoolYear(String schoolYear) {
        List<SysDictItem> itemList = null;
        List<ExaExamPlan> eaxmPlanList = this.exaExamPlanMapper.selectExamPlanByParam("","",schoolYear);
        int Size = eaxmPlanList.size();
        if(eaxmPlanList.size() > 0){
            itemList = new ArrayList<SysDictItem>();
        }
        for (int i=0; i < Size;i++){
            SysDictItem sysDictItem = new SysDictItem();
            ExaExamPlan exaExamPlanInfo = eaxmPlanList.get(i);

            sysDictItem.setText(exaExamPlanInfo.getName());
            sysDictItem.setCode(exaExamPlanInfo.getId());
            itemList.add(sysDictItem);
        }
        return itemList;
    }
    /**
     * 获取考试下拉数据
     * @return
     */
    public List<SysDictItem> getExamItems(String examPlanId,String status) {
        List<SysDictItem> itemList = null;
        List<ExaExamInfo> examList = this.exaExamInfoMapper.selectExamByParam(examPlanId, status);
        int Size = examList.size();
        if(examList.size() > 0){
            itemList = new ArrayList<SysDictItem>();
        }
        for (int i=0; i < Size;i++){
            SysDictItem sysDictItem = new SysDictItem();
            ExaExamInfo exaExamInfo = examList.get(i);
            sysDictItem.setText(exaExamInfo.getSubjectName());
            sysDictItem.setCode(exaExamInfo.getId());
            itemList.add(sysDictItem);
        }
        return itemList;
    }



    public ExaExamPlan getExamPlanInfoById(String id) {
        ExaExamPlan exaExamInfo = this.exaExamPlanMapper.selectExamPlanByPrimaryKey(id);
        return exaExamInfo;
    }

    public ExaExamInfo getExamInfoById(String id) {
        ExaExamInfo exaExamInfo = this.exaExamInfoMapper.selectExamByPrimaryKey(id);
        return exaExamInfo;
    }


    public PageBean getExamPageData(PageBean pageBean) {
        List resList = this.exaExamInfoMapper.selectExamInfoPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }


    public boolean addExamPlan(ExaExamPlan exaExamPlanInfo,String userId) {
        boolean bol = false;
        exaExamPlanInfo.setId(BizConstants.generatorPid());
        exaExamPlanInfo.setCreateTime(new Date());
        exaExamPlanInfo.setUpdateTime(new Date());
        exaExamPlanInfo.setCreator(userId);
        exaExamPlanInfo.setUpdater(userId);
        int i = this.exaExamPlanMapper.insert(exaExamPlanInfo);
        if(i > 0){
            bol = true;
        }
        return bol;
    }

    public boolean addExam(ExaExamInfo exaExamInfo,String userId) {
        boolean bol = false;
        exaExamInfo.setId(BizConstants.generatorPid());
        exaExamInfo.setCreateTime(new Date());
        exaExamInfo.setCreator(userId);
        exaExamInfo.setStatus(BizConstants.EXAM_STATUS.NEW);
        int i = this.exaExamInfoMapper.insert(exaExamInfo);
        if(i > 0){
            bol = true;
        }
        return bol;
    }
    public boolean modExamPlan(ExaExamPlan exaExamPlanInfo, String userId) {
        boolean bol = false;
        exaExamPlanInfo.setUpdateTime(new Date());
        exaExamPlanInfo.setUpdater(userId);
        int i = this.exaExamPlanMapper.updateByPrimaryKeySelective(exaExamPlanInfo);
        if(i > 0){
            bol = true;
        }
        return bol;
    }

    public boolean modExaminfo(ExaExamInfo exaExamInfo, String userId) {
        boolean bol = false;
        int i = this.exaExamInfoMapper.updateByPrimaryKeySelective(exaExamInfo);
        exaExamInfo.setUpdateTime(new Date());
        exaExamInfo.setUpdater(userId);
        if(i > 0){
            bol = true;
        }
        return bol;
    }

    public boolean modExaminfoStatus(ExaExamInfo exaExamInfo, String userId) {
        boolean bol = false;
        int i = this.exaExamInfoMapper.updateByPrimaryKeySelective(exaExamInfo);
        exaExamInfo.setUpdateTime(new Date());
        exaExamInfo.setUpdater(userId);
        if(i > 0){
            bol = true;
        }
        return bol;
    }

    /**
     * examStudents
     */
    public PageBean getExamStudentsPageData(PageBean pageBean) {
        List resList = this.exaStuExamInfoMapper.selectExamStudentsPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    public List<ExaStuExamInfo> getExamStudentsListByParam(ExaStuExamInfo exaStuExamInfo) {
        List<ExaStuExamInfo> resList = this.exaStuExamInfoMapper.selectExamStudentsListByParam(exaStuExamInfo);
        return resList;
    }

    public List<ExaStuExamInfo> getExamStudentsListByGrade(ExaStuExamInfo exaStuExamInfo) {
        List<ExaStuExamInfo> resList = this.exaStuExamInfoMapper.selectExamStudentsListByGrade(exaStuExamInfo);
        return resList;
    }

    public List<ExaStuExamInfo> getExamStudentsCountByExamId(ExaStuExamInfo exaStuExamInfo) {
        List<ExaStuExamInfo> resList = this.exaStuExamInfoMapper.selectExamStudentsCountByExamId(exaStuExamInfo);
        return resList;
    }

    public List<ExaExamRoomStudent> getExamRoomStudentsListByParam(ExaExamRoomStudent examRoomStudent) {
        List<ExaExamRoomStudent> resList = this.exaStuExamInfoMapper.selectExamRoomStudentsListByParam(examRoomStudent);
        return resList;
    }

    public List<ExaExamRoomStudent> getExamStudentsDetailList(ExaExamRoomStudent examRoomStudent) {
        List<ExaExamRoomStudent> resList = this.exaStuExamInfoMapper.selectExamStudentsDetailList(examRoomStudent);
        return resList;
    }

    public boolean addExamStudents(ExaStuExamInfo exaStuExamInfo,VUserInfo userInfo) throws BizException {
        String courseOpenIDs=exaStuExamInfo.getCourseOpenIDs();
        List courseOpenIDsList= this.changeGroupToList(courseOpenIDs);
        ExaStuExamInfo newRel=exaStuExamInfo;
        for(int i=0;i < courseOpenIDsList.size();i++){
            TchStuCourseOpenRel tempStuCourseOpenRel=new TchStuCourseOpenRel();
            tempStuCourseOpenRel.setCourseOpenId((String) courseOpenIDsList.get(i));
            tempStuCourseOpenRel.setStuStatus(BizConstants.INFO_STATUS.CHECKED);
            List<TchStuCourseOpenRel> tempStuCourseOpenRelList = this.tchStuCourseOpenRelMapper.selectCourseOpenStudentsList(tempStuCourseOpenRel);
            for(int j=0;j < tempStuCourseOpenRelList.size();j++){
                ExaStuExamInfo addStuExamInfo=exaStuExamInfo;
                addStuExamInfo.setStuId(tempStuCourseOpenRelList.get(j).getStuId());
                addStuExamInfo.setStatus(BizConstants.EXAM_STU_STATUS.NORMALE);
                //ScoExamScores addScoExamScores=new ScoExamScores();
                //addScoExamScores.setExamId(exaStuExamInfo.getExamId());
                //addScoExamScores.setStatus(BizConstants.EXAM_STU_STATUS.NORMALE);
                //addScoExamScores.setCourseOpenId(tempStuCourseOpenRelList.get(j).getCourseOpenId());
                //addScoExamScores.setStuId(tempStuCourseOpenRelList.get(j).getStuId());
                //addScoExamScores.setCreateTime(new Date());
                //addScoExamScores.setCreator(userInfo.getId());
                //if(!this.addStudentExamInfo(addStuExamInfo,addScoExamScores)){////////////已经取消与成绩的关联
                if(!this.addStudentExamInfo(addStuExamInfo)){
                    throw new BizException(BizConstants.HTML_VAL.ERROR_MES_DATA_PROCESSING);
                }
            }
        }
        return true;
    }

    public boolean addStudentExamInfo(ExaStuExamInfo exaStuExamInfo) {
        boolean bol = false;
        String stuExamInfoId=BizConstants.generatorPid();
        List<ExaStuExamInfo> resList=this.selectExaStuByParam(exaStuExamInfo);
       // List<ScoExamScores> resListSco= this.scoExamScoresMapper.selectByParam(scoExamScores);
        if(resList.size()==0){///////////////////////////无重复数据
            exaStuExamInfo.setId(stuExamInfoId);
            int i =this.exaStuExamInfoMapper.insertSelective(exaStuExamInfo);

            if(i > 0){///////////////////////////////////////////////////////添加学生成绩数据
               // bol=addExamScoresInfo(scoExamScores,stuExamInfoId);
               bol=true;
            }
        }else if(resList.size()==1){///////////////////////////存在重复数据
            ExaStuExamInfo tempExaStuExamInfoOld=this.exaStuExamInfoMapper.selectByPrimaryKey(resList.get(0).getId());
            exaStuExamInfo.setId(tempExaStuExamInfoOld.getId());
            int i =this.exaStuExamInfoMapper.updateByPrimaryKeySelective(exaStuExamInfo);
            if(i > 0){//////////////////////////////////////////////////////添加学生成绩数据
               // bol=addExamScoresInfo(scoExamScores,(String)resList.get(0).getId());
                bol=true;
            }
        }else{
            throw new BizException("现有数据存在错误!");
        }

        return bol;
    }

    public boolean addExamScoresInfo(ScoExamScores scoExamScores,String stuExamInfoId) {
        boolean bol = false;
        scoExamScores.setId(BizConstants.generatorPid());
        scoExamScores.setStuExamInfoId(stuExamInfoId);
        List<ScoExamScores> resList= this.scoExamScoresMapper.selectByParam(scoExamScores);
        if(resList.size()==0){//////无重复数据
            int i =this.scoExamScoresMapper.insertSelective(scoExamScores);
            if(i > 0){
                bol = true;
            }
        }else if(resList.size()==1){//////有重复数据
            ScoExamScores tempScoExamScoresInfoOld=this.scoExamScoresMapper.selectByPrimaryKey(resList.get(0).getId());
            scoExamScores.setId(tempScoExamScoresInfoOld.getId());
            int i =this.scoExamScoresMapper.updateByPrimaryKeySelective(scoExamScores);
            if(i > 0){
                bol = true;
            }
        }else{
            throw new BizException("现有数据存在错误!");
        }
        return bol;
    }

    /**
     * 修改考生状态
     * @param exaStuExamInfo
     * @return
     */
    public boolean modExamStudentsStatus(ExaStuExamInfo exaStuExamInfo) {
        boolean bol = false;
        int i = this.exaStuExamInfoMapper.updateByPrimaryKeySelective(exaStuExamInfo);
        if (i > 0) {
            bol = true;
        }
        /*if (i > 0) {
            ScoExamScores updateScoExamScoresInfo=new ScoExamScores();
            updateScoExamScoresInfo.setStuExamInfoId(exaStuExamInfo.getId());
            updateScoExamScoresInfo.setStatus(exaStuExamInfo.getStatus());
            int j =this.scoExamScoresMapper.updateByParams(updateScoExamScoresInfo);
            if (j > 0) {
                bol = true;
            }
        }*/
        return bol;
    }

    /**
     * 修改考生状态
     * @param exaStuExamInfo
     * @return
     */
    public boolean modAllExamStudentsStatusInExamPlan(ExaStuExamInfo exaStuExamInfo) {
        boolean bol = false;
        int i = this.exaStuExamInfoMapper.updateAllExamStudentsStatusInExamPlan(exaStuExamInfo);
        if (i > 0) {
            bol = true;
        }
        return bol;
    }

    public List<ExaStuExamInfo> selectExaStuByParam(ExaStuExamInfo exaStuExamInfo) {
        List<ExaStuExamInfo> resList= this.exaStuExamInfoMapper.selectByParam(exaStuExamInfo);
        return resList;
    }


    public List changeGroupToList(String Group) {
        List newList=new ArrayList();
        String a [] = Group.split("#");
        for(int i=0;i<a.length;i++){
            newList.add(a[i]);
        }
        return newList;
    }

    public boolean cleanExamRoomSettingInfo(ExaStuExamInfo exaStuExamInfo) {
        boolean bol = false;
        int i = this.exaStuExamInfoMapper.cleanExamRoomSettingInfo(exaStuExamInfo);
        if (i > 0) {
            bol = true;
        }
        return bol;
    }

    public boolean cleanExamStuInfoForStu(ExaStuExamInfo exaStuExamInfoToClean) {
        boolean bol = false;
        int i = this.exaStuExamInfoMapper.cleanExamStuInfoForStu(exaStuExamInfoToClean);
        if (i > 0) {
            bol = true;
        }
        return bol;
    }

    public boolean cleanExamRoomSettingInfoByExamRoomId(ExaExamRoom examRoomInfo) {
        boolean bol = false;
        int i = this.exaStuExamInfoMapper.cleanExamRoomSettingInfoByExamRoomId(examRoomInfo);
        if (i >= 0) {
            bol = true;
        }
        return bol;
    }

    public List<ExaStuExamInfo> getExamStudentsBlankList(ExaStuExamInfo exaStuExamInfo) {
        List<ExaStuExamInfo> resList = this.exaStuExamInfoMapper.selectExamStudentsBlankList(exaStuExamInfo);
        return resList;
    }

    public boolean modExamRoomStuInfo(ExaStuExamInfo exaStuExamInfo) {
        boolean bol = false;
        int i = this.exaStuExamInfoMapper.updateByPrimaryKeySelective(exaStuExamInfo);
        if(i > 0){
            bol = true;
        }
        return bol;
    }

    public Integer getExamStudentsSettedCount(ExaStuExamInfo exaStuExamInfo) {
        int count = this.exaStuExamInfoMapper.selectExamStudentsSettedCount(exaStuExamInfo);
        return count;
    }

    public Integer getExamStuUnsettingCount(ExaExamInfo exaExamInfo) {
        int count = this.exaStuExamInfoMapper.selectExamStuUnsettingCount(exaExamInfo);
        return count;
    }

    public boolean cleanStuExamInfoByParam(ExaStuExamInfo exaStuExamInfo) {
        boolean bol = true;

            this.exaStuExamInfoMapper.deleteStuExamInfoByParam(exaStuExamInfo);

        return bol;
    }

    public boolean cleanStuExamInfoByExamPlanId(String examPlanId) {
        boolean bol = true;
        this.exaStuExamInfoMapper.cleanExamRoomSettingInfoByExamPlanID(examPlanId);
        //this.exaStuExamInfoMapper.deleteStuExamInfoByExamPlanId(examPlanId);
        return bol;
    }

    public List getExamPlanDetailByExaExamPlanId(ExaExamPlan ExaExamPlanInfo) {
        List resList = this.exaExamPlanMapper.selectExamPlanDetailListByExaExamPlanId(ExaExamPlanInfo);
        return resList;
    }
    public List<ExaExamInfo> getExamInfoByParam(ExaExamInfo exaExamInfo) {
        exaExamInfo.setOrderParam("a.DATE");
        List<ExaExamInfo> resList = this.exaExamInfoMapper.selectExamInfoByParam(exaExamInfo);
        return resList;
    }
    public List<ExaExamInfo> getExamDateByParam(ExaExamInfo exaExamInfo) {
        exaExamInfo.setOrderParam("a.DATE");
        List<ExaExamInfo> resList = this.exaExamInfoMapper.selectExamDateByParam(exaExamInfo);
        return resList;
    }


    public Integer countSeatABTypeNum(int begin,int end,String type,String seatAB) {
        int count=0;
        int b=0;
        int e=0;
        if(begin<10){
            b=begin*4;
        }else if(begin>9){
            b=begin*5-9;
        }
        if(end<10){
            e=end*4;
        }else if(end>9){
            e=end*5-9;
        }
        if(e>seatAB.length()){
            e=seatAB.length();
        }
        System.out.println("--------------------countSeatABTypeNum===="+begin+"||"+end+"||"+seatAB+"||"+b+"||"+e+"||");

        String subSeatAB=seatAB.substring(b,e);
        String[] array = subSeatAB.split(type);
        if (array != null)
        {
            count=array.length - 1;
        }
        return count;
    }

    public Integer getFirstSeatABTypeNum(int begin,String type,String seatAB) {
        int count=-1;

        String[] seatABarray = seatAB.split("\\|");
     //   System.out.println("--------------------getFirstSeatABTypeNum111===="+begin+"||"+type+"||"+seatAB+"||"+seatABarray.length+"||"+count);
        for(int i = begin ; i < seatABarray.length ; i++){
           // System.out.println("--------------------getFirstSeatABTypeNum===="+i+"||"+seatABarray[i].substring(0,1)+"||"+count+"||"+"||");
            if(seatABarray[i].substring(0,1).equals(type) && count==-1){
                count=i;
                //System.out.println("--------------------getFirstSeatABTypeNum222===="+begin+"||"+type+"||"+seatAB+"||"+seatABarray.length+"||"+count);
            }
        }

        return count;
    }

    public PageBean getExamStudentsPageDataByExamPlan(PageBean pageBean) {
        List resList = this.exaStuExamInfoMapper.selectExamStudentsPageDataByExamPlan(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }
}
