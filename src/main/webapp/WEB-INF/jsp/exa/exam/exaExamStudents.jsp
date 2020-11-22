<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>武汉音乐学院</title>
    <jsp:include page="../../common/common_top.jsp"/>
</head>

<body>
<!--菜单star-->
<jsp:include page="../../common/leftMenu.jsp"/>
<!--菜单end-->
<!--头部star-->
<jsp:include page="../../common/header.jsp"/>
<!--头部end-->
<!--内容数据star-->
<div class="UI_main">
    <!--系统地图 star-->
    <div class="UI_address">
        <span></span>
        <a href="###">考试管理</a><i>>></i><a href="###">考试管理</a><i>>></i><a href="###">考生编排</a>
    </div>
    <!--系统地图 end-->
    <!--数据显示区域 star-->
            <div class="UI_data">
                <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>已排学生</span></div>
                    <div class="data_box clear">
                        <div class="data_text">
                            <span>所属考试计划：${exaExamInfo.examPlanName}</span>
                        </div>
                        <div class="data_text">
                            <span>科目名称：${exaExamInfo.subjectName}</span>

                        </div>
                        <div class="data_text">
                            <input type="hidden" id="examDate" value="${exaExamInfo.date}" />
                            <input type="hidden" id="examTime_s" value="${exaExamInfo.startTime}" />
                            <input type="hidden" id="examTime_e" value="${exaExamInfo.endTime}" />
                            <span id="examDateSpan"></span><span id="examTimeSpan_s"></span><span id="examTimeSpan_e"></span>
                        </div>
                    </div>
                    <form id="resultQueryForm">
                        <div class="data_box clear">
                            <!--下拉框 star-->
                            <div class="data_input schoolYearSel">
                            </div>
                            <!--下拉框 end-->
                            <!--下拉框 star-->
                            <div class="data_input gradeSel">
                            </div>
                            <!--下拉框 end-->
                            <!--下拉框 star-->
                            <div class="data_input classSel">
                                <span>班级：</span>
                                <div class="select_box"><select><option value="">--请选择--</option></select></div>
                            </div>
                            <!--下拉框 end-->
                            <div class="data_input">
                                <span>学号：</span>
                                <input type="text" name="stuId"/>
                            </div>
                            <div class="data_input">
                                <span>姓名：</span>
                                <input type="text" name="realName"/>
                            </div>
                            <div class="data_seach" id="resultQueryBtn" style="cursor:pointer">搜索</div>
                        </div>
                    </form>
                    <div class="UI_table data_box dataTable" id="examStudentTable">
                    </div>
            </div>
            <div class="UI_data">
                <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>添加学生</span></div>
                    <form id="queryForm">
                        <div class="data_box clear">
                            <div class="data_text">
                                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开课筛选</span>
                            </div>
                            <!--下拉框 star-->
                            <div class="data_input courseSel">
                            </div>
                            <!--下拉框 end-->
                            <!--下拉框 star-->
                            <div class="data_input schoolYearSel_2">
                            </div>
                            <!--下拉框 end-->
                            <!--下拉框 star-->
                            <div class="data_input gradeSel_2">
                            </div>
                            <!--下拉框 end-->
                            <!--下拉框 star-->
                            <div class="data_input classSel_2">
                                <span>班级：</span>
                                <div class="select_box"><select><option value="">--请选择--</option></select></div>
                            </div>
                            <div class="data_seach" id="queryBtn" style="cursor:pointer">搜索</div>
                        </div>
                    </form>
                   <!-- <div class="data_box clear"><div class="div_line clear"></div></div>-->
                   <form id="addForm">
                        <input type="hidden" id="examId" name="examId" value="${exaExamInfo.id}" />
                        <input type="hidden" id="examPlanId" value="${exaExamInfo.examPlanId}" />
                        <input type="hidden" id="subjectId" value="${exaExamInfo.subjectId}" />
                        <!--<div class="data_box clear">
                            <div class="data_text">
                                <span>相关数据设置</span>
                            </div>
                            <!--下拉框 star
                            <div class="data_input buildingSel">
                            </div>-->
                            <!--下拉框 end-->
                            <!--下拉框 star
                            <div class="data_input examroomSel">
                                <span>&nbsp;考&nbsp;&nbsp;场：</span>
                                <div class="select_box"><select class="validate[required]"><option value="">--请选择--</option></select></div>
                            </div>-->
                            <!--下拉框 end
                        </div>-->
                    </form>
                    <div class="UI_table data_box dataTable" id="courseOpenTable">
                    </div>
            </div>
            <!--table按钮-->
            <div class="operBar">
                <div class="oper_btn" id="addBtn" style="cursor:pointer">添加</div>
                <div class="oper_btn_fanhui" id="backBtn">返回</div>
            </div>





</div>
    <script type="text/javascript" src="${contextpath}/js/modules/exa/exam/exaExamStudents.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
