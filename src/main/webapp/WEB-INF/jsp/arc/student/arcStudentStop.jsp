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
        <a href="###">学生管理</a><i>>></i><a href="###">休/退学管理</a>
    </div>
    <!--系统地图 end-->

    <!--数据显示区域 star-->
    <div class="UI_data">
        <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>休/退学管理</span></div>
        <form id="queryForm">
            <div class="data_box clear">
                <div class="data_input">
                    <span>真实姓名：</span>
                    <input type="text" name="realName"/>
                </div>
                <div class="data_input">
                    <span>学号：</span>
                    <input type="text" name="stuNo"/>
                </div>
                <div class="data_input classSel">
                </div>
                <!--下拉框 star-->
                <div class="data_input statusSel">
                </div>
                <!--下拉框 end-->
                <!--时间选择 star-->
                <div class="data_input">
                    <span>入学时间：</span>
                    <input name="enrolDateYearStart" type="text" style="width:100px;"
                           onclick="laydate({istime: true, format: 'YYYY'})"/>
                    <span>-</span>
                    <input name="enrolDateYearEnd" type="text" style="width:100px;"
                           onclick="laydate({istime: true, format: 'YYYY'})"/>
                </div>
                <!--时间选择 end-->
                <div class="data_seach" id="queryBtn" style="cursor:pointer">搜索</div>
                <div class="data_seach" id="expBtn" style="cursor:pointer;background: cornflowerblue;margin-left: 5px;">导出筛选信息</div>
            </div>
        </form>
        <div class="data_box clear">
            <%--<div class="data_seach" id="fileDownLoad" style="cursor:pointer;background: forestgreen">模板下载</div>--%>
            <%--<div class="data_seach" id="importBtn" style="cursor:pointer;margin-left: 5px">导入</div>--%>

        </div>
        <div class="UI_table data_box dataTable">
        </div>
        <!--数据显示区域 end-->
    </div>
    <!--table按钮-->
    <div class="operBar">
        <div class="oper_btn" id="suspentInfoBtn" style="cursor:pointer">休学信息修改</div>
        <div class="oper_btn" id="dropInfoBtn"  style="cursor:pointer">退学信息修改</div>
    </div>
    <!--退学-->
    <form id="dropShowForm" >
        <div id="dropShowBox" class="message_bg" style="display: none">
            <div class="message_box">
                <div class="message_title">退学时间<i class="cancelBtn"></i></div>
                <div class="message_text">
                    <input type="hidden" id="dropShowFormStuId" class="stuFormId" name="stuId" />
                    <div class="data_input"><span>退学时间：</span>
                        <input type="text" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" name="starTime" />
                    </div>
                    <div style="clear:both;"></div>
                </div>
                <div class="message_submit">
                    <a id="dropInfoSubBtn" class="left">确定</a>
                    <a class="right cancelBtn">取消</a>
                </div>
            </div>
        </div>
    </form>
    <!--休学-->
    <form id="suspentShowForm" >
        <div id="suspentShowBox" class="message_bg" style="display: none">
            <div class="message_box">
                <div class="message_title">退学时间<i class="cancelBtn"></i></div>
                <div class="message_text">
                    <input type="hidden" id="suspentShowFormStuId" class="stuFormId" name="stuId" />
                    <div class="data_input">
                        <span>开始时间：</span>
                        <input type="text" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" name="starTime" />
                    </div>
                    <div class="data_input">
                        <span>结束时间：</span>
                        <input type="text" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" name="endTime" />
                    </div>
                    <div style="clear:both;"></div>
                </div>
                <div class="message_submit">
                    <a id="suspentInfoSubBtn" class="left">确定</a>
                    <a class="right cancelBtn">取消</a>
                </div>
            </div>
        </div>
    </form>
    <!--内容数据end-->
    <script type="text/javascript" src="${contextpath}/js/modules/arc/student/arcStudentStop.js?v=20180430"></script>
    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
