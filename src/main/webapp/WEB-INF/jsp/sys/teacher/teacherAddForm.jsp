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
        <a href="###">教师管理</a><i>>></i><a href="###">教师信息管理</a><i>>></i><a href="###">新增教师信息</a>
    </div>
    <!--系统地图 end-->

    <!--数据显示区域 star-->
    <form id="dataForm" action="">
        <div class="UI_data UI_bd">
            <table style="width:100%">
                <tr>
                    <td style="width:100px;">工号<b style="color: #ff0000">*</b></td>
                    <td style="width:160px;"><div class="data_input"><input type="text" class="validate[required]" name="code"></div></td>
                    <td style="width:100px;">真实姓名<b style="color: #ff0000">*</b></td>
                    <td><input type="text" class="validate[required]" name="name"></td>
                    <td style="width:110px;">出生日期</td>
                    <td><input type="text" name="birth" readonly="true" class=""
                               onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"></td>
                </tr>
                <tr>
                    <td>籍贯</td>
                    <td ><input type="text" name="nativePlace" class="" >
                    </td>
                    <td>教师类型<b style="color: #ff0000">*</b></td>
                    <td  id="teacherTypeSel"></td>
                    <td>年龄</td>
                    <td><input type="text" name="age" class="validate[custom[integer]]"></td>
                </tr>
                <tr>
                    <td>性别<b style="color: #ff0000">*</b></td>
                    <td id="sexSel"></td>
                    <td>姓名拼音<b style="color: #ff0000">*</b></td>
                    <td><input type="text" name="namePy" class="validate[required]" ></td>
                    <td>身份证号</td>
                    <td><input type="text" name="identityCard" class="validate[funcCall[idCardValidate]]"></td>
                </tr>
                <tr>
                    <td >入党日期</td>
                    <td style="width:160px;"><input type="text" name="inPartyDate" readonly="true" class=""
                                                    onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"></td>
                    <td>政治面貌</td>
                    <td id="politicalStatusSel"></td>
                    <td ></td>
                    <td></td>
                </tr>
                <tr>
                    <td>所属部门<b style="color: #ff0000">*</b></td>
                    <td id="depart_select_add">
                    </td>
                    <td>民族</td>
                    <td id="nationSel"></td>
                    <td>手机号码<b style="color: #ff0000">*</b></td>
                    <td><input type="text" name="phone" class="validate[required,custom[mobilPhone]]"></td>
                </tr>
                <tr>
                    <td>所学专业</td>
                    <td><input type="text" name="studyMajor" class=""></td>
                    <td>授课专业</td>
                    <td id="majorSel_2">
                    </td>
                    <td>院系</td>
                    <td><input type="text" name="colleg" class="" ></td>
                    </td>
                </tr>
                <tr>
                    <td>职称</td>
                    <td id="titleSel">
                    </td>
                    <td>职务</td>
                    <td id="postSel">
                    </td>
                    <td>专业特长</td>
                    <td ><input type="text" name="specialty">
                    </td>
                </tr>
                <tr>
                    <td>本科毕业时间</td>
                    <td ><div class="data_input"><input type="text" name="bachDate" class="" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"/></div>
                       </td>
                    <td>本科毕业院校</td>
                    <td> <input type="text" name="bachSchool" class="" /></td>
                    <td>本科毕业方向</td>
                    <td> <input type="text" name="bachMajor" class="" /></td>
                </tr>
                <tr>
                    <td>硕士毕业时间</td>
                    <td ><input type="text" name="masterDate" class="" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"/>
                    </td>
                    <td>硕士毕业院校</td>
                    <td> <input type="text" name="masterSchool" class=""   /></td>
                    <td>硕士毕业方向</td>
                    <td> <input type="text" name="masterMajor" class=""   /></td>
                </tr>
                <tr>
                    <td>博士毕业时间</td>
                    <td ><input type="text" name="doctorDate" class="" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"/>
                    </td>
                    <td>博士毕业院校</td>
                    <td> <input type="text" name="doctorSchool" class=""   /></td>
                    <td>博士毕业方向</td>
                    <td> <input type="text" name="doctorMajor" class=""   /></td>
                </tr>
                <tr>
                    <td>学位</td>
                    <td id="degreeSel">
                    </td>
                    <td>学历</td>
                    <td id="educationSel">
                    </td>
                    <td>教龄</td>
                    <td ><input type="text" name="beTeacherYears">
                    </td>
                </tr>
                <tr>
                    <td>任职日期</td>
                    <td ><input type="text" name="beTeacherDate" class=""   onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"/>
                    </td>
                    <td>参加工作日期</td>
                    <td ><input type="text" name="inworkDate" class=""   onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"/>
                    </td>
                    <td>附中入职日期</td>
                    <td ><input type="text" name="inschoolDate" class=""   onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"/>
                    </td>
                </tr>
                <tr>
                    <td>主讲课程</td>
                    <td ><input type="text" name="mainCourse" class=""/>
                    </td>
                    <td>是否双肩挑<b style="color: #ff0000">*</b></td>
                    <td><label>是</label><input name="doubleDuty" type="radio" value="1" class="validate[required]">
                        <label>否</label><input name="doubleDuty" type="radio" value="0" checked class="validate[required]"></td>
                    <td>博士毕业方向</td>
                    <td> <input type="text" name="doctorMajor" class=""   /></td>
                </tr>
                <tr>
                    <td>配偶姓名</td>
                    <td ><input type="text" name="spouseName" class=""/>
                    </td>
                    <td>配偶电话</td>
                    <td><input type="text" name="spousePhone" class=""/>
                    </td>
                    <td></td>
                    <td> </td>
                </tr>
            </table>
            <div class="clear"></div>
            <div class="data_row clear">
                <div class="data_seach" id="addBtn">提交</div>
                <div class="data_back" id="backBtn">返回</div>
            </div>
            <div class="clear"></div>
        </div>
    </form>



    </div>
    <script type="text/javascript" src="${contextpath}/js/modules/sys/teacher/teacherAddForm.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
