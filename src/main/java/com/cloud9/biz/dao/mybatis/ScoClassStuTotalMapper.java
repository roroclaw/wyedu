package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.ScoClassStuTotal;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ScoClassStuTotalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScoClassStuTotal record);

    int insertSelective(ScoClassStuTotal record);

    ScoClassStuTotal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScoClassStuTotal record);

    int updateByPrimaryKey(ScoClassStuTotal record);

    int deleteClassStuTotal(@Param("schoolYear")String schoolYear,@Param("term") String term,@Param("scoreType") String scoreType,@Param("grade") String grade);

    int calClassStuTotal(@Param("schoolYear")String schoolYear,@Param("term") String term,@Param("scoreType") String scoreType,@Param("grade") String grade);

    List<Map> select4ExamGradePrint(@Param("schoolYear")String schoolYear,@Param("term") String term, @Param("scoreType")String scoreType,@Param("grade") String grade);
}