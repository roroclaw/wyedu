/**
 * Created by dengxianzhi on 2017/1/29.
 */
$(function(){
    var gradu=0;
    // 表单验证
    $("#dataForm").validationEngine("attach", {
        promptPosition : "topLeft",
        autoHidePrompt : true,
        autoHideDelay : 3000,
        scroll : false
    });

    /**
     * 年级选择
     */
    var grade = $("#gradeSel").attr("param");
    if (grade=="12" || grade==12){
        gradu=1;
    }else{
        grade=parseInt(grade)+1;
        $('#gradeSel').attr('defValue', grade);
    }
    $('#gradu').val(gradu);
//alert(grade);
    if(gradu==1){
        /**
         * 状态选择
         */
        //初始化状态信息
        var classInfoStatusSel = $('.classInfoStatusSel').mysel({
            url : 'common/doGetClassInfoStatus.infc',
            text : '状态',
            name : 'status',
            isRequired : true
        });
        $("#statusSel").show();
    }else{
        //初始化年级信息
        var gradeSel = $('#gradeSel').mysel({
            url : 'common/getGradeItems.infc',
            text : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年级',
            name : 'grade',
            isRequired : true,
            defValue:grade
        });
        $("#gradeSel").show();
    }






    /**
     * 新增
     */
    $("#subBtn").click(function(){
        var bol = $("#dataForm").validationEngine("validate");
        if (bol) {
            var seq= $('#seq').val();
            var params = $('#dataForm').getValue();
            var newClassName = gradeNumToGradeName(params["grade"]);
            params["name"]=newClassName+"("+seq+")班";
            params["seq"]=seq;
            $.loadingBox.show();
            $.ajaxConnSend(this, 'class/doIncreaseGrade.infc', params, function(data) {
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
        window.location.href = "../tch/class/tchClass.html";
    });

});