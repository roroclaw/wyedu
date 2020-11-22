/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){
    // 表单验证
    $("#dataForm").validationEngine("attach", {
        promptPosition : "topLeft",
        autoHidePrompt : true,
        autoHideDelay : 3000,
        scroll : false
    });
    var examPlanId = $('#examPlanId').val();
    var tableObj = $(' <table style="width:100%" id="examPlanDetailTable" class="examPlanDetailTable"></table>');
    var tempHtml="";
    var dateList=[];//第一行日期列表
    var exaExamDateList;
    $.loadingBox.show();
    $.ajaxConnSend(this, 'exam/doGetExamPlanDetailByExaExamPlanId.infc', {id:examPlanId}, function(data) {
        var exaExamList = data['object'];
        var dateListSize;
       // console.log(items);

            $.ajaxConnSend(this, 'exam/doGetExamDateByParam.infc', {examPlanId:examPlanId}, function(data) {
                /////////////////////组装第一行日期
                exaExamDateList = data['object'];
                dateListSize=exaExamDateList.length;
                var tempDate;
                tempHtml=tempHtml+"<tr><td></td>";
                for(var i=0;i<exaExamDateList.length;i++){
                    var item = exaExamDateList[i];
                        if(tempDate!=item['date']){
                           tempHtml += '<td>'+ $.dateFormate(item['date'], 'yyyy-MM-dd')+'</td>';
                           tempDate=item['date'];
                           dateList[i]=item['date'];
                        }
                }
                tempHtml=tempHtml+"</tr>";
                tableObj.append(tempHtml);

                 ////////////////////////////////////////组装每行考场
                if(dateListSize>0){
                    var tempExamRoom="";
                    tempHtml="";
                    var tempColNum=0;
                    for(var j=0;j<exaExamList.length;j++){
                        var item = exaExamList[j];
                        if(tempExamRoom!=item['examRoomId']){////////////换行
                                 tempColNum=0;
                            if(j==0){
                                tempHtml += '<tr><td>'+item['examRoomName']+'</td>';

                            }else{
                                tempHtml += '</tr><tr><td>'+item['examRoomName']+'</td>';
                            }
                            for(var n=0;n<dateList.length;n++){
                                tempHtml += '<td><div param="'+item['examRoomId']+'" id="'+item['examRoomId']+'_'+dateList[n]+'">'+'</div></td>';
                            }
                            tempColNum+=1;
                            tempExamRoom=item['examRoomId'];
                        }else{////////////////////////////////////////////同一行
                           //tempHtml += '<td><div id="'+item['examRoomId']+'_'+dateList[tempColNum]+'">'+'</div></td>';
                           // tempColNum+=1;
                        }
                    }
                    tempHtml += '</tr>';
                }else{
                }
                tableObj.append(tempHtml);
                /////////////////////////////////////填充数据
                var divId="";
                var tempContent="";
                for(var m=0;m<exaExamList.length;m++){
                    var item = exaExamList[m];
                    divId=item['examRoomId']+'_'+item['date'];
                    tempContent='<div class="examPlanDetailDIV" param="'+item['examId']+'">' +item['subjectName']+'&nbsp;&nbsp;&nbsp;&nbsp;'+$.dateFormate(item['startTime'], 'yyyy-MM-dd HH:mm:ss')+'---'+$.dateFormate(item['endTime'], 'yyyy-MM-dd HH:mm:ss')
                               +'&nbsp;&nbsp;&nbsp;&nbsp;'+'人数：'+item['stuNum']+'</div>';
                   // $('#'+divId).empty();
                    //$('#'+divId).attr("param",item['id']);
                    $('#'+divId).append(tempContent);
                }
            }, function() {
                $('.examPlanDetailDIV').click(function(){
                    var examId=$(this).attr("param");
                    var examRoomId=$(this).parent().attr("param");

                    //if(typeof(winOpen)=="object"){
                   //     winOpen.close();             //关闭之前打开的窗口
                   // }
                    winOpen=window.open( BAS_URL+"exa/examRoom/examRoomSeatsDetail.html?p1="+examId+"&p2="+examRoomId,'classroomModForm');
                });
            })

    }, function() {
        $.loadingBox.close();
    });

    $("#content_div").append(tableObj);
    $('#backBtn').click(function(){
        window.location.href = "../exa/exam/exaExamPlan.html";
    });
    $('#closeBtn').click(function(){
        self.opener=null;
        self.close();
    });

    $('#excelBtn').click(function(){
        tableToExcel('examPlanDetailTable');
    });

});
