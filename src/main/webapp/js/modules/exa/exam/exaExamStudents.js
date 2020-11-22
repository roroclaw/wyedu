/**
 * Created by dxz on 2017/6/20.
 */
$(function(){
    var examId = $('#examId').val();
    var subjectId = $('#subjectId').val();
    var examPlanId= $('#examPlanId').val();
    var examDate= $('#examDate').val();
    var examTime_s= $('#examTime_s').val();
    var examTime_e= $('#examTime_e').val();

    $('#examDateSpan').html("考试日期："+ $.dateFormate(examDate, 'yyyy-MM-dd'));
    $('#examTimeSpan_s').html("考试时间："+ $.formateTime(examTime_s));
    $('#examTimeSpan_e').html("-----"+ $.formateTime(examTime_e));
    //console.debug(examTime_s);
    // 表单验证
    $("#addForm").validationEngine("attach", {
        promptPosition : "topLeft",
        autoHidePrompt : true,
        autoHideDelay : 3000,
        scroll : false
    });

    //下拉框设置
   /* $('.buildingSel').mysel({
        url : 'common/getbuildingNoItems.infc',
        text : '&nbsp;教&nbsp;学&nbsp;楼',
        name : 'buildingNo',
        isRequired : true,
        id:'buildingNo'
    });

    ///////////响应下拉框选择
    $('.buildingSel').change(function(){
        var buildingNo= $("#buildingNo").find("option:selected").val();
        $('.examroomSel').empty();
        $('.examroomSel').mysel({
            url : 'examRoom/getExamRoomItems.infc',
            text : '考场',
            name : 'examRoomId',
            params : {"examPlanId":examPlanId},
            isRequired : true,
            valueKey : 'code',
            textKey : 'text'
        });
    })
*/

    $('.schoolYearSel').mysel({
        url : 'common/getFromToEduYearItems.infc',
        text : '学年',
        name : 'schoolYear',
        isRequired : false,
        id:'schoolYear',
        callbackFun:function(){ $('.schoolYearSel').append("<span style='line-height: 35px;background: #fff;'>(班级筛选条件)</span>");}
    });


    $('.gradeSel').mysel({
        url : 'common/getGradeItems.infc',
        text : '年级',
        name : 'grade',
        isRequired : false,
        id:'grade',
        callbackFun:function(){ $('.gradeSel').append("<span style='line-height: 35px;background: #fff;'>(班级筛选条件)</span>");}
    });
    ///////////响应下拉框选择
    $('#grade').change(function(){
        var gradeCode= $("#grade").find("option:selected").val();
        var schoolYear= $("#schoolYear").find("option:selected").val();
        //班级选择
        if(gradeCode && schoolYear){
            $.ajaxConnSend(this, 'common/getGradeInfoByCode.infc', {code:gradeCode}, function(data) {
                gradeObj = data['object'];
                $('.classSel').empty();
                $('.classSel').mysel({
                    url : 'class/getClassInfoItemsForPower.infc',
                    text : '班级',
                    name : 'classId',
                    id:'classId',
                    isRequired : true,
                    params : {"grade":gradeCode,"schoolYear":schoolYear,"period":gradeObj.period}
                });
            }, function() {
            });
        }
    });
        $('.courseSel').mysel({
            url : 'common/getCourseItems.infc',
            text : '所属课程',
            name : 'courseId',
            params : {"subjectId":subjectId},
            isRequired : true
        });

    //////////////////////开始显示教学计划内容
    $('.schoolYearSel_2').mysel({
        url : 'common/getFromToEduYearItems.infc',
        text : '学年',
        name : 'schoolYear',
        isRequired : false,
        id:'schoolYear_2',
        callbackFun:function(){ $('.schoolYearSel_2').append("<span style='line-height: 35px;background: #fff;'>(班级筛选条件)</span>");}
    });
    $('.gradeSel_2').mysel({
        url : 'common/getGradeItems.infc',
        text : '年级',
        name : 'grade',
        isRequired : false,
        id:'grade_2',
        callbackFun:function(){ $('.gradeSel_2').append("<span style='line-height: 35px;background: #fff;'>(班级筛选条件)</span>");}
    });
    ///////////响应下拉框选择
    $('#grade_2').change(function(){
        var gradeCode= $("#grade_2").find("option:selected").val();
        var schoolYear= $("#schoolYear_2").find("option:selected").val();
        //班级选择
        if(gradeCode){
            $.ajaxConnSend(this, 'common/getGradeInfoByCode.infc', {code:gradeCode}, function(data) {
                gradeObj = data['object'];
                $('.classSel_2').empty();
                $('.classSel_2').mysel({
                    url : 'class/getClassInfoItemsForPower.infc',
                    text : '班级',
                    name : 'classId',
                    id:'classId_2',
                    isRequired : true,
                    params : {"grade":gradeCode,"schoolYear":schoolYear,"period":gradeObj.period}
                });
            }, function() {
            });
        }
    });

    //courseOpenTable
    var Table = $('#courseOpenTable').mytable({
        isCheck: true,
        idKeyName:'id',
        header: [{
            code: 'courseName',
            text: '课程名称'
        }, {
            code: 'schoolYear',
            text: '学年',
            formateFun:function(txt, i, code, rowdata){
                var schoolYear_int=parseInt(rowdata.schoolYear)+1;
                dateChar=  rowdata.schoolYear+"---"+schoolYear_int;
                return dateChar;
            }
        },  {
            code: 'className',
            text: '班级'
        }, {
            code: 'termText',
            text: '学期'
        }],
        url: 'courseOpen/doGetCourseOpensPageData.infc'
    });


    //subjecTable
    var examStudentTable = $('#examStudentTable').mytable({
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
        params:{"examId":examId},
        url: 'exam/doGetExamStudentsPageData.infc',
        operColFun:function(i,rowdata){
            var id =  rowdata['id'];
            var status =  rowdata['status'];
            if(rowdata['examStatus']=="0"){
                if(status == 1){
                    var operObj = $('<i href="###" class="iconfont icon-shibai" title="缺考" rownum="' + i + '" did="' + id + '"></i>');
                    operObj.on('click', function () {
                        changExamStuStatus(id,"2");
                    });
                }else if(status == 2){
                    var operObj = $('<i href="###" class="iconfont icon-shibai" title="缓考" rownum="' + i + '" did="' + id + '"></i>');
                    operObj.on('click', function () {
                        changExamStuStatus(id,"3");
                    });
                }else if(status == 3){
                    var operObj = $('<i href="###" class="iconfont icon-shibai" title="借读"  rownum="' + i + '" did="' + id + '"></i>');
                    operObj.on('click', function () {
                        changExamStuStatus(id,"5");
                    });
                }else if(status == 5){
                    var operObj = $('<i href="###" class="iconfont icon-zhengque" title="正常"  rownum="' + i + '" did="' + id + '"></i>');
                    operObj.on('click', function () {
                        changExamStuStatus(id,"1");
                    });
                }
            }
            return operObj;
        }
    });

    function changExamStuStatus(id,status){
        $.ajaxConnSend(this, 'exam/doModExamStudentsStatus.infc', {
            id: id,status:status
        }, function(data) {
            if (data.status == '1' && data.object) {
                $.alert_success('操作成功');
                examStudentTable.refreshData();
            } else {
                $.alert_error('操作失败');
            }
        },function(){
            $.loadingBox.close();
        })
    }

    /**
     * 搜索
     */
    $('#queryBtn').click(function(){
        var params = $('#queryForm').getValue();
        Table.refreshData(params);
    });

    /**
     * 搜索
     */
    $('#resultQueryBtn').click(function(){
        var params = $('#resultQueryForm').getValue();
        params['params']=examId;
        examStudentTable.refreshData(params);
    });

    /**
     * 根据开课信息设置参考人员
     */
    $("#addBtn").click(function(){
        var bol = $("#addForm").validationEngine("validate");
        var items=Table.getChecked();
        if(items.length>0){
            if (bol) {
                var courseOpenIDs="";
                for(var i=0;i<items.length;i++){//////////组装ID组
                    if(i==0){
                        courseOpenIDs=courseOpenIDs+items[i].id;
                    }else{
                        courseOpenIDs=courseOpenIDs+"#"+items[i].id;
                    }
                }
                var params = $('#addForm').getValue();
                params["courseOpenIDs"]=courseOpenIDs;
                $.loadingBox.show();
                $.ajaxConnSend(this, 'exam/doAddExamStudents.infc', params, function(data) {
                    if (data.status == '1' && data.object) {
                        $.alert_success('新增成功!');
                        examStudentTable.refreshData();
                    } else {
                        $.alert_error('新增失败');
                    }
                }, function() {
                    $.loadingBox.close();
                });
            }
        }else{
            $.alert_error('请至少选中一个科目');
        }

    });

    $('#backBtn').click(function(){
        window.location.href = "../exa/exam/exaExam.html";
    });
});

