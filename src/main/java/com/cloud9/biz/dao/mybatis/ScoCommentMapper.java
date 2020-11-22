package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.ScoComment;
import com.roroclaw.base.bean.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoCommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScoComment record);

    int insertSelective(ScoComment record);

    ScoComment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScoComment record);

    int updateByPrimaryKeyWithBLOBs(ScoComment record);

    int updateByPrimaryKey(ScoComment record);

//    List<ScoComment> selectCommentByPage(@Param("term") String term,@Param("schoolYear") String schoolYear,@Param("userId") String userId);
    List<ScoComment> selectCommentByPage(@Param("pageBean")PageBean pageBean,@Param("teacherId")String teacherId);

    ScoComment selectComment(@Param("stuId")String stuId,@Param("schoolYear") String schoolYear,@Param("term") String term);

    void batchCommentStatus(@Param("commentList")List<ScoComment> commentList);
}