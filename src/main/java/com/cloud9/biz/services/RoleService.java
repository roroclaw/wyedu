package com.cloud9.biz.services;

import com.cloud9.biz.dao.mybatis.SysMenusMapper;
import com.cloud9.biz.dao.mybatis.SysPowerRoleRelMapper;
import com.cloud9.biz.dao.mybatis.SysRoleMapper;
import com.cloud9.biz.dao.mybatis.SysUserRoleRelMapper;
import com.cloud9.biz.models.SysMenus;
import com.cloud9.biz.models.SysPowerRoleRel;
import com.cloud9.biz.models.SysRole;
import com.cloud9.biz.models.SysUserRoleRel;
import com.cloud9.biz.models.vo.ZTreeNode;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.bean.ItemBean;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.handler.BizException;
import com.roroclaw.base.service.BaseService;
import com.roroclaw.base.bean.ItemBean;
import com.roroclaw.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dengxianzhi on 2017/1/26.
 */
@Service("roleService")
@Transactional
public class RoleService extends BaseService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysMenusMapper sysMenusMapper;

    @Autowired
    private SysUserRoleRelMapper sysUserRoleRelMapper;

    @Autowired
    private SysPowerRoleRelMapper sysPowerRoleRelMapper;

    public List<ItemBean> getRoleItems() {
        List<ItemBean> itemsList = null;
        itemsList = sysRoleMapper.selectRoleItems();
        return itemsList;
    }

    public List<SysRole> getRolesList() {
        List<SysRole> resList = this.sysRoleMapper.selectRolesList();
        return resList;
    }

    /**
     *角色信息分页查询
     *
     * @param pageBean
     * @return
     */
    public PageBean getRolesPageData(PageBean pageBean) {
        List resList = sysRoleMapper.selectRolesPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }


    public List<SysUserRoleRel> getUserRolesRelList(SysUserRoleRel UserRoleRelInfo) {
        List<SysUserRoleRel> resList = this.sysUserRoleRelMapper.selectUserRolesRelList(UserRoleRelInfo);
        return resList;
    }

    public boolean addUserRolesRel(SysUserRoleRel userRoleRelInfo) throws BizException {
        this.sysUserRoleRelMapper.insertUserRolesRel(userRoleRelInfo);
        return true;
    }

    public boolean deleteUserRolesRelByPrimaryKey(String Id) throws BizException {
        this.sysUserRoleRelMapper.deleteUserRolesRelByPrimaryKey(Id);
        return true;
    }

    public boolean deleteUserRolesRelByUserId(String userId) throws BizException {
        this.sysUserRoleRelMapper.deleteUserRolesRelByUserId(userId);
        return true;
    }

    public List<ZTreeNode> getMenus4ZTree(String pid) {
        List<ZTreeNode> zTreeNodeList = new ArrayList<ZTreeNode>();
        List<SysMenus> menuList = null;
        if(pid != null && !"".equals(pid)){
            menuList = this.sysMenusMapper.selectSubMenus(pid);
        }else{
            menuList = this.sysMenusMapper.selectFirstMenus();
        }
        for(int i=0;i < menuList.size();i++){
            SysMenus sysMenus = menuList.get(i);
            ZTreeNode zTreeNode = new ZTreeNode();
            zTreeNode.setId(sysMenus.getId());
            zTreeNode.setName(sysMenus.getName());
            if(BizConstants.MENU_TYPE.FIRST.equals(sysMenus.getType())){
                zTreeNode.setIsParent(true);
                zTreeNode.setOpen(true);
            }else{
                zTreeNode.setIsParent(false);
            }
            zTreeNodeList.add(zTreeNode);
        }
        return zTreeNodeList;
    }

    public boolean addRole(SysRole sysRole) {
        boolean bol = false;
        //判断角色是否重名
        String name = sysRole.getRoleName();
        int count = this.sysRoleMapper.selectCountByName(name);
        if(count > 0){
             throw new BizException("角色名已存在!");
        }
        sysRole.setId(BizConstants.generatorPid());
        sysRole.setType(BizConstants.ROLE_TYPE.CUSTOME);
        int i = this.sysRoleMapper.insertSelective(sysRole);
        if(i > 0){
            bol = true;
        }
        return bol;
    }

    public boolean modRole(SysRole sysRole) {
        boolean bol = false;
        SysRole orgSysRole = this.sysRoleMapper.selectByPrimaryKey(sysRole.getId());
        //判断角色是否重名
        if(!orgSysRole.getRoleName().equals(sysRole.getRoleName())){
            String name = sysRole.getRoleName();
            int count = this.sysRoleMapper.selectCountByName(name);
            if(count > 0){
                throw new BizException("角色名已存在!");
            }
        }
        int i = this.sysRoleMapper.updateByPrimaryKeySelective(sysRole);
        if(i > 0){
            bol = true;
        }
        return bol;
    }

    public boolean delRole(String id) {
        boolean bol = false;
        //验证角色信息
        SysRole sysRole = this.sysRoleMapper.selectByPrimaryKey(id);
        String type = sysRole.getType();
        if(BizConstants.ROLE_TYPE.CUSTOME.equals(type)){
           int i = this.sysRoleMapper.deleteByPrimaryKey(id);
            if(i > 0){
                bol = true;
            }
        }else{
            throw new BizException("此角色类型不允许删除");
        }
        return bol;
    }

    /**
     * 提交角色权限信息
     * @param roleId
     * @param powerIds
     * @return
     */
    public boolean subRolePower(String roleId, String powerIds) {
        boolean bol = false;

        if(roleId == null || "".equals(roleId)){
            throw new BizException("角色信息为空!");
        }

        if(powerIds == null || "".equals(powerIds)){
            throw new BizException("权限信息为空!");
        }
        String[] powerIdArr = powerIds.split(",");
        List<SysPowerRoleRel> sysPowerRoleRels = new ArrayList<SysPowerRoleRel>();
        for (int i = 0; i < powerIdArr.length ; i++){
            String powerId = powerIdArr[i];
            SysPowerRoleRel sysPowerRoleRel = new SysPowerRoleRel();
            sysPowerRoleRel.setId(BizConstants.generatorPid());
            sysPowerRoleRel.setRoleId(roleId);
            sysPowerRoleRel.setMenuId(powerId);
            sysPowerRoleRels.add(sysPowerRoleRel);
        }
        //删除原有数据
        this.sysPowerRoleRelMapper.delRolePowerByRoleId(roleId);
        int i = this.sysPowerRoleRelMapper.subRolePower(sysPowerRoleRels);

        if(i > 0){
            bol = true;
        }
        return bol;
    }

    public List<ZTreeNode> getAllMenus4ZTree() {
        List<ZTreeNode> zTreeNodeList = new ArrayList<ZTreeNode>();
        List<SysMenus> menuList = null;
         menuList = this.sysMenusMapper.selectAllMenus();
        for(int i=0;i < menuList.size();i++){
            SysMenus sysMenus = menuList.get(i);
            ZTreeNode zTreeNode = new ZTreeNode();
            zTreeNode.setId(sysMenus.getId());
            zTreeNode.setName(sysMenus.getName());
            zTreeNode.setpId(sysMenus.getParentCode() != null ? sysMenus.getParentCode():"0");
            if(BizConstants.MENU_TYPE.FIRST.equals(sysMenus.getType())){
                zTreeNode.setIsParent(true);
                zTreeNode.setOpen(true);
            }else{
                zTreeNode.setIsParent(false);
            }
            zTreeNodeList.add(zTreeNode);
        }
        return zTreeNodeList;
    }

    public List<SysPowerRoleRel> getPowersByRoleId(String roleId) {
        List<SysPowerRoleRel> sysPowerRoleRels = null;
        sysPowerRoleRels = this.sysPowerRoleRelMapper.getPowersByRoleId(roleId);
        return sysPowerRoleRels;
    }
}
