/**
 * Created by dxz on 2017/6/20.
 */
$(function(){
     var id=$('#id').val();
    var examPlanId=$('#examPlanId').val();

    // 表单验证
    $("#dataForm").validationEngine("attach", {
        promptPosition : "topLeft",
        autoHidePrompt : true,
        autoHideDelay : 3000,
        scroll : false
    });
/*
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
    })

    $('.classRoomSel').mysel({
        url : 'classroom/getClassroomItems.infc',
        text : '使用教室',
        name : 'classroomId',
        isRequired : true,
        valueKey : 'code',
        textKey : 'text'
    });

*/
    /**
     * 修改
     */
    $("#addBtn").click(function(){
        var bol = $("#dataForm").validationEngine("validate");
        if (bol) {
            var params = $('#dataForm').getValue();
            $.loadingBox.show();
            $.ajaxConnSend(this, 'examRoom/doModExamRoom.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('修改成功!');
                    setTimeout("self.close()",1200);
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
