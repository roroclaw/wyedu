package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.ExaExamRoom;
import com.roroclaw.base.bean.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExaExamRoomMapper {
    int deleteByPrimaryKey(String id);

    int insert(ExaExamRoom record);

    int insertSelective(ExaExamRoom record);

    ExaExamRoom selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExaExamRoom record);

    int updateByPrimaryKey(ExaExamRoom record);
    ////////////////////////////////////

    List selectExamRoomInfoPageData(PageBean pageBean);

    List selectExamRoomListByParam(ExaExamRoom record);

    ExaExamRoom selectExamRoomByPrimaryKey(String id);

    List<ExaExamRoom> selectExamRoomByParam(@Param("buildingNo")String buildingNo,@Param("examPlanId")String examPlanId);

    int selectExamRoomForUsedCount(ExaExamRoom record);
}