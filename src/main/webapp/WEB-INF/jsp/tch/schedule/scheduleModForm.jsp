<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <input name="id" value="${scheduleInfo.id}" type="hidden"/>
        <div class="UI_data UIfrom">
            <div class="data_input">
                <span>名称：</span>
                <input id="name" name="name" type="text" class="validate[required]" value="${scheduleInfo.name}"/><i>*</i>
            </div>
            <div class="clear"></div>
            <div class="data_input periodSel">
            </div>
            <div class="clear"></div>
            <!--下拉框 star-->
            <div class="data_input gradeSel" defValue="${scheduleInfo.grade}">
            </div>
            <div class="clear"></div>
            <!--下拉框 star-->

            <!--<div class="data_input classSel" defValue="${scheduleInfo.classId}">
                <span>班级：</span>
                <div class="select_box">
                    <select class="validate[required]">
                        <option value="">-------</option>
                    </select>
                </div>
                <i>*</i>
            </div>
            <div class="clear"></div>-->
            <!--下拉框 star-->
            <div class="data_input termSel" defValue="${scheduleInfo.term}">
            </div>
            <div class="clear"></div>
            <!--下拉框 star-->
            <div class="data_input statusSel" defValue="${scheduleInfo.status}">
            </div>
            <div class="clear"></div>
            <div class="data_seach" id="subBtn"><a>提交</a></div>
            <div class="data_back" id="closeBtn"><a>关闭</a></div>
            <div class="clear"></div>
        </div>
    </form>
</div>

<script type="text/javascript" src="${contextpath}/plugins/autoInput/js/jquery.autocomplete.js"></script>
<script type="text/javascript" src="${contextpath}/js/common/jquery.roroclaw.autoInput.js"></script>
<script type="text/javascript" src="${contextpath}/js/modules/tch/schedule/scheduleModForm.js"></script>
<jsp:include page="../../common/basic.jsp"/>
</body>
</html>
