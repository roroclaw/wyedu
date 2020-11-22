package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.SysImportLog;
import com.roroclaw.base.bean.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysImportLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysImportLog record);

    int insertSelective(SysImportLog record);

    SysImportLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysImportLog record);

    int updateByPrimaryKeyWithBLOBs(SysImportLog record);

    int updateByPrimaryKey(SysImportLog record);

    List<SysImportLog> selectStudentImpLogPageData(@Param("pageBean")PageBean pageBean,@Param("type") String type);
}