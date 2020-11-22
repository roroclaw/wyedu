<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>武汉音乐学院</title>
    <jsp:include page="../../common/common_top.jsp"/>
    <link type="text/css" rel="stylesheet" href="${contextpath}/css/jquery.treeview.css"/>
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
        <a href="###">内容管理</a><i>>></i><a href="###">栏目管理</a>
    </div>
    <!--系统地图 end-->

    <!--数据显示区域 star-->
    <div id="divRowPart_1">
        <div class="col-md-4">
        <div class="UI_data">
            <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>栏目</span></div>
            <div class="UI_table data_box">
                <div  id="leftTreeViewContent">
                    <ul id="navigation_browser" class="filetree" >
                        <!-- Footer goes here -->
                    </ul>
                </div>
            </div>
            <!--数据显示区域 end-->
        </div>
        </div>
        <div class="col-md-8">
        <div class="UI_data">
            <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>栏目列表</span></div>
            <div class="UI_table data_box dataTable">
            </div>
            <!--数据显示区域 end-->
        </div>
        </div>
        <div class="clear"></div>
    </div>
    <div id="divRowPart_2" class="hidden_div">
        <div class="col-md-12">
            <div class="UI_data data_box">
                <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>栏目修改</span></div>
                <div class="data_Form clear">
                    <form id="editFolderForm">
                        <input type="hidden" id="folder_id" name="folder_id">

                            <div class="data_input">
                                <span>栏目名称：</span>
                                <input type="text" name="folder_name" class="validate[required]"><b>*</b>
                            </div>
                        <div class="clear"></div>

                            <div class="data_input">
                                <span>所属栏目：</span>
                                <div class="select_box" id="father_select_edit">
                                </div>
                            </div>
                        <div class="clear"></div>

                            <div class="data_input">
                                <span>排序：</span>
                                <input type="text" name="folder_sequence" class="validate[required]">
                            </div>
                        <div class="clear"></div>

                            <div class="data_input">
                                <span>状态：</span>
                                <div class="select_box">
                                    <select  class="form-control" name="status" class="validate[required]">
                                        <option value="1" selected>正常</option>
                                        <option value="2">停用</option>
                                    </select>
                                </div>
                            </div>
                        <div class="clear"></div>
                        <div class="form_operbar clear">
                            <div class="oper_btn_tijiao" id="doEditBtn" style="cursor:pointer">提交</div>
                            <div class="oper_btn_fanhui" style="cursor:pointer">返回</div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div id="divRowPart_3" class="hidden_div">
        <div class="col-md-12">
            <div class="UI_data data_box">
                <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>新增栏目</span></div>
                <div class="data_Form clear">
                    <form id="addFolderForm">
                        <input type="hidden" id="father_id" name="father_id">
                        <div class="form_row">
                            <div class="data_input">
                                <span>栏目名称：</span>
                                <input type="text" name="folder_name" class="validate[required]"><b>*</b>
                            </div>
                        </div>
                        <div class="form_row">
                            <div class="data_input">
                                <span>所属栏目：</span>
                                <input type="text" name="father_name" id="father_name" readonly>
                            </div>
                        </div>
                        <div class="form_row">
                            <div class="data_input">
                                <span>排序：</span>
                                <input type="text" name="folder_sequence" class="validate[required]">
                            </div>
                        </div>
                        <div class="form_row">
                            <div class="data_input" id="status_select_add">
                                <span>状态：</span>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <div class="form_operbar clear">
                            <div class="oper_btn_tijiao" id="doAddBtn" style="cursor:pointer">提交</div>
                            <div class="oper_btn_fanhui"  style="cursor:pointer">返回</div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!--table按钮-->
    <div class="operBar">
        <div class="oper_btn" id="addBtn" style="cursor:pointer" param="00000000">新增</div>
        <%--<div class="oper_btn" id="modBtn" style="cursor:pointer">修改</div>--%>
    </div>
    <!--内容数据end-->
    <script type="text/javascript" src="${contextpath}/js/modules/cms/folderManager.js"></script>
    <script type="text/javascript" src="${contextpath}/plugins/jquery.treeview.js"></script>
    <script type="text/javascript" src="${contextpath}/js/common/cmsCommon.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
    <script type="text/javascript">

        makeLeftTreeView();
    </script>




</body>
</html>
