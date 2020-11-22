/**
 * Created by dxz on 2017/6/20.
 */
$(function(){
    // 表单验证
    $("#addForm").validationEngine("attach", {
        promptPosition : "topLeft",
        autoHidePrompt : true,
        autoHideDelay : 3000,
        scroll : false
    });
    $('.periodSel').mysel({
        url : 'common/doGetPeriodItems.infc',
        text : '学段',
        name : 'period',
        isRequired : true,
        id:'period'
    });
    //下拉框设置
    createSel($('#typeSel'),'type','common/doGetSubjectTypeItems.infc',true);
    createSel($('#gradeSel'),'grade','common/getGradeItems.infc',true);
    createSel($('#termSel'),'term','common/doGetTermItems.infc',true);

    //////////////////////开始显示教学计划内容
    var tchPlanId = $('#id').val();


    //subjecTable
    var table = $('#subjecTable').mytable({
        isCheck: true,
        idKeyName:'id',
        header: [{
            code: 'name',
            text: '名称'
        },  {
            code: 'typeText',
            text: '科目类型'
        },{
            code: 'periodText',
            text: '学段'
        },  {
            code: 'term',
            text: '学期'
        }, {
            code: 'classHour',
            text: '学时'
        }, {
            code: 'statusText',
            text: '状态'
        }],
        url: 'subject/doGetSubjectPageData.infc'
    });

    /**
     * 搜索
     */
    $('#queryBtn').click(function(){
        var params = $('#queryForm').getValue();
        table.refreshData(params);
    });
    /**
     * 搜索
     */
    $('#resultQueryBtn').click(function(){
        var params = $('#resultQueryForm').getValue();
        planTable.refreshData(params);
    });

    /**
     * 添加科目
     */
    $("#addBtn").click(function(){
        var bol = $("#addForm").validationEngine("validate");
        var items=table.getChecked();
        if(items.length>0){
            if (bol) {
                var subjectIDs="";
                for(var i=0;i<items.length;i++){//////////组装ID组
                    if(i==0){
                        subjectIDs=subjectIDs+items[i].id;
                    }else{
                        subjectIDs=subjectIDs+"#"+items[i].id;
                    }
                }
                var params = $('#addForm').getValue();
                params["subjectIds"]=subjectIDs;
                $.loadingBox.show();
                $.ajaxConnSend(this, 'tchPlanSubjectRel/doAddTchPlanSubjectRel.infc', params, function(data) {
                    if (data.status == '1' && data.object) {
                        $.alert_success('新增成功!');
                        //////////////////////开始显示教学计划内容
                        planTable.refreshData();
                    } else {
                        $.alert_error('新增失败');
                    }
                }, function() {
                    $.loadingBox.close();
                });
            }
        }else{
            $.alert_error('请至少选中一个科目');
        }

    });

    $('#backBtn').click(function(){
        window.location.href = "../tch/teachingPlan/tchTeachingPlan.html";
    });

    /**
     * 构造本页面的下拉框控件
     * @param obj
     * @param url
     */
    function createSel(obj,name,url,isRequired){
        var optionHtml = '';
        optionHtml += '<div class="select_box noborder">';
        if(isRequired){
            optionHtml += '<select id="'+name+'" name="'+name+'" class="validate[required]">';
        }else{
            optionHtml += '<select>';
        }
        optionHtml += '<option value ="">--请选择--</option>';
        $.ajaxConnSend(this, url,null, function(data) {
            var items = data['object'];
            for(var i = 0; i < items.length; i++){
                var item = items[i];
                optionHtml += '<option value ="'+item['code']+'">'+item['text']+'</option>';
            }
            if(isRequired){
                optionHtml += '</select></div><i>*</i>';
            }else{
                optionHtml += '</select></div>';
            }

            obj.append(optionHtml);
        });

    }

    /**
     * 查询显示教学计划内容
     *
     *
     */

    //planTable
    var planTable = $('#planTable').mytable({
        isCheck: false,
        idKeyName:'id',
        header: [{
            code: 'subjectName',
            text: '名称'
        },  {
            code: 'gradeText',
            text: '年级'
        },{
            code: 'termText',
            text: '学期'
        },  {
            code: 'typeText',
            text: '科目类型'
        },{
            code: 'periodText',
            text: '学段'
        }],
        params:{"planId":tchPlanId},
        url: 'tchPlanSubjectRel/doGetTchPlanSubjectPageData.infc',
        delFun: function(id) {
            $.alert_confirm('确定删除此记录?', function() {
                $.loadingBox.show();
                $.ajaxConnSend(this, 'tchPlanSubjectRel/doDelTchPlanSubjectRelById.infc', {
                    id:id,status:status
                }, function(data) {
                    if (data.status == '1' && data.object) {
                        $.alert_success('操作成功');
                        planTable.refreshData();
                    } else {
                        $.alert_error('操作失败');
                    }
                },function(){
                    $.loadingBox.close();
                });

                //$.ajaxConnSend(this, 'user/doDelUserById.infc', {
                //    id: id
                //}, function(data) {
                //    if (data.status == '1' && data.object) {
                //        $.alert_success('删除成功');
                //        table.refreshData();
                //    } else {
                //        $.alert_error('删除失败');
                //    }
                //},function(){
                //    $.loadingBox.close();
                //})
            });
        }
    });


});