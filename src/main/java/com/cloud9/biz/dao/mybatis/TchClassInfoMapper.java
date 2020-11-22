package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.TchClassInfo;
import com.cloud9.biz.models.vo.ValidateDelVo;
import com.mchange.v2.codegen.bean.ClassInfo;
import com.roroclaw.base.bean.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TchClassInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(TchClassInfo record);

    int insertSelective(TchClassInfo record);

    TchClassInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TchClassInfo record);

    int updateByPrimaryKey(TchClassInfo record);

    List<TchClassInfo> selectActClassList();

    TchClassInfo selectClassinfoByGradeSeq(@Param("grade")String grade,@Param("classSeq")String classSeq);

    void refreshClassStuNum();

    List selectClassPageData(PageBean pageBean);

    int selectRepeatClassinfo(@Param("tchClassInfo")TchClassInfo tchClassInfo,@Param("selfId")String selfId);

    List<ValidateDelVo> validateClassDelLimit(String classId);

    int changeClassStatus(@Param("id")String id,@Param("status") String status);

    TchClassInfo selectClassInfoById(String id);

    TchClassInfo selectClassInfoByStuId(String stuId);

    List <TchClassInfo> selectClassItemsBySchoolYear(@Param("schoolYear")String schoolYear,@Param("teacherId")String teacherId);

    List <TchClassInfo> selectClassItemsBySchoolYearForTeacher(@Param("schoolYear")String schoolYear,@Param("term")String term,@Param("teacherId")String teacherId);


}