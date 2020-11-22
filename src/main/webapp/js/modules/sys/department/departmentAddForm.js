/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){
    //下拉框设置
    $('.statusSel').mysel({
        url : 'common/doGetUserStatus.infc',
        text : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状&nbsp;&nbsp;态',
        name : 'status',
        isRequired : false
    });

    /**
     * 部门选择
     */
    $.ajaxConnSend(this, 'department/doGetDepartsList.infc', {status:'1'}, function (data) {
        var fatherObj = data['object'];
        var temp_level=1;
        var selectHtml='<div class="select_box"><select  class="validate[required]"  name="pId">';
        for(x in fatherObj){
            //x表示是下标，来指定变量，指定的变量可以是数组元素，也可以是对象的属性。
            selectHtml=selectHtml+'<option value="'+fatherObj[x].id+'">';
            for(var i=1;i<fatherObj[x].level;i++){
                selectHtml=selectHtml+'│&nbsp;&nbsp;';
            }
            selectHtml=selectHtml+'├─'+fatherObj[x].name+'</option>';
            temp_level=fatherObj[x].level;
        }
        selectHtml=selectHtml+'</select></div><b>*</b>';
        $("#depart_select_add").append(selectHtml);

    });

    /**
     * 新增
     */
    $("#addBtn").click(function(){
        var bol = $("#dataForm").validationEngine("validate");
        if (bol) {
            var params = $('#dataForm').getValue();
            $.loadingBox.show();
            $.ajaxConnSend(this, 'department/doAddDepartInfo.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('新增成功!');
                    setTimeout("self.close()",1200);
                } else {
                    $.alert_error('新增失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }
    });
    $('#backBtn').click(function(){
        window.location.href = "../department/departmentManager.html";
    });


});

