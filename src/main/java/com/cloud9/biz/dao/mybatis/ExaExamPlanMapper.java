package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.ExaExamInfo;
import com.cloud9.biz.models.ExaExamPlan;
import com.roroclaw.base.bean.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExaExamPlanMapper {
    int deleteByPrimaryKey(String id);

    int insert(ExaExamPlan record);

    int insertSelective(ExaExamPlan record);

    ExaExamPlan selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExaExamPlan record);

    int updateByPrimaryKey(ExaExamPlan record);

    //////////////////////////////////////////////////
    List selectExamPlanPageData(PageBean pageBean);

    ExaExamPlan selectExamPlanByPrimaryKey(String id);

    List<ExaExamPlan> selectExamPlanByParam(@Param("type")String type ,@Param("status")String status ,@Param("schoolYear")String schoolYear);

    List selectExamPlanDetailListByExaExamPlanId(ExaExamPlan record);
}