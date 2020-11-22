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
        <a href="###">考试管理</a><i>>></i><a href="###">考场管理</a>
    </div>
    <!--系统地图 end-->

    <!--数据显示区域 star-->


            <div class="UI_data">
                <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>考场管理</span></div>
                <form id="queryForm">
                    <div class="data_box clear">
                        <!--下拉框 star-->
                        <div class="data_input buildingSel">
                        </div>
                        <div class="data_input schoolYearSel">
                        </div>
                        <div class="data_input examPlanSel">
                            <span>考试计划：</span>
                            <div class="select_box">
                                <select name="examPlanId" id="form-validation-field-0" class=""><option value="">--请选择--</option></select>
                            </div>
                        </div>
                        <!--下拉框 end-->
                        <div class="data_seach" id="queryBtn" style="cursor:pointer">搜索</div>
                    </div>
                </form>
                <div class="UI_table data_box dataTable">
                </div>
                <!--数据显示区域 end-->
            </div>



    <!--table按钮-->
    <div class="operBar">
        <div class="oper_btn" id="addBtn" style="cursor:pointer">新增</div>
        <div class="oper_btn_fun_2" id="editBtn" style="cursor:pointer">排座</div>
        <%-- <div class="oper_btn_fun_1" id="detailBtn" style="cursor:pointer">考场平面图</div>
         <div class="oper_btn" id="modBtn" style="cursor:pointer">修改</div>--%>
    </div>
    <!--内容数据end-->
    <script type="text/javascript" src="${contextpath}/js/modules/exa/examRoom/exaExamRoom.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
