/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){


    //table
    var table = $('.dataTable').mytable({
        isCheck: true,
        idKeyName:'id',
        header: [{
            code: 'name',
            text: '名称'
        }, {
            code: 'schoolYear',
            text: '学年'
        }, {
            code: 'gradeName',
            text: '年级'
        }, {
            code: 'termText',
            text: '学期'
        },{
            code: 'statusText',
            text: '状态'
        }],
        url: 'schedule/doGetSchedulePageData.infc',
        modFun:function(id) {
            if(typeof(winOpen)=="object"){
                winOpen.close();             //关闭之前打开的窗口
            }
            winOpen=window.open(BAS_URL+"/schedule/initEdit.do?id="+id,'scheduleModForm');
        },
        changeStatusFun:function(id,status) {
            // console.debug(data+"|||"+j);
            $.loadingBox.show();
            $.ajaxConnSend(this, 'schedule/doModScheduleInfo.infc', {
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

    function stopClassinfo(id){
        $.ajaxConnSend(this, 'class/doStopClassinfo.infc', {
            id: id
        }, function(data) {
            if (data.status == '1' && data.object) {
                $.alert_success('停用成功');
                table.refreshData();
            } else {
                $.alert_error('停用失败');
            }
        },function(){
            $.loadingBox.close();
        })
    }
    function activeClassinfo(id){
        $.ajaxConnSend(this, 'class/doActiveClassinfo.infc', {
            id: id
        }, function(data) {
            if (data.status == '1' && data.object) {
                $.alert_success('启用成功');
                table.refreshData();
            } else {
                $.alert_error('启用失败');
            }
        },function(){
            $.loadingBox.close();
        })
    }

    /**
     * 搜索
     */
    $('#queryBtn').click(function(){
        var params = $('#queryForm').getValue();
        table.refreshData(params);
    });

    $('#relMod').click(function(){
        var items=table.getChecked();
        if(items.length==1){
            window.location.href = BAS_URL+"teachingPlan/initEditForTchplanSubjectRel.do?id="+items[0].id;
        }else{
            $.alert_error('请选中一个教学计划');
        }
    });


    /**
     * 新增
     */
    $('#addBtn').click(function(){
         window.location.href = $.customOpt.url +'/tch/schedule/scheduleAddForm.html';
    });
    $('#editBtn').click(function(){
        var items=table.getChecked();
        if(items.length==1){
            window.location.href = $.customOpt.url +'/tch/schedule/scheduleEdit.html?id='+items[0].id;
        }else{
            $.alert_error('请选中一个教学计划');
        }


    });

});