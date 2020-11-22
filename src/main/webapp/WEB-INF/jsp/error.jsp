<%@page import="java.util.Map" %>
<%@ page import="com.roroclaw.base.utils.Constants" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>错误信息</title>
    <link type="text/css" rel="stylesheet" href="${contextpath}/css/login.css" />
</head>
<body style="background:#fff;">
<%
    Map exMap = (Map) request.getAttribute("exMap");
    String statusCode = (String) exMap.get("status");
    String describe = (String) exMap.get("describe");
    String basePath = request.getContextPath();
%>
<div class="error_div">
    <div class="error_message">
        <%=statusCode %>,<%=describe %>
        <%if(Constants.EXCEPTION_CODE.STATUS_FAIL_USER_VALIDATE.equals(statusCode)){%>
            <a href="<%=basePath%>/login.html">返回</a>
        <% }else{%>
            <a href="###" onclick="window.history.go(-1)">返回</a>
        <%} %>
    </div>
</div>
</body>
</html>