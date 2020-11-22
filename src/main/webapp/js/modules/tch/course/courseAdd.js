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
    //createSel($('#subjectType_select_add'),'subjectType','common/doGetSubjectTypeItems.infc',true);
    createSel($('#teacherType_select_add'),'teacherType','common/doGetTeacherTypeItems.infc',true);
    createSel($('#teachType_select_add'),'teachType','common/doGetTeachTypeItems.infc',true);
    createSel($('#classroomType_select_add'),'classroomType','common/doGetClassroomTypeItems.infc',true);
    createSel($('#status_select_add'),'status','common/doGetUserStatus.infc',true);

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

    ///////////响应下拉框选择
  //  $('#subjectType_select_add').change(function(){
   //     makeSelectChange('type',$("#subjectType_select_add").find("option:selected").val(),'subjectId','subject/doGetSubjectListsByParam.infc','name','id');
  //  })
    $('#teacherType_select_add').change(function(){
        makeSelectChange('type',$("#teacherType_select_add").find("option:selected").val(),'teacherId','teacher/doGetTeacherListsByParam.infc','name','id');
    });
    /**
     * 新增
     */
    $("#addBtn").click(function(){
        var bol = $("#dataForm").validationEngine("validate");
        if (bol) {
            var params = $('#dataForm').getValue();
            $.loadingBox.show();
            $.ajaxConnSend(this, 'course/doAddCourse.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('新增成功!');
                    window.location.href = "../course/tchCourse.html";
                } else {
                    $.alert_error('新增失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }
    });
});
$('#backBtn').click(function(){
    window.location.href = "../course/tchCourse.html";
});
/**
 * 构造本页面的级联响应下拉框控件
 * @param obj
 * @param url
 */
function makeSelectChange(paramKey,paraVal,id,url,selText,selVal){
    var keyVal='{'+paramKey+':'+paraVal+'}';
    var keyValObj=eval("("+keyVal+")");
    if(paraVal!=undefined && paraVal!=""){
        $.ajaxConnSend(this, url, keyValObj, function (data) {
            var optionHtml = '';
            optionHtml += '<option value ="">--请选择--</option>';
            var items = data['object'];
            $("#"+id).find("option:selected").removeAttr("selected");
            $("#"+id).empty();

            for(var i = 0; i < items.length; i++){
                var item = items[i];
                $("#"+id).get(0).options.add(new Option(item[selText],item[selVal]));
            }
        });
    }
}


/**
 * 构造本页面的下拉框控件
 * @param obj
 * @param url
 */
function createSel(obj,name,url,isRequired){
    var optionHtml = '';
    optionHtml += '<div class="select_box">';
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