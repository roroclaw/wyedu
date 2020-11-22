package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.ScoExamScores;
import com.cloud9.biz.models.ScoExamScoresForQuery;
import com.cloud9.biz.models.vo.VScoreQuery;
import com.roroclaw.base.bean.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoExamScoresMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScoExamScores record);

    int insertSelective(ScoExamScores record);

    ScoExamScores selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScoExamScores record);

    int updateByPrimaryKey(ScoExamScores record);

    List selectByParam(ScoExamScores record);

    int updateByParams(ScoExamScores record);

    int deleteScoExamScoresInfoByParam(ScoExamScores record);



    /**
     * 分页查询登分成绩信息
     * @param pageBean
     * @return
     */
    List<ScoExamScores> selectRecordScoresPageData(@Param("pageBean")PageBean pageBean,@Param("teacherId")String teacherId);

    /**
     * 批量提交登分成绩
     * @param scores
     * @return
     */
    int batchSubRecordScores(@Param("scoresList")List<ScoExamScores> scores);

    int batchAddScoExamScores(@Param("scoresList") List<ScoExamScores> scoList);

    List<ScoExamScores> selectExamScores4StatusPageData(@Param("pageBean")PageBean pageBean);

    /**
     * 成绩查询 by zl
     */
    List selectScoreCousesForQueryListByParam(ScoExamScoresForQuery record);

    List selectScoreStuForQueryListByParam(ScoExamScoresForQuery record);

    List selectScoreStuForScoreCollectingQueryByParam(ScoExamScoresForQuery record);

    List getCourseItemsForPower(@Param("type")String type,@Param("graduateYear")String graduateYear,@Param("term")String term,@Param("teacherId")String teacherId);

    List<VScoreQuery> stuQueryScorePageData(@Param("pageBean")PageBean pageBean,@Param("stuId") String stuId,@Param("recordStatus") String recordStatus,@Param("subjectScoreStatus") String subjectScoreStatus);

    int batchRecordScores(@Param("scoreList")List<ScoExamScores> scoreList);


    List<ScoExamScores> selectScoresDatas(@Param("openCourseId")String openCourseId,@Param("scoreType")  String scoreType,@Param("teacherId") String teacherId);

    List<ScoExamScores> selectExamScores4AdminModPageData(@Param("pageBean")PageBean pageBean);

    int updateRecordStatusByOpenCourseId(@Param("openCourseId")String openCourseId, @Param("scoreType")String scoresType,@Param("recordStatus") String recordStatus);
}