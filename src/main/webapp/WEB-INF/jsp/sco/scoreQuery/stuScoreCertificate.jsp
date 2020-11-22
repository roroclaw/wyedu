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
        <a href="###">成绩管理</a><i>>></i><a href="###">成绩证明</a>
    </div>
    <!--系统地图 end-->
    <!--数据显示区域 star-->
    <div class="UI_data">
                <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>添加学生</span></div>
                    <form id="queryForm">
                        <div class="data_box clear">
                            <div class="data_text">
                                <span>学生筛选</span>
                            </div>
                            <!--下拉框 star
                            <div class="data_input classSel">
                            </div>-->
                            <!--下拉框 end-->
                            <!--下拉框 star-->
                            <div class="data_input majorSel">
                            </div>
                            <!--下拉框 end-->
                            <div class="data_input">
                                <span>学号：</span>
                                <input type="text" name="stuNumber"/>
                            </div>
                            <div class="data_input">
                                <span>姓名：</span>
                                <input type="text" name="realName"/>
                            </div>
                            <div class="data_seach" id="queryBtn" style="cursor:pointer">搜索</div>
                        </div>
                    </form>
                <div class="data_box clear"><div class="div_line clear"></div></div>
                    <form id="addForm">
                        <input name="courseOpenId" id="courseOpenId" type="hidden" value="${courseOpenInfo.id}"/>
                        <div class="data_box clear">
                            <div class="data_text">
                                <span>相关数据设置</span>
                            </div>

                        </div>
                    </form>
                    <div class="UI_table data_box dataTable" id="studentsTable">
                    </div>
    </div>
            <!--table按钮
            <div class="operBar">
                <div class="oper_btn" id="addBtn" style="cursor:pointer">添加</div>
                <div class="oper_btn_fanhui" id="backBtn">返回</div>
            </div>-->






</div>
    <script type="text/javascript" src="${contextpath}/js/modules/sco/scoreQuery/stuScoreCertificate.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
