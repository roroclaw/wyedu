package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.SysClassroom;
import com.roroclaw.base.bean.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysClassroomMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysClassroom record);

    int insertSelective(SysClassroom record);

    SysClassroom selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysClassroom record);

    int updateByPrimaryKeyWithBLOBs(SysClassroom record);

    int updateByPrimaryKey(SysClassroom record);

    List<SysClassroom> selectClassRoomByKey(String key);

    List selectClassroomPageData(PageBean pageBean);

    SysClassroom selectClassroomByPrimaryKey(String id);

    List<SysClassroom> selectClassRoomByParam(@Param("buildingNo")String buildingNo);
}