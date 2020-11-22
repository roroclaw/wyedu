/**
 * Created by dxz on 2017/8/2.
 */
$(function(){

    /*==============初始化树插件star==============*/
    var setting = {
        //async: {
        //    enable: true,
        //    url: $.customOpt.url + "/role/doGetMenus4ZTree.infc"
        //    ,autoParam:["id"]
        //    //,otherParam:{"otherParam":"zTreeAsyncTest"},
        //    //,dataFilter: filter
        //},
        data: {
            simpleData: {
                enable: true
            }
        },
        check: {
            enable: true
        },
        callback: {
            //onCheck: checkFun
            //onAsyncSuccess: zTreeOnAsyncSuccess
        }
        //,view: {showIcon: true}
    };
    /*==============初始化树插件end==============*/
    //一次加载完所有权限树数据
    $.ajaxConnSend(null, 'role/doGetAllMenus4ZTree.infc',null, function(data) {
        if (data.status == '1' && data.object) {
            var treeData = data.object;
            $.fn.zTree.init($("#treeDiv"), setting,treeData);
        } else {
            $.alert_error('获取权限数据失败');
        }
    }, function() {
        $.loadingBox.close();
    });


    function refreshRoleData(){
        $('.roleTr').remove();
        /**
         * 初始化角色列表
         */
        $.ajaxConnSend(this, 'role/doGetRolesList.infc', {}, function (data) {
            var items = data['object'];
            var itemsHtml = '';
            for(var i =0 ; i < items.length;i++){
                var item = items[i];
                var roleId = item['id'];
                var roleName = item['roleName'];
                var type = item['type'];
                var typeText = item['typeText'];

                //组装按钮操作html
                var btnHtml = '';
                if(type > -1){
                    btnHtml += '<i href="###" class="iconfont icon-icon07 roleEditBtn" title="编辑" data-name="'+roleName+'"  data-id="'+roleId+'"></i>';
                    btnHtml += '<i href="###" class="iconfont icon-shanchu roleDelBtn" title="删除" data-id="'+roleId+'"></i>';
                }

                itemsHtml += '<tr class="roleTr" style="cursor: pointer;">'+
                '<td><input type="radio" name="roleCheck" class="roleRadio" pid="'+roleId+'"/></td>'+
                '<td>'+(i+1)+'</td>'+
                '<td>'+roleName+'</td>'+
                '<td>'+typeText+'</td>'+
                '<td>'+btnHtml+'</td>'+
                '</tr>';
            }
            $('#roleColTile').after(itemsHtml);
        });
    }

    refreshRoleData();

    //=====================角色表单操作star=============================//
    // 表单验证
    $(".dataForm").validationEngine("attach",{
        promptPosition : "topLeft",
        autoHidePrompt : true,
        autoHideDelay : 3000,
        scroll : false
    });

    //新增按钮
    $('#addBtn').click(function(){
        $('#roleAddShowBox').show();
    });

    //删除按钮
    $(document).on('click','.roleDelBtn',function(){
        var id = $(this).data('id');
        $.loadingBox.show();
        $.ajaxConnSend(this, 'role/doDelRole.infc',{id:id}, function(data) {
            if (data.status == '1' && data.object) {
                $.alert_success('删除成功!');
                refreshRoleData();
            } else {
                $.alert_error('删除失败');
            }
        }, function() {
            $.loadingBox.close();
        });
    });

    //修改按钮
    $(document).on('click','.roleEditBtn',function(){
        var id = $(this).data('id');
        var name = $(this).data('name');
        $('#roleModForm').setValue({id:id,roleName:name});
        $('#roleModShowBox').show();
    });

    $('#roleAddSubBtn').click(function(){
        var bol = $("#roleAddForm").validationEngine("validate");
        if (bol) {
            var params = $('#roleAddForm').getValue();
            $.loadingBox.show();
            $.ajaxConnSend(this, 'role/doAddRole.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('新增成功!');
                    refreshRoleData();
                    $('#roleAddShowBox').hide();
                } else {
                    $.alert_error('新增失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }
    });

    $('#roleModSubBtn').click(function(){
        var bol = $("#roleModForm").validationEngine("validate");
        if (bol) {
            var params = $('#roleModForm').getValue();
            var id = params['id'];
            if(id == undefined || id == null || id == ''){
                $.alert_error("无此角色信息!");
                return false;
            }
            $.loadingBox.show();
            $.ajaxConnSend(this, 'role/doModRole.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('修改成功!');
                    refreshRoleData();
                    $('#roleModShowBox').hide();
                } else {
                    $.alert_error('修改失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }
    });
    //=====================角色表单操作end=============================//

    $(document).on('click','.roleTr',function(){
         var thisObj = $(this);
         $('.roleRadio',thisObj).attr('checked','true');
         refreshRoleMenus();
    });

    //刷新角色菜单信息
    function refreshRoleMenus(){
        var zTree = $.fn.zTree.getZTreeObj("treeDiv");
        zTree.checkAllNodes(false);
         //获取选中角色信息
        var roleId = $('.roleRadio:checked').attr('pid');
        $.ajaxConnSend(this, 'role/doGetPowersByRoleId.infc', {roleId:roleId}, function(data) {
            if (data.status == '1' && data.object) {
                var items = data.object;

                for(var i = 0 ; i<items.length;i++){
                    var item = items[i];
                    var node = zTree.getNodeByParam("id",item['menuId']);
                    if(!node['isParent']){
                        zTree.checkNode(node, true, true);
                    }
                }
            } else {
                $.alert_error('获取角色权限失败');
            }
        }, function() {
            $.loadingBox.close();
        });

    }

    //=================角色权限提交star
    function filter(node) {   //过滤器直选中2级节点累加
        return (node.checked == true);
    }

    $('#powerSubBtn').click(function(){
        //获取用户信息
        var roleId = $('.roleRadio:checked').attr('pid');
        if(roleId == undefined){
            $.alert_error("请选择角色!");
            return false;
        }
        //获取权限信息
        var zTree = $.fn.zTree.getZTreeObj("treeDiv").getNodesByFilter(filter);
        var choose = "";
        var len = zTree.length;
        if(len == 0){
            $.alert_error("请选择权限!");
            return false;
        }
        for (var i=0;i < len;i++) {
            var treeNode = zTree[i];
            if(treeNode.name !=null && treeNode.id != null){
                choose +=(i == (zTree.length-1))?treeNode.id:treeNode.id+",";
            }
        }
        var params = {
            roleId : roleId,
            powerIds : choose
        };
        //提交关系数据
        $.ajaxConnSend(this, 'role/doSubRolePower.infc', params, function(data) {
            if (data.status == '1' && data.object) {
                $.alert_success('修改权限成功!');
                refreshRoleMenus();
            } else {
                $.alert_error('修改权限失败');
            }
        }, function() {
            $.loadingBox.close();
        });

    });
    //=================角色权限提交end
});