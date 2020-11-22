/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){
    //下拉框设置
    createSel($('#type_select_add'),'type','common/doGetSubjectTypeItems.infc',true);

    //table
    var table = $('.dataTable').mytable({
        isCheck: true,
        idKeyName:'id',
        header: [{
            code: 'name',
            text: '名称'
        }, {
            code: 'typeText',
            text: '类型'
        }, {
            code: 'periodText',
            text: '学段'
        },  {
            code: 'statusText',
            text: '状态'
        }],
        url: 'subject/doGetSubjectPageData.infc',
        modFun:function(id) {
            if(typeof(winOpen)=="object"){
                winOpen.close();             //关闭之前打开的窗口
            }
            winOpen=window.open( BAS_URL+"/subject/initEdit.do?id="+id,'subjectModForm');
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
            $.ajaxConnSend(this, 'subject/doModSubjectInfo.infc', {
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
         window.location.href = $.customOpt.url +'/sys/subject/subjectAddForm.html';
    });

});

/**
 * 构造本页面的下拉框控件
 * @param obj
 * @param url
 */
function createSel(obj,name,url,isRequired){
    var defaultVal = obj.attr('value');
    var optionHtml = '';
    optionHtml += '<div class="select_box noborder">';
    if(isRequired){
        optionHtml += '<select id="'+name+'" name="'+name+'">';
    }else{
        optionHtml += '<select>';        }
    optionHtml += '<option value ="">--请选择--</option>';
    $.ajaxConnSend(this, url,null, function(data) {
        var items = data['object'];
        for(var i = 0; i < items.length; i++){
            var item = items[i];
            var isChkStr = '';
            if($.trim(defaultVal) != '' && item['code'] == defaultVal){
                isChkStr = 'selected ';
            }
            optionHtml += '<option value ="'+item['code']+'" '+isChkStr+'>'+item['text']+'</option>';
        }
        if(isRequired){
            optionHtml += '</select></div>';
        }else{
            optionHtml += '</select></div>';
        }
        obj.append(optionHtml);
    });
}