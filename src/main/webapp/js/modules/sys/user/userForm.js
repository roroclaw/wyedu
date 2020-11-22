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
    //    for(var i =0 ; i < items.length;i++){
    //        var item = items[i];
    //        itemsHtml += '<option value="'+item['code']+'">'+item['text']+'</option>';
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
     * 角色选择
     */
    $.ajaxConnSend(this, 'role/doGetRolesList.infc', {}, function (data) {
        var items = data['object'];
        var itemsHtml = '';
        for(var i =0 ; i < items.length;i++){
            var item = items[i];
            //itemsHtml = '<option value="'+item['id']+'">'+item['roleName']+'</option>';
            itemsHtml += '<li class="roleItem" data-val="'+item['id']+'">'+item['roleName']+'</li>';
        }
        $('#roleSel').append(itemsHtml);
    });

    /**
     * 部门选择
     */
    //$.ajaxConnSend(this, 'department/doGetDepartsList.infc', {}, function (data) {
    //    var fatherObj = data['object'];
    //    var temp_level=1;
    //    var itemHtml='';
    //    for(x in fatherObj){
    //        //x表示是下标，来指定变量，指定的变量可以是数组元素，也可以是对象的属性。
    //        itemHtml += '<option value="'+fatherObj[x].id+'">';
    //        for(var i=1;i<fatherObj[x].level;i++){
    //            itemHtml += '│&nbsp;&nbsp;';
    //        }
    //        itemHtml +='├─'+fatherObj[x].name+'</option>';
    //        temp_level = fatherObj[x].level;
    //    }
    //    $('#deptSel').append(itemHtml);
    //});

    /**
     * 新增
     */
    $("#addBtn").click(function(){
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
            if(params.encryptPass != params.passVerify){
                $.alert_warn('两次输入的密码不一样');
                return false;
            }
            $.loadingBox.show();
            $.ajaxConnSend(this, 'user/doAddUser.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('新增成功!');
                    window.location.href = "sysUser.html";
                } else {
                    $.alert_error('新增失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }
    });

    $('#backBtn').click(function(){
        window.location.href = "sysUser.html";
    });

    /**角色点击效果**/
    $(document).on('click','.roleItem',function(){
        var $this = $(this);
        $this.toggleClass('cur');
    });
});