/**
 * Created by dengxianzhi on 2017/1/29.
 */
$(function(){

    // 表单验证
    $("#dataForm").validationEngine("attach", {
        promptPosition : "topLeft",
        autoHidePrompt : true,
        autoHideDelay : 3000,
        scroll : false
    });
    //学期选择
    $('.termSel').mysel({
        url : 'common/doGetTermItems.infc',
        text : '学期',
        name : 'term',
        isRequired : true
    });

    $('.periodSel').mysel({
        url : 'common/doGetPeriodItems.infc',
        text : '学段',
        name : 'period',
        id : 'period',
        isRequired : true,
        valueKey : 'code',
        textKey : 'text'
    });
    $('.statusSel').mysel({
        url : 'common/doGetUserStatus.infc',
        text : '状态',
        name : 'status',
        isRequired : true,
        valueKey : 'code',
        textKey : 'text'
    });
    $('.schoolYearSel').mysel({
        url : 'common/getEduYearItems.infc',
        text : '学年',
        name : 'schoolYear',
        isRequired : true
    });
    ///////////响应下拉框选择
    $('#period').change(function(){
       var period= $("#period").find("option:selected").val();
        //年级选择
        $('.gradeSel').empty();
        $('.gradeSel').mysel({
            url : 'common/getGradeItemsByPeriod.infc',
            text : '年级',
            name : 'grade',
            params : {"period":period},
            isRequired : true,
            valueKey : 'code',
            textKey : 'text',
            id : 'garde'
        });
    });
    /**
     * 年级选择
     */
    //$.ajaxConnSend(this, 'common/getGradeItems.infc', {}, function (data) {
    //    var items = data['object'];
    //    var itemsHtml = '<option value="">--请选择--</option>';
    //    for(var i =0 ; i < items.length;i++){
    //        var item = items[i];
    //        itemsHtml += '<option value="'+item['code']+'">'+item['text']+'</option>';
    //    }
    //    $('#gradeSel').append(itemsHtml);
    //});
    //$.genrateOptionsByItems('#gradeSel','common/getGradeItems.infc',true);

    /**
     * 教室
     */
    //$.ajaxConnSend(this, 'classroom/classroom.infc', {}, function (data) {
    //    var roomSel = $('#roomSel');
    //    var defaultVal = roomSel.attr('dfval');
    //    var items = data['object'];
    //    var itemsHtml = '<option value="">--请选择--</option>';
    //    for(var i =0 ; i < items.length;i++){
    //        var item = items[i];
    //        itemsHtml += '<option value="'+item['code']+'">'+item['text']+'</option>';
    //    }
    //    roomSel.append(itemsHtml);
    //    roomSel.val(defaultVal);
    //});


    /**
     * 新增
     */
    $("#addBtn").click(function(){
        var bol = $("#dataForm").validationEngine("validate");
        if (bol) {
            var params = $('#dataForm').getValue();
            $.loadingBox.show();
            $.ajaxConnSend(this, 'schedule/doAddSchedule.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('新增成功!');
                   // window.location.href = "../schedule/tchSchedule.html";
                } else {
                    $.alert_error('新增失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }
    });
    $('#backBtn').click(function(){
        window.location.href = "../schedule/tchSchedule.html";
    });



});