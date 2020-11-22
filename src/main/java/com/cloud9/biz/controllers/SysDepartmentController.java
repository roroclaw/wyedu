package com.cloud9.biz.controllers;

import com.cloud9.biz.models.SysDepartment;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.services.SysDepartmentService;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.controller.BaseController;
import com.roroclaw.base.handler.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/department")
public class SysDepartmentController extends BaseController {

    @Autowired
    private SysDepartmentService departmentService;

    @RequestMapping(value = "/doGetDepartsPageDataByFatherId.infc")
    @ResponseBody
    public Object doGetDepartsPageDataByFatherId(PageBean pageBean, WebRequest request, VUserInfo userInfo)
            throws Exception {
        pageBean = this.departmentService.getDepartsPageDataByFatherId(pageBean);
        return pageBean;
    }

    @RequestMapping(value = "/doGetDepartsList.infc")
    @ResponseBody
    public Object doGetDepartsList(SysDepartment DepartInfo, WebRequest request, VUserInfo vUserInfo) throws BizException {

        List<SysDepartment> DepartsInfosList = this.departmentService.getDepartsList(DepartInfo);
        return DepartsInfosList;
    }


    @RequestMapping(value = "/doGetDepartInfosByID.infc")
    @ResponseBody
    public Object doGetDepartInfosByID(SysDepartment DepartInfo, WebRequest request, VUserInfo vUserInfo) throws Exception {

        SysDepartment departInfo = this.departmentService.getDepartInfosByID(DepartInfo);
        if (departInfo == null) {
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_NORESULT);//无匹配数据。!
        }
        return departInfo;
    }

    @RequestMapping(value = "/doAddDepartInfo.infc")
    @ResponseBody
    public Object doAddDepartInfo(SysDepartment DepartInfo, WebRequest request, VUserInfo vUserInfo) throws BizException {
        boolean bol = false;

        DepartInfo.setId(DepartInfo.getpId());
        SysDepartment fatherDepartInfo = this.departmentService.getDepartInfosByID(DepartInfo);
        bol = this.departmentService.addDepartInfo(DepartInfo, fatherDepartInfo);
        return bol;
    }


    @RequestMapping(value = "/doUpdateDepartInfo.infc")
    @ResponseBody
    public Object doUpdateDepartInfo(SysDepartment DepartInfo, VUserInfo vUserInfo, PageBean pageBean) throws Exception {
        boolean bol = false;
        if (!(DepartInfo.getpId() == null)) {
            SysDepartment fatherDepartInfo = new SysDepartment();
            fatherDepartInfo.setId(DepartInfo.getpId());
            fatherDepartInfo = this.departmentService.getDepartInfosByID(fatherDepartInfo);
            DepartInfo.setPath(fatherDepartInfo.getPath() + "#" + DepartInfo.getId());
            DepartInfo.setLevel(String.valueOf(Integer.parseInt(fatherDepartInfo.getLevel()) + 1));
        }
        bol = this.departmentService.updateFolderInfo(DepartInfo);
        return bol;
    }

    @RequestMapping(value = "/initEdit.do")
    public ModelAndView initEdit(String id){
        ModelAndView mv = new ModelAndView("sys/department/departmentModForm");
        SysDepartment departInfo = new SysDepartment();
        departInfo.setId(id);
        SysDepartment sysDepartInfo = this.departmentService.getDepartInfosByID(departInfo);
        mv.addObject("sysDepartInfo",sysDepartInfo);
        return mv;
    }
}
