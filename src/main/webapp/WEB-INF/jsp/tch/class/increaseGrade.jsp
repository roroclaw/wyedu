<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>武汉音乐学院</title>
    <jsp:include page="../../common/common_top.jsp"/>
    <link type="text/css" rel="stylesheet" href="${contextpath}/plugins/autoInput/css/styles.css"/>
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
        <a href="###">教学管理</a><i>>></i><a href="###">班级管理</a><i>>></i><a href="###">班级修改</a>
    </div>
    <!--系统地图 end-->
    <!--数据显示区域 star-->
    <form id="dataForm" action="">
        <input name="id" value="${classInfo.id}" type="hidden"/>
        <input name="gradu" id="gradu" value="" type="hidden"/>
        <input id="seq" value="${classInfo.seq}" type="hidden"/>
        <div class="UI_data UIfrom">
            <div id="gradeSel" class="data_input hidden_div" param="${classInfo.grade}" defValue="">
            </div>
            <div class="clear"></div>
            <div id="statusSel" class="data_input classInfoStatusSel hidden_div" param="${classInfo.status}" defValue="2">
            </div>
            <div class="clear"></div>
            <div class="data_input">
                <span>&nbsp;&nbsp;&nbsp;毕业年份：</span>
                <input value="${classInfo.graduateYear}" type="text" readonly/>
            </div>
            <div class="clear"></div>
            <div class="data_seach" id="subBtn"><a>提交</a></div>
            <div class="data_back" id="backBtn"><a>返回</a></div>
            <div class="clear"></div>
        </div>
    </form>
</div>

<script type="text/javascript" src="${contextpath}/plugins/autoInput/js/jquery.autocomplete.js"></script>
<script type="text/javascript" src="${contextpath}/js/common/jquery.roroclaw.autoInput.js"></script>
<script type="text/javascript" src="${contextpath}/js/modules/tch/class/increaseGrade.js"></script>
<jsp:include page="../../common/basic.jsp"/>
</body>
</html>
