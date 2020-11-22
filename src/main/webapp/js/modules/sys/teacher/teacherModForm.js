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
    createSel($('#sexSel'),'sex','common/getSexItems.infc',true);
    createSel($('#teacherTypeSel'),'type','common/doGetTeacherTypeItems.infc',true);
    createSel($('#politicalStatusSel'),'politicalStatus','common/getPoliticalStatusItems.infc',false);
    createSel($('#nationSel'),'nation','common/getNationItems.infc',false);
    createSel($('#majorSel_2'),'teachMajorId','common/getMajorItems.infc',false);
    createSel($('#titleSel'),'proTitle','common/doGetTeacherTitleItems.infc',false);
    createSel($('#postSel'),'post','common/doGetTeacherPostItems.infc',false);
    createSel($('#educationSel'),'education','common/doGetEducationItems.infc',false);
    createSel($('#degreeSel'),'degree','common/doGetDegreeItems.infc',false);

    /**
     * 部门选择
     */
    $.ajaxConnSend(this, 'department/doGetDepartsList.infc', {}, function (data) {
        var fatherObj = data['object'];
        var defaultVal = $("#depart_select_add").attr('value');
        var temp_level=1;
        var selectHtml='<div class="data_input"><div class="select_box noborder"><select  class="validate[required]"  name="departId">';
        for(x in fatherObj){
            //x表示是下标，来指定变量，指定的变量可以是数组元素，也可以是对象的属性。
            if(defaultVal==fatherObj[x].id){
                selectHtml=selectHtml+'<option value="'+fatherObj[x].id+'" selected>';
            }else{
                selectHtml=selectHtml+'<option value="'+fatherObj[x].id+'">';
            }

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
            $.ajaxConnSend(this, 'teacher/doModTeacherInfo.infc', params, function(data) {
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
        window.location.href = "../sys/teahcer/sysTeacher.html";
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
                var isChkStr = '';
                if($.trim(defaultVal) != '' && item['code'] == defaultVal){
                    isChkStr = 'selected ';
                }
                optionHtml += '<option value ="'+item['code']+'" '+isChkStr+'>'+item['text']+'</option>';
            }
            if(isRequired){
                optionHtml += '</select></div>';
            }else{
                optionHtml += '</select></div>';
            }
            obj.append(optionHtml);
        });
    }

