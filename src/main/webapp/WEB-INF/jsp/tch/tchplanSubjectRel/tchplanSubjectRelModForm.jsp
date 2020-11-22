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
        <a href="###">教学管理</a><i>>></i><a href="###">教学计划管理</a><i>>></i><a href="###">教学计划编排</a>
    </div>
    <!--系统地图 end-->
    <!--数据显示区域 star-->


            <div class="UI_data">
                <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>现有科目</span></div>
                    <div class="data_box clear">
                        <div class="data_text">
                            <span>教学计划名称：${teachingPlanInfo.name}</span>
                        </div>
                        <div class="data_text">
                            <span>教学计划学段：${teachingPlanInfo.periodText}</span>

                        </div>
                        <div class="data_text">
                            <span>教学计划制定年份：${teachingPlanInfo.enrolYear}</span>
                        </div>
                    </div>
                    <div class="clear"></div>
                    <form id="resultQueryForm">
                        <div class="data_box clear">
                            <div class="data_input"><span>科目名称：</span><input name="subjectName" type="text"  class=""/></div>
                            <!--下拉框 star-->
                            <div class="data_input periodSel" >
                            </div>
                            <!--下拉框 end-->
                            <div class="data_seach" id="resultQueryBtn" style="cursor:pointer">搜索</div>
                        </div>
                    </form>
                    <div class="UI_table data_box dataTable" id="planTable">
                    </div>
            </div>
            <div class="UI_data">
                <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>添加科目</span></div>
                    <form id="queryForm">
                        <div class="data_box clear">
                            <div class="data_text">
                                <span>科目筛选</span>
                            </div>
                            <!--下拉框 star-->
                            <div class="data_input " id="typeSel">
                                <span>科目类型：</span>
                            </div>
                            <!--下拉框 end-->
                            <div class="data_seach" id="queryBtn" style="cursor:pointer">搜索</div>
                        </div>
                    </form>
                <div class="data_box clear"><div class="div_line clear"></div></div>
                    <form id="addForm">
                        <input name="planId" id="id" type="hidden" value="${teachingPlanInfo.id}"/>
                        <input name="period" id="period" type="hidden" value="${teachingPlanInfo.period}"/>
                        <div class="data_box clear">
                            <div class="data_text">
                                <span>相关数据设置</span>
                            </div>
                            <div class="data_input"><span>周学时：</span><input name="classHour" type="text"  class="validate[required,custom[integer]]"/><i>*</i></div>
                            <!--下拉框 star-->
                            <div class="data_input " id="gradeSel">
                                <span>年级：</span>
                            </div>
                            <!--下拉框 end-->
                            <!--下拉框 star-->
                            <div class="data_input " id="termSel">
                                <span>学期：</span>
                            </div>
                            <!--下拉框 end-->
                            <div class="data_input"><span>排序：</span><input name="seq" type="text"  class="validate[required,custom[integer]]"/><i>*</i></div>
                        </div>
                    </form>
                    <div class="UI_table data_box dataTable" id="subjecTable">
                    </div>
            </div>
            <!--table按钮-->
            <div class="operBar">
                <div class="oper_btn" id="addBtn" style="cursor:pointer">添加</div>
                <div class="oper_btn_fanhui" id="backBtn">返回</div>
            </div>
</div>
    <script type="text/javascript" src="${contextpath}/js/modules/tch/tchplanSubjectRel/tchPlanSubjectRelModForm.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
