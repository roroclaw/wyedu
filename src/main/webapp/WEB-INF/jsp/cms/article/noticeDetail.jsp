<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <a href="###">信息管理</a><i>>></i><a href="###">公告查询</a>
    </div>
    <!--系统地图 end-->

    <!--数据显示区域 star-->

        <div class="col-md-12">
            <div class="UI_data">
                <div class="data_box">
                    <div class="articleTitle"><span>${ArticleInfo.title}</span></div>
                    <div class="articleUpdate"><span>作者：${ArticleInfo.author}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>更新时间：<fmt:formatDate value="${ArticleInfo.update_time}" pattern="yyyy-MM-dd" /></span></div>
                    <div class="articleContent" id="articleContent">
                        <span>${ArticleInfo.content}</span>
                    </div>
                    <div class="clear"></div>
                    <div class="UI_bd clear">
                        <div class="data_back" id="closeBtn">关闭</div>
                    </div>
                <!--数据显示区域 end-->
                </div>
            </div>

        </div>


    <!--table按钮-->

    <!--内容数据end-->
    <script type="text/javascript" src="${contextpath}/js/modules/cms/noticeDetail.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
