<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>武汉音乐学院</title>
    <style>
        .tablebox{width: 100%;}
        table{border-collapse:collapse;width: 100%; color: #666; font-family: "微软雅黑";}
        table td{border:1px solid #ededed; line-height:16px; font-size:8px; text-align:center;}
        table tr.tabletitle{height:21px; font-size: 8px; font-weight: bold;color: #333;border-left:0px;border-top: 0px;border-right: 0px;}
        table .tab_tr{ background:#ededed; height: 39px; line-height: 35px; font-weight: bold; color: #555;}
        table .td_a img{ border:none; width:50px; height:50px; border:1xp solid #ccc;}
        table .tr_score{ height:30px; line-height:30px; background:#ededed;}
        .seatsTipPageBreak {
            page-break-before: auto;
            page-break-after: always;
        }
    </style>
</head>
<body>
<div class="tablebox">
    <div class="seatsTipPageBreak">
    <div style="padding:10px 20px 10px 20px;font-size:12px;font-weight:500;text-align: center;"><span>${schoolYear}学年${termText}${className}${courseName}成绩表</span></div>
    <table>
        <tr class="tab_tr">
            <td width="10%">序号</td>
            <td width="15%">学号</td>
            <td width="15%">姓名</td>
            <td width="25%">专业</td>
            <td width="15%">${scoreTypeText}</td>
            <td width="20%">分数备注</td>
        </tr>
        <c:forEach var="score" items="${scoList}" varStatus="status">
            <c:if test="${status.index>0 && (status.index)%50 == 0}">
                </table>
                </div>
                <div class="seatsTipPageBreak">
                <table>
                <tr class="tab_tr">
                    <td width="10%">序号</td>
                    <td width="15%">学号</td>
                    <td width="15%">姓名</td>
                    <td width="25%">专业</td>
                    <td width="15%">${scoreTypeText}</td>
                    <td width="20%">分数备注</td>
                </tr>
            </c:if>
            <tr >
                <td>${status.index+1}</td>
                <td>${score.stuNo}</td>
                <td>${score.stuName}</td>
                <td>${score.majorText}</td>
                <td>
                    <c:if test="${empty score.score}">
                        0
                    </c:if>
                    <c:if test="${!empty score.score}">
                        ${score.score}
                    </c:if>
                </td>
                <td>${score.remark}</td>
            </tr>
            <%--<tr >--%>
                <%--<td>${status.index+1}</td>--%>
                <%--<td>${score.stuNo}</td>--%>
                <%--<td>${score.stuName}</td>--%>
                <%--<td>${score.majorText}</td>--%>
                <%--<td>--%>
                    <%--<c:if test="${empty score.score}">--%>
                        <%--0--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${!empty score.score}">--%>
                        <%--${score.score}--%>
                    <%--</c:if>--%>
                <%--</td>--%>
                <%--<td>${score.remark}</td>--%>
            <%--</tr>--%>
        </c:forEach>
  </table>
  <div style="padding:20px 100px 20px 20px;text-align: right;"><span>教师签名:</span></div>
  </div>
</div>
</body>
<script type="text/javascript">
   //preview(1);
    function preview(oper)
    {
        if (oper < 10)
        {
            window.print();
            setTimeout( "window.close();",1000);
        } else {
            window.print();
        }
    }


</script>
</html>
