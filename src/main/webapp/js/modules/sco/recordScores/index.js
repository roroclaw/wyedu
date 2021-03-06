/**
 * Created by roroclaw on 2017/10/29.
 */
$(function () {

    /**
     * 搜索
     */
    $('#queryBtn').click(function () {
        var params = $('#queryForm').getValue();
        table.refreshData(params);
    });


    var table = $('.dataTable').mytable({
        isCheck: false,
        idKeyName: 'id',
        header: [{
            code: 'code',
            text: '课程编号'
        }, {
            code: 'name',
            text: '课程名'
        }, {
            code: 'classText',
            text: '班级'
        }, {
            code: 'subjectText',
            text: '科目'
        }, {
            code: 'schoolYear',
            text: '学年'
        }, {
            code: 'termText',
            text: '学期'
        }, {
            code: 'teacherText',
            text: '授课教师'
        }, {
            code: 'scoresTypeText',
            text: '成绩类型'
        }, {
            code: 'statusText',
            text: '成绩状态',
            formateFun: function (txt, i, code, rowdata) {
                var status = rowdata['status'];
                if (status == null || status == '') {
                    return '未设置';
                } else {
                    return rowdata['statusText'];
                }
            }
        }],
        url: 'scoExamScores/doGetScoRecordCoursePageData.infc'
        , operColFun: function (i, rowdata) {
            var status = rowdata['status'];
            var scoresType =  rowdata['scoresType'];

            if (status == '3') {
                var goRecordScoresObj = $('<i href="###" class="iconfont  icon-icon07 " title="登分" rownum="' + i + '"></i>');
                goRecordScoresObj.on('click', function () {
                    var id = rowdata['id'];
                    var scoresType = rowdata['scoresType'];
                    window.location.href = BAS_URL + "scoExamScores/initRecordScores.do?openCourseId=" + id + "&scoreType=" + scoresType;
                });
                var saveObj = $('<i href="###" class="iconfont  icon-baocun" title="提交登分" rownum="' + i + '"></i>');
                saveObj.on('click', function () {
                    var id = rowdata['id'];
                    var scoresType = rowdata['scoresType'];
                    subScoresConfig(id, scoresType);
                });

                if( scoresType =="2"){
                    var calObj = $('<i href="###" class="iconfont  bgColor-4 " title="计算科目成绩" rownum="' + i + '">算</i>');
                    calObj.on('click', function () {
                        // var id = rowdata['id'];
                        // var scoresType = rowdata['scoresType'];
                        var showBox = $.showPopupForm('#showForm',function(){
                            $.loadingBox.show();
                            var params = $('#showForm').getValue();
                            if(params['ruleId'] == null || params['ruleId'] == ''){
                                $.alert_error("请选择规则!");
                                $.loadingBox.close();
                                return false;
                            }

                            params['openCourseId'] = rowdata['id'];
                            params['scoreType'] = scoresType;

                            $.ajaxConnSend(this, 'sysScoreRule/calTchSocresByRuleId.infc',params,function (data) {
                                if (data.status == '1' && data.object) {
                                    $.alert_success('计算成功!');
                                    showBox.close();
                                } else {
                                    $.alert_error('计算失败');
                                }
                            }, function() {
                                $.loadingBox.close();
                            });
                        });

                    });
                    goRecordScoresObj.after(calObj);
                }

                goRecordScoresObj.after(saveObj);

                return goRecordScoresObj;
            } else if (status == '5') {
                var printObj = $('<i href="###" class="iconfont  bgColor-4 " title="印" rownum="' + i + '">印</i>');
                printObj.on('click', function () {
                    var id = rowdata['id'];
                    var scoresType = rowdata['scoresType'];
                    var url = BAS_URL + "scoExamScores/initPrint.do?openCourseId=" + id + "&scoreType=" + scoresType;
                    window.open(url, 'print');
                });
                return printObj;
            }
        }
    });

    function subScoresConfig(id, scoresType) {
        $.ajaxConnSend(this, 'scoExamScores/subScoresConfig.infc', {
            openCourseId: id,
            scoresType: scoresType
        }, function (data) {
            if (data.status == '1' && data.object) {
                $.alert_success('提交登分记录成功!');
                $('#queryBtn').trigger('click');
            } else {
                $.alert_error('设置失败');
            }
        }, function () {
            $.loadingBox.close();
        });
    }

    $('.ruleSel').mysel({
        url : 'common/getTchRules.infc',
        text : '规&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;则',
        name : 'ruleId',
        isRequired : true
    });

});