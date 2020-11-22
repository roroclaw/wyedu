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
        <a href="###">学生管理</a><i>>></i><a href="###">学生信息管理</a>
    </div>
    <!--系统地图 end-->

    <!--数据显示区域 star-->
            <div class="UI_data">
                <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>用户管理</span></div>
                    <form id="queryForm">
                        <div class="data_box clear">
                            <div  class="data_input gradeSel">
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
                <!--数据显示区域 end-->
            </div>

    <!--table按钮-->
    <div class="operBar">

        <%--<div class="oper_btn" id="modBtn" style="cursor:pointer">修改</div>--%>
    </div>
    <!--登记分数-->
    <form id="teachingPlanForm" style="display:none">
        <div class="message_title">教学计划</div>
        <div class="message_text">
            <div  class="data_input majorSel">
            </div>
            <div class="data_input teachingPlanSel">
                <span>教学计划：</span>
                <div class="select_box">
                    <select class="validate[required]"><option value="">--请选择--</option></select>
                </div>
            </div>
            <div style="clear:both;"></div>
        </div>
    </form>
    <!--内容数据end-->
    <script type="text/javascript" src="${contextpath}/js/modules/tch/teachingPlan/tchTeachPlanStudent.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
