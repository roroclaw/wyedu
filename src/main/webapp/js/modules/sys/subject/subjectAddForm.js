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

    //下拉框设置
    createSel($('#periodSel'),'period','common/doGetPeriodItems.infc',true);
    createSel($('#typeSel'),'type','common/doGetSubjectTypeItems.infc',true);
    createSel($('#status_select_add'),'status','common/doGetUserStatus.infc',true);


    /**
     * 新增
     */
    $("#addBtn").click(function(){
        var bol = $("#dataForm").validationEngine("validate");
        if (bol) {
            var params = $('#dataForm').getValue();
            $.loadingBox.show();
            $.ajaxConnSend(this, 'subject/doAddSubject.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('新增成功!');
                    window.location.href = $.customOpt.url+"sys/subject/sysSubject.html";
                } else {
                    $.alert_error('新增失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }
    });
    $('#backBtn').click(function(){
        window.location.href = $.customOpt.url+"sys/subject/sysSubject.html";
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