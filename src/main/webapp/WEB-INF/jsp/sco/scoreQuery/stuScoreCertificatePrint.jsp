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
<div id="noPrint">
<!--菜单star-->
<jsp:include page="../../common/leftMenu.jsp"/>
<!--菜单end-->
<!--头部star-->
<jsp:include page="../../common/header.jsp"/>
<!--头部end-->
        <!--内容数据star-->

        <input type="hidden" id="birth" value="<fmt:formatDate value="${studentInfo.birth}" pattern="yyyy-MM-dd" />" />
        <div class="UI_main">

                <!--系统地图 star-->
                <div class="UI_address">
                    <span></span>
                    <a href="###">成绩管理</a><i>>></i><a href="###">成绩证明</a><i>>></i><a href="###">打印成绩证明</a>
                </div>
            <!--数据显示区域 star-->
            <div class="UI_data ">
                <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>打印成绩证明</span></div>
                <form id="queryForm" action="">
                    <div class="data_box clear">
                        <input type="hidden" id="stuId" name="stuId" value="${studentInfo.id}" />
                        <input type="hidden" id="realName" value="${studentInfo.realName}" />
                        <input type="hidden" id="classText" value="${studentInfo.classText}" />
                        <input type="hidden" id="grade" value="${studentInfo.grade}" />
                        <input type="hidden" id="classSeq" value="${studentInfo.classSeq}" />
                        <input type="hidden" id="sexText" value="${studentInfo.sexText}" />
                        <div class="data_input buildingSel">
                        </div>
                        <div class="data_input">
                            <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学段：</span>
                            <div class="select_box">
                                <select name="periodSection"><option value="">--请选择--</option><option value="1">小学</option><option value="2">初中</option><option value="3">高中</option>
                                </select>
                            </div>
                        </div>
                        <div class="data_seach" id="queryBtn"><a>提交</a></div>
                        <div class="oper_btn_fun_1" id="printBtn" style="margin-left: 5px;margin-top: 8px;">打印</div>
                    </div>
                </form>
                <div  class="UI_bd">

                </div>
                <div  id="content_div" class="UI_bd TchPlanCourseOpenCheckList">

                </div>
            </div>

            <div class="data_back" id="closeBtn"><a>关闭</a></div>
            <div class="clear"></div>

        </div>
    <script type="text/javascript" src="${contextpath}/js/modules/sco/scoreQuery/stuScoreCertificatePrint.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
</div>

<!--startprint1-->
<div  id="content_div_print" class="UI_bd TchPlanCourseOpenCheckList">
</div>
<!--endprint1-->
</body>
</html>
