package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.SysDictItem;
import com.cloud9.biz.models.SysGrades;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysGradesMapper {
    int deleteByPrimaryKey(String code);

    int insert(SysGrades record);

    int insertSelective(SysGrades record);

    SysGrades selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(SysGrades record);

    int updateByPrimaryKey(SysGrades record);

    List<SysDictItem> getAllGradeItems();

    List<SysDictItem> getGradeItemsByPeriod(@Param("period")String period);
}