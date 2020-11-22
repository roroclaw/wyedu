/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){
    //下拉框设置
    $('.buildingSel').mysel({
        url : 'common/getbuildingNoItems.infc',
        text : '&nbsp;教&nbsp;学&nbsp;楼',
        name : 'buildingNo',
        isRequired : false,
        id:'buildingNo'
    });
    $('.schoolYearSel').mysel({
      //  url : 'common/getFromToEduYearItems.infc',getSchoolYearItems
        url : 'common/getSchoolYearItems.infc',
        text : '学年',
        name : 'schoolYear',
        isRequired : false,
        id:'schoolYear'
    });
    ///////////响应下拉框选择
    $('.schoolYearSel').change(function(){
        var schoolYear= $("#schoolYear").find("option:selected").val();
        $('.examPlanSel').empty();
        $('.examPlanSel').mysel({
            url : 'exam/getExamPlanItemsBySchoolYear.infc',
            text : '考试计划',
            name : 'examPlanId',
            params : {"schoolYear":schoolYear},
            isRequired : false,
            valueKey : 'code',
            textKey : 'text'
        });
    });
    //table
    var table = $('.dataTable').mytable({
        isCheck: true,
        idKeyName:'id',
        header: [{
            code: 'name',
            text: '考场名称'
        }, {
            code: 'classroomText',
            text: '教室'
        }, {
            code: 'examPlanText',
            text: '考试计划'
        }],
        url: 'examRoom/doGetExamRoomPageData.infc',
        modFun:function(id) {
            if(typeof(winOpen)=="object"){
                winOpen.close();             //关闭之前打开的窗口
            }
            winOpen=window.open( BAS_URL+"examRoom/initEditExamRoom.do?id="+id,'examRoomModForm');
          }
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
         window.location.href = $.customOpt.url +'/exa/examRoom/examRoomAddForm.html';
    });

    /**
     * 排座
     */
    $('#editBtn').click(function(){

        var items=table.getChecked();
        if(items.length==1){
            winOpen=window.open( BAS_URL+"examRoom/initEditForExamRoomSeat.do?id="+items[0].id,'EditForExamRoomSeat');

        }else{
            $.alert_error('请选中一个考场');
        }
    });

    /**
     * 考场平面

    $('#detailBtn').click(function(){

        var items=table.getChecked();
        if(items.length==1){
            winOpen=window.open( BAS_URL+"examRoom/initEditForExamRoomSeat.do?id="+items[0].id,'EditForExamRoomSeat');

        }else{
            $.alert_error('请选中一个考场');
        }
    });  */
});
