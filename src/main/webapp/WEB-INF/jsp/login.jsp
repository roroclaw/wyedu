<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>武汉音乐学院教务管理系统</title>
    <link type="text/css" rel="stylesheet" href="${contextpath}/css/login.css"/>
    <link type="text/css" rel="stylesheet" href="${contextpath}/css/messages.css"/>
    <link href="${contextpath}/css/validate/validationEngine.jquery.css" rel="stylesheet" type="text/css"/>


</head>

<body>
<div class="login_top">
    <div class="login_name">
        <div class="login_logo"><img src="${contextpath}/images/login_logo.png"/></div>
        <div class="login_right"><img src="${contextpath}/images/right.png"/></div>
    </div>
</div>

<form id="loginForm" method="post" >
    <div class="login_text">
        <div class="text_left"></div>
        <div class="text_right">
            <div class="right_title">欢迎回来</div>
            <div class="user_name"><span></span><input class="validate[required]" data-errormessage-value-missing="请输入用户名!" type="text" name="account" placeholder="输入用户名" value=""></div>
            <div class="user_pass"><span></span><input class="validate[required]" data-errormessage-value-missing="请输入密码!" name="pwd" type="password" placeholder="输入密码" value=""></div>
            <div class="user_cord"><span></span>
                <input name="verifyCode" class="validate[required]" data-errormessage-value-missing="输入验证码!" type="text" placeholder="输入验证码"><a>
                <img id="verifyCode" src="${contextpath}/verifyCodeImage"></a>
            </div>
            <div class="usr_message">${errorMsg}</div>
            <div class="usr_submit" id="loginBtn">
                <a  href="javascript:">登录</a>
            </div>
        </div>
        <div class="clear"></div>
    </div>
</form>

<div class="login_bottom">
</div>

<script type="text/javascript" src="${contextpath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${contextpath}/js/common/jquery.cookie.js"></script>
<script type="text/javascript" src="${contextpath}/js/validate/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${contextpath}/js/validate/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${contextpath}/js/common/common.js"></script>
<script type="text/javascript" src="${contextpath}/js/common/jquery.custom.alert.js"></script>
<script type="text/javascript" src="${contextpath}/js/common/jquery.custom.ajaxConnSend-1.1.js"></script>
<script type="text/javascript" src="${contextpath}/js/common/jquery.custom.form.js"></script>
<script type="text/javascript" src="${contextpath}/js/login.js"></script>

</body>
</html>
