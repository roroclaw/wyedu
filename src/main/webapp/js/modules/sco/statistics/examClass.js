/**
 * Created by roroclaw on 2018/1/15.
 */
$(function(){
    // 表单验证
    $("#dataForm").validationEngine("attach", {
        promptPosition : "topLeft",
        autoHidePrompt : true,
        autoHideDelay : 3000,
        scroll : false
    });

    $('.schoolYearSel').mysel({
        url : 'common/getFromToEduYearItems.infc',
        text : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学&nbsp;&nbsp;年',
        name : 'schoolYear',
        isRequired : true
    });

    $('.termSel').mysel({
        url : 'common/doGetTermItems.infc',
        text : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学&nbsp;&nbsp;期',
        name : 'term',
        isRequired : true
    });
    $('.scoreTypeSel').mysel({
        url : 'common/getScoreTypeItems.infc',
        text : '考试类型',
        name : 'scoreType',
        isRequired : true
    });

    $('.gradeSel').mysel({
        url : 'common/getGradeItems.infc',
        text : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;级',
        name : 'grade',
        isRequired : false,
        id:'grade'
    });

    $('#calBtn').click(function(){
        var bol = $("#dataForm").validationEngine("validate");
        if (bol) {
            var params = $('#dataForm').getValue();
            $.loadingBox.show();
            $.ajaxConnSend(this, 'scoExamScores/calExamClassStatisticsDatas.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('计算成功!');
                } else {
                    $.alert_error('计算失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }
    });


    $('#examClassBtn').click(function(){
        //var bol = $("#dataForm").validationEngine("validate");
        //if (bol) {
        var params = $('#dataForm').getValue();
        var schoolYear = params['schoolYear'];
        var term = params['term'];
        var scoreType = params['scoreType'];
        var grade = params['grade'];
        if(schoolYear == ''){
            $.alert_error("请选择学年");
            return false;
        }
        if(term == ''){
            $.alert_error("请选择学期");
            return false;
        }
        if(scoreType == ''){
            $.alert_error("请选择考试类型");
            return false;
        }
        if(grade == ''){
            $.alert_error("请选择年级");
            return false;
        }
        var url = BAS_URL + "scoExamScores/initPrintExamClass.do?schoolYear=" + schoolYear + "&term=" + term + "&scoreType=" + scoreType + "&grade=" + grade;
        window.open(url);
        //}
    });
});