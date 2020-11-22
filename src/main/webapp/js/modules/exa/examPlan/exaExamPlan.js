/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){
    //初始化类型
    $('.typeSel').mysel({
        url : 'common/doGetExamType.infc',
        text : '考试类型',
        name : 'type',
        isRequired : false
    });
    $('.schoolYearSel').mysel({
        url : 'common/getSchoolYearItems.infc',
        text : '学年',
        name : 'schoolYear',
        isRequired : false,
        id:'schoolYear',
        callbackFun:function(){ $('.schoolYearSel').append("<span style='line-height: 35px;background: #fff;'>(班级筛选条件)</span>");}
    });
    //table
    var table = $('.dataTable').mytable({
        isCheck: true,
        idKeyName:'id',
        header: [{
            code: 'name',
            text: '名称'
        }, {
            code: 'schoolYear',
            text: '学年'
        }, {
            code: 'examTypeText',
            text: '类型'
        }, {
            code: 'statusText',
            text: '状态'
        }],
        url: 'exam/doGetExamPlanPageData.infc',
        modFun:function(id) {
            if(typeof(winOpen)=="object"){
                winOpen.close();             //关闭之前打开的窗口
            }
            winOpen=window.open( BAS_URL+"exam/initEditExamPlan.do?id="+id,'classroomModForm');
        }
         /*,delFun: function(id) {
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
        },
        changeStatusFun:function(id,status) {
            // console.debug(data+"|||"+j);
            $.loadingBox.show();
            $.ajaxConnSend(this, 'exam/doModExamPlam.infc', {
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
        }*/
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
         window.location.href = $.customOpt.url +'/exa/examPlan/examPlanAddForm.html';
    });

    /**
     * 详情表
     */
     $('#detailBtn').click(function(){

        var items=table.getChecked();
        if(items.length==1){
            winOpen=window.open( BAS_URL+"exam/initEditExamPlanDetail.do?id="+items[0].id,'DetailForExamPlan');
        }else{
            $.alert_error('请选中一个考试计划');
        }
    });

    /**
     * 考生名单
     */
    $('#examStuBtn').click(function(){
        var items=table.getChecked();
        if(items.length==1){
            winOpen=window.open( BAS_URL+"exa/examPlan/examStudentsDetail.html?id="+items[0].id,'DetailForExamStu');
        }else{
            $.alert_error('请选中一个考试计划');
        }
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
        optionHtml += '<select id="'+name+'" name="'+name+'">';
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
            optionHtml += '</select></div>';
        }else{
            optionHtml += '</select></div>';
        }
        obj.append(optionHtml);
    });
}