/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){

    createSel($('#subjectType_select_add'),'subjectType','common/doGetSubjectTypeItems.infc',false);
    ///////////响应下拉框选择
    $('#subjectType_select_add').change(function(){
        makeSelectChange('type',$("#subjectType_select_add").find("option:selected").val(),'subjectId','subject/doGetSubjectListsByParam.infc','name','id');
    });

    //table
    var table = $('.dataTable').mytable({
        isCheck: true,
        idKeyName:'id',
        header: [{
            code: 'code',
            text: '课程编码'
        },{
            code: 'name',
            text: '名称'
        },{
            code: 'subjectName',
            text: '所属科目'
        },  {
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
        url: 'course/doGetCoursesPageData.infc',
        modFun:function(id) {
            if(typeof(winOpen)=="object"){
                winOpen.close();             //关闭之前打开的窗口
            }
            winOpen=window.open( BAS_URL+"/course/initEdit.do?id="+id,'courseModForm');
        },
        /*delFun: function(id) {
         $.alert_confirm('确定删除此记录?', function() {
         $.loadingBox.show();
         $.ajaxConnSend(this, 'user/doDelUserById.infc', {
         id: id
         }, function(data) {
         if (data.status == '1' && data.object) {
         $.alert_success('删除成功');
         table.refreshData();
         } else {
         $.alert_error('删除失败');
         }
         },function(){
         $.loadingBox.close();
         })
         });
         },*/
        changeStatusFun:function(id,status) {
            // console.debug(data+"|||"+j);
            $.loadingBox.show();
            $.ajaxConnSend(this, 'course/doModCourseInfo.infc', {
                id:id,status:status
            }, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('操作成功');
                    table.refreshData();
                } else {
                    $.alert_error('操作失败');
                }
            },function(){
                $.loadingBox.close();
            })
        }
    });

    /**
     * 搜索
     */
    $('#queryBtn').click(function(){
        var params = $('#queryForm').getValue();
        table.refreshData(params);
    });

    /**
     * 新增
     */
    $('#addBtn').click(function(){
        window.location.href = $.customOpt.url +'tch/course/courseAddForm.html';
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
