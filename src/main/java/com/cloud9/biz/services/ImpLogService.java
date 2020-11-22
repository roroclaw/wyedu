package com.cloud9.biz.services;

import com.cloud9.biz.dao.mybatis.SysImportLogMapper;
import com.roroclaw.base.bean.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by roroclaw on 2017/8/17.
 */
@Service("impLogService")
@Transactional
public class ImpLogService {
    @Autowired
    private SysImportLogMapper sysImportLogMapper;

    public PageBean getImpLogInfoPageData(PageBean pageBean,String type) {
        List resList = sysImportLogMapper.selectStudentImpLogPageData(pageBean,type);
        pageBean.setData(resList);
        return pageBean;
    }
}
