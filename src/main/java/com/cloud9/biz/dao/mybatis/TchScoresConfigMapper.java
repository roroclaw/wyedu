package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.ScoExamScores;
import com.cloud9.biz.models.TchScoresConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TchScoresConfigMapper {
    int deleteByPrimaryKey(String id);

    int insert(TchScoresConfig record);

    int insertSelective(TchScoresConfig record);

    TchScoresConfig selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TchScoresConfig record);

    int updateByPrimaryKey(TchScoresConfig record);

    int deleteByOpenCourseId(@Param("openCourseId")String openCourseId,@Param("scoresType")String scoresType);

    TchScoresConfig selectConfigByOpenCourseId(@Param("openCourseId")String openCourseId,@Param("scoresType")String scoresType);

    int updateStatusByOpenCourseId(@Param("openCourseId")String openCourseId,@Param("scoresType")String scoresType,@Param("status")String status);

    int selectRecordTeacherCountByUserId(@Param("userId")String userId,@Param("scoreId")String scoreId);

    List<String> validateTeacherUserIdByScore(@Param("userId")String userId,@Param("scoreList")List<ScoExamScores> scoreList);
}