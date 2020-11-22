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
    createSel($('#sexSel'),'sex','common/getSexItems.infc',true);
    createSel($('#teacherTypeSel'),'type','common/doGetTeacherTypeItems.infc',true);
    createSel($('#politicalStatusSel'),'politicalStatus','common/getPoliticalStatusItems.infc',false);
    createSel($('#nationSel'),'nation','common/getNationItems.infc',false);
    createSel($('#majorSel_1'),'majorId','common/getMajorItems.infc',false);
    createSel($('#majorSel_2'),'teachMajorId','common/getMajorItems.infc',false);
    createSel($('#titleSel'),'proTitle','common/doGetTeacherTitleItems.infc',false);
    createSel($('#postSel'),'post','common/doGetTeacherPostItems.infc',false);
    createSel($('#educationSel'),'education','common/doGetEducationItems.infc',false);
    createSel($('#degreeSel'),'degree','common/doGetDegreeItems.infc',false);

    /**
     * 部门选择
     */
    $.ajaxConnSend(this, 'department/doGetDepartsList.infc', {status:'1'}, function (data) {
        var fatherObj = data['object'];
        var temp_level=1;
        var selectHtml='<div class="data_input"><div class="select_box noborder"><select  class="validate[required]"  name="departId">';
        for(x in fatherObj){
            //x表示是下标，来指定变量，指定的变量可以是数组元素，也可以是对象的属性。
            selectHtml=selectHtml+'<option value="'+fatherObj[x].id+'">';
            for(var i=1;i<fatherObj[x].level;i++){
                selectHtml=selectHtml+'│&nbsp;&nbsp;';
            }
            selectHtml=selectHtml+'├─'+fatherObj[x].name+'</option>';
            temp_level=fatherObj[x].level;
        }
        selectHtml=selectHtml+'</select></div></div>';
        $("#depart_select_add").empty();
        $("#depart_select_add").append(selectHtml);

    });

    /**
     * 新增
     */
    $("#addBtn").click(function(){
        var bol = $("#dataForm").validationEngine("validate");
        if (bol) {
            var params = $('#dataForm').getValue();
            $.loadingBox.show();
            $.ajaxConnSend(this, 'teacher/doAddTeacher.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('新增成功!');
                    window.location.href ="../teacher/sysTeacher.html";
                } else {
                    $.alert_error('新增失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }
    });

    $('#backBtn').click(function(){
        window.location.href = "../teacher/sysTeacher.html";
    });

});


/**
 * 构造本页面的下拉框控件
 * @param obj
 * @param url
 */
function createSel(obj,name,url,isRequired){
    var optionHtml = '<div class="data_input">';
    optionHtml += '<div class="select_box noborder">';
    if(isRequired){
        optionHtml += '<select id="'+name+'" name="'+name+'" class="validate[required]">';
    }else{
        optionHtml += '<select id="'+name+'" name="'+name+'">';
    }
    optionHtml += '<option value ="">--请选择--</option>';
    $.ajaxConnSend(this, url,null, function(data) {
        var items = data['object'];
        for(var i = 0; i < items.length; i++){
            var item = items[i];
            optionHtml += '<option value ="'+item['code']+'">'+item['text']+'</option>';
        }
        if(isRequired){
            optionHtml += '</select></div>';
        }else{
            optionHtml += '</select></div>';
        }
        obj.append(optionHtml);
    });

}