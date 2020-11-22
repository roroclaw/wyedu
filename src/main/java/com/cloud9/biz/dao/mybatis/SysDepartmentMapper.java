package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.SysDepartment;
import com.roroclaw.base.bean.PageBean;

import java.util.List;

public interface SysDepartmentMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysDepartment record);

    int insertSelective(SysDepartment record);

    SysDepartment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysDepartment record);

    int updateByPrimaryKey(SysDepartment record);

    List selectDepartsPageDataByFatherId(PageBean pageBean);

    List selectDepartsList(SysDepartment record);

    SysDepartment selectDepartByPrimaryKey(SysDepartment record);

}