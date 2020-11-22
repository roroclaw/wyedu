/**
 * Created by dxz on 2017/6/20.
 */
$(function(){

    // 表单验证
    $("#dataForm").validationEngine("attach", {
        promptPosition : "topLeft",
        autoHidePrompt : true,
        autoHideDelay : 3000,
        scroll : false
    });

    $('.schoolYearSel').mysel({
        url : 'common/getSchoolYearItems.infc',
        text : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学&nbsp;&nbsp;年',
        name : 'schoolYear',
        isRequired : false
    });

    $('.termSel').mysel({
        url : 'common/doGetTermItems.infc',
        text : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学&nbsp;&nbsp;期',
        name : 'term',
        isRequired : false
    });

    //初始化类型
    $('.typeSel').mysel({
        url : 'common/doGetExamType.infc',
        text : '考试类型',
        name : 'type',
        isRequired : true
    });

    $('.statusSel').mysel({
        url : 'common/doGetUserStatus.infc',
        text : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状&nbsp;&nbsp;态',
        name : 'status',
        isRequired : false
    });

    /**
     * 修改
     */
    $("#addBtn").click(function(){
        var bol = $("#dataForm").validationEngine("validate");
        if (bol) {
            var params = $('#dataForm').getValue();
            $.loadingBox.show();
            $.ajaxConnSend(this, 'exam/doModExamPlan.infc', params, function(data) {
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
        window.location.href = "../exa/exam/exaExamPlan.html";
    });
    $('#closeBtn').click(function(){
        self.opener=null;
        self.close();
    });

});
