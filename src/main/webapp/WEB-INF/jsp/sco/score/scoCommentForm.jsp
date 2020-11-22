<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <a href="###">成绩管理</a><i>>></i><a href="###">期末总评管理</a><i>>></i><a href="###">期末总评</a>
    </div>
    <!--系统地图 end-->
    <!--数据显示区域 star-->
    <form id="dataForm" action="">
        <div class="UI_data UIfrom">
            <input name="stuId" type="hidden" value="${stuId}"/>

            <div class="data_input"><span>学号：</span>
                <input id="stuNo"  name="stuNo" type="text"  readonly="true"  value="${arcStudent.stuNumber}"/>
            </div>

            <div class="clear"></div>
            <div class="data_input"><span>姓名：</span>
                <input id="stuName"  name="stuName" type="text"  readonly="true" value="${arcStudent.realName}"/>
            </div>

            <div class="clear"></div>
            <div class="data_input select_box">
                <span>学年：</span>
                <select id="schoolYearSel" name="schoolYear" disabled class="select_box validate[required]" dfval="${schoolYear}">

                </select>
            </div>

            <div class="clear"></div>
            <div class="data_input">
                <span>学期：</span>
                <select id="termSel" name="term" disabled class=" select_box validate[required]"  dfval="${term}">

                </select>
            </div>

            <div class="clear"></div>
            <div class="data_input"><span>班级：</span>
                <input id="className"  name="className" type="text"  readonly="true" value="${arcStudent.classText}"/>
            </div>

            <div class="clear"></div>
            <div class="data_input"><span>评价：</span>
                <textarea id="comment" style="height:100px"  name="comment" class="validate[required]">
                    ${scoComment.comment}
                </textarea>
            </div>

            <div class="clear"></div>

            <div class="data_seach" id="subBtn"><a>提交并发布</a></div>
            <div class="data_back" id="backBtn"><a>返回</a></div>
            <div class="clear"></div>
        </div>
    </form>
</div>
<script type="text/javascript" src="${contextpath}/js/modules/sco/scores/scoCommentForm.js?v=20180701"></script>
<jsp:include page="../../common/basic.jsp"/>
</body>
</html>
