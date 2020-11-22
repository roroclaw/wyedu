package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.ScoClassScoreStatic;
import com.cloud9.biz.models.vo.StatisticsSectionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoClassScoreStaticMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScoClassScoreStatic record);

    int insertSelective(ScoClassScoreStatic record);

    ScoClassScoreStatic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScoClassScoreStatic record);

    int updateByPrimaryKey(ScoClassScoreStatic record);

    void deleteDatas(@Param("schoolYear")String schoolYear,@Param("term") String term,@Param("scoreType") String scoreType,@Param("grade") String grade);

    void calDatas(@Param("schoolYear")String schoolYear,@Param("term") String term,@Param("scoreType") String scoreType,@Param("grade") String grade,@Param("section") StatisticsSectionVo statisticsSectionVo);

    List<ScoClassScoreStatic> select4ExamClassPrint(@Param("schoolYear")String schoolYear,@Param("term") String term,@Param("scoreType") String scoreType,@Param("grade") String grade);
}