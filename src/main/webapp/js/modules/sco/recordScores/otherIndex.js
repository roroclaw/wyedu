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
        header: [{
            code: 'planText',
            text: '考试计划'
        }, {
            code: 'subjectText',
            text: '科目'
        },{
            code: 'recordTeacherText',
            text: '登分教师'
        },{
            code: 'statusText',
            text: '成绩状态',
            formateFun:function(txt, i, code, rowdata){
                var status =  rowdata['status'];
                if(status == null || status == ''){
                    return '未设置';
                }else{
                    return rowdata['statusText'];
                }
            }
        }],
        url: 'scoExamScores/doGetOtherScoRecordCoursePageData.infc'
        ,operColFun:function(i,rowdata){
            var status = rowdata['status'];
            if(status == '3'){
                var goRecordScoresObj = $('<i href="###" class="iconfont  icon-icon07 " title="登分" rownum="' + i + '"></i>');
                goRecordScoresObj.on('click', function () {
                    var id = rowdata['id'];
                    window.location.href = BAS_URL+"scoExamScores/initOtherRecordScores.do?examId="+id;
                });
                var saveObj = $('<i href="###" class="iconfont  icon-baocun" title="提交登分" rownum="' + i + '"></i>');
                saveObj.on('click', function () {
                    var id = rowdata['id'];
                    subScoresConfig(id);
                });
                goRecordScoresObj.after(saveObj);
                return goRecordScoresObj;
            }else if(status == '5') {
                var printObj = $('<i href="###" class="iconfont  bgColor-4 " title="印" rownum="' + i + '">印</i>');
                printObj.on('click', function () {
                    var id = rowdata['id'];
                    var url = BAS_URL+"scoExamScores/initOtherPrint.do?examId="+id;
                    window.open(url,'print');
                });
                return printObj;
            }
        }
    });

    function subScoresConfig(id){
        $.ajaxConnSend(this, 'scoExamScores/subOtherScoresConfig.infc', {examId:id}, function(data) {
            if (data.status == '1' && data.object) {
                $.alert_success('提交登分记录成功!');
                $('#queryBtn').trigger('click');
            } else {
                $.alert_error('设置失败');
            }
        }, function() {
            $.loadingBox.close();
        });
    }

    $('.subjectSel').mysel({
        url : 'subject/getSubjectInfoItems.infc',
        text : '考试科目',
        name : 'subjectId',
        params : {},
        isRequired : true,
        valueKey : 'code',
        textKey : 'text'
    });

});