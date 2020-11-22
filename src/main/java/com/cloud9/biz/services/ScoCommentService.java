package com.cloud9.biz.services;

import com.cloud9.biz.dao.mybatis.ScoCommentMapper;
import com.cloud9.biz.models.ScoComment;
import com.cloud9.biz.models.ScoSubjectScores;
import com.cloud9.biz.models.vo.VRecordConfig;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 评论service
 */
@Service("scoCommentService")
@Transactional
public class ScoCommentService extends BaseService {

    @Autowired
    private ScoCommentMapper scoCommentMapper;

    public PageBean getScoCommentPageData(PageBean pageBean,String teacherId) {
        List<ScoComment> resList = this.scoCommentMapper.selectCommentByPage(pageBean,teacherId);
        pageBean.setData(resList);
        return pageBean;
    }

    public ScoComment getComment(String stuId, String schoolYear, String term) {
        ScoComment scoComment = null;
        scoComment = this.scoCommentMapper.selectComment(stuId,schoolYear,term);
        return scoComment;
    }

    public int addComment(ScoComment scoComment) {
        return this.scoCommentMapper.insertSelective(scoComment);
    }

    public int updateComment(ScoComment scoComment) {
        return this.scoCommentMapper.updateByPrimaryKeySelective(scoComment);
    }

    public boolean batchPublishComment(String[] idArr) {
        List<ScoComment> commentList = new ArrayList<ScoComment>();
        for (int i = 0; i < idArr.length; i++){
            String id = idArr[i];
            ScoComment scoComment = new ScoComment();
            scoComment.setId(id);
            scoComment.setStatus(BizConstants.SCO_COMMENT_STATUS.PUBLISH);
            commentList.add(scoComment);
        }
        this.scoCommentMapper.batchCommentStatus(commentList);
        return true;
    }
}
