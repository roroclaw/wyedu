<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <a href="###">考试管理</a><i>>></i><a href="###">考试管理</a><i>>></i><a href="###">考试信息修改</a>
    </div>
    <!--系统地图 end-->

    <!--数据显示区域 star-->
    <form id="dataForm" action="">
        <input type="hidden" name="id" value="${exaExamInfo.id}" />
        <div class="UI_data UIfrom">
            <!--下拉框 star-->
            <div class="data_input planSel" defValue="${exaExamInfo.examPlanId}">
            </div>
            <div class="clear"></div>
            <div class="data_input subjectTypeSel" >
            </div>
            <div class="data_input subjectSel" defValue="${exaExamInfo.subjectId}">
            </div>
            <div class="clear"></div>
            <div class="data_input" ><span>考试日期：</span><input type="text" name="date" class="validate[required]" onclick="laydate({istime: false, format: 'YYYY-MM-DD'})"  value="<fmt:formatDate value="${exaExamInfo.date}" pattern="yyyy-MM-dd" />" ><i>*</i></div>
            <div class="clear"></div>
            <div class="data_input"><span>开始时间：</span><input type="text" name="startTime" class="validate[required]" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"  value="<fmt:formatDate value="${exaExamInfo.startTime}" pattern="yyyy-MM-dd hh:mm:ss" />"   onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/><i>*</i></div>
            <div class="clear"></div>
            <div class="data_input"><span>结束时间：</span><input type="text" name="endTime" class="validate[required]" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"  value="<fmt:formatDate value="${exaExamInfo.endTime}" pattern="yyyy-MM-dd hh:mm:ss" />"  ><i>*</i></div>
            <div class="clear"></div>
            <div class="data_seach" id="addBtn"><a>提交</a></div>
            <div class="data_back" id="closeBtn"><a>关闭</a></div>
            <div class="clear"></div>
        </div>
    </form>



    </div>
    <script type="text/javascript" src="${contextpath}/js/modules/exa/exam/examModForm.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
