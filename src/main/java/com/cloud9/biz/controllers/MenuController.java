package com.cloud9.biz.controllers;

import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.controller.BaseController;
import com.roroclaw.base.utils.Springkit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by dengxianzhi on 2017/1/26.
 */
@Controller
public class MenuController  extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(MenuController.class);

    @RequestMapping("/goMenu.do")
    public ModelAndView goMenu(String id)
            throws Exception {
//        logger.debug("访问地址="+path);
        //注册当前session菜单信息
        String url = BizConstants.MENU_MAP.get(id);
        if(url != null && !"".equals(url)){
            HttpSession session = Springkit.getRequest().getSession();
            session.setAttribute(BizConstants.SESSION_VAL.CUR_MENU,id);
        }else{
            if("main".equals(id)){
                HttpSession session = Springkit.getRequest().getSession();
                session.setAttribute(BizConstants.SESSION_VAL.CUR_MENU,"");
                url = "main.html";
            }
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/"+url);
        return mv;
    }
}
