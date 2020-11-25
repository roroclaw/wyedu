package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.SysTchScoresRuleConf;
import com.roroclaw.base.bean.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysTchScoresRuleConfMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysTchScoresRuleConf record);

    int insertSelective(SysTchScoresRuleConf record);

    SysTchScoresRuleConf selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysTchScoresRuleConf record);

    int updateByPrimaryKey(SysTchScoresRuleConf record);

    List<SysTchScoresRuleConf> selectTchScoreRulePageData(@Param("pageBean")PageBean pageBean,@Param("teacherId")String teacherId);

    SysTchScoresRuleConf selectScoreRuleById(@Param("ruleId")String ruleId);
}