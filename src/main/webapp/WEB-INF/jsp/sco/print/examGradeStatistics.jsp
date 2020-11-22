<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8" />
  <title></title>
  <style>
    .tablebox{width: 100%;}
    table{border-collapse:collapse;width: 100%; color: #666; font-family: "微软雅黑";}
    table td{border:1px solid #ededed; line-height:30px; font-size:12px; text-align:center;font-size: 12px; }
    table tr.tabletitle{height:50px; font-size: 16px; font-weight: bold;color: #333;}
    table .tab_tr{background:#ededed;  font-weight: bold; color: #555; height: 48px;}
    table .tab_tr td{height: 47px; padding: 0px; margin: 0px;}
    table .td_a img{ border:none; width:50px; height:50px; border:1xp solid #ccc;}
    table .tab_tr td img{height: 48px;}
    table .tr_score{ height:30px; line-height:30px; background:#ededed;}
    .dy{display: block; float: right; margin-right: 10px; border-left: none; width: 100px; height: 36px; line-height: 36px; text-align: center; border-radius: 4px; background: #32323a; color: white;}
  </style>
</head>
<body>
<div class="tablebox">
  <table>
    <tr class="tabletitle"><td colspan="10">${schoolYear}学年度${termText}${gradeText}${scoreTypeText}考试年级质量分析表</td><td colspan="3"><a onclick="window.print();" href="###" class="dy">打印</a></td></tr>
    <%--<tr>--%>
      <%--<td>初三（1）班</td>--%>
      <%--<td>考试人数</td>--%>
      <%--<td>总平分数</td><td>最高分</td>--%>
      <%--<td>≥600</td><td>≥580</td><td>≥550</td>--%>
      <%--<td>≥500</td><td>≥450</td><td>≥400</td>--%>
      <%--<td>≥350</td><td>≥300</td><td>＜300</td>--%>
    <%--</tr>--%>
    <c:forEach var="sta" items="${staList}" varStatus="status">
      <c:if test="${status.index%28 == 0}">
        <tr class="tab_tr">
        <td style="width: 100px;">
        <img src="${contextpath}/images/tip.png"/>
        </td>
        <td>考试人数</td>
        <td>总平均分数</td>
        <td>最高分</td>
        <td>≥600</td><td>≥580</td>
        <td>≥550</td><td>≥500</td>
        <td>≥450</td><td>≥400</td>
        <td>≥350</td><td>≥300</td>
        <td>＜300</td>
        </tr>
      </c:if>
      <tr>
        <td>${sta.className}</td>
        <td>${sta.stuCount}</td>
        <td>${sta.totalAvg}</td>
        <td>${sta.totalScore}</td>
        <td>${sta.col1}</td><td>${sta.col2}</td><td>${sta.col3}</td>
        <td>${sta.col4}</td><td>${sta.col5}</td><td>${sta.col6}</td>
        <td>${sta.col7}</td><td>${sta.col8}</td><td>${sta.col9}</td>
      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>

