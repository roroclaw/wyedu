package com.cloud9.biz.services;

import com.cloud9.biz.dao.mybatis.SysDictItemMapper;
import com.cloud9.biz.dao.mybatis.SysSubjectInfoMapper;
import com.cloud9.biz.models.*;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.handler.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zl on 2017/6/28.
 */
@Service("subjectService")
@Transactional
public class SysSubjectService {
    @Autowired
    private SysSubjectInfoMapper sysSubjectInfoMapper;

    @Autowired
    private SysDictItemMapper sysDictItemMapper;

    /**
     * 教师信息分页查询
     *
     * @param pageBean
     * @return
     */
    public PageBean getSubjectPageData(PageBean pageBean) {
        List resList = sysSubjectInfoMapper.selectSubjectPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    public List<SysSubjectInfo> getSubjectsListByParam(SysSubjectInfo sysSubjectInfo) {
        List<SysSubjectInfo> resList = this.sysSubjectInfoMapper.selectSubjectsListByParam(sysSubjectInfo);
        return resList;
    }

    public List<SysSubjectInfo> selectSubjectByParam(SysSubjectInfo subjectInfo) {
        List<SysSubjectInfo> resList= this.sysSubjectInfoMapper.selectByParam(subjectInfo);
        return resList;
    }


    /**
     * 新增学科信息
     * @param sysSubjectInfo
     * @return
     * @throws com.roroclaw.base.handler.BizException
     */
    public boolean addSubjectInfo(SysSubjectInfo sysSubjectInfo) throws BizException {
        sysSubjectInfo.setStatus(BizConstants.INFO_STATUS.ACTIVE);
        this.sysSubjectInfoMapper.insert(sysSubjectInfo);
        return true;
    }

    /**
     * 修改科目信息
     * @param sysSubjectInfo
     * @return
     */
    public boolean modifySubjectInfo(SysSubjectInfo sysSubjectInfo) {
        boolean bol = false;
        int i = this.sysSubjectInfoMapper.updateByPrimaryKeySelective(sysSubjectInfo);
        if (i > 0) {
            bol = true;
        }
        return bol;
    }
    public SysSubjectInfo getSubjectById(String id) {
        SysSubjectInfo sysSubject = null;
        sysSubject = this.sysSubjectInfoMapper.selectByPrimaryKey(id);
        return sysSubject;
    }

    /**
     * 获取科目下拉数据
     * @return
     */
    public List<SysDictItem> getSubjectItems(String type,String status,String name) {
        List<SysDictItem> itemList = null;
        List<SysSubjectInfo> sysSubjectInfosList = this.sysSubjectInfoMapper.selectSubjectsListByParam(type,status,name);
        int Size = sysSubjectInfosList.size();
        if(sysSubjectInfosList.size() > 0){
            itemList = new ArrayList<SysDictItem>();
        }
        for (int i=0; i < Size;i++){
            SysDictItem sysDictItem = new SysDictItem();
            SysSubjectInfo sysSubjectInfo = sysSubjectInfosList.get(i);
            sysDictItem.setText(sysSubjectInfo.getPeriodText()+"|"+sysSubjectInfo.getName());
            sysDictItem.setCode(sysSubjectInfo.getId());
            itemList.add(sysDictItem);
        }
        return itemList;
    }
}
