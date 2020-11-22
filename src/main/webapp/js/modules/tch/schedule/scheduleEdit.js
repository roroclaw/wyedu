/**
 * Created by dengxianzhi on 2017/1/26.
 */
var scheduleId=queryString("id");
var scheduleGrade="";
var scheduleSchoolYear="";
var scheduleTerm="";
$('#content_div').resize(function(){
   // alert($('#content_div').height());
   // $(".UI_bd").height($('#content_div').height());
});
$(function(){
///////////////////////////////////////获取课表信息
$.ajaxConnSend(this, 'schedule/doGetScheduleById.infc', {scheduleId:scheduleId}, function(data) {
    scheduleObj = data['object'];
    scheduleGrade=scheduleObj.grade;
    scheduleSchoolYear=scheduleObj.schoolYear;
    scheduleTerm=scheduleObj.term;
}, function() {
///////////////////////////////////////开课列表
    $.ajaxConnSend(this, 'courseOpen/doGetTchCourseOpenList.infc', {grade:scheduleGrade,schoolYear:scheduleSchoolYear,term:scheduleTerm}, function(data) {
        courseOpenList = data['object'];
        //console.debug(courseOpenList);
        var courseOpenHtml="<ul>";
        var tempClassID="";
        for(var i=0;i<courseOpenList.length;i++){
            if(tempClassID!=courseOpenList[i].classId){
                courseOpenHtml=courseOpenHtml+'<li class="level-1"><span>'+courseOpenList[i].className+'</span></li>';
                courseOpenHtml=courseOpenHtml+'<li class="level-2" param="'+courseOpenList[i].id+'" param2="'+courseOpenList[i].className+'"><span>'+courseOpenList[i].courseName+'&nbsp;('+courseOpenList[i].teacherName+')</span></li>';
                tempClassID=courseOpenList[i].classId;
            }else{
                courseOpenHtml=courseOpenHtml+'<li class="level-2" param="'+courseOpenList[i].id+'" param2="'+courseOpenList[i].className+'"><span>'+courseOpenList[i].courseName+'&nbsp;('+courseOpenList[i].teacherName+')</span></li>';
            }
        }
        courseOpenHtml=courseOpenHtml+'</ul>';
        //console.debug(courseOpenHtml);
        $('#leftCourseList').append(courseOpenHtml);
        $(".UI_bd").height($('#leftCourseList').height());
    }, function() {
        $('#leftCourseList').find('.level-2').click(function(){
            var tdID=$('#tdSelected').val();
            $("#"+tdID).find("span").empty();
            $("#"+tdID).find("span").html($(this).find("span").html()+"("+$(this).attr("param2")+")");
            $("#"+tdID).find("input").val($(this).attr("param"));

            var s=$("#"+tdID);
            var ns=s.next();
            $('#tdSelected').val(ns.attr("id"));
            $('.examPlanDetailTableTD').css("background","");
            ns.css("background","#ffeeee");
        });
    })

});

    // 表单验证
    $("#queryForm").validationEngine("attach", {
        promptPosition : "topLeft",
        autoHidePrompt : true,
        autoHideDelay : 3000,
        scroll : false
    });

    //下拉框设置
    $('.buildingSel').mysel({
        url : 'common/getbuildingNoItems.infc',
        text : '教学楼',
        name : 'buildingNo',
        isRequired : true
    });


    // var examPlanId = $('#examPlanId').val();
    var totalLineNum=0;
    var weekListDic=['星期一','星期二','星期三','星期四','星期五','星期六','星期日'];
    var weekSeqList=[];
    var tempHtml="";
  //  var dateList=[];//第一行日期列表
  //  var exaExamDateList;

    $("#start").click(function(){
        var tableObj = $(' <table style="width:100%" id="examPlanDetailTable" class="examPlanDetailTable"></table>');
        tempHtml="";
        weekSeqList=[];
        var params = $('#queryForm').getValue();
        $.loadingBox.show();
        $.ajaxConnSend(this, 'classroom/getClassroomList.infc', {buildingNo:params.buildingNo}, function(data) {  //////////////////获取教室数据
            var classRoomList = data['object'];
            totalLineNum=classRoomList.length;
            tempHtml=tempHtml+'<tr><td></td><td></td>';
            for(var i=0;i<classRoomList.length;i++){
                var classRoomItem = classRoomList[i];
                tempHtml += '<td param="'+ classRoomItem['id']+'">'+ classRoomItem['name']+'</td>';
            }
            tempHtml +='</tr>';
                $.ajaxConnSend(this, 'schedule/getScheduleSectionList.infc', {}, function(data) {////////////////////////////获取周期节次数据
                    scheduleSectionList = data['object'];
                    if(params.weekSeq){
                        weekSeqList.push(weekListDic[params.weekSeq-1]);
                    }else{
                        weekSeqList=weekListDic;
                    }
                    //console.debug(weekSeqList);
                    for(var l=0;l<weekSeqList.length;l++) {
                        tempHtml=tempHtml+"<tr>";
                        tempHtml += '<td rowspan="'+scheduleSectionList.length+'">' + weekSeqList[l] + '</td>';
                        for (var j = 0; j < scheduleSectionList.length; j++) {
                            if(j!=0){
                                tempHtml=tempHtml+"<tr>";
                            }
                            var sectionItem = scheduleSectionList[j];
                            tempHtml += '<td>' + sectionItem['name'] + '</td>';
                            for (var k = 0; k < totalLineNum; k++) {
                                tempHtml += '<td id="'+(l+1)+'_'+sectionItem['id']+'_'+classRoomList[k].id+'" class="examPlanDetailTableTD">'+
                                '<span></span><div class="errorInfo"></div><input name="'+(l+1)+'_'+sectionItem['id']+'_'+classRoomList[k].id+'" type="hidden" value=""/></td>';
                            }
                            tempHtml +='</tr>';
                        }
                    }
                    tableObj.append(tempHtml);
                }, function() {
                    $(".UI_bd").css('height','');
                    $("#content_div").empty();
                    $("#content_div").append(tableObj);
                    $("#examPlanDetailTable").on("click",".examPlanDetailTableTD", function() {
                        $('.examPlanDetailTableTD').css("background","");
                        $(this).css("background","#ffeeee");
                        $('#tdSelected').val($(this).attr("id"));
                    });
                    ///////////////////////////////////////获取已排课表数据
                    $.ajaxConnSend(this, 'schedule/getScheduleCourseOpenRelList.infc', {scheduleId:scheduleId}, function(data) {
                        scheduleCourseOpenObj = data['object'];
                        var tempTdId="";
                        for (var k = 0; k < scheduleCourseOpenObj.length; k++) {
                            var item=scheduleCourseOpenObj[k];
                            tempTdId=item.weekSeq+"_"+item.periodSeq+"_"+item.classroomId;
                            $("#"+tempTdId).find("span").empty();
                            $("#"+tempTdId).find("span").html(item.courseName+"("+item.className+")");
                            $("#"+tempTdId).find("input").val(item.courseOpenId);
                        }
                    }, function() {
                        $.loadingBox.close();
                    })

                })

        }, function() {

        });

    });

    /**
     * 新增
     */
    $("#addBtn").click(function(){
            var params = $('#dataForm').getValue();
            params["scheduleId"]=scheduleId;
            $.loadingBox.show();
            $.ajaxConnSend(this, 'schedule/doSetSchedule.infc', params, function(data) {
                console.debug(data);
                var dataList=data.object;

                var bol=dataList[0];

                if (bol.bol) {
                    $.alert_success('新增成功!');
                   // window.location.href = "../schedule/tchSchedule.html";
                } else {
                    $.alert_error('新增失败');
                    $('.errorInfo').each(function() {
                        $(this).empty();
                    });
                    for (var i = 1; i < dataList.length; i++) {
                        var item=dataList[i];
                        for (var key in item)
                        {
                            var tdId=key.substr(4);
                            console.debug(tdId);
                            $('#'+tdId).find(".errorInfo").append(item[key]);
                        }
                    }
                }
            }, function() {
                $.loadingBox.close();
            });

    });

    $('#cleanBtn').click(function(){
        var tdID=$('#tdSelected').val();
        $("#"+tdID).find("span").empty();
        $("#"+tdID).find(".errorInfo").empty();
        $("#"+tdID).find("input").val("");
    });

    $('#backBtn').click(function(){
        window.location.href = "../schedule/tchSchedule.html";
    });

    $('#closeBtn').click(function(){
        self.opener=null;
        self.close();
    });
});
