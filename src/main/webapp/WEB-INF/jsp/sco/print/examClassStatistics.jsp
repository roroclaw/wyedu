<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    table .tab_tr{ background:#ededed; height: 48px; line-height: 48px; font-weight: bold; color: #555;}
    table .td_a img{ border:none; width:50px; height:50px; border:1xp solid #ccc;}
    table .tr_score{ height:30px; line-height:30px; background:#ededed;}
    .dy{display: block; float: right; margin-right: 10px; border-left: none; width: 100px; height: 36px; line-height: 36px; text-align: center; border-radius: 4px; background: #32323a; color: white;}
  </style>
  <style type="text/css" media="print">
    .noprint { display:none;}
  </style>
</head>
<body>
<div class="tablebox">
  <table>
    <tr class="tabletitle"><td colspan="13">${schoolYear}学年度${termText}${gradeText}${scoreTypeText}考试班级质量分析表</td><td><a onclick="window.print();" href="###" class="dy noprint">打印</a></td></tr>
    <c:forEach var="sta" items="${staList}" varStatus="status">
      <c:if test="${status.index%28 == 0}">
        <tr class="tab_tr">
          <td style="width: 100px;">
            <img src="${contextpath}/images/tip.png"/>
          </td>
          <td>科目</td>
          <td>考试人数</td>
          <td>平均分</td>
          <td>最高分</td>
          <td>优秀率(${section.excellent})</td>
          <td>及格率(${section.pass})</td>
          <td>贡献率</td>
          <td>=100(${section.col1})</td>
          <td>≥90(${section.col2})</td>
          <td>≥80(${section.col3})</td>
          <td>≥70(${section.col4})</td>
          <td>≥60(${section.col5})</td>
          <td>＜60(${section.col6})</td>
        </tr>
      </c:if>
      <c:forEach var="substa" items="${sta.scoClassScoreStaticList}" varStatus="status">
        <tr>
          <c:if test="${status.index == 0}">
            <td rowspan="${fn:length(sta.scoClassScoreStaticList)}">${sta.className}</td>
          </c:if>
          <td>${substa.subjectName}</td>
          <td>${substa.stuCount}</td>
          <td>${substa.avgScore}</td>
          <td>${substa.maxScore}</td>
          <td><fmt:formatNumber type="number" pattern="##.##" value="${substa.excellent*100/substa.stuCount}" />%</td>
          <td><fmt:formatNumber type="number" pattern="##.##" value="${substa.pass*100/substa.stuCount}" />%</td>
          <c:if test="${sta.totalAvg == 0}">
            <td><fmt:formatNumber type="number" pattern="##.##" value="0" />%</td>
          </c:if>
          <c:if test="${sta.totalAvg > 0}">
            <td><fmt:formatNumber type="number" pattern="##.##" value="${substa.avgScore*100/sta.totalAvg}" />%</td>
          </c:if>
          <td>${substa.col1}</td>
          <td>${substa.col2}</td>
          <td>${substa.col3}</td>
          <td>${substa.col4}</td>
          <td>${substa.col5}</td>
          <td>${substa.col6}</td>
        </tr>
      </c:forEach>
    </c:forEach>
  </table>
</div>
</body>
</html>

