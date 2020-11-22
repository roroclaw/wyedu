/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){
    //下拉框设置
    createSel($('#statusSel'),'eduType','common/getEduTypeItems.infc',true);
    //table
    var table = $('.dataTable').mytable({
        isCheck: true,
        idKeyName:'id',
        header: [{
            code: 'name',
            text: '名称'
        }, {
            code: 'majorText',
            text: '专业'
        },  {
            code: 'periodText',
            text: '学段'
        }, {
            code: 'eduTypeText',
            text: '学制'
        },  {
            code: 'statusText',
            text: '状态'
        }],
        url: 'teachingPlan/doGetTeachingPlanPageData.infc',
        modFun:function(id) {
            if(typeof(winOpen)=="object"){
                winOpen.close();             //关闭之前打开的窗口
            }
            winOpen=window.open( BAS_URL+"/teachingPlan/initEdit.do?id="+id,'teachingPlanAddForm');
        },
        detailFun: function(id) {
            winOpen=window.open( BAS_URL+"/teachingPlan/initDetail.do?id="+id,'teachingPlanDetail');
        },
        /*delFun: function(id) {
            $.alert_confirm('确定删除此记录?', function() {
                $.loadingBox.show();
                $.ajaxConnSend(this, 'user/doDelUserById.infc', {
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
        },*/
        changeStatusFun:function(id,status) {
            // console.debug(data+"|||"+j);
            $.loadingBox.show();
            $.ajaxConnSend(this, 'teachingPlan/doModTeachingPlan.infc', {
                id:id,status:status
            }, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('操作成功');
                    table.refreshData();
                } else {
                    $.alert_error('操作失败');
                }
            },function(){
                $.loadingBox.close();
            })
        }
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
     */
    $('#addBtn').click(function(){
         window.location.href = $.customOpt.url +'/tch/teachingPlan/teachingPlanAddForm.html';
    });

    /**
     * 编排
     */
    $('#relMod').click(function(){
        var items=table.getChecked();
        if(items.length==1){
         window.location.href = BAS_URL+"teachingPlan/initEditForTchplanSubjectRel.do?id="+items[0].id;
        }else{
            $.alert_error('请选中一个教学计划');
        }
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
});