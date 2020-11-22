/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){
    // 表单验证
    $("#queryForm").validationEngine("attach", {
        promptPosition : "topLeft",
        autoHidePrompt : true,
        autoHideDelay : 3000,
        scroll : false
    });

    $('.subjectType').mysel({
        url : 'common/doGetSubjectTypeItems.infc',
        text : '所属科目类型',
        name : 'subjectType',
        isRequired : false,
        id:'subjectType'
    });
    ///////////响应下拉框选择
    $('#subjectType').change(function(){
        var subjectType= $("#subjectType").find("option:selected").val();
        //班级选择
        $('.subjectId').empty();
        $('.subjectId').mysel({
            url : 'subject/doGetSubjectListsByParam.infc',
            text : '所属科目',
            name : 'subjectId',
            params : {"type":subjectType},
            isRequired : true,
            valueKey : 'id',
            textKey : 'name'
        });
    });
    $('.termSel').mysel({
        url : 'common/doGetTermItems.infc',
        text : '学期',
        name : 'term',
        isRequired : false
    });
    /**
     * 班级选择
     */
    $('.classSel').mysel({
        url : 'class/getClassInfoItems.infc',
        text : '班级',
        name : 'classId',
        isRequired : false
    });

    /**
     * 学年选择
     */
    $('.schoolYearSel').mysel({
        url : 'common/getFromToEduYearItems.infc',
        text : '学年',
        name : 'schoolYear',
        isRequired : false,
        id:'schoolYear'
    });

    //table
    var table = $('.dataTable').mytable({
        isCheck: true,
        idKeyName:'id',
        header: [{
            code: 'courseName',
            text: '课程名称'
        }, {
            code: 'subjectName',
            text: '科目名称'
        },{
            code: 'schoolYear',
            text: '学年'
        }, {
            code: 'termText',
            text: '学期'
        }, {
            code: 'teacherName',
            text: '授课教师'
        },  {
            code: 'className',
            text: '班级'
        },  {
            code: 'classroomText',
            text: '教室'
        },{
            code: 'teachBook',
            text: '使用教材'
        },  {
            code: 'otherBooks',
            text: '教辅资料'
        }
        ],
        url: 'courseOpen/doGetCourseOpensByTeacherPageData.infc',
        operColFun:function(i,rowdata){
            var id =  rowdata['id'];
            var status =  rowdata['status'];
            var operObj = $('<i href="###" class="iconfont icon-form bgColor-2" title="点名册" rownum="' + i + '" did="' + id + '"></i>');
            operObj.on('click', function () {
                showRollCallList(rowdata['id']);
            });
            return operObj;
        }
    });

    /**
     * 搜索
     */
    $('#queryBtn').click(function(){
        var bol = $("#queryForm").validationEngine("validate");
        if (bol) {
            var params = $('#queryForm').getValue();
            table.refreshData(params);
        }
    });

    /**
     * 查看点名册
     * @param comment
     */
    function showRollCallList(courseOpenId){
        winOpen=window.open( BAS_URL+"/tch/courseOpen/tchCourseOpenRollCall.html?id="+courseOpenId,'CourseOpenRollCall');
    }
});
