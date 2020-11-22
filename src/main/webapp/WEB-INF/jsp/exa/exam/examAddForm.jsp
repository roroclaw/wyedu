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
        <a href="###">考试管理</a><i>>></i><a href="###">考试管理</a><i>>></i><a href="###">新增考试信息</a>
    </div>
    <!--系统地图 end-->
    <!--数据显示区域 star-->
    <form id="dataForm" action="">
        <div class="UI_data UIfrom">

            <!--下拉框 star-->
            <div class="data_input planSel">
            </div>
            <div class="clear"></div>
            <div class="data_input subjectTypeSel">
            </div>
            <div class="clear"></div>
            <div class="data_input subjectSel">
                <span>考试科目：</span>
                <div class="select_box">
                    <select name="subjectId" id="form-validation-field-0" class=""><option value="">--请选择--</option></select>
                </div>
            </div>
            <div class="clear"></div>
            <div class="data_input"><span>考试日期：</span><input type="text" name="date" class="validate[required]"   onclick="laydate({istime: false, format: 'YYYY-MM-DD'})"/><i>*</i></div>
            <div class="clear"></div>
            <div class="data_input"><span>开始时间：</span><input type="text" name="startTime" class="validate[required]"   onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/><i>*</i></div>
            <div class="clear"></div>
            <div class="data_input"><span>结束时间：</span><input type="text" name="endTime" class="validate[required]"   onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/><i>*</i></div>
            <div class="clear"></div>

            <div class="data_seach" id="addBtn"><a>提交</a></div>
            <div class="data_back" id="backBtn"><a>返回</a></div>
            <div class="clear"></div>
        </div>
    </form>
</div>
<script type="text/javascript" src="${contextpath}/js/modules/exa/exam/examAddForm.js"></script>
<jsp:include page="../../common/basic.jsp"/>
</body>
</html>
