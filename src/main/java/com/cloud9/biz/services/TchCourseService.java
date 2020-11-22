package com.cloud9.biz.services;

import com.cloud9.biz.dao.mybatis.TchCourseMapper;
import com.cloud9.biz.models.TchCourse;
import com.cloud9.biz.models.TchCourseWithBLOBs;
import com.roroclaw.base.bean.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("courseService")
@Transactional
public class TchCourseService {



    @Autowired
    private TchCourseMapper tchCourseMapper;

    public PageBean getCoursesPageData(PageBean pageBean){
        List resList = this.tchCourseMapper.selectCoursesPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }

   /* public PageBean getCoursesGroupPageData(PageBean pageBean){
        List resList = this.tchCourseMapper.selectCoursesGroupPageData(pageBean);
        pageBean.setData(resList);
        return pageBean;
    }
    */
    public List<TchCourse> getCoursesList(TchCourse courseInfo) {
        List<TchCourse> resList = this.tchCourseMapper.selectCoursesList(courseInfo);
        return resList;
    }

    public TchCourseWithBLOBs getCourseById(String id) {
        TchCourseWithBLOBs tchCourse = null;
        tchCourse = this.tchCourseMapper.selectByPrimaryKey(id);
        return tchCourse;
    }


    public boolean addCourse(TchCourseWithBLOBs courseInfo){

        this.tchCourseMapper.insertSelective(courseInfo);
        return true;
    }

    /**
     * 修改课程信息
     * @param courseInfo
     * @return
     */
    public boolean modifyCourseInfo(TchCourseWithBLOBs courseInfo) {
        boolean bol = false;
        int i = this.tchCourseMapper.updateByPrimaryKeySelective(courseInfo);
        if (i > 0) {
            bol = true;
        }
        return bol;
    }


}
