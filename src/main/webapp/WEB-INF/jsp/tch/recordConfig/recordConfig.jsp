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
    <a href="###">登分设置</a><i>>></i><a href="###">登分设置</a>
  </div>
  <!--系统地图 end-->

  <!--数据显示区域 star-->
  <div id="divRowPart_1">
    <div class="col-md-12">
      <div class="UI_data">
        <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>登分设置</span></div>
        <form id="queryForm">
          <div class="data_box clear">
            <div class="data_input schoolYearSel">
            </div>
            <div class="data_input termSel">
            </div>
            <div class="data_input classSel">
            </div>
            <div class="data_input scoresType">
            </div>
            <div class="data_seach" id="queryBtn" style="cursor:pointer">搜索</div>
            <div style="clear:both;"></div>
            <div class="data_input configStatus">
            </div>
            <div class="data_input">
              <span>课程名：</span>
              <input type="text" name="courseName"/>
            </div>
          </div>
        </form>
        <div class="UI_table data_box dataTable">
        </div>
        <!--数据显示区域 end-->
      </div>
    </div>
  </div>
</div>

<!--设置登分教师-->
  <div id="teacherSetBox" class="message_bg" style="display: none">
    <form id="teacherSetForm" class="dataForm">
    <input id="teacherSetForm_id" type="hidden" name="openCourseId">
    <input id="scoresType" type="hidden" name="scoresType">
    <div class="message_box">
      <div class="message_title">设置登分教师<i class="cancelBtn"></i></div>
      <div class="message_text">
        <div class="data_input teacherSel">
        </div>
        <div style="clear:both;"></div>
      </div>
      <div class="message_submit">
        <a href="###" id="teacherSetBtn" class="left">确定</a>
        <a href="###" class="right cancelBtn">取消</a>
      </div>
    </div>
    </form>
  </div>

<!--内容数据end-->
<script type="text/javascript" src="${contextpath}/plugins/autoInput/js/jquery.autocomplete.js"></script>
<script type="text/javascript" src="${contextpath}/js/common/jquery.roroclaw.autoInput.js"></script>
<script type="text/javascript" src="${contextpath}/js/modules/tch/recordConfig/recordConfig.js?v=2018042802"></script>
<jsp:include page="../../common/basic.jsp"/>
</body>
</html>
