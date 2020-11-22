<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>武汉音乐学院</title>
    <jsp:include page="../../common/common_top.jsp"/>
</head>
<link rel="stylesheet" href="${contextpath}/css/dataTable/buttons.dataTables.min.css">
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
        <a href="###">考试管理</a><i>>></i><a href="###">考场管理</a><i>>></i><a href="###">考场排座</a>
    </div>
    <!--系统地图 end-->
    <!--数据显示区域 star-->
        <div class="UI_data ">
            <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>考场排座</span></div>
            <div  class="UI_bd">
                <input type="hidden" id="excelFileName"  value="" />
                <div class="oper_btn_fanhui" id="previewBtn">打印</div>
                <div class="oper_btn_fun_1" id="excelBtn">导出EXCEL</div>
                <div class="clear"></div>
            </div>
            <!--startprint1-->
            <div  id="content_div" class="UI_bd">
            </div>
            <!--endprint1-->
        </div>




    </div>
<!--导出excel相关 start-->
<script type="text/javascript" src="${contextpath}/js/common/tableToExcel.js"></script>
<!--导出excel相关 end-->
<script type="text/javascript" src="${contextpath}/js/modules/exa/examRoom/examRoomSeatsDetail.js"></script>


    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
