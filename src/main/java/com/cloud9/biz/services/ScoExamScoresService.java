package com.cloud9.biz.services;

import com.cloud9.biz.dao.mybatis.*;
import com.cloud9.biz.models.*;
import com.cloud9.biz.models.vo.StatisticsSectionVo;
import com.cloud9.biz.models.vo.VRecordConfig;
import com.cloud9.biz.models.vo.VScoreQuery;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.util.BizConstants;
import com.cloud9.biz.util.EduKit;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.handler.BizException;
import com.roroclaw.base.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zl on 2017/10/8.
 */
@Service("examScoresService")
@Transactional
public class ScoExamScoresService extends BaseService{
    private static Logger logger = LoggerFactory.getLogger(ScoSubjectScoresService.class);

    @Autowired
    private CommonService commonService;

    @Autowired
    private TchCourseOpenMapper tchCourseOpenMapper;

    @Autowired
    private TchScoresConfigMapper tchScoresConfigMapper;

    @Autowired
    private ScoExamScoresMapper scoExamScoresMapper;

    @Autowired
    private TchCourseMapper tchCourseMapper;

    @Autowired
    private SysTeacherInfoMapper sysTeacherInfoMapper;

    @Autowired
    private ArcStudentInfoMapper arcStudentInfoMapper;

    @Autowired
    private ScoStuSubjctTotalAvgMapper scoStuSubjctTotalAvgMapper;

    @Autowired
    private ScoClassStuTotalMapper scoClassStuTotalMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private  ScoClassScoreStaticMapper scoClassScoreStaticMapper;

    @Autowired
    private ExaExamInfoMapper exaExamInfoMapper;

    @Autowired
    private TchExamScoresConfigMapper tchExamScoresConfigMapper;

    @Autowired
    private ExaStuExamInfoMapper exaStuExamInfoMapper;

    @Autowired
    private ScoOtherExamScoresMapper otherExamScoresMapper;


    private Lock lock = new ReentrantLock();

    /**
     * 分页查询登分成绩
     * @param pageBean
     * @return
     */
    public PageBean getRecordScoresPageData(PageBean pageBean,String userId) {
        //获取教师信息
        SysTeacherInfo sysTeacherInfo = this.sysTeacherInfoMapper.selectTeacherInfoByUserId(userId);
        List<ScoExamScores> resList = this.scoExamScoresMapper.selectRecordScoresPageData(pageBean,sysTeacherInfo.getId());
        pageBean.setData(resList);
        return pageBean;
    }

    public List<ScoExamScores> getScoresDatas(String openCourseId,String scoreType,String userId) {
        //获取教师信息
        SysTeacherInfo sysTeacherInfo = this.sysTeacherInfoMapper.selectTeacherInfoByUserId(userId);
        List<ScoExamScores> resList = this.scoExamScoresMapper.selectScoresDatas(openCourseId,scoreType,sysTeacherInfo.getId());
        return resList;
    }

    /**
     * 登分
     * @param id 成绩id
     * @param score 分数
     * @return
     */
    public boolean recordScores(String id,String score) {
        boolean bol = false;
        ScoExamScores scoExamScores = new ScoExamScores();
        scoExamScores.setId(id);
//        scoExamScores.setStatus(resultStatus);
        scoExamScores.setRecordeStatus(BizConstants.RECORDE_STATUS.UNSUB);
        scoExamScores.setScore(new BigDecimal(score));
        bol = this.scoExamScoresMapper.updateByPrimaryKeySelective(scoExamScores) > 0 ? true:false;
        return bol;
    }

