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

    $('.fatherMajorSel').mysel({
        url : 'major/getFatherMajorItems.infc',
        text : '所属专业',
        params:{type:"1"},
        name : 'pId',
        isRequired : false
    });

    $('.statusSel').mysel({
        url : 'common/doGetUserStatus.infc',
        text : '状态',
        name : 'status',
        isRequired : true
    });

    ///////////响应下拉框选择
    $('#majorType').change(function(){
        var type= $("#majorType").find("option:selected").val();
        if(type=="1"){
            $(".fatherMajorSel").hide();
        }else{
            $(".fatherMajorSel").show();
        }
    });


    /**
     * 新增
     */
    $("#addBtn").click(function(){
        var bol = $("#dataForm").validationEngine("validate");
        if (bol) {
            var params = $('#dataForm').getValue();
            $.loadingBox.show();
            $.ajaxConnSend(this, 'major/doAddMajor.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('新增成功!');
                    window.location.href = $.customOpt.url+"sys/major/sysMajor.html";
                } else {
                    $.alert_error('新增失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }
    });
    $('#backBtn').click(function(){
        window.location.href = $.customOpt.url+"sys/major/sysMajor.html";
    });

});