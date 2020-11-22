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
        isInitRefresh: false,
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
        url: 'scoExamScores/doGetRecordConfigPageData.infc'
        ,operColFun:function(i,rowdata){
            var status =  rowdata['status'];
            var btnObj = $('');

            var startObj = $('<i href="###" class="iconfont icon-kaiqi start" title="开始缺缓考设置" rownum="' + i + '"></i>');
            startObj.on('click', function () {
                var id = rowdata['id'];
                var scoresType = rowdata['scoresType'];
                //判断是否有登分教师,给予提示信息
                var recordTeacherText =  rowdata['recordTeacherText'];
                if(recordTeacherText = null || $.trim(recordTeacherText) == ''){
                    $.alert_confirm("未设置登分教师,将默认为授课教师!",function(){
                        startRecord(id,scoresType);
                    });
                }else{
                    startRecord(id,scoresType);
                }
            });

            if( status =="0" || status == null || status ==""){
                var modObj = $('<i href="###" class="iconfont icon-sousuoleimufill" title="设置登分教师" rownum="' + i + '"></i>');
                modObj.on('click', function () {
                    var id = rowdata['id'];
                    var scoresType = rowdata['scoresType'];
                    $('#teacherSetForm_id').val(id);
                    $('#scoresType').val(scoresType);
                    $('#teacherSetBox').show();
                });
                modObj.after(startObj);
                btnObj.after(modObj);
            }else if(status == "3"){ //教师登分后不提交,管理员无权结束登分状态
                var rebackObj = $('<i href="###" class="iconfont bgColor-4" title="退" rownum="' + i + '">退回</i>');
                rebackObj.on('click', function () {
                    var id = rowdata['id'];
                    var scoresType = rowdata['scoresType'];
                    rebackStatusSet(id,scoresType);
                });
                btnObj.after(rebackObj);
            }else if(status == "1"){
                var setObj = $('<i href="###" class="iconfont icon-icon07 setBtn" title="修改" rownum="' + i + '"></i>');
                setObj.on('click', function () {
                    var id = rowdata['id'];
                    $('#teacherSetForm_id').val(id);
                    $('#teacherSetBox').show();
                });
                setObj.after(startObj);
                btnObj.after(setObj);
            }else if(status == "2"){
                var startTeacherObj = $('<i href="###" class="iconfont icon-baocun" title="开始教师登分" rownum="' + i + '"></i>');
                startTeacherObj.on('click', function () {
                    var id = rowdata['id'];
                    var scoresType = rowdata['scoresType'];
                    startTeacher(id,scoresType);
                });
                btnObj.after(startTeacherObj);
            }else if(status == "5"){
                var rebackObj = $('<i href="###" class="iconfont bgColor-4" title="退" rownum="' + i + '">退回</i>');
                rebackObj.on('click', function () {
                    var id = rowdata['id'];
                    var scoresType = rowdata['scoresType'];
                    reback(id,scoresType);
                });
                var endObj = $('<i href="###" class="iconfont bgColor-4" title="完" rownum="' + i + '">完结</i>');
                endObj.on('click', function () {
                    var id = rowdata['id'];
                    var scoresType = rowdata['scoresType'];
                    endRecord(id,scoresType);
                });
                var modObj = $('<i href="###" class="iconfont bgColor-4" title="修" rownum="' + i + '">修分</i>');
                modObj.on('click', function () {
                    var id = rowdata['id'];
                    var scoresType = rowdata['scoresType'];
                    //modRecord(id,scoresType);
                    window.open(BAS_URL+"scoExamScores/initAdminModScores.do?openCourseId="+id+"&scoreType="+scoresType,'rcMod') ;
                });
                btnObj.after(rebackObj);
                btnObj.after(endObj);
                btnObj.after(modObj);
            }else if(status == "4"){
                var modObj = $('<i href="###" class="iconfont bgColor-4" title="修" rownum="' + i + '">修分</i>');
                modObj.on('click', function () {
                    var id = rowdata['id'];
                    var scoresType = rowdata['scoresType'];
                    //modRecord(id,scoresType);
                    //window.location.href = BAS_URL+"scoExamScores/initAdminModScores.do?openCourseId="+id+"&scoreType="+scoresType;
                    window.open(BAS_URL+"scoExamScores/initAdminModScores.do?openCourseId="+id+"&scoreType="+scoresType,'rcMod') ;
                });
                btnObj.after(modObj);
            }
            return btnObj;
        }
    });

    function startRecord(id,scoresType){
        if($(this).data('startRecord') == false || $(this).data('startRecord') == undefined){
            $(this).data('startRecord',true);
            $.ajaxConnSend(this, 'scoExamScores/doStartRecord.infc', {openCourseId:id,scoresType:scoresType}, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('设置成功,进入设置缺缓考!');
                    $('#queryBtn').trigger('click');
                } else {
                    $.alert_error('设置失败');
                }
            }, function() {
                $(this).data('startRecord',false);
                $.loadingBox.close();
            }, function() {
                $(this).data('startRecord',false);
                $.loadingBox.close();
            });
        }else{
            // $.alert_error('重复提交!');
            alert('重复提交!');
        }

    }

    function reback(id,scoresType){
         $.ajaxConnSend(this, 'scoExamScores/doRebackRecord.infc', {openCourseId:id,scoresType:scoresType}, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('回退成功!');
                    $('#queryBtn').trigger('click');
                } else {
                    $.alert_error('回退失败');
                }
            }, function() {
                $.loadingBox.close();
            });
    }

    function rebackStatusSet(id,scoresType){
         $.ajaxConnSend(this, 'scoExamScores/doRebackStatusSet.infc', {openCourseId:id,scoresType:scoresType}, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('回退成功!');
                    $('#queryBtn').trigger('click');
                } else {
                    $.alert_error('回退失败');
                }
            }, function() {
                $.loadingBox.close();
            });
    }

    function endRecord(id,scoresType){
         $.ajaxConnSend(this, 'scoExamScores/doEndRecord.infc', {openCourseId:id,scoresType:scoresType}, function(data) {
                if (data.status == '1' && data.object) {
                    //$('#queryBtn').trigger('click');
                    var params = $('#queryForm').getValue();
                    var curPage = Number($('.curPage').html());
                    params['curPage'] = curPage;
                    table.refreshData(params,1);
                    $.alert_success('此次登分已结束!');
                } else {
                    $.alert_error('结束登分失败');
                }
            }, function() {
                $.loadingBox.close();
            });
    }

    //function modRecord(id,scoresType){
    //
    //}

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

    $('.schoolYearSel').mysel({
        url : 'common/getFromToEduYearItems.infc',
        text : '学年',
        id : 'schoolYear',
        name : 'schoolYear'
    });

    $('#schoolYear').change(function(){
        var schoolYear= $("#schoolYear").find("option:selected").val();
        if(schoolYear) {
            $('.classSel').empty();
            $('.classSel').mysel({
                url :'class/getClassInfoItemsBySchoolYesr.infc',
                text : '班级',
                name : 'classId',
                id : 'classId',
                isRequired : true,
                params : {"schoolYear":schoolYear}
            });
        }
    });

    $('#schoolYear').trigger('change');

    $('.termSel').mysel({
        url : 'common/doGetTermItems.infc',
        text : '学期',
        name : 'term'
    });

    $('.scoresType').mysel({
        id : 'query_scoresType',
        url : 'common/doGetScoresTypeItems.infc',
        text : '分数类型',
        name : 'scoresType'
    });

    $('.configStatus').mysel({
        url : 'common/getRecordConfigStatusItems.infc',
        text : '状态',
        name : 'configStatus'
    });

    $('.teacherSel').mysel({
        url : 'teacher/doGetTeacherListsByParam.infc',
        text : '登分教师',
        name : 'teacherId',
        valueKey : 'id',
        textKey : 'name'
    });

    //$('.classSel').mysel({
    //    url : 'class/getClassInfoItems.infc',
    //    text : '班级',
    //    name : 'classId',
    //    isRequired : false
    //});

    $('#teacherSetBtn').click(function(){
        var params = $('#teacherSetForm').getValue();
        $.ajaxConnSend(this, 'scoExamScores/doTeacherSet.infc', params, function(data) {
            if (data.status == '1' && data.object) {
                $.alert_success('设置成功!');
                $('#queryBtn').trigger('click');
                $('#teacherSetBox').hide();
            } else {
                $.alert_error('设置失败');
            }
        }, function() {
            $.loadingBox.close();
        });
    });

    ////初始化页面查询
    //$('#queryBtn').trigger('click');

});