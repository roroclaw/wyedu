<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <title>武汉音乐学院</title>
  <style>
    .date_role_left{float: left; border: 1px solid #e5e5e5; height: 400px; overflow-y: auto;}
    .date_role_right{width:300px; float: left; border: 1px solid #e5e5e5; height: 400px; margin-left: 15px;overflow-y: auto;}
  </style>
  <link rel="stylesheet" href="${contextpath}/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
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
    <a href="###">系统管理</a><i>>></i><a href="###">角色管理</a><i>>></i><a href="###">角色新增</a>
  </div>
  <!--系统地图 end-->

  <!--数据显示区域 star-->
  <div class="UI_data">
    <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>角色管理</span></div>
    <div class="data_box clear">
      <div class="date_role_left UI_table" style="width: 500px;">
        <table>
          <tr id="roleColTile" class="title_tr">
            <td>选择</td>
            <td>序号</td>
            <td>角色名</td>
            <td>类型</td>
            <td>操作</td>
          </tr>
        </table>
      </div>

      <!--树显示区域-->
      <div id="treeDiv" class="date_role_right ztree">

      </div>
      <div class="ks-clear"></div>
      <div class="UI_data clear">
        <div id="addBtn" class="oper_btn"><a>新增角色</a></div>
        <div id="powerSubBtn" class="oper_btn"><a>提交权限修改</a></div>
      </div>
    </div>
  </div>
  <!--数据显示区域 end-->

  <!--角色form-->
  <form id="roleAddForm" class="dataForm">
    <div id="roleAddShowBox" class="message_bg" style="display: none">
      <div class="message_box">
        <div class="message_title">新增角色<i class="cancelBtn"></i></div>
        <div class="message_text">
          <div class="data_input"><span>角色名：</span><input type="text" name="roleName" class="validate[required]" /><b>*</b></div>
          <div style="clear:both;"></div>
        </div>
        <div class="message_submit">
          <a id="roleAddSubBtn" class="left">确定</a>
          <a class="right cancelBtn">取消</a>
        </div>
      </div>
    </div>
  </form>

  <!--角色修改form-->
  <form id="roleModForm" class="dataForm">
    <div id="roleModShowBox" class="message_bg" style="display: none">
      <div class="message_box">
        <div class="message_title">修改角色<i class="cancelBtn"></i></div>
        <div class="message_text">
          <div class="data_input"><span>角色名：</span>
            <input type="hidden" name="id" class="validate[required]" /><b>*</b>
            <input type="text" maxlength="15" name="roleName" class="validate[required]" /><b>*</b>
          </div>
          <div style="clear:both;"></div>
        </div>
        <div class="message_submit">
          <a id="roleModSubBtn" class="left">确定</a>
          <a class="right cancelBtn">取消</a>
        </div>
      </div>
    </div>
  </form>

</div>
<script type="text/javascript" src="${contextpath}/plugins/ztree/js/jquery.ztree.core.js?rand=${jsRand}"></script>
<script type="text/javascript" src="${contextpath}/plugins/ztree/js/jquery.ztree.excheck.js?rand=${jsRand}"></script>
<script type="text/javascript" src="${contextpath}/js/modules/role/roleForm.js?rand=${jsRand}"></script>
<jsp:include page="../../common/basic.jsp"/>
</body>
</html>
