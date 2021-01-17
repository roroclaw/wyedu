<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>开课选择</title>
    <jsp:include page="../common/common_top.jsp"/>
</head>
<body style="background: #eee">
<div class="UI_main" style="position: static">
    <!--系统地图 star-->
    <div class="UI_address">
        <span></span>
        <a href="###">开课信息选择</a>
    </div>
    <!--系统地图 end-->

    <!--数据显示区域 star-->
    <div class="UI_data">
        <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>用户管理</span></div>
        <form id="queryForm">
            <div class="data_box clear">
                <div class="data_input">
                    <span>开课名称</span>
                    <input type="text" name="openCoursename"/>
                </div>

                <!--下拉框 end-->
                <div class="data_input schoolYearSel">
                </div>
                <!--下拉框 star-->
                <div class="data_input termSel">
                </div>

                <!--时间选择 end-->
                <div class="data_seach" id="queryBtn" style="cursor:pointer">搜索</div>
                <div class="data_seach" id="submitBtn" style="margin-left: 10px;"><a>提交</a></div>
                <div class="data_seach" id="cleanBtn" style="margin-left: 10px;"><a>清除范围</a></div>
            </div>
        </form>
        <div class="UI_table data_box dataTable">
        </div>
        <!--数据显示区域 end-->
    </div>

    <!--内容数据end-->


    <script type="text/javascript" src="${contextpath}/js/modules/dialog/openCourseSel.js?v=20200117"></script>

</body>
</html>
