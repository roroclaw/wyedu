package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.*;
import com.roroclaw.base.bean.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExaStuExamInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ExaStuExamInfo record);

    int insertSelective(ExaStuExamInfo record);

    ExaStuExamInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExaStuExamInfo record);

    int updateByPrimaryKey(ExaStuExamInfo record);

    int updateAllExamStudentsStatusInExamPlan(ExaStuExamInfo record);

    //////////////////////////////
    List selectExamStudentsPageData(PageBean pageBean);

    List selectExamStudentsListByParam(ExaStuExamInfo record);

    List selectExamStudentsListByGrade(ExaStuExamInfo record);

    List selectExamStudentsCountByExamId(ExaStuExamInfo record);

    List selectByParam(ExaStuExamInfo record);

    List selectExamRoomStudentsListByParam(ExaExamRoomStudent record);

    List selectExamStudentsDetailList(ExaExamRoomStudent record);

    List selectExamStudentsPageDataByExamPlan(PageBean pageBean);

    int cleanExamRoomSettingInfoByExamPlanID(@Param("examPlanId")String examPlanId);

    int cleanExamRoomSettingInfo(ExaStuExamInfo record);

    int cleanExamStuInfoForStu(ExaStuExamInfo record);

    int cleanExamRoomSettingInfoByExamRoomId(ExaExamRoom record);

    List selectExamStudentsBlankList(ExaStuExamInfo record);

    int selectExamStudentsSettedCount(ExaStuExamInfo record);

    int selectExamStuUnsettingCount(ExaExamInfo record);

    int deleteStuExamInfoByParam(ExaStuExamInfo record);

    int deleteStuExamInfoByExamPlanId(@Param("examPlanId")String examPlanId);

    List<ExaStuExamInfo> selectStusByExamId(String examId);
}