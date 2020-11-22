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
    <a href="###">成绩管理</a><i>>></i><a href="###">科目成绩管理</a><i>>></i><a href="###">科目成绩新增</a>
  </div>
  <!--系统地图 end-->
  <!--数据显示区域 star-->
  <form id="dataForm" action="">
    <input name="id" value="${scoSubjectScores.id}" type="hidden"/>
    <div class="UI_data UIfrom">
      <div class="data_input"><span>学员：</span>
        <input id="stuNameSel"  name="stuName" type="text" placeholder="输入学号过滤选择"  data-code="${scoSubjectScores.stuId}"  value="${scoSubjectScores.stuName}"  class="validate[required]"/><i>*</i>
      </div>
      <div class="clear"></div>
      <div class="data_input"><span>班级：</span>
        <input id="className" value="${scoSubjectScores.className}" name="className" type="text"  readonly="true"/>
      </div>
      <div class="clear"></div>
      <!--下拉框 star-->
      <div class="data_input">
        <span>科目：</span>
        <div class="select_box">
          <select id="subjectSel"  dfVal="${scoSubjectScores.subjectId}" name="subjectId" class="validate[required]">

          </select>
        </div>
        <i>*</i>
      </div>
      <div class="clear"></div>
      <div class="data_input"><span>分数：</span>
        <input name="score" value="${scoSubjectScores.score}" type="text"  class="validate[required,custom[number]]"/><i>*</i>
      </div>
      <div class="clear"></div>
      <div class="data_input"><span>学年：</span>
        <div class="select_box">
          <select id="schoolYearSel"  dfVal="${scoSubjectScores.schoolYear}" name="schoolYear" class="validate[required]">

          </select>
        </div>
        <i>*</i>
      </div>
      <div class="clear"></div>
      <div class="data_seach" id="subBtn"><a>提交</a></div>
      <div class="data_back" id="backBtn"><a>返回</a></div>
      <div class="clear"></div>
    </div>
  </form>
</div>
<script type="text/javascript" src="${contextpath}/plugins/autoInput/js/jquery.autocomplete.js"></script>
<script type="text/javascript" src="${contextpath}/js/common/jquery.roroclaw.autoInput.js"></script>
<script type="text/javascript" src="${contextpath}/js/modules/sco/scores/subjectScoreModForm.js"></script>
<jsp:include page="../../common/basic.jsp"/>
</body>
</html>
