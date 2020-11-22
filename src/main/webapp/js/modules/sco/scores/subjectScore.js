/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){

    //$('.statusSel').mysel({
    //    url : 'common/doGetUserStatus.infc',
    //    text : '状态',
    //    name : 'userStatus',
    //    isRequired : false
    //});

    $('.schoolYearSel').mysel({
        url : 'common/getFromToEduYearItems.infc',
        text : '学年',
        name : 'schoolYear'
    });
    $('.termSel').mysel({
        url : 'common/doGetTermItems.infc',
        text : '学期',
        name : 'term',
        id:'term',
        isRequired : true
    });
    $('.subjectSel').mysel({
        url : 'subject/doGetSubjectListsByParam.infc',
        text : '科&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目',
        name : 'subjectId',
        valueKey : 'id',
        textKey : 'name',
        isRequired : false
    });

    $('.statusSel').mysel({
        url : 'common/doGetSubjectScoresStatusItems.infc',
        text : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状&nbsp;&nbsp;态',
        name : 'status',
        isRequired : false,
        id:'statusSel'
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
            code: 'subjectName',
            text: '科目'
        }, {
            code: 'score',
            text: '分数'
        }, {
            code: 'className',
            text: '班级'
        }, {
            code: 'termText',
            text: '学期'
        }, {
            code: 'schoolYear',
            text: '学年',
            formateFun: $.cSchoolYearInput
        }, {
            code: 'statusText',
            text: '状态'
        }, {
            code: 'createTime',
            text: '录入时间',
            formateFun: $.formateDateTime
        }
        ],
        url: 'subjectScores/doGetSubjectScoresPageData.infc',
        modFun:function(id) {
          window.location.href = $.customOpt.url + '/subjectScores/initEdit.do?id='+id;
        },
        delFun: function(id) {
            $.alert_confirm('确定删除此记录?', function() {
                $.loadingBox.show();
                $.ajaxConnSend(this, 'subjectScores/doDelSubjectScores.infc', {
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
        //,operColFun:function(i,rowdata){
        //    var status = rowdata['status'];
        //    if(status == '2'){
        //        var publishObj = $('<i href="###" class="iconfont  bgColor-4 " title="发" rownum="' + i + '">发</i>');
        //        publishObj.on('click', function () {
        //            var id = rowdata['id'];
        //            $.ajaxConnSend(this, 'subjectScores/publish.infc', {
        //                id: id
        //            }, function(data) {
        //                if (data.status == '1' && data.object) {
        //                    $.alert_success('发布成功');
        //                    var curPage = Number($('.curPage').html());
        //                    params['curPage'] = curPage;
        //                    table.refreshData(params,1);
        //                } else {
        //                    $.alert_error('发布失败');
        //                }
        //            },function(){
        //                $.loadingBox.close();
        //            })
        //        });
        //        return publishObj;
        //    }
        //}
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

    $('#addBtn').click(function(){
        window.location.href = $.customOpt.url +'sco/score/subjectScoreAddForm.html';
    });
     */

    /**
     * 批量发布
     */
    $('#batchPublishBtn').click(function(){
        var items = table.getChecked();
        var idArr = [];
        for(var i=0 ; i < items.length ; i++){
            var item = items[i];
            var id = item['id'];
            idArr.push(id);
        }
        $.ajaxConnSend(this, 'subjectScores/batchPublish.infc', {idArr:idArr}, function(data) {
            //var classinfos = data['object'];
            //$('#classSel').setSelItems(classinfos);
            $.alert_success("批量发布成功!");
            var params = $('#queryForm').getValue();
            var curPage = Number($('.curPage').html());
            params['curPage'] = curPage;
            table.refreshData(params,1);
        }, function() {
            $.loadingBox.close();
        });
    });

    $('#batchPublishAllBtn').click(function(){
        var schoolYear = $('#schoolYear').val();
        var subjectId = $('#subjectId').val();
        var subjectText = $('#subjectId option:selected').text();
        if(typeof schoolYear == "undefined" || schoolYear == null || schoolYear == "" || typeof subjectId == "undefined" || subjectId == null || subjectId == ""){
            $.alert_error('请选择学年和科目。');
        }else{
            $.alert_confirm('确定批量发布'+schoolYear+'---'+(parseInt(schoolYear)+1)+'学年“'+subjectText+'”科目的成绩?', function() {
                $.loadingBox.show();
                $.ajaxConnSend(this, 'subjectScores/batchPublishAllByParam.infc', {
                    schoolYear: schoolYear,subjectId:subjectId
                }, function(data) {
                    if (data.status == '1' && data.object) {
                        $.alert_success('发布成功');
                        table.refreshData();
                    } else {
                        $.alert_error('发布失败');
                    }
                },function(){
                    $.loadingBox.close();
                })
            });
        }

    });
    //初始化页面查询
    $('#queryBtn').trigger('click');
});