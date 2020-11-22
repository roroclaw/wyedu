<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>武汉音乐学院</title>
    <jsp:include page="../../common/common_top.jsp"/>
    <script type="text/javascript" charset="utf-8" src="${contextpath}/plugins/UEditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${contextpath}/plugins/UEditor/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${contextpath}/plugins/UEditor/lang/zh-cn/zh-cn.js"> </script>
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
        <a href="###">教务信息管理</a><i>>></i><a href="###">修改信息</a>
    </div>
    <!--系统地图 end-->
    <input type="hidden"  id="folderId" value="${articleInfo.folder_id}">
    <input type="hidden"  id="folderName" value="${articleInfo.folder_name}">
    <input type="hidden"  id="keyWord" value="${articleInfo.keyWord}">
    <!--数据显示区域 star-->
        <div class="UI_data UIfrom">
            <div class="data_title clear"><i class="iconfont icon-hangbiao"></i><span>修改信息</span></div>
            <div class="data_Form clear">
                <form id="editArticleForm">
                    <input type="hidden" class="form-control validate[required]"  name="article_id" id="article_id" value="${articleInfo.article_id}" readonly>

                    <div class="data_input">
                        <span>文章标题：</span>
                        <input type="text" name="title" class="validate[required]" value="${articleInfo.title}"><b>*</b>
                    </div>

                    <div class="clear"></div>
                    <div class="data_input folder_select" id="folder_select">
                        <span>所属栏目：</span>
                    </div>
                    <div class="clear"></div>
                    <div class="data_input classSel hidden_div" defValue="${articleInfo.keyWord}">
                    </div>
                    <div class="clear"></div>
                    <div class="data_input">
                        <span>正文：</span>
                        <div >
                            <script id="content_input" name="content" type="text/plain"
                                    style="width: 100%; height: 260px;"></script>
                            <script type="text/javascript">
                                var ue = UE.getEditor('content_input', {
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
                                ue.ready(function() {
                                    ue.setContent('${articleInfo.content}');
                                });

                                //editor.setContent('${articleInfo.content}');
                            </script>
                        </div>
                    </div>
                    <div class="form_operbar clear">
                        <div class="oper_btn_tijiao" id="doEditBtn" style="cursor:pointer">提交</div>
                        <div class="oper_btn_fanhui"  id="closeBtn" style="cursor:pointer">返回</div>
                    </div>
                </form>
            </div>
        </div>




    </div>
    <script type="text/javascript" src="${contextpath}/js/modules/cms/articleModForm.js"></script>
    <jsp:include page="../../common/basic.jsp"/>
</body>
</html>
