package com.cloud9.biz.services;

import com.cloud9.biz.dao.mybatis.*;
import com.cloud9.biz.models.ScoExamScoresForQuery;
import com.cloud9.biz.models.SysDictItem;
import com.cloud9.biz.models.SysGrades;
import com.cloud9.biz.models.SysUserRoleRel;
import com.cloud9.biz.models.vo.MajorItem;
import com.cloud9.biz.models.vo.VFileObj;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.bean.MemoryCache;
import com.roroclaw.base.service.BaseService;
import com.roroclaw.base.utils.FileKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dengxianzhi on 2017/1/28.
 */
@Service("commonService")
@Transactional
public class CommonService extends BaseService {

    @Autowired
    private SysDictItemMapper dictItemMapper;

    @Autowired
    private  SysDictItemMapper sysDictItemMapper;

    @Autowired
    private SysMajorMapper sysMajorMapper;

    @Autowired
    private SysGradesMapper sysGradesMapper;

    @Autowired
    private SysAreaMapper sysAreaMapper;

    @Autowired
    private ScoExamScoresMapper scoExamScoresMapper;

//    @Autowired
//    private SysTchScoresRuleConfMapper sysTchScoresRuleConfMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private TchTeachingPlanMapper tchTeachingPlanMapper;
    private List scoreTypeItems;
    private List recordConfigStatusItems;

    /**
     * 根据类型获取元素列表
     * @param dictType
     * @return
     */
    public List<SysDictItem> getItemsByType(String dictType,String keyText){
        List<SysDictItem> resList = null;
        resList = this.dictItemMapper.getItemsByType(dictType,keyText);
        return resList;
    }

    public List<SysDictItem> getItemsByTypeAndCode(String dictType,String code){
        List<SysDictItem> resList = null;
        resList = this.dictItemMapper.getItemsByTypeAndCode(dictType,code);
        return resList;
    }
    /**
     * 获取年级下拉数据
     * @return
     */
    public List<SysDictItem> getAllGradeItems() {
        List<SysDictItem> resList = null;
        resList = this.sysGradesMapper.getAllGradeItems();
        return resList;
    }

    /**
     * 获取年级下拉数据
     * @return
     */
    public List<SysDictItem> getGradeItemsByPeriod(String period) {
        List<SysDictItem> resList = null;
        resList = this.sysGradesMapper.getGradeItemsByPeriod(period);
        return resList;
    }


    /**
     * 获取所有专业item信息
     * @return
     */
    public List<SysDictItem> getAllMajorItems() {
        List<SysDictItem> resList = null;
        resList = this.sysMajorMapper.getAllMajorItems();
        return resList;
    }

    public List<SysDictItem> getAreaByType(String type) {
        List<SysDictItem> resList = null;
        resList = this.sysAreaMapper.getAreasByType(type);
        return resList;
    }

    /**
     * 根据父节点获取区域信息
     * @param pid
     * @return
     */
    public List<SysDictItem> getAreaItemsByPid(String pid) {
        List<SysDictItem> resList = null;
        resList = this.sysAreaMapper.getAreaItemsByPid(pid);
        return resList;
    }

    /**
     * 写入文件
     *
     * @param multiFile
     * @return
     */
    public VFileObj writeFile(MultipartFile multiFile,String subDir) throws IOException {
        VFileObj vFileObj = new VFileObj();
        String filename = multiFile.getOriginalFilename();
        vFileObj.setOriginalName(filename);
        String exName = FileKit.getExtensionName(filename);
        vFileObj.setType(exName);
        String fileName = FileKit.getRadomFileName() + "." + exName;
        String fileDir = MemoryCache.getSysConfigKey("fileUpload.dir")+"/"+subDir;
        File fileDirFile = new File(fileDir);
        if (!fileDirFile.exists())
            fileDirFile.mkdirs();
        FileKit.saveFileFromInputStream(multiFile.getInputStream(),fileDir, fileName);
        String fulldir = MemoryCache.getSysConfigKey("fileUpload.accDir")
                + "/" + subDir + "/"+fileName;
        String shortdir = subDir+"/"+fileName;
        vFileObj.setFullDir(fulldir);
        vFileObj.setShortDir(shortdir);
        vFileObj.setSize(multiFile.getSize());
        vFileObj.setFileName(fileName);
        return vFileObj;
    }

