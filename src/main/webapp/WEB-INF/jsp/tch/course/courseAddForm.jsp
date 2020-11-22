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
        <a href="###">教学管理</a><i>>></i><a href="###">添加课程</a>
    </div>
    <!--系统地图 end-->
    <!--数据显示区域 star-->
    <form id="dataForm" action="">
        <div class="UI_data UIfrom">
            <div class="data_input">
                <span>&nbsp;课&nbsp;&nbsp;程&nbsp;&nbsp;名&nbsp;&nbsp;称：</span>
                <input type="text" name="name" class="validate[required]"><b>*</b>
            </div>
            <div class="clear"></div>
            <div class="data_input">
                <span>课程英文名称：</span>
                <input type="text" name="nameEn" class="validate[required]"><b>*</b>
            </div>
            <div class="clear"></div>
            <div class="data_input">
                <span>&nbsp;课&nbsp;&nbsp;程&nbsp;&nbsp;编&nbsp;&nbsp;号：</span>
                <input type="text" name="code" class="validate[required]"><b>*</b>
            </div>
            <div class="clear"></div>
            <div class="data_input subjectTypeSel">
            </div>
            <div class="clear"></div>
            <div class="data_input subjectSel">
                <span>&nbsp;所&nbsp;&nbsp;属&nbsp;&nbsp;科&nbsp;&nbsp;目：</span>
                <div class="select_box">
                  <select name="subjectId" id="form-validation-field-0" class="validate[required]"><option value="">--请选择--</option></select>
                </div>
            </div>
            <div class="clear"></div>
            <div class="data_input" id="teacherType_select_add">
                <span>授课教师类型：</span>
            </div>
            <div class="clear"></div>
            <div class="data_input" id="teacher_select_add">
                <span>&nbsp;授&nbsp;&nbsp;课&nbsp;&nbsp;教&nbsp;&nbsp;师：</span>
                <div class="select_box">
                    <select id="teacherId" name="teacherId" class="validate[required]"></select>
                </div><i>*</i>
            </div>
            <div class="clear"></div>
            <div class="data_input" id="teachType_select_add">
                <span>&nbsp;教&nbsp;&nbsp;学&nbsp;&nbsp;方&nbsp;&nbsp;式：</span>
            </div>
            <div class="clear"></div>
            <div class="data_input" id="classroomType_select_add">
                <span>&nbsp;教&nbsp;&nbsp;室&nbsp;&nbsp;类&nbsp;&nbsp;型：</span>
            </div>
            <div class="clear"></div>
            <div class="data_input">
               <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;顺&nbsp;&nbsp;序：</span>
                <input   type="text" name="seq" class="validate[required,custom[integer]]"><b>*</b>
            </div>
            <div class="clear"></div>
            <div class="data_input" id="status_select_add" >
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状&nbsp;&nbsp;态：</span>
            </div>
            <div class="clear"></div>
            <div class="data_input">
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备&nbsp;&nbsp;注：</span>
                <textarea name="remark" class=""></textarea>
            </div>
            <div class="clear"></div>
            <div class="data_seach" id="addBtn"><a>提交</a></div>
            <div class="data_back" id="backBtn"><a>返回</a></div>
            <div class="clear"></div>
        </div>
    </form>
    <!--table按钮-->

    <!--内容数据end-->
    <script type="text/javascript" src="${contextpath}/js/modules/tch/course/courseAdd.js"></script>
    <jsp:include page="../../common/basic.jsp"/>




</body>
</html>
