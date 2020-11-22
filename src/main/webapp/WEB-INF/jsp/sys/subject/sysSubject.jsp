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
        <a href="###">教学管理</a><i>>></i><a href="###">科目管理</a>
    </div>
    <!--系统地图 end-->

    <!--数据显示区域 star-->
    <div id="divRowPart_1">
        <div class="col-md-12">
            <div class="UI_data">
                <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>科目管理</span></div>
                <form id="queryForm">
                    <div class="data_box clear">
                        <div class="data_input">
                            <span>科目名称：</span>
                            <input type="text" name="name"/>
                        </div>
                        <!--下拉框 star-->
                        <div class="data_input" id="type_select_add">
                            <span>科目类型：</span>
                        </div>
                        <!--下拉框 end-->
                        <div class="data_seach" id="queryBtn" style="cursor:pointer">搜索</div>
                    </div>
                </form>
                <div class="UI_table data_box dataTable">
                </div>
                <!--数据显示区域 end-->
            </div>
        </div>
    </div>

    <!--table按钮-->
    <div class="operBar">
        <div class="oper_btn" id="addBtn" style="cursor:pointer">新增</div>
        <%--<div class="oper_btn" id="modBtn" style="cursor:pointer">修改</div>--%>
    </div>
    <!--内容数据end-->
    <script type="text/javascript" src="${contextpath}/js/modules/sys/subject/sysSubject.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
