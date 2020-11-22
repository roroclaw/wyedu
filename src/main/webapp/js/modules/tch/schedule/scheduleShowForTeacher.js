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
    $('.termSel').mysel({
        url : 'common/doGetTermItems.infc',
        text : '学期',
        name : 'term',
        isRequired : true
    });
    /**
     * 学年选择
     */
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
    var weekListDic=['上午&nbsp;1','上午&nbsp;2','上午&nbsp;3','上午&nbsp;4','中午&nbsp;1','中午&nbsp;2','下午&nbsp;1','下午&nbsp;2','下午&nbsp;3','下午&nbsp;4','晚上&nbsp;1','晚上&nbsp;2'];
    $.loadingBox.show();
    var maxWeekSeq=7;
    var maxPeriodSeq=12;
    var tempHtml='<table style="width:100%;height: 100%;"  class="tableOut"><tr><td style="width:80px;">节次\星期</td><td style="width:150px;">星期一</td>' +
        '<td style="width:150px;">星期二</td><td style="width:150px;">星期三</td><td style="width:150px;">星期四</td><td style="width:150px;">星期五</td><td style="width:150px;">星期六</td>' +
        '<td style="width:150px;">星期日</td></tr>';
    for(var i=0;i<maxPeriodSeq;i++){
        tempHtml=tempHtml+'<tr><td>'+weekListDic[i]+'</td>';
        for(var j=0;j<maxWeekSeq;j++){
            tempHtml=tempHtml+'<td id="td_'+(i+1)+'_'+(j+1)+'">';
        }
        tempHtml=tempHtml+'</tr>';
    }
    tempHtml=tempHtml+'</table>';
    $("#content_div").empty();
    $("#content_div").html(tempHtml);
    ///////////////////////////////////////获取已排课表数据
    $.ajaxConnSend(this, 'schedule/getScheduleCourseOpenRelListShowForTeacher.infc', params, function(data) {
        scheduleCourseOpenObj = data['object'];
        var tempTdId="";
        var tempHtml="";
        for (var k = 0; k < scheduleCourseOpenObj.length; k++) {
            var item=scheduleCourseOpenObj[k];
            tempHtml='<span>'+item.courseName+"("+item.teacherName+")"+'</span>'+'<span>'+item.classroomText+'</span>';

            tempTdId="td_"+item.periodSeq+"_"+item.weekSeq;

            $("#"+tempTdId).empty();
            $("#"+tempTdId).html(tempHtml);
        }
    }, function() {
        $.loadingBox.close();
    })

}