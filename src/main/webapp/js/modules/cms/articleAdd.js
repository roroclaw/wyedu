/**
 * Created by zl on 2017/1/26.
 */
$(function(){
    /**
$("#navigation_browser").on("click","a",function() {
    var param = $(this).attr("param");
    var folderName=$(this).html();
  //  console.debug(folderName);
    $("#addArticleForm").find("#folder_id").val(param);
    $("#addArticleForm").find("#folder_name").val(folderName);
});
    */



    $.ajaxConnSend(this, 'folder/doGetFoldersListByRoleIdForAddArti.infc', {}, function (data) {
        var Obj = data['object'];
        var temp_level=1;
        var selectHtml='<span>所属栏目：</span><div class="select_box"><select  class="form-control" name="folder_id" id="folderSel" class="validate[required]">';
        selectHtml=selectHtml+'<option value="">--请选择--</option>';
        for(x in Obj ){

            //x表示是下标，来指定变量，指定的变量可以是数组元素，也可以是对象的属性。
            selectHtml=selectHtml+'<option value="'+Obj[x].folder_id+'">';
            for(var i=1;i<Obj[x].folder_level;i++){
                //selectHtml=selectHtml+'│&nbsp;&nbsp;';
            }
            //selectHtml=selectHtml+'├─'+Obj[x].folder_name+'</option>';
            selectHtml=selectHtml+Obj[x].folder_name+'</option>';
            temp_level=Obj[x].folder_level;
        }
        selectHtml=selectHtml+'</select></div>';
        $("#folder_select_add").empty();
        $("#folder_select_add").append(selectHtml);
    });

    ///////////响应下拉框选择
    $('.folder_select_add').change(function(){
        var folderId= $("#folderSel").find("option:selected").val();
        if(folderId=='2131455968989343029'){  /////学生公告
            $('.classSel').empty();
            $('.classSel').mysel({
                url : 'article/getClassInfoItemsByPowerForArtiAdd.infc',
                text : '对应班级',
                name : 'keyWord',
                isRequired : true,
                valueKey : 'code',
                textKey : 'text'
            });
            $('.classSel').show();
        }else{
            $('.classSel').empty();
            $('.classSel').hide();
        }
    });

    /**
     * 新增
     */
    $("#doAddBtn").click(function(){
        var bol = $("#addArticleForm").validationEngine("validate");
        if (bol) {
            var params = $('#addArticleForm').getValue();
            $.loadingBox.show();
            $.ajaxConnSend(this, 'article/doAddArticleInfo.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('新增成功!');
                } else {
                    $.alert_error('新增失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }
    });


});



/**
 * 左边树形目录
 */
/**
function makeLeftTreeView(){
    $.ajaxConnSend(this, 'folder/doGetFoldersList.infc', {}, function (data) {
        var obj = data['object'];
        var temp_level=-1;
        var leftTreeViewHtml='';
        for(var i=0;i<obj.length;i++){
            if (obj[i].folder_level < temp_level) {
                leftTreeViewHtml = leftTreeViewHtml + '</li>';
                for (var j = 0; j < temp_level - obj[i].folder_level; j++) {
                    leftTreeViewHtml = leftTreeViewHtml+ '</ul></li>';
                }
            }
            else if(obj[i].folder_level > temp_level){

                leftTreeViewHtml = leftTreeViewHtml + '<ul>';
            }
            else{
                leftTreeViewHtml = leftTreeViewHtml + '</li>';
            }

            leftTreeViewHtml = leftTreeViewHtml +'<li><span class="folder"><a  param="'+obj[i].folder_id+'" >'+obj[i].folder_name+'</a></span>';


            temp_level=obj[i].folder_level;
        }
        leftTreeViewHtml = leftTreeViewHtml +' </li></ul>';
        $("#navigation_browser").empty();
        $("#navigation_browser").append(leftTreeViewHtml);
        $("#navigation_browser").treeview({
            persist: "location",
            collapsed: true,
            unique: true
        });
    });
}
*/

