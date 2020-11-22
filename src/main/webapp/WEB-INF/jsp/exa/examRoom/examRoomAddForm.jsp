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
        <a href="###">考试管理</a><i>>></i><a href="###">考场管理</a><i>>></i><a href="###">新增考场</a>
    </div>
    <!--系统地图 end-->

    <!--数据显示区域 star-->

            <div class="UI_data">
                <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>新增考场</span></div>
                <form id="addForm">
                    <div class="data_box clear">
                        <div class="data_input"><span>考场名称：</span><input name="name" type="text"  class="validate[required]"/><i>*</i></div>
                        <div class="clear"></div>
                        <!--下拉框 star-->
                        <div class="data_input buildingSel">
                        </div>
                        <!--下拉框 end-->
                        <!--下拉框 star-->
                        <div class="data_input classRoomSel">
                            <span>使用教室：</span>
                            <div class="select_box"><select ><option value="">--请选择--</option></select></div>
                        </div>
                        <!--下拉框 end-->
                    </div>
                </form>
                <div class="data_subTitle clear"><i class="iconfont icon-hangbiao"></i><span>相关考试计划</span></div>
                <form id="queryForm">
                    <div class="data_box clear">
                        <!--下拉框 star-->
                        <div class="data_input typeSel">
                        </div>
                        <!--下拉框 end-->
                    </div>
                </form>
                <div class="UI_table data_box dataTable">
                </div>
                <!--数据显示区域 end-->
            </div>


    <!--table按钮-->
    <div class="operBar">
        <div class="oper_btn" id="addBtn" style="cursor:pointer">提交</div>
        <div class="oper_btn_fanhui" id="backBtn">返回</div>
    </div>
    <!--内容数据end-->
    <script type="text/javascript" src="${contextpath}/js/modules/exa/examRoom/examRoomAddForm.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
