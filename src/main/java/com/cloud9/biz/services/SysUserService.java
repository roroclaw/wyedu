package com.cloud9.biz.services;

import com.cloud9.biz.dao.mybatis.*;
import com.cloud9.biz.models.*;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.bean.AccTokenBean;
import com.roroclaw.base.bean.MemoryCache;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.bean.UserBean;
import com.roroclaw.base.handler.AuthorErrorException;
import com.roroclaw.base.handler.BizException;
import com.roroclaw.base.service.BaseAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("userService")
@Transactional
public class SysUserService extends BaseAuthService {

    private static Logger logger = LoggerFactory.getLogger(SysUserService.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysTeacherInfoMapper sysTeacherInfoMapper;

    @Autowired
    private SysMenusMapper sysMenusMapper;

    @Autowired
    private SysTokenMapper sysTokenMapper;

    @Autowired
    private SysUserRoleRelMapper sysUserRoleRelMapper;

    @Autowired
    private ArcStudentInfoMapper arcStudentInfoMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public String validateAccToken(AccTokenBean accTokenBean) throws BizException {
         String newToken = null;
        //访问的token信息
        String accToken = accTokenBean.getAccToken();
        newToken = accToken;
//        String accAddress = accTokenBean.getAccAddress();
        //获取系统token信息
        SysToken sysToken = sysTokenMapper.selectByToken(accToken);
//        if(sysToken == null){
//            throw new AuthorErrorException();
//        }
//        PasswordEncoder encoder = new StandardPasswordEncoder();

//        String plaintext = sysTime+"_"+sysAddress;
//        boolean isAccToken = sysToken.getAccToken().equals(accToken); //token是否合法
        if (sysToken != null) {//合法
//            String  sysAddress = sysToken.getAddress();
            Date sysTime = sysToken.getTokenTime();
//            //访问地址非法
//            if(!sysAddress.equals(accAddress)){
//                throw new AuthorErrorException();
//            }
            //过期刷新accToken
            Date curTime = new Date();
            int loseTime = Integer.valueOf(MemoryCache.getSysConfigKey("ACCTOKEN_LOSE_TIME"));
            boolean timelose = (curTime.getTime() - loseTime - sysTime.getTime()) > 0 ;
            if(timelose){
                throw new AuthorErrorException();
            }
            if(curTime.after(sysTime)){//token过期
//                SysToken newSysToken = new SysToken();
//                newSysToken.setAddress(accAddress);
//                newSysToken.setTokenTime(curTime);
//                newToken = BizConstants.generatorAccToken(newSysToken);
                //更新数据库
                this.refreshTokenTime(accToken);
            }
        } else{
            throw new AuthorErrorException();
        }
        return newToken;
    }

    /**
     * 通过token获取用户信息
     * @param accToken
     * @return
     * @throws BizException
     */
    public SysUser getUserInfoByAccToken(String accToken) throws BizException {
        SysUser sysUser = sysUserMapper.selectByAccToken(accToken);
        return sysUser;
    }

    /**
     * 通过账号获取用户信息
     *
     * @param account
     * @return
     */
    public SysUser getUserInfoByAccount(String account) throws BizException {
        SysUser userInfo = this.sysUserMapper.selectByAccount(account);
        return userInfo;
    }

    /**
     * 刷新用户令牌,产生新的token
     *
     */
    public void refreshToken(String oldToken,String newToken) throws BizException {
        this.sysTokenMapper.refreshToken(oldToken,newToken);
    }

    /**
     * 刷新用户令牌时间
     *
     */
    public void refreshTokenTime(String token) throws BizException {
        Date tokenTime = BizConstants.getTokenTime();
        this.sysTokenMapper.refreshTokenTime(token, tokenTime);
    }

    @Override
    public UserBean perfectUserBean(String accToken) {
        VUserInfo vSysuser = null;
        if (accToken != null) {
            vSysuser = this.sysUserMapper.selectVUserByAccToken(accToken);
        } else {
            return null;
        }
        return vSysuser;
    }


    /**
     * 获取一级菜单
     *
     * @return
     */
    public List getFirstMenusByUserId(String userId) throws BizException {
        List resList = null;
        resList = this.sysMenusMapper.selectFirstMenusByUserId(userId);
        return resList;
    }


    /**
     * 获取一级菜单
     *
     * @return
     */
    public List getFirstMenus() throws BizException {
        List resList = null;
        resList = this.sysMenusMapper.selectFirstMenus();
        return resList;
    }

    /**
     * 获取子菜单
     *
     * @return
     */
    public List getSubMenusByUserId(String userId, String menuId) throws BizException {
        List resList = null;
        resList = this.sysMenusMapper.selectSubMenusByUserId(userId, menuId);
        return resList;
    }

    public List getSubMenus(String menuId) throws BizException {
        List resList = null;
        resList = this.sysMenusMapper.selectSubMenus(menuId);
        return resList;
    }

    public List getMenusByUserInfo(SysUser userInfo) {
        List resList = null;
        if (BizConstants.USER_TYPE.SUPER == userInfo.getType()) {//超级管理员
            resList = this.getAllMenus();
        } else {
            resList = this.getAllMenusByUserId(userInfo.getId());
        }
        resList = this.structMenus(resList);
        return resList;
    }

    public SysUser getUserByParams(SysUser UserInfo) {
        SysUser userInfo= this.sysUserMapper.selectUserByParams(UserInfo);
        return userInfo;
    }

    public SysUser getUserByByID(SysUser UserInfo) {
        SysUser userInfo= this.sysUserMapper.selectByPrimaryKey(UserInfo.getId());
        return userInfo;
    }

    public SysUser getUserByByID(String id) {
        SysUser userInfo= this.sysUserMapper.selectByPrimaryKey(id);
        return userInfo;
    }


    /**
     * 结构化菜单数据
     *
     * @param srcMenuList
     * @return
     */
    private List<SysMenus> structMenus(List<SysMenus> srcMenuList) {
        List<SysMenus> menuList = new ArrayList<SysMenus>();
        Map<String, List<SysMenus>> subMap = new HashMap<String, List<SysMenus>>();
        for (int i = 0; i < srcMenuList.size(); i++) {
            SysMenus sysMenus = srcMenuList.get(i);
            String type = sysMenus.getType();
            if (BizConstants.MENU_TYPE.FIRST.equals(type)) {
                String id = sysMenus.getId();
                List<SysMenus> subList = subMap.get(id);
                if (subList == null) {
                    subList = new ArrayList<SysMenus>();
                    subMap.put(id, subList);
                }
                sysMenus.setSubMenus(subList);
                menuList.add(sysMenus);
            } else {
                String pid = sysMenus.getParentCode();
                List<SysMenus> subList = subMap.get(pid);
                if (subList == null) {
                    subList = new ArrayList<SysMenus>();
                }
                subList.add(sysMenus);
                subMap.put(pid, subList);
            }
        }
        return menuList;
    }

    /**
     * 根据用户获取所有菜单
     *
     * @param userId
     * @return
     */
    private List getAllMenusByUserId(String userId) {
        List<SysMenus> list = this.sysMenusMapper.selectAllMenusByUserId(userId);
        return list;
    }

    /**
     * 获取所有菜单
     *
     * @return
     */
    private List getAllMenus() {
        List<SysMenus> list = this.sysMenusMapper.selectAllMenus();
        return list;
    }

    /**
     * 用户信息分页查询
     *
     * @param pageBean
     * @return
     */
    public PageBean getUserPageData(PageBean pageBean) {
        List resList = sysUserMapper.selectUserPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    public boolean delUserById(String id) throws BizException {
        boolean bol = false;
        // 显示超级管理员不让删除
        SysUser sysUser = this.sysUserMapper.selectByPrimaryKey(id);
        int userType = sysUser.getType();
        if (BizConstants.USER_TYPE.SUPER == userType) {
            throw new BizException("此用户不可以删除!");
        }
        //判断用户相关业务信息(教师,学籍信息)是否存在,存在不可以删除
        if(userType == BizConstants.USER_TYPE.SYSTEM_TEACHER){
            //查询教师表记录
            int count = this.sysTeacherInfoMapper.selectActCountByUserId(id);
            if(count > 0){
                throw new BizException("存在教师信息,不可以删除!");
            }
        }else if(userType == BizConstants.USER_TYPE.SYSTEM_STUDENT){
            //查询学生表记录
           int count = this.arcStudentInfoMapper.selectActCountByUserId(id);
           if(count > 0){
               throw new BizException("存在学籍信息,不可以删除!");
           }
        }
        //删除用户信息
        int i = this.sysUserMapper.deleteByPrimaryKey(id);
        //删除用户角色信息
        this.sysUserRoleRelMapper.deleteUserRolesRelByUserId(id);
        if (i > 0) {
            bol = true;
        }
        return bol;
    }

    public boolean modifyUser(SysUser sysUser) {
        boolean bol = false;
        int i = this.sysUserMapper.updateByPrimaryKeySelective(sysUser);
        if (i > 0) {
            bol = true;
        }
        return bol;
    }

    /**
     * 保存/修改token相关信息
     * @param sysToken
     */
    public void addorUpdateToken(SysToken sysToken) {
        String userId = sysToken.getUserId();
        String appId = sysToken.getAppid();
        List<SysToken> tokenList = this.sysTokenMapper.selectByUserApp(userId, appId);
        SysToken token = null;
        if(tokenList.size() > 0){
            token = tokenList.get(0);
        }
        if(token != null){
            sysToken.setId(token.getId());
            sysTokenMapper.updateByPrimaryKeySelective(sysToken);
        }else{
            sysToken.setId(BizConstants.generatorPid());
            sysTokenMapper.insertSelective(sysToken);
        }
    }

    /**
     * 新增用户信息
     * @param newSysUser
     * @param roleIds
     * @return
     */
    public boolean addUser(SysUser newSysUser, String roleIds) {
        boolean bol = true;

        //验证账号是否存在
        SysUser tempUser = this.sysUserMapper.selectByAccount(newSysUser.getLoginName());
        if (tempUser != null)
            throw new BizException("账号已存在");

        //新增用户信息
        String userId = BizConstants.generatorPid();
        newSysUser.setId(userId);
        newSysUser.setStatus(BizConstants.COMMON_STATUS.ACTIVE);
        PasswordEncoder encoder = new StandardPasswordEncoder();
        String pwd = newSysUser.getEncryptPass();
        newSysUser.setEncryptPass(encoder.encode(pwd));
        newSysUser.setRegisterDate(new Date());
        //新增角色信息
        String[] roleArr = roleIds.split("\\|");
        int roleCount = roleArr.length;
        List<SysUserRoleRel> userRoleList = null ;
        if(roleCount > 0){
            userRoleList = new ArrayList();
        }else{
            throw new BizException("缺失用户角色!");
        }
        for (int i =0 ; i < roleCount ;i++){
            String roleId = roleArr[i];
            SysUserRoleRel sysUserRoleRel = new SysUserRoleRel();
            sysUserRoleRel.setId(BizConstants.generatorPid());
            sysUserRoleRel.setUserId(userId);
            sysUserRoleRel.setRoleId(roleId);
            userRoleList.add(sysUserRoleRel);
        }

        this.sysUserMapper.insertSelective(newSysUser);
        this.sysUserRoleRelMapper.batchInsert(userRoleList);

        return bol;
    }

    /**
     * 修改用户状态
     * @param newUserStatus
     * @return
     */
    public boolean modifyStudentInfoStatus(SysUser newUserStatus) {
        boolean bol = false;

        int i = this.sysUserMapper.updateByPrimaryKeySelective(newUserStatus);
        if (i > 0) {
            bol = true;
        }
        return bol;
    }

    public List<SysRole> getUserRoles(String userId) {
        List<SysRole>  resList = null;
        resList = this.sysRoleMapper.selectUserRoles(userId);
        return resList;
    }

    /**
     * 修改用户
     * @param newSysUser
     * @param roleIds
     * @return
     */
    public boolean modUser(SysUser newSysUser, String roleIds) {
        boolean bol = true;
        //这里不允许修改账号,所以不需要验证账号的重复性
        newSysUser.setLoginName(null);
//        //验证账号是否存在
//        SysUser tempUser = this.sysUserMapper.selectByAccount(newSysUser.getLoginName());
//        if (tempUser != null)
//            throw new BizException("账号已存在");

        //新增用户信息
//        String userId = BizConstants.generatorPid();
//        newSysUser.setId(userId);
//        newSysUser.setStatus(BizConstants.COMMON_STATUS.ACTIVE);
//        PasswordEncoder encoder = new StandardPasswordEncoder();
//        String pwd = newSysUser.getEncryptPass();
//        newSysUser.setEncryptPass(encoder.encode(pwd));
//        newSysUser.setRegisterDate(new Date());

        //新增角色信息
        String[] roleArr = roleIds.split("\\|");
        int roleCount = roleArr.length;
        List<SysUserRoleRel> userRoleList = null ;
        if(roleCount > 0){
            userRoleList = new ArrayList();
        }else{
            throw new BizException("缺失用户角色!");
        }
        for (int i =0 ; i < roleCount ;i++){
            String roleId = roleArr[i];
            SysUserRoleRel sysUserRoleRel = new SysUserRoleRel();
            sysUserRoleRel.setId(BizConstants.generatorPid());
            sysUserRoleRel.setUserId(newSysUser.getId());
            sysUserRoleRel.setRoleId(roleId);
            userRoleList.add(sysUserRoleRel);
        }

        this.sysUserMapper.updateByPrimaryKeySelective(newSysUser);
        //删除原有角色信息
        this.sysUserRoleRelMapper.delByUserId(newSysUser.getId());
        this.sysUserRoleRelMapper.batchInsert(userRoleList);
        return bol;
    }

    /**
     * 修改用户密码
     * @param id

     * @return
     */
    public boolean resetPwd(String id) {
        boolean bol = false;
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setEncryptPass(BizConstants.DEFAULT_PASSWORD);
        bol = this.sysUserMapper.updateByPrimaryKeySelective(sysUser) > 0?true:false;
        return bol;
    }

    public boolean modPwd(String id, String newPwd) {
        boolean bol = false;
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        PasswordEncoder encoder = new StandardPasswordEncoder();
        String encryptPass = encoder.encode(newPwd);
        sysUser.setEncryptPass(encryptPass);
        bol = this.sysUserMapper.updateByPrimaryKeySelective(sysUser) > 0?true:false;
        return bol;
    }
}