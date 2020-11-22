<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>武汉音乐学院</title>
    <jsp:include page="../../common/common_top.jsp"/>
    <script type="text/javascript" charset="utf-8" src="${contextpath}/plugins/UEditor/ueditor.config-2.js"></script>
    <script type="text/javascript" charset="utf-8" src="${contextpath}/plugins/UEditor/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${contextpath}/plugins/UEditor/lang/zh-cn/zh-cn.js"> </script>
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
        <a href="###">教务信息管理</a><i>>></i><a href="###">信息管理</a>
    </div>
    <!--系统地图 end-->

    <!--数据显示区域 star-->
    <div id="divRowPart_1">

            <div class="UI_data">
                <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>文章栏目</span></div>
                <form id="queryForm">
                    <div class="data_box clear">
                        <div class="data_input">
                            <span>文章标题：</span>
                            <input type="text" name="title" class="">
                        </div>

                        <div class="data_input folder_select_add" id="folder_select_add">
                            <span>所属栏目：</span>
                        </div>

                        <div class="data_input classSel hidden_div">
                        </div>
                        <div class="data_input statusSel" >
                        </div>
                        <div class="data_seach" id="queryBtn" style="cursor:pointer">搜索</div>
                    </div>
                </form>
                <div class="UI_table data_box">
                    <div  id="leftTreeViewContent">
                        <ul id="navigation_browser" class="filetree" >
                            <!-- Footer goes here -->
                        </ul>
                    </div>
                </div>
                <!--数据显示区域 end-->
            </div>
            <div class="UI_data">
                <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>文章列表</span></div>
                <div class="UI_table data_box dataTable">
                </div>
                <!--数据显示区域 end-->
            </div>
        <div class="clear"></div>
    </div>
    <div id="divRowPart_2" class="hidden_div">
        <div class="col-md-12">
            <div class="UI_data UIfrom">
                <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>文章修改</span></div>
                <div class="data_Form clear">
                    <form id="editArticleForm">
                        <input type="hidden" class="form-control validate[required]"  name="article_id" id="article_id" readonly>

                            <div class="data_input">
                                <span>文章标题：</span>
                                <input type="text" name="title" class="validate[required]"><b>*</b>
                            </div>

                            <div class="data_input">
                                <span>所属栏目：</span>
                                <div class="select_box" id="folder_select_edit">
                                </div>
                            </div>


                            <div class="clear"></div>

                            <div class="data_input">
                                <span>正文：</span>
                                <div >
                                    <script id="content_input" name="content" type="text/plain"
                                            style="width: 100%; height: 260px;"></script>
                                    <script type="text/javascript">
                                        UE.getEditor('content_input', {
                                            // toolbars:[['FullScreen', 'Source', 'Undo', 'Redo','Bold']],//这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
                                            lang:"zh-cn",
                                            toolbars: [[
                                                'fullscreen', 'source', '|', 'undo', 'redo', '|',
                                                'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
                                                'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
                                                'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
                                                'directionalityltr', 'directionalityrtl', 'indent', '|',
                                                'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
                                                'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
                                                'simpleupload', '|',
                                                //'insertimage', 'insertvideo',  'attachment', '|',
                                                'horizontal', 'date', 'time', '|',
                                                // , 'spechars','snapscreen', 'wordimage', '|',
                                                'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',
                                                'print', 'preview', 'searchreplace', 'help', 'drafts'
                                            ]]
                                        });
                                    </script>
                                </div>
                            </div>

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

                            <div class="data_input">
                                <span>标题：</span>
                                <input type="text" name="title" class="">
                            </div>


                            <div class="data_input">
                                <span>所属栏目：</span>
                                <input type="text" name="father_name" id="father_name" readonly>
                            </div>
                            <div class="data_input classSel hidden_div">
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
        <%-- <div class="oper_btn" id="addBtn" style="cursor:pointer" param="00000000">新增</div>
         <div class="oper_btn" id="modBtn" style="cursor:pointer">修改</div>--%>
    </div>
    <!--内容数据end-->
    <script type="text/javascript" src="${contextpath}/js/modules/cms/articleManager.js"></script>
    <script type="text/javascript" src="${contextpath}/js/common/cmsCommon.js"></script>
    <jsp:include page="../../common/basic.jsp"/>





</body>
</html>
