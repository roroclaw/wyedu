/**
 * Created by dxz on 2017/6/20.
 */
$(function(){
    // 表单验证
    $("#addForm").validationEngine("attach", {
        promptPosition : "topLeft",
        autoHidePrompt : true,
        autoHideDelay : 3000,
        scroll : false
    });

   /* //下拉框设置
    $('.gradeSel').mysel({
        url : 'common/getGradeItems.infc',
        text : '年级',
        name : '',
        isRequired : false,
        id:'grade'
    });*/
    $('.classSel').mysel({
        url : 'class/getClassInfoItems.infc',
        text : '班级',
        name : 'classId',
        isRequired : true
    });

    $('.majorSel').mysel({
        url : 'common/getMajorItems.infc',
        text : '专业',
        name : 'majorId',
        isRequired : true
    });
    $('.sexSel').mysel({
        url : 'common/getSexItems.infc',
        text : '性别',
        name : 'sex',
        isRequired : false
    });
  /*  ///////////响应下拉框选择
    $('#grade').change(function(){
        var grade= $("#grade").find("option:selected").val();
        //班级选择
        $('.classroomSel').empty();
        $('.classroomSel').mysel({
            url : 'class/getClassInfoItems.infc',
            text : '班级',
            name : 'classId',
            params : {"grade":grade},
            isRequired : true,
            valueKey : 'code',
            textKey : 'text'
        });
    })*/
    //////////////////////开始显示教学计划内容
    var courseOpenId = $('#courseOpenId').val();



    var table = $('#studentsTable').mytable({
        isCheck: true,
        idKeyName:'stuId',
        header: [{
        code: 'stuNum',
        text: '学号'
        }, {
            code: 'realName',
            text: '姓名'
        },  {
            code: 'sexText',
            text: '性别'
        }, {
            code: 'identityCard',
            text: '身份证号'
        },  {
            code: 'className',
            text: '班级'
        }, {
            code: 'stuMajorText',
            text: '专业'
        }, {
            code: 'statusText',
            text: '状态'
        }],
        url: 'student/doGetStudentInfoPageDataByParam.infc'
    });

    /**
     * 搜索
     */
    $('#queryBtn').click(function(){
        var params = $('#queryForm').getValue();
        table.refreshData(params);
    });
    /**
     * 添加科目
     */
    $("#addBtn").click(function(){
        var bol = $("#addForm").validationEngine("validate");
        var items=table.getChecked();
        if(items.length>0){
            if (bol) {
                var studentIDs="";
                for(var i=0;i<items.length;i++){//////////组装ID组
                    if(i==0){
                        studentIDs=studentIDs+items[i].stuId;
                    }else{
                        studentIDs=studentIDs+"#"+items[i].stuId;
                    }
                }
                var params = $('#addForm').getValue();
                params["studentIds"]=studentIDs;
                $.loadingBox.show();
                $.ajaxConnSend(this, 'courseOpen/doAddCourseStudentRel.infc', params, function(data) {
                    if (data.status == '1' && data.object) {
                        $.alert_success('新增成功!');
                        //////////////////////开始显示教学计划内容
                        stuRelTable.refreshData();
                    } else {
                        $.alert_error('新增失败');
                    }
                }, function() {
                    $.loadingBox.close();
                });
            }
        }else{
            $.alert_error('请至少选中一位学生');
        }

    });

    $('#backBtn').click(function(){
        window.location.href = "../tch/courseOpen/tchCourseOpen.html";
    });

    /**
     * 查询显示内容
     *
     *
     */

    //stuRelTable
    var stuRelTable = $('#stuRelTable').mytable({
        isCheck: false,
        isPage: true,
        _recordsPerpage: 1,
        idKeyName:'id',
        header: [{
            code: 'stuNumber',
            text: '学号'
        },{
            code: 'realName',
            text: '姓名'
        },{
            code: 'identityCard',
            text: '身份证号'
        },  {
            code: 'stuGrade',
            text: '年级'
        },{
            code: 'className',
            text: '班级'
        },  {
            code: 'majorText',
            text: '专业'
        },  {
            code: 'schoolYear',
            text: '课程学年'
        }],
        params:{"courseOpenId":courseOpenId},
        url: 'courseOpen/doGetTchCourseOpenStudentsPageData.infc',
        delFun: function(id) {
            $.alert_confirm('确定删除此记录?', function() {
                $.loadingBox.show();
                $.ajaxConnSend(this, 'courseOpen/doDelTchStuCourseOpenRelById.infc', {
                    id:id,status:status
                }, function(data) {
                    if (data.status == '1' && data.object) {
                        $.alert_success('操作成功');
                        stuRelTable.refreshData();
                    } else {
                        $.alert_error('操作失败');
                    }
                },function(){
                    $.loadingBox.close();
                })
            });
        }
    });


});