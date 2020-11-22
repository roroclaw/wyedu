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

    //初始化教室类型
    $('.typeSel').mysel({
        url : 'common/doGetClassroomTypeItems.infc',
        text : '教室类型',
        name : 'type',
        isRequired : true
    });

    $('.buildingSel').mysel({
        url : 'common/getbuildingNoItems.infc',
        text : '&nbsp;&nbsp;&nbsp;教学楼',
        name : 'buildingNo',
        isRequired : true
    });


    $('.statusSel').mysel({
        url : 'common/doGetUserStatus.infc',
        text : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状&nbsp;&nbsp;态',
        name : 'status',
        isRequired : false
    });
    /**
     * 新增
     */
    $("#addBtn").click(function(){
        var bol = $("#dataForm").validationEngine("validate");
        if (bol) {
            var params = $('#dataForm').getValue();
            $.loadingBox.show();
            $.ajaxConnSend(this, 'classroom/doAddClassroom.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('新增成功!');
                    window.location.href = "../classroom/sysClassroom.html";
                } else {
                    $.alert_error('新增失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }
    });
    $('#backBtn').click(function(){
        window.location.href = "../classroom/sysClassroom.html";
    });

    /**
     * 构造本页面的下拉框控件
     * @param obj
     * @param url
     */
    function createSel(obj,name,url,isRequired){
        var optionHtml = '';
        optionHtml += '<div class="select_box noborder">';
        if(isRequired){
            optionHtml += '<select id="'+name+'" name="'+name+'" class="validate[required]">';
        }else{
            optionHtml += '<select>';
        }
        optionHtml += '<option value ="">--请选择--</option>';
        $.ajaxConnSend(this, url,null, function(data) {
            var items = data['object'];
            for(var i = 0; i < items.length; i++){
                var item = items[i];
                optionHtml += '<option value ="'+item['code']+'">'+item['text']+'</option>';
            }
            if(isRequired){
                optionHtml += '</select></div><i>*</i>';
            }else{
                optionHtml += '</select></div>';
            }

            obj.append(optionHtml);
        });

    }
});