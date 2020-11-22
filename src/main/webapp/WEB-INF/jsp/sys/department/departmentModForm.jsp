<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>武汉音乐学院</title>
    <jsp:include page="../../common/common_top.jsp"/>
    <link type="text/css" rel="stylesheet" href="${contextpath}/css/jquery.treeview.css"/>
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
        <a href="###">系统管理</a><i>>></i><a href="###">部门管理</a>
    </div>
    <input type="hidden" id="departId" value="${sysDepartInfo.pId}" />
    <!--系统地图 end-->
    <form id="dataForm" action="">
        <input type="hidden" name="id" value="${sysDepartInfo.id}" />
        <div class="UI_data UIfrom">
            <div class="data_input"><span>部门名称：</span><input name="name" type="text"  class="validate[required]" value="${sysDepartInfo.name}"/><i>*</i></div>
            <div class="clear"></div>
            <!--下拉框 star-->
            <div class="data_input" id="depart_select_add"><span>上级部门名称：</span></div>
            <div class="clear"></div>
            <!--下拉框 star-->
            <div class="data_input statusSel" defValue="${sysDepartInfo.status}">
            </div>
            <div class="clear"></div>
            <div class="data_seach" id="addBtn"><a>提交</a></div>
            <div class="data_back" id="backBtn"><a>返回</a></div>
            <div class="clear"></div>
        </div>
    </form>
    <!--内容数据end-->

    <script type="text/javascript" src="${contextpath}/js/modules/sys/department/departmentModForm.js"></script>
    <jsp:include page="../../common/basic.jsp"/>




</body>
</html>