    /**
     * 登分
     * @return
     */
    public boolean batchRecordScores(String[]  valStrArr,String userId) {
        boolean bol = false;
        List<ScoExamScores> scoreList = new ArrayList<ScoExamScores>();
        for (int i = 0; i < valStrArr.length; i++){
            String valStr = valStrArr[i];
            String[] valArr = valStr.split("\\|");
            String id = valArr[0];
            String score = valArr[1];
            ScoExamScores scoExamScores = new ScoExamScores();
            if(valArr.length == 3){
                String remark = valArr[2];
                scoExamScores.setRemark(remark);
            }else{
                scoExamScores.setRemark(null);
            }
            scoExamScores.setId(id);
//            scoExamScores.setRecordeStatus(BizConstants.RECORDE_STATUS.UNSUB);
            scoExamScores.setScore(new BigDecimal(score));
            scoExamScores.setUpdater(userId);
            scoExamScores.setUpdateTime(new Date());
            scoreList.add(scoExamScores);
        }

        //判断用户是否是管理员角色
        SysUser sysUser = this.sysUserMapper.selectByPrimaryKey(userId);
        if (BizConstants.USER_TYPE.SUPER != sysUser.getType()) {//超级管理员
            boolean checkbol = this.validateRecordTeacherByUserId(userId,scoreList);
            if(!checkbol){
                throw new BizException("非登分教师,不可以登分!");
            }
        }

        bol = this.scoExamScoresMapper.batchRecordScores(scoreList) > 0 ? true:false;
        return bol;
    }

    /**
     * 提交登分成绩
     * @param ids
     * @return
     */
    public boolean recordScores(String ids) {
        boolean bol = false;
        String[] idArr = ids.split(",");
        List<ScoExamScores> scores = new ArrayList<ScoExamScores>();
        for (int i=0;i < idArr.length;i++){
            String id = idArr[i];
            ScoExamScores scoExamScores = new ScoExamScores();
            scoExamScores.setId(id);
            scoExamScores.setRecordeStatus(BizConstants.RECORDE_STATUS.SUBED);
            scores.add(scoExamScores);
        }
        bol = this.scoExamScoresMapper.batchSubRecordScores(scores) >0 ? true : false;
        return bol;
    }

    /**
     * BY ZL
     */
    public boolean cleanScoExamScoresInfoByParam(ScoExamScores scoExamScoresInfo) {
        boolean bol = true;

      this.scoExamScoresMapper.deleteScoExamScoresInfoByParam(scoExamScoresInfo);

        return bol;
    }

    /**
     * 获取
     * @param pageBean
     * @return
     */
    public PageBean getRecordConfigPageData(PageBean pageBean) {
        List<VRecordConfig> resList = this.tchCourseOpenMapper.selectRecordConfigPageData(pageBean);
        //遍历计算当时班级名称
        for (int i=0 ; i < resList.size(); i++){
            VRecordConfig vRecordConfig =  resList.get(i);
           String classId = vRecordConfig.getClassId();
//           String className = "测试计算班级";//这里测试,后面调用统一方法
           String className = commonService.getPresentGradeName(vRecordConfig.getPeriod(),vRecordConfig.getGraduateYear(),vRecordConfig.getSchoolYear(),vRecordConfig.getClassText());
           vRecordConfig.setClassText(className);
        }
        pageBean.setData(resList);
        return pageBean;
    }

    /**
     * 登分教师设置
     * @param openCourseId
     * @param teacherId
     * @return
     */
    public boolean doTeacherSet(String openCourseId, String teacherId,String scoresType) {
        this.tchScoresConfigMapper.deleteByOpenCourseId(openCourseId,scoresType);
        TchScoresConfig tchScoresConfig = new TchScoresConfig();
        tchScoresConfig.setId(BizConstants.generatorPid());
        tchScoresConfig.setCourseOpenId(openCourseId);
        tchScoresConfig.setTeacherId(teacherId);
        tchScoresConfig.setStatus(BizConstants.RECORD_CONFIG_STATUS.SET_TEACHER);
        tchScoresConfig.setScoresType(scoresType);
        this.tchScoresConfigMapper.insertSelective(tchScoresConfig);

        return true;
    }

