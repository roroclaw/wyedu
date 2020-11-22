package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.TchScheduleCourseOpen;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TchScheduleCourseOpenMapper {
    int deleteByPrimaryKey(String id);

    int insert(TchScheduleCourseOpen record);

    int insertSelective(TchScheduleCourseOpen record);

    TchScheduleCourseOpen selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TchScheduleCourseOpen record);

    int updateByPrimaryKey(TchScheduleCourseOpen record);

    int cleanScheduleCourseOpenRelInfo(TchScheduleCourseOpen record);

    List<TchScheduleCourseOpen> selectScheduleCourseOpenRelList(TchScheduleCourseOpen record);

    List<TchScheduleCourseOpen> selectScheduleCourseOpenRelListForShow(@Param("stuId")String stuId,@Param("schoolYear")String schoolYear,@Param("term")String term,
                                                                       @Param("teacherId")String teacherId,@Param("orderParam")String orderParam);

    List<TchScheduleCourseOpen> selectScheduleCourseOpenRelListForClassShow(@Param("schoolYear")String schoolYear,@Param("term")String term,
                                                                            @Param("classId")String classId,@Param("orderParam")String orderParam);

    List<TchScheduleCourseOpen> selectScheduleCourseOpenRelListForCollecting(@Param("schoolYear")String schoolYear,@Param("term")String term,@Param("period")String period);


    List<TchScheduleCourseOpen> selectClassInfoForCollectingShow(@Param("graduateYears")String graduateYears,@Param("term")String term,@Param("period")String period);

    List<TchScheduleCourseOpen> checkScheduleCourseOpenRelForTeacher(TchScheduleCourseOpen record);

    List<TchScheduleCourseOpen> checkScheduleCourseOpenRelForStu(TchScheduleCourseOpen record);
}