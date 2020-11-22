<%@ page import="com.roroclaw.base.utils.Constants" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<script type="text/javascript" src="${contextpath}/js/common/header.js"></script>
<div class="UI_top">
    <div class="top_left"><img src="${contextpath}/images/logo.png"/></div>
    <div class="top_right">
        <div class="admin_name">
            <i class="iconfont icon-yonghu"></i>
            <span>欢迎您！<em><%=session.getAttribute(Constants.SESSION_USER_NAME)%></em></span>
            <i class="iconfont icon-xiangxia martop"></i>

            <div id="modpwd" class="admin_role ">
                <a href="###">修改密码</a>
            </div>
        </div>
        <div id="exitBtn" class="admin_exit">
            <i class="iconfont icon-tuichu"></i>
            <span>退出</span>
        </div>
    </div>
    <!--修改密码弹出-->
    <form id="modPwdForm" style="display:none">
        <div class="message_title">密码修改</div>
        <div class="message_text">
            <div class="data_input"><span>原始密码：</span><input type="password" id="oldPwd" name="oldPwd" class="validate[required]"/><b>*</b></div>
            <div class="data_input"><span>&nbsp;&nbsp;&nbsp;新密码：</span><input type="password" name="newPwd" class="validate[required]"/><b>*</b></div>
            <div class="data_input"><span>确认新密码：</span><input type="password" name="confirmPwd" class="validate[required]"/><b>*</b></div>
            <div style="clear:both;"></div>
        </div>
    </form>
</div>