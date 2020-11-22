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
        <a href="###">信息管理</a><i>>></i><a href="###">公告查询</a>
    </div>
    <!--系统地图 end-->
    <div class="form_operbar clear">
        <div class="oper_btn_tijiao" id="allNotice" style="cursor:pointer">通知公告</div>
        <div class="oper_btn_fanhui" id="classNotice" style="cursor:pointer">本班公告</div>
    </div>
    <!--数据显示区域 star-->
    <div id="divRowPart_1">
        <div class="col-md-12">
            <div class="UI_data">
                <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span id="top_title"></span></div>
                <div class="UI_table data_box dataTable">
                </div>
                <!--数据显示区域 end-->
            </div>
        </div>
    </div>

    <!--table按钮-->

    <!--内容数据end-->
    <script type="text/javascript" src="${contextpath}/js/modules/cms/noticeQueryST.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
