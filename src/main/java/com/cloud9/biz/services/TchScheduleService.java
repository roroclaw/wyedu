package com.cloud9.biz.services;

import com.cloud9.biz.dao.mybatis.TchScheduleMapper;
import com.cloud9.biz.dao.mybatis.TchScheduleCourseOpenMapper;
import com.cloud9.biz.models.TchCourseWithBLOBs;
import com.cloud9.biz.models.TchSchedule;
import com.cloud9.biz.models.TchScheduleCourseOpen;
import com.cloud9.biz.models.TchScheduleSection;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.bean.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zl on 2017/9/8.
 */
@Service("scheduleService")
@Transactional
public class TchScheduleService {

    @Autowired
    private TchScheduleMapper tchScheduleMapper;

    @Autowired
    private TchScheduleCourseOpenMapper tchScheduleCourseOpenMapper;


    public PageBean getSchedulePageData(PageBean pageBean) {
        List resList = this.tchScheduleMapper.selectSchedulePageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    public TchSchedule getScheduleById(String id) {
        TchSchedule tchSchedule = null;
        tchSchedule = this.tchScheduleMapper.getScheduleById(id);
        return tchSchedule;
    }


    /**
     * 新增课表信息
     * @param tchScheduleInfo
     * @return
     */
    public boolean addSchedule(TchSchedule tchScheduleInfo) {
        boolean bol = false;
        String newId = BizConstants.ID_PRECODE.SCHEDULE_ID_PRECODE+BizConstants.generatorPid();
        tchScheduleInfo.setId(newId);
        if(tchScheduleInfo.getStatus()==null || tchScheduleInfo.getStatus().equals("")){
            tchScheduleInfo.setStatus(BizConstants.COMMON_STATUS.STOP_STR);
        }

        int i = this.tchScheduleMapper.insert(tchScheduleInfo);
        if(i > 0){
            bol = true;
        }
        return bol;
    }

    /**
     * 修改课表信息
     * @param tchScheduleInfo
     * @return
     */
    public boolean modifyScheduleInfo(TchSchedule tchScheduleInfo) {
        boolean bol = false;
        int i = this.tchScheduleMapper.updateByPrimaryKeySelective(tchScheduleInfo);
        if (i > 0) {
            bol = true;
        }
        return bol;
    }

    public List<TchScheduleSection> getScheduleSectionList() {
        List<TchScheduleSection> scheduleSectionListList = this.tchScheduleMapper.selectScheduleSectionList();
        return scheduleSectionListList;
    }

    public List<TchScheduleCourseOpen> getScheduleCourseOpenRelList(TchScheduleCourseOpen tchScheduleCourseOpenRel) {
        List<TchScheduleCourseOpen> scheduleCourseOpenRelList = this.tchScheduleCourseOpenMapper.selectScheduleCourseOpenRelList(tchScheduleCourseOpenRel);
        return scheduleCourseOpenRelList;
    }

    public List<TchScheduleCourseOpen> getScheduleCourseOpenRelListForShow(String stuId,String schoolYear,String term,String teacherId,String orderParam) {
        List<TchScheduleCourseOpen> scheduleCourseOpenRelList = this.tchScheduleCourseOpenMapper.selectScheduleCourseOpenRelListForShow(stuId,schoolYear, term,teacherId, orderParam);
        return scheduleCourseOpenRelList;
    }

    public List<TchScheduleCourseOpen> getScheduleCourseOpenRelListForClassShow(String schoolYear,String term,String classId,String orderParam) {
        List<TchScheduleCourseOpen> scheduleCourseOpenRelList = this.tchScheduleCourseOpenMapper.selectScheduleCourseOpenRelListForClassShow(schoolYear,term,classId,orderParam);
        return scheduleCourseOpenRelList;
    }

    public List<TchScheduleCourseOpen> getScheduleCourseOpenRelListForCollecting(String schoolYear,String term,String period) {
        List<TchScheduleCourseOpen> scheduleCourseOpenRelList = this.tchScheduleCourseOpenMapper.selectScheduleCourseOpenRelListForCollecting(schoolYear,term,period);
        return scheduleCourseOpenRelList;
    }

    public List<TchScheduleCourseOpen> getClassInfoForCollectingShow(String graduateYears,String term,String period) {
        List<TchScheduleCourseOpen> scheduleCourseOpenRelList = this.tchScheduleCourseOpenMapper.selectClassInfoForCollectingShow(graduateYears, term,period);
        return scheduleCourseOpenRelList;
    }

    public boolean addScheduleCourseOpenRelInfo(TchScheduleCourseOpen newScheduleCourseOpenRel) {
        boolean bol = false;
        String newId = BizConstants.generatorPid();
        newScheduleCourseOpenRel.setId(newId);
        int i = this.tchScheduleCourseOpenMapper.insert(newScheduleCourseOpenRel);
        if(i > 0){
            bol = true;
        }
        return bol;
    }

    public boolean cleanScheduleCourseOpenRelInfo(TchScheduleCourseOpen scheduleCourseOpenRel) {
        boolean bol = false;
        int i = this.tchScheduleCourseOpenMapper.cleanScheduleCourseOpenRelInfo(scheduleCourseOpenRel);
        if (i >= 0) {
            bol = true;
        }
        return bol;
    }

    public List<TchScheduleCourseOpen> checkScheduleCourseOpenRelForTeacher(TchScheduleCourseOpen tchScheduleCourseOpenRel) {
        List<TchScheduleCourseOpen> scheduleCourseOpenRelList = this.tchScheduleCourseOpenMapper.checkScheduleCourseOpenRelForTeacher(tchScheduleCourseOpenRel);
        return scheduleCourseOpenRelList;
    }

    public int checkScheduleCourseOpenRelForStu(TchScheduleCourseOpen tchScheduleCourseOpenRel) {
        List<TchScheduleCourseOpen> scheduleCourseOpenRelList = this.tchScheduleCourseOpenMapper.checkScheduleCourseOpenRelForStu(tchScheduleCourseOpenRel);
        int count=scheduleCourseOpenRelList.size();
        return count;
    }

}
