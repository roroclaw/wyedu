/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){
    //下拉框设置
    createSel($('#status_select_add'),'status','common/doGetUserInfoStatus.infc',true);

    var table = $('.dataTable').mytable({
        isCheck: true,
        _recordsPerpage:1,
        idKeyName:'folder_id',
        header: [{
            code: 'folder_name',
            text: '栏目名称'
        },{
            code: 'statusText',
            text: '状态'
        }],
        url: 'folder/doGetFoldersPageDataByFatherId.infc',
        params:{father_id:'00000000'},
        callbackFun:function(rowdatas){
            $("#addBtn").attr("param",rowdatas.father_id);
        },

        /*    delFun: function(id) {
         $.alert_confirm('确定删除此记录?', function() {
         $.loadingBox.show();
         $.ajaxConnSend(this, 'user/doDelUserById.infc', {
         id: id
         }, function(data) {
         if (data.status == '1' && data.object) {
         $.alert_success('删除成功');
         table.refreshData();
         } else {
         $.alert_error('删除失败');
         }
         },function(){
         $.loadingBox.close();
         })
         });
         },*/
        modFunByAuthor:function(id) {
            //  $.loadingBox.show();
            /*  $.ajaxConnSend(this, 'folder/doDelUserById.infc', {
             id: id
             }, function(data) {
             if (data.status == '1' && data.object) {
             $.alert_success('删除成功');
             table.refreshData();
             } else {
             $.alert_error('删除失败');
             }
             },function(){
             $.loadingBox.close();
             })*/


            $.ajaxConnSend(this, 'folder/doGetFolderInfosByID.infc', {folder_id:id}, function (data) {
                var FolderObj = data['object'];
                $('#editFolderForm').setValue(FolderObj);
                $.ajaxConnSend(this, 'folder/doGetFoldersList.infc', {}, function (data) {
                    var fatherObj = data['object'];
                    var temp_level=1;
                    var selectHtml='<select  class="" name="father_id">';
                    selectHtml=selectHtml+'<option value="'+FolderObj.father_id+'"selected>'+FolderObj.father_name+'</option>';
                    for(x in fatherObj){
                        //x表示是下标，来指定变量，指定的变量可以是数组元素，也可以是对象的属性。
                        selectHtml=selectHtml+'<option value="'+fatherObj[x].folder_id+'">';
                        for(var i=0;i<fatherObj[x].folder_level;i++){
                            selectHtml=selectHtml+'│&nbsp;&nbsp;';
                        }
                        selectHtml=selectHtml+'├─'+fatherObj[x].folder_name+'</option>';
                        temp_level=fatherObj[x].folder_level;
                    }
                    selectHtml=selectHtml+'</select>';
                    $("#father_select_edit").empty();
                    $("#father_select_edit").append(selectHtml);

                });
                changeDivShow("_2",3);
            });

        }

    });




$("#navigation_browser").on("click","a",function() {
    var param = $(this).attr("param");
    table.refreshData({father_id:param, callbackFun:function(){
        $("#addBtn").attr("param",param);
    }});
});

    /**
     * 新增
     */
    $("#doAddBtn").click(function(){
        var bol = $("#addFolderForm").validationEngine("validate");
        if (bol) {
            var params = $('#addFolderForm').getValue();
            $.loadingBox.show();
            $.ajaxConnSend(this, 'folder/doAddFolderInfo.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('新增成功!');
                    changeDivShow("_1",3);
                    // var table = $('.dataTable').mytable();

                    var curPage= parseInt($('.curPage').text());
                    table.refreshData({curPage:curPage},"refresh");
                } else {
                    $.alert_error('新增失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }
    });

    /**
     * 修改
     */
    $("#doEditBtn").click(function(){
        var bol = $("#editFolderForm").validationEngine("validate");
        if (bol) {
            var params = $('#editFolderForm').getValue();
            $.loadingBox.show();
            $.ajaxConnSend(this, 'folder/doUpdateFolderInfo.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('修改成功!');
                    changeDivShow("_1",3);
                   // var table = $('.dataTable').mytable();

                    var curPage= parseInt($('.curPage').text());
                   table.refreshData({curPage:curPage},"refresh");
                } else {
                    $.alert_error('修改失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }
    });

    /**
     * 开始新增
     */
    $("#addBtn").click(function(){
        var param = $(this).attr("param");
        $.ajaxConnSend(this, 'folder/doGetFolderInfosByID.infc', {folder_id:param}, function (data) {
            var FolderObj = data['object'];
            $('#father_id').val(FolderObj.folder_id);
            $('#father_name').val(FolderObj.folder_name);
            changeDivShow("_3",3);
        });

    });

    $('.oper_btn_fanhui').click(function(){
        changeDivShow("_1",3);
    });

});



/**
 * 左边树形目录
 */

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


/**
 * 构造本页面的下拉框控件
 * @param obj
 * @param url
 */
function createSel(obj,name,url,isRequired){
    var optionHtml = '';
    optionHtml += '<div class="select_box">';
    if(isRequired){
        optionHtml += '<select id="'+name+'" name="'+name+'" class="validate[required]">';
    }else{
        optionHtml += '<select>';
    }
    optionHtml += '<option value ="">--请选择--</option>';
    $.ajaxConnSend(this, url,null, function(data) {
        var items = data['object'];
        for(var i = 0; i < items.length; i++){
            var item = items[i];
            optionHtml += '<option value ="'+item['code']+'">'+item['text']+'</option>';
        }
        if(isRequired){
            optionHtml += '</select></div><i>*</i>';
        }else{
            optionHtml += '</select></div>';
        }
        obj.append(optionHtml);
    });

}