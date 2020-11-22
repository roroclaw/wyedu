package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.SysTeacherInfo;
import com.cloud9.biz.models.SysUser;
import com.cloud9.biz.models.SysTeacherInfo;
import com.cloud9.biz.models.SysRole;
import com.cloud9.biz.models.vo.VUserInfo;
import com.roroclaw.base.bean.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser selectByAccToken(String accToken);

    VUserInfo selectVUserByAccToken(String accToken);

    SysUser selectByAccount(String account);

    SysUser selectUserByParams(SysUser record);

    List selectUserPageData(PageBean pageBean);

    List selectRolesList(SysRole record);

    int selecCountByLoginName(String loginName);

    void batchAddUser(@Param("dataList")List<SysUser> userList);
}