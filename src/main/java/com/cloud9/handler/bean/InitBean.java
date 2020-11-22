package com.cloud9.handler.bean;

import com.cloud9.biz.dao.mybatis.SysDictItemMapper;
import com.cloud9.biz.dao.mybatis.SysDictTypeMapper;
import com.cloud9.biz.models.SysMenus;
import com.cloud9.biz.services.MenuService;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.utils.Springkit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.roroclaw.base.bean.MemoryCache;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class InitBean {

	private static Logger logger = LoggerFactory.getLogger(InitBean.class);

	public InitBean() {
		logger.info("自定义初始化开始....");
//		initDictInfo();
		logger.info("自定义初始化结束....");
	}

//	public void initDictInfo(){
//		SysDictItemMapper sysDictItemMapper = (SysDictItemMapper) Springkit.getWebApplicationContext().getBean("sysDictItemMapper");
//		System.out.println(sysDictItemMapper);
//	}

//	public void initWeixinConfig() {
//		logger.debug("自定义微信参数....");
//		WxClient.APPID = MemoryCache.getSysConfigKey("WEIXIN_APPID");
//		WxClient.SECRET = MemoryCache.getSysConfigKey("WEIXIN_SECRET");
//		WxClient.INDEX_URL = MemoryCache.getSysConfigKey("WEIXIN_INDEX_URL");
//		WxClient.REG_URL = MemoryCache.getSysConfigKey("WEIXIN_REG_URL");
//	}

}
