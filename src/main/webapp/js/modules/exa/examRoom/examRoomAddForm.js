/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){


    //初始化类型
    $('.typeSel').mysel({
        url : 'common/doGetExamType.infc',
        text : '考试类型',
        name : 'type',
        isRequired : false
    });

    //table
    var table = $('.dataTable').mytable({
        isCheck: true,
        idKeyName:'id',
        header: [{
            code: 'name',
            text: '名称'
        }, {
            code: 'examTypeText',
            text: '类型'
        }, {
            code: 'updateTime',
            text: '更新时间'
        },  {
            code: 'statusText',
            text: '状态'
        }],
        url: 'exam/doGetExamPlanPageData.infc'

    });
    ///////////响应下拉框选择
    $('.typeSel').change(function(){
        var params = $('#queryForm').getValue();
        table.refreshData(params);
    });

    //下拉框设置
    $('.buildingSel').mysel({
        url : 'common/getbuildingNoItems.infc',
        text : '&nbsp;教&nbsp;学&nbsp;楼',
        name : 'buildingNo',
        isRequired : true,
        id:'buildingNo'
    });

    ///////////响应下拉框选择
    $('.buildingSel').change(function(){
        var buildingNo= $("#buildingNo").find("option:selected").val();
        $('.classRoomSel').empty();
        $('.classRoomSel').mysel({
            url : 'classroom/getClassroomItems.infc',
            text : '使用教室',
            name : 'classroomId',
            params : {"buildingNo":buildingNo,"status":'1'},
            isRequired : true,
            valueKey : 'code',
            textKey : 'text'
        });
    });

    /**
     * 新增
     */
    $('#addBtn').click(function(){
        var bol = $("#addForm").validationEngine("validate");
        var items=table.getChecked();
        if(items.length==1){
            if (bol) {
                var params = $('#addForm').getValue();
                params["examPlanId"]=items[0].id;
                $.loadingBox.show();
                $.ajaxConnSend(this, 'examRoom/doAddExamRoom.infc', params, function(data) {
                    if (data.status == '1' && data.object) {
                        $.alert_success('新增成功!');
                        window.location.href = "../examRoom/exaExamRoom.html";
                    } else {
                        $.alert_error('新增失败');
                    }
                }, function() {
                    $.loadingBox.close();
                });
            }
        }else{
            $.alert_error('请选中一个考试计划');
        }
    });

    $('#backBtn').click(function(){
        window.location.href = "../examRoom/exaExamRoom.html";
    });


});
