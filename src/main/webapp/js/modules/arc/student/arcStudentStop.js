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
            code: 'sexText',
            text: '性别'
        }, {
            code: 'identityCard',
            text: '身份证号'
        }, {
            code: 'majorText',
            text: '方向'
        }, {
            code: 'classText',
            text: '班级'
        },{
            code: 'statusText',
            text: '状态'
        }, {
            code: 'extInfo',
            text: '异动信息'
        }],
        url: 'student/doGetStudentChangeInfoPageData.infc',
        operColFun:function(i,rowdata){
            var id =  rowdata['id'];
            var status =  rowdata['status'];
            var btnObj = $('');
            var suspentionObj = $('<i href="###" class="iconfont bgColor-4" title="休" rownum="' + i + '">休</i>');
            suspentionObj.on('click', function () {
                var id = rowdata['id'];
                suspention(id);
            });
            var dropObj = $('<i href="###" class="iconfont bgColor-4" title="退" rownum="' + i + '">退</i>');
            dropObj.on('click', function () {
                var id = rowdata['id'];
                drop(id);
            });
            btnObj.after(suspentionObj);
            btnObj.after(dropObj);
            if(status == '11' || status == '12'){
                var gobackObj = $('<i href="###" class="iconfont bgColor-4" title="复" rownum="' + i + '">复</i>');
                gobackObj.on('click', function () {
                    var id = rowdata['id'];
                    goback(id);
                });
                btnObj.after(gobackObj);
            }
            return btnObj;
        }
    });

    function suspention(id){
        var params = {'id':id};
        $.loadingBox.show();
        $.ajaxConnSend(this, 'student/doModStudentSuspension.infc', params, function(data) {
            if (data.status == '1' && data.object) {
                $.alert_success('修改成功!');
                var curPage= parseInt($('.curPage').text());
                table.refreshData({curPage:curPage},"refresh");
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
                var curPage= parseInt($('.curPage').text());
                table.refreshData({curPage:curPage},"refresh");
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

    function goback(id){
        var params = {'id':id};
        $.loadingBox.show();
        $.ajaxConnSend(this, 'student/doModStudentGoBack.infc', params, function(data) {
            if (data.status == '1' && data.object) {
                $.alert_success('修改成功!');
                var curPage= parseInt($('.curPage').text());
                table.refreshData({curPage:curPage},"refresh");
            } else {
                $.alert_error('修改失败');
            }
        }, function() {
            $.loadingBox.close();
        });
    }

    /**退学信息修改start**/
    function dropInfoMod(){
        var items=table.getChecked();
        if(items.length==1){
            //winOpen=window.open( BAS_URL+"exam/initEditExamPlanDetail.do?id="+items[0].id,'DetailForExamPlan');
            $('#dropShowBox').show();
            $('#dropShowFormStuId').val(items[0].id);
        }else{
            $.alert_error('请选择一个学生');
        }
    }

    $('#dropInfoBtn').click(dropInfoMod);

    $('#dropInfoSubBtn').click(function(){
        var params = $('#dropShowForm').getValue();
        var starTime = params['starTime'];
        if(starTime != ''&& starTime != undefined){
            $.loadingBox.show();
            $.ajaxConnSend(this, 'student/doModStudentDropInfo.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('修改成功!');
                    var curPage= parseInt($('.curPage').text());
                    table.refreshData({curPage:curPage},"refresh");
                    $('#dropShowBox').hide();
                } else {
                    $.alert_error('修改失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }else{
            $.alert_error("请选择退学时间!");
        }
    });
    /**退学信息修改end**/

    /**休学信息修改start**/
    function suspentInfoMod(){
        var items=table.getChecked();
        if(items.length==1){
            //winOpen=window.open( BAS_URL+"exam/initEditExamPlanDetail.do?id="+items[0].id,'DetailForExamPlan');
            $('#suspentShowBox').show();
            $('#suspentShowFormStuId').val(items[0].id);
        }else{
            $.alert_error('请选择一个学生');
        }
    }

    $('#suspentInfoBtn').click(suspentInfoMod);

    $('#suspentInfoSubBtn').click(function(){
        var params = $('#suspentShowForm').getValue();
        var starTime = params['starTime'];
        var endTime = params['endTime'];
        if(starTime != ''&& starTime != undefined && endTime != ''&& endTime != undefined){
            $.loadingBox.show();
            $.ajaxConnSend(this, 'student/doModStudentSuspentInfo.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('修改成功!');
                    var curPage= parseInt($('.curPage').text());
                    table.refreshData({curPage:curPage},"refresh");
                    $('#suspentShowBox').hide();
                } else {
                    $.alert_error('修改失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }else{
            $.alert_error("请选择休学开始,结束时间!");
        }
    });
    /**退学信息修改end**/

    /**导出star**/
    $('#expBtn').click(function(){
        var params = $('#queryForm').getValue();
        var url = BAS_URL+'student/exportStuInfo.do';
        var formObj = $('<form id="expForm" target="_blank" action="'+url+'" style="display: none"></form>');
        for (var name in params) {
            formObj.append('<input name="'+name+'" value="'+params[name]+'" />')
        }
        $('body').append(formObj);
        formObj.submit();
        formObj.remove();
    });
    /**导出end**/
});