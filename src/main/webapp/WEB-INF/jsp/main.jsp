<%@ page import="com.roroclaw.base.utils.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <title>武汉音乐学院</title>
  <jsp:include page="common/common_top.jsp" />
  <script type="text/javascript" src="${contextpath}/js/main.js"></script>
</head>

<body>
<!--菜单star-->
<jsp:include page="common/leftMenu.jsp" />
<!--菜单end-->
<!--头部star-->
<jsp:include page="common/header.jsp" />
<!--头部end-->

<!--内容数据star-->
<div class="UI_main">
  <!--系统地图 star-->
  <div class="UI_address">
    <span></span>
    <a  href="###">首页</a></a>
  </div>
  <!--系统地图 end-->
</div>
<!--内容数据end-->
<div id="basic_accToken" val="<%=session.getAttribute(Constants.ACC_TOKEN)%>" style="display:none"></div>
<jsp:include page="common/basic.jsp" />
</body>
</html>
