package com.cloud9.biz.services;

import com.cloud9.biz.dao.mybatis.ArcStudentInfoMapper;
import com.cloud9.biz.dao.mybatis.SysDictItemMapper;
import com.cloud9.biz.dao.mybatis.TchClassInfoMapper;
import com.cloud9.biz.models.ArcStudentInfo;
import com.cloud9.biz.models.SysDictItem;
import com.cloud9.biz.models.TchClassInfo;
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

import java.util.Date;
import java.util.List;

/**
 * Created by dxz on 2017/8/13.
 */
@Service("classService")
@Transactional
public class TchClassService extends BaseService {
    @Autowired
    private TchClassInfoMapper classInfoMapper;

    @Autowired
    private SysDictItemMapper sysDictItemMapper;

    @Autowired
    private ArcStudentInfoMapper arcStudentInfoMapper;

    @Autowired
    private CommonService commonService;

    public PageBean getClassPageData(PageBean pageBean) {
        List resList = this.classInfoMapper.selectClassPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    /**
     * 新增班级信息
     * @param tchClassInfo
     * @return
     */
    public boolean addClass(TchClassInfo tchClassInfo,String userId) {
        boolean bol = false;
        //验证班级重复性
        int count = this.classInfoMapper.selectRepeatClassinfo(tchClassInfo,null);
        if(count > 0){
            throw new BizException("此班级名或年级班次已存在!");
        }
        tchClassInfo.setId(BizConstants.generatorPid());
        tchClassInfo.setPeriod(EduKit.getPeriodByGrade(tchClassInfo.getGrade()));
        tchClassInfo.setNumber(0);
        tchClassInfo.setCreateTime(new Date());
        tchClassInfo.setCreator(userId);
        tchClassInfo.setStatus(BizConstants.COMMON_STATUS.ACTIVE_STR);
        int i = this.classInfoMapper.insertSelective(tchClassInfo);
        if(i > 0){
            bol = true;
        }
        return bol;
    }

    public boolean delClassById(String classId) {
        boolean bol = false;
        //验证班级删除条件
        List<ValidateDelVo> validateDelVoList= this.classInfoMapper.validateClassDelLimit(classId);
        ToolKit.validateDelFun(validateDelVoList);
        int i = this.classInfoMapper.deleteByPrimaryKey(classId);
        if(i > 0){
            bol = true;
        }
        return bol;
    }

    public boolean stopClassinfo(String id) {
        boolean bol = false;
        int i = this.classInfoMapper.changeClassStatus(id,BizConstants.CLASS_STATUS.STOP);
        if( i > 0){
            bol = true;
        }
        return bol;
    }

    public boolean activeClassinfo(String id) {
        boolean bol = false;
        int i = this.classInfoMapper.changeClassStatus(id,BizConstants.CLASS_STATUS.ACTIVE);
        if( i > 0){
            bol = true;
        }
        return bol;
    }

    /**
     * 获取班级信息
     * @param id
     * @return
     */
    public TchClassInfo getClassInfoById(String id) {
        TchClassInfo tchClassInfo = this.classInfoMapper.selectClassInfoById(id);
        return tchClassInfo;
    }

    public boolean modClass(TchClassInfo tchClassInfo, String userId) {
        boolean bol = false;
        //验证班级重复性
        int count = this.classInfoMapper.selectRepeatClassinfo(tchClassInfo,tchClassInfo.getId());
        if(count > 0){
            throw new BizException("此班级名或年级班次已存在!");
        }
        tchClassInfo.setPeriod(EduKit.getPeriodByGrade(tchClassInfo.getGrade()));
        tchClassInfo.setUpdateTime(new Date());
        tchClassInfo.setUpdater(userId);
        int i = this.classInfoMapper.updateByPrimaryKeySelective(tchClassInfo);
        if(i > 0){
            bol = true;
        }
        return bol;
    }

    public boolean increaseGrade(String classIDs ,String userId) {
        // TchClassInfo tchClassInfo=new TchClassInfo();
        boolean bol = false;
        List classIDsList = this.commonService.changeGroupToList(classIDs);
        // TchStuCourseOpenRel newRel=tchStuCourseOpenRel;
        //  TchStuCourseOpenRel newRelForCheck=new TchStuCourseOpenRel();
        //   newRelForCheck.setCourseOpenId(tchStuCourseOpenRel.getCourseOpenId());
        //System.out.println("--------------------increaseGrade||"+classIDsList+"||");

        for(int i=0;i < classIDsList.size();i++){
            TchClassInfo tempTchClassInfo = this.classInfoMapper.selectClassInfoById((String)classIDsList.get(i));
            TchClassInfo newTchClassInfo = new TchClassInfo();
            if(!tempTchClassInfo.getStatus().equals(BizConstants.CLASS_STATUS.ACTIVE)){
                throw new BizException("非正常状态班级无法升年级!");
            }
            if(tempTchClassInfo.getGrade().equals("12")){///毕业归档
                newTchClassInfo.setId(tempTchClassInfo.getId());
                newTchClassInfo.setUpdateTime(new Date());
                newTchClassInfo.setUpdater(userId);
                newTchClassInfo.setStatus(BizConstants.CLASS_STATUS.ENDED);
                int  k= this.classInfoMapper.updateByPrimaryKeySelective(newTchClassInfo);
                if(k > 0){
                    bol = true;
                }
            }else{///升年级

                String newGrade=Integer.toString(Integer.parseInt(tempTchClassInfo.getGrade()) + 1);
                String newClassName=BizConstants.GRADE_NAME[Integer.parseInt(newGrade)]+"("+tempTchClassInfo.getSeq()+")班";
                newTchClassInfo.setId(tempTchClassInfo.getId());
                newTchClassInfo.setGrade(newGrade);
                newTchClassInfo.setPeriod(EduKit.getPeriodByGrade(newGrade));
                newTchClassInfo.setName(newClassName);
                newTchClassInfo.setUpdateTime(new Date());
                newTchClassInfo.setUpdater(userId);
                //验证班级重复性
                int count = this.classInfoMapper.selectRepeatClassinfo(newTchClassInfo,newTchClassInfo.getId());
                if(count > 0){
                    throw new BizException("此班级名或年级班次已存在!");
                }
                int l = this.classInfoMapper.updateByPrimaryKeySelective(newTchClassInfo);
                if(l > 0){
                    ////////////////////学生升年级
                    ArcStudentInfo arcStudentInfo =new ArcStudentInfo();
                    arcStudentInfo.setClassId(newTchClassInfo.getId());
                    arcStudentInfo.setGrade(newTchClassInfo.getGrade());
                    arcStudentInfo.setUpdater(userId);
                    arcStudentInfo.setUpdateTime(new Date());
                    int j = this.arcStudentInfoMapper.increaseStuGrade(arcStudentInfo);
                    if (j > 0) {
                        bol = true;
                    }
                }
            }
        }
        return bol;
    }

    /**
     * Created by zl
     */
    public List<SysDictItem> getClassItems(String grade) {
        List<SysDictItem> resList = this.sysDictItemMapper.selectClassItems(grade);
        return resList;
    }

    /**
     * Created by zl
     */
    public List<SysDictItem> getClassItemsForPower(String grade,String schoolYear,String period,String teacherId) {
        String graduateYear="";
        if(grade!=null && !grade.equals("") && period!=null && !period.equals("") && schoolYear!=null && !schoolYear.equals("")){///查某学届的所有班级，要清空grade参数。
            String periodGradeNum=BizConstants.periodGradeNum(period);
            int GraduateYear=Integer.parseInt(schoolYear)+(Integer.parseInt(periodGradeNum)-Integer.parseInt(grade))+1;
            graduateYear=Integer.toString(GraduateYear);
            grade="";
        }
        List<SysDictItem> resList = this.sysDictItemMapper.selectClassItemsForPower(period,grade, graduateYear,teacherId);
        return resList;
    }

    public List<SysDictItem> getClassItemsByPowerForArtiAdd(String teacherId) {
        List<SysDictItem> resList = this.sysDictItemMapper.selectClassItemsByPowerForArtiAdd(teacherId);
        return resList;
    }
    public List<SysDictItem> getClassItemsForTeachPower(String grade,String schoolYear,String period,String teacherId,String term) {

        List<SysDictItem> resList = this.sysDictItemMapper.selectClassItemsForTeachPower(period,grade, schoolYear, term, teacherId);
        return resList;
    }

    public List<TchClassInfo> getClassItemsBySchoolYear(String schoolYear,String teacherId) {
        schoolYear=schoolYear+"-01-01";
        List<TchClassInfo> resList = this.classInfoMapper.selectClassItemsBySchoolYear(schoolYear, teacherId);
        return resList;
    }

    public List<TchClassInfo> getClassItemsBySchoolYearForTeacher(String schoolYear,String teacherId,String term) {
        schoolYear=schoolYear+"-01-01";
        List<TchClassInfo> resList = this.classInfoMapper.selectClassItemsBySchoolYearForTeacher(schoolYear, term, teacherId);
        return resList;
    }
}
