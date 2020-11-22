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
    createSel($('#termSel'),'term','common/doGetTermItems.infc',true);

    /**
     * 班级选择
     */
    $.genrateOptionsByItems('#classSel','class/getClassInfoItems.infc',false);


    /**
     * 学年选择
     */
    $.genrateOptionsByItems('#schoolYearSel','common/getEduYearItems.infc',false);

    $('.schoolYearSel').mysel({
        url : 'common/getEduYearItems.infc',
        text : '学年',
        name : 'schoolYear',
        isRequired : true
    });

    $('.buildingSel').mysel({
        url : 'common/getbuildingNoItems.infc',
        text : '教学楼',
        name : 'buildingNo',
        isRequired : false,
        id:'building'
    });
    $('.classroomSel').mysel({
        url : 'classroom/getClassroomItems.infc',
        text : '教室',
        name : 'classroomId',
        isRequired : true
    });

    ///////////响应下拉框选择
    $('#building').change(function(){
        var buildingNo= $("#building").find("option:selected").val();
        //班级选择
        $('.classroomSel').empty();
        $('.classroomSel').mysel({
            url : 'classroom/getClassroomItems.infc',
            text : '教室',
            name : 'classroomId',
            params : {"buildingNo":buildingNo},
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
            $.ajaxConnSend(this, 'courseOpen/doModCourseOpenInfo.infc', params, function(data) {
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
