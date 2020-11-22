package com.cloud9.biz.controllers;

import com.cloud9.biz.models.SysUser;
import com.cloud9.biz.services.RoleService;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.bean.ItemBean;
import com.roroclaw.base.controller.BaseController;
import com.roroclaw.base.handler.BizException;
import com.roroclaw.base.utils.Springkit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by dengxianzhi on 2017/1/26.
 */
@Controller
@RequestMapping(value = "/sys")
public class SysController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(SysController.class);

    @Autowired
    private RoleService roleService;

    @RequestMapping("/getRoleItems.infc")
    @ResponseBody
    public Object getRoleItems() throws BizException {
        List<ItemBean> resList = null;
        resList = this.roleService.getRoleItems();
        return resList;
    }
}
