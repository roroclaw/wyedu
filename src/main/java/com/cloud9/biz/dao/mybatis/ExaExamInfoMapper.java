package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.ExaExamInfo;
import com.cloud9.biz.models.vo.VRecordConfig;
import com.roroclaw.base.bean.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExaExamInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ExaExamInfo record);

    int insertSelective(ExaExamInfo record);

    ExaExamInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExaExamInfo record);

    int updateByPrimaryKey(ExaExamInfo record);
    //////////////////////////////////////////////////
    List selectExamInfoPageData(PageBean pageBean);

    ExaExamInfo selectExamByPrimaryKey(String id);

    List<ExaExamInfo> selectExamByParam(@Param("examPlanId")String examPlanId ,@Param("status")String status);

    List<ExaExamInfo> selectExamInfoByParam(ExaExamInfo record);

    List<ExaExamInfo> selectExamDateByParam(ExaExamInfo record);

    List<VRecordConfig> selectOtherRecordConfigPageData(PageBean pageBean);

    VRecordConfig selectInfosById(String examId);
}