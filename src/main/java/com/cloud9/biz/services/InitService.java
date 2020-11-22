package com.cloud9.biz.services;

import com.cloud9.biz.dao.mybatis.SysDictItemMapper;
import com.cloud9.biz.dao.mybatis.SysGradesMapper;
import com.cloud9.biz.dao.mybatis.SysMenusMapper;
import com.cloud9.biz.models.SysDictItem;
import com.cloud9.biz.models.SysMenus;
import com.cloud9.biz.util.BizConstants;
import com.cloud9.biz.util.EduKit;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.handler.BizException;
import com.roroclaw.base.service.BaseInitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dengxianzhi on 2017/1/24.
 */
@Service("initService")
@Transactional
public class InitService extends BaseInitService {

    private static Logger logger = LoggerFactory.getLogger(InitService.class);

    @Autowired
    private SysMenusMapper sysMenusMapper;

    @Autowired
    private SysDictItemMapper sysDictItemMapper;

    @Autowired
    private SysGradesMapper sysGradesMapper;

    @Override
    public void afterPropertiesSet() throws BizException {
        initDictItems();
        initMenus();
        initGradeInfo();
        logger.info("设置每页分页数为30...");
        PageBean.SQL_PARAM_LIMIT_DEFAULTVALUE = 30;
    }

    private void initDictItems(){
        logger.info("字典表初始化开始....");
        BizConstants.DICT_MAP = new HashMap<String, Map<String, String>>();
        List<SysDictItem> sysDictItemList = sysDictItemMapper.selectAll();
        int count = sysDictItemList.size();
        for (int i=0;i < count ; i++ ){
            SysDictItem sysDictItem = sysDictItemList.get(i);
            String key = sysDictItem.getDictType();
            Map itemsMap = BizConstants.DICT_MAP.get(key);
            if(itemsMap == null){
                itemsMap = new HashMap();
                BizConstants.DICT_MAP.put(key,itemsMap);
            }
            itemsMap.put(sysDictItem.getCode(),sysDictItem.getText());
        }
        logger.info("字典表初始化结束....");
    }

    private void initMenus(){
        logger.info("菜单配置初始化开始....");
        List<SysMenus> sysMenusList =sysMenusMapper.selectAllMenus();
        for (int i = 0 ;i < sysMenusList.size() ;i++){
            SysMenus sysMenus = sysMenusList.get(i);
            String url = sysMenus.getUrl();
            String id = sysMenus.getId();
            if(url != null && !"".equals(url)){
                BizConstants.MENU_MAP.put(id,url);
            }
        }
        logger.info("菜单配置初始化结束....");
    }

    public void initGradeInfo(){
        logger.info("年级配置初始化开始....");
        Map<String,String> itemGradeMap = new HashMap();
        List<SysDictItem> sysGradesList = this.sysGradesMapper.getAllGradeItems();
        for (int i = 0 ;i < sysGradesList.size() ;i++){
            SysDictItem sysDictItem = sysGradesList.get(i);
            String code = sysDictItem.getCode();
            String text = sysDictItem.getText();
            itemGradeMap.put(code,text);
        }
        EduKit.setGradeMap(itemGradeMap);
        logger.info("年级配置初始化结束....");
    }
}
