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
        isRequired : true,
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


    /** $('.statusSel').mysel({
        url : 'common/doGetExamStatus.infc',
        text : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状&nbsp;&nbsp;态',
        name : 'status',
        isRequired : false
    }); */
    /**
     * 新增
     */
    $("#addBtn").click(function(){
        var bol = $("#dataForm").validationEngine("validate");
        if (bol) {
            var params = $('#dataForm').getValue();
            $.loadingBox.show();
            $.ajaxConnSend(this, 'exam/doAddExamInfo.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('新增成功!');
                   // window.location.href = "../exam/exaExam.html";
                } else {
                    $.alert_error('新增失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }
    });
    $('#backBtn').click(function(){
        window.location.href = "../exam/exaExam.html";
    });


});