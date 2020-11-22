package com.cloud9.biz.controllers;

import com.cloud9.biz.models.ArcStudentInfo;
import com.cloud9.biz.models.SysPowerRoleRel;
import com.cloud9.biz.models.SysRole;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.models.SysUserRoleRel;
import com.cloud9.biz.models.vo.ZTreeNode;
import com.cloud9.biz.services.RoleService;
import com.roroclaw.base.annotation.NativeInfc;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.controller.BaseController;
import com.roroclaw.base.handler.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/role")
public class SysRoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    //	/**
//	 * 角色查询
//	 *
//	 * @param pageBean
//	 * @param request
//	 * @return
//	 * @throws Exception
//	 */
    @RequestMapping(value = "/doGetRolesList.infc")
    @ResponseBody
    public Object doGetRolesList(WebRequest request,VUserInfo vUserInfo) throws BizException{
        List<SysRole> RolesInfosList = this.roleService.getRolesList();
        return RolesInfosList;
    }


    /**
     * 查询角色信息分页信息
     *
     * @param pageBean
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doGetRolesPageData.infc")
    @ResponseBody
    public Object doGetRolesPageData(PageBean pageBean, WebRequest request)
            throws Exception {
        pageBean = this.roleService.getRolesPageData(pageBean);
        return pageBean;
    }


    @RequestMapping(value = "/doGetUserRolesRelList.infc")
    @ResponseBody
    public Object doGetUserRolesRelList(SysUserRoleRel UserRoleRelInfo,WebRequest request,VUserInfo vUserInfo) throws BizException{

        List<SysUserRoleRel> UserRoleRelInfoList = this.roleService.getUserRolesRelList(UserRoleRelInfo);
        return UserRoleRelInfoList;
    }

    @RequestMapping(value = "/doDelRole.infc")
    @ResponseBody
    public Object doDelRole(String id) throws BizException{
        boolean bol = false;
        bol = this.roleService.delRole(id);
        return bol;
    }

    @RequestMapping(value = "/doAddRole.infc")
    @ResponseBody
    public Object doAddRole(SysRole sysRole) throws BizException{
        boolean bol = false;
        bol = this.roleService.addRole(sysRole);
        return bol;
    }

    @RequestMapping(value = "/doModRole.infc")
    @ResponseBody
    public Object doModRole(SysRole sysRole) throws BizException{
        boolean bol = false;
        bol = this.roleService.modRole(sysRole);
        return bol;
    }


    /**
     * 获取菜单ztreeNode结构
     * @param pid
     * @return
     * @throws BizException
     */
    @NativeInfc
    @RequestMapping(value = "/doGetMenus4ZTree.infc")
    @ResponseBody
    public Object doGetMenus4ZTree(@RequestParam(required = false,value = "id") String pid) throws BizException{
        List<ZTreeNode> UserRoleRelInfoList = this.roleService.getMenus4ZTree(pid);
        return UserRoleRelInfoList;
    }

    /**
     * 获取菜单ztreeNode结构
     * @return
     * @throws BizException
     */
    @RequestMapping(value = "/doGetAllMenus4ZTree.infc")
    @ResponseBody
    public Object doGetAllMenus4ZTree() throws BizException{
        List<ZTreeNode> userRoleRelInfoList = this.roleService.getAllMenus4ZTree();
        return userRoleRelInfoList;
    }

    @RequestMapping(value = "/doSubRolePower.infc")
    @ResponseBody
    public Object doSubRolePower(String roleId ,String powerIds) throws BizException{
        boolean bol = true;
        bol = this.roleService.subRolePower(roleId,powerIds);
        return bol;
    }

    @RequestMapping(value = "/doGetPowersByRoleId.infc")
    @ResponseBody
    public Object doGetPowersByRoleId(String roleId) throws BizException{
        List<SysPowerRoleRel> sysPowerRoleRels = null;
        sysPowerRoleRels = this.roleService.getPowersByRoleId(roleId);
        return sysPowerRoleRels;
    }
}
