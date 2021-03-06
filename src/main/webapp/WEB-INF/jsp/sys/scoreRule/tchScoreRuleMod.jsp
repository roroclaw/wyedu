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
    <a href="###">系统管理</a><i>>></i><a href="###">(${tchScoresRuleConf.name})成绩规则配置</a>
  </div>
  <!--系统地图 end-->

  <!--数据显示区域 star-->
  <form id="dataForm" >
    <div class="UI_data UIfrom">
        <input type="hidden" name="id" value="${tchScoresRuleConf.id}" />
        <div class="data_input">
            <span>规则名称:</span>
            <input name="name" type="text" data-text="规则名称"  value="${tchScoresRuleConf.name}" class="ratioInput validate[required]"/>
        </div>
        <div class="clear"></div>
        <div class="data_input">
          <span>科目</span>
          <input name="subjectText" type="text"  value="${tchScoresRuleConf.subjectName}" readonly="true"/>
        </div>
        <div class="clear"></div>
        <div class="data_input">
          <span>期中比例</span>
          <input name="middelRatio" type="text" data-text="期中比例" value="${tchScoresRuleConf.middelRatio}" class="ratioInput validate[required,,custom[number]]"/>%<i>*</i>
        </div>
        <div class="clear"></div>
        <div class="data_input">
          <span>期末比例</span>
          <input name="endRatio" type="text" data-text="期末比例" value="${tchScoresRuleConf.endRatio}" class="ratioInput validate[required,,custom[number]]"/>%<i>*</i>
        </div>
        <div class="clear"></div>
        <div class="data_input">
          <span>平时比例</span>
          <input name="usualRatio" type="text"  data-text="平时比例"  value="${tchScoresRuleConf.usualRatio}" class="ratioInput validate[required,,custom[number]]"/>%<i>*</i>
        </div>
        <div class="clear"></div>
        <div class="data_input scopeSel" defValue="${tchScoresRuleConf.gradeScopeId}">
            <%--<span>适用范围</span>--%>
            <%--<input name="usualRatio" type="text"  data-text="平时比例"  value="${tchScoresRuleConf.usualRatio}" class="ratioInput validate[required,,custom[number]]"/>%<i>*</i>--%>
        </div>
        <div class="clear"></div>
        <div class="data_seach" id="modBtn"><a>提交</a></div>
        <div class="data_back" id="backBtn"><a>返回</a></div>
        <div class="clear"></div>
    </div>
  </form>
</div>
<script type="text/javascript" src="${contextpath}/js/modules/sys/scoreRule/tchScoreRuleMod.js?v=20201125"></script>
</body>
</html>
