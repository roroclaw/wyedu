package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.SysDictItem;
import com.cloud9.biz.models.SysMajor;
import com.cloud9.biz.models.vo.MajorItem;
import com.roroclaw.base.bean.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMajorMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysMajor record);

    int insertSelective(SysMajor record);

    SysMajor selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysMajor record);

    int updateByPrimaryKey(SysMajor record);

    /////////////////
    SysMajor selectByID(String id);

    List selectMajorPageData(PageBean pageBean);

    List<SysDictItem> getAllMajorItems();

    List<SysDictItem> getFatherMajorItems(@Param("type")String type);




    List<MajorItem> getAllSubMajorItems();

}