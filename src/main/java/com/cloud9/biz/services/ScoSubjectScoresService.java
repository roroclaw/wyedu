package com.cloud9.biz.services;

import com.cloud9.biz.dao.mybatis.ScoSubjectScoresMapper;
import com.cloud9.biz.dao.mybatis.SysImportLogMapper;
import com.cloud9.biz.dao.mybatis.TchClassInfoMapper;
import com.cloud9.biz.models.ScoSubjectScores;
import com.cloud9.biz.models.SysImportLog;
import com.cloud9.biz.models.TchClassInfo;
import com.cloud9.biz.models.vo.VFileObj;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.util.BizConstants;
import com.cloud9.biz.util.ImportKit;
import com.roroclaw.base.bean.MemoryCache;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.handler.BizException;
import com.roroclaw.base.service.BaseService;
import com.roroclaw.base.utils.POIExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by roroclaw on 2017/8/16.
 */
@Service("subjectScoresService")
@Transactional
public class ScoSubjectScoresService extends BaseService {

    private static Logger logger = LoggerFactory.getLogger(ScoSubjectScoresService.class);

    @Autowired
    private CommonService commonService;

    @Autowired
    private ScoSubjectScoresMapper scoSubjectScoresMapper;

    @Autowired
    private SysImportLogMapper sysImportLogMapper;

    @Autowired
    private TchClassInfoMapper classInfoMapper;

