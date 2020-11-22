/**
 * Created by zl on 2017/6/20.
 */
var perPageLineNum=30;/////每页打印行数
var printLineHeight=30;/////打印行高
$(function(){
    $("#content_div_print").hide();

    $('#printBtn').click(function(){
        $("#noPrint").hide();
        $("#content_div_print").show();
        preview(1);
    });
    $('#queryBtn').click(function(){
        var params = $('#queryForm').getValue();
        queryForData(params);
    });
});

    function queryForData(params){
       // var tempStuId="";
     //   var tempenrolYear="";
      //  var tempTerm="";
        var realName=$('#realName').val();
        var classText=$('#classText').val();
        var grade=$('#grade').val();
        var classSeq=$('#classSeq').val();
        var sexText=$('#sexText').val();
        var sex=$('#sex').val();
        var birth=$('#birth').val();
        var birthDate=getDate(birth);
        var birthText_ch=birthDate.getFullYear() + "年"+ (birthDate.getMonth() + 1) +"月"+ birthDate.getDate() +"日";
        var birthText_en=dateToEn(birthDate);
       // $('#birthText_ch').html( birthDate.getFullYear() + "年"+ (birthDate.getMonth() + 1) +"月"+ birthDate.getDate() +"日");
       // $('#birthText_en').html(dateToEn(birthDate));

        var ScoreCousesListObj;
        var schollYearNum=0;
        var tempHtml_a;
        var tempHtml_b;///打印部分
        var tableHead='<div  style="padding:0 20px 20px 20px;font-size:23px;font-weight:700;text-align: center;"><span>武汉音乐学院附属中等音乐学校学生成绩单</span></div>' +
            '<div style="padding:0 20px 20px 20px;font-size:16px;text-align: center;"><span>Student'+"'"+'s Transcript of Music Middle School Affiliated to Wuhan Conservatory</span></div>'+
            '<div style="margin:0 auto;">' +
            '<table style="width: auto;height: 100%;border: 0px solid black;text-align: left;margin-left: auto; margin-right: auto;"><tr style="border: 0px solid black;">' +
            '<td style="border: 0px solid black;"> <span>姓名 Name :</span><span>'+realName+'</span></td>' +
            '<td style="border: 0px solid black;"> <span>班级 Class：</span><span>'+classText+'</span><span>Grade</span><span>'+grade+'</span><span>Class</span><span>'+classSeq+'</span></td></tr>' +
            '<tr style="border: 0px solid black;"><td style="border: 0px solid black;"> <span>性别 Gender:</span><span>'+sexText+'</span>';
        if(sex=="1" || sex==1){
            tableHead=tableHead+'<span>Male</span>';
        }else if(sex=="2" || sex==2){
            tableHead=tableHead+'<span>Female</span>';
        }
        tableHead=tableHead+'</td><td  style="border: 0px solid black;"><span>出生日期 Date of Birth:</span><span>'+birthText_ch+'</span><span>'+birthText_en+'</span></td></tr></table>';

        var tempHtml=tableHead+'<table style="width: auto;text-align: center;height: 100%;margin-left: auto; margin-right: auto;border: 1px solid black;"  class="tableOut"><tr><td style="width:200px;" rowspan="2">科目<br>Subject</td>';

        $.loadingBox.show();
            $.ajaxConnSend(this, 'subjectScores/doGetStuScoreCertificateTableInfoByParam.infc', params, function(data) { ///////组建空表
                ScoreStuListObj = data['object'];
                //console.debug(ScoreStuListObj[0]);
                var ScoreStuHeadList=ScoreStuListObj[0];////////表头学年数据
                ////////////////////////////显示区内容
                var tempStId="";
                var lineNum=0;
                for(var i =0;i<ScoreStuHeadList.length;i++){
                    var schollYearText=ScoreStuHeadList[i].schoolYear+"—"+(parseInt(ScoreStuHeadList[i].schoolYear)+1);
                    tempHtml=tempHtml+'<td style="width:200px;" colspan="2">'+schollYearText+'学年<br>'+schollYearText+'Academic Year</td>';
                    schollYearNum+=1;
                }
                tempHtml=tempHtml+'</tr><tr>';
                for(var p =0;p<schollYearNum;p++){
                    tempHtml=tempHtml+'<td style="width:100px;">上学期<br>First Term</td><td style="width:100px;">下学期<br>Second Term</td>';
                }
                tempHtml_a=tempHtml;
                tempHtml_b='<div class="seatsTipPageBreak">'+tempHtml;
                //////////////////////教学计划科目数据
               // console.debug(ScoreStuListObj[1]);
                var ScoreStuTchplanSubjectList=ScoreStuListObj[1];///////科目列
                for(var j =0;j<ScoreStuTchplanSubjectList.length;j++){
                    if((j+1)%(perPageLineNum)==0){//////////////分页标记
                        tempHtml_b=tempHtml_b+'</table></div><div class="seatsTipPageBreak"><table style="text-align: center;height: 100%;margin-left: auto; margin-right: auto;border: 1px solid black;"  class="tableOut">';
                    }
                    tempHtml_a=tempHtml_a+'<tr><td style="width:100px;">'+ScoreStuTchplanSubjectList[j].subjectName+'<br>'+ScoreStuTchplanSubjectList[j].subjectNameEn+'</td>';
                    tempHtml_b=tempHtml_b+'<tr><td style="width:100px;">'+ScoreStuTchplanSubjectList[j].subjectName+'<br>'+ScoreStuTchplanSubjectList[j].subjectNameEn+'</td>';
                    for(var i =0;i<ScoreStuHeadList.length;i++){
                        tempHtml_a=tempHtml_a+'<td style="width:100px;" id="'+ScoreStuTchplanSubjectList[j].subjectId+'_'+ScoreStuHeadList[i].schoolYear+'_1_a'+'"></td>' +
                                              '<td style="width:100px;" id="'+ScoreStuTchplanSubjectList[j].subjectId+'_'+ScoreStuHeadList[i].schoolYear+'_2_a'+'"></td>';
                        tempHtml_b=tempHtml_b+'<td style="width:100px;" id="'+ScoreStuTchplanSubjectList[j].subjectId+'_'+ScoreStuHeadList[i].schoolYear+'_1_b'+'"></td>' +
                                              '<td style="width:100px;" id="'+ScoreStuTchplanSubjectList[j].subjectId+'_'+ScoreStuHeadList[i].schoolYear+'_2_b'+'"></td>';
                    }
                    tempHtml_a=tempHtml_a+'</tr>';
                    tempHtml_b=tempHtml_b+'</tr>';
                }
                tempHtml_a=tempHtml_a+'</tr></table>';
                tempHtml_b=tempHtml_b+'</tr></table></div>';
                tableEnd='<table style="height: 100%;border: 0px solid black;margin-left: auto; margin-right: auto;">' +
                '<tr style="border: 0px solid black;"><td style="border: 0px solid black;text-align: left;"><span>注明：各科满分为100分.(The full mark of all the subjects is 100.)</span></td></tr>' +
                '<tr style="border: 0px solid black;"><td style="border: 0px solid black;text-align: right;"><span>武汉音乐学院附属中等音乐学校</span></td></tr>' +
                '<tr style="border: 0px solid black;"><td style="border: 0px solid black;text-align: right;"><span>Music Middle School Affiliated to Wuhan Conservatory</span></td></tr>' +
                '<tr style="border: 0px solid black;"><td style="border: 0px solid black;text-align: right;"><span>'+birthText_ch+'</span></td></tr>' +
                '<tr style="border: 0px solid black;"><td style="border: 0px solid black;text-align: right;"><span>'+birthText_en+'</span></td></tr></table></div>';
                tempHtml_a=tempHtml_a+tableEnd;
                tempHtml_b=tempHtml_b+tableEnd;
                $("#content_div").empty();
                $("#content_div").append(tempHtml_a);
                $("#content_div_print").empty();
                $("#content_div_print").append(tempHtml_b);
                ////////////////////////////////标题列组建
                /* var tempGrade="";
                var tempSchoolYear="";
                var tempTerm=0;
                for(var i =0;i<ScoreStuListObj.length;i++){

                }

                    tempHtml_a=tempHtml_a+'</table>';
                $("#content_div").empty();
                $("#content_div").html(tempHtml_a);
                   ////////////////////////////////////打印区内容
                tempStId="";
                lineNum=0;
                tempHtml_b='<div class="seatsTipPageBreak">'+tempHtml;
                for(var j =0;j<ScoreStuListObj.length;j++){
                    if(tempStId!=ScoreStuListObj[j].stuId){///换行
                        lineNum+=1;
                        if(lineNum%(perPageLineNum+1)==0){
                            tempHtml_b=tempHtml_b+'</table></div><div class="seatsTipPageBreak">'+pageHeadForPrint;
                        }
                        tempHtml_b=tempHtml_b+'<tr style="height:'+printLineHeight+'px;"><td style="width:100px;">'+lineNum+'</td><td style="width:150px;">'+ScoreStuListObj[j].stuNo+'</td>'+
                        '<td style="width:150px;">'+ScoreStuListObj[j].stuName+'</td><td style="width:150px;">'+ScoreStuListObj[j].majorText+'</td>';
                        tempHtml_b=tempHtml_b+'<td style="width:150px;"><span id="'+ScoreStuListObj[j].stuId+'_3_b"></span></td>'+
                        '<td style="width:150px;"><span id="'+ScoreStuListObj[j].stuId+'_1_b"></span></td>'+'<td style="width:150px;"><span id="'+ScoreStuListObj[j].stuId+'_2_b"></span></td>' +
                        '<span id="'+ScoreStuListObj[j].stuId+'_zp_b"></span></td></tr>';
                    }else{
                        //tempHtml=tempHtml+'<td style="width:150px;"><span id="'+ScoreStuListObj[i].stuId+'_'+ScoreStuListObj[i].courseId+'"></span></td>';
                    }
                }
                tempHtml_b=tempHtml_b+'</table>';
                tempHtml_b=tempHtml_b+'<div style="padding:20px 100px 20px 20px;text-align: right;"><span>教师签名:</span></div>';
                $("#content_div_print").empty();
                $("#content_div_print").html(tempHtml_b);*/
            }, function() {
                $.ajaxConnSend(this, 'subjectScores/doGetStuScoreCertificateInfoByParam.infc', params, function(data) { ///////成绩数据填充
                    ScoreStuListObj = data['object'];
                    var tempStId="";
                    for(var  k=0;k<ScoreStuListObj.length;k++){
                        $("#"+ScoreStuListObj[k].subjectId+"_"+ScoreStuListObj[k].schoolYear+"_"+ScoreStuListObj[k].term+"_a").empty();
                        $("#"+ScoreStuListObj[k].subjectId+"_"+ScoreStuListObj[k].schoolYear+"_"+ScoreStuListObj[k].term+"_a").html(ScoreStuListObj[k].score);
                        $("#"+ScoreStuListObj[k].subjectId+"_"+ScoreStuListObj[k].schoolYear+"_"+ScoreStuListObj[k].term+"_b").empty();
                        $("#"+ScoreStuListObj[k].subjectId+"_"+ScoreStuListObj[k].schoolYear+"_"+ScoreStuListObj[k].term+"_b").html(ScoreStuListObj[k].score);
                    }
                }, function() {
                    $.loadingBox.close();
                });
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