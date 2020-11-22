<%@page import="com.roroclaw.base.utils.Constants"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="${contextpath}/js/common/leftMenu.js"></script>
<div class="UI_menu">
  <div class="UI_logo">
    <a href="###"><img src="${contextpath}/images/logo.jpg"/></a>
    <span>武汉音乐学院</span>
  </div>

  <div class="UI_menu_box">
    <input id="cur_menu" type="hidden"  value="${cur_menu}"/>
    <div class="UI_menu_list">
      <h4 class="smenu " data-id="main" data-url="main.html" style="cursor:pointer" >首页<i class="v vb"></i></h4>
    </div>
  </div>
  <div class="clear"></div>
</div>