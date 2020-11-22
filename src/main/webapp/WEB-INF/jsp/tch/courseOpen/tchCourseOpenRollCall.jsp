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
            <a href="###">教学管理</a><i>>></i><a href="###">开课查询</a><i>>></i><a href="###">点名册</a>
        </div>
    <!--数据显示区域 star-->
    <div class="UI_data ">
        <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>点名册</span></div>
        <div  class="UI_bd">
            <div class="oper_btn_fun_1" id="printBtn">打印</div>
            <div class="clear"></div>
        </div>
        <div  id="content_div" class="UI_bd TchPlanCourseOpenCheckList">
        </div>

    </div>

    <div class="data_back" id="closeBtn"><a>关闭</a></div>
    <div class="clear"></div>

</div>
    <script type="text/javascript" src="${contextpath}/js/modules/tch/courseOpen/tchCourseOpenRollCall.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
</div>
<!--startprint1-->
<div  id="content_div_print" class="UI_bd TchPlanCourseOpenCheckList">
</div>
<!--endprint1-->
</body>
</html>
