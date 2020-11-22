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

    ///**
    // * 用户类型选择
    // */
    //$.ajaxConnSend(this, 'common/getUserTypeItems.infc', {}, function (data) {
    //    var items = data['object'];
    //    var itemsHtml = '';
    //    var defalutVal = $('#userTypeSel').attr('defValue');
    //    for(var i =0 ; i < items.length;i++){
    //        var item = items[i];
    //        var code = item['code'] ;
    //        if(defalutVal == code){
    //            itemsHtml += '<option value="'+code+'" selected>'+item['text']+'</option>';
    //        }else{
    //            itemsHtml += '<option value="'+code+'">'+item['text']+'</option>';
    //        }
    //    }
    //    $('#userTypeSel').append(itemsHtml);
    //});

    //初始化用户类型
    $('.userTypeSel').mysel({
        url : 'common/getUserTypeItems.infc',
        text : '用户类型',
        name : 'type',
        isRequired : true
    });


    /**
     * 修改
     */
    $("#subBtn").click(function(){
        var bol = $("#dataForm").validationEngine("validate");
        if (bol) {
            var params = $('#dataForm').getValue();
            var selectedRoles = $('.roleItem').filter('.cur');
            var roleStr = '';
            if(selectedRoles.length > 0){
                for(var i=0;i < selectedRoles.length;i++){
                    var roleItem = selectedRoles[i];
                    var roleId = $(roleItem).data('val');
                    roleStr += roleId+'|';
                }
            }else{
                $.alert_warn('请选择角色');
                return false;
            }
            params['roleIds'] = roleStr;
            //if(params.encryptPass != params.passVerify){
            //    $.alert_warn('两次输入的密码不一样');
            //    return false;
            //}
            $.loadingBox.show();
            $.ajaxConnSend(this, 'user/doModUser.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('修改成功!');
                    window.location.href = $.customOpt.url + "/sys/user/sysUser.html";
                } else {
                    $.alert_error('修改失败!');
                }
            }, function() {
                $.loadingBox.close();
            });
        }
    });

    $('#backBtn').click(function(){
        window.location.href = $.customOpt.url + "/sys/user/sysUser.html";
    });

    /**角色点击效果**/
    $(document).on('click','.roleItem',function(){
        var $this = $(this);
        $this.toggleClass('cur');
    });
});