    public PageBean getSubjectScoresPageData(PageBean pageBean) {
        List resList = scoSubjectScoresMapper.selectSubjectScoresPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    /**
     * 导入科目成绩信息
     *
     * @return
     */
    public String importSubjectScores(String fileName,MultipartFile file,VUserInfo userInfo) throws BizException, IOException {
        //保存文件到文件系统
        VFileObj fileObj = this.commonService.writeFile(file,"subjectScoresFiles");
        InputStream inputStream = file.getInputStream();
        int batchCommitCount = Integer.valueOf(MemoryCache.getSysConfigKey("IMPORT_COMMIT_COUNT"));

        String importInfo = "";
        Sheet sheet = POIExcelUtil.getExcelSheet(fileName, inputStream);
        if (sheet == null) {
            throw new BizException("excel数据为空!");
        }

//      //获取每行成绩数据
        int begin = sheet.getFirstRowNum() + 1;
        int end = sheet.getLastRowNum();
        List<ScoSubjectScores> subjectScoresList = new ArrayList<ScoSubjectScores>();

        int k = 0;
        int totalCommitNum = 0;
        int errorNum = 0;

        //单文件重复验证
        Map<String,Object> selfMap = new HashMap<String, Object>();

        //错误日志记录
        String excelDataErr = "";
        for (int i = begin; i <= end; i++) {
            Row row = sheet.getRow(i);
            if (null != row) {
                if(POIExcelUtil.isEmptyRow(row)){
                    continue;
                }
                k++;
                try {
                      ScoSubjectScores scoSubjectScores = this.getSubjectScoresByRow(row,selfMap);
                      scoSubjectScores.setCreator(userInfo.getId());
                      scoSubjectScores.setCreateTime(new Date());
//                      scoSubjectScores.setFlag(BizConstants.SUBJECT_SCORE_FLAG.IMP);
                      scoSubjectScores.setRemark("通过文件["+fileName+"]导入");
                      scoSubjectScores.setStatus(BizConstants.SCORES_SUBJECT_STATUS.UNPUBLISH);
                      scoSubjectScores.setFlag(Integer.valueOf(BizConstants.SCORES_SUBJECT_STATUS.NORMAL));
                      subjectScoresList.add(scoSubjectScores);
                      totalCommitNum += 1;
                } catch (Exception e) {
                    e.printStackTrace();
                    errorNum += 1;
                    String errorMsg = "第["+i+"]行数据发生错误:"+e.getMessage();
                    excelDataErr += "</br>"+errorMsg;
                    logger.debug(errorMsg);
                }

                if (k >= batchCommitCount) {
                    this.batchAddSubjectScores(subjectScoresList);
                    k = 0 ;
                    subjectScoresList.clear();
                }
            }
        }
        if (k > 0) {
            this.batchAddSubjectScores(subjectScoresList);
        }

        if (errorNum > 0) {
            importInfo += "导入错误数据[" + errorNum + "]条!" + importInfo;
        }
        importInfo = "已导入[" + totalCommitNum + "]成绩数据!" + importInfo;

        if(!"".equals(excelDataErr)){
            excelDataErr = importInfo + ":</br>"+excelDataErr;
        }

        //记录日志表
        SysImportLog sysImportLog = new SysImportLog();
        sysImportLog.setCreateTime(new Date());
        sysImportLog.setCreator(userInfo.getId());
        sysImportLog.setSrcFile(fileName);
        sysImportLog.setFile(fileObj.getFileName());
        sysImportLog.setType(BizConstants.IMP_TYPE.SUBJECT_SCORES_INFO);
        sysImportLog.setComment(excelDataErr);
        this.sysImportLogMapper.insertSelective(sysImportLog);
        return importInfo;
    }

    /**
     * 获取excel成绩行数据
     * @param row
     * @param selfMap
     * @return
     */
    private ScoSubjectScores getSubjectScoresByRow(Row row,Map<String,Object> selfMap) throws ParseException {
        //校验信息
        String errorMsg = "";
        //验证学籍信息重复性(统一用一个方法来判断) 1.学籍姓名是否存在 科目是否存在
        Cell stuNumCell = row.getCell(0);
        String stuNum = POIExcelUtil.getStringCellValue(stuNumCell);
        errorMsg += ImportKit.isValEmpty(stuNum,"学号");
        Cell nameCell = row.getCell(1);
        String stuName = POIExcelUtil.getStringCellValue(nameCell);
        errorMsg += ImportKit.isValEmpty(stuName, "姓名");
        Cell subjectCell = row.getCell(2);
        String subjectName = POIExcelUtil.getStringCellValue(subjectCell);
        errorMsg += ImportKit.isValEmpty(subjectName, "科目");
        Cell termCell = row.getCell(5);
        String termCode =String.valueOf(Double.valueOf(POIExcelUtil.getStringCellValue(termCell)).intValue());
        errorMsg += ImportKit.isValEmpty(termCode, "学期");
        Cell gradeCell = row.getCell(6);
        String gradeId = POIExcelUtil.getStringCellValue(gradeCell);
        errorMsg += ImportKit.isValEmpty(gradeId, "年级");
        Cell schoolYearCell = row.getCell(4);
        String schoolYear = POIExcelUtil.getStringCellValue(schoolYearCell);
        //校验学年格式
        if(!validateSchoolYear(schoolYear)){
            errorMsg += "学年非法";
        }

        Map paramAttr = new HashMap();
        paramAttr.put("stuNum", stuNum);
        paramAttr.put("stuName",stuName);
        paramAttr.put("subjectName",subjectName);
        paramAttr.put("term",termCode);
        schoolYear = schoolYear.substring(0,4);
        paramAttr.put("schoolYear",schoolYear);

        Map resInfo = this.scoSubjectScoresMapper.selectExistInfo(paramAttr);
        String stuId = null;
        String subjectId = null;
//        String gradeId = null;
        String selfKey = null;
        if(resInfo != null){
           stuId =  resInfo.get("stuId") != null ? (String)resInfo.get("stuId") : null;
           subjectId =  resInfo.get("subjectId") != null ? (String)resInfo.get("subjectId") : null;
//           gradeId =  resInfo.get("gradeId") != null ? (String)resInfo.get("gradeId") : null;
           int repeatNum =  resInfo.get("repeatNum") != null ? ((Long)resInfo.get("repeatNum")).intValue() : 0;

            if(subjectId == null){
                errorMsg += "科目信息不存在,";
            }
//            if(gradeId == null){
//                errorMsg += "年级不存在,";
//            }
            if(repeatNum > 0){
                errorMsg += "此学生科目成绩已存在,";
            }else{
                selfKey = stuId+"-"+subjectId+"-"+schoolYear;
                if(selfMap.get(selfKey) != null){
                    throw new BizException("重复数据!");
                }
            }
        }else{
            errorMsg += "学籍信息不存在,";
        }

        ScoSubjectScores scoSubjectScores = new ScoSubjectScores();

        scoSubjectScores.setId(BizConstants.generatorPid());
        scoSubjectScores.setStuId(stuId);
        scoSubjectScores.setSubjectId(subjectId);
        scoSubjectScores.setGradeId(gradeId);
        scoSubjectScores.setSchoolYear(schoolYear);
        scoSubjectScores.setTerm(termCode);

        Cell scoreCell = row.getCell(3);
        String score = POIExcelUtil.getStringCellValue(scoreCell);
        try {
            scoSubjectScores.setScore(new BigDecimal(score));
        } catch (NumberFormatException e) {
            errorMsg += "分数格式非法,";
        }

        Cell classNameCell = row.getCell(7);
        String className= POIExcelUtil.getStringCellValue(classNameCell);
        scoSubjectScores.setClassName(className);

        if(!"".equals(errorMsg)){
            throw new BizException(errorMsg);
        }
        selfMap.put(selfKey,true);
        return scoSubjectScores;
    }

//    private boolean validateSchoolYear(String schoolYear) {
//        boolean bol = true;
//        String[] years = schoolYear.split("-");
//        if(years.length != 2){
//            bol = false;
//        }
//        try {
//            int starYear = Integer.valueOf(years[0]);
//            int endYear = Integer.valueOf(years[1]);
//            if( (endYear - starYear) != 1 ){
//                bol = false;
//            }
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//            bol = false;
//        }
//        return bol;
//    }

    private  boolean validateSchoolYear(String schoolYear) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
             // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
                format.setLenient(false);
                format.parse(schoolYear);
            } catch (ParseException e) {
                return false;
            }
        return true;
    }

    /**
     * 批量新增科目成绩信息
     * @param subjectScoresList
     */
    private void batchAddSubjectScores(List<ScoSubjectScores> subjectScoresList) {
        if(subjectScoresList.size() > 0){
            this.scoSubjectScoresMapper.batchAddSubjectScores(subjectScoresList);
        }
    }

    /**
     * 新增科目成绩信息
     * @param subjectScores
     * @return
     */
    public boolean addSubjectScore(ScoSubjectScores subjectScores,String userId) {
        boolean bol = true;
        //获取学员班级信息
        String stuId = subjectScores.getStuId();
        if(stuId == null || "".equals(stuId)){
            throw new BizException("缺少学员信息!");
        }
        TchClassInfo classInfo = this.classInfoMapper.selectClassInfoByStuId(stuId);
        if(classInfo == null){
            throw new BizException("该学员所在班级未知!");
        }
        //驗證重複性
        String subjectId = subjectScores.getSubjectId();
        String schoolYear = subjectScores.getSchoolYear();

        int count = this.scoSubjectScoresMapper.selectScoresRepeat(stuId,subjectId,schoolYear,null);

        if(count > 0){
            throw new BizException("科目成绩已存在不可再重复添加!");
        }

        subjectScores.setClassId(classInfo.getId());
        subjectScores.setClassName(classInfo.getName());
        subjectScores.setGradeId(classInfo.getGrade());
        subjectScores.setCreateTime(new Date());
        subjectScores.setCreator(userId);
        subjectScores.setId(BizConstants.generatorPid());

        this.scoSubjectScoresMapper.insertSelective(subjectScores);

        return bol;
    }

    /**
     * 刪除科目成績信息
     * @param id
     * @return
     */
    public boolean delSubjectScores(String id) {
        boolean bol = true;
        this.scoSubjectScoresMapper.deleteByPrimaryKey(id);
        return bol;
    }

    /**
     * 通过id获取科目成绩信息
     * @param id
     * @return
     */
    public ScoSubjectScores selectSubjectSocresById(String id) {
        ScoSubjectScores scoSubjectScores = null;
        scoSubjectScores = this.scoSubjectScoresMapper.selectSubjectSocresById(id);
        return scoSubjectScores;
    }

    /**
     * 修改科目成绩信息
     * @param subjectScores
     * @param userId
     * @return
     */
    public boolean modSubjectScore(ScoSubjectScores subjectScores, String userId) {
        boolean bol = false;
        //获取学员班级信息
        String stuId = subjectScores.getStuId();
        if(stuId == null || "".equals(stuId)){
            throw new BizException("缺少学员信息!");
        }
        TchClassInfo classInfo = this.classInfoMapper.selectClassInfoByStuId(stuId);
        if(classInfo == null){
            throw new BizException("该学员所在班级未知!");
        }
        //驗證重複性
        String subjectId = subjectScores.getSubjectId();
        String schoolYear = subjectScores.getSchoolYear();
        int count = this.scoSubjectScoresMapper.selectScoresRepeat(stuId,subjectId,schoolYear,subjectScores.getId());
       // if(count > 0){
        //    throw new BizException("科目成绩已存在不可再重复添加!");
       // }
        subjectScores.setClassId(classInfo.getId());
        subjectScores.setClassName(classInfo.getName());
        subjectScores.setGradeId(classInfo.getGrade());
        subjectScores.setUpdateTime(new Date());
        subjectScores.setUpdater(userId);
        subjectScores.setStatus(BizConstants.SCORES_SUBJECT_STATUS.UNPUBLISH);
        int i = this.scoSubjectScoresMapper.updateByPrimaryKeySelective(subjectScores);
        if(i > 0){
            bol = true;
        }
        return bol;
    }

