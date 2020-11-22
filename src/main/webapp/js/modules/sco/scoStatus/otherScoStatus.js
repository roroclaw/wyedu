/**
 * Created by roroclaw on 2017/10/29.
 */
$(function(){

    /**
     * 搜索
     */
    $('#queryBtn').click(function(){
        var params = $('#queryForm').getValue();
        table.refreshData(params);
    });

    var table = $('.dataTable').mytable({
        isCheck: false,
        idKeyName:'id',
        header: [{ code: 'stuNo',
            text: '学号'
        },{
            code: 'stuName',
            text: '学员姓名'
        },{
            code: 'subjectName',
            text: '科目'
        },{
            code: 'statusText',
            text: '缺/缓考'
        }],
        url: 'scoExamScores/doGetOtherExamScores4StatusPageData.infc?examId='+$('#examId').val() ,
        modFun:function(id) {//登记分数
            $('#recordScoreFormId').val(id);
            var showBox = $.showPopupForm('#recordScoreForm',function(){
                var bol = $("#recordScoreForm").validationEngine("validate");
                if (bol) {
                    var formData = $("#recordScoreForm").getValue();
                    $.loadingBox.show();
                    $.ajaxConnSend(this, 'scoExamScores/doOtherSetScoresStatus.infc',formData, function(data) {
                        $.alert_success("设置成功!");
                        var curPage = Number($('.curPage').html());
                        var params = $('#queryForm').getValue();
                        params['curPage'] = curPage;
                        table.refreshData(params,1);
                        showBox.close();
                    },function(){
                        $.loadingBox.close();
                    });
                }
            });
        }
    });

    $('.statusSel').mysel({
        url : 'common/doGetExamStuStatus.infc',
        text : '状&nbsp;&nbsp;态',
        name : 'status',
        isRequired : false
    });

    $('#backBtn').click(function(){
        window.location.href = BAS_URL+'sco/scoStatus/otherIndex.html';
    })
});