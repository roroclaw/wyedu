package com.cloud9.biz.controllers;

import com.cloud9.biz.models.TchStuCourseOpenRel;
import com.cloud9.biz.models.TchTchplanSubjectRel;
import com.cloud9.biz.models.TchPlanOpenCourseRel;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.services.TchCourseOpenService;
import com.cloud9.biz.services.TchPlanSubjectRelService;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zl on 2017/7/7.
 */
@Controller
@RequestMapping(value = "/tchPlanSubjectRel")
public class TchPlanSubjectRelController extends BaseController {
    @Autowired
    private TchPlanSubjectRelService tchPlanSubjectRelService;

    @Autowired
    private TchCourseOpenService courseOpenService;

    @RequestMapping(value = "/doGetTchPlanSubjectPageData.infc")
    @ResponseBody
    public Object doGetTchPlanSubjectPageData(PageBean pageBean, WebRequest request)
            throws Exception {
        pageBean = this.tchPlanSubjectRelService.getTchPlanSubjectPageData(pageBean);
        return pageBean;
    }

    @RequestMapping(value = "/doGetTchPlanRelListByTchPlanId.infc")
    @ResponseBody
    public Object doGetTchPlanRelListByTchPlanId(TchTchplanSubjectRel tchTchplanSubjectRel, WebRequest request, VUserInfo vUserInfo) throws BizException {

        List<TchTchplanSubjectRel> TchPlanRelInfosList = this.tchPlanSubjectRelService.getTchPlanRelListByTchPlanId(tchTchplanSubjectRel);
        return TchPlanRelInfosList;
    }


    @RequestMapping(value = "/doAddTchPlanSubjectRel.infc")
    @ResponseBody
    public Object doAddTeachingPlan(TchTchplanSubjectRel tchTchplanSubjectRel,VUserInfo userInfo) throws Exception {
        boolean bol =true;
        this.tchPlanSubjectRelService.addTchPlanSubjectRelInfo(tchTchplanSubjectRel);
        return bol;
    }


    /**
     * 删除
     *
     * @return
     */
    @RequestMapping(value = "/doDelTchPlanSubjectRelById.infc")
    @ResponseBody
    public Object doDelTchPlanSubjectRelById(String id) throws BizException {
        boolean bol = this.tchPlanSubjectRelService.delTchPlanSubjectRelById(id);
        return bol;
    }

  /*  @RequestMapping(value = "/initDetail.do")
    public ModelAndView initDetail(String id){
        ModelAndView mv = new ModelAndView("tch/teachingPlan/tchTeachingPlanDtail");
        //获取计划信息
        TchTchplanSubjectRel tchTchplanSubjectRelInfo=new TchTchplanSubjectRel();
        tchTchplanSubjectRelInfo.setPlanId(id);
        List<TchTchplanSubjectRel> TchPlanRelInfosList = this.tchPlanSubjectRelService.getTchPlanRelListByTchPlanId(tchTchplanSubjectRelInfo);
        mv.addObject("teachingPlanDetailInfo",TchPlanRelInfosList);
        return mv;
    }

    @RequestMapping(value = "/doGetTchPlanCourseOpensList.infc")
    @ResponseBody
    public Object doGetTchPlanCourseOpensList(TchTchplanSubjectRel tchTchplanSubjectRel, WebRequest request, VUserInfo vUserInfo) throws BizException {

        List<TchTchplanSubjectRel> TchPlanRelInfosList = this.tchPlanSubjectRelService.getTchPlanCourseOpensList(tchTchplanSubjectRel);
        return TchPlanRelInfosList;
    }*/

