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
        <a href="###">教学管理</a><i>>></i><a href="###">开课</a>
    </div>
    <!--系统地图 end-->
    <!--数据显示区域 star-->
    <div class="UI_data">
        <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>开课</span></div>
        <form id="queryForm">
            <div class="data_box clear">
                <div class="data_text">
                    <span>课程筛选</span>
                </div>
                <!--下拉框 star-->
                <div class="data_input subjectTypeSel">

                </div>
                <div class="data_input subjectSel">
                    <span>所属科目：</span>
                    <div class="select_box">
                        <select id="subjectId" name="subjectId" class="validate[required]"></select>
                    </div>
                </div>
                <!--下拉框 end-->
                <div class="data_seach" id="queryBtn" style="cursor:pointer">搜索</div>
            </div>
        </form>
        <div class="data_box clear"><div class="div_line clear"></div></div>
        <form id="addForm">
            <input name="planId" id="id" type="hidden" value="${teachingPlanInfo.id}"/>
            <input name="period" id="period" type="hidden" value="${teachingPlanInfo.period}"/>
            <div class="data_box clear">
                <div class="data_text">
                    <span>相关数据设置</span>
                </div>
                <div class="data_text">
                    <div class="data_input"><span>学年：</span>
                        <div class="select_box">
                            <select id="schoolYearSel" name="schoolYear" class="validate[required]">

                            </select>
                        </div><i>*</i>
                    </div>
                    <!--下拉框 star-->
                    <div class="data_input " id="termSel">
                        <span>学期：</span>

                    </div>
                    <!--下拉框 star-->
                    <div class="data_input"><span>班级：</span>
                        <div class="select_box">
                            <select id="classSel" name="classId" class="validate[required]">

                            </select>
                        </div><i>*</i>
                    </div>
                    <!--下拉框 end-->
                    <div class="data_input buildingSel">
                    </div>
                    <div class="data_input classroomSel">
                    </div>
                    <div class="data_input">
                        <span>使用教材：</span>
                        <input type="text" name="teachBook" class="">
                    </div>
                    <div class="data_input">
                        <span>教辅资料：</span>
                        <input type="text" name="otherBooks" class="">
                    </div>
                    <div class="data_input">
                    <div class="oper_btn" id="addBtn" style="cursor:pointer">添加</div>
                    <div class="oper_btn_fanhui" id="backBtn">返回</div>
                    </div>
                    <!--下拉框 end-->
                </div>
            </div>
        </form>
        <div class="UI_table data_box dataTable" id="subjecTable">
        </div>
    </div>
    <!--table按钮-->
    <div class="operBar">

    </div>
    <!--内容数据end-->
    <script type="text/javascript" src="${contextpath}/js/modules/tch/courseOpen/courseOpenAdd.js"></script>
    <jsp:include page="../../common/basic.jsp"/>




</body>
</html>
