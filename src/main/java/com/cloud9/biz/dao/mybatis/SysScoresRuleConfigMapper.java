package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.ScoExamScores;
import com.cloud9.biz.models.SysScoresRuleConfig;
import com.roroclaw.base.bean.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysScoresRuleConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysScoresRuleConfig record);

    int insertSelective(SysScoresRuleConfig record);

    SysScoresRuleConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysScoresRuleConfig record);

    int updateByPrimaryKey(SysScoresRuleConfig record);

    List<SysScoresRuleConfig> selectScoreRulePageData(PageBean pageBean);

    SysScoresRuleConfig selectScoreRuleById(String id);

    SysScoresRuleConfig selectScoreRuleBySubjectId(String subjectId);

    List<ScoExamScores> selectExaScoresBySubjectId(@Param("subjectId")String subjectId,@Param("schoolYear")String schoolYear,@Param("term")String term);

    List<ScoExamScores> selectExaScoresBySubjectIdGrades(@Param("subjectId")String subjectId,@Param("schoolYear")String schoolYear,@Param("term")String term,@Param("gradeArr") String[] gradeArr);

    List<ScoExamScores> selectExaScoresByExam(@Param("openCourseId")String openCourseId,@Param("scoreType")String scoreType);
}