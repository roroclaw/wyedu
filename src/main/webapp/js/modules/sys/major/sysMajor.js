/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){

    $('.fatherMajorSel').mysel({
        url : 'major/getFatherMajorItems.infc',
        text : '专业',
        name : 'pId',
        params:{type:"0"},
        isRequired : false,
        callbackFun: function(data){

        }
    });
    //table
     var table = $('.dataTable').mytable({

     isCheck: true,
     idKeyName:'id',
     header: [{
     code: 'name',
     text: '名称'
     }, {
     code: 'typeText',
     text: '类型'
     }, {
     code: 'statusText',
     text: '状态'
     }],
     url: 'major/doGetMajorPageData.infc',
     modFun:function(id) {
     if(typeof(winOpen)=="object"){
     winOpen.close();             //关闭之前打开的窗口
     }
     winOpen=window.open( BAS_URL+"/major/initEdit.do?id="+id,'subjectModForm');
     },
     /*delFun: function(id) {
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
    changeStatusFun:function(id,status) {
        // console.debug(data+"|||"+j);
        $.loadingBox.show();
        $.ajaxConnSend(this, 'major/doModMajorInfo.infc', {
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


    /**
     * 搜索
     */
    $('#queryBtn').click(function(){
        var params = $('#queryForm').getValue();
        table.refreshData(params);
    });

    /**
     * 新增
     */
    $('#addBtn').click(function(){
         window.location.href = $.customOpt.url +'/sys/major/majorAddForm.html';
    });

});

