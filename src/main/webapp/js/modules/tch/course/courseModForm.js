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
    //createSel($('#subjectType_select_add'),'sub','common/doGetPeriodItems.infc',false);
    createSel($('#teacherType_select_add'),'teacherType','common/doGetTeacherTypeItems.infc',false);
    createSel($('#teachType_select_add'),'teachType','common/doGetTeachTypeItems.infc',true);
    createSel($('#classroomType_select_add'),'classroomType','common/doGetClassroomTypeItems.infc',true);
    createSel($('#status_select_add'),'status','common/doGetUserStatus.infc',true);
  //  makeSubjectSelectChange($("#subjectType_select_add").val(),'subject_select_add','subject/doGetSubjectListsByParam.infc');
 //   makeTeacherSelectChange($("#subjectType_select_add").val(),'teacher_select_add','teacher/doGetTeacherListsByParam.infc');
   // console.debug("<option value='"+$("#subjectType_select_add").attr('value')+"'>"+$("#subjectType_select_add").attr('param')+"</option>");
 //   $("#subjectId").append("<option value='"+$("#subject_select_add").attr('value')+"'>"+$("#subject_select_add").attr('param')+"</option>");
    $("#teacherId").append("<option value='"+$("#teacher_select_add").attr('value')+"'>"+$("#teacher_select_add").attr('param')+"</option>");

    ///////////响应下拉框选择
  /*  $('#subjectType_select_add').change(function(){
        makeSubjectSelectChange($("#subjectType_select_add").find("option:selected").val(),'subjectId','subject/doGetSubjectListsByParam.infc');
    })
    $('#teacherType_select_add').change(function(){
        makeTeacherSelectChange($("#teacherType_select_add").find("option:selected").val(),'teacherId','teacher/doGetTeacherListsByParam.infc');
    })*/

    $('.subjectTypeSel').mysel({
        url : 'common/doGetSubjectTypeItems.infc',
        text : '所属科目类型',
        name : '',
        isRequired : true,
        id:'subjectType'
    });
    ///////////响应下拉框选择
    $('.subjectTypeSel').change(function(){
        var type= $("#subjectType").find("option:selected").val();
        $('.subjectSel').empty();
        $('.subjectSel').mysel({
            url : 'subject/getSubjectInfoItems.infc',
            text : '&nbsp;所&nbsp;&nbsp;属&nbsp;&nbsp;科&nbsp;&nbsp;目',
            name : 'subjectId',
            params : {"type":type},
            isRequired : true,
            valueKey : 'code',
            textKey : 'text'
        });
    });

    /**
     * 修改
     */
    $("#modBtn").click(function(){
        var bol = $("#dataForm").validationEngine("validate");
        if (bol) {
            var params = $('#dataForm').getValue();
            $.loadingBox.show();
            $.ajaxConnSend(this, 'course/doModCourseInfo.infc', params, function(data) {
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
        window.location.href = "../course/tchCourse.html";
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

function makeSubjectSelectChange(type,id,url){
    if(type!=undefined && type!=""){
        $.ajaxConnSend(this, url, {type:type}, function (data) {
            var optionHtml = '';
            optionHtml += '<option value ="">--请选择--</option>';
            var items = data['object'];
            $("#"+id).find("option:selected").removeAttr("selected");
            $("#"+id).empty();
            for(var i = 0; i < items.length; i++){
                var item = items[i];
                $("#"+id).get(0).options.add(new Option(item['name'],item['id']));
                //optionHtml += '<option value ="'+item['id']+'">'+item['name']+'</option>';
            }
            // $("#"+id).append(optionHtml);
        });
    }
}
function makeTeacherSelectChange(type,id,url){
    if(type!=undefined && type!=""){
        $.ajaxConnSend(this, url, {type:type}, function (data) {
            var optionHtml = '';
            optionHtml += '<option value ="">--请选择--</option>';
            var items = data['object'];
            $("#"+id).find("option:selected").removeAttr("selected");
            $("#"+id).empty();

            for(var i = 0; i < items.length; i++){
                var item = items[i];
                $("#"+id).get(0).options.add(new Option(item['name'],item['id']));
                //optionHtml += '<option value ="'+item['id']+'">'+item['name']+'</option>';
            }
            // $("#"+id).append(optionHtml);
        });
    }
}
