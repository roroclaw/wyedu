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
        <a href="###">考试管理</a><i>>></i><a href="###">考试管理</a>
    </div>
    <!--系统地图 end-->

    <!--数据显示区域 star-->
    <div id="divRowPart_1">
        <div class="col-md-12">
            <div class="UI_data">
                <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>考试管理</span></div>
                <form id="queryForm">
                    <div class="data_box clear">
                        <!--下拉框 star-->
                        <div class="data_input planSel">
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
        <div class="oper_btn_fun_2" id="editBtn" style="cursor:pointer">考生编排</div>
        <div class="oper_btn_fun_2" id="examroomEditBtn" style="cursor:pointer">考场安排</div>
        <div class="oper_btn_fun_1" id="examroomSeatTipBtn" style="cursor:pointer">考场座位帖</div>
        <%--<div class="oper_btn" id="modBtn" style="cursor:pointer">修改</div>--%>
    </div>
    <!--修考试状态弹出-->
    <form id="modExamStatusForm" style="display:none">
        <div class="message_title">考试状态修改</div>
        <div class="message_text">
            <input type="hidden" name="id" id="id" value="" />
            <!--下拉框 star-->
            <div class="data_input statusSel" defValue="">
            </div>
            <div style="clear:both;"></div>
        </div>
    </form>

    <!--内容数据end-->
    <script type="text/javascript" src="${contextpath}/js/modules/exa/exam/exaExam.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
