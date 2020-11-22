/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){

    $('.classInfoStatus').mysel({
        url : 'common/doGetClassInfoStatus.infc',
        text : '班级状态',
        name : 'status',
        isRequired : false,
        id:'classInfoStatus'
    });
    //初始化学年信息
    var schoolYearSel = $('.schoolYearSel').mysel({
        url : 'common/getEduYearItems.infc',
        text : '入学年份',
        name : 'enrolYear',
        isRequired : false
    });
    //table
    var table = $('.dataTable').mytable({
        isCheck: true,
        idKeyName:'id',
        header: [{
            code: 'name',
            text: '名称'
        }, {
            code: 'periodText',
            text: '学段'
        }, {
            code: 'number',
            text: '人数'
        }, {
            code: 'roomText',
            text: '教室'
        }, {
            code: 'teacherText',
            text: '班主任'
        }, {
            code: 'enrolYear',
            text: '入学年份 ',
            formateFun: $.formate2Year
        }, {
            code: 'graduateYear',
            text: '毕业年份 ',
            formateFun: $.formate2Year
        }, {
            code: 'createTime',
            text: '创建时间',
            formateFun: $.formateDateTime
        },  {
            code: 'statusText',
            text: '状态'
        }],
        url: 'class/doGetClassPageData.infc?status=1',
        modFun:function(id) {
            if(typeof(winOpen)=="object"){
                winOpen.close();             //关闭之前打开的窗口
            }
            winOpen=window.open(BAS_URL+"/class/initEdit.do?id="+id,'classModForm');
        },
        delFun: function(id) {
            $.alert_confirm('确定删除此记录?', function() {
                $.loadingBox.show();
                $.ajaxConnSend(this, 'class/doDelClassById.infc', {
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
        },
        operColFun:function(i,rowdata){
            var id =  rowdata['id'];
            var status =  rowdata['status'];
            if(status == 1){
                var operObj = $('<i href="###" class="iconfont icon-shibai" title="停用" rownum="' + i + '" did="' + id + '"></i>');
                operObj.on('click', function () {
                    stopClassinfo(id)
                });
            }else if(status == -1){
                var operObj = $('<i href="###" class="iconfont icon-kaiqi" title="启用" param="1" rownum="' + i + '" did="' + id + '"></i>');
                operObj.on('click', function () {
                    activeClassinfo(id)
                });
            }
            return operObj;
        }
    });

    function stopClassinfo(id){
        $.ajaxConnSend(this, 'class/doStopClassinfo.infc', {
            id: id
        }, function(data) {
            if (data.status == '1' && data.object) {
                $.alert_success('停用成功');
                table.refreshData();
            } else {
                $.alert_error('停用失败');
            }
        },function(){
            $.loadingBox.close();
        })
    }
    function activeClassinfo(id){
        $.ajaxConnSend(this, 'class/doActiveClassinfo.infc', {
            id: id
        }, function(data) {
            if (data.status == '1' && data.object) {
                $.alert_success('启用成功');
                table.refreshData();
            } else {
                $.alert_error('启用失败');
            }
        },function(){
            $.loadingBox.close();
        })
    }



    /**
     * 搜索
     */
    $('#queryBtn').click(function(){
        var bol = $("#queryForm").validationEngine("validate");
        if (bol) {
            var params = $('#queryForm').getValue();
            if (params['enrolYear']!="" && params['enrolYear']!=null){
                params['enrolYear']=params['enrolYear']+'-01-01';
            }
            table.refreshData(params);
        }
    });

    /**
     * 新增
     */
    $('#addBtn').click(function(){
       window.location.href = $.customOpt.url +'/tch/class/classAddForm.html';
    });

    /**
     * 升年级
     */
    $('#increaseGradeBtn').click(function(){

        var items=table.getChecked();
        if(items.length>0){
            var classIDs="";
            for(var i=0;i<items.length;i++){//////////组装ID组
                if(i==0){
                    classIDs=classIDs+items[i].id;
                }else{
                    classIDs=classIDs+"#"+items[i].id;
                }
            }
            var params = {'classIDs':classIDs};
            $.loadingBox.show();
            $.ajaxConnSend(this, 'class/doIncreaseGrade.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('年级升级成功!');
                    //////////////////////开始显示教学计划内容
                    table.refreshData();
                } else {
                    $.alert_error('新增失败');
                    table.refreshData();
                }
            }, function() {
                $.loadingBox.close();
            });
        }else{
            $.alert_error('请至少选中一位学生');
        }
       // var items=table.getChecked();
       // if(items.length==1){
      //      winOpen=window.open( BAS_URL+"/class/initIncreaseGrade.do?id="+items[0].id,'increaseGrade');

      //  }else{
      //      $.alert_error('请选中一个班级');
      //  }
    });

});