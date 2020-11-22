package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.SysMenus;

import java.util.List;

public interface SysMenusMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysMenus record);

    int insertSelective(SysMenus record);

    SysMenus selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysMenus record);

    int updateByPrimaryKey(SysMenus record);

    List selectFirstMenusByUserId(String userId);

    List<SysMenus> selectFirstMenus();

    List selectSubMenusByUserId(String userId, String menuId);

    List selectSubMenus(String menuId);

    List<SysMenus> selectAllMenusByUserId(String userId);

    List<SysMenus> selectAllMenus();

    List<SysMenus> selectAllMenusByRoleId(String roleId);
}