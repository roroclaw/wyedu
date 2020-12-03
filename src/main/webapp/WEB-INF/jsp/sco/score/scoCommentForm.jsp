<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <a href="###">成绩管理</a><i>>></i><a href="###">期末总评管理</a><i>>></i><a href="###">期末总评</a>
    </div>
    <!--系统地图 end-->
    <!--数据显示区域 star-->
    <form id="dataForm" action="">
        <div class="UI_data UIfrom">
            <input name="stuId" type="hidden" value="${stuId}"/>
            <input name="commentMarksList" id="commentMarksList" type="hidden" value="${commentMarksList}"/>
            <div class="data_input"><span>学号：</span>
                <input id="stuNo"  name="stuNo" type="text"  readonly="true"  value="${arcStudent.stuNumber}"/>
            </div>
            <div class="data_input"><span>姓名：</span>
                <input id="stuName"  name="stuName" type="text"  readonly="true" value="${arcStudent.realName}"/>
            </div>
            <div class="data_input select_box">
                <span>学年：</span>
                <select id="schoolYearSel" name="schoolYear" disabled class="select_box validate[required]" dfval="${schoolYear}">

                </select>
            </div>
            <div class="data_input">
                <span>学期：</span>
                <select id="termSel" name="term" disabled class=" select_box validate[required]"  dfval="${term}">

                </select>
            </div>
            <div class="data_input"><span>班级：</span>
                <input id="className"  name="className" type="text"  readonly="true" value="${arcStudent.classText}"/>
            </div>
            <div class="clear"></div>
            <div class="data_title clear" style="height: 3px;"></div>
            <div class="clear"></div>
            <div class="data_input select_box">
                <span id="cmItem_1"></span>
                <select id="cmItemSel_1" name="01"  class="select_box validate[required]" >

                </select>
            </div>
            <div class="clear"></div>
            <div class="data_input select_box">
                <span id="cmItem_2"></span>
                <select id="cmItemSel_2" name="02"  class="select_box validate[required]" >

                </select>
            </div>
            <div class="clear"></div>
            <div class="data_input select_box">
                <span id="cmItem_3"></span>
                <select id="cmItemSel_3" name="03"  class="select_box validate[required]" >

                </select>
            </div>
            <div class="clear"></div>
            <div class="data_input select_box">
                <span id="cmItem_4"></span>
                <select id="cmItemSel_4" name="04"  class="select_box validate[required]">

                </select>
            </div>
            <div class="clear"></div>
            <div class="data_input select_box">
                <span id="cmItem_5"></span>
                <select id="cmItemSel_5" name="05"  class="select_box validate[required]" >

                </select>
            </div>
            <div class="clear"></div>
            <div class="data_input select_box">
                <span id="cmItem_6"></span>
                <select id="cmItemSel_6" name="06"  class="select_box validate[required]">

                </select>
            </div>
            <div class="clear"></div>
            <div class="data_input select_box">
                <span id="cmItem_7"></span>
                <select id="cmItemSel_7" name="07"  class="select_box validate[required]">

                </select>
            </div>
            <div class="clear"></div>
            <div class="data_input select_box">
                <span id="cmItem_8"></span>
                <select id="cmItemSel_8" name="08"  class="select_box validate[required]">

                </select>
            </div>
            <div class="clear"></div>
            <div class="data_input select_box">
                <span id="cmItem_9"></span>
                <select id="cmItemSel_9" name="09"  class="select_box validate[required]">

                </select>
            </div>
            <div class="clear"></div>
            <div class="data_input select_box">
                <span id="cmItem_10"></span>
                <select id="cmItemSel_10" name="10"  class="select_box validate[required]" >

                </select>
            </div>
            <div class="clear"></div>
            <div class="data_input select_box">
                <span id="cmItem_11"></span>
                <select id="cmItemSel_11" name="11"  class="select_box validate[required]" >

                </select>
            </div>
            <div class="clear"></div>
            <div class="data_input select_box">
                <span id="cmItem_12"></span>
                <select id="cmItemSel_12" name="12"  class="select_box validate[required]">

                </select>
            </div>
            <div class="clear"></div>
            <div class="data_input"><span>评价：</span>
                <textarea id="comment" style="height:100px;width:300px;"  name="comment" class="validate[required]">
                    ${comment}
                </textarea>
            </div>

            <div class="clear"></div>

            <div class="data_seach" id="subBtn"><a>提交并发布</a></div>
            <div class="data_back" id="backBtn"><a>返回</a></div>
            <div class="clear"></div>
        </div>
    </form>
</div>
<script type="text/javascript" src="${contextpath}/js/modules/sco/scores/scoCommentForm.js?v=20180701"></script>
<jsp:include page="../../common/basic.jsp"/>
</body>
</html>
