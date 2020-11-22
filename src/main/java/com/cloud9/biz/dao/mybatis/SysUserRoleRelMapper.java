package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.SysRole;
import com.cloud9.biz.models.SysUserRoleRel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserRoleRelMapper {

    List selectUserRolesRelList(SysUserRoleRel record);

    int deleteUserRolesRelByPrimaryKey(String id);

    int insert(SysUserRoleRel record);

    int insertSelective(SysUserRoleRel record);

    SysUserRoleRel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUserRoleRel record);

    int updateByPrimaryKey(SysUserRoleRel record);

    int insertUserRolesRel(SysUserRoleRel record);

    int deleteUserRolesRelByUserId(String UserId);

    void batchInsert(@Param("dataList")List<SysUserRoleRel> userRoleList);

    int delByUserId(@Param("userId")String userId);
}