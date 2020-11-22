package com.cloud9.biz.services;

import com.cloud9.biz.dao.mybatis.SysDictItemMapper;
import com.cloud9.biz.dao.mybatis.SysClassroomMapper;
import com.cloud9.biz.models.SysDictItem;
import com.cloud9.biz.models.SysClassroom;
import com.cloud9.biz.models.TchClassInfo;
import com.cloud9.biz.models.vo.ValidateDelVo;
import com.cloud9.biz.util.BizConstants;
import com.cloud9.biz.util.EduKit;
import com.cloud9.biz.util.ToolKit;
import com.roroclaw.base.bean.PageBean;
import com.roroclaw.base.handler.BizException;
import com.roroclaw.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dxz on 2017/8/13.
 */
@Service("classroomService")
@Transactional
public class SysClassroomService extends BaseService {
    @Autowired
    private SysClassroomMapper classroomInfoMapper;

    public PageBean getClassPageData(PageBean pageBean) {
        List resList = this.classroomInfoMapper.selectClassroomPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }


    /**
     * 获取教室下拉数据
     * @return
     */
    public List<SysDictItem> getClassroomItems(String buildingNo) {
        List<SysDictItem> itemList = null;
        List<SysClassroom> classroomList = this.classroomInfoMapper.selectClassRoomByParam(buildingNo);
        int roomSize = classroomList.size();
        if(classroomList.size() > 0){
            itemList = new ArrayList<SysDictItem>();
        }
        for (int i=0; i < roomSize;i++){
            SysDictItem sysDictItem = new SysDictItem();
            SysClassroom sysClassroom = classroomList.get(i);

            sysDictItem.setText(sysClassroom.getName());
            sysDictItem.setCode(sysClassroom.getId());
            itemList.add(sysDictItem);
        }
        return itemList;
    }

    /**
     * 获取教室数据
     * @return
     */
    public List<SysClassroom> getClassroomList(String buildingNo) {
        List<SysClassroom> classroomList = this.classroomInfoMapper.selectClassRoomByParam(buildingNo);
        return classroomList;
    }

    /**
     * 获取教室信息
     * @param id
     * @return
     */
    public SysClassroom getClassroomInfoById(String id) {
        SysClassroom sysClassroom = this.classroomInfoMapper.selectClassroomByPrimaryKey(id);
        return sysClassroom;
    }

    /**
     * 新增教室信息
     * @param sysClassroomInfo
     * @return
     */
    public boolean addClassroom(SysClassroom sysClassroomInfo) {
        boolean bol = false;

        sysClassroomInfo.setId(BizConstants.generatorPid());
        int i = this.classroomInfoMapper.insert(sysClassroomInfo);
        if(i > 0){
            bol = true;
        }
        return bol;
    }

    public boolean modClassroom(SysClassroom sysClassroomInfo, String userId) {
        boolean bol = false;
        int i = this.classroomInfoMapper.updateByPrimaryKeySelective(sysClassroomInfo);
        if(i > 0){
            bol = true;
        }
        return bol;
    }

}
