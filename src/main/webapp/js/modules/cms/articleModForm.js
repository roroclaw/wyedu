/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){
    $.ajaxConnSend(this, 'folder/doGetFoldersListByRoleIdForAddArti.infc', {}, function (data) {
        var Obj = data['object'];
        var folderName=$("#folderName").val();
        var folderId=$("#folderId").val();
        var temp_level=1;
        var selectHtml='<span>所属栏目：</span><div class="select_box"><select  class="form-control" name="folder_id" id="folderSel" class="validate[required]">';
        selectHtml=selectHtml+'<option value="'+folderId+'">'+folderName+'</option>';
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
        $("#folder_select").empty();
        $("#folder_select").append(selectHtml);
        if(folderId=='2131455968989343029'){  /////学生公告
            var keyWord=$("#keyWord").val();
            $('.classSel').empty();
            $('.classSel').show();
            $('.classSel').mysel({
                url : 'article/getClassInfoItemsByPowerForArtiAdd.infc',
                text : '对应班级',
                id:'keyWordSel',
                name : 'keyWord',
                isRequired : true,
                valueKey : 'code',
                textKey : 'text'
            });
        }else{
            $('.classSel').empty();
            $('.classSel').hide();
        }
    });

    ///////////响应下拉框选择
    $('.folder_select').change(function(){
        var folderId= $("#folderSel").find("option:selected").val();
        if(folderId=='2131455968989343029'){  /////学生公告
            $('.classSel').empty();
            $('.classSel').mysel({
                url : 'article/getClassInfoItemsByPowerForArtiAdd.infc',
                text : '对应班级',
                id:'keyWordSel',
                name : 'keyWord',
                isRequired : true,
                valueKey : 'code',
                textKey : 'text'
            });
            alert("classSel");
            $('.classSel').show();
        }else{
            $('.classSel').empty();
            $('.classSel').hide();
        }
    });

    /**
     * 新增
     */
    $("#doEditBtn").click(function(){
        var bol = $("#editArticleForm").validationEngine("validate");
        if (bol) {
            var params = $('#editArticleForm').getValue();
            $.loadingBox.show();
            $.ajaxConnSend(this, 'article/doUpdateArticleInfo.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('修改成功!');
                } else {
                    $.alert_error('修改失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }
    });

    $('#closeBtn').click(function(){
        self.opener=null;
        self.close();
    });
});



function test(){
    var keyWord=$("#keyWord").val();
    alert(keyWord);
   //$("#keyWordSel").find("option[text='初一年级(2)班']").attr("selected",true);
   // $("#keyWordSel").val("初一年级(2)班");
    //$("#keyWordSel option[text='"+keyWord+"']").attr("selected", true);
    $("#keyWordSel").prepend("<option value='0'>"+keyWord+"</option>");
}
