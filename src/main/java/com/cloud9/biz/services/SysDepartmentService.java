package com.cloud9.biz.services;

import com.cloud9.biz.dao.mybatis.SysDepartmentMapper;
import com.cloud9.biz.models.CmsFolder;
import com.cloud9.biz.models.SysDepartment;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.bean.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("departmentService")
@Transactional
public class SysDepartmentService {

    @Autowired
    private SysDepartmentMapper sysDepartmentMapper;

    public PageBean getDepartsPageDataByFatherId(PageBean pageBean){
        List resList = this.sysDepartmentMapper.selectDepartsPageDataByFatherId(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    public SysDepartment getDepartInfosByID(SysDepartment DepartInfo) {
        SysDepartment departInfo= this.sysDepartmentMapper.selectDepartByPrimaryKey(DepartInfo);
        return departInfo;
    }


    public List<SysDepartment> getDepartsList(SysDepartment DepartInfo) {
        List<SysDepartment> resList = this.sysDepartmentMapper.selectDepartsList(DepartInfo);
        return resList;
    }

    public boolean addDepartInfo(SysDepartment departInfo,SysDepartment fatherdepartInfo) {
        String ID= BizConstants.generatorPid();
        departInfo.setId(ID);
        departInfo.setpId(fatherdepartInfo.getId());
        int fatherLevel=Integer.parseInt(fatherdepartInfo.getLevel());
        departInfo.setLevel(String.valueOf(fatherLevel + 1));
        departInfo.setPath(fatherdepartInfo.getPath()+"#"+ID);
        this.sysDepartmentMapper.insertSelective(departInfo);
        return true;
    }

    public boolean updateFolderInfo(SysDepartment departInfo) {
        this.sysDepartmentMapper.updateByPrimaryKeySelective(departInfo);
        return true;
    }

}
