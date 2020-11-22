package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.ScoStuSubjctTotalAvg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoStuSubjctTotalAvgMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScoStuSubjctTotalAvg record);

    int insertSelective(ScoStuSubjctTotalAvg record);

    ScoStuSubjctTotalAvg selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScoStuSubjctTotalAvg record);

    int updateByPrimaryKey(ScoStuSubjctTotalAvg record);

    void calStuSubjctTotalAvg(@Param("schoolYear")String schoolYear,@Param("term") String term,@Param("scoreType") String scoreType,@Param("grade") String grade);

    void deleteStuSubjctTotalAvg(@Param("schoolYear")String schoolYear,@Param("term") String term,@Param("scoreType") String scoreType,@Param("grade") String grade);

    List<ScoStuSubjctTotalAvg> select4Print(@Param("schoolYear")String schoolYear,@Param("term") String term,@Param("scoreType") String scoreType,@Param("grade") String grade);


}