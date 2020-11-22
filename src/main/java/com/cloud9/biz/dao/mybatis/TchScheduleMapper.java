package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.TchSchedule;
import com.cloud9.biz.models.TchScheduleCourseOpen;
import com.cloud9.biz.models.TchScheduleSection;
import com.roroclaw.base.bean.PageBean;

import java.util.List;

public interface TchScheduleMapper {
    int deleteByPrimaryKey(String id);

    int insert(TchSchedule record);

    int insertSelective(TchSchedule record);

    TchSchedule selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TchSchedule record);

    int updateByPrimaryKey(TchSchedule record);

//////////////////////////////////
    List selectSchedulePageData(PageBean pageBean);

    TchSchedule getScheduleById(String id);

    List<TchScheduleSection> selectScheduleSectionList();

}