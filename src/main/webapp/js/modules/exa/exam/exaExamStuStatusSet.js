/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){
    //下拉框设置
    $('.planSel').mysel({
        url : 'exam/getExamPlanItems.infc',
        text : '考试计划',
        name : 'examPlanId',
        isRequired : true
    });
    //班级选择
    $.ajaxConnSend(this, 'class/getClassInfoItemsForPower.infc', {}, function(data) {
        var classinfos = data['object'];
        $('#classSel').setSelItems(classinfos);
    }, function() {
        $.loadingBox.close();
    });

    $('.statusSel').mysel({
        url : 'common/doGetExamStuStatusShort.infc',
        text : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状&nbsp;&nbsp;态',
        name : 'status',
        isRequired : true,
        id:'statusSel'
    });
    //table

    var examStudentTable = $('.dataTable').mytable({
        isCheck: true,
        idKeyName:'id',
        header: [{
            code: 'stuNumber',
            text: '学号'
        },  {
            code: 'realName',
            text: '姓名'
        },{
            code: 'gradeName',
            text: '年级'
        },  {
            code: 'className',
            text: '班级'
        }, {
            code: 'statusText',
            text: '状态'
        }],
        params:{"classId":'00'},
        url: 'exam/doGetExamStudentsPageDataByExamPlan.infc',
        operColFun_2:function(i,rowdata){
            var stuId =  rowdata['stuId'];
            var status =  rowdata['status'];
            var examPlanId =  rowdata['examPlanId'];
            var operObj = $('<i href="###" class="iconfont icon-viewlist bgColor-2" title="状态修改" rownum="' + i + '" did="' + stuId + '"></i>');
            operObj.on('click', function () {
                $('#stu_id').val(stuId);
                $('#exam_plan_id').val(examPlanId);
                $('#statusSel').val(status);
                $.showPopupForm('#modExamStuStatusForm',function(){
                    var params = $('#modExamStuStatusForm').getValue();
                    $.loadingBox.show();
                    $.ajaxConnSend(this, 'exam/doModAllExamStudentsStatusInExamPlan.infc', params, function(data) {
                        if (data.status == '1' && data.object) {
                            $.alert_success('修改成功!');
                            examStudentTable.refreshData();
                        } else {
                            $.alert_error('修改失败');
                        }
                    }, function() {
                        $.loadingBox.close();
                        //operObj.close();
                        //$('.message_box').hide();
                        $('.cancelBtn').click();
                    });
                });

            });
            return operObj;
        }
    });
    /**
     * 搜索
     */
    $('#queryBtn').click(function(){
        var params = $('#queryForm').getValue();
        examStudentTable.refreshData(params);
    });

    /**
     * 新增
     */
    $('#addBtn').click(function(){
         window.location.href = $.customOpt.url +'/exa/exam/examAddForm.html';
    });

    /**
     * 考生编排
     */
    $('#editBtn').click(function(){

        var items=table.getChecked();
        if(items.length==1){
            //winOpen=window.open( BAS_URL+"exam/initEditForExamStudent.do?id="+items[0].id,'EditForExamStudent');
            window.location.href = $.customOpt.url +'exam/initEditForExamStudent.do?id='+items[0].id;
        }else{
            $.alert_error('请选中一个考场');
        }
    });

    /**
     * 考场安排
     */
    $('#examroomEditBtn').click(function(){
        var items=table.getChecked();
        if(items.length==1){
            window.location.href = $.customOpt.url +'exam/initEditForExamExamRoom.do?id='+items[0].id;
        }else{
            $.alert_error('请选中一个考场');
        }
    });
    /**
     * 考场座位帖
     */
    $('#examroomSeatTipBtn').click(function(){
        var items=table.getChecked();
        if(items.length==1){
            winOpen=window.open( BAS_URL+"exa/exam/examRoomSeatsTip.html?id="+items[0].id,'ExamroomSeatTip');
        }else{
            $.alert_error('请选中一个考场');
        }
    });


});
