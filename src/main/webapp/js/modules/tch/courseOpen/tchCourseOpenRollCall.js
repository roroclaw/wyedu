/**
 * Created by zl on 2017/6/20.
 */
var courseOpenId=queryString("id");
var perPageLineNum=20;/////每页打印行数
var printLineHeight=27;/////打印行高
var blankCols=18;/////空白列数
var haveZPscore=false;////////是否有总评分，没有就隐藏总评分一列
$(function(){
    $("#content_div_print").hide();

    queryForData(courseOpenId);


    $('#printBtn').click(function(){
        $("#noPrint").hide();
        $("#content_div_print").show();
        preview(1);
    });

});

    function queryForData(courseOpenId){
       // var tempStuId="";
     //   var tempenrolYear="";
      //  var tempTerm="";
        var rollCallListObj;
        var printPageNum=0;
        var pageHeadForPrint="";
        var pageHeadForPrint_f="";
        var pageHeadForPrint_e="";
        var tempHtml_a;
        var tempHtml_b;
        var className="";
        var courseName="";
        var schoolYear="";
        var termText="";
        var teacherName="";
        var tempHtml="";
        var pageHeadForPrint_head="";

        $.loadingBox.show();
            $.ajaxConnSend(this, 'courseOpen/doGetTchCourseOpenStudentsList.infc', {courseOpenId:courseOpenId,stuStatus:'3'}, function(data) { ///////组建空表
                rollCallListObj = data['object'];
               // console.debug(rollCallListObj);
                if(rollCallListObj.length>0){
                    className=rollCallListObj[0].className;
                    courseName=rollCallListObj[0].courseName;
                    schoolYear=rollCallListObj[0].schoolYear;
                    termText=rollCallListObj[0].termText;
                    teacherName=rollCallListObj[0].teacherName;
                }
                tempHtml='<div style="padding:0 20px 10px 20px;font-size:20px;font-weight:700;text-align: center;"><span>学生教学记录表</span></div>'+
                '<div style="padding:0 20px 0px 20px;font-size:16px;text-align: center;"><span>'+schoolYear+"-"+(parseInt(schoolYear)+1)+'&nbsp;&nbsp;'+termText+'</span></div>'+
                '<table style="width: 100%;height: 100%;border: 0px solid black;text-align: left;margin-left: auto; margin-right: auto;"><tr style="border: 0px solid black;">' +
                '<td style="border: 0px solid black;text-align: left;"> <span style="padding-left:50px;">课程名 :</span><span>'+courseName+'</span><span style="padding-left:50px;">任课教师：</span><span>'+teacherName+'</span></td></tr></table>';
                pageHeadForPrint_head='<div style="padding:0 20px 10px 20px;font-size:20px;font-weight:700;text-align: center;"><span>学生教学记录表</span></div>'+
                '<div style="padding:0 20px 0px 20px;font-size:16px;text-align: center;"><span>'+schoolYear+"-"+(parseInt(schoolYear)+1)+'&nbsp;&nbsp;'+termText+'</span></div>'+
                '<table style="width: 100%;height: 100%;border: 0px solid black;text-align: left;margin-left: auto; margin-right: auto;"><tr style="border: 0px solid black;">' +
                '<td style="border: 0px solid black;text-align: left;"> <span style="padding-left:50px;">课程名 :</span><span>'+courseName+'</span><span style="padding-left:50px;">任课教师：</span><span>'+teacherName+'</span></td></tr></table>';
                tempHtml=tempHtml+'<table id="scoreQueryTable" style="width:100%;height: 100%;"  class="tableOut"><tr><td style="width:40px;">序号</td><td style="width:100px;">学号</td>' +
                '<td style="width:130px;">姓名</td><td style="width:150px;">方向</td><td style="width:150px;">班级</td><td style="width:30px;">性别</td><td style="width:80px;">期中<br>成绩</td><td style="width:80px;">期末<br>成绩</td><td style="width:80px;">平时<br>成绩</td>';
                pageHeadForPrint_f=pageHeadForPrint_f+'<table id="scoreQueryTablePrint_';
                pageHeadForPrint_e=pageHeadForPrint_e+'" style="width:100%;height: 100%;font-size:3px;"  class="tableOut"><tr><td style="width:40px;">序号</td><td style="width:90px;">学号</td>' +
                '<td style="width:110px;">姓名</td><td style="width:150px;">方向</td><td style="width:140px;">班级</td><td style="width:30px;">性别</td><td style="width:80px;">期中<br>成绩</td><td style="width:80px;">期末<br>成绩</td><td style="width:80px;">平时<br>成绩</td>';
                for(var n =0;n<blankCols;n++){
                    tempHtml=tempHtml+'<td style="width:60px;">'+(n+1)+'</td>';
                    pageHeadForPrint_e=pageHeadForPrint_e+'<td style="width:60px;">'+(n+1)+'</td>';
                }
                tempHtml=tempHtml+'</tr>';
                pageHeadForPrint_e=pageHeadForPrint_e+'</tr>';
               ////////////////////////////显示区内容
                var tempStId="";
                var lineNum=0;
                tempHtml_a=tempHtml;
                tempHtml_b='<div class="seatsTipPageBreak">'+pageHeadForPrint_head+pageHeadForPrint_f+'0'+pageHeadForPrint_e;
                for(var i =0;i<rollCallListObj.length;i++){
                    lineNum+=1;
                    tempHtml_a=tempHtml_a+'<tr><td>'+lineNum+'</td><td>'+rollCallListObj[i].stuNumber+'</td><td>'+rollCallListObj[i].realName+'</td><td>'+rollCallListObj[i].majorText+'</td><td>'+rollCallListObj[i].className+'</td>' +
                    '<td>'+rollCallListObj[i].sexText+'</td><td></td><td></td><td></td>';
                    if(lineNum%(perPageLineNum+1)==0){
                        printPageNum=printPageNum+1;
                        tempHtml_b=tempHtml_b+'</table><div style="padding:0 20px 5px 20px;font-size:16px;text-align: right;"><span>第'+printPageNum+'页</span></div>' +
                        '</div><div class="seatsTipPageBreak" style="margin-top:50px;">'+pageHeadForPrint_f+printPageNum+pageHeadForPrint_e;
                    }
                    tempHtml_b=tempHtml_b+'<tr style="height:'+printLineHeight+'px;"><td>'+lineNum+'</td><td>'+rollCallListObj[i].stuNumber+'</td><td>'+rollCallListObj[i].realName+'</td><td>'+rollCallListObj[i].majorText+'</td>'+
                    '<td>'+rollCallListObj[i].className+'</td><td>'+rollCallListObj[i].sexText+'</td><td></td><td></td><td></td>';
                    for(var n =0;n<blankCols;n++){
                        tempHtml_a=tempHtml_a+'<td></td>';
                        tempHtml_b=tempHtml_b+'<td></td>';
                    }
                    tempHtml_a=tempHtml_a+'</tr>';
                    tempHtml_b=tempHtml_b+'</tr>';

                }
                printPageNum=printPageNum+1;
                tempHtml_a=tempHtml_a+'</table>';
                tempHtml_b=tempHtml_b+'</table><table style="width: 100%;height: 100%;border: 0px solid black;text-align: left;margin-left: auto; margin-right: auto;margin-top: 20px;"><tr style="border: 0px solid black;">' +
                '<td style="border: 0px solid black;text-align: left;"> <span style="padding-left:50px;">考试日期 :</span><span></span></td><td style="border: 0px solid black;text-align: right;"><span style="padding-left:50px;padding-right:100px;">任课教师签字：</span><span></span></td></tr></table>' +
                '<div style="padding:0 20px 5px 20px;font-size:16px;text-align: right;"><span>第'+printPageNum+'页</span></div>';
                $("#content_div").empty();
                $("#content_div").html(tempHtml_a);
                $("#content_div_print").empty();
                $("#content_div_print").html(tempHtml_b);
             /*      ////////////////////////////////////打印区内容
                tempStId="";
                lineNum=0;
                tempHtml_b='<div class="seatsTipPageBreak">'+pageHeadForPrint_head+pageHeadForPrint_f+'0'+pageHeadForPrint_e;
                for(var j =0;j<ScoreStuListObj.length;j++){
                    if(tempStId!=ScoreStuListObj[j].stuId){///换行
                        lineNum+=1;
                        if(lineNum%(perPageLineNum+1)==0){
                            printPageNum=printPageNum=1;
                            tempHtml_b=tempHtml_b+'</table></div><div class="seatsTipPageBreak">'+pageHeadForPrint_f+printPageNum+pageHeadForPrint_e;
                        }
                        tempHtml_b=tempHtml_b+'<tr style="height:'+printLineHeight+'px;"><td style="width:100px;">'+lineNum+'</td><td style="width:150px;">'+ScoreStuListObj[j].stuNo+'</td>'+
                        '<td style="width:150px;">'+ScoreStuListObj[j].stuName+'</td><td style="width:150px;">'+ScoreStuListObj[j].majorText+'</td>';
                        tempHtml_b=tempHtml_b+'<td style="width:150px;"><span id="'+ScoreStuListObj[j].stuId+'_3_b"></span></td>'+
                        '<td style="width:150px;"><span id="'+ScoreStuListObj[j].stuId+'_1_b"></span></td>'+'<td style="width:150px;"><span id="'+ScoreStuListObj[j].stuId+'_2_b"></span></td>' +
                        '<td style="width:150px;"><span id="'+ScoreStuListObj[j].stuId+'_zp_b"></span></td></tr>';
                        tempStId=ScoreStuListObj[j].stuId;
                    }else{
                        //tempHtml=tempHtml+'<td style="width:150px;"><span id="'+ScoreStuListObj[i].stuId+'_'+ScoreStuListObj[i].courseId+'"></span></td>';
                    }
                }
                tempHtml_b=tempHtml_b+'</table>';
                tempHtml_b=tempHtml_b+'<div style="padding:20px 100px 20px 20px;text-align: right;"><span>教师签名:</span></div>';
                $("#content_div_print").empty();
                $("#content_div_print").html(tempHtml_b);*/
            }, function() {
                $.loadingBox.close();
            });
    }

$('#closeBtn').click(function(){
    self.opener=null;
    self.close();
});

function preview(oper)
{
    if (oper < 10)
    {
        bdhtml=window.document.body.innerHTML;//获取当前页的html代码
        sprnstr="<!--startprint"+oper+"-->";//设置打印开始区域
        eprnstr="<!--endprint"+oper+"-->";//设置打印结束区域
        prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); //从开始代码向后取html
        prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html
        // window.document.body.innerHTML=prnhtml;
        window.print();
        //window.document.body.innerHTML=bdhtml;
        $("#noPrint").show();
        $("#content_div_print").hide();
    } else {
        window.print();
    }
}