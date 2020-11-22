<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <a href="###">成绩管理</a><i>>></i><a href="###">(${openCourse.courseName})缺缓考登记</a>
  </div>
  <!--系统地图 end-->

  <!--数据显示区域 star-->
  <div id="divRowPart_1">
    <div class="col-md-12">
      <div class="UI_data">
        <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>缺缓考登记</span></div>
        <input id="openCourseId" name="openCourseId" type="hidden" value="${openCourse.id}"/>
        <input id="scoreType" name="scoreType" type="hidden" value="${scoreType}"/>
        <form id="queryForm">
          <div class="data_box clear">
            <div class="data_input">
              <span>学号：</span>
              <input type="text" name="stuNumber"/>
            </div>
            <div class="data_seach" id="queryBtn" style="cursor:pointer">搜索</div>
          </div>
        </form>
        <div class="UI_table data_box dataTable">
        </div>
        <!--数据显示区域 end-->
      </div>
      <!--table按钮-->
      <div class="operBar">
        <div class="oper_btn" id="backBtn" style="cursor:pointer">返回</div>
        <%--<div class="oper_btn" id="modBtn" style="cursor:pointer">修改</div>--%>
      </div>
    </div>
  </div>
</div>

<!--登记分数-->
<form id="recordScoreForm" style="display:none">
  <div class="message_title">缺缓考登记</div>
  <div class="message_text">
    <input type="hidden" id="recordScoreFormId" name="id" />
    <div class="data_input statusSel" defValue="1">

    </div>
    <div style="clear:both;"></div>
  </div>
</form>

<!--内容数据end-->
<script type="text/javascript" src="${contextpath}/js/modules/sco/scoStatus/scoStatus.js?v=20180116"></script>
<jsp:include page="../../common/basic.jsp"/>

</body>
</html>
