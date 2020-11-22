<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>武汉音乐学院</title>
    <jsp:include page="../../common/common_top.jsp"/>
</head>

<body>
<div id="noPrint">
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
            <a href="###">学生管理</a><i>>></i><a href="###">学生考勤管理</a>
        </div>
    <!--数据显示区域 star-->
    <div class="UI_data ">
        <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>学生考勤管理</span></div>
        <form id="queryForm">
            <div class="data_box clear">
                <div  class="data_input attendanceCheckItemsSel">
                </div>
                <div  class="data_input termSel">
                </div>
                <div  class="data_input schoolYearSel">
                </div>
                <div class="data_input classSel">
                    <span>班级：</span>
                    <div class="select_box">
                        <select class="validate[required]"><option value="">--请选择--</option></select>
                    </div>
                </div>
                <div class="data_seach" id="queryBtn" style="cursor:pointer">开始</div>
            </div>
        </form>
        <div class="UI_table data_box dataTable">
        </div>
    </div>

    <form id="printForm" method="post"><input name="printHtml" id="printHtml" type="hidden"/></form>
</div>
    <script type="text/javascript" src="${contextpath}/js/modules/tch/stuAttendanceCheckTotal/stuAttendanceCheck.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
</div>
<!--startprint1-->
<div  id="content_div_print" class="UI_bd TchPlanCourseOpenCheckList">
</div>
<!--endprint1-->
</body>
</html>
