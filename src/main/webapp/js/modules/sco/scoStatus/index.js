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
            code: 'code',
            text: '课程编号'
        }, {
            code: 'name',
            text: '课程名'
        },{
            code: 'subjectText',
            text: '科目'
        },{
            code: 'classText',
            text: '班级'
        },{
            code: 'schoolYear',
            text: '学年'
        }, {
            code: 'termText',
            text: '学期'
        },{
            code: 'scoresTypeText',
            text: '成绩类型'
        },{
            code: 'teacherText',
            text: '授课教师'
        }, {
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
        url: 'scoExamScores/doGetScoStatusCoursePageData.infc'
        ,operColFun:function(i,rowdata){
            var saveObj = $('<i href="###" class="iconfont  icon-baocun" title="保存,开始教师登分" rownum="' + i + '"></i>');
            saveObj.on('click', function () {
                var id = rowdata['id'];
                var scoresType = rowdata['scoresType'];
                startTeacher(id,scoresType);
            });
            var goStatusSetObj = $('<i href="###" class="iconfont  icon-icon07 " title="设置缺缓考" rownum="' + i + '"></i>');
            goStatusSetObj.on('click', function () {
                var id = rowdata['id'];
                var scoresType = rowdata['scoresType'];
                window.location.href = BAS_URL+"scoExamScores/initScoStatus.do?openCourseId="+id+"&scoreType="+scoresType;
            });
            goStatusSetObj.after(saveObj);
            return goStatusSetObj;
        }
    });

    $('.schoolYearSel').mysel({
        url : 'common/getFromToEduYearItems.infc',
        text : '学年',
        name : 'schoolYear'
    });

    $('.termSel').mysel({
        url : 'common/doGetTermItems.infc',
        text : '学期',
        name : 'term'
    });

    function startTeacher(id,scoresType){
        $.ajaxConnSend(this, 'scoExamScores/doStartTeacher.infc', {openCourseId:id,scoresType:scoresType}, function(data) {
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

});