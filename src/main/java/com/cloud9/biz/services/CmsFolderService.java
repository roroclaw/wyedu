package com.cloud9.biz.services;

import com.cloud9.biz.dao.mybatis.SysMenusMapper;
import com.cloud9.biz.dao.mybatis.CmsFolderMapper;
import com.cloud9.biz.models.CmsFolder;
import com.cloud9.biz.models.SysMenus;
import com.cloud9.biz.models.SysUser;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.bean.MemoryCache;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.bean.UserBean;
import com.roroclaw.base.handler.BizException;
import com.roroclaw.base.service.BaseAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("folderService")
@Transactional
public class CmsFolderService {

    @Autowired
    private CmsFolderMapper cmsFolderMapper;

    public PageBean getFoldersPageDataByFatherId(PageBean pageBean){
        List resList = this.cmsFolderMapper.selectFlodersPageDataByFatherId(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    public CmsFolder getFolderInfosByID(CmsFolder FolderInfo) {
        CmsFolder folderInfo= this.cmsFolderMapper.selectFolderByPrimaryKey(FolderInfo);
        return folderInfo;
    }


    public List<CmsFolder> getFoldersList(CmsFolder FolderInfo) {
        List<CmsFolder> resList = this.cmsFolderMapper.selectFoldersList(FolderInfo);
        return resList;
    }

    public boolean addFolderInfo(CmsFolder folderInfo,CmsFolder fatherfolderInfo) {
        String ID= BizConstants.generatorPid();
        folderInfo.setFolder_id(ID);
        folderInfo.setFather_id(fatherfolderInfo.getFolder_id());
        int fatherLevel=Integer.parseInt(fatherfolderInfo.getFolder_level());
        folderInfo.setFolder_level(String.valueOf(fatherLevel+1));
        folderInfo.setPath(fatherfolderInfo.getPath()+"#"+ID);
        //System.out.print("*************************String_addFolderInfo222222222"+String.valueOf(fatherLevel+1)+"|||"+folderInfo.getFolder_sequence());
        this.cmsFolderMapper.insertFolder(folderInfo);
        return true;
    }

    public boolean updateFolderInfo(CmsFolder folderInfo) {
        this.cmsFolderMapper.updateFolder(folderInfo);
        return true;
    }

}