    /**
     * 获取所有方向数据
     * @return
     */
    public List<MajorItem> getAllSubMajorItems() {
        List<MajorItem> resList = null;
        resList = this.sysMajorMapper.getAllSubMajorItems();
        return resList;
    }

    public SysGrades getGradeInfoByCode(String code) {
        SysGrades gradeInfo = this.sysGradesMapper.selectByPrimaryKey(code);
        return gradeInfo;
    }



    /**
     * 角色检验
     * @param userId
     * @return
     */
    public boolean isTheRoleForCheck(String userId,String roleIdCheckFor){
        boolean bol = false;
        SysUserRoleRel userRoleRelInfo=new SysUserRoleRel();
        if(userId!=null && !userId.equals("") && roleIdCheckFor!=null && !roleIdCheckFor.equals("")){
            userRoleRelInfo.setUserId(userId);
            List<SysUserRoleRel> userRoleRelList=this.roleService.getUserRolesRelList(userRoleRelInfo);
            for(int i=0;i<userRoleRelList.size();i++){
                if(userRoleRelList.get(i).getRoleId().equals(roleIdCheckFor)){
                    bol=true;
                    i=userRoleRelList.size();
                }
            }
        }
        return bol;
    }

    public List<ScoExamScoresForQuery> getCourseItemsForPower(String grade,String schoolYear,String type,String term,String teacherId,String period) {
        String graduateYear="";
        if(grade!=null && !grade.equals("") && period!=null && !period.equals("") && schoolYear!=null && !schoolYear.equals("")){///查某学届的所有班级，要清空grade参数。
            String periodGradeNum= BizConstants.periodGradeNum(period);
            int GraduateYear=Integer.parseInt(schoolYear)+(Integer.parseInt(periodGradeNum)-Integer.parseInt(grade))+1;
            graduateYear=Integer.toString(GraduateYear);
            grade="";
        }
        List<ScoExamScoresForQuery> resList = this.scoExamScoresMapper.getCourseItemsForPower(type, graduateYear, term,teacherId);
        return resList;
    }

    public List<SysDictItem> getTeachingPlanItems(String majorId) {
        List<SysDictItem> resList = this.dictItemMapper.getTeachingPlanItems(majorId);
        return resList;
    }

    public List<SysDictItem> getScopeSelItems() {
        List<SysDictItem> resList = this.dictItemMapper.getScopeSelItems();
        return resList;
    }

    public List getScoreTypeItems() {
        List<SysDictItem> resList = this.dictItemMapper.getItemsByType(BizConstants.DICT_TYPE.SCORES_TYPE,null);
        return resList;
    }

    public List getRecordConfigStatusItems() {
        List<SysDictItem> resList = this.dictItemMapper.getItemsByType(BizConstants.DICT_TYPE.RECORD_CONFIG_STATUS,null);
        return resList;
    }
    public String getPresentGradeName(String periodStr,String graduateYearStr,String schoolYearStr,String oldGradeName) {

        int period=Integer.parseInt(periodStr);
        int graduateYear=Integer.parseInt(graduateYearStr);
        int tempGrade=period*6-(graduateYear-Integer.parseInt(schoolYearStr))+1;
        String tempGradeName=BizConstants.GRADE_NAME[tempGrade];
        String presentGradeName=tempGradeName+oldGradeName.substring(oldGradeName.length()-4);

        return presentGradeName;
    }
    public List changeGroupToList(String Group) {
        List newList=new ArrayList();
        String a [] = Group.split("#");
        for(int i=0;i<a.length;i++){
            newList.add(a[i]);
        }
        return newList;
    }

    public List getTchRules(String teacherId) {
        List<SysDictItem> resList = null;
        resList = this.dictItemMapper.selectTchRules4Items(teacherId);
        return resList;
    }

}
