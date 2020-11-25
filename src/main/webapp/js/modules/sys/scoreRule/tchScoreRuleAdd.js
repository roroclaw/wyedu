/**
 * Created by roroclaw on 2017/11/11.
 */
$(function(){
    // 表单验证
    $("#dataForm").validationEngine("attach", {
        promptPosition : "topLeft",
        autoHidePrompt : true,
        autoHideDelay : 3000,
        scroll : false
    });

    $('#backBtn').click(function(){
        window.location.href = BAS_URL+'sysScoreRule/tchScoreRuleConfig.do';
    });

    $('#addBtn').click(function(){
        var bol = $("#dataForm").validationEngine("validate");
        if (bol) {
            //验证参数比例合法性
            var params = $('#dataForm').getValue();
            var totalVal = 0;
            var ratios = $('.ratioInput');
            for(var i = 0; i < ratios.length;i++){
                var item = ratios[i];
                var text = $(item).data("text");
                var val = Number($(item).val());
                if(val < 0 || val > 100){
                    $.alert_error(text+"输入非法!");
                    return false;
                }else{
                    totalVal += val;
                }
            }

            $.loadingBox.show();
            $.ajaxConnSend(this, 'sysScoreRule/addTchScoreRule.infc',params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('新增成功!');
                    window.location.href = BAS_URL+'sysScoreRule/tchScoreRuleConfig.do';
                }
            }, function() {
                $.loadingBox.close();
            });
        }
    });

    $('.subjectSel').mysel({
        url : 'subject/doGetSubjectListsByParam.infc',
        text : '科&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目',
        name : 'subjectId',
        valueKey : 'id',
        textKey : 'name',
        isRequired : true
    });

});