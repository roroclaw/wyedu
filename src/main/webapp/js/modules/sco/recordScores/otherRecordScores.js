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
        header: [{
            code: 'stuNo',
            text: '学号'
        },{
            code: 'stuName',
            text: '学员姓名'
        },{
            code: 'classText',
            text: '班级'
        },{
            code: 'subjectName',
            text: '科目'
        }, {
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
        }, {
            code: 'statusText',
            text: '成绩状态'
        }],
        url: 'scoExamScores/doGetOtherExamScoresPageData.infc?examId='+$('#examId').val()
    });

    $('.gradeSel').mysel({
        url : 'common/getGradeItems.infc',
        text : '年级',
        name : 'gradeId',
        isRequired : false,
        id:'grade'
    });

    ///////////响应下拉框选择
    $('#grade').change(function(){
        var gradeCode= $("#grade").find("option:selected").val();
        //班级选择
        $.ajaxConnSend(this, 'class/getClassInfoItems.infc', {grade:gradeCode}, function(data) {
            var classinfos = data['object'];
            $('#classSel').setSelItems(classinfos);
        }, function() {
            $.loadingBox.close();
        });
    });

    $(document).on('blur','.colInput',function(){
        var thisObj = $(this);
        var val = thisObj.val();
        var r = /^\d+(\.\d{1})?$/;//判断是否为小数点后一位数
        if(!r.test(val)|| val < 0 || val > 150){
            $.alert_error("请输入0至150区间的1位小数!");
            thisObj.val(0);
        }
    });

    //保存 可以用到input的 rownum 获取行数据 再组装分数集合后台提交
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
        //console.debug(scoreArr);
        $.ajaxConnSend(this, 'scoExamScores/otherBatchRecordScores.infc', {scoreArr:scoreArr}, function(data) {
            $.alert_success("分数登记成功!");
        }, function() {
            $.loadingBox.close();
        });
    });

    $('#backBtn').click(function(){
        window.location.href = BAS_URL+"sco/recordScores/otherIndex.html";
    });
});