    @RequestMapping(value = "/doGetTchPlanCourseOpenCheckList.infc")
    @ResponseBody
    public Object doGetTchPlanCourseOpenCheckList(TchPlanOpenCourseRel tchPlanOpenCourseRel, WebRequest request, VUserInfo vUserInfo) throws BizException {
        List<TchPlanOpenCourseRel> tchPlanOpenCourseRelResList =new ArrayList<TchPlanOpenCourseRel>();
        List<TchPlanOpenCourseRel> tchPlanOpenCourseRelList = this.tchPlanSubjectRelService.getTchPlanCourseOpenCheckList(tchPlanOpenCourseRel);
       // System.out.println("-------------doGetTchPlanCourseOpenCheckList111|||"+tchPlanOpenCourseRelList.size());
        int tchPlanOpenCourseRelListSize=tchPlanOpenCourseRelList.size();
        for(int i=0 ;i < tchPlanOpenCourseRelListSize; i++){
            TchStuCourseOpenRel tempTchStuCourseOpenRelInfo=new TchStuCourseOpenRel();
            tempTchStuCourseOpenRelInfo.setStuId(tchPlanOpenCourseRelList.get(i).getStuId());
           // int tempSchoolYear_1=Integer.parseInt(tchPlanOpenCourseRelList.get(i).getEnrolYear());
          //  int tempSchoolYear_2=Integer.parseInt(tchPlanOpenCourseRelList.get(i).getEnrolYear())+1;
           // String tempSchoolYear=tempSchoolYear_1+"-"+tempSchoolYear_2;

            int tempSchoolYearInt=Integer.parseInt(tchPlanOpenCourseRelList.get(i).getEnrolDate().substring(0,4))+Integer.parseInt(tchPlanOpenCourseRelList.get(i).getGrade());


            if(Integer.parseInt(tchPlanOpenCourseRelList.get(i).getEduType())==2 && Integer.parseInt(tchPlanOpenCourseRelList.get(i).getPeriod())==1){//小学

            }else if(Integer.parseInt(tchPlanOpenCourseRelList.get(i).getEduType())==2 && Integer.parseInt(tchPlanOpenCourseRelList.get(i).getPeriod())==2){//中学
                tempSchoolYearInt=tempSchoolYearInt-7;
            }else if(Integer.parseInt(tchPlanOpenCourseRelList.get(i).getEduType())==1 && Integer.parseInt(tchPlanOpenCourseRelList.get(i).getPeriod())==2){//高中
                tempSchoolYearInt=tempSchoolYearInt-10;
            }else{
                throw new BizException(BizConstants.HTML_VAL.ERROR_MES_PARAM_ERROR);
            }
            String tempSchoolYear=tempSchoolYearInt+"";

            tempTchStuCourseOpenRelInfo.setSchoolYear(tempSchoolYear);
          //  System.out.println("-------------doGetTchPlanCourseOpenCheckList555|||" + tchPlanOpenCourseRelList.get(i).getEnrolDate().substring(0, 4) + "|||"
         //          + tchPlanOpenCourseRelList.get(i).getGrade() + "|||" + tempSchoolYearInt);
            tempTchStuCourseOpenRelInfo.setTerm(tchPlanOpenCourseRelList.get(i).getTerm());
            tempTchStuCourseOpenRelInfo.setSubjectId(tchPlanOpenCourseRelList.get(i).getSubjectId());
            List<TchStuCourseOpenRel> tchStuCourseOpenRelList = this.courseOpenService.getStuCourseOpenCheckList(tempTchStuCourseOpenRelInfo);
          //  System.out.println("-------------doGetTchPlanCourseOpenCheckList222|||"+tchStuCourseOpenRelList.size());
            //if(tchStuCourseOpenRelList.size()>0){
            //    tchPlanOpenCourseRelList.remove(i);
            //    System.out.println("-------------doGetTchPlanCourseOpenCheckList333|||"+tchStuCourseOpenRelList.size());
          //  }
            if(tchStuCourseOpenRelList.size()==0){
          //      System.out.println("-------------doGetTchPlanCourseOpenCheckList333|||"+tchStuCourseOpenRelList.size());
                tchPlanOpenCourseRelResList.add(tchPlanOpenCourseRelList.get(i));
            }
        }
        return tchPlanOpenCourseRelResList;
    }
}
