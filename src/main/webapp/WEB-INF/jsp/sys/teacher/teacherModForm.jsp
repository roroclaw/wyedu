<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>武汉音乐学院</title>
    <jsp:include page="../../common/common_top.jsp"/>
    <style type="text/css">
        .UI_bd{background:white;padding: 0px;}
    </style>
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
        <a href="###">教师管理</a><i>>></i><a href="###">教师信息管理</a><i>>></i><a href="###">教师信息修改</a>
    </div>
    <!--系统地图 end-->

    <!--数据显示区域 star-->
    <form id="dataForm" action="">
        <input type="hidden" name="id" value="${teacherInfo.id}" />
        <div class="UI_data UI_bd">
            <table style="width:100%">
                <tr>
                    <td style="width:100px;">工号<b style="color: #ff0000">*</b></td>
                    <td style="width:160px;"><input type="text" class="validate[required]" name="code" value="${teacherInfo.code}"></td>
                    <td style="width:100px;">真实姓名<b style="color: #ff0000">*</b></td>
                    <td><input type="text" class="validate[required]" name="name" value="${teacherInfo.name}"></td>
                    <td style="width:110px;">出生日期</td>
                    <td><input type="text" name="birth" readonly="true" class=""
                               onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"  value="<fmt:formatDate value="${teacherInfo.birth}" pattern="yyyy-MM-dd" />" ></td>
                </tr>
                <tr>
                    <td>籍贯</td>
                    <td ><input type="text" name="nativePlace" class="" value="${teacherInfo.nativePlace}">
                    </td>
                    <td>教师类型<b style="color: #ff0000">*</b></td>
                    <td  id="teacherTypeSel" value="${teacherInfo.type}"></td>
                    <td>年龄</td>
                    <td><input type="text" name="age" class="validate[custom[integer]]" value="${teacherInfo.age}"></td>
                </tr>
                <tr>
                    <td>性别<b style="color: #ff0000">*</b></td>
                    <td id="sexSel" value="${teacherInfo.sex}"></td>
                    <td>姓名拼音<b style="color: #ff0000">*</b></td>
                    <td><input type="text" name="namePy" class="validate[required]" value="${teacherInfo.namePy}"></td>
                    <td>身份证号<b style="color: #ff0000">*</b></td>
                    <td><input type="text" name="identityCard" class="validate[required,funcCall[idCardValidate]]" value="${teacherInfo.identityCard}"></td>
                </tr>
                <tr>
                    <td >入党日期</td>
                    <td style="width:160px;"><input type="text" name="inPartyDate" readonly="true" class=""
                                                    onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" value="<fmt:formatDate value="${teacherInfo.inPartyDate}" pattern="yyyy-MM-dd" />"></td>
                    <td>政治面貌</td>
                    <td id="politicalStatusSel" value="${teacherInfo.politicalStatus}"></td>
                    <td ></td>
                    <td></td>
                </tr>
                <tr>
                    <td>所属部门<b style="color: #ff0000">*</b></td>
                    <td id="depart_select_add"  value="${teacherInfo.departId}">
                    </td>
                    <td>民族</td>
                    <td id="nationSel"  value="${teacherInfo.nation}"></td>
                    <td>手机号码<b style="color: #ff0000">*</b></td>
                    <td><input type="text" name="phone" class="validate[required,custom[mobilPhone]]" value="${teacherInfo.phone}"></td>
                </tr>
                <tr>
                    <td>所学专业</td>
                    <td><input type="text" name="studyMajor" class="" value="${teacherInfo.studyMajor}"></td>
                    <td>授课专业</td>
                    <td id="majorSel_2"  value="${teacherInfo.teachMajorId}">
                    </td>
                    <td>院系</td>
                    <td><input type="text" name="colleg" class="" value="${teacherInfo.colleg}"></td>
                    </td>
                </tr>
                <tr>
                    <td>职称</td>
                    <td id="titleSel"  value="${teacherInfo.proTitle}">
                    </td>
                    <td>职务</td>
                    <td id="postSel" value="${teacherInfo.post}">
                    </td>
                    <td>专业特长</td>
                    <td ><input type="text" name="specialty" value="${teacherInfo.specialty}">
                    </td>
                </tr>
                <tr>
                    <td>本科毕业时间</td>
                    <td ><input type="text" name="bachDate" class="" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"
                                value="<fmt:formatDate value="${teacherInfo.bachDate}" pattern="yyyy-MM-dd" />"></td>
                    <td>本科毕业院校</td>
                    <td> <input type="text" name="bachSchool" class=""   value="${teacherInfo.bachSchool}"/></td>
                    <td>本科毕业方向</td>
                    <td> <input type="text" name="bachMajor" class=""   value="${teacherInfo.bachMajor}"/></td>
                </tr>
                <tr>
                    <td>硕士毕业时间</td>
                    <td ><input type="text" name="masterDate" class=""   onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" value="<fmt:formatDate value="${teacherInfo.masterDate}" pattern="yyyy-MM-dd" />"></td>
                    <td>硕士毕业院校</td>
                    <td> <input type="text" name="masterSchool" class=""  value="${teacherInfo.masterSchool}"/></td>
                    <td>硕士毕业方向</td>
                    <td> <input type="text" name="masterMajor" class=""  value="${teacherInfo.masterMajor}"></td>
                </tr>
                <tr>
                    <td>博士毕业时间</td>
                    <td ><input type="text" name="doctorDate" class="" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" value="<fmt:formatDate value="${teacherInfo.doctorDate}" pattern="yyyy-MM-dd" />"/></td>
                    <td>博士毕业院校</td>
                    <td> <input type="text" name="doctorSchool" class=""  value="${teacherInfo.doctorSchool}" /></td>
                    <td>博士毕业方向</td>
                    <td> <input type="text" name="doctorMajor" class=""  value="${teacherInfo.doctorMajor}" /></td>
                </tr>
                <tr>
                    <td>学位</td>
                    <td id="degreeSel"  value="${teacherInfo.degree}" >
                    </td>
                    <td>学历</td>
                    <td id="educationSel"  value="${teacherInfo.education}">
                    </td>
                    <td>教龄</td>
                    <td ><input type="text" name="beTeacherYears" value="${teacherInfo.beTeacherYears}">
                    </td>
                </tr>
                <tr>
                    <td>任职日期</td>
                    <td ><input type="text" name="beTeacherDate" class=""   onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" value="<fmt:formatDate value="${teacherInfo.beTeacherDate}" pattern="yyyy-MM-dd" />">
                    </td>
                    <td>参加工作日期</td>
                    <td ><input type="text" name="inworkDate" class=""   onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" value="<fmt:formatDate value="${teacherInfo.inworkDate}" pattern="yyyy-MM-dd" />">
                    </td>
                    <td>附中入职日期</td>
                    <td ><input type="text" name="inschoolDate" class=""  onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"  value="<fmt:formatDate  value="${teacherInfo.inschoolDate}" pattern="yyyy-MM-dd" />">
                    </td>
                </tr>
                <tr>
                    <td>主讲课程</td>
                    <td ><input type="text" name="mainCourse" class="" value="${teacherInfo.mainCourse}"/>
                    </td>
                    <td>是否双肩挑<b style="color: #ff0000">*</b></td>
                    <td>
                        <c:if test="${stuInfo.doubleDuty == '1'}">
                            <label>是</label>
                            <input name="doubleDuty" type="radio" value="1" checked class="validate[required]">
                            <label>否</label>
                            <input name="doubleDuty" type="radio" value="0"  class="validate[required]">
                        </c:if>
                        <c:if test="${stuInfo.doubleDuty != '1'}">
                            <label>是</label>
                            <input name="doubleDuty" type="radio" value="1"  class="validate[required]">
                            <label>否</label>
                            <input name="doubleDuty" type="radio" value="0" checked class="validate[required]">
                        </c:if>
                     </td>
                    <td>博士毕业方向</td>
                    <td> <input type="text" name="doctorMajor" class=""   value="${teacherInfo.doctorMajor}"/></td>
                </tr>
                <tr>
                    <td>配偶姓名</td>
                    <td ><input type="text" name="spouseName" class="" value="${teacherInfo.spouseName}"/>
                    </td>
                    <td>配偶电话</td>
                    <td><input type="text" name="spousePhone" class="" value="${teacherInfo.spousePhone}"/>
                    </td>
                    <td></td>
                    <td> </td>
                </tr>
            </table>
            <div class="clear"></div>
            <div class="data_row clear">
                <div class="data_seach" id="addBtn"><a>提交</a></div>
                <div class="data_back" id="closeBtn"><a>关闭</a></div>
            </div>
            <div class="clear"></div>
        </div>
    </form>



    </div>
    <script type="text/javascript" src="${contextpath}/js/modules/sys/teacher/teacherModForm.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
