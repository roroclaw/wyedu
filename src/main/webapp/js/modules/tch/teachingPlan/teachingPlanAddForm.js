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

    $('.majorSel').mysel({
        url : 'major/getFatherMajorItems.infc',
        text : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;专&nbsp;&nbsp;&nbsp;&nbsp;业',
        name : 'majorId',
        params:{type:"0"},
        isRequired : true
    });

    //下拉框设置
    createSel($('#periodSel'),'period','common/doGetPeriodItems.infc',true);
    createSel($('#eduTypeSel'),'eduType','common/getEduTypeItems.infc',true);
    createSchoolYearSel($('#enrolYearSel'),'enrolYear',true);
    createSel($('#status_select_add'),'status','common/doGetUserStatus.infc',true);

    /**
     * 新增
     */
    $("#addBtn").click(function(){
        var bol = $("#dataForm").validationEngine("validate");
        if (bol) {
            var params = $('#dataForm').getValue();
            $.loadingBox.show();
            $.ajaxConnSend(this, 'teachingPlan/doAddTeachingPlan.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('新增成功!');
                    window.location.href = "../teachingPlan/tchTeachingPlan.html";
                } else {
                    $.alert_error('新增失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }
    });
    $('#backBtn').click(function(){
        window.location.href = "../teachingPlan/tchTeachingPlan.html";
    });
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

/**
 * 构造本页面的学年下拉框控件
 * @param obj
 * @param url
 */
function createSchoolYearSel(obj,name,isRequired){
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
            optionHtml += '<option value ="'+items[i]+'">'+items[i]+'-'+(parseInt(items[i])+1)+'</option>';
        }
        if(isRequired){
            optionHtml += '</select></div><i>*</i>';
        }else{
            optionHtml += '</select></div>';
        }

        obj.append(optionHtml);

    });

}