<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
    <a href="###">系统管理</a><i>>></i><a href="###">(${scoresRuleConfig.subjectName})成绩规则配置</a>
  </div>
  <!--系统地图 end-->

  <!--数据显示区域 star-->
  <form id="dataForm" >
    <div class="UI_data UIfrom">
      <div class="data_input schoolYearSel">
      </div>
      <div class="clear"></div>
      <div class="data_input termSel">
      </div>
      <div class="clear"></div>
      <div class="data_input scoreTypeSel">
      </div>
      <div class="clear"></div>
      <div class="data_input gradeSel">
      </div>
      <div class="clear"></div>
      <div class="data_seach" id="calBtn"><a>计算</a></div>
      <div class="data_back" id="examGradeBtn"><a>查看年级质量</a></div>
      <%--<div class="data_back" id="examClassBtn"><a>查看班级质量</a></div>--%>
      <div class="clear"></div>
    </div>
  </form>
</div>
<script type="text/javascript" src="${contextpath}/js/modules/sco/statistics/examGrade.js?v=20180115"></script>
</body>
</html>
