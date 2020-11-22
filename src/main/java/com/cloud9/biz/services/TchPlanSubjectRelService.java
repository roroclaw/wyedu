package com.cloud9.biz.services;

import com.cloud9.biz.dao.mybatis.TchTchplanSubjectRelMapper;
import com.cloud9.biz.models.TchPlanOpenCourseRel;
import com.cloud9.biz.models.TchTchplanSubjectRel;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.handler.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zl on 2017/7/7.
 */
@Service("tchPlanSubjectRelService")
@Transactional
public class TchPlanSubjectRelService {

    @Autowired
    private TchTchplanSubjectRelMapper tchTchplanSubjectRelMapper;


    /**
     * 新增教学计划科目信息
     * @param tchTchplanSubjectRel
     * @return
     * @throws com.roroclaw.base.handler.BizException
     */


    /**
     * 教学计划信息分页查询
     *
     * @param pageBean
     * @return
     */
    public PageBean getTchPlanSubjectPageData(PageBean pageBean) {
        List resList = tchTchplanSubjectRelMapper.selectTchPlanSubjectPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    public List<TchTchplanSubjectRel> getTchPlanRelListByTchPlanId(TchTchplanSubjectRel tchTchplanSubjectRel) {
        List<TchTchplanSubjectRel> resList = this.tchTchplanSubjectRelMapper.selectTchPlanRelListByTchPlanId(tchTchplanSubjectRel);
        return resList;
    }

    public List<TchTchplanSubjectRel> getTchPlanRelListByParam(TchTchplanSubjectRel tchTchplanSubjectRel) {
        List<TchTchplanSubjectRel> resList = this.tchTchplanSubjectRelMapper.selectTchPlanRelListByParam(tchTchplanSubjectRel);
        return resList;
    }

    public List<TchTchplanSubjectRel> getTchPlanRelSubjectsListByTchPlanId(TchTchplanSubjectRel tchTchplanSubjectRel) {
        List<TchTchplanSubjectRel> resList = this.tchTchplanSubjectRelMapper.selectTchPlanRelSubjectsListByTchPlanId(tchTchplanSubjectRel);
        return resList;
    }

    public boolean addTchPlanSubjectRelInfo(TchTchplanSubjectRel tchTchplanSubjectRel) throws BizException {
        String subjectIds=tchTchplanSubjectRel.getSubjectIds();
        List subjectIdsList= this.changeGroupToList(subjectIds);
        TchTchplanSubjectRel newRel=tchTchplanSubjectRel;
        for(int i=0;i < subjectIdsList.size();i++){
            newRel.setSubjectId((String)subjectIdsList.get(i));

            int count = this.tchTchplanSubjectRelMapper.selectTchPlanSubjectRelCountByParam(newRel);
            if(!(count>0)){
                newRel.setId(BizConstants.generatorPid());
                this.tchTchplanSubjectRelMapper.insertSelective(newRel);
            }
        }
        return true;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean delTchPlanSubjectRelById(String id) throws BizException {
        boolean bol = false;
        int i = this.tchTchplanSubjectRelMapper.deleteByPrimaryKey(id);
        if (i > 0) {
            bol = true;
        }
        return bol;
    }


    public List changeGroupToList(String Group) {
        List newList=new ArrayList();
        String a [] = Group.split("#");
        for(int i=0;i<a.length;i++){
            newList.add(a[i]);
        }
        return newList;
    }

   /* public List<TchTchplanSubjectRel> getTchPlanCourseOpensList(TchTchplanSubjectRel tchTchplanSubjectRel) {
        List<TchTchplanSubjectRel> resList = this.tchTchplanSubjectRelMapper.selectTchPlanCourseOpensList(tchTchplanSubjectRel);
        return resList;
    }*/
    public List<TchPlanOpenCourseRel> getTchPlanCourseOpenCheckList(TchPlanOpenCourseRel tchPlanOpenCourseRel) {
        List<TchPlanOpenCourseRel> resList = this.tchTchplanSubjectRelMapper.selectTchPlanCourseOpenCheckList(tchPlanOpenCourseRel);
        return resList;
    }
}
