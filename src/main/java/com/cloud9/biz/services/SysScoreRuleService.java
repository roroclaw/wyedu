package com.cloud9.biz.services;

import com.cloud9.biz.dao.mybatis.*;
import com.cloud9.biz.models.*;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.handler.BizException;
import com.roroclaw.base.service.BaseService;
import com.sun.java.accessibility.util.EventID;
import org.omg.CORBA.StringHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by roroclaw on 2017/11/11.
 */
@Service("sysScoreRuleService")
@Transactional
public class SysScoreRuleService extends BaseService {

    private static Logger logger = LoggerFactory.getLogger(SysScoreRuleService.class);

    @Autowired
    private SysScoresRuleConfigMapper sysScoresRuleConfigMapper;

    @Autowired
    private SysTchScoresRuleConfMapper sysTchScoresRuleConfMapper;

    @Autowired
    private ScoSubjectScoresMapper scoSubjectScoresMapper;

    @Autowired
    private SysTeacherInfoMapper sysTeacherInfoMapper;

    @Autowired
    private SysScoresRuleRelMapper sysScoresRuleRelMapper;

//    @Autowired
//    private TchStuCourseOpenRelMapper tchStuCourseOpenRelMapper;

     @Autowired
     private ScoGraduationScoresMapper scoGraduationScoresMapper;


//    /**
//     * 获取规则信息列表
//     * @return
//     */
//    public List<SysScoresRuleConfig> getRuleList(){
//        List<SysScoresRuleConfig> resList = null;
//        resList = sysScoresRuleConfigMapper.selectAllRules();
//        return resList;
//    }
//
//    /**
//     * 修改规则
//     * @param dataStr 数据串
//     * @param userId 修改人id
//     * @return
//     */
//    public boolean modScoreRule(String dataStr,String userId) {
//        boolean bol = true;
//        String[] itemStrArr = dataStr.split("\\|");
//        int totalVal = 0;
//        List<SysScoresRuleConfig> dataList = new ArrayList<SysScoresRuleConfig>();
//        for (int i=0; i< itemStrArr.length; i++){
//            SysScoresRuleConfig sysScoresRuleConfig = new SysScoresRuleConfig();
//            String itemStr = itemStrArr[i];
//            String[] itemDataArr = itemStr.split(",");
//            String id = itemDataArr[0];
//            String text = itemDataArr[1];
//            double val = Double.valueOf(itemDataArr[2]);
//            if(val < 0 || val > 100){
//                throw new BizException(text+"输入非法!");
//            }
//            totalVal += val;
//            sysScoresRuleConfig.setId(id);
//            sysScoresRuleConfig.setRatio(val);
//            sysScoresRuleConfig.setUpdater(userId);
//            sysScoresRuleConfig.setUpdateTime(new Date());
//            dataList.add(sysScoresRuleConfig);
//        }
//        if(totalVal > 100){
//            throw new BizException("占比总合超出100!");
//        }else if(totalVal < 100){
//            throw new BizException("占比总合未到100!");
//        }
//        this.sysScoresRuleConfigMapper.batchModRules(dataList);
//        return bol;
//    }

