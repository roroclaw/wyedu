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

    /**
     * 科目选择
     */
    $.genrateOptionsByItems('#subjectSel','subject/getSubjectInfoItems.infc',true);

    /**
     * 学年选择
     */
    $.genrateOptionsByItems('#schoolYearSel','common/getFromToEduYearItems.infc',false);

    /**
     * 获取学员筛选信息
     */
    var options = {
        serviceUrl: BAS_URL+'/student/getStuInfoByKey.infc',//获取数据的后台页面
        name:'stuId'
        ,width: 233//提示框的宽度
        ,type:'POST'
        ,onSelectedFun:function(item){
            var obj = item['object'];
            var className = obj['className'];
            $('#className').val(className);
        }
        ,triggerSelectOnValidInput:true
    };

    $('#stuNameSel').autoInput(options);

    /**
     * 新增
     */
    $("#subBtn").click(function(){
        var bol = $("#dataForm").validationEngine("validate");
        if (bol) {
            var params = $('#dataForm').getValue();
            $.loadingBox.show();
            $.ajaxConnSend(this, 'subjectScores/doModSubjectScore.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('修改成功!');
                    window.location.href = "../sco/score/subjectScore.html";
                } else {
                    $.alert_error('修改失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }
    });

    $('#backBtn').click(function(){
        window.location.href = "../sco/score/subjectScore.html";
    });

});