/**
 * Created by dxz on 2017/6/20.
 */
$(function(){
    var subjectId=$('.subjectSel').attr("defValue");
    // 表单验证
    $("#dataForm").validationEngine("attach", {
        promptPosition : "topLeft",
        autoHidePrompt : true,
        autoHideDelay : 3000,
        scroll : false
    });
    //初始化
    $('.planSel').mysel({
        url : 'exam/getExamPlanItems.infc',
        text : '考试计划',
        name : 'examPlanId',
        isRequired : true
    });

    $('.subjectTypeSel').mysel({
        url : 'common/doGetSubjectTypeItems.infc',
        text : '科目类型',
        name : '',
        isRequired : false,
        id:'subjectType'
    });


    ///////////响应下拉框选择
    $('.subjectTypeSel').change(function(){
        var type= $("#subjectType").find("option:selected").val();
        $('.subjectSel').empty();
        $('.subjectSel').mysel({
            url : 'subject/getSubjectInfoItems.infc',
            text : '考试科目',
            name : 'subjectId',
            params : {"type":type,"status":'1'},
            isRequired : true,
            valueKey : 'code',
            textKey : 'text'
        });
    });
    $('.subjectSel').mysel({
        url : 'subject/getSubjectInfoItems.infc',
        text : '考试科目',
        name : 'subjectId',
        params : {},
        isRequired : true,
        valueKey : 'code',
        textKey : 'text'
    });
    /**
     * 修改
     */
    $("#addBtn").click(function(){
        var bol = $("#dataForm").validationEngine("validate");
        var params = $('#dataForm').getValue();
        if (bol) {
            if(subjectId!=params.subjectId){  ////////////修改了考试的科目
                $.loadingBox.show();
                $.ajaxConnSend(this, 'exam/doCheckExamForUseddoCheckExamForUsed.infc', params, function(data) {
                    var items = data['object'];
                    //console.log(items);
                    if(items){
                        $.alert_confirm('该考试已经被安排考生，修改其考试科目将导致相关数据被清除！',function(){
                            UpdateExamInfo(params);
                        });
                    }else{
                        UpdateExamInfo(params);
                    }
                }, function() {
                    $.loadingBox.close();
                });
            }else{
                UpdateExamInfo(params);
            }
        }
    });

    function UpdateExamInfo(params){
        $.loadingBox.show();
        $.ajaxConnSend(this, 'exam/doModExamInfo.infc', params, function(data) {
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

    $('#backBtn').click(function(){
        window.location.href = "../sys/subject/sysSubject.html";
    });
    $('#closeBtn').click(function(){
        self.opener=null;
        self.close();
    });

});

/**
 * 构造本页面的下拉框控件
 * @param obj
 * @param url
 */
function createSel(obj,name,url,isRequired){
    var defaultVal = obj.attr('value');
    var optionHtml = '';
    optionHtml += '<div class="select_box noborder">';
    if(isRequired){
        optionHtml += '<select id="'+name+'" name="'+name+'" class="validate[required]">';
    }else{
        optionHtml += '<select>';        }
    optionHtml += '<option value ="">--请选择--</option>';
    $.ajaxConnSend(this, url,null, function(data) {
        var items = data['object'];
        for(var i = 0; i < items.length; i++){
            var item = items[i];
            var isChkStr = '';
            if($.trim(defaultVal) != '' && item['code'] == defaultVal){
                isChkStr = 'selected ';
            }
            optionHtml += '<option value ="'+item['code']+'" '+isChkStr+'>'+item['text']+'</option>';
        }
        if(isRequired){
            optionHtml += '</select></div><i>*</i>';
        }else{
            optionHtml += '</select></div>';
        }
        obj.append(optionHtml);
    });
}