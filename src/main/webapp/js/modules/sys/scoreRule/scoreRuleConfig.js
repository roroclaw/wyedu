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
        header: [{
            code: 'subjectName',
            text: '科目'
        },{
            code: 'middelRatio',
            text: '期中成绩比例'
        }, {
            code: 'endRatio',
            text: '期末成绩比例'
        }, {
            code: 'usualRatio',
            text: '平时成绩比例'
        },{
            code: 'scopeName',
            text: '适用范围'
        }
        ],
        url: 'sysScoreRule/doScoreRulePageData.infc',
        operColFun:function(i,rowdata){
            var operObj = $('<i href="###" class="iconfont icon-icon07" title="编辑" rownum="' + i + '"></i>');
            operObj.on('click', function () {
                var id = rowdata['id'];
                window.location.href = $.customOpt.url + '/sysScoreRule/initEdit.do?ruleId='+id;
            });
            return operObj;
        },
        delFun: function(id) {
            $.alert_confirm('确定删除此记录?', function() {
                $.loadingBox.show();
                $.ajaxConnSend(this, 'sysScoreRule/doDelScoreRuleById.infc', {
                    id:id
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
            });
        }
    });

    /**
     * 搜索
     */
    $('#queryBtn').click(function(){
        var params = $('#queryForm').getValue();
        table.refreshData(params);
    });

    //初始化科目选择
    $('.subjectSel').mysel({
        url : 'subject/doGetSubjectListsByParam.infc',
        text : '科目',
        name : 'subjectId',
        valueKey : 'id',
        textKey : 'name',
        isRequired : false
    });

    $('#addBtn').click(function(){
        window.location.href = BAS_URL+'sys/scoreRule/scoreRuleAdd.html';
    });

    $('#calBtn').click(function(){
        var items=table.getChecked();
        if(items.length==1){
            var item = items[0];
            $('#ruleFormId').val(item['id']);
            var showBox = $.showPopupForm('#showForm',function(){
                $.loadingBox.show();
                var params = $('#showForm').getValue();
                //params['subjectId'] = subjectId;
                if(params['schoolYear'] == null || params['schoolYear'] == ''){
                    $.alert_error("请选择学年!");
                    $.loadingBox.close();
                    return false;
                }
                $.ajaxConnSend(this, 'sysScoreRule/calSocresBySubjectId.infc',params,function (data) {
                    if (data.status == '1' && data.object) {
                        $.alert_success('计算成功!');
                        showBox.close();
                    } else {
                        $.alert_error('计算失败');
                    }
                }, function() {
                    $.loadingBox.close();
                });
            });
        }else{
            $.alert_error('请选中一个科目');
        }
    });

    $('.termSel').mysel({
        url : 'common/doGetTermItems.infc',
        text : '学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期',
        name : 'term',
        isRequired : false
    });

    $('.schoolYearSel').mysel({
        url : 'common/getFromToEduYearItems.infc',
        text : '学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年',
        name : 'schoolYear',
        isRequired : true
    });

});