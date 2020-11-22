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
        <a href="###">考试管理</a><i>>></i><a href="###">考场管理</a><i>>></i><a href="###">${exaExamRoomInfo.name}考场排座</a>
    </div>
    <!--系统地图 end-->

    <!--数据显示区域 star-->
    <input type="hidden" name="id" id="id"  value="${exaExamRoomInfo.id}" />
    <input type="hidden" name="seatColNum" id="seatColNum"  value="${exaExamRoomInfo.seatColNum}" />
    <input type="hidden" name="seatRowNum" id="seatRowNum"  value="${exaExamRoomInfo.seatRowNum}" />
    <input type="hidden" name="seatRowNum" id="seatAb"  value="${exaExamRoomInfo.seatAb}" />
        <div class="UI_data ">
            <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>考场排座</span></div>
            <div class="data_box clear">
                <div class="data_text"><span>A：</span><input name="seatType" type="radio" value="A" class="validate[required]" checked/></div>
                <div class="data_text"><span>B：</span><input name="seatType" type="radio" value="B" class="validate[required]"/></div>
                <div class="data_text"><span>清除：</span><input name="seatType" type="radio" value="clean" class="validate[required]"/></div>
                <div class="clear"></div>
            </div>
            <form id="dataForm" action="">
            <div  id="content_div" class="UI_bd">

            </div>
            </form>
            <div class="clear"></div>
            <div class="UI_bd clear">
                <div class="data_seach" id="addBtn">提交</div>
                <div class="data_back" id="closeBtn">关闭</div>
            </div>
            <div class="clear"></div>
        </div>




    </div>

<script type="text/javascript" src="${contextpath}/js/modules/exa/examRoom/examRoomSeats.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
