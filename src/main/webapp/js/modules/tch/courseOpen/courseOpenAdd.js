/**
 * Created by dengxianzhi on 2017/1/29.
 */
$(function(){

    // 表单验证
    $("#addForm").validationEngine("attach", {
        promptPosition : "topLeft",
        autoHidePrompt : true,
        autoHideDelay : 3000,
        scroll : false
    });

    createSel($('#termSel'),'term','common/doGetTermItems.infc',true);

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
     * 班级选择
     */
    $.genrateOptionsByItems('#classSel','class/getClassInfoItems.infc',false);


    /**
     * 学年选择
     */
    $.genrateOptionsByItems('#schoolYearSel','common/getFromToEduYearItems.infc',false);



    //table
    var table = $('.dataTable').mytable({
        isCheck: true,
        idKeyName:'id',
        header: [{
            code: 'code',
            text: '课程编码'
        },{
            code: 'name',
            text: '课程名称'
        },{
            code: 'subjectName',
            text: '科目名称'
        }, {
            code: 'teacherName',
            text: '授课教师'
        }, {
            code: 'teachTypeText',
            text: '教学方式'
        }, {
            code: 'classroomTypeText',
            text: '教室类型'
        },  {
            code: 'reamrk',
            text: '备注'
        }, {
            code: 'statusText',
            text: '状态'
        }],
        url: 'course/doGetCoursesPageData.infc'
    });

    /**
     * 搜索
     */
    $('#queryBtn').click(function(){
        var params = $('#queryForm').getValue();
        table.refreshData(params);
    });

    $('#backBtn').click(function(){
        window.location.href = "../courseOpen/tchCourseOpen.html";
    });
    /**
     * 添加科目
     */
    $("#addBtn").click(function(){
        var bol = $("#addForm").validationEngine("validate");
        var items=table.getChecked();
        if(items.length==1){
            if (bol) {
                var params = $('#addForm').getValue();
                params["courseId"]=items[0].id;
                $.loadingBox.show();
                $.ajaxConnSend(this, 'courseOpen/doAddCourseOpen.infc', params, function(data) {
                    if (data.status == '1' && data.object) {
                        $.alert_success('新增成功!');

                    } else {
                        $.alert_error('新增失败');
                    }
                }, function() {
                    $.loadingBox.close();
                });
            }
        }else{
            $.alert_error('请选中一个课程');
        }

    });

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