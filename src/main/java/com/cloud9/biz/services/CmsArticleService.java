package com.cloud9.biz.services;

import com.cloud9.biz.dao.mybatis.CmsArticleMapper;
import com.cloud9.biz.models.CmsArticle;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.bean.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("articleService")
@Transactional
public class CmsArticleService {



    @Autowired
    private CmsArticleMapper cmsArticleMapper;

    public PageBean selectNoticesPageDataByFolderId(PageBean pageBean){
        List resList = this.cmsArticleMapper.selectNoticesPageDataByFolderId(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    public PageBean getArticlesPageDataByFolderId(PageBean pageBean){
        List resList = this.cmsArticleMapper.selectArticlesPageDataByFolderId(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    public CmsArticle getArticleInfosByID(CmsArticle ArticleInfo) {
        CmsArticle articleInfo= this.cmsArticleMapper.selectArticleByPrimaryKey(ArticleInfo);
        return articleInfo;
    }

    public boolean addArticleInfo(CmsArticle ArticleInfo) {
        String ID= BizConstants.generatorPid();
        ArticleInfo.setArticle_id(ID);
        ArticleInfo.setCreate_time(new Date());
        this.cmsArticleMapper.insertArticle(ArticleInfo);
        return true;
    }

    public boolean updateArticleInfo(CmsArticle ArticleInfo) {
        ArticleInfo.setUpdate_time(new Date());
        this.cmsArticleMapper.updateArticle(ArticleInfo);
        return true;
    }

    public boolean deleteArticleInfo(CmsArticle ArticleInfo) {
        ArticleInfo.setUpdate_time(new Date());
        this.cmsArticleMapper.deleteArticle(ArticleInfo);
        return true;
    }

}