//    public static void main(String[] args) {
//        System.out.println(Float.valueOf("85.0").longValue());
//    }


    public List<ScoSubjectScores> getStuScoreCertificateInfoByParam(String grades,String stuId,String status) {
        List<ScoSubjectScores> resList = this.scoSubjectScoresMapper.selectStuScoreCertificateInfoByParam(grades,stuId,status);
        return resList;
    }

    public List<ScoSubjectScores> getStuSubjectScoreByParam(ScoSubjectScores subjectScores) {
        List<ScoSubjectScores> resList = this.scoSubjectScoresMapper.selectStuSubjectScoreInfoByParam(subjectScores);
        return resList;
    }

    public List<ScoSubjectScores> getStuSubjectInfoListByParam(ScoSubjectScores subjectScores) {
        List<ScoSubjectScores> resList = this.scoSubjectScoresMapper.selectStuSubjectInfoListByParam(subjectScores);
        return resList;
    }

    public List<ScoSubjectScores> getStuScoreCertificateHeadInfoByParam(String grades,String stuId,String status) {
        List<ScoSubjectScores> resList = this.scoSubjectScoresMapper.selectStuScoreCertificateInfoHeadByParam(grades, stuId,status);
        return resList;
    }

//    public boolean publishScore(String id) {
//        ScoSubjectScores scoSubjectScores = new ScoSubjectScores();
//        scoSubjectScores.setId(id);
//        scoSubjectScores.setStatus(BizConstants.SCORES_SUBJECT_STATUS.NORMAL);
//        this.scoSubjectScoresMapper.updateByPrimaryKeySelective(scoSubjectScores);
//        return true;
//    }

    /**
     * 批量发布
     * @param idArr
     * @return
     */
    public boolean batchPublishScore(String[] idArr) {
        List<ScoSubjectScores> scoreList = new ArrayList<ScoSubjectScores>();
        for (int i = 0; i < idArr.length; i++){
            String id = idArr[i];
            ScoSubjectScores scoSubjectScores = new ScoSubjectScores();
            scoSubjectScores.setId(id);
            scoSubjectScores.setStatus(BizConstants.SCORES_SUBJECT_STATUS.NORMAL);
            scoreList.add(scoSubjectScores);
        }
        this.scoSubjectScoresMapper.batchScoreStatus(scoreList);
        return true;
    }

    /**
     * 整体发布科目成绩
     * @param subjectScores
     * @param userId
     * @return
     */
    public boolean batchPublishAllScoreByParam(ScoSubjectScores subjectScores) {
        String schoolYear = subjectScores.getSchoolYear();
        String subId = subjectScores.getSubjectId();
        if(schoolYear == null || "".equals(schoolYear) || subId == null || "".equals(subId)){
            throw new BizException("缺少学年或者科目信息!");
        }
        subjectScores.setStatus(BizConstants.SCORES_SUBJECT_STATUS.UNPUBLISH);////只修改未发布状态的数据
        this.scoSubjectScoresMapper.batchAllScoreStatusByParam(subId,schoolYear,BizConstants.SCORES_SUBJECT_STATUS.NORMAL,BizConstants.SCORES_SUBJECT_STATUS.UNPUBLISH);
        return true;
    }

//    public PageBean getSubjectScoreClassPageData(PageBean pageBean) {
//        List resList = scoSubjectScoresMapper.selectSubjectScoresPageData(pageBean);
//        pageBean.setData(resList);
//        return pageBean;
//    }
}
