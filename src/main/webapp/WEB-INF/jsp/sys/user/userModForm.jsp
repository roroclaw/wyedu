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
    <a href="###">系统管理</a><i>>></i><a href="###">用户管理</a><i>>></i><a href="###">用户修改</a>
  </div>
  <!--系统地图 end-->
  <!--数据显示区域 star-->
  <form id="dataForm" action="">
    <div class="UI_data UIfrom">
      <input name="id" value="${sysUserInfo.id}" type="hidden" />
      <div class="data_input"><span>用户姓名：</span><input name="realName" value="${sysUserInfo.realName}" maxlength="15" type="text"  class="validate[required]"/><i>*</i></div>
      <div class="data_input"><span>用户账号：</span><input name="loginName" disabled="true" readonly="true" maxlength="15" value="${sysUserInfo.loginName}"  type="text" class="validate[required]"/><i>*</i></div>
      <div class="clear"></div>
      <!--下拉框 star-->
      <div class="data_input userTypeSel" defValue="${sysUserInfo.type}" >
      </div>
      <div class="clear"></div>
      <div class="data_input"><span>角色选择：</span>
        <ul id="roleSel" >
            <c:forEach var="role" items="${allRoles}">
              <c:choose>
                <c:when test="${role.isOwn}">
                    <li class="roleItem cur" data-val="${role.id}">${role.roleName}</li>
                </c:when>
                <c:otherwise>
                    <li class="roleItem" data-val="${role.id}">${role.roleName}</li>
                </c:otherwise>
              </c:choose>
            </c:forEach>
        </ul>
      </div>
      <div class="clear"></div>
      <div class="data_seach" id="subBtn"><a>提交</a></div>
      <div class="data_back" id="backBtn"><a>返回</a></div>
      <div class="clear"></div>
    </div>
  </form>
</div>
<script type="text/javascript" src="${contextpath}/js/modules/sys/user/userModForm.js"></script>
<jsp:include page="../../common/basic.jsp"/>
</body>
</html>
