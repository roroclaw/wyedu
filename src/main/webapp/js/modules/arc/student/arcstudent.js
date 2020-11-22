/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){
    //初始化角色信息
    $('.statusSel').mysel({
        url : 'common/doGetUserInfoStatus.infc',
        text : '状态',
        name : 'status',
        isRequired : false
    });

    $('.classSel').mysel({
        url : 'class/getClassInfoItems.infc',
        text : '班级',
        name : 'classId',
        isRequired : false
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
            code: 'majorText',
            text: '方向'
        }, {
            code: 'classText',
            text: '班级'
        }, {
            code: 'statusText',
            text: '状态'
        }],
        url: 'student/doGetStudentInfoPageData.infc',
        modFun:function(id) {
            if(typeof(winOpen)=="object"){
                winOpen.close();             //关闭之前打开的窗口
            }
            winOpen=window.open(BAS_URL+"/student/initEdit.do?id="+id,'studentAddForm');
        },
        delFun: function(id) {
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
        }
        //,operColFun:function(i,rowdata){
        //    var id =  rowdata['id'];
        //    var status =  rowdata['status'];
        //    var btnObj = $('');
        //    var suspentionObj = $('<i href="###" class="iconfont bgColor-4" title="休" rownum="' + i + '">休</i>');
        //    suspentionObj.on('click', function () {
        //        var id = rowdata['id'];
        //        suspention(id);
        //    });
        //    var dropObj = $('<i href="###" class="iconfont bgColor-4" title="退" rownum="' + i + '">退</i>');
        //    dropObj.on('click', function () {
        //        var id = rowdata['id'];
        //        drop(id);
        //    });
        //    btnObj.after(suspentionObj);
        //    btnObj.after(dropObj);
        //    if(status == '11' || status == '12'){
        //        var gobackObj = $('<i href="###" class="iconfont bgColor-4" title="复" rownum="' + i + '">复</i>');
        //        gobackObj.on('click', function () {
        //            var id = rowdata['id'];
        //            goback(id);
        //        });
        //        btnObj.after(gobackObj);
        //    }
        //    return btnObj;
        //}
    });

    function suspention(id){
            var params = {'id':id};
            $.loadingBox.show();
            $.ajaxConnSend(this, 'student/doModStudentSuspension.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('修改成功!');
                    table.refreshData();
                } else {
                    $.alert_error('修改失败');
                }
            }, function() {
                $.loadingBox.close();
            });
    }

    function drop(id){
        var params = {'id':id};
        $.loadingBox.show();
        $.ajaxConnSend(this, 'student/doModStudentDrop.infc', params, function(data) {
            if (data.status == '1' && data.object) {
                $.alert_success('修改成功!');
                table.refreshData();
            } else {
                $.alert_error('修改失败');
            }
        }, function() {
            $.loadingBox.close();
        });
    }

    function goback(id){
        var params = {'id':id};
        $.loadingBox.show();
        $.ajaxConnSend(this, 'student/doModStudentGoBack.infc', params, function(data) {
            if (data.status == '1' && data.object) {
                $.alert_success('修改成功!');
                table.refreshData();
            } else {
                $.alert_error('修改失败');
            }
        }, function() {
            $.loadingBox.close();
        });
    }

    /**
     * 搜索
     */
    $('#queryBtn').click(function(){
        var params = $('#queryForm').getValue();
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