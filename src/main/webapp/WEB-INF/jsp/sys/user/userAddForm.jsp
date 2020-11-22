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
        <a href="###">系统管理</a><i>>></i><a href="###">用户管理</a><i>>></i><a href="###">用户新增</a>
    </div>
    <!--系统地图 end-->
    <!--数据显示区域 star-->
    <form id="dataForm" action="">
        <div class="UI_data UIfrom">
            <div class="data_input"><span>用户姓名：</span><input name="realName" type="text" maxlength="15" class="validate[required]"/><i>*</i></div>
            <div class="data_input"><span>用户账号：</span><input name="loginName" type="text" maxlength="15"  class="validate[required]"/><i>*</i></div>
            <div class="clear"></div>
            <!--下拉框 star-->
            <div class="data_input userTypeSel">
                <%--<span>用户类型：</span>--%>
                <%--<div class="select_box">--%>
                    <%--<select id="userTypeSel" name="type" class="validate[required]">--%>

                    <%--</select>--%>
                <%--</div>--%>
                <%--<i>*</i>--%>
            </div>
            <div class="clear"></div>
            <div class="data_input"><span>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</span><input type="password" maxlength="11" name="encryptPass" value="123456"/><i>*</i></div>
            <div class="data_input"><span>密码确认：</span><input type="password" name="passVerify"  maxlength="11" value="123456"/><i>*</i></div>
            <div class="clear"></div>
            <div class="data_input"><span>角色选择：</span>
                <ul id="roleSel">
                </ul>
            </div>
            <div class="clear"></div>

            <div class="data_seach" id="addBtn"><a>提交</a></div>
            <div class="data_back" id="backBtn"><a>返回</a></div>
            <div class="clear"></div>
        </div>
    </form>
</div>
<script type="text/javascript" src="${contextpath}/js/modules/sys/user/userForm.js"></script>
<jsp:include page="../../common/basic.jsp"/>
</body>
</html>
