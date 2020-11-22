/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){
    $("#queryForm").validationEngine("attach", {
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
    $('.termSel').mysel({
        url : 'common/doGetTermItems.infc',
        text : '学期',
        name : 'term',
        isRequired : true,
        id:'term'
    });

    $('.schoolYearSel').mysel({
        url : 'common/getFromToEduYearItems.infc',
        text : '学年',
        name : 'schoolYear',
        isRequired : true,
        id:'schoolYear'
    });
    /**
     * 搜索
     */
    $('#queryBtn').click(function(){
        var bol = $("#queryForm").validationEngine("validate");
        if(bol){
            var params = $('#queryForm').getValue();
            queryForData(params);
        }
    });
});

function queryForData(params){
    var periodListDic=['上午&nbsp;1','上午&nbsp;2','上午&nbsp;3','上午&nbsp;4','中午&nbsp;1','中午&nbsp;2','下午&nbsp;1','下午&nbsp;2','下午&nbsp;3','下午&nbsp;4','晚上&nbsp;1','晚上&nbsp;2'];
    var weekListDic=['星期一','星期二','星期三','星期四','星期五','星期六','星期日'];
    $.loadingBox.show();
    var classOpenObj;
    var maxWeekSeq=7;
    var maxPeriodSeq=12;
    var tempWeekSeq=0;
    var tempPeriodSeq=0;
    var tempHtml='<table style="width:100%;height: 100%;"  class="tableOut"><tr><td style="width:80px;">星期</td><td style="width:80px;">节次\班级</td>';
    ///////////////////////////////////////获取第一行班级数据
    $.ajaxConnSend(this, 'schedule/getClassInfoForCollectingShow.infc', params, function(data) {
        classOpenObj = data['object'];
        ///console.debug(scheduleCourseOpenObj);
        for (var k = 0; k < classOpenObj.length; k++) { ///////////第一排班级
            var item=classOpenObj[k];
            tempHtml=tempHtml+'<td>'+item.className+'</td>';
        }
        tempHtml=tempHtml+'</tr>';
        for (var i = 0; i < maxWeekSeq; i++) { ///////////星期循环
            for (var j = 0; j < maxPeriodSeq; j++) {///////////班次循环
                if(j==0){//////////第一节
                    tempHtml=tempHtml+'</tr><tr><td rowspan="12">'+weekListDic[i]+'</td>';
                }else{
                    tempHtml=tempHtml+'</tr><tr>';
                }
                tempHtml=tempHtml+'<td>'+periodListDic[j]+'</td>';
                for (var k = 0; k < classOpenObj.length; k++) {///////////班级
                    var item=classOpenObj[k];
                    tempHtml=tempHtml+'<td id="'+(i+1)+'_'+(j+1)+'_'+item.classId+'"></td>';
                }
            }
        }
        $("#content_div").empty();
        $("#content_div").html(tempHtml);

    }, function() {
        ///////////////////////////////////////获取已排课表数据
        $.ajaxConnSend(this, 'schedule/getScheduleCourseOpenRelListShowForCollecting.infc', params, function(data) {
            scheduleCourseOpenObj = data['object'];
            var tempTdId="";
            var tempHtml="";
            for (var k = 0; k < scheduleCourseOpenObj.length; k++) {
                var item=scheduleCourseOpenObj[k];
                tempHtml='<span>'+item.courseName+"("+item.teacherName+")"+'</span>'+'<span>'+item.classroomText+'</span>';

                tempTdId=item.weekSeq+"_"+item.periodSeq+"_"+item.classId;
                if ($("#"+tempTdId).length > 0){     // 找到对应id=div的元素，然后执行此块代码
                    $("#"+tempTdId).empty();
                    $("#"+tempTdId).html(tempHtml);
                 }else{
                    $.alert_error('表格无对应区域');
                }
            }
            }, function() {
            $.loadingBox.close();
        })
    })



}