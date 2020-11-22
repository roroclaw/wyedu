package com.cloud9.biz.controllers;
import com.cloud9.biz.models.SysClassroom;
import com.cloud9.biz.models.SysDictItem;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.models.ExaExamInfo;
import com.cloud9.biz.models.ExaExamRoom;
import com.cloud9.biz.services.ExmExamRoomService;
import com.cloud9.biz.services.ExmExamService;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.handler.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zl on 2017/9/28.
 */
@Controller
@RequestMapping(value = "/examRoom")
public class ExaExamRoomController {

    @Autowired
    private ExmExamRoomService exmExamRoomService;

    @Autowired
    private ExmExamService exmExamService;

    @RequestMapping(value = "/doGetExamRoomPageData.infc")
    @ResponseBody
    public Object doGetExamRoomPageData(PageBean pageBean, WebRequest request)
            throws Exception {
        pageBean = this.exmExamRoomService.getExamRoomPageData(pageBean);
        return pageBean;
    }

    @RequestMapping(value = "/doGetExamRoomListsByParam.infc")
    @ResponseBody
    public Object doGetExamRoomListsByParam(ExaExamRoom examRoomInfo, WebRequest request, VUserInfo vUserInfo) throws BizException {

        List<ExaExamRoom> ExaExamRoomList = this.exmExamRoomService.getExamRoomListByParam(examRoomInfo);

        return ExaExamRoomList;
    }

    @RequestMapping(value = "/doGetExamRoomInfoById.infc")
    @ResponseBody
    public Object doGetExamRoomInfoById(ExaExamRoom examRoomInfo, WebRequest request, VUserInfo vUserInfo) throws BizException {
        String examRoomId=examRoomInfo.getId();
        ExaExamRoom exaExamRoom = this.exmExamRoomService.getExamRoomInfoById(examRoomId);
        return exaExamRoom;
    }

    @RequestMapping(value = "/doAddExamRoom.infc")
         @ResponseBody
         public Object doAddExamRoom(ExaExamRoom examRoomInfo,VUserInfo vUserInfo)
            throws Exception {
        boolean bol = this.exmExamRoomService.addExamRoom(examRoomInfo);
        return bol;
    }

    @RequestMapping(value = "/doSetExamRoomSeats.infc")
    @ResponseBody
    public Object doSetExamRoomSeats(ExaExamRoom examRoomInfo,VUserInfo vUserInfo)
            throws Exception {
        boolean bol =false;
        if(examRoomInfo.getId()==null || examRoomInfo.getId().equals("")){
            throw new BizException(BizConstants.HTML_VAL.ERROR_MES_PARAM_ERROR);
        }
        ExaExamRoom exaExamRoom = this.exmExamRoomService.getExamRoomInfoById(examRoomInfo.getId());
        String  exaExamPlanId=exaExamRoom.getExamPlanId();
        bol = this.exmExamService.cleanStuExamInfoByExamPlanId(exaExamPlanId);
        if(bol){
            bol = this.exmExamRoomService.modExamRoom(examRoomInfo);
        }
        return bol;
    }

    @RequestMapping(value = "/doModExamRoom.infc")
    @ResponseBody
    public Object doModExamRoom(ExaExamRoom exaExamRoomInfo,VUserInfo vUserInfo)
            throws Exception {
        boolean bol = this.exmExamRoomService.modExamRoom(exaExamRoomInfo);
        return bol;
    }

    @RequestMapping(value = "/initEditExamRoom.do")
    public ModelAndView initEditExamRoom(String id){
        ModelAndView mv = new ModelAndView("exa/examRoom/examRoomModForm");
        ExaExamRoom exaExamRoom = this.exmExamRoomService.getExamRoomInfoById(id);
        mv.addObject("exaExamRoomInfo",exaExamRoom);
        return mv;
    }

    @RequestMapping(value = "/initEditForExamRoomSeat.do")
    public ModelAndView initEditForExamRoomSeat(String id){
        ModelAndView mv = new ModelAndView("exa/examRoom/examRoomSeats");
        ExaExamRoom exaExamRoom = this.exmExamRoomService.getExamRoomInfoById(id);
        mv.addObject("exaExamRoomInfo",exaExamRoom);
        return mv;
    }

    @RequestMapping(value = "/getExamRoomItems.infc")
    @ResponseBody
    public Object getExamRoomItems(String buildingNo,String examPlanId) throws BizException {
        List<SysDictItem> resList = null;
        resList = this.exmExamRoomService.getExamRoomItems(buildingNo,examPlanId);
        return resList;
    }

    @RequestMapping(value = "/doCheckExamRoomForUsed.infc")
    @ResponseBody
    public Object doCheckExamRoomForUsed(ExaExamRoom examRoomInfo,VUserInfo vUserInfo)
            throws Exception {
        boolean bol =false;
        ExaExamRoom ExamRoom = this.exmExamRoomService.getExamRoomInfoById(examRoomInfo.getId());
        int exaExamRoomForUsedNum = this.exmExamRoomService.getExamRoomForUsedCount(ExamRoom);
        if(exaExamRoomForUsedNum>0){////////////////考场被使用
            bol=true;
        }
        return bol;
    }


}
