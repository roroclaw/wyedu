/**
 * Created by dxz on 2017/6/20.
 */
$(function(){
    var examId = $('#examId').val();
    var subjectId = $('#subjectId').val();
    var examPlanId= $('#examPlanId').val();
    var examDate= $('#examDate').val();
    var gradeList=[];
    $('#examDateSpan').html("考试日期："+ $.dateFormate(examDate, 'yyyy-MM-dd HH:mm:ss'));

    // 表单验证
    $("#addForm").validationEngine("attach", {
        promptPosition : "topLeft",
        autoHidePrompt : true,
        autoHideDelay : 3000,
        scroll : false
    });

    var tableObj = $(' <table style="width:100%"></table>');
    var eerABInfoTableObj = $(' <table style="width:100%"><tr style="border: none;"><td style="border: none;"></td><td style="border: none;"></td></tr><tr><td style="border: none;"></td><td style="border: none;"></td></tr></table>');  ////////////////////////eerABInfo区域显示
    var eerABInfoTempHtml="";
    var tempHtml="";
    var colNum=0;
    var rowNum=0;
    //////////////////////////////按年级分考生
    $.loadingBox.show();
    $.ajaxConnSend(this, 'exam/doGetExamStudentsListByGrade.infc', {'examId':examId}, function(data) {
        var ExamStudentsObj = data['object'];

        tempHtml=tempHtml+"<tr><td></td>";
        for(var i = 0; i < ExamStudentsObj.length; i++){
            colNum+=1;

            var item = ExamStudentsObj[i];
            tempHtml += '<td colspan="2">'+item['gradeName']+'(<span id="grade_'+item['grade']+'" param='+item['stuNum']+'>0</span><span>/'+item['stuNum']+'</span>)</td>';
            gradeList[i]=item['grade'];
        }
        tempHtml=tempHtml+"</tr>";
        tempHtml=tempHtml+"<tr><td></td>";
        for(var k = 0; k < colNum; k++){
            tempHtml=tempHtml+"<td>A座人数</td><td>B座人数</td>";
        }
        tempHtml=tempHtml+"</tr>";
        tableObj.append(tempHtml);
        $("#colNum").val(colNum);

        //console.debug(colNum);
       // console.debug("grade|||"+gradeList);
        //////////////////////////////获取考场列
        $.ajaxConnSend(this, 'examRoom/doGetExamRoomListsByParam.infc', {'examPlanId':examPlanId}, function(data) {
            var ExamRoomsObj = data['object'];

            tempHtml="";
            for(var j = 0; j < ExamRoomsObj.length; j++){
                rowNum+=1;
                var item = ExamRoomsObj[j];
                tempHtml=tempHtml+'<tr param="'+item['id']+'"><td>'+item['name']+'</td>';
                for(var k = 0; k < colNum; k++){
                    if(item['seatAb']){/////////////////////////是否排了AB座
                        var seatAb = item['seatAb'];
                        var regexA = new RegExp('A#', 'g'); // 使用g表示整个字符串都要匹配
                        var regexB = new RegExp('B#', 'g'); // 使用g表示整个字符串都要匹配
                        var seatANum = seatAb.match(regexA);
                        var seatBNum = seatAb.match(regexB);
                        var countA = !seatANum ? 0 : seatANum.length;
                        var countB = !seatBNum ? 0 : seatBNum.length;
                        //console.log(countA + " ||| " + countB);

                        tempHtml=tempHtml+'<td><input class="exam_room_input" type="text" value=0 id="'+item['id']+'_'+gradeList[k]+'_A" name="'+item['id']+'_'+gradeList[k]+'_A" /></td>' +
                    '<td><input class="exam_room_input" type="text" value=0 id="'+item['id']+'_'+gradeList[k]+'_B" name="'+item['id']+'_'+gradeList[k]+'_B"/></td>';
                    }else{
                        tempHtml=tempHtml+'<td><input class="exam_room_input" type="text"  id="'+item['id']+'_'+gradeList[k]+'_A" readonly/></td>' +
                        '<td><input type="text" id="'+item['id']+'_'+gradeList[k]+'_B" readonly/></td>';
                    }
                }
                eerABInfoTempHtml=eerABInfoTempHtml+'<tr><td valign="middle">'+item['name']+'</td><td><span>A:</span><span id="'+item['id']+'_A" param='+countA+'>0</span><span>/'+countA+'</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
                '<span>B:</span><span id="'+item['id']+'_B" param='+countB+'>0</span><span>/'+countB+'</span></td></tr>';
            }
            tempHtml=tempHtml+"</tr>";
            tableObj.append(tempHtml);
            eerABInfoTableObj.append(eerABInfoTempHtml);
            //console.debug(colNum);
           // console.debug(tempHtml);
            $("#rowNum").val(rowNum);
        }, function() {
            $.loadingBox.close();
        });

        $("#eerABInfo").append(eerABInfoTableObj);
        $("#examExramRoomTable").append(tableObj);
    }, function() {
        /////////填充已有数据
        $.ajaxConnSend(this, 'exam/doGetExamStudentsCountByExamId.infc', {'examId':examId}, function(data) {
            var ExamStuCountObj = data['object'];
            var tempInputId="";
            var seatTypeList=['','A','B','C','D'];
            for(var i = 0; i < ExamStuCountObj.length; i++){
                tempInputId=ExamStuCountObj[i].examRoomId+'_'+ExamStuCountObj[i].grade+'_'+seatTypeList[parseInt(ExamStuCountObj[i].seatAb)];
                $("#"+tempInputId).val(ExamStuCountObj[i].stuNum);
                      /////////////////////////////统计年级人数
                    tempGrade=ExamStuCountObj[i].grade;
                    checkGradeSetNum(tempGrade);
                     /////////////////////////////统计考场人数
                    tempExamRoomId=ExamStuCountObj[i].examRoomId;
                    checkExamRoomSetNum(tempExamRoomId,seatTypeList[parseInt(ExamStuCountObj[i].seatAb)]);
            }
        }, function() {
            $.loadingBox.close();
        });
        $.loadingBox.close();
    });

    $(document).on("change",".exam_room_input",function(){
        /////////////////////年级人数核对
        var itemId=$(this).attr("id");

        itemIdArry = itemId.split('_');
        //  for(var i=0;i<resultArry.length;i++){
        //      var inputsArry = resultArry[i].split(':');
        //      $('#'+inputsArry[0]).val(inputsArry[1]);
        //      $('#'+inputsArry[0]).attr("readonly","readonly");
        //  }


        //var grade= itemId.substr(itemId.length-3,1);
        var grade=itemIdArry[1];
       // console.debug(grade);
        var stCountGrade=0;
        $("input[id*='_"+grade+"_']").each(function () {
            //console.debug("val==="+$(this).val());
            if($(this).val()){
                stCountGrade=parseInt(stCountGrade)+parseInt($(this).val());
            }
        });
        var gradeInfoId="grade_"+grade;
        $("#"+gradeInfoId).html();
        $("#"+gradeInfoId).html(stCountGrade);
        $("#"+gradeInfoId).css('color','#000');
        if(parseInt($("#"+gradeInfoId).attr("param"))<stCountGrade){
            $.alert_error('设置人数超过年级人数！');
            $("#"+gradeInfoId).css('color','red');
        }
        /////////////////////考场人数核对
        var examRoomId= $(this).parent().parent().attr("param");
        var inputId = $(this).attr("id");
        var ABSeat=inputId.substr(inputId.length-1,1);
        var stCount=0;
        //console.debug(examRoomId+"||"+ABSeat);
        $("input[id^='"+examRoomId+"'][id$='_"+ABSeat+"']").each(function () {
            //console.debug("val==="+$(this).val());
            if($(this).val()){
                stCount=parseInt(stCount)+parseInt($(this).val());
            }
        });
        var infoId=examRoomId+"_"+ABSeat;
        $("#"+infoId).html();
        $("#"+infoId).html(stCount);
        $("#"+infoId).css('color','#000');
        if(parseInt($("#"+infoId).attr("param"))<stCount){
            $.alert_error('设置人数超过配置座位数！');
            $("#"+infoId).css('color','red');
        }
    });

   $('#addBtn').click(function(){
     //  var bol = $("#dataForm").validationEngine("validate");
    //   if (bol) {
           var params = $('#dataForm').getValue();
         //console.debug(params);
           $.loadingBox.show();
           $.ajaxConnSend(this, 'exam/doAddExamExamRoomSetting.infc', params, function(data) {
               if (data.status == '1' && data.object) {
                   $.alert_success('新增成功!');
                   // window.location.href = "../exam/exaExam.html";
               } else {
                   $.alert_error('新增失败');
               }
           }, function() {
               $.loadingBox.close();
           });
       //}

    });

    $('#backBtn').click(function(){
        window.location.href = "../exa/exam/exaExam.html";
    });

    function checkGradeSetNum(grade){
        /////////////////////年级人数核对
        var stCountGrade=0;
        $("input[id*='_"+grade+"_']").each(function () {
            //console.debug("val==="+$(this).val());
            if($(this).val()){
                stCountGrade=parseInt(stCountGrade)+parseInt($(this).val());
            }
        });
        var gradeInfoId="grade_"+grade;
        $("#"+gradeInfoId).html();
        $("#"+gradeInfoId).html(stCountGrade);
        $("#"+gradeInfoId).css('color','#000');
        if(parseInt($("#"+gradeInfoId).attr("param"))<stCountGrade){
            $.alert_error('设置人数超过年级人数！');
            $("#"+gradeInfoId).css('color','red');
        }
    }

    function checkExamRoomSetNum(examRoomId,ABSeat){
        /////////////////////考场人数核对
        var stCount=0;
        console.debug(examRoomId+"||"+ABSeat);
        $("input[id^='"+examRoomId+"'][id$='_"+ABSeat+"']").each(function () {
            //console.debug("val==="+$(this).val());
            if($(this).val()){
                stCount=parseInt(stCount)+parseInt($(this).val());
            }
        });
        var infoId=examRoomId+"_"+ABSeat;
        $("#"+infoId).html();
        $("#"+infoId).html(stCount);
        $("#"+infoId).css('color','#000');
        if(parseInt($("#"+infoId).attr("param"))<stCount){
            $.alert_error('设置人数超过配置座位数！');
            $("#"+infoId).css('color','red');
        }
    }
});