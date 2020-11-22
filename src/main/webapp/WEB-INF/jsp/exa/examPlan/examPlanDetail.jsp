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
        <a href="###">考试管理</a><i>>></i><a href="###">考试计划管理</a><i>>></i><a href="###">考试计划详情表</a>
    </div>
    <!--系统地图 end-->

    <!--数据显示区域 star-->
        <div class="UI_data ">
            <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>${exaExamPlanInfo.name}-详情表</span></div>
            <input type="hidden" name="id" id="examPlanId" value="${exaExamPlanInfo.id}" />
            <form id="dataForm" action="">
            <div  id="content_div" class="UI_bd">

            </div>
            </form>
            <div class="clear"></div>
            <div class="UI_bd clear">
                <div class="data_seach" id="addBtn">提交</div>
                <div class="data_back" id="closeBtn">关闭</div>
            </div>
            <div class="clear"></div>
        </div>




    </div>
<!--导出excel相关 start-->
<script type="text/javascript" src="${contextpath}/js/common/tableToExcel.js"></script>
<!--导出excel相关 end-->

<script type="text/javascript" src="${contextpath}/js/modules/exa/examPlan/examPlanDetail.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
