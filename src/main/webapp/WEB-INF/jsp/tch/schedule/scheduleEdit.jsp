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
        <a href="###">教学管理</a><i>>></i><a href="###">课表管理</a><i>>></i><a href="###">课表编排</a>
    </div>
    <!--系统地图 end-->
    <input id="tdSelected" type="hidden" value=""/>
    <!--数据显示区域 star-->

        <div class="UI_data UIfrom">
            <!--下拉框 star-->
            <form id="queryForm" action="">
                <div class="data_input buildingSel">
                </div>
                <div class="data_input">
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;星期：</span>
                    <div class="select_box">
                        <select name="weekSeq"><option value="">--请选择--</option><option value="1">星期一</option><option value="2">星期二</option><option value="3">星期三</option><option value="4">星期四</option>
                            <option value="5">星期五</option><option value="6">星期六</option><option value="7">星期日</option>
                        </select>
                    </div>
                </div>
            </form>
            <!--下拉框 end-->
            <div class="oper_btn_fun_2" id="start"><a>开始</a></div>
            <div class="oper_btn_tijiao" id="addBtn"><a>提交</a></div>
            <div class="oper_btn_fanhui" id="cleanBtn"><a>清除</a></div>
            <div class="oper_btn_fanhui" id="backBtn"><a>返回</a></div>
            <div class="clear"></div>
            <div class="UI_bd">
                <form id="dataForm" action="">
                    <div  id="leftCourseList" class="leftCourseList">
                    </div>

                    <div  id="content_div" class="rightSchedule">
                    </div>
                </form>
                <div class="clear"></div>
            </div>
        </div>
    </form>



    </div>
    <script type="text/javascript" src="${contextpath}/js/modules/tch/schedule/scheduleEdit.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
