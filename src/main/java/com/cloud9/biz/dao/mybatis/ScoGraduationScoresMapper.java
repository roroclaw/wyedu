package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.ScoGraduationScores;

public interface ScoGraduationScoresMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScoGraduationScores record);

    int insertSelective(ScoGraduationScores record);

    ScoGraduationScores selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScoGraduationScores record);

    int updateByPrimaryKey(ScoGraduationScores record);

    int delGraduationScoresByYear(String schoolYear);

    int genGraduationScoresByYear(String schoolYear);
}