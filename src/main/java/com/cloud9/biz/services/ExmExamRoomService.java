package com.cloud9.biz.services;

import com.cloud9.biz.dao.mybatis.*;
import com.cloud9.biz.models.ExaExamInfo;
import com.cloud9.biz.models.ExaExamRoom;
import com.cloud9.biz.models.SysClassroom;
import com.cloud9.biz.models.SysDictItem;
import com.cloud9.biz.util.BizConstants;
import com.roroclaw.base.bean.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Created by zl on 2017/9/28.
 */

@Service("examRoomService")
@Transactional
public class ExmExamRoomService {
    @Autowired
    private ExaExamRoomMapper exaExamRoomMapper;

    @Autowired
    private SysClassroomMapper classroomInfoMapper;


    public PageBean getExamRoomPageData(PageBean pageBean) {
        List resList = this.exaExamRoomMapper.selectExamRoomInfoPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

    public List<ExaExamRoom> getExamRoomListByParam(ExaExamRoom examRoomInfo) {
        List<ExaExamRoom> resList = this.exaExamRoomMapper.selectExamRoomListByParam(examRoomInfo);
        return resList;
    }

    public boolean addExamRoom(ExaExamRoom examRoomInfo) {
        boolean bol = false;
        examRoomInfo.setId(BizConstants.generatorPid());
        SysClassroom tempClassRoom=this.classroomInfoMapper.selectClassroomByPrimaryKey(examRoomInfo.getClassroomId());
        examRoomInfo.setSeatColNum(tempClassRoom.getSiteColNum());
        examRoomInfo.setSeatRowNum(tempClassRoom.getSiteRowNum());
        int i = this.exaExamRoomMapper.insert(examRoomInfo);
        if(i > 0){
            bol = true;
        }
        return bol;
    }

    public boolean modExamRoom(ExaExamRoom exaExamRoomInfo) {
        boolean bol = false;
        int i = this.exaExamRoomMapper.updateByPrimaryKeySelective(exaExamRoomInfo);
        if(i > 0){
            bol = true;
        }
        return bol;
    }

    public ExaExamRoom getExamRoomInfoById(String id) {
        ExaExamRoom exaExamRoom = this.exaExamRoomMapper.selectExamRoomByPrimaryKey(id);
        return exaExamRoom;
    }

    /**
     * 获取考场下拉数据
     * @return
     */
    public List<SysDictItem> getExamRoomItems(String buildingNo,String examPlanId) {
        List<SysDictItem> itemList = null;
        List<ExaExamRoom> examRoomList = this.exaExamRoomMapper.selectExamRoomByParam(buildingNo,examPlanId);
        int examRoomSize = examRoomList.size();
        if(examRoomList.size() > 0){
            itemList = new ArrayList<SysDictItem>();
        }
        for (int i=0; i < examRoomSize;i++){
            SysDictItem sysDictItem = new SysDictItem();
            ExaExamRoom exaExamRoom = examRoomList.get(i);

            sysDictItem.setText(exaExamRoom.getName());
            sysDictItem.setCode(exaExamRoom.getId());
            itemList.add(sysDictItem);
        }
        return itemList;
    }


    public Integer getExamRoomForUsedCount(ExaExamRoom examRoomInfo) {
        int count = this.exaExamRoomMapper.selectExamRoomForUsedCount(examRoomInfo);
        return count;
    }

}