    public PageBean getScoreRulePageData(PageBean pageBean) {
        List resList = sysScoresRuleConfigMapper.selectScoreRulePageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    /**
     * 获取分数规则信息
     * @param id
     * @return
     */
    public SysScoresRuleConfig getScoreRuleById(String id) {
        SysScoresRuleConfig sysScoresRuleConfig = sysScoresRuleConfigMapper.selectScoreRuleById(id);
        return sysScoresRuleConfig;
    }

    public SysScoresRuleConfig getScoreRuleBySubjectId(String subjectId) {
        SysScoresRuleConfig sysScoresRuleConfig = sysScoresRuleConfigMapper.selectScoreRuleBySubjectId(subjectId);
        return sysScoresRuleConfig;
    }

    public void addScoreRule(SysScoresRuleConfig scoresRuleConfig) {
        scoresRuleConfig.setId(BizConstants.generatorPid());
        sysScoresRuleConfigMapper.insert(scoresRuleConfig);
    }

    public boolean modScoreRule(SysScoresRuleConfig sysScoresRuleConfig) {
        sysScoresRuleConfigMapper.updateByPrimaryKeySelective(sysScoresRuleConfig);
        return true;
    }

//    public void calSocresBySubjectId(String subjectId,String userId,String schoolYear,String term) {
//        //删除科目学年下的成绩信息
//        this.scoSubjectScoresMapper.deleteBySubjectAndYearTerm(subjectId, schoolYear, term);
//        //获取科目开课信息
//        List<ScoExamScores> scoExamScoresList = this.sysScoresRuleConfigMapper.selectExaScoresBySubjectId(subjectId, schoolYear,term);
//        SysScoresRuleConfig sysScoresRuleConfig = sysScoresRuleConfigMapper.selectScoreRuleBySubjectId(subjectId);
//        Map<String,ScoSubjectScores> subjectScoresMap = new HashMap<String, ScoSubjectScores>();
//        for(int i= 0 ;i < scoExamScoresList.size(); i++){
//            ScoExamScores scoExamScores = scoExamScoresList.get(i);
//            String stuId =scoExamScores.getStuId();
//            String scoresType = scoExamScores.getType();
//            BigDecimal score = scoExamScores.getScore() != null ? scoExamScores.getScore() : new BigDecimal(0);
//            ScoSubjectScores scoSubjectScore = subjectScoresMap.get(stuId);
//
//            if(scoSubjectScore == null){
//                scoSubjectScore = new ScoSubjectScores();
//                subjectScoresMap.put(stuId,scoSubjectScore);
//                scoSubjectScore.setStuId(stuId);
//                scoSubjectScore.setSubjectId(subjectId);
//                scoSubjectScore.setSchoolYear(schoolYear);
//                scoSubjectScore.setTerm(scoExamScores.getTerm());
//                scoSubjectScore.setCreateTime(new Date());
//                scoSubjectScore.setCreator(userId);
//                scoSubjectScore.setClassName(scoExamScores.getClassText());
//            }
//
//            if(BizConstants.SCORES_TYPE.MID.equals(scoresType)){
//                scoSubjectScore.setHasMidScore(true);
//                Double ratio = sysScoresRuleConfig.getMiddelRatio();
//                double resScore = ratio*score.doubleValue()/100;
//                scoSubjectScore.addScore(resScore);
//            }else if(BizConstants.SCORES_TYPE.FINAL.equals(scoresType)){
//                scoSubjectScore.setHasEndScore(true);
//                Double ratio = sysScoresRuleConfig.getEndRatio();
//                double resScore = ratio*score.doubleValue()/100;
//                scoSubjectScore.addScore(resScore);
//            }else if(BizConstants.SCORES_TYPE.USUAL.equals(scoresType)){
//                scoSubjectScore.setHasUsualScore(true);
//                Double ratio = sysScoresRuleConfig.getUsualRatio();
//                double resScore = ratio*score.doubleValue()/100;
//                scoSubjectScore.addScore(resScore);
//            }
//        }
//
//        //循环map
//        List<ScoSubjectScores> scoSubjectScoresList = new ArrayList<ScoSubjectScores>();
//        for(ScoSubjectScores subjectScore : subjectScoresMap.values()){
//            if(!subjectScore.isHasEndScore()){
//                subjectScore.addRemark("无期末成绩");
//                subjectScore.setStatus(BizConstants.SCORES_SUBJECT_STATUS.EXCAPTION);
//            }else if(!subjectScore.isHasMidScore()){
//                subjectScore.addRemark("无期中成绩");
//                subjectScore.setStatus(BizConstants.SCORES_SUBJECT_STATUS.EXCAPTION);
//            }else if(!subjectScore.isHasUsualScore()){
//                subjectScore.addRemark("无平时成绩");
//                subjectScore.setStatus(BizConstants.SCORES_SUBJECT_STATUS.EXCAPTION);
//            }
//            subjectScore.setId(BizConstants.generatorPid());
//            scoSubjectScoresList.add(subjectScore);
//        }
//        int scoresNum = scoSubjectScoresList.size();
//        if(scoresNum > 0){
//            logger.info("计算生成"+schoolYear+"学年"+scoresNum+"条数据!");
//            this.scoSubjectScoresMapper.batchAddSubjectScores(scoSubjectScoresList);
//        }else{
//            logger.info("计算生成"+schoolYear+"学年0条数据!");
//        }
//    }

    public boolean delScoreRuleById(String id) {
        boolean bol = true;
        this.sysScoresRuleConfigMapper.deleteByPrimaryKey(id);
        return bol;
    }

    /**
     * 根据计算规则计算成绩
     * @param ruleId
     * @param schoolYear
     * @param term
     */
    public void calSocresByRuleId(String ruleId, String userId, String schoolYear, String term) {
         //获取规则信息
        SysScoresRuleConfig sysScoresRuleConfig = this.sysScoresRuleConfigMapper.selectScoreRuleById(ruleId);
        String gradeScopeStr = sysScoresRuleConfig.getScope();
        String subjectId = sysScoresRuleConfig.getSubjectId();
        String[] gradeArr = gradeScopeStr.split(",");

        //获取规则课程关系下的学员信息
        List<SysScoresRuleRelKey> ruleRellist = this.sysScoresRuleRelMapper.selectAllByRuleId(ruleId);

//        String[] courseIds = new String[ruleRellist.size()];
//
//        for(int i = 0 ; i < ruleRellist.size() ; i++){
//            SysScoresRuleRelKey ruleRel = ruleRellist.get(i);
//            courseIds[i] = ruleRel.getCourseId();
//        }

//        List<TchStuCourseOpenRel> subjectStuInfos = tchStuCourseOpenRelMapper.selectScoresRuleStuInfos(courseIds);

        //删除科目学年下的成绩信息
//        this.scoSubjectScoresMapper.deleteBySubjectAndYearTermGrades(subjectId, schoolYear, term, gradeArr,subjectStuInfos);
        List<ScoExamScores> scoExamScoresList = null;
        if(ruleRellist != null && ruleRellist.size() > 0){
            scoExamScoresList = this.sysScoresRuleConfigMapper.selectExaScoresBySubjectIdGradesCourse(subjectId, schoolYear, term,gradeArr,ruleRellist);
        }else{
           scoExamScoresList = this.sysScoresRuleConfigMapper.selectExaScoresBySubjectIdGrades(subjectId, schoolYear, term,gradeArr);
        }

        //        SysScoresRuleConfig sysScoresRuleConfig = sysScoresRuleConfigMapper.selectScoreRuleBySubjectId(subjectId);
        Map<String,ScoSubjectScores> subjectScoresMap = new HashMap<String, ScoSubjectScores>();
        for(int i= 0 ;i < scoExamScoresList.size(); i++){
            ScoExamScores scoExamScores = scoExamScoresList.get(i);
            String stuId =scoExamScores.getStuId();
            String scoresType = scoExamScores.getType();
            BigDecimal score = scoExamScores.getScore() != null ? scoExamScores.getScore() : new BigDecimal(0);
            ScoSubjectScores scoSubjectScore = subjectScoresMap.get(stuId);

            if(scoSubjectScore == null){
                scoSubjectScore = new ScoSubjectScores();
                subjectScoresMap.put(stuId,scoSubjectScore);
                scoSubjectScore.setStuId(stuId);
                scoSubjectScore.setSubjectId(subjectId);
                scoSubjectScore.setSchoolYear(schoolYear);
                scoSubjectScore.setTerm(scoExamScores.getTerm());
                scoSubjectScore.setCreateTime(new Date());
                scoSubjectScore.setCreator(userId);
                scoSubjectScore.setClassName(scoExamScores.getClassText());
                scoSubjectScore.setGradeId(scoExamScores.getGrade());
                scoSubjectScore.setStatus(BizConstants.SCORES_SUBJECT_STATUS.UNPUBLISH);
                scoSubjectScore.setFlag(Integer.valueOf(BizConstants.SCORES_SUBJECT_STATUS.NORMAL));
            }

//            if(BizConstants.SCORES_TYPE.MID.equals(scoresType)){ //期中
//                scoSubjectScore.setHasMidScore(true);
//                BigDecimal ratio = new BigDecimal(sysScoresRuleConfig.getMiddelRatio());
//                BigDecimal resScore = ratio.multiply(score).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
//                scoSubjectScore.addScore(resScore);
//            }else if(BizConstants.SCORES_TYPE.FINAL.equals(scoresType)){//期末
//                scoSubjectScore.setHasEndScore(true);
//                BigDecimal ratio = new BigDecimal(sysScoresRuleConfig.getEndRatio());
//                BigDecimal resScore = ratio.multiply(score).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
//                scoSubjectScore.addScore(resScore);
//            }else if(BizConstants.SCORES_TYPE.USUAL.equals(scoresType)){//平时
//                scoSubjectScore.setHasUsualScore(true);
//                BigDecimal ratio = new BigDecimal(sysScoresRuleConfig.getUsualRatio());
//                BigDecimal resScore = ratio.multiply(score).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
//                scoSubjectScore.addScore(resScore);
//            }

            scoSubjectScore.setGradeScopeId(sysScoresRuleConfig.getGradeScopeId());
            if(BizConstants.SCORES_TYPE.MID.equals(scoresType)){ //期中
//                scoSubjectScore.setHasMidScore(true);
                scoSubjectScore.setMiddleScore(score);
            }else if(BizConstants.SCORES_TYPE.FINAL.equals(scoresType)){//期末
//                scoSubjectScore.setHasEndScore(true);
                scoSubjectScore.setFinalScore(score);
            }else if(BizConstants.SCORES_TYPE.USUAL.equals(scoresType)){//平时
//                scoSubjectScore.setHasUsualScore(true);
                scoSubjectScore.setUsualScore(score);
            }

            //判断考试成绩状态
            String subjectScoresStatus =  scoExamScores.getStatus();
            if(scoExamScores.getId().equals("f13e2b0dd0dc4c4bbfd3353033853251")){
                System.out.println("subjectScoresStatus====="+subjectScoresStatus);
            }
            if(subjectScoresStatus != null && subjectScoresStatus.equals(BizConstants.EXAM_STU_STATUS.TEMP_STUDY)){//请长假
                scoSubjectScore.setStatus(BizConstants.SCORES_SUBJECT_STATUS.TEMP_STUDY);
//                calTempStudyScores(scoSubjectScore,scoExamScores,sysScoresRuleConfig);
            }else if(subjectScoresStatus != null && subjectScoresStatus.equals(BizConstants.EXAM_STU_STATUS.DELAY)){//期中缓考
                scoSubjectScore.setStatus(BizConstants.SCORES_SUBJECT_STATUS.MID_DELAY);
//                calMidDelayScores(scoSubjectScore,scoExamScores,sysScoresRuleConfig);
            }else{
                String subjectStatus = scoSubjectScore.getStatus();
                if(!subjectStatus.equals(BizConstants.SCORES_SUBJECT_STATUS.TEMP_STUDY) && !subjectStatus.equals(BizConstants.SCORES_SUBJECT_STATUS.MID_DELAY) ){
                    calCommonScores(scoSubjectScore,scoExamScores,sysScoresRuleConfig);//常规规则计算
                }
            }

            //数据重整
            reSureScores(scoSubjectScore);

        }

        //循环map
        List<ScoSubjectScores> scoSubjectScoresList = new ArrayList<ScoSubjectScores>();
        for(ScoSubjectScores subjectScore : subjectScoresMap.values()){
            if(!subjectScore.isHasEndScore()){
                subjectScore.addRemark("无期末成绩");
                subjectScore.setFlag(Integer.valueOf(BizConstants.SCORES_SUBJECT_STATUS.EXCAPTION));
            }else if(!subjectScore.isHasMidScore()){
                subjectScore.addRemark("无期中成绩");
                subjectScore.setFlag(Integer.valueOf(BizConstants.SCORES_SUBJECT_STATUS.EXCAPTION));
            }else if(!subjectScore.isHasUsualScore()){
                subjectScore.addRemark("无平时成绩");
                subjectScore.setFlag(Integer.valueOf(BizConstants.SCORES_SUBJECT_STATUS.EXCAPTION));
            }
            subjectScore.setId(BizConstants.generatorPid());
            subjectScore.intScore();

            //删除原有成绩
            this.scoSubjectScoresMapper.deleteBySubjectAndYearTermStuId(subjectId,schoolYear,term,subjectScore.getStuId());
            scoSubjectScoresList.add(subjectScore);
        }
        int scoresNum = scoSubjectScoresList.size();
        if(scoresNum > 0){
            logger.info("计算生成"+schoolYear+"学年"+scoresNum+"条数据!");
            this.scoSubjectScoresMapper.batchAddSubjectScores(scoSubjectScoresList);
        }else{
            logger.info("计算生成"+schoolYear+"学年0条数据!");
        }

    }

    /**
     * 数据重整
     */
    public void reSureScores(ScoSubjectScores scoSubjectScore){
        String subjectScoresStatus =  scoSubjectScore.getStatus();
        if(subjectScoresStatus != null && subjectScoresStatus.equals(BizConstants.SCORES_SUBJECT_STATUS.TEMP_STUDY)){//请长假
            calTempStudyScores(scoSubjectScore);
        }else if(subjectScoresStatus != null && subjectScoresStatus.equals(BizConstants.SCORES_SUBJECT_STATUS.MID_DELAY)){//期中缓考
            calMidDelayScores(scoSubjectScore);
        }
    }

    //计算请长假成绩
    public void calTempStudyScores(ScoSubjectScores scoSubjectScore){
        String gradeScopeId = scoSubjectScore.getGradeScopeId();
        String subjectId = scoSubjectScore.getSubjectId();

        if(BizConstants.PERIOD.MIDDLE_SCHOOL.equals(gradeScopeId)){ //初中（语文、数学、英语 期末83.33% 政治、历史、地理 期末100%）

            //判断科目
            if(BizConstants.SUBJECT_IDS.yuwen.equals(subjectId) || BizConstants.SUBJECT_IDS.shuxue.equals(subjectId) || BizConstants.SUBJECT_IDS.yingyu.equals(subjectId)){
                    scoSubjectScore.setRemark("初中请长假(语数外)");
                    BigDecimal ratio = new BigDecimal(83.33);
                    BigDecimal resScore = ratio.multiply(scoSubjectScore.getFinalScore()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
                    scoSubjectScore.setScore(resScore);
            }else if(BizConstants.SUBJECT_IDS.zhengzhi.equals(subjectId) || BizConstants.SUBJECT_IDS.lishi.equals(subjectId) || BizConstants.SUBJECT_IDS.dili.equals(subjectId)){
                    scoSubjectScore.setRemark("初中请长假(政史地)");
                    BigDecimal ratio = new BigDecimal(100);
                    BigDecimal resScore = ratio.multiply(scoSubjectScore.getFinalScore()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
                    scoSubjectScore.setScore(resScore);
            }
        }else if(BizConstants.PERIOD.HIGH_SCHOOL.equals(gradeScopeId)){ //高中（语文、数学、英语 期末66.67% 政治、历史、地理 期末100%）

            //判断科目
            if(BizConstants.SUBJECT_IDS.yuwen.equals(subjectId) || BizConstants.SUBJECT_IDS.shuxue.equals(subjectId) || BizConstants.SUBJECT_IDS.yingyu.equals(subjectId)){
                    scoSubjectScore.setRemark("高中请长假(语数外)");
                    BigDecimal ratio = new BigDecimal(66.67);
                    BigDecimal resScore = ratio.multiply(scoSubjectScore.getFinalScore()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
                    scoSubjectScore.setScore(resScore);
            }else if(BizConstants.SUBJECT_IDS.zhengzhi.equals(subjectId) || BizConstants.SUBJECT_IDS.lishi.equals(subjectId) || BizConstants.SUBJECT_IDS.dili.equals(subjectId)){
                    scoSubjectScore.setRemark("高中请长假(政史地)");
                    BigDecimal ratio = new BigDecimal(100);
                    BigDecimal resScore = ratio.multiply(scoSubjectScore.getFinalScore()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
                    scoSubjectScore.setScore(resScore);
            }
        }

    }

//    //计算请长假成绩
//    public ScoSubjectScores calTempStudyScores(ScoSubjectScores scoSubjectScore,ScoExamScores scoExamScores,SysScoresRuleConfig sysScoresRuleConfig){
//        String gradeScopeId = sysScoresRuleConfig.getGradeScopeId();
//        String subjectId = scoSubjectScore.getSubjectId();
//
//        if(BizConstants.PERIOD.MIDDLE_SCHOOL.equals(gradeScopeId)){ //初中（语文、数学、英语 期末83.33% 政治、历史、地理 期末100%）
//
//            BigDecimal score = scoExamScores.getScore() != null ? scoExamScores.getScore() : new BigDecimal(0);
//            String scoresType = scoExamScores.getType();
//            //判断科目
//            if(BizConstants.SUBJECT_IDS.yuwen.equals(subjectId) || BizConstants.SUBJECT_IDS.shuxue.equals(subjectId) || BizConstants.SUBJECT_IDS.yingyu.equals(subjectId)){
//                if(BizConstants.SCORES_TYPE.FINAL.equals(scoresType)){
//                    scoSubjectScore.setRemark("初中请长假");
//                    BigDecimal ratio = new BigDecimal(83.33);
//                    BigDecimal resScore = ratio.multiply(score).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
//                    scoSubjectScore.addScore(resScore);
//                }
//            }else if(BizConstants.SUBJECT_IDS.zhengzhi.equals(subjectId) || BizConstants.SUBJECT_IDS.lishi.equals(subjectId) || BizConstants.SUBJECT_IDS.dili.equals(subjectId)){
//                if(BizConstants.SCORES_TYPE.FINAL.equals(scoresType)) {
//                    scoSubjectScore.setRemark("初中请长假");
//                    BigDecimal ratio = new BigDecimal(100);
//                    BigDecimal resScore = ratio.multiply(score).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
//                    scoSubjectScore.addScore(resScore);
//                }
//            }else{
//                calCommonScores(scoSubjectScore,scoExamScores,sysScoresRuleConfig);
//            }
//        }else if(BizConstants.PERIOD.HIGH_SCHOOL.equals(gradeScopeId)){ //高中（语文、数学、英语 期末66.67% 政治、历史、地理 期末100%）
//            BigDecimal score = scoExamScores.getScore() != null ? scoExamScores.getScore() : new BigDecimal(0);
//            String scoresType = scoExamScores.getType();
//            //判断科目
//            if(BizConstants.SUBJECT_IDS.yuwen.equals(subjectId) || BizConstants.SUBJECT_IDS.shuxue.equals(subjectId) || BizConstants.SUBJECT_IDS.yingyu.equals(subjectId)){
//                if(BizConstants.SCORES_TYPE.FINAL.equals(scoresType)){
//                    scoSubjectScore.setRemark("高中请长假");
//                    BigDecimal ratio = new BigDecimal(66.67);
//                    BigDecimal resScore = ratio.multiply(score).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
//                    scoSubjectScore.addScore(resScore);
//                }
//            }else if(BizConstants.SUBJECT_IDS.zhengzhi.equals(subjectId) || BizConstants.SUBJECT_IDS.lishi.equals(subjectId) || BizConstants.SUBJECT_IDS.dili.equals(subjectId)){
//                if(BizConstants.SCORES_TYPE.FINAL.equals(scoresType)) {
//                    scoSubjectScore.setRemark("高中请长假");
//                    BigDecimal ratio = new BigDecimal(100);
//                    BigDecimal resScore = ratio.multiply(score).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
//                    scoSubjectScore.addScore(resScore);
//                }
//            }else{
//                calCommonScores(scoSubjectScore,scoExamScores,sysScoresRuleConfig);
//            }
//        }
//        return scoSubjectScore;
//    }

    //计算期中缓考
//    public ScoSubjectScores calMidDelayScores(ScoSubjectScores scoSubjectScore,ScoExamScores scoExamScores,SysScoresRuleConfig sysScoresRuleConfig){
//        String gradeScopeId = sysScoresRuleConfig.getGradeScopeId();
//        String subjectId = scoSubjectScore.getSubjectId();
//
//        if(BizConstants.PERIOD.MIDDLE_SCHOOL.equals(gradeScopeId)){ //初中（语文、数学、英语 期中0%+期末50%+平时40% --- 政治、历史、地理 期中0%+期末60%+平时40%）
//            BigDecimal score = scoExamScores.getScore() != null ? scoExamScores.getScore() : new BigDecimal(0);
//            String scoresType = scoExamScores.getType();
//            //判断科目
//            if(BizConstants.SUBJECT_IDS.yuwen.equals(subjectId) || BizConstants.SUBJECT_IDS.shuxue.equals(subjectId) || BizConstants.SUBJECT_IDS.yingyu.equals(subjectId)){
//                if(BizConstants.SCORES_TYPE.FINAL.equals(scoresType)){ //期末成绩
//                    scoSubjectScore.setRemark("初中期中缓考");
//                    BigDecimal ratio = new BigDecimal(50);
//                    BigDecimal resScore = ratio.multiply(score).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
//                    scoSubjectScore.addScore(resScore);
//                }else if(BizConstants.SCORES_TYPE.USUAL.equals(scoresType)){ //平时成绩
//                    scoSubjectScore.setRemark("初中期中缓考");
//                    BigDecimal ratio = new BigDecimal(40);
//                    BigDecimal resScore = ratio.multiply(score).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
//                    scoSubjectScore.addScore(resScore);
//                }
//            }else if(BizConstants.SUBJECT_IDS.zhengzhi.equals(subjectId) || BizConstants.SUBJECT_IDS.lishi.equals(subjectId) || BizConstants.SUBJECT_IDS.dili.equals(subjectId)){//
//                if(BizConstants.SCORES_TYPE.FINAL.equals(scoresType)) {// 期末成绩
//                    scoSubjectScore.setRemark("初中期中缓考");
//                    BigDecimal ratio = new BigDecimal(60);
//                    BigDecimal resScore = ratio.multiply(score).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
//                    scoSubjectScore.addScore(resScore);
//                }else if(BizConstants.SCORES_TYPE.USUAL.equals(scoresType)){ //平时成绩
//                    scoSubjectScore.setRemark("初中期中缓考");
//                    BigDecimal ratio = new BigDecimal(40);
//                    BigDecimal resScore = ratio.multiply(score).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
//                    scoSubjectScore.addScore(resScore);
//                }
//            }else{
//                calCommonScores(scoSubjectScore,scoExamScores,sysScoresRuleConfig);
//            }
//        }else if(BizConstants.PERIOD.HIGH_SCHOOL.equals(gradeScopeId)){//高中（语文、数学、英语 期中0%+期末40%+平时40% --- 政治、历史、地理 期中0%+期末60%+平时40%）
//            BigDecimal score = scoExamScores.getScore() != null ? scoExamScores.getScore() : new BigDecimal(0);
//            String scoresType = scoExamScores.getType();
//            //判断科目
//            if(BizConstants.SUBJECT_IDS.yuwen.equals(subjectId) || BizConstants.SUBJECT_IDS.shuxue.equals(subjectId) || BizConstants.SUBJECT_IDS.yingyu.equals(subjectId)){
//                if(BizConstants.SCORES_TYPE.FINAL.equals(scoresType)){//期末成绩
//                    scoSubjectScore.setRemark("高中期中缓考");
//                    BigDecimal ratio = new BigDecimal(40);
//                    BigDecimal resScore = ratio.multiply(score).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
//                    scoSubjectScore.addScore(resScore);
//                }else if(BizConstants.SCORES_TYPE.USUAL.equals(scoresType)){ //平时成绩
//                    scoSubjectScore.setRemark("初中期中缓考");
//                    BigDecimal ratio = new BigDecimal(40);
//                    BigDecimal resScore = ratio.multiply(score).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
//                    scoSubjectScore.addScore(resScore);
//                }
//            }else if(BizConstants.SUBJECT_IDS.zhengzhi.equals(subjectId) || BizConstants.SUBJECT_IDS.lishi.equals(subjectId) || BizConstants.SUBJECT_IDS.dili.equals(subjectId)){
//                if(BizConstants.SCORES_TYPE.FINAL.equals(scoresType)) {//期末成绩
//                    scoSubjectScore.setRemark("高中期中缓考");
//                    BigDecimal ratio = new BigDecimal(60);
//                    BigDecimal resScore = ratio.multiply(score).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
//                    scoSubjectScore.addScore(resScore);
//                }else if(BizConstants.SCORES_TYPE.USUAL.equals(scoresType)){ //平时成绩
//                    scoSubjectScore.setRemark("初中期中缓考");
//                    BigDecimal ratio = new BigDecimal(40);
//                    BigDecimal resScore = ratio.multiply(score).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
//                    scoSubjectScore.addScore(resScore);
//                }
//            }else{
//                calCommonScores(scoSubjectScore,scoExamScores,sysScoresRuleConfig);
//            }
//        }
//        return scoSubjectScore;
//    }

    public ScoSubjectScores calMidDelayScores(ScoSubjectScores scoSubjectScore){
        String gradeScopeId = scoSubjectScore.getGradeScopeId();
        String subjectId = scoSubjectScore.getSubjectId();

        if(BizConstants.PERIOD.MIDDLE_SCHOOL.equals(gradeScopeId)){ //初中（语文、数学、英语 期中0%+期末50%+平时40% --- 政治、历史、地理 期中0%+期末60%+平时40%）
            //判断科目
            scoSubjectScore.setScore(new BigDecimal(0));
            if(BizConstants.SUBJECT_IDS.yuwen.equals(subjectId) || BizConstants.SUBJECT_IDS.shuxue.equals(subjectId) || BizConstants.SUBJECT_IDS.yingyu.equals(subjectId)){
                    scoSubjectScore.setRemark("初中期中缓考(语数外)");
                    BigDecimal fratio = new BigDecimal(50);
                    BigDecimal fresScore = fratio.multiply(scoSubjectScore.getFinalScore()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
                    scoSubjectScore.addScore(fresScore);

                    scoSubjectScore.setRemark("初中期中缓考（语数外）");
                    BigDecimal uratio = new BigDecimal(40);
                    BigDecimal uresScore = uratio.multiply(scoSubjectScore.getUsualScore()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
                    scoSubjectScore.addScore(uresScore);

            }else if(BizConstants.SUBJECT_IDS.zhengzhi.equals(subjectId) || BizConstants.SUBJECT_IDS.lishi.equals(subjectId) || BizConstants.SUBJECT_IDS.dili.equals(subjectId)){
                    scoSubjectScore.setRemark("初中期中缓考(政史地)");
                    BigDecimal fratio = new BigDecimal(60);
                    BigDecimal fresScore = fratio.multiply(scoSubjectScore.getFinalScore()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
                    scoSubjectScore.addScore(fresScore);

                    scoSubjectScore.setRemark("初中期中缓考（政史地）");
                    BigDecimal uratio = new BigDecimal(40);
                    BigDecimal uresScore = uratio.multiply(scoSubjectScore.getUsualScore()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
                    scoSubjectScore.addScore(uresScore);
            }

        }else if(BizConstants.PERIOD.HIGH_SCHOOL.equals(gradeScopeId)){//高中（语文、数学、英语 期中0%+期末40%+平时40% --- 政治、历史、地理 期中0%+期末60%+平时40%）
            //判断科目
            if(BizConstants.SUBJECT_IDS.yuwen.equals(subjectId) || BizConstants.SUBJECT_IDS.shuxue.equals(subjectId) || BizConstants.SUBJECT_IDS.yingyu.equals(subjectId)){
                scoSubjectScore.setRemark("高中期中缓考(语数外)");
                BigDecimal fratio = new BigDecimal(40);
                BigDecimal fresScore = fratio.multiply(scoSubjectScore.getFinalScore()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
                scoSubjectScore.addScore(fresScore);

                scoSubjectScore.setRemark("高中期中缓考（语数外）");
                BigDecimal uratio = new BigDecimal(40);
                BigDecimal uresScore = uratio.multiply(scoSubjectScore.getUsualScore()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
                scoSubjectScore.addScore(uresScore);

            }else if(BizConstants.SUBJECT_IDS.zhengzhi.equals(subjectId) || BizConstants.SUBJECT_IDS.lishi.equals(subjectId) || BizConstants.SUBJECT_IDS.dili.equals(subjectId)){
                scoSubjectScore.setRemark("高中期中缓考(政史地)");
                BigDecimal fratio = new BigDecimal(60);
                BigDecimal fresScore = fratio.multiply(scoSubjectScore.getFinalScore()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
                scoSubjectScore.addScore(fresScore);

                scoSubjectScore.setRemark("高中期中缓考（政史地）");
                BigDecimal uratio = new BigDecimal(40);
                BigDecimal uresScore = uratio.multiply(scoSubjectScore.getUsualScore()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
                scoSubjectScore.addScore(uresScore);
            }
//            if(BizConstants.SUBJECT_IDS.yuwen.equals(subjectId) || BizConstants.SUBJECT_IDS.shuxue.equals(subjectId) || BizConstants.SUBJECT_IDS.yingyu.equals(subjectId)){
//                if(BizConstants.SCORES_TYPE.FINAL.equals(scoresType)){//期末成绩
//                    scoSubjectScore.setRemark("高中期中缓考");
//                    BigDecimal ratio = new BigDecimal(40);
//                    BigDecimal resScore = ratio.multiply(score).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
//                    scoSubjectScore.addScore(resScore);
//                }else if(BizConstants.SCORES_TYPE.USUAL.equals(scoresType)){ //平时成绩
//                    scoSubjectScore.setRemark("初中期中缓考");
//                    BigDecimal ratio = new BigDecimal(40);
//                    BigDecimal resScore = ratio.multiply(score).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
//                    scoSubjectScore.addScore(resScore);
//                }
//            }else if(BizConstants.SUBJECT_IDS.zhengzhi.equals(subjectId) || BizConstants.SUBJECT_IDS.lishi.equals(subjectId) || BizConstants.SUBJECT_IDS.dili.equals(subjectId)){
//                if(BizConstants.SCORES_TYPE.FINAL.equals(scoresType)) {//期末成绩
//                    scoSubjectScore.setRemark("高中期中缓考");
//                    BigDecimal ratio = new BigDecimal(60);
//                    BigDecimal resScore = ratio.multiply(score).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
//                    scoSubjectScore.addScore(resScore);
//                }else if(BizConstants.SCORES_TYPE.USUAL.equals(scoresType)){ //平时成绩
//                    scoSubjectScore.setRemark("初中期中缓考");
//                    BigDecimal ratio = new BigDecimal(40);
//                    BigDecimal resScore = ratio.multiply(score).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
//                    scoSubjectScore.addScore(resScore);
//                }
//            }else{
//                calCommonScores(scoSubjectScore,scoExamScores,sysScoresRuleConfig);
//            }
        }
        return scoSubjectScore;
    }

    //计算常规成绩
    public ScoSubjectScores calCommonScores(ScoSubjectScores scoSubjectScore,ScoExamScores scoExamScores,SysScoresRuleConfig sysScoresRuleConfig){
        String scoresType = scoExamScores.getType();
        BigDecimal score = scoExamScores.getScore() != null ? scoExamScores.getScore() : new BigDecimal(0);
        if(BizConstants.SCORES_TYPE.MID.equals(scoresType)){ //期中
            scoSubjectScore.setHasMidScore(true);
            BigDecimal ratio = new BigDecimal(sysScoresRuleConfig.getMiddelRatio());
            BigDecimal resScore = ratio.multiply(score).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
            scoSubjectScore.addScore(resScore);
        }else if(BizConstants.SCORES_TYPE.FINAL.equals(scoresType)){//期末
            scoSubjectScore.setHasEndScore(true);
            BigDecimal ratio = new BigDecimal(sysScoresRuleConfig.getEndRatio());
            BigDecimal resScore = ratio.multiply(score).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
            scoSubjectScore.addScore(resScore);
        }else if(BizConstants.SCORES_TYPE.USUAL.equals(scoresType)){//平时
            scoSubjectScore.setHasUsualScore(true);
            BigDecimal ratio = new BigDecimal(sysScoresRuleConfig.getUsualRatio());
            BigDecimal resScore = ratio.multiply(score).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
            scoSubjectScore.addScore(resScore);
        }
        return scoSubjectScore;
    }

    public PageBean getTchScoreRulePageData(PageBean pageBean, String userId) {
        //获取教师信息
        SysTeacherInfo sysTeacherInfo = this.sysTeacherInfoMapper.selectTeacherInfoByUserId(userId);
        if(sysTeacherInfo == null){
            throw new BizException("当前非教师用户,不可使用此功能!");
        }
        List resList = sysTchScoresRuleConfMapper.selectTchScoreRulePageData(pageBean,sysTeacherInfo.getId());
        pageBean.setData(resList);
        return pageBean;
    }

    /**
     * 教师根据计算规则计算成绩
     * @param ruleId
     */
    public void calTchSocresByRuleId(String ruleId, String userId, String openCourseId,String scoreType) {
        //获取规则信息
        SysTchScoresRuleConf tchScoresRuleConf = this.sysTchScoresRuleConfMapper.selectScoreRuleById(ruleId);
        SysTeacherInfo sysTeacherInfo = this.sysTeacherInfoMapper.selectTeacherInfoByUserId(userId);
        String subjectId = tchScoresRuleConf.getSubjectId();
        if(sysTeacherInfo == null){
            throw new BizException("当前非教师用户,不可使用此功能!");
        }

        List<ScoExamScores> scoExamScoresList = this.sysScoresRuleConfigMapper.selectExaScoresByExam(openCourseId, scoreType);
        Map<String,ScoSubjectScores> subjectScoresMap = new HashMap<String, ScoSubjectScores>();
        for(int i= 0 ;i < scoExamScoresList.size(); i++){
            ScoExamScores scoExamScores = scoExamScoresList.get(i);
            String stuId =scoExamScores.getStuId();
            String scoresType = scoExamScores.getType();
            BigDecimal score = scoExamScores.getScore() != null ? scoExamScores.getScore() : new BigDecimal(0);
            String schoolYear = scoExamScores.getSchoolYear();
            String term = scoExamScores.getTerm();

            ScoSubjectScores scoSubjectScore = subjectScoresMap.get(stuId);

            if(scoSubjectScore == null){
                scoSubjectScore = new ScoSubjectScores();
                subjectScoresMap.put(stuId,scoSubjectScore);
                scoSubjectScore.setStuId(stuId);
                scoSubjectScore.setSubjectId(subjectId);
                scoSubjectScore.setSchoolYear(schoolYear);
                scoSubjectScore.setTerm(scoExamScores.getTerm());
                scoSubjectScore.setCreateTime(new Date());
                scoSubjectScore.setCreator(userId);
                scoSubjectScore.setClassName(scoExamScores.getClassText());
                scoSubjectScore.setGradeId(scoExamScores.getGrade());
                scoSubjectScore.setStatus(BizConstants.SCORES_SUBJECT_STATUS.UNPUBLISH);
                scoSubjectScore.setFlag(Integer.valueOf(BizConstants.SCORES_SUBJECT_STATUS.NORMAL));
            }

            if(BizConstants.SCORES_TYPE.MID.equals(scoresType)){
                scoSubjectScore.setHasMidScore(true);
                BigDecimal ratio = new BigDecimal(tchScoresRuleConf.getMiddelRatio());
                BigDecimal resScore = ratio.multiply(score).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
                scoSubjectScore.addScore(resScore);
            }else if(BizConstants.SCORES_TYPE.FINAL.equals(scoresType)){
                scoSubjectScore.setHasEndScore(true);
                BigDecimal ratio = new BigDecimal(tchScoresRuleConf.getEndRatio());
                BigDecimal resScore = ratio.multiply(score).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
                scoSubjectScore.addScore(resScore);
            }else if(BizConstants.SCORES_TYPE.USUAL.equals(scoresType)){
                scoSubjectScore.setHasUsualScore(true);
                BigDecimal ratio = new BigDecimal(tchScoresRuleConf.getUsualRatio());
                BigDecimal resScore = ratio.multiply(score).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
                scoSubjectScore.addScore(resScore);
            }
            //删除改学生的原有成绩
            this.scoSubjectScoresMapper.deleteBySubjectAndYearTermStuId(subjectId,schoolYear, term,stuId);
        }

        //循环map,新增学生成绩
        List<ScoSubjectScores> scoSubjectScoresList = new ArrayList<ScoSubjectScores>();
        for(ScoSubjectScores subjectScore : subjectScoresMap.values()){
            if(!subjectScore.isHasEndScore()){
                subjectScore.addRemark("无期末成绩");
                subjectScore.setFlag(Integer.valueOf(BizConstants.SCORES_SUBJECT_STATUS.EXCAPTION));
            }else if(!subjectScore.isHasMidScore()){
                subjectScore.addRemark("无期中成绩");
                subjectScore.setFlag(Integer.valueOf(BizConstants.SCORES_SUBJECT_STATUS.EXCAPTION));
            }else if(!subjectScore.isHasUsualScore()){
                subjectScore.addRemark("无平时成绩");
                subjectScore.setFlag(Integer.valueOf(BizConstants.SCORES_SUBJECT_STATUS.EXCAPTION));
            }
            subjectScore.setId(BizConstants.generatorPid());
            subjectScore.intScore();
            scoSubjectScoresList.add(subjectScore);
        }
        int scoresNum = scoSubjectScoresList.size();
        if(scoresNum > 0){
            logger.info("计算生成"+scoresNum+"条数据!");
            this.scoSubjectScoresMapper.batchAddSubjectScores(scoSubjectScoresList);
        }else{
            logger.info("计算生成"+"0条数据!");
        }

    }


    public void addTchScoreRule(SysTchScoresRuleConf tchScoresRuleConf) {
        tchScoresRuleConf.setId(BizConstants.generatorPid());
        sysTchScoresRuleConfMapper.insert(tchScoresRuleConf);
    }

    public SysTchScoresRuleConf getTchScoreRuleById(String ruleId) {
        SysTchScoresRuleConf tchScoresRuleConf = sysTchScoresRuleConfMapper.selectScoreRuleById(ruleId);
        return tchScoresRuleConf;
    }

    public boolean modTchScoreRule(SysTchScoresRuleConf tchScoresRuleConf) {
        sysTchScoresRuleConfMapper.updateByPrimaryKeySelective(tchScoresRuleConf);
        return true;
    }


    /**
     * 设置分数计算规则关系
     * @param ruleId
     * @param openCourseIds
     * @return
     */
    public boolean setRuleRel(String ruleId, String[] openCourseIds) {
        List<SysScoresRuleRelKey> datalist = new ArrayList<SysScoresRuleRelKey>();
        for(int i=0 ;i < openCourseIds.length ; i++){
            String openCourseId = openCourseIds[i];
            SysScoresRuleRelKey sysScoresRuleRelKey = new SysScoresRuleRelKey();
            sysScoresRuleRelKey.setRuleId(ruleId);
            sysScoresRuleRelKey.setCourseId(openCourseId);
            datalist.add(sysScoresRuleRelKey);
        }

        //批量删除关系
        sysScoresRuleRelMapper.batchDeleteRels(datalist);

        //批量添加关系
        sysScoresRuleRelMapper.batchInsertRels(datalist);
        return true;
    }

    public boolean clearRuleRel(String ruleId) {
        sysScoresRuleRelMapper.clearRuleRel(ruleId);
        return true;
    }

    public boolean genGraduationScores(String schoolYear) {
        //删除原有学年毕业成绩
        scoGraduationScoresMapper.delGraduationScoresByYear(schoolYear);
        //迁移新的学年毕业成绩
        scoGraduationScoresMapper.genGraduationScoresByYear(schoolYear);
        return true;
    }
}
