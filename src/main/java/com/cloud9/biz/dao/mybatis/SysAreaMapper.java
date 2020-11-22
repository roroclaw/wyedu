package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.SysArea;
import com.cloud9.biz.models.SysDictItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAreaMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysArea record);

    int insertSelective(SysArea record);

    SysArea selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysArea record);

    int updateByPrimaryKey(SysArea record);

    List<SysDictItem> getAreasByType(@Param("type")String type);

    List<SysDictItem> getAreaItemsByPid(@Param("pid")String pid);

    List<SysArea> getAllAreaItems();
}