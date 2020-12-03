package com.cloud9.biz.controllers;

import com.cloud9.biz.dao.mybatis.ArcStudentInfoMapper;
import com.cloud9.biz.models.*;
import com.cloud9.biz.models.vo.VUserInfo;
import com.cloud9.biz.services.ArcStudentService;
import com.cloud9.biz.services.ScoCommentService;
import com.cloud9.biz.services.SysTeacherService;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.controller.BaseController;
import com.roroclaw.base.handler.BizException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 总评controller
 */
@Controller
@RequestMapping(value = "/comment")
public class ScoCommentController extends BaseController {

    @Autowired
    private ScoCommentService scoCommentService;

    @Autowired
    private SysTeacherService sysTeacherService;

    @Autowired
    private ArcStudentService arcStudentService;

    /**
     * 分页查询班主任学生信息
     * @param pageBean
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doGetScoCommentPageData.infc")
    @ResponseBody
    public Object doGetScoCommentPageData(PageBean pageBean,VUserInfo vUserInfo, WebRequest request)
            throws Exception {
        //判断用户身份
        int userType = vUserInfo.getType();
        if (BizConstants.USER_TYPE.SYSTEM_TEACHER != userType){
            throw new BizException("非教师用户!");
        }

        SysTeacherInfo sysTeacherInfo = this.sysTeacherService.getTeacherInfoByUserId(vUserInfo.getId());
        if (sysTeacherInfo == null){
            throw new BizException("非教师用户!");
        }
        pageBean = this.scoCommentService.getScoCommentPageData(pageBean,sysTeacherInfo.getId());
        return pageBean;
    }

    @RequestMapping(value = "/initEdit.do")
    public ModelAndView initEdit(String stuId,String schoolYear,String term){
        ModelAndView mv = new ModelAndView("sco/score/scoCommentForm");
        //判断此学年学期 stuId是否存在
        List<ScoCommentMarks> scoCommentMarks= this.scoCommentService.getCommentMark(stuId, schoolYear, term);
        ScoComment scoComment = this.scoCommentService.getComment(stuId, schoolYear, term);
        String commentMarksList="";
        String comment=scoComment.getComment();
        if(scoCommentMarks != null){
            for(int i=0;i<scoCommentMarks.size();i++){
                if (i+1 >= scoCommentMarks.size()){
                    commentMarksList = commentMarksList+scoCommentMarks.get(i).getMark();
                    comment = scoCommentMarks.get(i).getComment();
                }else{
                    commentMarksList = commentMarksList+scoCommentMarks.get(i).getMark()+",";
                }

            }
        }

        //后去学员信息
        ArcStudentInfoWithBLOBs arcStudentInfoWithBLOBs = this.arcStudentService.getAllStudentInfoByStuId(stuId);

        mv.addObject("stuId", stuId);
        mv.addObject("arcStudent", arcStudentInfoWithBLOBs);
        mv.addObject("schoolYear", schoolYear);
        mv.addObject("term", term);
        mv.addObject("commentMarksList", commentMarksList);
        mv.addObject("comment",comment);
        return mv;
    }

    @RequestMapping(value = "/addorUpdateComment.infc")
    @ResponseBody
    public Object addorUpdateComment(ScoComment newScoComment,VUserInfo vUserInfo){
        //清理输入中的空格
        newScoComment.setComment(newScoComment.getComment().trim());

        //判断此学年学期 stuId是否存在
        String stuId = newScoComment.getStuId();
        String schoolYear = newScoComment.getSchoolYear();
        String term = newScoComment.getTerm();
        ScoComment oldScoComment = this.scoCommentService.getComment(stuId,schoolYear,term);
        String commentId = "";
        if(oldScoComment == null){//新增
            commentId = BizConstants.generatorPid();
            newScoComment.setId(commentId);
            newScoComment.setCreateTime(new Date());
            newScoComment.setCreator(vUserInfo.getId());
            newScoComment.setUpdateTime(new Date());
            newScoComment.setUpdater(vUserInfo.getId());
            newScoComment.setStatus(BizConstants.SCO_COMMENT_STATUS.PUBLISH);
            this.scoCommentService.addComment(newScoComment);
        }else{//修改
            commentId = oldScoComment.getId();
            oldScoComment.setUpdateTime(new Date());
            oldScoComment.setUpdater(vUserInfo.getId());
            oldScoComment.setStatus(BizConstants.SCO_COMMENT_STATUS.PUBLISH);
            oldScoComment.setComment(newScoComment.getComment());
            this.scoCommentService.updateComment(oldScoComment);
        }
        this.scoCommentService.deleteCommentItemsByCommentID(commentId);
        String marks[]=newScoComment.getCommentMarksList().split(",");

        List<ScoCommentMarks> scoCommentMarksList = new ArrayList<ScoCommentMarks>();
        for(int i=0;i<marks.length;i++){
            ScoCommentMarks scoCommentMarks = new ScoCommentMarks();
            scoCommentMarks.setCommentId(commentId);
            scoCommentMarks.setId(BizConstants.generatorPid()+i);
            if(i+1<10){
                scoCommentMarks.setCode("0"+(i+1));
            }else{
                scoCommentMarks.setCode(""+(i+1));
            }
            scoCommentMarks.setMark(marks[i]);
            scoCommentMarksList.add(i,scoCommentMarks);

        }
        //for(int i=0;i<scoCommentMarksList.size();i++){
        //    System.out.println("addorUpdateComment:::::::"+scoCommentMarksList.get(i).getId());
       // }

        if(this.scoCommentService.addCommentMarks(scoCommentMarksList)){

        }else{
            throw new BizException("数据写入失败!");
        }
        return true;
    }

    /**
     * 发布成绩信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/batchPublish.infc")
    @ResponseBody
    public Object batchPublishScore(@RequestParam(value = "idArr[]") String[] idArr) throws Exception {
        boolean bol = this.scoCommentService.batchPublishComment(idArr);
        return bol;
    }

}
