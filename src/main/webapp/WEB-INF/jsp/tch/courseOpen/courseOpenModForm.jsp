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
        <a href="###">教学管理</a><i>>></i><a href="###">开课信息管理</a><i>>></i><a href="###">课程信息修改</a>
    </div>
    <!--系统地图 end-->

    <!--数据显示区域 star-->
    <form id="dataForm" action="">
        <input type="hidden" name="id" value="${courseOpenInfo.id}" />
        <div class="UI_data UIfrom">

                <div class="data_input schoolYearSel" defValue="${courseOpenInfo.schoolYear}">
                </div>
            <div class="clear"></div>
                <!--下拉框 star-->
                <div class="data_input" id="classId_select_add" defValue="${courseOpenInfo.classId}"><span>班级：</span>
                    <div class="select_box">
                        <select id="classSel" name="classId" class="validate[required]">

                        </select>
                    </div><i>*</i>
                </div>
            <div class="clear"></div>
                <!--下拉框 end-->
                <!--下拉框 star-->
                <div class="data_input " id="termSel"  value="${courseOpenInfo.term}">
                    <span>学期：</span>

                </div>
            <div class="clear"></div>
            <div class="data_input buildingSel">
            </div>
            <div class="clear"></div>
            <div class="data_input classroomSel"    defValue="${courseOpenInfo.classroomId}">
            </div>
            <div class="clear"></div>
            <div class="data_input">
                    <span>使用教材：</span>
                    <input type="text" name="teachBook" class="" value="${courseOpenInfo.teachBook}">
            </div>
            <div class="clear"></div>
            <div class="data_input">
                    <span>教辅资料：</span>
                    <input type="text" name="otherBooks" class="" value="${courseOpenInfo.otherBooks}">
            </div>
                <!--下拉框 end-->

            <div class="clear"></div>
            <div class="data_seach" id="modBtn"><a>提交</a></div>
            <div class="data_back" id="closeBtn"><a>关闭</a></div>
            <div class="clear"></div>
        </div>
    </form>



    </div>
    <script type="text/javascript" src="${contextpath}/js/modules/tch/courseOpen/courseOpenModForm.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
