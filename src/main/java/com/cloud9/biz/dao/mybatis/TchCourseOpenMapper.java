package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.TchCourseOpen;
import com.cloud9.biz.models.TchStuCourseOpenRel;
import com.cloud9.biz.models.vo.VRecordConfig;
import com.roroclaw.base.bean.PageBean;

import java.util.List;

public interface TchCourseOpenMapper {
    int deleteByPrimaryKey(String id);

    int insert(TchCourseOpen record);

    int insertSelective(TchCourseOpen record);

    TchCourseOpen selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TchCourseOpen record);

    int updateByPrimaryKey(TchCourseOpen record);

    ///////自定义
    List selectCourseOpensPageData(PageBean pageBean);

    TchCourseOpen selectCourseOpensById(String id);

    List selectCourseOpenList(TchCourseOpen record);

    List<VRecordConfig> selectRecordConfigPageData(PageBean pageBean);
}