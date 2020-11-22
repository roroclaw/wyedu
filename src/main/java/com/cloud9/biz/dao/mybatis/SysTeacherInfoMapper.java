package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.SysTeacherInfo;
import com.roroclaw.base.bean.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysTeacherInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysTeacherInfo record);

    int insertSelective(SysTeacherInfo record);

    SysTeacherInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysTeacherInfo record);

    int updateByPrimaryKey(SysTeacherInfo record);
    ////////自定义
    int selectActCountByUserId(String id);

    int selectActCountByWorkId(String code);

    int selectActCountByIdentityCard(String identityCard);

    List selectTeacherInfoPageData(PageBean pageBean);

    List selectTeachersListByParam(SysTeacherInfo record);


    List<SysTeacherInfo> selectTeacherListByKey(@Param("key")String key,@Param("numLimit")int numLimit);

    SysTeacherInfo selectTeacherInfoByUserId(String userId);
}