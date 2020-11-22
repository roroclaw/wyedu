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
        <div class="UI_data UIfrom">
            <div class="data_input">
                <span>班级名称：</span>
                <input id="name" name="name" type="text" value="${classInfo.name}" placeholder="会根据年级以及班次生成"  class="validate[required]"/><i>*</i>
            </div>
            <div class="clear"></div>
            <div class="data_input schoolYearSel" defValue="<fmt:formatDate value="${classInfo.enrolYear}" pattern="yyyy" />">
            </div>
            <div class="clear"></div>
            <div class="data_input graduateYearSel" defValue="${classInfo.graduateYear}">
            </div>
            <div class="clear"></div>
            <div id="gradeSel" class="data_input" defValue="${classInfo.grade}">
            </div>
            <div class="clear"></div>
            <div class="data_input">
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;班次：</span>
                <div class="select_box">
                    <select id="seqSel" name="seq" class="validate[required]">
                        <option value="">--请选择--</option>
                        <c:forEach varStatus="status" begin="1" end="10" >
                            <c:choose>
                                <c:when test="${status.index == classInfo.seq}">
                                    <option value="${status.index}" selected>${status.index}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${status.index}" >${status.index}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
                <i>*</i>
            </div>
            <div class="clear"></div>
            <div class="data_input">
                <span>&nbsp;&nbsp;&nbsp;班主任：</span>
                <input id="teacherSel" value="${classInfo.teacherText}" data-code="${classInfo.teacherId}"   placeholder="输入关键字查询教师" type="text"  class="validate[required]"/><i>*</i>
            </div>
            <div class="clear"></div>
            <div class="data_input roomSel" defValue="${classInfo.classroomId}" >
            </div>
            <div class="clear"></div>
            <div class="data_input classInfoStatusSel" defValue="${classInfo.status}">
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
<script type="text/javascript" src="${contextpath}/js/modules/tch/class/classModForm.js"></script>
<jsp:include page="../../common/basic.jsp"/>
</body>
</html>
