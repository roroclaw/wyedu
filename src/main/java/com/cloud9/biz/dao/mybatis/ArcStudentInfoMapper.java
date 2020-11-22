package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.ArcStudentInfo;
import com.cloud9.biz.models.ArcStudentInfoWithBLOBs;
import com.cloud9.biz.models.TchStuAttendanceCheckInfo;
import com.roroclaw.base.bean.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArcStudentInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ArcStudentInfoWithBLOBs record);

    int insertSelective(ArcStudentInfoWithBLOBs record);

    ArcStudentInfoWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ArcStudentInfoWithBLOBs record);

    int updateInfoByIDSelective(ArcStudentInfo record);

    int updateByPrimaryKeyWithBLOBs(ArcStudentInfoWithBLOBs record);

    int updateByPrimaryKey(ArcStudentInfo record);

    ArcStudentInfo selectStudentInfoByParams(ArcStudentInfo record);

    List selectStudentInfoPageData(PageBean pageBean);

    int selectActCountByUserId(String userId);

    ArcStudentInfo selectStuInfoByUserId(String userId);

    ArcStudentInfoWithBLOBs selectAllStuInfoByUserId(String userId);

    int selectActCountByStuNum(String stuNumber);

    int selectActCountByDocId(String docId);

    int selectActCountByIdentityCard(String identityCard);

    int increaseStuGrade(ArcStudentInfo record);

    Map selectExistInfo(ArcStudentInfo paramAttr);

    void batchAddStuInfo(@Param("dataList") List<ArcStudentInfo> arcStuInfoList);

    List<ArcStudentInfo> selectStudentInfoByKey(@Param("key")String key,@Param("numLimit")int numLimit);

    List<ArcStudentInfo> selectStusByOpenCourseId(String openCourseId);

    List<ArcStudentInfo> selectStudentChangeInfoPageData(PageBean pageBean);

    List<ArcStudentInfo> selectStudentChangeInfo(Map paramsMap);

    ArcStudentInfo queryStuinfoByUserId(@Param("userId")String userId);

    ArcStudentInfoWithBLOBs selectAllStuInfoByStuId(@Param("stuId")String stuId);

    List selectStuAttendanceCheckInfoPageData(PageBean pageBean);

    List selectStudentInfosByParams(ArcStudentInfo record);

    int selectStuAttendanceCheckInfoCountByParam(TchStuAttendanceCheckInfo record);

    //List selectStuAttendanceCheckInfoByParams(TchStuAttendanceCheckInfo record);

    int insertSelectiveStuAttendanceCheckInfo(TchStuAttendanceCheckInfo record);

    int modStuAttendanceCheckTotal(@Param("Id")String Id,@Param("increment")int increment);
}