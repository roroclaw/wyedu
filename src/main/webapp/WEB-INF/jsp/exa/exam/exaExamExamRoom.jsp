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
        <a href="###">考试管理</a><i>>></i><a href="###">考试管理</a><i>>></i><a href="###">考场安排</a>
    </div>
    <!--系统地图 end-->
    <!--数据显示区域 star-->
            <div class="UI_data">
                <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>已排学生</span></div>
                    <div class="data_box clear">
                        <div class="data_text">
                            <span>所属考试计划：${exaExamInfo.examPlanName}</span>
                        </div>
                        <div class="data_text">
                            <span>科目名称：${exaExamInfo.subjectName}</span>
                        </div>
                        <div class="data_text">
                            <input type="hidden" id="examDate" value="${exaExamInfo.date}" />
                            <span id="examDateSpan"></span>
                        </div>
                        <div class="clear"></div>
                        <div>
                            <div class="col-md-2">
                                <div class="UI_bd" >
                                    <div  class="UI_table" id="eerABInfo">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-10">
                                <div class="UI_bd" >
                                        <form id="dataForm" action="">
                                            <input type="hidden" id="examId" name="examId" value="${exaExamInfo.id}" />
                                            <input type="hidden" id="examPlanId" value="${exaExamInfo.examPlanId}" />
                                            <input type="hidden" id="subjectId" value="${exaExamInfo.subjectId}" />
                                            <input type="hidden" id="colNum" name="colNum" value="" />
                                            <input type="hidden" id="rowNum" name="rowNum" value="" />
                                           <div class="UI_table " id="examExramRoomTable"></div>
                                        </form>
                                </div>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <div class="data_row clear">
                            <div class="data_seach" id="addBtn">提交</div>
                            <div class="data_back" id="backBtn">返回</div>
                        </div>
                        <div class="data_row clear"></div>
                    </div>
            </div>






</div>
    <script type="text/javascript" src="${contextpath}/js/modules/exa/exam/exaExamExamRoom.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
