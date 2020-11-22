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
        window.location.href = BAS_URL+'sysScoreRule/scoreRuleConfig.do';
    });

    $('#modBtn').click(function(){
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

            //if(totalVal > 100){
            //    $.alert_error("占比总合超出100!");
            //    return;
            //}else if(totalVal < 100){
            //    $.alert_error("占比总合未到100!");
            //    return;
            //}

            $.loadingBox.show();
            $.ajaxConnSend(this, 'sysScoreRule/modScoreRule.infc',params, function(data) {
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

    $('.scopeSel').mysel({
        url : 'common/getScopeSelItems.infc',
        text : '适用范围',
        name : 'gradeScopeId',
        isRequired : true
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