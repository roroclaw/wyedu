/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){
    //初始化角色信息
    $('.statusSel').mysel({
        url : 'common/doGetUserStatus.infc',
        text : '状态',
        name : 'userStatus',
        isRequired : false
    });

    //table
    var table = $('.dataTable').mytable({
        isCheck: true,
        //idKeyName:'userId',
        header: [{
            code: 'realName',
            text: '姓名'
        }, {
            code: 'loginName',
            text: '用户名'
        }, {
            code: 'registerDate',
            text: '注册时间',
            formateFun: $.formateDateTime
        }, {
            code: 'typeText',
            text: '类型'
        }, {
            code: 'statusText',
            text: '账户状态'
        }
        ],
        url: 'user/doGetUserPageData.infc',
        modFun:function(id) {
            var url =
            window.location.href = $.customOpt.url + '/user/initEdit.do?id='+id;
            //$.loadingBox.show();
            //$.ajaxConnSend(this, '', {
            //    id: id
            //}, function(data) {
            //    if (data.status == '1' && data.object) {
            //        $.alert_success('删除成功');
            //        table.refreshData();
            //    } else {
            //        $.alert_error('删除失败');
            //    }
            //},function(){
            //    $.loadingBox.close();
            //});
        },
        delFun: function(id) {
            $.alert_confirm('确定删除此记录?', function() {
                $.loadingBox.show();
                $.ajaxConnSend(this, 'user/doDelUserById.infc', {
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
            var statusInt=parseInt(status);
            $.loadingBox.show();
            $.ajaxConnSend(this, 'user/doModUserStatus.infc', {
                id:id,status:statusInt
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
    });

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
         window.location.href = $.customOpt.url +'sys/user/userAddForm.html';
    });

    /**
     * 新增
     */
    $('#passWordReset').click(function(){
        var items=table.getChecked();
        if(items.length==1){
            $.alert_confirm('确定重置此人的密码?', function() {
                $.loadingBox.show();
                $.ajaxConnSend(this, 'user/doResetPwd.infc', {
                    id:items[0].id
                }, function(data) {
                    if (data.status == '1' && data.object) {
                        $.alert_success('重置成功');
                        table.refreshData();
                    } else {
                        $.alert_error('重置失败');
                    }
                },function(){
                    $.loadingBox.close();
                })
            });
        }else{
            $.alert_error('一次只能选择一个用户。');
        }
    });

    //$('#doEditBtn').click(function(){
    //    var bol = $("#editUserForm").validationEngine("validate");
    //    if (bol) {
    //        var params = $('#editUserForm').getValue();
    //       // console.debug(params.type);
    //        ////////角色数据组合
    //        var role_name_list="";
    //        for(var i=0;i<params.roleIds.length;i++){
    //            role_name_list=role_name_list+","+params.roleIds[i];
    //        }
    //        role_name_list=role_name_list.substring(1);
    //        params["roleIds"]=role_name_list;
    //        $.loadingBox.show();
    //        $.ajaxConnSend(this, 'user/doUpdateUser.infc', params, function(data) {
    //            if (data.status == '1' && data.object) {
    //                $.alert_success('修改成功!');
    //                changeDivShow("_1",2);
    //                // var table = $('.dataTable').mytable();
    //
    //                var curPage= parseInt($('.curPage').text());
    //                table.refreshData({curPage:curPage},"refresh");
    //            } else {
    //                $.alert_error('修改失败');
    //            }
    //        }, function() {
    //            $.loadingBox.close();
    //        });
    //    }
    //});


});