package com.cloud9.biz.controllers;

import com.cloud9.biz.models.CmsFolder;
import com.cloud9.biz.models.SysUserRoleRel;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.services.CmsFolderService;
import com.cloud9.biz.services.RoleService;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.controller.BaseController;
import com.roroclaw.base.handler.BizException;
import com.roroclaw.base.utils.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/folder")
public class CMSFolderController extends BaseController {

    @Autowired
    private CmsFolderService folderService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/doGetFoldersPageDataByFatherId.infc")
    @ResponseBody
    public Object doGetFoldersPageDataByFatherId(PageBean pageBean,WebRequest request,VUserInfo userInfo)
            throws Exception {
        int userType =userInfo.getType();
        pageBean.addQueryparam("userId",userInfo.getId());
        if(userType==BizConstants.USER_TYPE.SUPER){                   ////////////如果为管理员
            pageBean.addQueryparam("locked", userInfo.getAccToken());
        }else{
            pageBean.addQueryparam("userId", userInfo.getId());
        }
        pageBean = this.folderService.getFoldersPageDataByFatherId(pageBean);
        return pageBean;
    }

    @RequestMapping(value = "/doGetFoldersList.infc")
    @ResponseBody
    public Object doGetFoldersList(CmsFolder FolderInfo,WebRequest request,VUserInfo vUserInfo) throws BizException{

        List<CmsFolder> FoldersInfosList = this.folderService.getFoldersList(FolderInfo);
        return FoldersInfosList;
    }

    @RequestMapping(value = "/doGetFoldersListByRoleIdForAddArti.infc")
    @ResponseBody
    public Object doGetFoldersListByRoleIdForAddArti(CmsFolder FolderInfo,WebRequest request,VUserInfo vUserInfo,SysUserRoleRel UserRoleRelInfo) throws BizException{
        UserRoleRelInfo.setUserId(vUserInfo.getId());
        List<SysUserRoleRel> UserRoleRelInfoList = this.roleService.getUserRolesRelList(UserRoleRelInfo);
        int tempRoleId=0;
        for (int i = 0 ; i < UserRoleRelInfoList.size() ; i++ ){
            if(UserRoleRelInfoList.get(i).getRoleId().equals("5") || UserRoleRelInfoList.get(i).getRoleId().equals("1")){///////////信息管理员或管理员
                tempRoleId=5;
            }else if(UserRoleRelInfoList.get(i).getRoleId().equals("4") && tempRoleId!=5){///////////////班主任
                FolderInfo.setFolder_type("1");///////////////限学生公告
                tempRoleId=4;
            }else if(tempRoleId!=4 && tempRoleId!=5){
                FolderInfo.setFolder_type("1010101");///////////////让返回list为空
            }
           //System.out.print("*************************doGetFoldersListByRoleIdForAddArti:::::tempRoleId:::"+tempRoleId);
        }
        List<CmsFolder> FoldersInfosList = this.folderService.getFoldersList(FolderInfo);
        return FoldersInfosList;
    }



    @RequestMapping(value = "/doGetFolderInfosByID.infc")
    @ResponseBody
    public Object doGetFolderInfosByID(CmsFolder FolderInfo,WebRequest request,VUserInfo vUserInfo)throws Exception{

        CmsFolder folderInfo = this.folderService.getFolderInfosByID(FolderInfo);
        if(folderInfo==null){
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_NORESULT);//无匹配数据。!
        }
        return folderInfo;
    }

    @RequestMapping(value = "/doAddFolderInfo.infc")
    @ResponseBody
    public Object doAddFolderInfo(CmsFolder FolderInfo,WebRequest request,VUserInfo vUserInfo) throws BizException{
        boolean bol =false;
        if(request.getParameter("folderType")!=null && !request.getParameter("folderType").equals("")){
            FolderInfo.setFolder_type(request.getParameter("folderType"));
        }
        FolderInfo.setAuthor(vUserInfo.getId());
        FolderInfo.setFolder_id(FolderInfo.getFather_id());
        CmsFolder fatherFolderInfo = this.folderService.getFolderInfosByID(FolderInfo);

        bol =this.folderService.addFolderInfo(FolderInfo,fatherFolderInfo);

        return bol;
    }


    @RequestMapping(value = "/doUpdateFolderInfo.infc")
    @ResponseBody
    public Object doUpdateFolderInfo(CmsFolder FolderInfo,VUserInfo vUserInfo,PageBean pageBean)throws Exception {
        boolean bol =false;


            if(!(FolderInfo.getFather_id()==null)){
                CmsFolder fatherFolderInfo=new CmsFolder();
                fatherFolderInfo.setFolder_id(FolderInfo.getFather_id());
                fatherFolderInfo = this.folderService.getFolderInfosByID(fatherFolderInfo);
                FolderInfo.setPath(fatherFolderInfo.getPath()+"#"+FolderInfo.getFolder_id());
                FolderInfo.setFolder_level(String.valueOf(Integer.parseInt(fatherFolderInfo.getFolder_level())+1));
            }
            FolderInfo.setAuthor(vUserInfo.getId());
            bol =this.folderService.updateFolderInfo(FolderInfo);


        return bol;
    }


}
