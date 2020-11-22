<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    double jsRand = Math.random();
    pageContext.setAttribute("jsRand",jsRand);
%>
<link type="text/css" rel="stylesheet" href="${contextpath}/css/common.css"/>
<link type="text/css" rel="stylesheet" href="${contextpath}/css/messages.css"/>
<link type="text/css" rel="stylesheet" href="${contextpath}/css/style.css"/>
<link href="${contextpath}/css/validate/validationEngine.jquery.css"  rel="stylesheet" type="text/css"/>
<link href="${contextpath}/plugins/laydate/need/laydate.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${contextpath}/js/jquery-1.8.3.min.js?rand=${jsRand}"></script>
<script type="text/javascript" src="${contextpath}/js/validate/jquery.validationEngine-zh_CN.js?rand=${jsRand}"></script>
<script type="text/javascript" src="${contextpath}/js/validate/jquery.validationEngine.js?rand=${jsRand}"></script>
<script type="text/javascript" src="${contextpath}/js/common/common.js?rand=${jsRand}"></script>
<script type="text/javascript" src="${contextpath}/js/common/jquery.cookie.js?rand=${jsRand}"></script>
<script type="text/javascript" src="${contextpath}/js/common/jquery.custom.form.js?rand=${jsRand}"></script>
<script type="text/javascript" src="${contextpath}/js/common/jquery.custom.tool.js?rand=${jsRand}"></script>
<script type="text/javascript" src="${contextpath}/js/common/jquery.custom.alert.js?rand=${jsRand}"></script>
<script type="text/javascript" src="${contextpath}/js/common/jquery.custom.select.js?rand=${jsRand}"></script>
<script type="text/javascript" src="${contextpath}/js/common/jquery.custom.ajaxConnSend.js?rand=${jsRand}"></script>
<script type="text/javascript" src="${contextpath}/js/common/jquery.custom.table.js?rand=${jsRand}"></script>
<script type="text/javascript" src="${contextpath}/plugins/laydate/laydate.js?rand=${jsRand}"></script>