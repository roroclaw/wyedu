package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.CmsArticle;
import com.roroclaw.base.bean.PageBean;

import java.util.List;

public interface CmsArticleMapper {

    CmsArticle selectArticleByPrimaryKey(CmsArticle record);
    List selectArticlesPageDataByFolderId(PageBean pageBean);
    List selectNoticesPageDataByFolderId(PageBean pageBean);

    int insertArticle(CmsArticle record);
    int updateArticle(CmsArticle record);
    int deleteArticle(CmsArticle record);
}