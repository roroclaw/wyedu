package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.TchPlanOpenCourseRel;
import com.cloud9.biz.models.TchTchplanSubjectRel;
import com.roroclaw.base.bean.PageBean;


import java.util.List;

public interface TchTchplanSubjectRelMapper {
    int deleteByPrimaryKey(String id);

    int insert(TchTchplanSubjectRel record);

    int insertSelective(TchTchplanSubjectRel record);

    TchTchplanSubjectRel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TchTchplanSubjectRel record);

    int updateByPrimaryKey(TchTchplanSubjectRel record);

    ////////自定义

    int selectTchPlanSubjectRelCountByParam(TchTchplanSubjectRel record);

    List selectTchPlanRelListByTchPlanId(TchTchplanSubjectRel record);

    List selectTchPlanRelListByParam(TchTchplanSubjectRel record);

    List selectTchPlanRelSubjectsListByTchPlanId(TchTchplanSubjectRel record);

    List selectTchPlanSubjectPageData(PageBean pageBean);

   // List selectTchPlanCourseOpensList(TchTchplanSubjectRel record);

    List selectTchPlanCourseOpenCheckList(TchPlanOpenCourseRel record);
}