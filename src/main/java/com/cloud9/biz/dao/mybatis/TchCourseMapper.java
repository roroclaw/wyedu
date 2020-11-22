package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.TchCourse;
import com.cloud9.biz.models.TchCourseWithBLOBs;
import com.roroclaw.base.bean.PageBean;

import java.util.List;

public interface TchCourseMapper {
    int deleteByPrimaryKey(String id);

    int insert(TchCourseWithBLOBs record);

    int insertSelective(TchCourseWithBLOBs record);

    TchCourseWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TchCourseWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TchCourseWithBLOBs record);

    int updateByPrimaryKey(TchCourse record);

    ///////自定义
    List selectCoursesPageData(PageBean pageBean);

    // List selectCoursesGroupPageData(PageBean pageBean);

    List selectCoursesList(TchCourse courseInfo);
}