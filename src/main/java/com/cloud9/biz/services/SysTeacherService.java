package com.cloud9.biz.services;

import com.cloud9.biz.dao.mybatis.*;
import com.cloud9.biz.models.*;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.handler.BizException;
import com.roroclaw.base.service.BaseAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service("teacherService")
@Transactional
public class SysTeacherService  {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysTeacherInfoMapper sysTeacherInfoMapper;

    @Autowired
    private SysUserRoleRelMapper sysUserRoleRelMapper;


    /**
     * 教师信息分页查询
     *
     * @param pageBean
     * @return
     */
    public PageBean getTeacherInfoPageData(PageBean pageBean) {
        List resList = sysTeacherInfoMapper.selectTeacherInfoPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    public List<SysTeacherInfo> getTeachersListByParam(SysTeacherInfo sysTeacherInfo) {
        List<SysTeacherInfo> resList = this.sysTeacherInfoMapper.selectTeachersListByParam(sysTeacherInfo);
        return resList;
    }

    public boolean addTeacher(SysTeacherInfo sysTeacherInfo,VUserInfo vUserInfo) throws BizException {
       // 验证工号是否已存在
        int count = this.sysTeacherInfoMapper.selectActCountByWorkId(sysTeacherInfo.getCode());
        if (count > 0){
            throw new BizException("工号已存在!");
        }

        //验证档案号是否已存在
       /*
        count = this.sysTeacherInfoMapper.selectActCountByIdentityCard(sysTeacherInfo.getIdentityCard());
        if(count > 0){
           throw new BizException("身份证号已存在!");
        }
      */

        //新增账号信息
        SysUser sysUser = new SysUser();
        String newUserId = BizConstants.generatorPid();
        sysUser.setId(newUserId);
        sysUser.setRealName(sysTeacherInfo.getName());
        sysUser.setEncryptPass(BizConstants.DEFAULT_PASSWORD);
        sysUser.setRegisterDate(new Date());
        sysUser.setLoginName(sysTeacherInfo.getCode());
        sysUser.setStatus(BizConstants.COMMON_STATUS.ACTIVE);
        sysUser.setType(BizConstants.USER_TYPE.SYSTEM_TEACHER);


        //新增角色信息
        SysUserRoleRel sysUserRoleRel = new SysUserRoleRel();
        sysUserRoleRel.setId(BizConstants.generatorPid());
        sysUserRoleRel.setRoleId(BizConstants.SYS_ROLE_ID.TEACHER);
        sysUserRoleRel.setUserId(newUserId);

        //新增教师信息
        sysTeacherInfo.setUserId(newUserId);
        sysTeacherInfo.setStatus(BizConstants.INFO_STATUS.ACTIVE);
        sysTeacherInfo.setId(BizConstants.ID_PRECODE.TEACHER_ID_PRECODE + BizConstants.generatorPid());
        this.sysUserMapper.insertSelective(sysUser);
        this.sysTeacherInfoMapper.insertSelective(sysTeacherInfo);
        this.sysUserRoleRelMapper.insertSelective(sysUserRoleRel);

        return true;
    }

    /**
     * 修改教师信息
     * @param sysTeacherInfo
     * @return
     */
    public boolean modifyTeacherInfo(SysTeacherInfo sysTeacherInfo) {
        boolean bol = false;

        //获取旧信息
        SysTeacherInfo oldTeacherInfo = this.sysTeacherInfoMapper.selectByPrimaryKey(sysTeacherInfo.getId());

        if(!oldTeacherInfo.getCode().equals(sysTeacherInfo.getCode())){
            //验证工号是否已存在
            int count = this.sysTeacherInfoMapper.selectActCountByWorkId(sysTeacherInfo.getCode());
            if(count > 0){
                throw new BizException("工号已存在!");
            }
        }

        if(!oldTeacherInfo.getIdentityCard().equals(sysTeacherInfo.getIdentityCard())) {
            //验证身份证号是否已存在
            int count = this.sysTeacherInfoMapper.selectActCountByIdentityCard(sysTeacherInfo.getIdentityCard());
            if(count > 0){
                throw new BizException("身份证号已存在!");
            }
        }
        int i = this.sysTeacherInfoMapper.updateByPrimaryKeySelective(sysTeacherInfo);
        if (i > 0) {
            bol = true;
        }
        return bol;
    }




    public SysTeacherInfo getTeavherInfoById(String id) {
        SysTeacherInfo sysTeacherInfo = null;
        sysTeacherInfo = this.sysTeacherInfoMapper.selectByPrimaryKey(id);
        return sysTeacherInfo;
    }

    public List<SysTeacherInfo> getTeacherListByKey(String key) {
        List<SysTeacherInfo> teacherInfoList = this.sysTeacherInfoMapper.selectTeacherListByKey(key,10);
        return teacherInfoList;
    }



    public SysTeacherInfo getTeacherInfoByUserId(String id) {
        SysTeacherInfo sysTeacherInfo = this.sysTeacherInfoMapper.selectTeacherInfoByUserId(id);
        return sysTeacherInfo;
    }




    /*

    public boolean addTeacherInfo(SysTeacherInfo sysTeacherInfo) throws BizException {
        sysTeacherInfo.setId(BizConstants.ID_PRECODE.TEACHER_ID_PRECODE + BizConstants.generatorPid());
//        sysTeacherInfo.setStatus(BizConstants.INFO_STATUS.ACTIVE);
        this.sysTeacherInfoMapper.insertTeacherInfo(sysTeacherInfo);
        return true;
    }



    public boolean modifyTeacherInfo(SysTeacherInfo sysTeacherInfo) {
        boolean bol = false;
        int i = this.sysTeacherInfoMapper.updateTeacherInfoByPrimaryKeySelective(sysTeacherInfo);
        if (i > 0) {
            bol = true;
        }
        return bol;
    }


//    /**
//     * 保存token相关信息
//     * @param sysToken
//     */
//    public void refreshToken(SysToken sysToken) {
//        if(sysToken.getId() != null){//修改
//            sysTokenMapper.updateByPrimaryKeySelective(sysToken);
//            //更新token(同一ip或者appId)
//        }else{//新增
//            sysToken.setId(BizConstants.generatorPid());
//            sysTokenMapper.insertSelective(sysToken);
//        }
//    }
//    /**
//     * 保存token相关信息
//     * @param sysToken
//     */
//    public void refreshToken(SysToken sysToken) {
//        if(sysToken.getId() != null){//修改
//            sysTokenMapper.updateByPrimaryKeySelective(sysToken);
//            //更新token(同一ip或者appId)
//        }else{//新增
//            sysToken.setId(BizConstants.generatorPid());
//            sysTokenMapper.insertSelective(sysToken);
//        }
//    }
    /**
     * 保存/修改token相关信息
     * @param sysToken
     */
    /**  public void addorUpdateToken(SysToken sysToken) {
        String userId = sysToken.getUserId();
        String appId = sysToken.getAppid();
        List<SysToken> tokenList = this.sysTokenMapper.selectByUserApp(userId, appId);
        SysToken token = tokenList.get(0);
        if(token != null){
            sysToken.setId(token.getId());
            sysTokenMapper.updateByPrimaryKeySelective(sysToken);
        }else{
            sysToken.setId(BizConstants.generatorPid());
            sysTokenMapper.insertSelective(sysToken);
        }
    }

    public static void main(String[] args) {
        PasswordEncoder encoder = new StandardPasswordEncoder();
        System.out.println(encoder.encode("2017-02-11 22:13:27_0:0:0:0:0:0:0:1"));
    }
    */
}