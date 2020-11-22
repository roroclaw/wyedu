package com.cloud9.biz.services;


import com.cloud9.biz.dao.mybatis.SysMajorMapper;
import com.cloud9.biz.models.SysDictItem;
import com.cloud9.biz.models.SysMajor;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.handler.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("sysMajorService")
@Transactional
/**
 * Created by zl on 2017/11/13.
 */
public class SysMajorService {
    @Autowired
    private SysMajorMapper sysMajorMapper;

    public List<SysDictItem> getFatherMajorItems(String type){
        List<SysDictItem> resList = null;
        resList = this.sysMajorMapper.getFatherMajorItems(type);
        return resList;
    }

    public PageBean getMajorPageData(PageBean pageBean) {
        List resList = this.sysMajorMapper.selectMajorPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    public SysMajor getMajorById(String id) {
        SysMajor sysMajor = null;
        sysMajor = this.sysMajorMapper.selectByID(id);
        return sysMajor;
    }
    /**
     * 新增信息
     * @param sysMajorInfo
     * @return
     * @throws com.roroclaw.base.handler.BizException
     */
    public boolean addMajorInfo(SysMajor sysMajorInfo) throws BizException {
        sysMajorInfo.setStatus(BizConstants.INFO_STATUS.ACTIVE);
        this.sysMajorMapper.insertSelective(sysMajorInfo);
        return true;
    }

    /**
     * 修改信息
     * @param sysMajorInfo
     * @return
     */
    public boolean modifyMajorInfo(SysMajor sysMajorInfo) {
        boolean bol = false;
        int i = this.sysMajorMapper.updateByPrimaryKeySelective(sysMajorInfo);
        if (i > 0) {
            bol = true;
        }
        return bol;
    }
}
