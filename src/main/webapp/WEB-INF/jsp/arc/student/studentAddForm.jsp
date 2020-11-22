<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <a href="###">学生管理</a><i>>></i><a href="###">学生信息管理</a><i>>></i><a href="###">学籍登记</a>
    </div>
    <!--系统地图 end-->

    <!--数据显示区域 star-->
    <form id="dataForm" action="">
        <div class="UI_data UI_bd">
            <table style="width:100%">
                <tr>
                    <td style="width:55px;">编号<b style="color: #ff0000"></b></td>
                    <td style="width:160px;"><input type="text" class="" name="docId"></td>
                    <td style="width:100px;">姓名<b style="color: #ff0000">*</b></td>
                    <td><input type="text" class="validate[required]" name="realName"></td>
                    <td style="width:110px;">出生日期<b style="color: #ff0000">*</b></td>
                    <td><input type="text" name="birth" readonly="true" class="validate[required]"
                               onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"></td>
                    <td rowspan="5" style="width:150px;" class="useimg">
                        <img id="stuImg" src="${contextpath}/images/useimg.png"/>
                        <input id="imgUrl" name="imgUrl" class="validate[required]" type="hidden"/>
                        <div class="btns" >
                            <div id="filePicker">选择照片</div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>性别<b style="color: #ff0000">*</b></td>
                    <td id="sexSel">
                    </td>
                    <td>曾用名</td>
                    <td><input type="text" name="usedName"></td>
                    <td>身份证号<b style="color: #ff0000">*</b></td>
                    <td><input type="text" name="identityCard" class="validate[required]"></td>
                </tr>
                <tr>
                    <td>年级<b style="color: #ff0000">*</b></td>
                    <td id="gradeSel">
                    </td>
                    <td>民族<b style="color: #ff0000">*</b></td>
                    <td id="nationSel"></td>
                    <td>手机号码<b style="color: #ff0000"></b></td>
                    <td><input type="text" name="phone" class="validate[custom[mobilPhone]]"></td>
                </tr>
                <tr>
                    <td>学制<b style="color: #ff0000">*</b></td>
                    <td id="eduTypeSel">
                    </td>
                    <td>修读方向<b style="color: #ff0000">*</b></td>
                    <td id="majorSel">

                    </td>
                    <td>教学计划<b style="color: #ff0000">*</b></td>
                    <td id="tchPlanSel">

                    </td>
                </tr>
                <tr>
                    <td>政治面貌</td>
                    <td id="politicalStatusSel"></td>
                    <td>入学年月<b style="color: #ff0000">*</b></td>
                    <td><input type="text" name="enrolDate" readonly="true" onclick="laydate({istime: true, format: 'YYYY-MM'})" class="validate[required]"></td>
                    <td>邮政编码</td>
                    <td><input type="text" name="postCode" maxlength="15"></td>
                </tr>
                <tr>
                    <td >班级<b style="color: #ff0000">*</b></td>
                    <td id="classSel"></td>
                    <td >毕业学校</td>
                    <td colspan="4"><input type="text" name="graduateFrom"></td>
                </tr>
                <tr>
                    <td colspan="2">户籍所在省<b style="color: #ff0000"></b></td>
                    <td colspan="2" id="provinceSel"></td>
                    <td>户籍性质<b style="color: #ff0000"></b></td>
                    <td colspan="2" id="registryTypeSel">
                    </td>
                </tr>
                <tr>
                    <td colspan="2">户籍所在市<b style="color: #ff0000"></b></td>
                    <td colspan="2" id="citySel"></td>
                    <td>籍贯</td>
                    <td colspan="2"><input type="text" name="nativePlace" max="15"></td>
                </tr>
                <tr>
                    <td colspan="2">户籍所在区县<b style="color: #ff0000"></b></td>
                    <td colspan="2" id="districtSel"></td>
                    <td>港澳台侨胞<b style="color: #ff0000"></b></td>
                    <td colspan="2">
                        <label>是</label><input name="fromHmt" type="radio" value="1" class="">
                        <label>否</label><input name="fromHmt" type="radio" value="0" checked class="">
                    </td>
                </tr>
                <tr>
                    <td colspan="2">户籍详细地址</td>
                    <td colspan="5"><input type="text" name="registryAddr"></td>
                </tr>
                <tr>
                    <td colspan="2">现家庭住址（通讯地址）</td>
                    <td colspan="5"><input type="text" name="homeAddr"></td>
                </tr>
                <tr>
                    <td colspan="2">是否低保<b style="color: #ff0000"></b></td>
                    <td colspan="2">
                        <label>是</label><input name="basicAllowances" value="1" type="radio" class="">
                        <label>否</label><input name="basicAllowances" value="0" type="radio" checked class="">
                    </td>
                    <td>是否转入户口<b style="color: #ff0000"></b></td>
                    <td colspan="2">
                        <label>是</label><input name="transRegistry" value="1" type="radio" class="">
                        <label>否</label><input name="transRegistry" value="0" type="radio" checked class="">
                    </td>
                </tr>
                <tr>
                    <td colspan="2">是否享受国家助学金<b style="color: #ff0000"></b></td>
                    <td colspan="2">
                        <label>是</label><input name="eduGrant" value="1" type="radio" class="">
                        <label>否</label><input name="eduGrant" value="0" type="radio" checked class="">
                    </td>
                    <td>校内学号<b style="color: #ff0000">*</b></td>
                    <td colspan="2"><input type="text" name="stuNumber" maxlength="32" class="validate[required]"></td>
                </tr>
                <tr>
                    <td colspan="2">助学金月发放标准（元）</td>
                    <td colspan="2"><input type="text" name="eduGrantAmount" class="validate[custom[number]]"></td>
                    <td>银行卡号</td>
                    <td colspan="2"><input type="text" name="bankAccounts" maxlength="32"></td>
                </tr>
                <tr>
                    <td colspan="2">学生类型<b style="color: #ff0000"></b></td>
                    <td colspan="2" id="stuTypeSel"></td>
                    <td>学习形式<b style="color: #ff0000"></b></td>
                    <td colspan="2" id="studyTypeSel"></td>
                </tr>
                <tr>
                    <td colspan="2">招生类型<b style="color: #ff0000"></b></td>
                    <td colspan="2" id="recruitTypeSel"></td>
                    <td>教学点名称<b style="color: #ff0000"></b></td>
                    <td colspan="2" id="teachPlaceSel"></td>
                </tr>
                <tr>
                    <td colspan="2">生源类别<b style="color: #ff0000"></b></td>
                    <td colspan="5" id="studentFromSel"></td>
                </tr>
                <tr>
                    <td colspan="2">学生类别<b style="color: #ff0000"></b></td>
                    <td colspan="2" id="stuSortSel"></td>
                    <td>省级电子注册学籍号</td>
                    <td colspan="2"><input type="text" name="officialRegCode" maxlength="32"></td>
                </tr>
                <tr>
                    <td colspan="2">家长(监护人)姓名</td>
                    <td colspan="2"><input type="text" name="parentsName" maxlength="16"></td>
                    <td>家长(监护人)电话</td>
                    <td colspan="2"><input type="text" name="parentsPhone" class="validate[custom[mobilPhone]]"></td>
                </tr>
                <tr>
                    <td colspan="2">父亲姓名</td>
                    <td colspan="2"><input type="text" name="faName" maxlength="16"></td>
                    <td>联系方式</td>
                    <td colspan="2"><input type="text" name="faPhone" class="validate[custom[mobilPhone]]"></td>
                </tr>
                <tr>
                    <td colspan="2">父亲工作单位</td>
                    <td colspan="5"><input type="text" name="faWork" maxlength="25"></td>
                </tr>
                <tr>
                    <td colspan="2">母亲姓名</td>
                    <td colspan="2"><input type="text" name="maName" maxlength="16"></td>
                    <td>联系方式</td>
                    <td colspan="2"><input type="text" name="maPhone" class="validate[custom[mobilPhone]]"></td>
                </tr>
                <tr>
                    <td colspan="2">母亲工作单位</td>
                    <td colspan="5"><input type="text" name="maWork" maxlength="25"></td>
                </tr>
                <tr>
                    <td>备注</td>
                    <td colspan="6"><input type="text" name="remarks"></td>
                    <%--<td>家长签名</td>--%>
                    <%--<td><input type="text"></td>--%>
                </tr>
            </table>
            <div class="clear"></div>
            <div class="data_row clear">
                <div class="data_seach" id="addBtn"><a>提交</a></div>
            </div>
            <div class="clear"></div>
        </div>

    </form>
    <!--数据显示区域 end-->
    <script type="text/javascript" src="${contextpath}/plugins/webuploader/webuploader.js?rand=${jsRand}"></script>
    <script type="text/javascript" src="${contextpath}/js/modules/arc/student/imgUploader.js?rand=${jsRand}"></script>
    <script type="text/javascript" src="${contextpath}/js/modules/arc/student/studentAddForm.js?rand=20171016"></script>
    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
