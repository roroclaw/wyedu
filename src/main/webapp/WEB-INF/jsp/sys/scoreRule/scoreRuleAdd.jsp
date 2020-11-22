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
    <a href="###">系统管理</a><i>>></i><a href="###">新增成绩规则配置</a>
  </div>
  <!--系统地图 end-->

  <!--数据显示区域 star-->
  <form id="dataForm" >
    <div class="UI_data UIfrom">
        <div class="data_input subjectSel">
          <%--<span>科目:</span>--%>
          <%--<input name="subjectText" type="text"  value="0" />--%>
        </div>
        <div class="clear"></div>
        <div class="data_input">
          <span>期中比例:</span>
          <input name="middelRatio" type="text" data-text="期中比例" value="0" class="ratioInput validate[required,,custom[number]]"/>%<i>*</i>
        </div>
        <div class="clear"></div>
        <div class="data_input">
          <span>期末比例:</span>
          <input name="endRatio" type="text" data-text="期末比例" value="0" class="ratioInput validate[required,,custom[number]]"/>%<i>*</i>
        </div>
        <div class="clear"></div>
        <div class="data_input">
          <span>平时比例:</span>
          <input name="usualRatio" type="text"  data-text="平时比例"  value="0" class="ratioInput validate[required,,custom[number]]"/>%<i>*</i>
        </div>
        <div class="clear"></div>
        <div class="data_input scopeSel">
          <%--<span>适用范围</span>--%>
          <%--<input name="usualRatio" type="text"  data-text="平时比例"  value="${scoresRuleConfig.usualRatio}" class="ratioInput validate[required,,custom[number]]"/>%<i>*</i>--%>
        </div>
        <div class="clear"></div>
        <div class="data_seach" id="addBtn"><a>提交</a></div>
        <div class="data_back" id="backBtn"><a>返回</a></div>
        <div class="clear"></div>
    </div>
  </form>
</div>
<script type="text/javascript" src="${contextpath}/js/modules/sys/scoreRule/scoreRuleAdd.js"></script>
</body>
</html>
