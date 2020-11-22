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
        <a href="###">教学管理</a><i>>></i><a href="###">教学计划管理</a><i>>></i><a href="###">新增教学计划</a>
    </div>
    <!--系统地图 end-->
    <!--数据显示区域 star-->
    <form id="dataForm" action="">
        <div class="UI_data UIfrom">
            <div class="data_input"><span>教学计划名称：</span><input name="name" type="text"  class="validate[required]"/><i>*</i></div>
            <div class="clear"></div>
            <div class="data_input majorSel">
            </div>
            <div class="clear"></div>
            <!--下拉框 star-->
            <div class="data_input" id="enrolYearSel">
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学&nbsp;&nbsp;&nbsp;&nbsp;年：</span>
            </div>
            <div class="clear"></div>
            <!--下拉框 star-->
            <div class="data_input" id="periodSel">
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学&nbsp;&nbsp;&nbsp;&nbsp;段：</span>
            </div>
            <div class="clear"></div>
            <!--下拉框 star-->
            <div class="data_input" id="eduTypeSel">
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学&nbsp;&nbsp;&nbsp;&nbsp;制：</span>
            </div>
            <div class="clear"></div>
            <div class="data_input" id="status_select_add">
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状&nbsp;&nbsp;&nbsp;&nbsp;态：</span>
            </div>
            <div class="clear"></div>
            <div class="data_seach" id="addBtn"><a>提交</a></div>
            <div class="data_back" id="backBtn"><a>返回</a></div>
            <div class="clear"></div>
        </div>
    </form>
</div>
    <script type="text/javascript" src="${contextpath}/js/modules/tch/teachingPlan/teachingPlanAddForm.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
