package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.ArcStudentExt;

public interface ArcStudentExtMapper {
    int deleteByPrimaryKey(String id);

    int insert(ArcStudentExt record);

    int insertSelective(ArcStudentExt record);

    ArcStudentExt selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ArcStudentExt record);

    int updateByPrimaryKey(ArcStudentExt record);

    ArcStudentExt selectByStuId(String stuId);
}