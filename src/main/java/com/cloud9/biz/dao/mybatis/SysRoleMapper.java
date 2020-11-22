package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.SysRole;
import com.roroclaw.base.bean.ItemBean;
import com.cloud9.biz.models.SysUserRoleRel;
import com.roroclaw.base.bean.ItemBean;
import com.roroclaw.base.bean.PageBean;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<ItemBean> selectRoleItems();

    List selectRolesList();

    List<SysRole> selectRolesPageData(PageBean pageBean);

    List<SysRole> selectUserRoles(String userId);

    int selectCountByName(String name);
}