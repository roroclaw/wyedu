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
        <a href="###">教学管理</a><i>>></i><a href="###">课程信息管理</a><i>>></i><a href="###">课程信息修改</a>
    </div>
    <!--系统地图 end-->

    <!--数据显示区域 star-->
    <form id="dataForm" action="">
        <input type="hidden" name="id" value="${courseInfo.id}" />
        <div class="UI_data UIfrom">
            <div class="data_input">
                <span>&nbsp;课&nbsp;&nbsp;程&nbsp;&nbsp;名&nbsp;&nbsp;称：</span>
                <input type="text" name="name" class="validate[required]" value="${courseInfo.name}"><b>*</b>
            </div>
            <div class="clear"></div>
            <div class="data_input">
                <span>课程英文名称：</span>
                <input type="text" name="nameEn" class="validate[required]" value="${courseInfo.nameEn}"><b>*</b>
            </div>
            <div class="clear"></div>
            <div class="data_input">
                <span>&nbsp;课&nbsp;&nbsp;程&nbsp;&nbsp;编&nbsp;&nbsp;号：</span>
                <input type="text" name="code" class="validate[required]" value="${courseInfo.code}"><b>*</b>
            </div>
            <div class="clear"></div>
            <div class="data_input subjectTypeSel" >
            </div>
            <div class="clear"></div>
            <div class="data_input subjectSel" defValue="${courseInfo.subjectId}">
                <span>&nbsp;所&nbsp;&nbsp;属&nbsp;&nbsp;科&nbsp;&nbsp;目：</span>
                <div class="select_box">
                    <select id="form-validation-field-0" class="validate[required]"><option value="">${courseInfo.subjectName}</option></select>
                </div>
            </div>
            <div class="clear"></div>
            <div class="data_input" id="teacherType_select_add" value="">
                <span>授课教师类型：</span>
            </div>
            <div class="clear"></div>
            <div class="data_input" id="teacher_select_add" value="${courseInfo.teacherId}" param="${courseInfo.teacherName}">
                <span>&nbsp;授&nbsp;&nbsp;课&nbsp;&nbsp;教&nbsp;&nbsp;师：</span>
                  <div class="select_box">
                     <select id="teacherId" name="teacherId" class="validate[required]"></select>
                  </div>
            </div>
            <div class="clear"></div>
            <div class="data_input" id="teachType_select_add" value="${courseInfo.teachType}">
                <span>&nbsp;教&nbsp;&nbsp;学&nbsp;&nbsp;方&nbsp;&nbsp;式：</span>
            </div>
            <div class="clear"></div>
            <div class="data_input" id="classroomType_select_add" value="${courseInfo.classroomType}">
                <span>&nbsp;教&nbsp;&nbsp;室&nbsp;&nbsp;类&nbsp;&nbsp;型：</span>
            </div>
            <div class="clear"></div>
            <div class="data_input">
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;顺&nbsp;&nbsp;序：</span>
                <input   type="text" name="seq" class="validate[required,custom[integer]]" value="${courseInfo.seq}"><b>*</b>
            </div>
            <div class="clear"></div>
            <div class="data_input" id="status_select_add" value="${courseInfo.status}" >
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状&nbsp;&nbsp;态：</span>
            </div>
            <div class="clear"></div>
            <div class="data_input">
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备&nbsp;&nbsp;注：</span>
                <textarea name="remark" class="" >${courseInfo.remark}</textarea>
            </div>
            <div class="clear"></div>
            <div class="data_seach" id="modBtn"><a>提交</a></div>
            <div class="data_back" id="closeBtn"><a>关闭</a></div>
            <div class="clear"></div>
        </div>
    </form>



    </div>
    <script type="text/javascript" src="${contextpath}/js/modules/tch/course/courseModForm.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
