/**
 * Created by dengxianzhi on 2017/1/29.
 */
$(function(){

    // 表单验证
    $("#dataForm").validationEngine("attach", {
        promptPosition : "topLeft",
        autoHidePrompt : true,
        autoHideDelay : 3000,
        scroll : false
    });


    $('.periodSel').mysel({
        url : 'common/doGetPeriodItems.infc',
        text : '学段',
        name : 'period',
        id : 'period',
        isRequired : true,
        valueKey : 'code',
        textKey : 'text'
    });

    //学期选择
    $('.termSel').mysel({
        url : 'common/doGetTermItems.infc',
        text : '学期',
        name : 'term',
        isRequired : true
    });
    //年级选择
    $('.gradeSel').mysel({
        url : 'common/getGradeItems.infc',
        text : '年级',
        name : '',
        isRequired : true,
        valueKey : 'code',
        textKey : 'text',
        id : 'garde',
        callbackFun:function(){
        }
    });

    ///////////响应下拉框选择
    $('#period').change(function(){
        var period= $("#period").find("option:selected").val();
        //年级选择
        $('.gradeSel').empty();
        $('.gradeSel').mysel({
            url : 'common/getGradeItemsByPeriod.infc',
            text : '年级',
            name : 'grade',
            params : {"period":period},
            isRequired : true,
            valueKey : 'code',
            textKey : 'text',
            id : 'garde'
        });
    });

    $('.statusSel').mysel({
        url : 'common/doGetUserStatus.infc',
        text : '状态',
        name : 'status',
        isRequired : true,
        valueKey : 'code',
        textKey : 'text'
    });


    /**
     * 修改
     */
    $("#subBtn").click(function(){
        var bol = $("#dataForm").validationEngine("validate");
        if (bol) {
            var params = $('#dataForm').getValue();
            $.loadingBox.show();
            $.ajaxConnSend(this, 'schedule/doModScheduleInfo.infc', params, function(data) {
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

    $('#backBtn').click(function(){
        window.location.href = "../tch/schedule/tchSchedule.html";
    });
    $('#closeBtn').click(function(){
        self.opener=null;
        self.close();
    });
});