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
            <a href="###">教学管理</a><i>>></i><a href="###">教学计划管理</a><i>>></i><a href="###">教学计划详情</a>
        </div>
        <input type="hidden" id="planId" value="${teachingPlanInfo.id}" />
        <input type="hidden" id="period" value="${teachingPlanInfo.period}" />
        <input type="hidden" id="name" value="${teachingPlanInfo.name}" />
        <!--系统地图 end-->

        <!--数据显示区域 star-->
        <div id="table_content" class="UI_data UI_bd"></div>

    <div class="data_back" id="closeBtn"><a>关闭</a></div>
    <div class="clear"></div>

</div>
    <script type="text/javascript" src="${contextpath}/js/modules/tch/teachingPlan/tchTeachingPlanDetail.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
