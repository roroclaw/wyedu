$(function(){
    var commentMarksList = $("#commentMarksList").val().split(",");
    genrateOptionsByItems('#schoolYearSel','common/getFromToEduYearItems.infc',true);
    genrateOptionsByItems('#termSel','common/doGetTermItems.infc',true);
    //genrateOptionsByItems_2('#cmItemSel_','common/getCommentItemMarks.infc',false,commentMarksList);
    for(var i =1;i<13;i++){
        //console.debug(commentMarksList[i-1]);
        genrateOptionsByItems('#cmItemSel_'+i,'common/getCommentItemMarks.infc',false,commentMarksList[i-1]);
    }


    $.ajaxConnSend(this, 'common/getCommentItemNames.infc',{}, function(data) {
        Objs = data['object'];
       // console.debug(Objs);
        for(var i =0;i<Objs.length;i++){
            var temp_id = (i+1)+"";
            //console.debug(temp_id);
            //console.log(i);
            $("#cmItem_"+temp_id).html(Objs[i].text);
        }
    }, function() {
       // $.loadingBox.close();
    });
    /**
     * 新增
     */
    $("#subBtn").click(function(){
        var bol = $("#dataForm").validationEngine("validate");
        if (bol) {
            var params = $('#dataForm').getValue();
            var commentMarksList = "";
            for(var i =1;i<13;i++){
                //console.debug(params[i]);
                if (i == 12){
                    commentMarksList = commentMarksList + params[i];
                }else{
                    if(i<10){
                        commentMarksList = commentMarksList + params["0"+i]+",";
                    }else{
                        commentMarksList = commentMarksList + params[i]+",";
                    }

                }
            }
            params['commentMarksList']= commentMarksList;
            $.loadingBox.show();
            $.ajaxConnSend(this, 'comment/addorUpdateComment.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    console.debug(data.object);
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

function genrateOptionsByItems(jkey,url,allowEmpty,defVal) {
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
            //console.debug(item['code']+"||||||"+defVal);
            if (item['code']==defVal){
                itemsHtml = '<option value="'+item['code']+'"selected>'+item['text']+'</option>'+itemsHtml;
            }else{
                itemsHtml += '<option value="'+item['code']+'">'+item['text']+'</option>';
            }

        }
        objSel.append(itemsHtml);
        objSel.val(defaultVal);
    });
}

function genrateOptionsByItems_2(jkey,url,allowEmpty,list) {
    $.ajaxConnSend(null, url, {}, function (data) {
       // var defaultVal = objSel.attr('dfval');
        var items = data['object'];

        for (var i = 1; i < list.length+1; i++) {
            var defVal=list[i];
            var itemsHtml = '';
            var objSel = $(jkey + i);
            if (allowEmpty) {
                itemsHtml += '<option value="">--请选择--</option>';
            }
            for (var i = 0; i < items.length; i++) {
                var item = items[i];
                //console.debug(item['code']+"||||||"+defVal);
                if (item['code'] == defVal) {
                    itemsHtml = '<option value="' + item['code'] + '"selected>' + item['text'] + '</option>' + itemsHtml;
                } else {
                    itemsHtml += '<option value="' + item['code'] + '">' + item['text'] + '</option>';
                }

            }
            objSel.append(itemsHtml);
        }

    });
}