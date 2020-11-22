package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.SysDictItem;
import com.cloud9.biz.models.TchStuCourseOpenRel;
import com.cloud9.biz.models.TchTchplanSubjectRel;
import com.cloud9.biz.models.TchTeachingPlan;
import com.cloud9.biz.models.vo.VTeachPlan;
import com.roroclaw.base.bean.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TchTeachingPlanMapper {
    int deleteByPrimaryKey(String id);

    int insert(TchTeachingPlan record);

    int insertSelective(TchTeachingPlan record);

    TchTeachingPlan selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TchTeachingPlan record);

    int updateByPrimaryKey(TchTeachingPlan record);

    ////////自定义

    List selectTeachingPlanPageData(PageBean pageBean);

    TchTeachingPlan selectTchPlanByPrimaryKey(String id);

    List checkTeachPlanAcco(TchStuCourseOpenRel record);


    /**
     * 获取学级方向教学计划数
     * @param subMajorId 方向
     * @param enrolYear 学级
     * @return
     */
    int selectCountPlanByYearSubMajorId(@Param("subMajorId")String subMajorId,@Param("enrolYear") String enrolYear);

    TchTeachingPlan selectTchTeachingPlan(@Param("subMajorId")String subMajorId,@Param("year") String year,@Param("period") String period);

    List<VTeachPlan> selectTeachingPlanItemsByParams(@Param("subMajorId")String subMajorId,@Param("period")String period);

}