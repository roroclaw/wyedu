$(function(){
    genrateOptionsByItems('#schoolYearSel','common/getFromToEduYearItems.infc',true);
    genrateOptionsByItems('#termSel','common/doGetTermItems.infc',true);

    /**
     * 新增
     */
    $("#subBtn").click(function(){
        var bol = $("#dataForm").validationEngine("validate");
        if (bol) {
            var params = $('#dataForm').getValue();
            $.loadingBox.show();
            $.ajaxConnSend(this, 'comment/addorUpdateComment.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('提交发布成功!');
                    // window.location.href = "../score/scoComment.html";
                    window.close();
                } else {
                    $.alert_error('提交失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }
    });

    $('#backBtn').click(function(){
        window.location.href = "../sco/score/scoComment.html";
    });

    // $('#publishBtn').click(function(){
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

});

function genrateOptionsByItems(jkey,url,allowEmpty) {
    $.ajaxConnSend(null,url, {}, function (data) {
        var objSel = $(jkey);
        var defaultVal = objSel.attr('dfval');
        var items = data['object'];
        var itemsHtml = '';

        if(allowEmpty){
            itemsHtml += '<option value="">--请选择--</option>';
        }

        for(var i =0 ; i < items.length;i++){
            var item = items[i];
            itemsHtml += '<option value="'+item['code']+'">'+item['text']+'</option>';
        }
        objSel.append(itemsHtml);
        objSel.val(defaultVal);
    });
}