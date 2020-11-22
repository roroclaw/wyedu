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
    <a href="###">成绩管理</a><i>>></i><a href="###">科目成绩导入</a>
  </div>
  <!--系统地图 end-->

  <!--数据显示区域 star-->
  <div id="divRowPart_1">
    <div class="col-md-12">
      <div class="UI_data">
        <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>科目成绩导入</span></div>
        <div class="data_box clear">
          <div class="data_seach" id="fileDownLoad" style="cursor:pointer;background: forestgreen">模板下载</div>
          <div class="data_seach" id="importBtn" style="cursor:pointer;margin-left: 5px">导入</div>
        </div>
        <div class="UI_table data_box dataTable">
        </div>
      </div>
    </div>
  </div>

  <!--日志详情-->
  <div id="logShowBox" class="message_bg" style="display: none;">
    <div class="message_box" >
      <div class="message_title">日志详情<i class="cancelBtn"></i></div>
      <div class="message_text" id="logDetail" style="height: 200px;width:100%;overflow-y: auto;overflow-x: hidden">

      </div>
      <div class="message_submit">
        <a class="left cancelBtn" style="width:calc(100% - 1px)">确定</a>
      </div>
    </div>
  </div>

  <jsp:include page="../../common/simpleUploader.jsp" flush="true">
    <jsp:param value="科目成绩导入" name="titleName"/>
    <jsp:param value="上传excel" name="btnName"/>
    <jsp:param value="${contextpath}/subjectScores/importSubjectScores.infc" name="url"/>
    <jsp:param value="xls,xlsx" name="extensions"/>
  </jsp:include>
  <!--内容数据end-->
  <script type="text/javascript" src="${contextpath}/js/modules/sco/scores/subjectScoreImp.js"></script>
  <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
