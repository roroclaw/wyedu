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
        }, {
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
        url: 'scoExamScores/doGetOtherScoStatusCoursePageData.infc'
        ,operColFun:function(i,rowdata){
            var saveObj = $('<i href="###" class="iconfont  icon-baocun" title="保存,开始教师登分" rownum="' + i + '"></i>');
            saveObj.on('click', function () {
                var id = rowdata['id'];
                startTeacher(id);
            });
            var goStatusSetObj = $('<i href="###" class="iconfont  icon-icon07 " title="设置缺缓考" rownum="' + i + '"></i>');
            goStatusSetObj.on('click', function () {
                var id = rowdata['id'];
                var scoresType = rowdata['scoresType'];
                window.location.href = BAS_URL+"scoExamScores/initOtherScoStatus.do?examId="+id;
            });
            goStatusSetObj.after(saveObj);
            return goStatusSetObj;
        }
    });

    function startTeacher(id){
        $.ajaxConnSend(this, 'scoExamScores/doOtherStartTeacher.infc', {examId:id}, function(data) {
            if (data.status == '1' && data.object) {
                $.alert_success('设置成功,进入教师登分!');
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