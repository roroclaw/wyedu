package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.TchStuCourseOpenRel;
import com.roroclaw.base.bean.PageBean;

import java.util.List;

public interface TchStuCourseOpenRelMapper {
    int deleteByPrimaryKey(String id);

    int insert(TchStuCourseOpenRel record);

    int insertSelective(TchStuCourseOpenRel record);

    TchStuCourseOpenRel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TchStuCourseOpenRel record);

    int updateByPrimaryKey(TchStuCourseOpenRel record);

    ///////自定义

    int selectTchStuCourseOpenRelCountByParam(TchStuCourseOpenRel record);

    int insertTchStuCourseOpenRelSelective(TchStuCourseOpenRel record);

    List selecTchCourseOpenStudentsPageData(PageBean pageBean);

    List selectCourseOpenStudentsList(TchStuCourseOpenRel record);

    List selectStuCourseOpenCheckList(TchStuCourseOpenRel record);

    int selectTchStuCourseOpenRelForCheck(TchStuCourseOpenRel record);

    int selectStuOpenCourseNum(String stuId);
}