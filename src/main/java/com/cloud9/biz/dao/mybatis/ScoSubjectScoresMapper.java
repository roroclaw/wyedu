package com.cloud9.biz.dao.mybatis;

import com.cloud9.biz.models.ScoSubjectScores;
import com.cloud9.biz.models.TchStuCourseOpenRel;
import com.roroclaw.base.bean.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ScoSubjectScoresMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScoSubjectScores record);

    int insertSelective(ScoSubjectScores record);

    ScoSubjectScores selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScoSubjectScores record);

    int updateByPrimaryKeyWithBLOBs(ScoSubjectScores record);

    int updateByPrimaryKey(ScoSubjectScores record);

    List<ScoSubjectScores> selectSubjectScoresPageData(PageBean pageBean);

    Map selectExistInfo(Map paramAttr);

    void batchAddSubjectScores(@Param("dataList")List<ScoSubjectScores> subjectScoresList);

    int selectScoresRepeat(@Param("stuId")String stuId,@Param("subjectId")String subjectId,@Param("schoolYear")String schoolYear,@Param("filterId")String filterId);

    ScoSubjectScores selectSubjectSocresById(String id);

    void deleteBySubjectAndYear(@Param("subjectId")String subjectId,@Param("schoolYear") String schoolYear);

    void deleteBySubjectAndYearTerm(@Param("subjectId")String subjectId,@Param("schoolYear")  String schoolYear,@Param("term")  String term);

    void deleteBySubjectAndYearTermGrades(@Param("subjectId")String subjectId,@Param("schoolYear")  String schoolYear,@Param("term")  String term,@Param("gradeArr")String[] gradeArr);

//    void deleteBySubjectAndYearTermGrades4ss(@Param("subjectId")String subjectId,@Param("schoolYear")  String schoolYear,@Param("term")  String term,@Param("gradeArr")String[] gradeArr,@Param("subjectStuInfos")List<TchStuCourseOpenRel> subjectStuInfos);

    List selectStuSubjectScoreInfoByParam(ScoSubjectScores record);

    List selectStuSubjectInfoListByParam(ScoSubjectScores record);

    List selectStuScoreCertificateInfoByParam(@Param("grades") String grades,@Param("stuId") String stuId,@Param("status") String status);

    List selectStuScoreCertificateInfoHeadByParam(@Param("grades") String grades,@Param("stuId") String stuId,@Param("status") String status);

    int batchScoreStatus(@Param("scoreList")List<ScoSubjectScores> scoreList);

    int batchAllScoreStatusByParam(@Param("subjectId")String subjectId,@Param("schoolYear")  String schoolYear,@Param("StatusSet")  String StatusSet,@Param("StatusUsed")  String StatusUsed);

    void deleteBySubjectAndYearTermStuId(@Param("subjectId")String subjectId,@Param("schoolYear") String schoolYear,@Param("term")String term,@Param("stuId")String stuId);

//    void deleteByExam(@Param("openCourseId")String openCourseId,@Param("scoreType")String scoreType,@Param("teacherId")String teacherId);
}