package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.SysScoresRuleRelKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysScoresRuleRelMapper {
    int deleteByPrimaryKey(SysScoresRuleRelKey key);

    int insert(SysScoresRuleRelKey record);

    int insertSelective(SysScoresRuleRelKey record);

    List<SysScoresRuleRelKey> selectAllByRuleId(String id);

    int batchInsertRels(@Param("dataList")List<SysScoresRuleRelKey> datalist);

    int batchDeleteRels(@Param("dataList")List<SysScoresRuleRelKey> datalist);

    void clearRuleRel(@Param("ruleId")String ruleId);
}