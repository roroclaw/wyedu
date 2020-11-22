/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){

    $('.schoolYearSel').mysel({
        url : 'common/getFromToEduYearItems.infc',
        text : '学年',
        name : 'schoolYear'
    });

    $('.termSel').mysel({
        url : 'common/doGetTermItems.infc',
        text : '学期',
        name : 'term'
    });

    $('.majorSel').mysel({
        url : 'common/getSubMajorItems.infc',
        text : '专业',
        name : 'majorId',
        isRequired : true
    });

    $('.classSel').mysel({
        url : 'class/getClassInfoItems.infc',
        text : '班级',
        name : 'classId',
        isRequired : true
    });


    //table
    var table = $('.dataTable').mytable({
        isCheck: true,
        isInitRefresh: false,
        //idKeyName:'userId',
        header: [{
            code: 'stuNumber',
            text: '学号'
        }, {
            code: 'stuName',
            text: '姓名'
        }, {
            code: 'majorText',
            text: '专业'
        }, {
            code: 'classText',
            text: '班级'
        }, {
            code: 'statusText',
            text: '状态',
            formateFun: function(statusText){
                 if(statusText == null || statusText == ''){
                    return '未填报';
                 }else {
                     return statusText;
                 }
            }
        }, {
            code: 'updateTime',
            text: '录入时间',
            formateFun: $.formateDateTime
        }
        ],
        url: 'comment/doGetScoCommentPageData.infc',
        idKeyName:'stuId',
        // modFun:function(id) {
        //     stuId,String schoolYear,String term
        //     window.location.href = $.customOpt.url + '/comment/initEdit.do?id='+id;
        // },
        operColFun:function(i,rowdata){
                    var goRecordScoresObj = $('<i href="###" class="iconfont  icon-icon07 " title="填报" rownum="' + i + '"></i>');
                    goRecordScoresObj.on('click', function () {
                        var stuId = rowdata['stuId'];
                        var term = rowdata['term'];
                        var schoolYear = rowdata['schoolYear'];
                        // window.location.href = $.customOpt.url + '/comment/initEdit.do?stuId='+stuId+'&schoolYear='+schoolYear+'&term='+term;
                        window.open($.customOpt.url + '/comment/initEdit.do?stuId='+stuId+'&schoolYear='+schoolYear+'&term='+term,'scoCommentEdit');
                    });
                    return goRecordScoresObj;
        }
      });

    /**
     * 搜索
     */
    $('#queryBtn').click(function(){
        var params = $('#queryForm').getValue();

        //验证学年学期
        var schoolYear = params['schoolYear'];
        if(schoolYear = undefined || $.trim(schoolYear) == '' ){
           $.alert_warn('请选择学年!');
           return false;
        }
        var term = params['term'];
        if(term = undefined || $.trim(term) == '' ){
            $.alert_warn('请选择学期!');
            return false;
        }
        table.refreshData(params);
    });

    /**
     * 新增
     */
    $('#addBtn').click(function(){
        window.location.href = $.customOpt.url +'sco/score/scoCommentForm.html';
    });

    // /**
    //  * 批量发布
    //  */
    // $('#batchPublishBtn').click(function(){
    //     var items = table.getChecked();
    //     var idArr = new Array();
    //     for(var i=0 ; i < items.length ; i++){
    //         var item = items[i];
    //         var id = item['id'];
    //         idArr.push(id);
    //     }
    //     $.ajaxConnSend(this, 'subjectScores/batchPublish.infc', {idArr:idArr}, function(data) {
    //         //var classinfos = data['object'];
    //         //$('#classSel').setSelItems(classinfos);
    //         $.alert_success("批量发布成功!");
    //         var params = $('#queryForm').getValue();
    //         var curPage = Number($('.curPage').html());
    //         params['curPage'] = curPage;
    //         table.refreshData(params,1);
    //     }, function() {
    //         $.loadingBox.close();
    //     });
    // });

    //初始化页面查询
    // $('#queryBtn').trigger('click');
});