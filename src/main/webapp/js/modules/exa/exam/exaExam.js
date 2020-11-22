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
    $('.statusSel').mysel({
        url : 'common/doGetExamStatus.infc',
        text : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状&nbsp;&nbsp;态',
        name : 'status',
        isRequired : true,
        id:'statusSel'
    });

    //table
    var table = $('.dataTable').mytable({
        isCheck: true,
        idKeyName:'id',
        header: [{
            code: 'examPlanName',
            text: '所属考试计划'
        }, {
            code: 'subjectName',
            text: '科目名称'
        }, {
            code: 'date',
            text: '考试日期',
            formateFun:function(txt, i, code, rowdata){
                var dateChar="";
                dateChar=  $.formate2Day(rowdata.date);
                return dateChar;
            }
        } ,{
            code: 'startTime',
            text: '开始时间',
            formateFun:function(txt, i, code, rowdata){
                var dateChar="";
                dateChar=  $.formateDateTime(rowdata.startTime);
                return dateChar;
            }
        }, {
            code: 'endTime',
            text: '结束时间',
            formateFun:function(txt, i, code, rowdata){
                var dateChar="";
                dateChar=  $.formateDateTime(rowdata.endTime);
                return dateChar;
            }
        },  {
            code: 'statusText',
            text: '状态'
        }],
        url: 'exam/doGetExamPageData.infc',
       /* modFun:function(id,rowdata) {
            console.debug(rowdata.status);
            if(typeof(winOpen)=="object"){
                winOpen.close();             //关闭之前打开的窗口
            }
           winOpen=window.open( BAS_URL+"exam/initEditExam.do?id="+id,'examModForm');
        },*/
        operColFun:function(i,rowdata){
            var status =  rowdata['status'];
            var id =  rowdata['id'];
            if( status=="0"){
                var modObj = $('<i href="###" class="iconfont icon-icon07" title="编辑" rownum="' + i + '"></i>');
                modObj.on('click', function () {
                    if(typeof(winOpen)=="object"){
                        winOpen.close();             //关闭之前打开的窗口
                    }
                    winOpen=window.open( BAS_URL+"exam/initEditExam.do?id="+id,'examModForm');
                });
               // operObj.append(modObj);
            }
            return modObj;
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
        },  changeStatusFun:function(id,status) {
         // console.debug(data+"|||"+j);
         $.loadingBox.show();
         $.ajaxConnSend(this, 'exam/doModExamInfo.infc', {
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
        operColFun_2:function(i,rowdata){
            var id =  rowdata['id'];
            var status =  rowdata['status'];
            var operObj = $('<i href="###" class="iconfont icon-viewlist bgColor-2" title="状态修改" rownum="' + i + '" did="' + id + '"></i>');
            operObj.on('click', function () {
                $('#id').val(id);
                $('#statusSel').val(status);
                $.showPopupForm('#modExamStatusForm',function(){
                    var params = $('#modExamStatusForm').getValue();
                    $.loadingBox.show();
                    $.ajaxConnSend(this, 'exam/doModExamInfoStatus.infc', params, function(data) {
                        if (data.status == '1' && data.object) {
                            $.alert_success('修改成功!');
                            table.refreshData();
                        } else {
                            $.alert_error('修改失败');
                        }
                    }, function() {
                        $.loadingBox.close();
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
        table.refreshData(params);
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
