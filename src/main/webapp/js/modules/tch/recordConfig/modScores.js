/**
 * Created by roroclaw on 2017/10/29.
 */
$(function(){

    /**
     * 搜索
     */
    $('#queryBtn').click(function(){
        var params = $('#queryForm').getValue();
        table.refreshData(params);
    });

    var table = $('.dataTable').mytable({
        isCheck: false,
        idKeyName:'id',
        header: [{ code: 'stuNo',
            text: '学号'
        },{
            code: 'stuName',
            text: '学员姓名'
        },{
            code: 'courseName',
            text: '课程'
        },{
            code: 'classText',
            text: '班级'
        },{
            code: 'score',
            text: '分数',
            formateFun: function (text, i, name,rowdata) {
                var id = rowdata['id'];
                var inputHtml = '';
                if(rowdata['status'] == '1'){
                    var score = rowdata['score'] != null ? rowdata['score'] : 0;
                    inputHtml = '<input type="text" data-key="' + id + '" name="' + name + '" value="'+score+'" class="colInput" rowNum="' + i + '"></input>';
                }else{
                    inputHtml = '0';
                    inputHtml += '<input type="hidden"  readOnly="true" data-key="' + id + '" name="' + name + '" value="0" class="colInput" rowNum="' + i + '" ></input>'
                }
                return inputHtml;
            }
        },{
            code: 'remark',
            text: '分数备注',
            formateFun: function (text, i, name,rowdata) {
                var id = rowdata['id'];
                var inputHtml = '';
                inputHtml += '<select type="hidden"  readOnly="true" data-key="' + id + '" name="' + name + '" value="0" class="colSelect" rowNum="' + i + '" >';
                inputHtml +=  '<option value="" >-请选择-</option>';
                var bol_1 = rowdata[name] == '优'? 'selected':'';
                var bol_2 = rowdata[name] == '良'? 'selected':'';
                var bol_3 = rowdata[name] == '中'? 'selected':'';
                var bol_4 = rowdata[name] == '差'? 'selected':'';
                inputHtml +=  '<option value ="优" '+bol_1+' >优</option>';
                inputHtml +=  '<option value ="良" '+bol_2+' >良</option>';
                inputHtml +=  '<option value ="中" '+bol_3+' >中</option>';
                inputHtml +=  '<option value ="差" '+bol_4+' >差</option>';
                inputHtml +=  '</select>';
                return inputHtml;
            }
        },{
            code: 'typeText',
            text: '成绩类型'
        },{
            code: 'statusText',
            text: '缺/缓考'
        }],
        url: 'scoExamScores/doGetExamScores4AdminModPageData.infc?openCourseId='+$('#openCourseId').val()+'&scoreType='+$('#scoreType').val() ,
        modFun:function(id) {//登记分数
            $('#recordScoreFormId').val(id);
            var showBox = $.showPopupForm('#recordScoreForm',function(){
                var bol = $("#recordScoreForm").validationEngine("validate");
                if (bol) {
                    var formData = $("#recordScoreForm").getValue();
                    $.loadingBox.show();
                    $.ajaxConnSend(this, 'scoExamScores/doSetScoresStatus.infc',formData, function(data) {
                        $.alert_success("设置成功!");
                        //$('#queryBtn').trigger('click');
                        var params = $('#queryForm').getValue();
                        var curPage = Number($('.curPage').html());
                        params['curPage'] = curPage;
                        table.refreshData(params,1);
                        showBox.close();
                    },function(){
                        $.loadingBox.close();
                    });
                }
            });
        }
    });

    $('.statusSel').mysel({
        url : 'common/doGetExamStuStatus.infc',
        text : '状&nbsp;&nbsp;态',
        name : 'status',
        isRequired : false
    });

    $('#backBtn').click(function(){
        window.location.href = BAS_URL+'tch/recordConfig/recordConfig.html';
    });

    $('#saveBtn').click(function(){
        var inputs = $('.colInput');
        var selects = $('.colSelect');
        var scoreArr = [];
        for(var i = 0 ;i < inputs.length ; i++){
            var input = $(inputs[i]);
            var select = $(selects[i]);
            var key = input.data('key');
            var val = input.val();
            var itemStr = key+'|'+val+'|'+select.val();
            scoreArr.push(itemStr);
        }
        $.ajaxConnSend(this, 'scoExamScores/batchRecordScores.infc', {scoreArr:scoreArr}, function(data) {
            //var classinfos = data['object'];
            //$('#classSel').setSelItems(classinfos);
            $.alert_success("分数登记成功!");
        }, function() {
            $.loadingBox.close();
        });
    });

});