package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.SysDictType;

public interface SysDictTypeMapper {
    int deleteByPrimaryKey(String code);

    int insert(SysDictType record);

    int insertSelective(SysDictType record);

    SysDictType selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(SysDictType record);

    int updateByPrimaryKey(SysDictType record);
}