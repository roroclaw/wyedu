package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.ArcStudentPlanRel;
import com.roroclaw.base.bean.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArcStudentPlanRelMapper {
    int deleteByPrimaryKey(String id);

    int insert(ArcStudentPlanRel record);

    int insertSelective(ArcStudentPlanRel record);

    ArcStudentPlanRel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ArcStudentPlanRel record);

    int updateByPrimaryKey(ArcStudentPlanRel record);

    int batchInsertRels(@Param("dataList")List<ArcStudentPlanRel> tchPlanRelList);

    ArcStudentPlanRel selectByStuId(String id);

    int updatePlanByStuId(@Param("id")String id,@Param("planId")String planId);

    List selectStudentInfoPageDataByParam(PageBean pageBean);
}