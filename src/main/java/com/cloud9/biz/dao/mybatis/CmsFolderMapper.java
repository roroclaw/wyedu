package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.SysUser;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.models.CmsFolder;
import com.roroclaw.base.bean.PageBean;

import java.util.List;

public interface CmsFolderMapper {

    CmsFolder selectFolderByPrimaryKey(CmsFolder record);
    List selectFlodersPageDataByFatherId(PageBean pageBean);
    List selectFoldersList(CmsFolder record);

    int insertFolder(CmsFolder record);
    int updateFolder(CmsFolder record);

}