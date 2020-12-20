/**
 * Created by dengxianzhi on 2017/1/26.
 */

$(function(){

    var ruleId = $.getRequestValue("id");

    $('.termSel').mysel({
        url : 'common/doGetTermItems.infc',
        text : '学期',
        name : 'term',
        isRequired : false
    });

    /**
     * 学年选择
     */
    $('.schoolYearSel').mysel({
        url : 'common/getFromToEduYearItems.infc',
        text : '学年',
        name : 'schoolYear',
        isRequired : false,
        id:'schoolYear'
    });

    //table
    var table = $('.dataTable').mytable({
        isCheck: true,
        idKeyName:'id',
        header: [{
            code: 'courseName',
            text: '课程名称'
        }, {
            code: 'subjectName',
            text: '科目名称'
        },{
            code: 'schoolYear',
            text: '学年',
            formateFun:function(txt, i, code, rowdata){
                var dateChar="";
                var schoolYear=rowdata.schoolYear;
                var schoolYearInt=parseInt(schoolYear)+1;
                dateChar= schoolYear+"---"+schoolYearInt;
                return dateChar;
            }
        }, {
            code: 'termText',
            text: '学期'
        }, {
            code: 'teacherName',
            text: '授课教师'
        }
        ],
        url: 'courseOpen/doGetCourseOpensSelPageData.infc',
        params:{'ruleId':ruleId}
    });

    /**
     * 搜索
     */
    $('#queryBtn').click(function(){
        var params = $('#queryForm').getValue();
        table.refreshData(params);
    });

    //提交开课关系信息
    $('#submitBtn').click(function(){
        //获取当前页面开课选择信息
        var items=table.getChecked();
        if(items.length>0){

            var openCourseIds = [];
            for(var i = 0 ;i < items.length ; i++){
                 var item = items[i];
                openCourseIds.push(item.id);
            }

            $.ajaxConnSend(this, 'sysScoreRule/setRuleRel.infc',{
                openCourseIds:openCourseIds,
                ruleId:ruleId,
            },function (data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('设置成功!');
                    showBox.close();
                } else {
                    $.alert_error('设置失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }else{
            $.alert_error('请选中开课信息');
        }
    });


});