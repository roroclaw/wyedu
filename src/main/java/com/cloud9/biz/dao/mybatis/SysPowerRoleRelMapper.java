package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.SysPowerRoleRel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPowerRoleRelMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysPowerRoleRel record);

    int insertSelective(SysPowerRoleRel record);

    SysPowerRoleRel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysPowerRoleRel record);

    int updateByPrimaryKey(SysPowerRoleRel record);

    int subRolePower(@Param("dataList")List<SysPowerRoleRel> powerIdArr);

    int delRolePowerByRoleId(String roleId);

    List<SysPowerRoleRel> getPowersByRoleId(String roleId);
}