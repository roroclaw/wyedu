/**
 * Created by dxz on 2017/6/20.
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
    createSel($('#eduTypeSel'),'eduType','common/getEduTypeItems.infc',true);
    createSchoolYearSel($('#enrolYearSel'),'enrolYear',true);
    createSel($('#status_select_add'),'status','common/doGetUserStatus.infc',true);

    /**
     * 修改
     */
    $("#editBtn").click(function(){
        var bol = $("#dataForm").validationEngine("validate");
        if (bol) {
            var params = $('#dataForm').getValue();
            $.loadingBox.show();
            $.ajaxConnSend(this, 'teachingPlan/doModTeachingPlan.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('新增成功!');
                    setTimeout("self.close()",1200);
                } else {
                    $.alert_error('新增失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }
    });

    $('#backBtn').click(function(){
        self.close();
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
/**
 * 构造本页面的学年下拉框控件
 * @param obj
 * @param url
 */
function createSchoolYearSel(obj,name,isRequired){
    var defaultVal = obj.attr('value');
    var optionHtml = '';
    optionHtml += '<div class="select_box noborder">';
    if(isRequired){
        optionHtml += '<select id="'+name+'" name="'+name+'" class="validate[required]">';
    }else{
        optionHtml += '<select>';
    }
    optionHtml += '<option value ="">--请选择--</option>';
    $.ajaxConnSend(this, "common/doGetRecentSchoolYearItems.infc",null, function(data) {
        var items = data['object'];
        for(var i = 0; i < items.length; i++){
            if($.trim(defaultVal) != '' && items[i].substring(0,4) == defaultVal){
                optionHtml += '<option value ="'+items[i]+'" selected>'+items[i]+'-'+(parseInt(items[i])+1)+'</option>';
            }else{
                optionHtml += '<option value ="'+items[i]+'" >'+items[i]+'-'+(parseInt(items[i])+1)+'</option>';
            }
        }
        if(isRequired){
            optionHtml += '</select></div><i>*</i>';
        }else{
            optionHtml += '</select></div>';
        }

        obj.append(optionHtml);

    });

}