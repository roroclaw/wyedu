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
        <a href="###">考试管理</a><i>>></i><a href="###">考场管理</a><i>>></i><a href="###">考场信息修改</a>
    </div>
    <!--系统地图 end-->

    <!--数据显示区域 star-->
    <form id="dataForm" action="">
        <input type="hidden" name="id" id="id"  value="${exaExamRoomInfo.id}" />
        <input type="hidden" id="examPlanId"  value="${exaExamRoomInfo.examPlanId}" />
        <div class="UI_data UIfrom">
                <div class="data_input"><span>考场名称：</span><input name="name" type="text"  class="validate[required]" value="${exaExamRoomInfo.name}"/><i>*</i></div>
                <div class="clear"></div>
                <div class="data_input"><span>使用教室：</span><input type="text"  class="validate[required]" value="${exaExamRoomInfo.classroomText}" readonly/><i>*</i></div>
                <div class="clear"></div>
                <div class="data_seach" id="addBtn"><a>提交</a></div>
                <div class="data_back" id="closeBtn"><a>关闭</a></div>
                <div class="clear"></div>
        </div>
    </form>



</div>
<script type="text/javascript" src="${contextpath}/js/modules/exa/examRoom/examRoomModForm.js"></script>
<jsp:include page="../../common/basic.jsp"/>
</body>
</html>
