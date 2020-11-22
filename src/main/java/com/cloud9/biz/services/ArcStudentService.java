package com.cloud9.biz.services;

import com.cloud9.biz.dao.mybatis.*;
import com.cloud9.biz.models.*;
import com.cloud9.biz.models.vo.MajorItem;
import com.cloud9.biz.models.vo.VFileObj;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.util.BizConstants;
import com.cloud9.biz.util.EduKit;
import com.cloud9.biz.util.ImportKit;
import com.roroclaw.base.bean.MemoryCache;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.handler.BizException;
import com.roroclaw.base.utils.POIExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("studentService")
@Transactional
public class ArcStudentService {

    private static Logger logger = LoggerFactory.getLogger(ArcStudentService.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private ArcStudentInfoMapper arcStudentInfoMapper;

    @Autowired
    private SysUserRoleRelMapper sysUserRoleRelMapper;

    @Autowired
    private SysMajorMapper sysMajorMapper;

    @Autowired
    private SysAreaMapper sysAreaMapper;

    @Autowired
    private SysImportLogMapper sysImportLogMapper;

    @Autowired
    private TchClassInfoMapper classInfoMapper;

    @Autowired
    private TchTeachingPlanMapper teachingPlanMapper;

    @Autowired
    private ArcStudentPlanRelMapper arcStudentPlanRelMapper;

    @Autowired
    private ArcStudentExtMapper arcStudentExtMapper;

    @Autowired
    private CommonService commonService;

    @Autowired
    private SysUserService userService;

    public static Map<String,String> IMP_TOOL_MAJOR_MAP;
    public static Map<String,String> IMP_TOOL_AREA_MAP;
    public static Map<String,TchClassInfo> ACTICTY_CLASS_MAP;
//    public static Map<String,String> IMP_TOOL_CITY_MAP;
//    public static Map<String,String> IMP_TOOL_DISTRICT_MAP;

    /**
     * 学生信息分页查询
     *
     * @param pageBean
     * @return
     */
    public PageBean getUserInfoPageData(PageBean pageBean) {
        List resList = arcStudentInfoMapper.selectStudentInfoPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    /**
     * 学生异动信息分页查询
     *
     * @param pageBean
     * @return
     */
    public PageBean doGetStudentChangeInfoPageData(PageBean pageBean) {
        List<ArcStudentInfo> resList = arcStudentInfoMapper.selectStudentChangeInfoPageData(pageBean);
        structureExtInfo(resList);
        //循环组装扩展信息
//        for (int i=0 ; i < resList.size() ; i++){
//            ArcStudentInfo arcStudentInfo = resList.get(i);
//            String extType = arcStudentInfo.getExtType();
//            if(extType != null){
//                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                String extTypeText = arcStudentInfo.getStatusText();
//                String extInfo = "["+extTypeText +"]";
//                if(arcStudentInfo.getExtStarTime() != null){
//                    extInfo += "-开始时间:"+dateFormat.format(arcStudentInfo.getExtStarTime());
//                }
//                if(arcStudentInfo.getExtEndTime() != null){
//                    extInfo += "-结束时间:"+dateFormat.format(arcStudentInfo.getExtEndTime());
//                }
//                arcStudentInfo.setExtInfo(extInfo);
//            }
//        }
        pageBean.setData(resList);
        return pageBean;
    }

    /**by zl
     * 学生信息分页查询带参数
     *
     * @param pageBean
     * @return
     */
    public PageBean getUserInfoPageDataByParam(PageBean pageBean) {
        List resList = this.arcStudentPlanRelMapper.selectStudentInfoPageDataByParam(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }


    /**
     * 新增学籍信息
     * @param arcStudentInfo
     * @return
     * @throws BizException
     */
    public boolean addStudentInfo(ArcStudentInfoWithBLOBs arcStudentInfo,VUserInfo vUserInfo) throws BizException {
        //验证学号是否已存在
        int count = this.arcStudentInfoMapper.selectActCountByStuNum(arcStudentInfo.getStuNumber());
        if(count > 0){
            throw new BizException("学号已存在!");
        }

        //验证档案号是否已存在
        String dicId = arcStudentInfo.getDocId();
        if(dicId != null && !"".equals(dicId)){
            count = this.arcStudentInfoMapper.selectActCountByDocId(arcStudentInfo.getDocId());
            if(count > 0){
                throw new BizException("档案号已存在!");
            }
        }

        //验证身份证是否已存在
        count = this.arcStudentInfoMapper.selectActCountByIdentityCard(arcStudentInfo.getIdentityCard());
        if(count > 0){
            throw new BizException("身份证已存在!");
        }

        //验证教学计划是否存在
//        Date enroleDate = arcStudentInfo.getEnrolDate();
//        String majorId = arcStudentInfo.getMajorId();
//        boolean isPlanExist = this.validatePlanExist(enroleDate,majorId);
//
//        if(!isPlanExist){
//            throw new BizException("缺失所选学年专业的教学计划!");
//        }

        //新增账号信息
        SysUser sysUser = new SysUser();
        String newUserId = BizConstants.generatorPid();
        sysUser.setId(newUserId);
        sysUser.setRealName(arcStudentInfo.getRealName());
        sysUser.setEncryptPass(BizConstants.DEFAULT_PASSWORD);
        sysUser.setRegisterDate(new Date());
        sysUser.setLoginName(arcStudentInfo.getStuNumber());
        sysUser.setStatus(BizConstants.COMMON_STATUS.ACTIVE);
        sysUser.setType(BizConstants.USER_TYPE.SYSTEM_STUDENT);
//        sysUser.setRealName(arcStudentInfo.getRealName());

        //新增角色信息
        SysUserRoleRel sysUserRoleRel = new SysUserRoleRel();
        sysUserRoleRel.setId(BizConstants.generatorPid());
        sysUserRoleRel.setRoleId(BizConstants.SYS_ROLE_ID.STUDENT);
        sysUserRoleRel.setUserId(newUserId);

        //新增学籍信息

        arcStudentInfo.setUserId(newUserId);
        arcStudentInfo.setStatus(BizConstants.INFO_STATUS.UNCHECKED);
        arcStudentInfo.setId(BizConstants.ID_PRECODE.STUDENT_ID_PRECODE + BizConstants.generatorPid());
        arcStudentInfo.setCreateTime(new Date());
        arcStudentInfo.setCreator(vUserInfo.getId());

        //新增教学计划关系信息
        ArcStudentPlanRel arcStudentPlanRel = new ArcStudentPlanRel();
        arcStudentPlanRel.setId(BizConstants.generatorPid());
        arcStudentPlanRel.setCreateTime(new Date());
        arcStudentPlanRel.setCreator(vUserInfo.getId());
        arcStudentPlanRel.setPlanId(arcStudentInfo.getTchPlanId());
        arcStudentPlanRel.setStuId(arcStudentInfo.getId());

        this.sysUserMapper.insertSelective(sysUser);
        this.arcStudentInfoMapper.insertSelective(arcStudentInfo);
        this.sysUserRoleRelMapper.insertSelective(sysUserRoleRel);
        this.arcStudentPlanRelMapper.insertSelective(arcStudentPlanRel);
        return true;
    }

    /**
     * 修改用户信息
     * @param arcStudentInfo
     * @return
     */
    public boolean modifyStudentInfo(ArcStudentInfoWithBLOBs arcStudentInfo,VUserInfo vUserInfo) {
        boolean bol = false;

        //获取旧信息
        ArcStudentInfo oldStudentInfo = this.arcStudentInfoMapper.selectByPrimaryKey(arcStudentInfo.getId());


        if(!oldStudentInfo.getStuNumber().equals(arcStudentInfo.getStuNumber())){
            //验证学号是否已存在
            int count = this.arcStudentInfoMapper.selectActCountByStuNum(arcStudentInfo.getStuNumber());
            if(count > 0){
                throw new BizException("学号已存在!");
            }
        }

        if(!oldStudentInfo.getDocId().equals(arcStudentInfo.getDocId())) {
            //验证档案号是否已存在
            int count = this.arcStudentInfoMapper.selectActCountByDocId(arcStudentInfo.getDocId());
            if(count > 0){
                throw new BizException("档案号已存在!");
            }
        }

        if(!oldStudentInfo.getIdentityCard().equals(arcStudentInfo.getIdentityCard())){
            //验证身份证是否已存在
            int count = this.arcStudentInfoMapper.selectActCountByIdentityCard(arcStudentInfo.getIdentityCard());
            if(count > 0){
                throw new BizException("身份证已存在!");
            }
        }
        arcStudentInfo.setStatus(BizConstants.INFO_STATUS.UNCHECKED);

        int i = this.arcStudentInfoMapper.updateByPrimaryKeySelective(arcStudentInfo);

        //修改教学计划关系
        int j = this.arcStudentPlanRelMapper.updatePlanByStuId(arcStudentInfo.getId(),arcStudentInfo.getTchPlanId());

        if(j == 0){ //缺失教学计划
            ArcStudentPlanRel arcStudentPlanRel = new ArcStudentPlanRel();
            arcStudentPlanRel.setId(BizConstants.generatorPid());
            arcStudentPlanRel.setCreateTime(new Date());
            arcStudentPlanRel.setCreator(vUserInfo.getId());
            arcStudentPlanRel.setPlanId(arcStudentInfo.getTchPlanId());
            arcStudentPlanRel.setStuId(arcStudentInfo.getId());
            j = this.arcStudentPlanRelMapper.insertSelective(arcStudentPlanRel);
        }

        if (i > 0 && j > 0) {
            bol = true;
        }
        return bol;
    }

//    /**
//     * 验证专业是否存在教学计划
//     * @param enrolDate 入学年份
//     * @param subMajorId 方向
//     * @return
//     */
//    public boolean validatePlanExist(Date enrolDate,String subMajorId){
//        boolean bol = true;
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(enrolDate);
//        String year = String.valueOf(cal.get(Calendar.YEAR));
//        int i = this.teachingPlanMapper.selectCountPlanByYearSubMajorId(subMajorId, year);
//        if(i == 0){
//            bol = false;
//        }
//        return bol;
//    }

    /**
     * 查询相关教学计划
     * @param subMajorId
     * @return
     */
    public TchTeachingPlan selectTchTeachingPlan(String subMajorId,String year,String period){
        TchTeachingPlan tchTeachingPlan = null;
        tchTeachingPlan = this.teachingPlanMapper.selectTchTeachingPlan(subMajorId,year,period);
        return tchTeachingPlan;
    }

    /**
     * 修改学生信息状态
     * @param arcStudentInfo
     * @return
     */
    public boolean modifyStudentInfoStatus(ArcStudentInfo arcStudentInfo) {
        boolean bol = false;

        int i = this.arcStudentInfoMapper.updateInfoByIDSelective(arcStudentInfo);
        if (i > 0) {
            bol = true;
        }
        return bol;
    }

    private boolean isEmptyRow(Row row){
        boolean bol = false;
        Cell realNameCell = row.getCell(1);
        if(realNameCell == null || realNameCell.getCellType() == Cell.CELL_TYPE_BLANK){
            bol = true;
        }
        return bol;
    }

    /**
     * 导入学籍信息
     *
     * @return
     */
    public String importStudents(String fileName,MultipartFile file,VUserInfo vUserInfo) throws BizException, IOException {
        //保存文件到文件系统
        VFileObj fileObj = this.commonService.writeFile(file,"stuImpFiles");
        InputStream inputStream = file.getInputStream();
        int batchCommitCount = Integer.valueOf(MemoryCache.getSysConfigKey("IMPORT_COMMIT_COUNT"));
        //初始化专业数据,便于导入
        initImpMajorMap();
        initImpAreaMap();

        String importInfo = "";
        Sheet sheet = POIExcelUtil.getExcelSheet(fileName, inputStream);
        if (sheet == null) {
            throw new BizException("excel数据为空!");
        }

        //获取每行学生数据
        int begin = sheet.getFirstRowNum() + 1;
        int end = sheet.getLastRowNum();
        List<SysUser> userList = new ArrayList<SysUser>();
        List<SysUserRoleRel> sysUserRoleList = new ArrayList<SysUserRoleRel>();
        List<ArcStudentInfo> arcStuInfoList = new ArrayList<ArcStudentInfo>();
        List<ArcStudentPlanRel> tchPlanRelList = new ArrayList<ArcStudentPlanRel>();

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
                if(this.isEmptyRow(row)){
                    continue;
                }
                k++;
                try {
                    SysUser sysUser = this.getSysUserByRow(row);
                    SysUserRoleRel sysUserRoleRel = this.getSysUserRoleByRow(row, sysUser.getId());
                    ArcStudentInfo arcStuInfo = this.getArcStuInfoByRow(row, sysUser.getId(), selfMap);
                    arcStuInfo.setCreator(vUserInfo.getId());
                    arcStuInfo.setCreateTime(new Date());

                    //添加批量提交集合
                    userList.add(sysUser);
                    sysUserRoleList.add(sysUserRoleRel);
                    arcStuInfoList.add(arcStuInfo);
                    TchTeachingPlan tchTeachingPlan = arcStuInfo.getTchTeachingPlan();
                    ArcStudentPlanRel arcStudentPlanRel = new ArcStudentPlanRel();
                    arcStudentPlanRel.setId(BizConstants.generatorPid());
                    arcStudentPlanRel.setCreateTime(new Date());
                    arcStudentPlanRel.setCreator(vUserInfo.getId());
                    arcStudentPlanRel.setPlanId(tchTeachingPlan.getId());
                    arcStudentPlanRel.setStuId(arcStuInfo.getId());
                    tchPlanRelList.add(arcStudentPlanRel);

                    totalCommitNum += 1;
                } catch (Exception e) {
                    e.printStackTrace();
                    errorNum += 1;
                    String errorMsg = "第["+i+"]行数据发生错误:"+e.getMessage();
                    excelDataErr += "</br>"+errorMsg;
                    logger.debug(errorMsg);
                }

                if (k >= batchCommitCount) {
                                this.batchAddStudents(userList,sysUserRoleList,arcStuInfoList,tchPlanRelList);
                                k = 0 ;
                                userList.clear();
                                sysUserRoleList.clear();
                                arcStuInfoList.clear();
                                tchPlanRelList.clear();
                }
        }
    }
        if (k > 0) {
            this.batchAddStudents(userList,sysUserRoleList,arcStuInfoList,tchPlanRelList);
        }

        if (errorNum > 0) {
            importInfo += "导入错误数据[" + errorNum + "]条!" + importInfo;
        }
        importInfo = "已导入[" + totalCommitNum + "]学员数据!" + importInfo;

        if(!"".equals(excelDataErr)){
            excelDataErr = importInfo + ":</br>"+excelDataErr;
        }

        //记录日志表
        SysImportLog sysImportLog = new SysImportLog();
        sysImportLog.setCreateTime(new Date());
        sysImportLog.setCreator(vUserInfo.getId());
        sysImportLog.setSrcFile(fileName);
        sysImportLog.setFile(fileObj.getFileName());
        sysImportLog.setType(BizConstants.IMP_TYPE.STUDENT_INFO);
        sysImportLog.setComment(excelDataErr);
        this.sysImportLogMapper.insertSelective(sysImportLog);
        //清空班级信息map
        if(ACTICTY_CLASS_MAP != null){
            ACTICTY_CLASS_MAP.clear();
        }
        //更新班级人数
        this.classInfoMapper.refreshClassStuNum();
        return importInfo;
    }


    /**
     * 批量插入学籍信息
     * @param userList
     * @param sysUserRoleList
     * @param arcStuInfoList
     */
    private void batchAddStudents(List<SysUser> userList, List<SysUserRoleRel> sysUserRoleList, List<ArcStudentInfo> arcStuInfoList,List<ArcStudentPlanRel> tchPlanRelList) {
           if(userList.size() > 0){
               this.sysUserMapper.batchAddUser(userList);
               this.sysUserRoleRelMapper.batchInsert(sysUserRoleList);
               this.arcStudentInfoMapper.batchAddStuInfo(arcStuInfoList);
               this.arcStudentPlanRelMapper.batchInsertRels(tchPlanRelList);
           }
    }

    /**
     * 组装学籍信息
     * @param row
     * @return
     */
    private ArcStudentInfo getArcStuInfoByRow(Row row,String userId,Map<String,Object> selfMap)  {
        //校验信息
        String errorMsg = "";
        //验证学籍信息重复性(统一用一个方法来判断) 1.档案号 2.学号 3.身份证号
        Cell docIdCell = row.getCell(0);
        String docId = POIExcelUtil.getStringCellValue(docIdCell);
        errorMsg += ImportKit.isValEmpty(docId, "档案号");
        Cell stuNumCell = row.getCell(10);
        String stuNum = POIExcelUtil.getStringCellValue(stuNumCell);
        errorMsg += ImportKit.isValEmpty(stuNum, "学号");
        Cell idCardCell = row.getCell(5);
        String idCard = POIExcelUtil.getStringCellValue(idCardCell);
        errorMsg +=  ImportKit.isValEmpty(idCard, "身份证号");
        Cell realNameCell = row.getCell(1);
        String realName = POIExcelUtil.getStringCellValue(realNameCell);
        errorMsg +=  ImportKit.isValEmpty(realName, "姓名");

        //单文件重复验证
        String selfKey = docId + "-" + stuNum + "-" + idCard;
        if(selfMap.get(selfKey) != null){
            throw new BizException("重复数据!");
        }

        ArcStudentInfo paramAttr = new ArcStudentInfo();
        paramAttr.setDocId(docId);
        paramAttr.setStuNumber(stuNum);
        paramAttr.setIdentityCard(idCard);
        Map resInfo = this.arcStudentInfoMapper.selectExistInfo(paramAttr);
        if(resInfo != null){
            BigDecimal docIdCount = (BigDecimal) resInfo.get("docIdCount");
            BigDecimal stuNumCount = (BigDecimal) resInfo.get("stuNumCount");
            BigDecimal idCardCount = (BigDecimal) resInfo.get("idCardCount");

            if(docIdCount.intValue() > 0){
                errorMsg += "档案号已存在,";
            }
            if(stuNumCount.intValue() > 0){
                errorMsg += "学号已存在,";
            }
            if(idCardCount.intValue() > 0){
                errorMsg += "身份证号已存在,";
            }

        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        ArcStudentInfoWithBLOBs arcStudentInfo = new ArcStudentInfoWithBLOBs();
        arcStudentInfo.setId(BizConstants.ID_PRECODE.STUDENT_ID_PRECODE + BizConstants.generatorPid());
        arcStudentInfo.setUserId(userId);
        arcStudentInfo.setStatus(BizConstants.INFO_STATUS.UNCHECKED);
//        Cell docIdCell = row.getCell(0);
//        String docId = POIExcelUtil.getStringCellValue(docIdCell);
        arcStudentInfo.setDocId(docId);


        arcStudentInfo.setRealName(realName);

        Cell sexCell = row.getCell(2);
        String sexText = POIExcelUtil.getStringCellValue(sexCell);
        arcStudentInfo.setSex(BizConstants.getItemCodeByText(BizConstants.DICT_TYPE.USER_SEX, sexText));

        Cell usedNameCell = row.getCell(3);
        String usedName = POIExcelUtil.getStringCellValue(usedNameCell);
        arcStudentInfo.setUsedName(usedName);

        Cell birthDayCell = row.getCell(4);
        String birthDay = POIExcelUtil.getStringCellValue(birthDayCell);
        try {
            if(HSSFDateUtil.isCellDateFormatted(birthDayCell)){
                arcStudentInfo.setBirth(HSSFDateUtil.getJavaDate(birthDayCell.getNumericCellValue()));
            }else{
                arcStudentInfo.setBirth(sdf.parse(birthDay));
            }
        } catch (Exception e) {
            errorMsg += "出生日期格式非法,";
//           throw  new BizException("出生日期格式非法!");
        }

//        Cell idCardCell = row.getCell(5);
//        String idCard = POIExcelUtil.getStringCellValue(idCardCell);
        arcStudentInfo.setIdentityCard(idCard);

        Cell nationCell = row.getCell(6);
        String nationText = POIExcelUtil.getStringCellValue(nationCell);
        arcStudentInfo.setNation(BizConstants.getItemCodeByText(BizConstants.DICT_TYPE.MINZU, nationText));

        Cell politicalStatuCell = row.getCell(7);
        String politicalStatusText = POIExcelUtil.getStringCellValue(politicalStatuCell);
        arcStudentInfo.setPoliticalStatus(BizConstants.getItemCodeByText(BizConstants.DICT_TYPE.POLITICAL_STATUS, politicalStatusText));

        Cell postCodeCell = row.getCell(8);
        String postCode = POIExcelUtil.getStringCellValue(postCodeCell);
        arcStudentInfo.setPostCode(postCode);

        Cell stuTypeCell = row.getCell(9);
        String stuTypeText = POIExcelUtil.getStringCellValue(stuTypeCell);
        arcStudentInfo.setType(BizConstants.getItemCodeByText(BizConstants.DICT_TYPE.STU_TYPE, stuTypeText));

//        Cell stuNumCell = row.getCell(10);
//        String stuNum = POIExcelUtil.getStringCellValue(stuNumCell);
        arcStudentInfo.setStuNumber(stuNum);

        Cell gradeCell = row.getCell(11);
        String grade = POIExcelUtil.getStringCellValue(gradeCell);
        arcStudentInfo.setGrade(grade);

        //班次
        Cell classSeqCell = row.getCell(12);
        String classSeq = POIExcelUtil.getStringCellValue(classSeqCell);
        //根据年级以及班次生成班级信息
        try {
            //获取学届
            Cell graduateYearCell = row.getCell(46);
            String graduateYear = POIExcelUtil.getStringCellValue(graduateYearCell);
            try
            {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
                dateFormat.parse(graduateYear);
            } catch (Exception e)
            {
                errorMsg += "学届格式非法,";
            }
            TchClassInfo tchClassInfo = this.getClassInfo(grade, classSeq,userId,graduateYear);
            if(tchClassInfo != null){
                arcStudentInfo.setClassId(tchClassInfo.getId());
            }
        } catch (Exception e) {
            errorMsg += "年级或班次非法,";
        }

        Cell enrolDateCell = row.getCell(13);
        String enrolDate = POIExcelUtil.getStringCellValue(enrolDateCell);
//        try {
//            arcStudentInfo.setEnrolDate(sdf.parse(enrolDate));
//        } catch (ParseException e) {
//            errorMsg += "入学日期格式非法,";
//        }
        try {
            if(HSSFDateUtil.isCellDateFormatted(enrolDateCell)){
                arcStudentInfo.setEnrolDate(HSSFDateUtil.getJavaDate(enrolDateCell.getNumericCellValue()));
            }else{
                arcStudentInfo.setEnrolDate(sdf.parse(enrolDate));
            }
        } catch (Exception e) {
            errorMsg += "入学日期格式非法,";
        }

        Cell phoneCell = row.getCell(14);
        String phone = POIExcelUtil.getStringCellValue(phoneCell);
        arcStudentInfo.setPhone(phone);

        Cell majorCell = row.getCell(15);
        String majorText = POIExcelUtil.getStringCellValue(majorCell);
        String majorId = IMP_TOOL_MAJOR_MAP.get(majorText);
        arcStudentInfo.setMajorId(majorId);
        arcStudentInfo.setMajorText(majorText);

        Cell graduateFromCell = row.getCell(16);
        String graduateFrom = POIExcelUtil.getStringCellValue(graduateFromCell);
        arcStudentInfo.setGraduateFrom(graduateFrom);

        Cell registryProvinceCell = row.getCell(17);
        String registryProvinceText = POIExcelUtil.getStringCellValue(registryProvinceCell);
        String provinceCode = IMP_TOOL_AREA_MAP.get(registryProvinceText);
        arcStudentInfo.setRegistryProvinceCode(provinceCode);
        arcStudentInfo.setRegistryProvinceText(registryProvinceText);

        Cell registryCityCell = row.getCell(18);
        String registryCityText = POIExcelUtil.getStringCellValue(registryCityCell);
        String cityCode = IMP_TOOL_AREA_MAP.get(registryCityText+"\\|"+provinceCode);
        arcStudentInfo.setRegistryCityCode(cityCode);
        arcStudentInfo.setRegistryCityText(registryCityText);

        Cell registryDistrictCell = row.getCell(19);
        String registryDistrictText = POIExcelUtil.getStringCellValue(registryDistrictCell);
        String districtCode = IMP_TOOL_AREA_MAP.get(registryDistrictText+"\\|"+cityCode);
        arcStudentInfo.setRegistryDistrictCode(districtCode);
        arcStudentInfo.setRegistryDistrictText(registryDistrictText);

        Cell registryAddrCell = row.getCell(20);
        String registryAddr = POIExcelUtil.getStringCellValue(registryAddrCell);
        arcStudentInfo.setRegistryAddr(registryAddr);

        Cell registryTypeCell = row.getCell(21);
        String registryTypeText = POIExcelUtil.getStringCellValue(registryTypeCell);
        arcStudentInfo.setRegistryType(BizConstants.getItemCodeByText(BizConstants.DICT_TYPE.REGISTRY_TYPE, registryTypeText));

        Cell homeAddrCell = row.getCell(22);
        String homeAddr = POIExcelUtil.getStringCellValue(homeAddrCell);
        arcStudentInfo.setHomeAddr(homeAddr);

        Cell nativePlaceCell = row.getCell(23);
        String nativePlace = POIExcelUtil.getStringCellValue(nativePlaceCell);
        arcStudentInfo.setNativePlace(nativePlace);

        Cell fromHmtCell = row.getCell(24);
        String fromHmtText = POIExcelUtil.getStringCellValue(fromHmtCell);
        arcStudentInfo.setFromHmt(BizConstants.getItemCodeByText(BizConstants.DICT_TYPE.COMMON_BOOLEAN, fromHmtText));

        Cell eduTypeCell = row.getCell(25);
        String eduTypeText = POIExcelUtil.getStringCellValue(eduTypeCell);
        arcStudentInfo.setEduType(BizConstants.getItemCodeByText(BizConstants.DICT_TYPE.EDU_TYPE, eduTypeText));

        Cell basicAllowancesCell = row.getCell(26);
        String basicAllowancesText = POIExcelUtil.getStringCellValue(basicAllowancesCell);
        arcStudentInfo.setBasicAllowances(BizConstants.getItemCodeByText(BizConstants.DICT_TYPE.COMMON_BOOLEAN, basicAllowancesText));

        Cell transRegistryCell = row.getCell(27);
        String transRegistryText = POIExcelUtil.getStringCellValue(transRegistryCell);
        arcStudentInfo.setTransRegistry(BizConstants.getItemCodeByText(BizConstants.DICT_TYPE.COMMON_BOOLEAN, transRegistryText));

        Cell eduGrantCell = row.getCell(28);
        String eduGrantText = POIExcelUtil.getStringCellValue(eduGrantCell);
        arcStudentInfo.setEduGrant(BizConstants.getItemCodeByText(BizConstants.DICT_TYPE.COMMON_BOOLEAN, eduGrantText));

        Cell eduGrantAmountCell = row.getCell(29);
        String eduGrantAmount = POIExcelUtil.getStringCellValue(eduGrantAmountCell);
        arcStudentInfo.setEduGrantAmount(eduGrantAmount);

        Cell bankAccountsCell = row.getCell(30);
        String bankAccounts = POIExcelUtil.getStringCellValue(bankAccountsCell);
        arcStudentInfo.setBankAccounts(bankAccounts);

        Cell learnTypeCell = row.getCell(31);
        String learnTypeText = POIExcelUtil.getStringCellValue(learnTypeCell);
        arcStudentInfo.setLearnType(BizConstants.getItemCodeByText(BizConstants.DICT_TYPE.LEARN_TYPE, learnTypeText));

        Cell recruitTypeCell = row.getCell(32);
        String recruitTypeText = POIExcelUtil.getStringCellValue(recruitTypeCell);
        arcStudentInfo.setRecruitType(BizConstants.getItemCodeByText(BizConstants.DICT_TYPE.RECRUIT_TYPE, recruitTypeText));

        Cell teachPlaceCell = row.getCell(33);
        String teachPlaceText = POIExcelUtil.getStringCellValue(teachPlaceCell);
        arcStudentInfo.setTeachPlace(BizConstants.getItemCodeByText(BizConstants.DICT_TYPE.TEACH_PLACE, teachPlaceText));

        Cell studentFromCell = row.getCell(34);
        String studentFromText = POIExcelUtil.getStringCellValue(studentFromCell);
        arcStudentInfo.setStudentFrom(BizConstants.getItemCodeByText(BizConstants.DICT_TYPE.STUDENT_FROM, studentFromText));

        Cell studentSortCell = row.getCell(35);
        String studentSortText = POIExcelUtil.getStringCellValue(studentSortCell);
        arcStudentInfo.setStudentSort(BizConstants.getItemCodeByText(BizConstants.DICT_TYPE.STU_SORT, studentSortText));

        Cell officialRegCodeCell = row.getCell(36);
        String officialRegCode = POIExcelUtil.getStringCellValue(officialRegCodeCell);
        arcStudentInfo.setOfficialRegCode(officialRegCode);

        Cell parentsNameCell = row.getCell(37);
        String parentsName = POIExcelUtil.getStringCellValue(parentsNameCell);
        arcStudentInfo.setParentsName(parentsName);

        Cell parentsPhoneCell = row.getCell(38);
        String parentsPhone = POIExcelUtil.getStringCellValue(parentsPhoneCell);
        arcStudentInfo.setParentsPhone(parentsPhone);

        Cell faNameCell = row.getCell(39);
        String faName = POIExcelUtil.getStringCellValue(faNameCell);
        arcStudentInfo.setFaName(faName);

        Cell faPhoneCell = row.getCell(40);
        String faPhone = POIExcelUtil.getStringCellValue(faPhoneCell);
        arcStudentInfo.setFaPhone(faPhone);

        Cell faWorkCell = row.getCell(41);
        String faWork = POIExcelUtil.getStringCellValue(faWorkCell);
        arcStudentInfo.setFaWork(faWork);

        Cell maNameCell = row.getCell(42);
        String maName = POIExcelUtil.getStringCellValue(maNameCell);
        arcStudentInfo.setMaName(maName);

        Cell maPhoneCell = row.getCell(43);
        String maPhone = POIExcelUtil.getStringCellValue(maPhoneCell);
        arcStudentInfo.setMaPhone(maPhone);

        Cell maWorkCell = row.getCell(44);
        String maWork = POIExcelUtil.getStringCellValue(maWorkCell);
        arcStudentInfo.setMaWork(maWork);

        //教学计划学年
        Cell planYearCell = row.getCell(45);
        String planYear = POIExcelUtil.getStringCellValue(planYearCell);
//        arcStudentInfo.setMaWork(maWork);

        //验证教学计划是否存在
        String period = EduKit.getPeriodByGrade(arcStudentInfo.getGrade());
        TchTeachingPlan tchTeachingPlan = this.selectTchTeachingPlan(majorId, planYear, period);

        if(tchTeachingPlan == null){
            errorMsg += "缺失所选学年专业学年的教学计划!";
        }else{
            arcStudentInfo.setTchTeachingPlan(tchTeachingPlan);
        }

        if(!"".equals(errorMsg)){
            throw new BizException(errorMsg);
        }

        selfMap.put(selfKey,true);
        return arcStudentInfo;
    }

    private TchClassInfo getClassInfo(String grade,String classSeq,String userId,String graduateYear){
        TchClassInfo tchClassInfo = null;
        tchClassInfo = getClassInfoByGradeSeq(grade,classSeq);
        if(tchClassInfo == null){ //新增一个班级
            tchClassInfo = new TchClassInfo();
            tchClassInfo.setGrade(grade);
            tchClassInfo.setSeq(Integer.valueOf(classSeq));
            tchClassInfo.setId(BizConstants.generatorPid());
            String className = EduKit.joinClassName(grade,classSeq);
            tchClassInfo.setName(className);
            tchClassInfo.setPeriod(EduKit.getPeriodByGrade(grade));
            tchClassInfo.setCreateTime(new Date());
            tchClassInfo.setCreator(userId);
            tchClassInfo.setStatus(BizConstants.CLASS_STATUS.ACTIVE);
            tchClassInfo.setNumber(1);
            //计算入学年份
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.YEAR, (0 - Integer.valueOf(grade)+1));
//            int enrolYear = calendar.get(Calendar.YEAR);
            tchClassInfo.setEnrolYear(calendar.getTime());
            tchClassInfo.setGraduateYear(graduateYear);
            this.classInfoMapper.insertSelective(tchClassInfo);
            ACTICTY_CLASS_MAP.put(grade+"-"+classSeq,tchClassInfo);
        }
//        else{
//            //更新班级人数
//            int oldNum = tchClassInfo.getNumber();
//            int newNum = oldNum+1;
//            tchClassInfo.setNumber(newNum);
//        }
        return tchClassInfo;
    }

    /**
     * 组装用户信息
     * @param row
     * @return
     */
    private SysUser getSysUserByRow(Row row) {
        SysUser sysUser = new SysUser();
        String newUserId = BizConstants.generatorPid();
        sysUser.setId(newUserId);
        Cell realNameCell = row.getCell(1);
        String realName = POIExcelUtil.getStringCellValue(realNameCell);
        sysUser.setRealName(realName);
        sysUser.setEncryptPass(BizConstants.DEFAULT_PASSWORD);
        sysUser.setRegisterDate(new Date());
        Cell stuNumCell = row.getCell(10);
        String stuNum = POIExcelUtil.getStringCellValue(stuNumCell);
        sysUser.setLoginName(stuNum);
        sysUser.setStatus(BizConstants.COMMON_STATUS.ACTIVE);
        sysUser.setType(BizConstants.USER_TYPE.SYSTEM_STUDENT);
        return sysUser;
    }

    /**
     * 组装用户角色信息
     * @param row
     * @return
     */
    private SysUserRoleRel getSysUserRoleByRow(Row row,String userId) {
        SysUserRoleRel sysUserRoleRel = new SysUserRoleRel();
        sysUserRoleRel.setId(BizConstants.generatorPid());
        sysUserRoleRel.setRoleId(BizConstants.SYS_ROLE_ID.STUDENT);
        sysUserRoleRel.setUserId(userId);
        return sysUserRoleRel;
    }

    public ArcStudentInfoWithBLOBs getStudentInfoById(String id) {
        ArcStudentInfoWithBLOBs arcStudentInfo = null;
        arcStudentInfo = this.arcStudentInfoMapper.selectByPrimaryKey(id);
        return arcStudentInfo;
    }

    public ArcStudentInfo getStudentInfoByUserId(String userId) {
        ArcStudentInfo arcStudentInfo = null;
        arcStudentInfo = this.arcStudentInfoMapper.selectStuInfoByUserId(userId);
        return arcStudentInfo;
    }

    public ArcStudentInfoWithBLOBs getAllStudentInfoByUserId(String userId) {
        ArcStudentInfoWithBLOBs arcStudentInfo = null;
        arcStudentInfo = this.arcStudentInfoMapper.selectAllStuInfoByUserId(userId);
        return arcStudentInfo;
    }

    public ArcStudentInfoWithBLOBs getAllStudentInfoByStuId(String stuId) {
        ArcStudentInfoWithBLOBs arcStudentInfo = null;
        arcStudentInfo = this.arcStudentInfoMapper.selectAllStuInfoByStuId(stuId);
        return arcStudentInfo;
    }

    /**
     * 初始化专业内存数据
     */
    public void initImpMajorMap(){
        if(IMP_TOOL_MAJOR_MAP == null){
            List<MajorItem> majorList = this.sysMajorMapper.getAllSubMajorItems();
            IMP_TOOL_MAJOR_MAP = new HashMap<String, String>();
            for (int i = 0; i < majorList.size(); i++){
                MajorItem majorItem = majorList.get(i);
                IMP_TOOL_MAJOR_MAP.put(majorItem.getOrgText(),majorItem.getCode());
            }
        }
    }

    public TchClassInfo getClassInfoByGradeSeq(String grade,String classSeq){
        TchClassInfo tchClassInfo = null;
        if(ACTICTY_CLASS_MAP == null || ACTICTY_CLASS_MAP.size() == 0 ){
            initImpActClassMap();
        }
        //获取班级
        String key = grade +"-"+ classSeq;
        tchClassInfo = ACTICTY_CLASS_MAP.get(key);
        return tchClassInfo;
    }

    /**
     * 初始化活跃班级信息
     */
    private void initImpActClassMap(){
//        if(this.ACTICTY_CLASS_MAP == null || this.ACTICTY_CLASS_MAP == null){
            List<TchClassInfo> classList = this.classInfoMapper.selectActClassList();
            ACTICTY_CLASS_MAP = new HashMap<String, TchClassInfo>();
            for (int i = 0; i < classList.size(); i++){
                TchClassInfo tchClassInfo = classList.get(i);
                String key = tchClassInfo.getGrade() +"-"+ tchClassInfo.getSeq();
                ACTICTY_CLASS_MAP.put(key,tchClassInfo);
            }
//        }
    }

    /**
     * 初始化区域内存数据
     */
    public void initImpAreaMap(){
        if(IMP_TOOL_AREA_MAP == null){
            List<SysArea> areaList = this.sysAreaMapper.getAllAreaItems();
            IMP_TOOL_AREA_MAP = new HashMap<String, String>();
            for (int i = 0; i < areaList.size(); i++){
                SysArea sysArea = areaList.get(i);
                String type = sysArea.getType();
                if(BizConstants.AREA_TYPE.PROVINCE.equals(type)){
                    IMP_TOOL_AREA_MAP.put(sysArea.getName(),sysArea.getCode());
                }else{
                    String key = sysArea.getName() + "\\|"+sysArea.getpCode();
                    IMP_TOOL_AREA_MAP.put(key,sysArea.getCode());
                }

            }
        }
    }

    /**
     * 删除学籍信息
     * @param id
     * @return
     */
    public boolean doDelStuById(String id) {
        boolean bol = true;
        //判断学籍状态
        ArcStudentInfo arcStudentInfo = this.getStudentInfoById(id);
        if(BizConstants.INFO_STATUS.UNCHECKED.equals(arcStudentInfo.getStatus())){
            //删除学籍信息
            this.arcStudentInfoMapper.deleteByPrimaryKey(id);
            //删除用户信息
            this.userService.delUserById(arcStudentInfo.getUserId());
        }else{
            throw new BizException("信息已审核,不可以删除!");
        }
        return bol;
    }

//    /**
//     * 写入文件
//     *
//     * @param multiFile
//     * @return
//     */
//    private VFileObj writeFile(MultipartFile multiFile) throws IOException {
//        VFileObj vFileObj = new VFileObj();
//        String filename = multiFile.getOriginalFilename();
//        vFileObj.setOriginalName(filename);
//        String exName = FileKit.getExtensionName(filename);
//        vFileObj.setType(exName);
//        String fileName = FileKit.getRadomFileName() + "." + exName;
//        String fileDir = MemoryCache.getSysConfigKey("fileUpload.dir");
//        File fileDirFile = new File(fileDir);
//        if (!fileDirFile.exists())
//            fileDirFile.mkdirs();
//        FileKit.saveFileFromInputStream(multiFile.getInputStream(),
//                MemoryCache.getSysConfigKey("fileUpload.dir"), fileName);
//        String fulldir = MemoryCache.getSysConfigKey("fileUpload.accDir")
//                + "/" + fileName;
//        String shortdir = fileName;
//        vFileObj.setFullDir(fulldir);
//        vFileObj.setShortDir(shortdir);
//        vFileObj.setSize(multiFile.getSize());
//        vFileObj.setFileName(fileName);
//        return vFileObj;
//    }


//    public PageBean getImpLogInfoPageData(PageBean pageBean) {
//        List resList = sysImportLogMapper.selectStudentImpLogPageData(pageBean);
//        pageBean.setData(resList);
//        return pageBean;
//    }

//    /**
//     * 判断值是够为null 或者 ""
//     * @param val
//     * @param valDesc
//     * @return
//     */
//    private String isValEmpty(String val,String valDesc){
//        String errMsg = "";
//        if(val == null || "".equals(val)){
//            errMsg = valDesc+"不可以为空!";
//        }
//        return errMsg;
//    }


    /**
     *
     * @param newStudentPlanRel
     * @return
     */
    public boolean modifyStuTeachingPlanInfo(ArcStudentPlanRel newStudentPlanRel) {
        boolean bol = false;

        int i = this.arcStudentPlanRelMapper.updateByPrimaryKey(newStudentPlanRel);
        if (i > 0) {
            bol = true;
        }
        return bol;
    }

    public boolean modStudentDropInfo(String stuId, String starTime,String userId) throws ParseException {
        boolean bol = true;
        ArcStudentExt arcStudentExt = arcStudentExtMapper.selectByStuId(stuId);
        if (arcStudentExt != null){
            arcStudentExt.setCreateTime(new Date());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(starTime);
            arcStudentExt.setStartTime(date);
            arcStudentExt.setEndTime(null);
            arcStudentExt.setCtrator(userId);
            arcStudentExt.setType(BizConstants.INFO_STATUS.DROP);
            arcStudentExt.setStuId(stuId);
            this.arcStudentExtMapper.updateByPrimaryKey(arcStudentExt);
        }else{
            arcStudentExt = new ArcStudentExt();
            arcStudentExt.setId(BizConstants.generatorPid());
            arcStudentExt.setStuId(stuId);
            arcStudentExt.setCreateTime(new Date());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(starTime);
            arcStudentExt.setStartTime(date);
            arcStudentExt.setCtrator(userId);
            arcStudentExt.setType(BizConstants.INFO_STATUS.DROP);
            this.arcStudentExtMapper.insertSelective(arcStudentExt);
        }
        return bol;
    }

    /**
     * 休学
     * @param stuId
     * @param starTime
     * @param endTime
     * @return
     */
    public boolean doModStudentSuspentInfo(String stuId, String starTime, String endTime, String userId) throws ParseException {
        boolean bol = true;
        ArcStudentExt arcStudentExt = arcStudentExtMapper.selectByStuId(stuId);
        if (arcStudentExt != null){
            arcStudentExt.setCreateTime(new Date());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            arcStudentExt.setStartTime(simpleDateFormat.parse(starTime));
            arcStudentExt.setEndTime(simpleDateFormat.parse(endTime));
            arcStudentExt.setCtrator(userId);
            arcStudentExt.setType(BizConstants.INFO_STATUS.SUSPERNSION);
            arcStudentExt.setStuId(stuId);
            this.arcStudentExtMapper.updateByPrimaryKey(arcStudentExt);
        }else{
            arcStudentExt = new ArcStudentExt();
            arcStudentExt.setId(BizConstants.generatorPid());
            arcStudentExt.setStuId(stuId);
            arcStudentExt.setCreateTime(new Date());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            arcStudentExt.setStartTime(simpleDateFormat.parse(starTime));
            arcStudentExt.setEndTime(simpleDateFormat.parse(endTime));
            arcStudentExt.setCtrator(userId);
            arcStudentExt.setType(BizConstants.INFO_STATUS.SUSPERNSION);
            this.arcStudentExtMapper.insertSelective(arcStudentExt);
        }
        return bol;
    }

    public List<ArcStudentInfo> doGetStudentChangeInfo(Map paramMap) {
        List<ArcStudentInfo> resList = arcStudentInfoMapper.selectStudentChangeInfo(paramMap);
        structureExtInfo(resList);
        return resList;
    }

    /**
     * 构造学籍扩展信息
     * @param resList
     */
    private void structureExtInfo(List<ArcStudentInfo> resList){
        //循环组装扩展信息
        for (int i=0 ; i < resList.size() ; i++){
            ArcStudentInfo arcStudentInfo = resList.get(i);
            String extType = arcStudentInfo.getExtType();
            if(extType != null){
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String extTypeText = arcStudentInfo.getStatusText();
                String extInfo = "["+extTypeText +"]";
                if(arcStudentInfo.getExtStarTime() != null){
                    extInfo += "-开始时间:"+dateFormat.format(arcStudentInfo.getExtStarTime());
                }
                if(arcStudentInfo.getExtEndTime() != null){
                    extInfo += "-结束时间:"+dateFormat.format(arcStudentInfo.getExtEndTime());
                }
                arcStudentInfo.setExtInfo(extInfo);
            }
        }
    }

    public ArcStudentInfo selectStuinfoByUserId(String id) {
        ArcStudentInfo arcStudentInfo = this.arcStudentInfoMapper.queryStuinfoByUserId(id);
        return arcStudentInfo;
    }

    /**by zl
     * 学生考勤信息分页查询
     *
     * @param pageBean
     * @return
     */
    public PageBean getStuAttendanceCheckInfoPageData(PageBean pageBean) {
        List resList = arcStudentInfoMapper.selectStuAttendanceCheckInfoPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    public List<ArcStudentInfo> selectStudentInfoByParam(ArcStudentInfo studentInfo) {
        List<ArcStudentInfo> resList = arcStudentInfoMapper.selectStudentInfosByParams(studentInfo);
        return resList;
    }

    // public List<TchStuAttendanceCheckInfo> selectStuAttendanceCheckInfoByParam(TchStuAttendanceCheckInfo tchStuAttendanceCheckInfo) {
    //    List<TchStuAttendanceCheckInfo> resList = arcStudentInfoMapper.selectStuAttendanceCheckInfoByParams(tchStuAttendanceCheckInfo);
    //    return resList;
    // }

    /**
     * 新增学生考勤
     * @param tchStuAttendanceCheckInfo
     * @return
     */
    public boolean addStuattendanceCheckInfo(TchStuAttendanceCheckInfo tchStuAttendanceCheckInfo) {
        boolean bol = false;
        tchStuAttendanceCheckInfo.setId(BizConstants.generatorPid());
        int i = this.arcStudentInfoMapper.insertSelectiveStuAttendanceCheckInfo(tchStuAttendanceCheckInfo);
        if(i > 0){
            bol = true;
        }
        return bol;
    }

}