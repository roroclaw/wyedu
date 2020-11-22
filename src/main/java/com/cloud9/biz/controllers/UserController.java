package com.cloud9.biz.controllers;

import com.cloud9.biz.models.*;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.services.ArcStudentService;
import com.cloud9.biz.services.RoleService;
import com.cloud9.biz.services.SysUserService;
import com.cloud9.biz.util.BizConstants;
import com.cloud9.biz.util.EduKit;
import com.roroclaw.base.annotation.Authorize;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.controller.BaseController;
import com.roroclaw.base.handler.BizException;
import com.roroclaw.base.utils.Constants;
import com.roroclaw.base.utils.HttpKit;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ArcStudentService arcStudentService;



    /**
     * 身份验证
     *
     * @param account
     * @param pwd
     * @param verifyCode
     * @param session
     * @return
     * @throws com.roroclaw.base.handler.BizException
     */
    @Authorize(required = false)
    @RequestMapping(value = "/doValidate.infc")
    @ResponseBody
    public Object doValidate(String account, String pwd, String verifyCode, HttpSession session, HttpServletRequest request) throws BizException {
        String accToken = null;

        //验证码验证
        String rand = session.getAttribute("rand") != null ? (String)session.getAttribute("rand") : "" ;
        if(verifyCode == null){
            throw new BizException("请输入验证码!");
        }
        if(!verifyCode.toLowerCase().equals(rand.toLowerCase())){
            throw new BizException("验证码错误!");
        }
        SysUser ucUserInfo = this.userService.getUserInfoByAccount(account);
        if (ucUserInfo != null) {
            if(BizConstants.USER_TYPE.SYSTEM_STUDENT == ucUserInfo.getType()){//如果是学生
                ArcStudentInfo studentInfo = this.arcStudentService.selectStuinfoByUserId(ucUserInfo.getId());
                String stuStatus = studentInfo.getStatus();
                if(BizConstants.INFO_STATUS.INVALID.equals(stuStatus)){//学籍信息不正常
                    throw new BizException("非正常学籍状态,不可登录!");
                }
            }
        }
//        else{
//
//        }

        if (ucUserInfo != null) {
            String eyPwd = ucUserInfo.getEncryptPass();
            PasswordEncoder encoder = new StandardPasswordEncoder();
            if (encoder.matches(pwd, eyPwd)) {// 验证密码
                SysToken sysToken = new SysToken();
                sysToken.setAddress(HttpKit.getIpAddress(request));
                sysToken.setTokenTime(BizConstants.getTokenTime());
                sysToken.setUserId(ucUserInfo.getId());
                accToken = BizConstants.generatorAccToken(sysToken);
                sysToken.setAccToken(accToken);
                this.userService.addorUpdateToken(sysToken);
                session.setAttribute(Constants.ACC_TOKEN, accToken);
                session.setAttribute(Constants.SESSION_USER_NAME, ucUserInfo.getRealName());
                //当前学年
                session.setAttribute(BizConstants.SESSION_CUR_SCHOOLYEAR, EduKit.getCurStudyYear());

            } else {
                throw new BizException("密码错误!");
            }
        } else {
            throw new BizException("用户不存在!");
        }

        return accToken;
    }

    /**
     * 后台登录
     *
     * @param session
     * @return
     * @throws
     */
    @RequestMapping(value = "/loginOut.do")
    public ModelAndView loginOut(HttpSession session) throws BizException {
        ModelAndView mv = new ModelAndView("redirect:/login.html");
        session.invalidate();
        return mv;
    }

    /**
     * 根据用户查询首页菜单(一级)
     *
     * @return
     * @throws
     */
    @RequestMapping(value = "/doGetFirstMenusByUserId.infc")
    @ResponseBody
    public Object doGetFirstMenusByUserId(VUserInfo userInfo) throws BizException {
        List resList = null;
        if(BizConstants.USER_TYPE.SUPER ==  userInfo.getType()){//超级管理员
            resList = this.userService.getFirstMenus();
        }else{
            resList = this.userService.getFirstMenusByUserId(userInfo.getId());
        }
        return resList;
    }

    /**
     * 根据用户查询首页菜单(一级)
     *
     * @return
     * @throws
     */
    @RequestMapping(value = "/doModPwd.infc")
    @ResponseBody
    public Object doModPwd(String oldPwd,String newPwd,VUserInfo userInfo) throws BizException {
        boolean bol = true;
        PasswordEncoder encoder = new StandardPasswordEncoder();
        boolean isEqual = encoder.matches(oldPwd,userInfo.getEncryptPass());
        if(!isEqual){
            throw new BizException("旧密码错误!");
        }
        bol = this.userService.modPwd(userInfo.getId(),newPwd);
        return bol;
    }

    @RequestMapping(value = "/doResetPwd.infc")
    @ResponseBody
    public Object doResetPwd(String id,VUserInfo userInfo) throws BizException {
        boolean bol = false;
        if(BizConstants.USER_TYPE.SUPER ==  userInfo.getType()){//超级管理员
            bol = this.userService.resetPwd(id);
        } else{
            throw new BizException("无此权限!");
        }
        return bol;
    }

    /**
     * 根据用户查询首页菜单(下级)
     *
     * @return
     * @throws
     */
    @RequestMapping(value = "/doGetSubMenusByUserId.infc")
    @ResponseBody
    public Object doGetSubMenusByUserId(VUserInfo userInfo, String menuId) throws BizException {
        List resList = null;
        if(BizConstants.USER_TYPE.SUPER ==  userInfo.getType()){//超级管理员
            resList = this.userService.getSubMenus(menuId);
        }else{
            resList = this.userService.getSubMenusByUserId(userInfo.getId(),
                    menuId);
        }
        return resList;
    }

    @RequestMapping(value = "/doGetMenusByUserId.infc")
    @ResponseBody
    public Object doGetMenusByUserId(VUserInfo userInfo) throws BizException {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userInfo, sysUser);
        List resList = this.userService.getMenusByUserInfo(sysUser);
        return resList;
    }


    @RequestMapping(value = "/doAddUser.infc")
    @ResponseBody
    public Object doAddUser(SysUser newSysUser,String roleIds) throws BizException {
        boolean bol =true;
        bol = this.userService.addUser(newSysUser,roleIds);
        return bol;
    }

    @RequestMapping(value = "/doModUser.infc")
    @ResponseBody
    public Object doModUser(SysUser newSysUser,String roleIds) throws BizException {
        boolean bol =true;
        bol = this.userService.modUser(newSysUser,roleIds);
        return bol;
    }

    @RequestMapping(value = "/doUpdateUser.infc")
    @ResponseBody
    public Object doUpdateUser(SysUser newSysUser,SysUserRoleRel userRoleRel) throws BizException {
        boolean bol =true;

        return bol;
    }

    /**
     * 修改用户状态
     */
    @RequestMapping(value = "/doModUserStatus.infc")
    @ResponseBody
    public Object doModUserStatus(VUserInfo userInfo,SysUser newSysUser) throws Exception {
        boolean bol = true;
        SysUser newUserStatus=new SysUser();
        String userId = newSysUser.getId();
        if(userId == null || "".equals(userId)){
            throw new BizException("修改目标缺失!");
        }
        newUserStatus.setId(userId);
        newUserStatus.setStatus(newSysUser.getStatus());
        if (!this.userService.modifyStudentInfoStatus(newUserStatus)) {
            bol = false;
        }
        return bol;
    }
    /**
     * 删除用户
     *
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/doDelUserById.infc")
    @ResponseBody
    public Object doDelUserById(String id) throws BizException {
        boolean bol = this.userService.delUserById(id);
        return bol;
    }

    @RequestMapping(value = "/doModifyUser.infc")
    @ResponseBody
    public Object doModifyUser(VUserInfo userInfo)
            throws BizException {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userInfo, sysUser);
        PasswordEncoder encoder = new StandardPasswordEncoder();
        String encodePass= encoder.encode(userInfo.getEncryptPass());
        sysUser.setEncryptPass(encodePass);
        return this.userService.modifyUser(sysUser);
    }

    @RequestMapping(value = "/doGetUserByParams.infc")
    @ResponseBody
    public Object doGetUserByParams(SysUser SysUser,WebRequest request,VUserInfo vUserInfo)throws BizException{

        SysUser sysUserInfo = this.userService.getUserByParams(SysUser);
        if(sysUserInfo==null){
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_NORESULT);//无匹配数据。!
        }
        return sysUserInfo;
    }

    @RequestMapping(value = "/doGetUserPageData.infc")
    @ResponseBody
    public Object doGetUserPageData(PageBean pageBean, WebRequest request)
            throws Exception {
        pageBean = this.userService.getUserPageData(pageBean);
        return pageBean;
    }

    @RequestMapping(value = "/doGetUserByID.infc")
    @ResponseBody
    public Object doGetUserByID(SysUser SysUser,WebRequest request,VUserInfo vUserInfo)throws Exception{
        SysUser sysUserInfo = this.userService.getUserByByID(SysUser);
        if(sysUserInfo==null){
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_NORESULT);//无匹配数据。!
        }
        return sysUserInfo;
    }


    @RequestMapping(value = "/initEdit.do")
    public ModelAndView initEdit(String id){
        ModelAndView mv = new ModelAndView("sys/user/userModForm");
        //获取用戶信息
        SysUser sysUserInfo = this.userService.getUserByByID(id);
        List<SysRole> userSysRoles = this.userService.getUserRoles(id);
        //获取所有角色列表
        List<SysRole> allRoles = this.roleService.getRolesList();
        for (int i = 0 ; i < allRoles.size() ; i++ ){
            SysRole sysRole = allRoles.get(i);
            String roleId = sysRole.getId();
            boolean isOwn = false;
            for (int j = 0 ; j < userSysRoles.size() ; j++ ){
                SysRole userSysRole = userSysRoles.get(j);
                String userRoleId = userSysRole.getId();
                if(roleId.equals(userRoleId)){
                    isOwn = true;
                }
            }
            sysRole.setIsOwn(isOwn);
        }
        mv.addObject("sysUserInfo",sysUserInfo);
        mv.addObject("allRoles",allRoles);
        return mv;
    }



}
