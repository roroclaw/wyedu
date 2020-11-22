/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){
   var table = $('.dataTable').mytable({
        isCheck: true,
        _recordsPerpage:1,
        idKeyName:'id',
        header: [{
            code: 'name',
            text: '部门名称'
        },{
            code: 'statusText',
            text: '状态'
        }],
        url: 'department/doGetDepartsPageDataByFatherId.infc',
        params:{P_ID:'0000'},
       modFun:function(id) {
           if(typeof(winOpen)=="object"){
               winOpen.close();             //关闭之前打开的窗口
           }
           winOpen=window.open( BAS_URL+"department/initEdit.do?id="+id,'departmentModForm');
       },
       changeStatusFun:function(id,status) {
           // console.debug(data+"|||"+j);
           $.loadingBox.show();
           $.ajaxConnSend(this, 'department/doUpdateDepartInfo.infc', {
               id:id,status:status
           }, function(data) {
               if (data.status == '1' && data.object) {
                   $.alert_success('操作成功');
                   table.refreshData();
               } else {
                   $.alert_error('操作失败');
               }
           },function(){
               $.loadingBox.close();
           })
       }
    });




$("#navigation_browser").on("click","a",function() {
    var param = $(this).attr("param");
    table.refreshData({P_ID:param, callbackFun:function(){
    }});
});

    /**
     * 新增
     */
    $('#addBtn').click(function(){
        window.location.href = $.customOpt.url +'/sys/department/departmentAddForm.html';
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


});



/**
 * 左边树形目录
 */

function makeLeftTreeView(){
    $.ajaxConnSend(this, 'department/doGetDepartsList.infc', {}, function (data) {
        var obj = data['object'];
        var temp_level=-1;
        var leftTreeViewHtml='';
        for(var i=0;i<obj.length;i++){
            if (obj[i].level < temp_level) {
                leftTreeViewHtml = leftTreeViewHtml + '</li>';
                for (var j = 0; j < temp_level - obj[i].level; j++) {
                    leftTreeViewHtml = leftTreeViewHtml+ '</ul></li>';
                }
            }
            else if(obj[i].level > temp_level){

                leftTreeViewHtml = leftTreeViewHtml + '<ul>';
            }
            else{
                leftTreeViewHtml = leftTreeViewHtml + '</li>';
            }

            leftTreeViewHtml = leftTreeViewHtml +'<li><span class="folder"><a  param="'+obj[i].id+'" >'+obj[i].name+'</a></span>';


            temp_level=obj[i].level;
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

