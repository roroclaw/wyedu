package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.SysDictItem;
import com.cloud9.biz.models.SysSubjectInfo;
import com.roroclaw.base.bean.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysSubjectInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysSubjectInfo record);

    int insertSelective(SysSubjectInfo record);

    SysSubjectInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysSubjectInfo record);

    int updateByPrimaryKey(SysSubjectInfo record);

    ////////自定义

    List selectSubjectPageData(PageBean pageBean);

    List selectSubjectsListByParam(SysSubjectInfo record);

    List selectByParam(SysSubjectInfo record);

    List<SysSubjectInfo> selectSubjectsListByParam(@Param("type")String type ,@Param("status")String status ,@Param("name")String name);


}