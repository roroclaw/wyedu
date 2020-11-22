/**
 * Created by dengxianzhi on 2017/1/29.
 */
$(function(){
    $(document).on('click', '#exitBtn', function(e) {
        window.location.href = $.customOpt.url+'user/loginOut.do'
    });

    $('.admin_name').hover(function() {
        $(".admin_role").show();
    }, function(){
        $(".admin_role").hide();
    });

    // 表单验证
    $("#modPwdForm").validationEngine("attach", {
        promptPosition : "topLeft",
        autoHidePrompt : true,
        autoHideDelay : 3000,
        scroll : false
    });

    /**
     * 密码修改
     */
    $('#modpwd').click(function(){
        var showbox = $.showPopupForm('#modPwdForm',function(){
            var bol = $("#modPwdForm").validationEngine("validate");
            if (bol) {
                var formData = $("#modPwdForm").getValue();
                // 确认新密码
                var newPwd = formData['newPwd'];
                var confirmPwd = formData['confirmPwd'];
                if(newPwd != confirmPwd){
                    $.alert_error("两次密码输入不一致!");
                    return false;
                }
                $.loadingBox.show();
                $.ajaxConnSend(this, 'user/doModPwd.infc',formData, function(data) {
                    $.alert_success("密码修改成功,退出后生效!");
                    showbox.close();
                },function(){
                    $.loadingBox.close();
                })
            }
        });
        $(this).hide();
    });

});