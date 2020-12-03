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
            text: '学年',
            formateFun:function(txt, i, code, rowdata){
                var dateChar="";
                var schoolYear=rowdata.schoolYear;
                var schoolYearInt=parseInt(schoolYear)+1;
                dateChar= schoolYear+"---"+schoolYearInt;
                return dateChar;
            }
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
        }
        ],
        url: 'courseOpen/doGetCourseOpensForEntranceExamPageData.infc',
        modFun:function(id) {
            if(typeof(winOpen)=="object"){
                winOpen.close();             //关闭之前打开的窗口
            }
            winOpen=window.open( BAS_URL+"/courseOpen/initEdit.do?id="+id,'courseOpenModForm');
        },
        delFun: function(id) {
         $.alert_confirm('确定删除此记录?', function() {
         $.loadingBox.show();
         $.ajaxConnSend(this, 'courseOpen/doDelTchCourseOpenById.infc', {
         id: id
         }, function(data) {
         if (data.status == '1' && data.object) {
         $.alert_success('删除成功');
         table.refreshData();
         } else {
         $.alert_error('删除失败');
         }
         },function(){
         $.loadingBox.close();
         })
         });
         }
        /*,
        changeStatusFun:function(id,status) {
            // console.debug(data+"|||"+j);
            $.loadingBox.show();
            $.ajaxConnSend(this, 'course/doModCourseInfo.infc', {
                id:id,status:status
            }, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('操作成功');
                    table.refreshData();
                } else {
                    $.alert_error('操作失败');
                }
            },function(){
                $.loadingBox.close();
            })
        }*/
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
     * 学生编排
     */
    $('#relMod').click(function(){
        var items=table.getChecked();
        if(items.length==1){
            window.location.href = BAS_URL+"courseOpen/initEditForEntranceExamStudentRel.do?id="+items[0].id;
        }else{
            $.alert_error('请选中一个已开课程');
        }
    });

});
