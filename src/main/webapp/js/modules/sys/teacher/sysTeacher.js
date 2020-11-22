/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){
     var winOpen;

    //table
    var table = $('.dataTable').mytable({
        isCheck: true,
        idKeyName:'id',
        header: [{
            code: 'name',
            text: '名字'
        }, {
            code: 'sexText',
            text: '性别'
        }, {
            code: 'identityCard',
            text: '身份证号'
        }, {
            code: 'code',
            text: '工号'
        }, {
            code: 'studyMajorText',
            text: '所学专业'
        }, {
            code: 'infoStatusText',
            text: '账户状态'
        }],
        url: 'teacher/doGetTeacherPageData.infc',
        modFun:function(id) {
            if(typeof(winOpen)=="object"){
                 winOpen.close();             //关闭之前打开的窗口
            }
             winOpen=window.open(BAS_URL+"/teacher/initEdit.do?id="+id,'teacherAddForm');
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
            $.ajaxConnSend(this, 'teacher/doModTeacherInfo.infc', {
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
        window.location.href = $.customOpt.url +'sys/teacher/teacherAddForm.html';

    });

    /**
     * 部门选择
     */
    $.ajaxConnSend(this, 'department/doGetDepartsList.infc', {}, function (data) {
        var fatherObj = data['object'];
        var temp_level=1;
        var selectHtml='<span>所属部门：</span><div class="select_box noborder"><select  class="validate[required]"  name="departId">';
        for(x in fatherObj){
            //x表示是下标，来指定变量，指定的变量可以是数组元素，也可以是对象的属性。
            selectHtml=selectHtml+'<option value="'+fatherObj[x].id+'">';
            for(var i=1;i<fatherObj[x].level;i++){
                selectHtml=selectHtml+'│&nbsp;&nbsp;';
            }
            selectHtml=selectHtml+'├─'+fatherObj[x].name+'</option>';
            temp_level=fatherObj[x].level;
        }
        selectHtml=selectHtml+'</select></div>';
        $("#depart_select_add").empty();
        $("#depart_select_add").append(selectHtml);

    });

});