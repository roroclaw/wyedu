package com.cloud9.biz.services;

import com.cloud9.biz.dao.mybatis.SysMenusMapper;
import com.cloud9.biz.models.SysMenus;
import com.roroclaw.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dengxianzhi on 2017/1/24.
 */
@Service("menuService")
@Transactional
public class MenuService extends BaseService{
    @Autowired
    private SysMenusMapper sysMenusMapper;

    /**
     * 获取全部菜单信息
     * @return
     */
    public List<SysMenus> getAllSysMenusList(){
        List<SysMenus> sysMenuseList = sysMenusMapper.selectAllMenus();
        return sysMenuseList;
    }
}
