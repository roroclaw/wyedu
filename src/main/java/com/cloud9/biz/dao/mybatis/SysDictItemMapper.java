package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.SysDictItem;
import com.cloud9.biz.models.SysDictItemKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDictItemMapper {
    int deleteByPrimaryKey(SysDictItemKey key);

    int insert(SysDictItem record);

    int insertSelective(SysDictItem record);

    SysDictItem selectByPrimaryKey(SysDictItemKey key);

    int updateByPrimaryKeySelective(SysDictItem record);

    int updateByPrimaryKey(SysDictItem record);

    List<SysDictItem> getItemsByType(@Param("dictType") String dictType, @Param("keyText") String keyText);

    List<SysDictItem> getItemsByTypeAndCode(@Param("dictType") String dictType, @Param("code") String keyText);

    List<SysDictItem> selectAll();


    ///////////////by zl
    List <SysDictItem> selectClassItems(@Param("grade") String grade);

    List <SysDictItem> selectClassItemsForPower(@Param("period") String period, @Param("grade") String grade, @Param("graduateYear") String graduateYear, @Param("teacherId") String teacherId);

    List <SysDictItem> selectClassItemsByPowerForArtiAdd(@Param("teacherId") String teacherId);

    List <SysDictItem> selectClassItemsForTeachPower(@Param("period") String period, @Param("grade") String grade, @Param("schoolYear") String schoolYear, @Param("term") String term, @Param("teacherId") String teacherId);

    //List <SysDictItem> getCourseItemsForPower(@Param("period")String period,@Param("grade")String grade,@Param("graduateYear")String graduateYear,@Param("teacherId")String teacherId);

    List <SysDictItem> getTeachingPlanItems(@Param("majorId") String majorId);

    List<SysDictItem> getScopeSelItems();

}