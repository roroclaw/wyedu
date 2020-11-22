<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>武汉音乐学院</title>
    <jsp:include page="../../common/common_top.jsp"/>
    <link type="text/css" rel="stylesheet" href="${contextpath}/plugins/autoInput/css/styles.css"/>
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
        <a href="###">教学管理</a><i>>></i><a href="###">班级管理</a><i>>></i><a href="###">班级新增</a>
    </div>
    <!--系统地图 end-->
    <!--数据显示区域 star-->
    <form id="dataForm" action="">
        <div class="UI_data UIfrom">
            <div class="data_input">
                <span>班级名称：</span>
                <input id="name" name="name" type="text" placeholder="会根据年级以及班次生成"  class="validate[required]"/><i>*</i>
            </div>
            <div class="clear"></div>
            <div class="data_input schoolYearSel" >
            </div>
            <div class="clear"></div>
            <div class="data_input graduateYearSel" >
            </div>
            <div class="clear"></div>
            <div id="gradeSel" class="data_input">
            </div>
            <div class="clear"></div>
            <div class="data_input">
                    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;班次：</span>
                    <div class="select_box">
                        <select id="seqSel" name="seq" class="validate[required]">
                            <option value="">--请选择--</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                        </select>
                    </div>
                    <i>*</i>
             </div>
            <div class="clear"></div>
             <div class="data_input">
                    <span>&nbsp;&nbsp;&nbsp;班主任：</span>
                    <input id="teacherSel"  placeholder="输入关键字查询教师" type="text"  class="validate[required]"/><i>*</i>
             </div>
            <div class="clear"></div>
            <div class="data_input roomSel">
            </div>
        <div class="clear"></div>
        <div class="data_seach" id="addBtn"><a>提交</a></div>
        <div class="data_back" id="backBtn"><a>返回</a></div>
        <div class="clear"></div>
        </div>
    </form>
</div>

<script type="text/javascript" src="${contextpath}/plugins/autoInput/js/jquery.autocomplete.js"></script>
<script type="text/javascript" src="${contextpath}/js/common/jquery.roroclaw.autoInput.js"></script>
<script type="text/javascript" src="${contextpath}/js/modules/tch/class/classAddForm.js"></script>
<jsp:include page="../../common/basic.jsp"/>
</body>
</html>
