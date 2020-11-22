package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.ScoOtherExamScores;
import com.cloud9.biz.models.TchExamScoresConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TchExamScoresConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(TchExamScoresConfig record);

    int insertSelective(TchExamScoresConfig record);

    TchExamScoresConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TchExamScoresConfig record);

    int updateByPrimaryKey(TchExamScoresConfig record);

    int deleteByExamId(String examId);

    TchExamScoresConfig selectByExamId(String examId);

    int updateOtherStatusByExamId(@Param("examId") String examId,@Param("status") String status);

    List<String> validateTeacherUserIdByScore(@Param("userId")String userId,@Param("scoreList")List<ScoOtherExamScores> scoreList);
}