    /**
     * 开始登分
     * @param openCourseId
     * @param scoresType
     * @param vUserInfo
     * @return
     */
    public boolean startRecord(String openCourseId,String scoresType,VUserInfo vUserInfo) throws Exception{

//        int k = this.tchScoresConfigMapper.updateStatusByOpenCourseId(openCourseId,scoresType,BizConstants.RECORD_CONFIG_STATUS.SET_STATUS);

        TchScoresConfig tchScoresConfig = this.tchScoresConfigMapper.selectConfigByOpenCourseId(openCourseId, scoresType);

        if( (tchScoresConfig!=null && BizConstants.RECORD_CONFIG_STATUS.SET_STATUS.equals(tchScoresConfig.getStatus()))){
            throw new BizException("已进入登分环节!");
        }
        //加锁
        if(!lock.tryLock()){
            throw new BizException("处理中,请稍等!");
        }
//        lock.lock();
        try{

        //获取开课信息
        TchCourseOpen tchCourseOpen = this.tchCourseOpenMapper.selectByPrimaryKey(openCourseId);

//        //如果无备课设置信息,自动设置授课教师为登分教师
//        TchScoresConfig tchScoresConfig = this.tchScoresConfigMapper.selectConfigByOpenCourseId(openCourseId,scoresType);
        if(tchScoresConfig == null || tchScoresConfig.getTeacherId() == null){
            TchCourseWithBLOBs tchCourse = this.tchCourseMapper.selectByPrimaryKey(tchCourseOpen.getCourseId());
            this.doTeacherSet(openCourseId,tchCourse.getTeacherId(),scoresType);
        }

        //查询开课相关学生信息,生成成绩信息
        List<ArcStudentInfo> studInfos = this.arcStudentInfoMapper.selectStusByOpenCourseId(openCourseId);
        List<ScoExamScores> scoList = new ArrayList<ScoExamScores>();
        int stuSize = studInfos.size();
        if(stuSize == 0){
            throw new BizException("此开课信息无任何学员!");
        }
        for (int i= 0 ; i < studInfos.size() ; i++){
            ArcStudentInfo arcStudentInfo = studInfos.get(i);
            ScoExamScores scoExamScores = new ScoExamScores();
            scoExamScores.setId(BizConstants.generatorPid());
            scoExamScores.setRecordeStatus(BizConstants.RECORDE_STATUS.UNSUB);
            scoExamScores.setStatus(BizConstants.EXAM_STU_STATUS.NORMALE);
            scoExamScores.setCourseOpenId(openCourseId);
            scoExamScores.setCreateTime(new Date());
            scoExamScores.setCreator(vUserInfo.getId());
            scoExamScores.setSchoolYear(tchCourseOpen.getSchoolYear());
            scoExamScores.setTerm(tchCourseOpen.getTerm());
            scoExamScores.setStuId(arcStudentInfo.getId());
            scoExamScores.setType(scoresType);
            scoExamScores.setClassId(arcStudentInfo.getClassId());
            scoExamScores.setGradeId(tchCourseOpen.getGrade());
//            String className = "测试班级";//计算班级名称
            String className = commonService.getPresentGradeName(arcStudentInfo.getPeriod(),arcStudentInfo.getGraduateYear(),tchCourseOpen.getSchoolYear(),arcStudentInfo.getClassName());
            scoExamScores.setClassName(className);
            scoList.add(scoExamScores);
        }

        if(scoList.size() > 0){
            this.scoExamScoresMapper.batchAddScoExamScores(scoList);
        }

        //设置状态为登分中
        int k = this.tchScoresConfigMapper.updateStatusByOpenCourseId(openCourseId,scoresType,BizConstants.RECORD_CONFIG_STATUS.SET_STATUS);
        }catch (Exception e){
            throw e;
        }finally {
            lock.unlock();
        }
        return true;
    }

    public boolean startTeacher(String openCourseId,String scoresType) {
        this.tchScoresConfigMapper.updateStatusByOpenCourseId(openCourseId,scoresType,BizConstants.RECORD_CONFIG_STATUS.RECORDING);
        return true;
    }

    /**
     * 设置成绩考试状态
     * @param id
     * @param status
     * @return
     */
    public boolean setScoresStatus(String id, String status) {
        boolean bol = false;
        ScoExamScores scoExamScores = new ScoExamScores();
        scoExamScores.setId(id);
        scoExamScores.setStatus(status);
        if(!status.equals(BizConstants.EXAM_STU_STATUS.NORMALE)){
            scoExamScores.setScore(new BigDecimal(0));
        }
        this.scoExamScoresMapper.updateByPrimaryKeySelective(scoExamScores);
        return bol;
    }

