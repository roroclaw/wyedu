package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.ScoOtherExamScores;
import com.cloud9.biz.models.TchStuCourseOpenRel;
import com.roroclaw.base.bean.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoOtherExamScoresMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScoOtherExamScores record);

    int insertSelective(ScoOtherExamScores record);

    ScoOtherExamScores selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScoOtherExamScores record);

    int updateByPrimaryKey(ScoOtherExamScores record);

    void batchAddScoExamScores(@Param("scoresList") List<ScoOtherExamScores> scoresList);

    List<ScoOtherExamScores> selectExamScores4StatusPageData(@Param("pageBean")PageBean pageBean);

    List<ScoOtherExamScores> selectRecordScoresPageData(@Param("pageBean")PageBean pageBean,@Param("teacherId") String teacherId);

    int batchRecordScores(@Param("scoreList")List<ScoOtherExamScores> scoreList);

    List<ScoOtherExamScores> selectScoresDatas(@Param("examId")String examId,@Param("teacherId") String id);

    List<ScoOtherExamScores> selectExamScores4AdminModPageData(@Param("pageBean")PageBean pageBean);

    int updateRecordStatusByExamId(@Param("examId")String examId,@Param("recordStatus")String recordStatus);

}