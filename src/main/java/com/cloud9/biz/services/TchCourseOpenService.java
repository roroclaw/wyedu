package com.cloud9.biz.services;

import com.cloud9.biz.dao.mybatis.*;
import com.cloud9.biz.models.*;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.models.vo.ValidateDelVo;
import com.cloud9.biz.util.BizConstants;
import com.cloud9.biz.util.EduKit;
import com.cloud9.biz.util.ToolKit;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.handler.BizException;
import com.roroclaw.base.service.BaseService;
import com.cloud9.biz.services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zl on 2017/9/3.
 */
@Service("courseOpenService")
@Transactional
public class TchCourseOpenService extends BaseService{
    @Autowired
    private TchCourseOpenMapper courseOpenMapper;

    @Autowired
    private ArcStudentInfoMapper arcStudentInfoMapper;

    @Autowired
    private TchTeachingPlanMapper tchTeachingPlanMapper;

    @Autowired
    private TchStuCourseOpenRelMapper tchStuCourseOpenRelMapper;

    @Autowired
    private ScoExamScoresMapper scoExamScoresMapper;

    @Autowired
    private SysScoresRuleRelMapper sysScoresRuleRelMapper;

    @Autowired
    private CommonService commonService;

    public PageBean getCourseOpensPageData(PageBean pageBean){
        List resList = this.courseOpenMapper.selectCourseOpensPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    public PageBean getCourseOpensForEntranceExamPageData(PageBean pageBean){
        List resList = this.courseOpenMapper.selectCourseOpensForEntranceExamPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    public PageBean doGetCourseOpensSelPageData(PageBean pageBean){
        List resList = this.courseOpenMapper.selectCourseOpensSelPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }


    public List<TchCourseOpen> getCourseOpenList(TchCourseOpen tchCourseOpen) {
        List<TchCourseOpen> resList = this.courseOpenMapper.selectCourseOpenList(tchCourseOpen);
        return resList;
    }


    public boolean addCourseOpen(TchCourseOpen courseIOpeninfo){

        this.courseOpenMapper.insertSelective(courseIOpeninfo);
        return true;
    }
    /**
     * 修改开课信息
     * @param courseIOpeninfo
     * @return
     */
    public boolean modifyCourseOpenInfo(TchCourseOpen courseIOpeninfo) {
        boolean bol = false;
        //////////////////////////////验证开课信息是否被使用
        int i = this.courseOpenMapper.updateByPrimaryKeySelective(courseIOpeninfo);
        if (i > 0) {
            bol = true;
        }
        return bol;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean delTchCourseOpenById(String id) throws BizException {
        boolean bol = false;
        //////////////////////////////验证开课信息是否被使用
        int i = this.courseOpenMapper.deleteByPrimaryKey(id);
        if (i > 0) {
            bol = true;
        }
        return bol;
    }

    public TchCourseOpen getCourseOpenById(String id) {
        TchCourseOpen tchCourseOpen = null;
        tchCourseOpen = this.courseOpenMapper.selectCourseOpensById(id);
        return tchCourseOpen;
    }

    public boolean addStuCourseOpenRelInfo(TchStuCourseOpenRel tchStuCourseOpenRel) throws BizException {
        String studentIds=tchStuCourseOpenRel.getStudentIds();
        List studentIdsList= this.commonService.changeGroupToList(studentIds);
        TchStuCourseOpenRel newRel=tchStuCourseOpenRel;
        //TchStuCourseOpenRel newRelForCheck=new TchStuCourseOpenRel();
       // newRelForCheck.setCourseOpenId(tchStuCourseOpenRel.getCourseOpenId());
        for(int i=0;i < studentIdsList.size();i++){
            newRel.setStuId((String) studentIdsList.get(i));
           // newRelForCheck.setStuId((String) studentIdsList.get(i));
            ////////////////////检验学籍信息状态
            ArcStudentInfo arcStudentInfo=new ArcStudentInfo();
            arcStudentInfo=this.arcStudentInfoMapper.selectByPrimaryKey((String) studentIdsList.get(i));
            if(arcStudentInfo.getStatus().equals(BizConstants.INFO_STATUS.CHECKED)){
                 /////////////////////与教学计划比对
               // ArcStudentInfo arcStudentInfo=new ArcStudentInfo();
               // arcStudentInfo=this.arcStudentInfoMapper.selectByPrimaryKey((String) studentIdsList.get(i));
               // if(this.checkTeachPlanAcco(newRel)){
                if(true){
                    int count = this.tchStuCourseOpenRelMapper.selectTchStuCourseOpenRelCountByParam(newRel);////////////排课排重
                    //int count_2 = this.tchStuCourseOpenRelMapper.selectTchStuCourseOpenRelForCheck(newRelForCheck);////////////同一学生、学年、学期不能有科目课程开课重复
                    //if(!(count>0) && !(count_2>0)){
                      if(!(count>0)){
                        newRel.setId(BizConstants.generatorPid());
                        this.tchStuCourseOpenRelMapper.insertTchStuCourseOpenRelSelective(newRel);
                    }
                }else{
                    //throw new BizException("与教学计划不符!");
                }
            }
        }
        return true;
    }


    public boolean addStuEntranceExamRelInfo(TchStuCourseOpenRel tchStuCourseOpenRel,VUserInfo userInfo) throws BizException {
        String studentIds=tchStuCourseOpenRel.getStudentIds();
        List studentIdsList= this.commonService.changeGroupToList(studentIds);
        ScoExamScores newScores=new ScoExamScores();
        for(int i=0;i < studentIdsList.size();i++){
            newScores.setStuId((String) studentIdsList.get(i));
            newScores.setCourseOpenId((String)tchStuCourseOpenRel.getCourseOpenId());
            newScores.setType(BizConstants.SCORES_TYPE.ENTRANCE);
            ////////////////////检验学籍信息状态
            ArcStudentInfo arcStudentInfo=new ArcStudentInfo();
            arcStudentInfo=this.arcStudentInfoMapper.selectByPrimaryKey((String) studentIdsList.get(i));
            if(arcStudentInfo.getStatus().equals(BizConstants.INFO_STATUS.CHECKED)){
                int count = this.scoExamScoresMapper.selectExamScoresCountByParam(newScores);////////////成绩数据排重
                if(!(count>0)){
                    TchCourseOpen tchCourseOpen = this.courseOpenMapper.selectCourseOpensById((String)tchStuCourseOpenRel.getCourseOpenId());
                    newScores.setId(BizConstants.generatorPid());
                    newScores.setStatus(BizConstants.EXAM_STU_STATUS.NORMALE);
                    newScores.setClassId((String) tchCourseOpen.getClassId());
                    newScores.setClassName((String) tchCourseOpen.getClassName());
                    newScores.setSchoolYear((String) tchCourseOpen.getSchoolYear());
                    newScores.setTerm((String) tchCourseOpen.getTerm());
                    newScores.setCreateTime(new Date());
                    newScores.setCreator(userInfo.getId());
                    newScores.setGrade((String) tchCourseOpen.getGrade());
                    newScores.setUpdater(userInfo.getId());
                    newScores.setUpdateTime(new Date());
                    newScores.setRecordeStatus(BizConstants.RECORD_STATUS.UNSET);
                    this.scoExamScoresMapper.insertSelective(newScores);
                }
            }
        }
        return true;
    }

    public PageBean getTchCourseOpenStudentsPageData(PageBean pageBean) {
        List resList = tchStuCourseOpenRelMapper.selecTchCourseOpenStudentsPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    public List<TchStuCourseOpenRel> getCourseOpenStudentsList(TchStuCourseOpenRel tchStuCourseOpenRel) {
        List<TchStuCourseOpenRel> resList = this.tchStuCourseOpenRelMapper.selectCourseOpenStudentsList(tchStuCourseOpenRel);
        return resList;
    }



    /**
     * 开课时与教学计划比对
     * @param tchStuCourseOpenRel
     * @return
     * @throws com.roroclaw.base.handler.BizException
     */
    public boolean checkTeachPlanAcco(TchStuCourseOpenRel tchStuCourseOpenRel) throws BizException {
        boolean bol = false;
        List resList =this.tchTeachingPlanMapper.checkTeachPlanAcco(tchStuCourseOpenRel);
        if (resList.size() > 0) {
            bol = true;
        }
        return bol;
    }

    /**
     * 开课完成后与教学计划比对
     * @param tchStuCourseOpenRel
     * @return
     * @throws com.roroclaw.base.handler.BizException
     */
    public List<TchStuCourseOpenRel> getStuCourseOpenCheckList(TchStuCourseOpenRel tchStuCourseOpenRel) {
        List<TchStuCourseOpenRel> resList = this.tchStuCourseOpenRelMapper.selectStuCourseOpenCheckList(tchStuCourseOpenRel);
        return resList;
    }

    /**
     * 删除开课学生
     * @param id
     * @return
     */
    public boolean delTchStuCourseOpenRelById(String id) throws BizException {
        boolean bol = false;
        //////////////////////////////验证开课信息是否被使用
        int i = this.tchStuCourseOpenRelMapper.deleteByPrimaryKey(id);
        if (i > 0) {
            bol = true;
        }
        return bol;
    }

    public int getStuOpenCourseNumByStuId(String id) {
        int i = this.tchStuCourseOpenRelMapper.selectStuOpenCourseNum(id);
        return i;
    }

    public List<SysScoresRuleRelKey> getScoresRuleRelInfo(String id) {
        List<SysScoresRuleRelKey> datas = this.sysScoresRuleRelMapper.selectAllByRuleId(id);
        return datas;
    }


}