    /**
     * 分页获取缺/缓考数据
     * @param pageBean
     * @return
     */
    public PageBean doGetExamScores4StatusPageData(PageBean pageBean) {
        List<ScoExamScores> resList = this.scoExamScoresMapper.selectExamScores4StatusPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    /**
     * 分页获分数数据
     * @param pageBean
     * @return
     */
    public PageBean getExamScores4AdminModPageData(PageBean pageBean) {
        List<ScoExamScores> resList = this.scoExamScoresMapper.selectExamScores4AdminModPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    public PageBean getRecordConfigPageData(PageBean pageBean, String userId) {
        SysTeacherInfo sysTeacherInfo = this.sysTeacherInfoMapper.selectTeacherInfoByUserId(userId);
        if(sysTeacherInfo != null){
            pageBean.getQueryparam().put("teacherId",sysTeacherInfo.getId());
            List<VRecordConfig> resList = this.tchCourseOpenMapper.selectRecordConfigPageData(pageBean);
            //遍历计算当时班级名称
            for (int i=0 ; i < resList.size(); i++){
                VRecordConfig vRecordConfig =  resList.get(i);
                String classId = vRecordConfig.getClassId();
//                String className = "测试计算班级";//这里测试,后面调用统一方法
                String className = commonService.getPresentGradeName(vRecordConfig.getPeriod(),vRecordConfig.getGraduateYear(),vRecordConfig.getSchoolYear(),vRecordConfig.getClassText());
                vRecordConfig.setClassText(className);
                vRecordConfig.setClassText(className);
            }
            pageBean.setData(resList);
        }
        return pageBean;
    }

    public PageBean getOtherRecordConfigPageData(PageBean pageBean, String userId) {
        SysTeacherInfo sysTeacherInfo = this.sysTeacherInfoMapper.selectTeacherInfoByUserId(userId);
        if(sysTeacherInfo != null){
            pageBean.getQueryparam().put("teacherId",sysTeacherInfo.getId());
            List<VRecordConfig> resList = exaExamInfoMapper.selectOtherRecordConfigPageData(pageBean);
            pageBean.setData(resList);
        }
        return pageBean;
    }

    public boolean updateStatusByOpenCourseId(String openCourseId,String scoresType,String status){
        this.tchScoresConfigMapper.updateStatusByOpenCourseId(openCourseId,scoresType,status);
        return true;
    }

    public boolean endScoreConfig(String openCourseId,String scoresType){
        this.tchScoresConfigMapper.updateStatusByOpenCourseId(openCourseId,scoresType,BizConstants.RECORD_CONFIG_STATUS.END);
        //修改此设置下所有成绩状态
        this.scoExamScoresMapper.updateRecordStatusByOpenCourseId(openCourseId,scoresType,BizConstants.RECORDE_STATUS.PUBLISH);
        return true;
    }


    public boolean validateRecordTeacherByUserId(String userId, List<ScoExamScores> scoreList) {
        boolean bol = false;
        List<String> teacherUserIdList= this.tchScoresConfigMapper.validateTeacherUserIdByScore(userId, scoreList);
        if(teacherUserIdList.size() == 1 && teacherUserIdList.get(0).equals(userId)){
            bol = true;
        }
        return bol;
    }

    public boolean validateRecordTeacherByUserId(String userId,String scoreId) {
        boolean bol = false;
        int num = this.tchScoresConfigMapper.selectRecordTeacherCountByUserId(userId, scoreId);
        if(num == 1){
            bol = true;
        }
        return bol;
    }

    ////////////by zl
    public List<ScoExamScoresForQuery> getScoreCousesForQueryListByParam(ScoExamScoresForQuery scoExamScoresForQuery) {
        List<ScoExamScoresForQuery> resList = this.scoExamScoresMapper.selectScoreCousesForQueryListByParam(scoExamScoresForQuery);
        return resList;
    }

    public List<ScoExamScoresForQuery> getScoreStuForQueryListByParam(ScoExamScoresForQuery scoExamScoresForQuery) {
        List<ScoExamScoresForQuery> resList = this.scoExamScoresMapper.selectScoreStuForQueryListByParam(scoExamScoresForQuery);
        return resList;
    }

    public List<ScoExamScoresForQuery> getScoreStuForScoreCollectingQueryByParam(ScoExamScoresForQuery scoExamScoresForQuery) {
        List<ScoExamScoresForQuery> resList = this.scoExamScoresMapper.selectScoreStuForScoreCollectingQueryByParam(scoExamScoresForQuery);
        return resList;
    }

    public List<ScoExamScoresForQuery> getCourseItemsForPower(ScoExamScoresForQuery scoExamScoresForQuery) {
        List<ScoExamScoresForQuery> resList = this.scoExamScoresMapper.selectScoreCousesForQueryListByParam(scoExamScoresForQuery);
        return resList;
    }

    /**
     * 学生成绩查询
     * @param pageBean
     * @param
     * @return
     */
    public PageBean stuQueryScorePageData(PageBean pageBean, String stuId,String normaleStatus,String recordStatus,String subjectScoreStatus) {
        List<VScoreQuery> resList = this.scoExamScoresMapper.stuQueryScorePageData(pageBean, stuId, recordStatus,subjectScoreStatus);
        pageBean.setData(resList);
        return pageBean;
    }

    /**
     * 获取临时考试记录
     * @param pageBean
     * @return
     */
    public PageBean getOtherRecordConfigPageData(PageBean pageBean) {
        List<VRecordConfig> resList = exaExamInfoMapper.selectOtherRecordConfigPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    public boolean calStatisticsDatas(String schoolYear, String term, String scoreType, String grade) {
        boolean bol = true;
        this.scoClassStuTotalMapper.deleteClassStuTotal(schoolYear, term, scoreType,grade);
        this.scoClassStuTotalMapper.calClassStuTotal(schoolYear, term, scoreType,grade);
        this.scoStuSubjctTotalAvgMapper.deleteStuSubjctTotalAvg(schoolYear, term, scoreType, grade);
        this.scoStuSubjctTotalAvgMapper.calStuSubjctTotalAvg(schoolYear, term, scoreType, grade);
        return bol;
    }

    public boolean calExamClassStatisticsDatas(String schoolYear, String term, String scoreType, String grade) {
        boolean bol = true;
        StatisticsSectionVo statisticsSectionVo = getSectionByGrade(grade);
//        String period  = EduKit.getPeriodByGrade4Statistics(grade);
//        StatisticsSectionVo statisticsSectionVo = new StatisticsSectionVo();
//        if(BizConstants.PERIOD.PRIMARY_SCHOOL.equals(period)){
//            statisticsSectionVo.setExcellent(80);
//            statisticsSectionVo.setPass(60);
//            statisticsSectionVo.setCol1(100);
//            statisticsSectionVo.setCol2(90);
//            statisticsSectionVo.setCol3(80);
//            statisticsSectionVo.setCol4(70);
//            statisticsSectionVo.setCol5(60);
//            statisticsSectionVo.setCol6(60);
//        }else if(BizConstants.PERIOD.MIDDLE_SCHOOL.equals(period)){
//            statisticsSectionVo.setExcellent(96);
//            statisticsSectionVo.setPass(72);
//            statisticsSectionVo.setCol1(120);
//            statisticsSectionVo.setCol2(108);
//            statisticsSectionVo.setCol3(96);
//            statisticsSectionVo.setCol4(84);
//            statisticsSectionVo.setCol5(72);
//            statisticsSectionVo.setCol6(72);
//        }else if(BizConstants.PERIOD.HIGH_SCHOOL.equals(period)){
//            statisticsSectionVo.setExcellent(120);
//            statisticsSectionVo.setPass(90);
//            statisticsSectionVo.setCol1(150);
//            statisticsSectionVo.setCol2(135);
//            statisticsSectionVo.setCol3(120);
//            statisticsSectionVo.setCol4(105);
//            statisticsSectionVo.setCol5(90);
//            statisticsSectionVo.setCol6(90);
//        }
        this.scoClassScoreStaticMapper.deleteDatas(schoolYear, term, scoreType, grade);
        this.scoClassScoreStaticMapper.calDatas(schoolYear, term, scoreType, grade,statisticsSectionVo);
        return bol;
    }

    public StatisticsSectionVo getSectionByGrade(String grade){
        String period  = EduKit.getPeriodByGrade4Statistics(grade);
        StatisticsSectionVo statisticsSectionVo = new StatisticsSectionVo();
        if(BizConstants.PERIOD.PRIMARY_SCHOOL.equals(period)){
            statisticsSectionVo.setExcellent(80);
            statisticsSectionVo.setPass(60);
            statisticsSectionVo.setCol1(100);
            statisticsSectionVo.setCol2(90);
            statisticsSectionVo.setCol3(80);
            statisticsSectionVo.setCol4(70);
            statisticsSectionVo.setCol5(60);
            statisticsSectionVo.setCol6(60);
        }else if(BizConstants.PERIOD.MIDDLE_SCHOOL.equals(period)){
            statisticsSectionVo.setExcellent(96);
            statisticsSectionVo.setPass(72);
            statisticsSectionVo.setCol1(120);
            statisticsSectionVo.setCol2(108);
            statisticsSectionVo.setCol3(96);
            statisticsSectionVo.setCol4(84);
            statisticsSectionVo.setCol5(72);
            statisticsSectionVo.setCol6(72);
        }else if(BizConstants.PERIOD.HIGH_SCHOOL.equals(period)){
            statisticsSectionVo.setExcellent(120);
            statisticsSectionVo.setPass(90);
            statisticsSectionVo.setCol1(150);
            statisticsSectionVo.setCol2(135);
            statisticsSectionVo.setCol3(120);
            statisticsSectionVo.setCol4(105);
            statisticsSectionVo.setCol5(90);
            statisticsSectionVo.setCol6(90);
        }
        return statisticsSectionVo;
    }

    public boolean doOtherTeacherSet(String examId, String teacherId) {
        this.tchExamScoresConfigMapper.deleteByExamId(examId);
        TchExamScoresConfig tchExamScoresConfig = new TchExamScoresConfig();
        tchExamScoresConfig.setId(BizConstants.generatorPid());
        tchExamScoresConfig.setExamId(examId);
        tchExamScoresConfig.setTeacherId(teacherId);
        tchExamScoresConfig.setStatus(BizConstants.RECORD_CONFIG_STATUS.SET_TEACHER);
        this.tchExamScoresConfigMapper.insertSelective(tchExamScoresConfig);
        return true;
    }

    public boolean otherStartTeacher(String examId) {
        TchExamScoresConfig tchExamScoresConfig = this.tchExamScoresConfigMapper.selectByExamId(examId);
        String teacherId = tchExamScoresConfig.getTeacherId();
        if(teacherId != null && !"".equals(teacherId) ){
            this.tchExamScoresConfigMapper.updateOtherStatusByExamId(examId, BizConstants.RECORD_CONFIG_STATUS.RECORDING);
        }else{
            throw new BizException("请设置登分教师!");
        }
        return true;
    }

    public boolean otherStartRecord(String examId, VUserInfo vUserInfo) {
        TchExamScoresConfig tchExamScoresConfig = this.tchExamScoresConfigMapper.selectByExamId(examId);
        String teacherId = tchExamScoresConfig.getTeacherId();
        if(teacherId != null && !"".equals(teacherId) ){
            //查询开课相关学生信息,生成成绩信息
            List<ExaStuExamInfo> exaStuExamInfos = this.exaStuExamInfoMapper.selectStusByExamId(examId);
            List<ScoOtherExamScores> scoList = new ArrayList<ScoOtherExamScores>();
            int stuSize = exaStuExamInfos.size();
            if(stuSize == 0){
                throw new BizException("此考试无任何学员!");
            }
            for (int i= 0 ; i < exaStuExamInfos.size() ; i++){
                ExaStuExamInfo exaStuExamInfo = exaStuExamInfos.get(i);
                ScoOtherExamScores scoOtherExamScores = new ScoOtherExamScores();
                scoOtherExamScores.setId(BizConstants.generatorPid());
                scoOtherExamScores.setRecordeStatus(BizConstants.RECORDE_STATUS.UNSUB);
                scoOtherExamScores.setStatus(BizConstants.EXAM_STU_STATUS.NORMALE);
                scoOtherExamScores.setExamId(examId);
                scoOtherExamScores.setSubjuctId(exaStuExamInfo.getSubjectId());
                scoOtherExamScores.setCreateTime(new Date());
                scoOtherExamScores.setCreator(vUserInfo.getId());
                scoOtherExamScores.setStuId(exaStuExamInfo.getStuId());
                scoList.add(scoOtherExamScores);
            }

            if(scoList.size() > 0){
                this.otherExamScoresMapper.batchAddScoExamScores(scoList);
            }

            //设置状态为登分中
            int k = this.tchExamScoresConfigMapper.updateOtherStatusByExamId(examId,BizConstants.RECORD_CONFIG_STATUS.SET_STATUS);
            return true;
        }else{
            throw new BizException("请设置登分教师!");
        }
    }


    public PageBean doGetOtherExamScores4StatusPageData(PageBean pageBean) {
        List<ScoOtherExamScores> resList = this.otherExamScoresMapper.selectExamScores4StatusPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    public boolean updateOtherStatusByExamId(String examId, String status) {
        this.tchExamScoresConfigMapper.updateOtherStatusByExamId(examId, status);
        return true;
    }

    public boolean setOtherScoresStatus(String id, String status) {
        boolean bol = false;
        ScoOtherExamScores scoOtherExamScores = new ScoOtherExamScores();
        scoOtherExamScores.setId(id);
        scoOtherExamScores.setStatus(status);
        if(!status.equals(BizConstants.EXAM_STU_STATUS.NORMALE)){
            scoOtherExamScores.setScore(new BigDecimal(0));
        }
        this.otherExamScoresMapper.updateByPrimaryKeySelective(scoOtherExamScores);
        return bol;
    }

    public PageBean getOtherRecordScoresPageData(PageBean pageBean, String userId) {
        //获取教师信息
        SysTeacherInfo sysTeacherInfo = this.sysTeacherInfoMapper.selectTeacherInfoByUserId(userId);
        List<ScoOtherExamScores> resList = this.otherExamScoresMapper.selectRecordScoresPageData(pageBean,sysTeacherInfo.getId());
        pageBean.setData(resList);
        return pageBean;
    }

    public boolean otherBatchRecordScores(String[] valStrArr, String userId) {
        boolean bol = false;
        List<ScoOtherExamScores> scoreList = new ArrayList<ScoOtherExamScores>();
        for (int i = 0; i < valStrArr.length; i++){
            String valStr = valStrArr[i];
            String[] valArr = valStr.split("\\|");
            String id = valArr[0];
            String score = valArr[1];
            ScoOtherExamScores scoOtherExamScores = new ScoOtherExamScores();
            if(valArr.length == 3){
                String remark = valArr[2];
                scoOtherExamScores.setRemark(remark);
            }else{
                scoOtherExamScores.setRemark(null);
            }
            scoOtherExamScores.setId(id);
//            scoExamScores.setRecordeStatus(BizConstants.RECORDE_STATUS.UNSUB);
            scoOtherExamScores.setScore(new BigDecimal(score));
            scoOtherExamScores.setUpdater(userId);
            scoOtherExamScores.setUpdateTime(new Date());
            scoreList.add(scoOtherExamScores);
        }

        //判断用户是否是管理员角色
        SysUser sysUser = this.sysUserMapper.selectByPrimaryKey(userId);
        if (BizConstants.USER_TYPE.SUPER != sysUser.getType()) {//超级管理员
            boolean checkbol = this.validateOtherRecordTeacherByUserId(userId,scoreList);
            if(!checkbol){
                throw new BizException("非登分教师,不可以登分!");
            }
        }

        bol = this.otherExamScoresMapper.batchRecordScores(scoreList) > 0 ? true:false;
        return bol;
    }

    public boolean validateOtherRecordTeacherByUserId(String userId, List<ScoOtherExamScores> scoreList) {
        boolean bol = false;
        List<String> teacherUserIdList= this.tchExamScoresConfigMapper.validateTeacherUserIdByScore(userId, scoreList);
        if(teacherUserIdList.size() == 1 && teacherUserIdList.get(0).equals(userId)){
            bol = true;
        }
        return bol;
    }

    /**
     * @param examId
     * @param userId
     * @return
     */
    public List<ScoOtherExamScores> getOtherScoresDatas(String examId, String userId) {
        //获取教师信息
        SysTeacherInfo sysTeacherInfo = this.sysTeacherInfoMapper.selectTeacherInfoByUserId(userId);
        List<ScoOtherExamScores> resList = this.otherExamScoresMapper.selectScoresDatas(examId,sysTeacherInfo.getId());
        return resList;
    }

    public PageBean getOtherExamScores4AdminModPageData(PageBean pageBean) {
        List<ScoOtherExamScores> resList = this.otherExamScoresMapper.selectExamScores4AdminModPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    public boolean endOtherScoreConfig(String examId) {
        this.tchExamScoresConfigMapper.updateOtherStatusByExamId(examId, BizConstants.RECORD_CONFIG_STATUS.END);
        //修改此设置下所有成绩状态
        this.otherExamScoresMapper.updateRecordStatusByExamId(examId,BizConstants.RECORDE_STATUS.PUBLISH);
        return true;
    }
}
