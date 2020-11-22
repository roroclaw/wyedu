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
        <a href="###">学生管理</a><i>>></i><a href="###">学生信息管理</a><i>>></i><a href="###">学籍修改</a>
    </div>
    <!--系统地图 end-->

    <!--数据显示区域 star-->
    <form id="dataForm" action="">
        <input type="hidden" name="id" value="${stuInfo.id}" />
        <div class="UI_data UI_bd">
            <table style="width:100%">
                <tr>
                    <td style="width:55px;">编号</td>
                    <td style="width:160px;"><input type="text"  class="" name="docId" value="${stuInfo.docId}"></td>
                    <td style="width:100px;">姓名</td>
                    <td><input type="text"  class="validate[required]" name="realName" value="${stuInfo.realName}"></td>
                    <td style="width:110px;">出生日期<b style="color: #ff0000">*</b></td>
                    <td><input type="text" name="birth" readonly="true" class="validate[required]"
                               onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"  value="<fmt:formatDate value="${stuInfo.birth}" pattern="yyyy-MM-dd" />" ></td>
                    <td rowspan="5" style="width:150px;" class="useimg">
                        <img id="stuImg" src="${stuInfo.realImgUrl}"/>
                        <input id="imgUrl" name="imgUrl" class="validate[required]" type="hidden" value="${stuInfo.imgUrl}"/>
                        <div class="btns" >
                            <div id="filePicker">选择照片</div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>性别<b style="color: #ff0000">*</b></td>
                    <td id="sexSel" value="${stuInfo.sex}">
                    </td>
                    <td>曾用名</td>
                    <td><input type="text" name="usedName" value="${stuInfo.usedName}"></td>
                    <td>身份证号<b style="color: #ff0000">*</b></td>
                    <td><input type="text" name="identityCard" class="validate[required]"  value="${stuInfo.identityCard}"></td>
                </tr>
                <tr>
                    <td>年级<b style="color: #ff0000">*</b></td>
                    <td id="gradeSel" >
                        <input type="text" readonly value="${stuInfo.gradeText}">
                        <input type="hidden" name="grade" value="${stuInfo.grade}">
                    </td>
                    <td>民族<b style="color: #ff0000">*</b></td>
                    <td id="nationSel" value="${stuInfo.nation}">

                    </td>
                    <td>手机号码<b style="color: #ff0000"></b></td>
                    <td><input type="text" name="phone" class="validate[custom[mobilPhone]]" value="${stuInfo.phone}"></td>
                </tr>
                <tr>
                    <td>学制<b style="color: #ff0000">*</b></td>
                    <td id="eduTypeSel" value="${stuInfo.eduType}">
                        <input type="text" readonly value="${stuInfo.eduTypeText}">
                        <input type="hidden" name="eduType" value="${stuInfo.eduType}">
                    </td>
                    <td>修读方向<b style="color: #ff0000">*</b></td>
                    <td id="majorSel" >
                        <input type="text" readonly value="${stuInfo.majorText}">
                        <input type="hidden" name="majorId" value="${stuInfo.majorId}">
                    </td>
                    <td>教学计划<b style="color: #ff0000">*</b></td>
                    <td id="tchPlanSel">
                        <input type="text" readonly value="${stuInfo.tchPlanText}">
                        <input type="hidden" name="tchPlanId"  value="${stuInfo.tchPlanId}">
                    </td>
                </tr>
                <tr>
                    <td>政治面貌</td>
                    <td id="politicalStatusSel" value="${stuInfo.politicalStatus}"></td>
                    <td>入学年月<b style="color: #ff0000">*</b></td>
                    <td><input type="text" name="enrolDate"  value="<fmt:formatDate value="${stuInfo.enrolDate}" pattern="yyyy-MM" />" readonly="true" onclick="laydate({istime: true, format: 'YYYY-MM'})" class="validate[required]"></td>
                    <td>邮政编码</td>
                    <td><input type="text" name="postCode" maxlength="15"  value="${stuInfo.postCode}"></td>
                </tr>
                <tr>
                    <td >班级<b style="color: #ff0000">*</b></td>
                    <td id="classSel" value="${stuInfo.classId}">
                        <input type="text" readonly value="${stuInfo.classText}">
                        <input type="hidden" name="classId" value="${stuInfo.classId}">
                    </td>
                    <td >毕业学校</td>
                    <td colspan="4"><input type="text" name="graduateFrom" value="${stuInfo.graduateFrom}"></td>
                </tr>
                <tr>
                    <td colspan="2">户籍所在省<b style="color: #ff0000"></b></td>
                    <td colspan="2" id="provinceSel"  value="${stuInfo.registryProvinceCode}"></td>
                    <td>户籍性质<b style="color: #ff0000"></b></td>
                    <td colspan="2" id="registryTypeSel"  value="${stuInfo.registryType}">
                    </td>
                </tr>
                <tr>
                    <td colspan="2">户籍所在市<b style="color: #ff0000"></b></td>
                    <td colspan="2" id="citySel"  value="${stuInfo.registryCityCode}"></td>
                    <td>籍贯</td>
                    <td colspan="2"><input type="text" name="nativePlace" max="15"  value="${stuInfo.nativePlace}"></td>
                </tr>
                <tr>
                    <td colspan="2">户籍所在区县<b style="color: #ff0000"></b></td>
                    <td colspan="2" id="districtSel"  value="${stuInfo.registryDistrictCode}"></td>
                    <td>港澳台侨胞<b style="color: #ff0000"></b></td>
                    <td colspan="2">
                        <c:if test="${stuInfo.fromHmt == '1'}">
                            <label>是</label>
                            <input name="fromHmt" type="radio" value="1" checked class="">
                            <label>否</label>
                            <input name="fromHmt" type="radio" value="0"  class="">
                        </c:if>
                        <c:if test="${stuInfo.fromHmt != '1'}">
                            <label>是</label>
                            <input name="fromHmt" type="radio" value="1"  class="">
                            <label>否</label>
                            <input name="fromHmt" type="radio" value="0" checked class="">
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">户籍详细地址</td>
                    <td colspan="5"><input type="text" name="registryAddr" value="${stuInfo.registryAddr}"></td>
                </tr>
                <tr>
                    <td colspan="2">现家庭住址（通讯地址）</td>
                    <td colspan="5"><input type="text" name="homeAddr" value="${stuInfo.homeAddr}"></td>
                </tr>
                <tr>
                    <td colspan="2">是否低保<b style="color: #ff0000"></b></td>
                    <td colspan="2">
                        <c:if test="${stuInfo.basicAllowances == '1'}">
                            <label>是</label><input name="basicAllowances" value="1" type="radio" checked class="">
                            <label>否</label><input name="basicAllowances" value="0" type="radio"  class="">
                        </c:if>
                        <c:if test="${stuInfo.basicAllowances != '1'}">
                            <label>是</label><input name="basicAllowances" value="1" type="radio" class="">
                            <label>否</label><input name="basicAllowances" value="0" type="radio" checked class="">
                        </c:if>
                    </td>
                    <td>是否转入户口<b style="color: #ff0000"></b></td>
                    <td colspan="2">
                        <c:if test="${stuInfo.transRegistry == '1'}">
                            <label>是</label><input name="transRegistry" value="1" type="radio" class="">
                            <label>否</label><input name="transRegistry" value="0" type="radio" checked class="">
                        </c:if>
                        <c:if test="${stuInfo.transRegistry != '1'}">
                            <label>是</label><input name="transRegistry" value="1" type="radio" checked class="">
                            <label>否</label><input name="transRegistry" value="0" type="radio"  class="">
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">是否享受国家助学金<b style="color: #ff0000"></b></td>
                    <td colspan="2">
                        <c:if test="${stuInfo.eduGrant == '1'}">
                            <label>是</label><input name="eduGrant" value="1" type="radio" checked class="">
                            <label>否</label><input name="eduGrant" value="0" type="radio"  class="">
                        </c:if>
                        <c:if test="${stuInfo.eduGrant != '1'}">
                            <label>是</label><input name="eduGrant" value="1" type="radio" class="">
                            <label>否</label><input name="eduGrant" value="0" type="radio" checked class="">
                        </c:if>
                    </td>
                    <td>校内学号</td>
                    <td colspan="2"><input type="text" name="stuNumber"  value="${stuInfo.stuNumber}" maxlength="32" class="validate[required]"></td>
                </tr>
                <tr>
                    <td colspan="2">助学金月发放标准（元）</td>
                    <td colspan="2"><input type="text" name="eduGrantAmount"  value="${stuInfo.eduGrantAmount}" class="validate[custom[number]]"></td>
                    <td>银行卡号</td>
                    <td colspan="2"><input type="text" name="bankAccounts"  value="${stuInfo.bankAccounts}" maxlength="32"></td>
                </tr>
                <tr>
                    <td colspan="2">学生类型<b style="color: #ff0000"></b></td>
                    <td colspan="2" id="stuTypeSel"  value="${stuInfo.type}"></td>
                    <td>学习形式<b style="color: #ff0000"></b></td>
                    <td colspan="2" id="studyTypeSel"  value="${stuInfo.learnType}"></td>
                </tr>
                <tr>
                    <td colspan="2">招生类型<b style="color: #ff0000"></b></td>
                    <td colspan="2" id="recruitTypeSel"  value="${stuInfo.recruitType}"></td>
                    <td>教学点名称<b style="color: #ff0000">*</b></td>
                    <td colspan="2" id="teachPlaceSel"  value="${stuInfo.teachPlace}"></td>
                </tr>
                <tr>
                    <td colspan="2">生源类别<b style="color: #ff0000"></b></td>
                    <td colspan="5" id="studentFromSel"  value="${stuInfo.studentFrom}"></td>
                </tr>
                <tr>
                    <td colspan="2">学生类别<b style="color: #ff0000"></b></td>
                    <td colspan="2" id="stuSortSel"  value="${stuInfo.studentSort}"></td>
                    <td>省级电子注册学籍号</td>
                    <td colspan="2"><input type="text" name="officialRegCode" maxlength="32"  value="${stuInfo.officialRegCode}"></td>
                </tr>
                <tr>
                    <td colspan="2">家长(监护人)姓名</td>
                    <td colspan="2"><input type="text" name="parentsName" maxlength="16"  value="${stuInfo.parentsName}"></td>
                    <td>家长(监护人)电话</td>
                    <td colspan="2"><input type="text" name="parentsPhone" class="validate[custom[mobilPhone]]"   value="${stuInfo.parentsPhone}"></td>
                </tr>
                <tr>
                    <td colspan="2">父亲姓名</td>
                    <td colspan="2"><input type="text" name="faName" maxlength="16"  value="${stuInfo.faName}"></td>
                    <td>联系方式</td>
                    <td colspan="2"><input type="text" name="faPhone" class="validate[custom[mobilPhone]]"  value="${stuInfo.faPhone}"></td>
                </tr>
                <tr>
                    <td colspan="2">父亲工作单位</td>
                    <td colspan="5"><input type="text" name="faWork" maxlength="25" value="${stuInfo.faWork}"></td>
                </tr>
                <tr>
                    <td colspan="2">母亲姓名</td>
                    <td colspan="2"><input type="text" name="maName" maxlength="16"  value="${stuInfo.maName}"></td>
                    <td>联系方式</td>
                    <td colspan="2"><input type="text" name="maPhone" class="validate[custom[mobilPhone]]"  value="${stuInfo.maPhone}"></td>
                </tr>
                <tr>
                    <td colspan="2">母亲工作单位</td>
                    <td colspan="5"><input type="text" name="maWork" maxlength="25"  value="${stuInfo.maWork}"></td>
                </tr>
                <tr>
                    <td>备注</td>
                    <td colspan="6"><input type="text" name="remarks"   value="${stuInfo.remarks}"></td>
                </tr>
            </table>
            <div class="clear"></div>
            <div class="data_row clear">
                <div class="data_seach" id="subBtn"><a>提交</a></div>
                <div class="data_back" id="closeBtn"><a>关闭</a></div>
            </div>
            <div class="clear"></div>
        </div>

    </form>
    <!--数据显示区域 end-->
    <script type="text/javascript" src="${contextpath}/plugins/webuploader/webuploader.js?rand=${jsRand}"></script>
    <script type="text/javascript" src="${contextpath}/js/modules/arc/student/imgUploader.js?rand=${jsRand}"></script>
    <script type="text/javascript" src="${contextpath}/js/modules/arc/student/arcInfo4Tch.js?rand=${jsRand}"></script>
    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
