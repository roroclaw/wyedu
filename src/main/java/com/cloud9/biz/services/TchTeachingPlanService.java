package com.cloud9.biz.services;

import com.cloud9.biz.dao.mybatis.TchTeachingPlanMapper;
import com.cloud9.biz.models.SysDictItem;
import com.cloud9.biz.models.TchStuCourseOpenRel;
import com.cloud9.biz.models.TchTchplanSubjectRel;
import com.cloud9.biz.models.TchTeachingPlan;
import com.cloud9.biz.models.vo.VTeachPlan;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.handler.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zl on 2017/7/3.
 */
@Service("tchTeachingPlanService")
@Transactional
public class TchTeachingPlanService {
    @Autowired
    private TchTeachingPlanMapper tchTeachingPlanMapper;

    /**
     * 教学计划信息分页查询
     *
     * @param pageBean
     * @return
     */
    public PageBean getTeachingPlanPageData(PageBean pageBean) {
        List resList = tchTeachingPlanMapper.selectTeachingPlanPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }
    /**
     * 新增教学计划信息
     * @param tchTeachingPlanInfo
     * @return
     * @throws com.roroclaw.base.handler.BizException
     */
    public boolean addTeachingPlanInfo(TchTeachingPlan tchTeachingPlanInfo) throws BizException {
        tchTeachingPlanInfo.setId(BizConstants.ID_PRECODE.SYS_BASEINFO_ID_PRECODE + BizConstants.generatorPid());
        tchTeachingPlanInfo.setStatus(BizConstants.INFO_STATUS.ACTIVE);
        this.tchTeachingPlanMapper.insertSelective(tchTeachingPlanInfo);
        return true;
    }

    /**
     * 修改教学计划信息
     * @param tchTeachingPlanInfo
     * @return
     */
    public boolean modifyTeachingPlanInfo(TchTeachingPlan tchTeachingPlanInfo) {
        boolean bol = false;
        int i = this.tchTeachingPlanMapper.updateByPrimaryKeySelective(tchTeachingPlanInfo);
        if (i > 0) {
            bol = true;
        }
        return bol;
    }


    public TchTeachingPlan getTeachingPlanById(String id) {
        TchTeachingPlan tchTeachingPlan = null;
        tchTeachingPlan = this.tchTeachingPlanMapper.selectTchPlanByPrimaryKey(id);
        return tchTeachingPlan;
    }


    public List<VTeachPlan> getTeachingPlanItems(String subMajorId, String period) {
        List<VTeachPlan> tchTeachingPlans = this.tchTeachingPlanMapper.selectTeachingPlanItemsByParams(subMajorId, period);
        return tchTeachingPlans;
    }


}
