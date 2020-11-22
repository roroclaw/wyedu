/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){

    $('.majorSel').mysel({
        url : 'major/getFatherMajorItems.infc',
        text : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;专&nbsp;&nbsp;&nbsp;&nbsp;业',
        name : 'majorId',
        params:{type:"0"},
        isRequired : true,
        id:'majorSel'
    });
    $('#majorSel').change(function(){
        var majorId= $("#majorSel").find("option:selected").val();
        //班级选择
        if(majorId){
            $('.teachingPlanSel').empty();
            $('.teachingPlanSel').mysel({
                            url : 'common/doGetTeachingPlanItems.infc',
                            text : '教学计划',
                            name : 'planId',
                            isRequired : true,
                            params : {"majorId":majorId}
            });
        }
    });


    //初始化角色信息
    $('.statusSel').mysel({
        url : 'common/doGetUserInfoStatus.infc',
        text : '状态',
        name : 'status',
        isRequired : false
    });

    $('.gradeSel').mysel({
        url : 'common/getGradeItems.infc',
        text : '年级',
        name : 'grade',
        isRequired : true,
        id:'grade'
    });

    ///////////响应下拉框选择
    var schoolYear="";
    $('#grade').change(function(){
        var gradeCode= $("#grade").find("option:selected").val();
      //  var schoolYear= $("#schoolYear").find("option:selected").val();
        //班级选择
        if(gradeCode ){
            $.ajaxConnSend(this, 'common/getCurSchoolYearSig.infc', {code:gradeCode}, function(data) {
                yearObj = data['object'];
                schoolYear=yearObj;
            }, function() {
                if(gradeCode && schoolYear){
                    $.ajaxConnSend(this, 'common/getGradeInfoByCode.infc', {code:gradeCode}, function(data) {
                        gradeObj = data['object'];
                        console.debug(gradeObj);
                        $('.classSel').empty();
                        $('.classSel').mysel({
                            url : 'class/getClassInfoItemsForPower.infc',
                            text : '班级',
                            name : 'classId',
                            isRequired : true,
                            params : {"grade":gradeCode,"schoolYear":schoolYear,"period":gradeObj.period}
                        });
                    }, function() {
                    });
                }

            });
        }
    });

    //table
    var table = $('.dataTable').mytable({
        isCheck: true,
        idKeyName:'id',
        header: [{
            code: 'stuNumber',
            text: '学号'
        }, {
            code: 'realName',
            text: '名字'
        }, {
            code: 'identityCard',
            text: '身份证号'
        }, {
            code: 'stuMajorText',
            text: '学生方向'
        }, {
            code: 'planMajorText',
            text: '教学计划'
        }, {
            code: 'planMajorText',
            text: '计划方向'
        }, {
            code: 'statusText',
            text: '状态'
        }],
        url: 'student/doGetStudentInfoPageDataByParam.infc',
        modFun:function(id) {
            var showBox = $.showPopupForm('#teachingPlanForm',function(){
                var bol = $("#teachingPlanForm").validationEngine("validate");
                if (bol) {
                    var formData = $("#teachingPlanForm").getValue();
                    formData['relId'] = id;
                    console.debug(formData);
                    $.loadingBox.show();
                   /* $.ajaxConnSend(this, 'student/doModStuTeachingPlan.infc',formData, function(data) {
                        $.alert_success("修改成功!");
                        $('#queryBtn').trigger('click');
                        showBox.close();
                    },function(){
                        $.loadingBox.close();
                    })*/
                }
            });
        }
        /*,delFun: function(id) {
            $.alert_confirm('确定删除此记录?', function() {
                $.loadingBox.show();
                $.ajaxConnSend(this, 'student/doDelStuById.infc', {
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
        },
        changeStatusFun:function(id,status) {
         // console.debug(id+"|||"+status);

            $.loadingBox.show();
            $.ajaxConnSend(this, 'student/doModStudentInfoStatus.infc', {
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
        var params = $('#queryForm').getValue();
        params['grade']="";
        table.refreshData(params);
    });

    /**
     * 新增
     */
    $('#addBtn').click(function(){
         window.location.href = $.customOpt.url +'arc/student/studentAddForm.html';
    });

    $('#doEditBtn').click(function(){
        var bol = $("#editStudentForm").validationEngine("validate");
        if (bol) {
            var params = $('#editStudentForm').getValue();
            console.debug(params.type);
            var birthday =params["birthday"].replace(/-/g,"/");
            params["birthday"] = new Date(birthday);

            $.loadingBox.show();
            $.ajaxConnSend(this, 'student/doUpdateStudentInfo.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('修改成功!');
                    changeDivShow("_1",2);
                    // var table = $('.dataTable').mytable();

                    var curPage= parseInt($('.curPage').text());
                    table.refreshData({curPage:curPage},"refresh");
                } else {
                    $.alert_error('修改失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }
    });

    $('.oper_btn_fanhui').click(function(){
        changeDivShow("_1",2);
    });
});