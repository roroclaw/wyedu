/**
 * Created by dengxianzhi on 2017/1/29.
 */
$(function(){

    // 表单验证
    $("#dataForm").validationEngine("attach", {
        promptPosition : "topLeft",
        autoHidePrompt : true,
        autoHideDelay : 3000,
        scroll : false
    });

    /**
     * 年级选择
     */
    //初始化学年信息
    var schoolYearSel = $('.schoolYearSel').mysel({
        url : 'common/getEduYearItems.infc',
        text : '入学年份',
        name : 'enrolYear',
        isRequired : true
    });

    /**
     * 毕业时间
     */
    //初始毕业时间信息
    var schoolYearSel = $('.graduateYearSel').mysel({
        url : 'common/getGraduateYearItems.infc',
        text : '毕业年份',
        name : 'graduateYear',
        isRequired : true
    });

    //初始化年级信息
    var gradeSel = $('#gradeSel').mysel({
        url : 'common/getGradeItems.infc',
        text : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年级',
        name : 'grade',
        isRequired : true
    });


    /**
     * 教室
     */
    //初始化教室信息
    var roomSel = $('.roomSel').mysel({
        url : 'classroom/getClassroomItems.infc',
        text : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;教室',
        name : 'classroomId',
        isRequired : true
    });

    var options = {
        serviceUrl: BAS_URL+'/teacher/getTeacherItems.infc',//获取数据的后台页面
        name:'teacherId'
        ,width: 233//提示框的宽度
        ,type:'POST'
        //,onSelect: onAutocompleteSelect//选中之后的回调函数
        ,triggerSelectOnValidInput:true
    };

    $('#teacherSel').autoInput(options);

    /**
     * 新增
     */
    $("#addBtn").click(function(){
        var bol = $("#dataForm").validationEngine("validate");
        if (bol) {
            var params = $('#dataForm').getValue();
            $.loadingBox.show();
            $.ajaxConnSend(this, 'class/doAddClass.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('新增成功!');
                    window.location.href = "../class/tchClass.html";
                } else {
                    $.alert_error('新增失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }
    });

    $('#backBtn').click(function(){
        window.location.href = "../class/tchClass.html";
    });

    $('#name').keydown(function(){
        var thisObj = $(this);
        thisObj.data('flag',true);
    });

    gradeSel.change(genrateClassName);
    $('#seqSel').change(genrateClassName);

    function genrateClassName(){
         var classNameCusFlag = $('#name').data('flag');
         if(!classNameCusFlag){
            var gradeText = gradeSel.find("option:selected").text();
            var gradeVal = gradeSel.val();
            var seq = $('#seqSel').find("option:selected").text();
            var seqVal = $('#seqSel').val();
            if(gradeVal != '' && seqVal != ''){
                 className = gradeText + '('+seq+')班';
                 $('#name').val(className);
            }else{
                $('#name').val('');
            }
         }
    }

});