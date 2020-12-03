/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){

    //table
    var table = $('.dataTable').mytable({
        isCheck: true,
        header: [{
            code: 'name',
            text: '规则名'
        },{
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
        }
        ],
        url: 'sysScoreRule/doTchScoreRulePageData.infc',
        operColFun:function(i,rowdata){
            var operObj = $('<i href="###" class="iconfont icon-icon07" title="编辑" rownum="' + i + '"></i>');
            operObj.on('click', function () {
                var id = rowdata['id'];
                window.location.href = $.customOpt.url + '/sysScoreRule/iniTchRuleEdit.do?ruleId='+id;
            });
            return operObj;
        }
        // ,delFun: function(id) {
        //     $.alert_confirm('确定删除此记录?', function() {
        //         $.loadingBox.show();
        //         $.ajaxConnSend(this, 'sysScoreRule/doDelTchScoreRuleById.infc', {
        //             id:id
        //         }, function(data) {
        //             if (data.status == '1' && data.object) {
        //                 $.alert_success('操作成功');
        //                 table.refreshData();
        //             } else {
        //                 $.alert_error('操作失败');
        //             }
        //         },function(){
        //             $.loadingBox.close();
        //         })
        //     });
        // }
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
        window.location.href = BAS_URL+'sys/scoreRule/tchScoreRuleAdd.html';
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