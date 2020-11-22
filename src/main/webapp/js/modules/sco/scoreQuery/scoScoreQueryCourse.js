/**
 * Created by zl on 2017/6/20.
 */
var perPageLineNum=50;/////每页打印行数
var printLineHeight=11;/////打印行高
var haveZPscore=false;////////是否有总评分，没有就隐藏总评分一列
$(function(){
    $("#content_div_print").hide();
    // 表单验证
    $("#queryForm").validationEngine("attach", {
        promptPosition : "topLeft",
        autoHidePrompt : true,
        autoHideDelay : 3000,
        scroll : false
    });
  // console.debug();

   /* $('.examTypeSel').mysel({
        url : 'common/doGetScoresTypeItems.infc',
        text : '考试类型',
        name : 'type',
        isRequired : true,
        id:'type'
    });*/

    $('.termSel').mysel({
        url : 'common/doGetTermItems.infc',
        text : '学期',
        name : 'term',
        id:'term',
        isRequired : true
    });

    $('.schoolYearSel').mysel({
        url : 'common/getFromToEduYearItems.infc',
        text : '学年',
        name : 'schoolYear',
        isRequired : true,
        id:'schoolYear'
    });



    $('.gradeSel').mysel({
        url : 'common/getGradeItems.infc',
        text : '年级',
        name : 'grade',
        isRequired : true,
        id:'grade'
    });

    ///////////响应下拉框选择
    $('#grade').change(function(){
        var gradeCode= $("#grade").find("option:selected").val();
        var schoolYear= $("#schoolYear").find("option:selected").val();
        //班级选择
        if(gradeCode && schoolYear){
            $.ajaxConnSend(this, 'common/getGradeInfoByCode.infc', {code:gradeCode}, function(data) {
                gradeObj = data['object'];
                $('.classSel').empty();
                $('.classSel').mysel({
                    url : 'class/getClassInfoItemsForPower.infc',
                    text : '班级',
                    name : 'classId',
                    id:'classId',
                    isRequired : true,
                    params : {"grade":gradeCode,"schoolYear":schoolYear,"period":gradeObj.period}
                });
            }, function() {
            });
        }
    });

    $('.classSel').change(function(){
        var schoolYear= $("#schoolYear").find("option:selected").val();
        var classId= $("#classId").find("option:selected").val();
        var term= $("#term").find("option:selected").val();
        var grade= $("#grade").find("option:selected").val();
        if(classId && schoolYear){
            $('.courseSel').empty();
            $('.courseSel').mysel({
                url : 'common/getCourseInfoItemsForPower.infc',
                text : '课程',
                name : 'courseId',
                id : 'courseId',
                valueKey : 'courseId',
                textKey : 'courseName',
                isRequired : true,
                params : {"classId":classId,"schoolYear":schoolYear,"term":term,"grade":grade}
            });
        }

    });
        ///////////响应下拉框选择
      /*
       $('.gradeSel').mysel({
       url : 'common/getGradeItems.infc',
       text : '年级',
       name : 'grade',
       isRequired : true,
       id:'grade'
       });

      $('#grade').change(function(){
            var gradeCode= $("#grade").find("option:selected").val();
            var schoolYear= $("#schoolYear").find("option:selected").val();
            var term= $("#term").find("option:selected").val();

            if(gradeCode && schoolYear){
                $.ajaxConnSend(this, 'common/getGradeInfoByCode.infc', {code:gradeCode}, function(data) {
                    gradeObj = data['object'];
                    $('.classSel').empty();
                    $('.classSel').mysel({
                        url : 'class/getClassInfoItemsForPower.infc',
                        text : '班级',
                        name : 'classId',
                        id : 'classId',
                        isRequired : true,
                        params : {"grade":gradeCode,"schoolYear":schoolYear,"period":gradeObj.period,"term":term}
                    });
                }, function() {
                });
            }
            //班级选择
            if(gradeCode && schoolYear){
                    $('.courseSel').empty();
                    $('.courseSel').mysel({
                        url : 'common/getCourseInfoItemsForPower.infc',
                        text : '课程',
                        name : 'courseId',
                        id : 'courseId',
                        valueKey : 'courseId',
                        textKey : 'courseName',
                        isRequired : true,
                        params : {"grade":gradeCode,"schoolYear":schoolYear,"term":term}
                    });
            }
        })
*/




    /**
     * 搜索
     */
    $('#queryBtn').click(function(){
        haveZPscore=false;
        var bol = $("#queryForm").validationEngine("validate");
        if(bol){
        var params = $('#queryForm').getValue();
        params['type']='';
        params['schoolYearText']=$("#schoolYear").find("option:selected").text();
        params['termText']=$("#term").find("option:selected").text();
        params['classText']=$("#classId").find("option:selected").text();
        params['courseText']=$("#courseId").find("option:selected").text();
        queryForData(params);
        }
    });

    $('#printBtn').click(function(){
        $("#noPrint").hide();
        $("#content_div_print").show();
        preview(1);
    });

});

    function queryForData(params){
       // var tempStuId="";
     //   var tempenrolYear="";
      //  var tempTerm="";
        var ScoreCousesListObj;
        var printPageNum=0;
        var pageHeadForPrint="";
        var pageHeadForPrint_f="";
        var pageHeadForPrint_e="";
        var tempHtml_a;
        var tempHtml_b;
        var tempHtml='<div style="padding:0 20px 20px 20px;font-size:20px;font-weight:700;text-align: center;"><span>'+
            params.schoolYearText+params.termText+params.classText+'《'+params.courseText+'》'+'成绩表</span></div>';
        var pageHeadForPrint_head='<div style="padding:0 20px 20px 20px;font-size:20px;font-weight:700;text-align: center;"><span>'+
        params.schoolYearText+params.termText+params.classText+'《'+params.courseText+'》'+'成绩表</span></div>';
        tempHtml=tempHtml+'<table id="scoreQueryTable" style="width:100%;height: 100%;"  class="tableOut"><tr><td style="width:100px;">序号</td><td style="width:150px;">学号</td>' +
            '<td style="width:150px;">姓名</td><td style="width:150px;">专业</td><td style="width:150px;">平时</td><td style="width:150px;">期中</td><td style="width:150px;">期末</td>' +
            '<td style="width:150px;">总评</td></tr>';
        pageHeadForPrint_f=pageHeadForPrint_f+'<table id="scoreQueryTablePrint_';
        pageHeadForPrint_e=pageHeadForPrint_e+'" style="width:100%;height: 100%;"  class="tableOut"><tr style="height:15px;"><td style="width:100px;">序号</td><td style="width:150px;">学号</td>' +
        '<td style="width:150px;">姓名</td><td style="width:150px;">专业</td><td style="width:150px;">平时</td><td style="width:150px;">期中</td><td style="width:150px;">期末</td>' +
        '<td style="width:150px;">总评</td></tr>';
        $.loadingBox.show();

            $.ajaxConnSend(this, 'scoExamScores/doGetScoreStuForQueryList.infc', params, function(data) { ///////组建空表
                ScoreStuListObj = data['object'];
               ////////////////////////////显示区内容
                var tempStId="";
                var lineNum=0;
                tempHtml_a=tempHtml;
                for(var i =0;i<ScoreStuListObj.length;i++){
                    if(tempStId!=ScoreStuListObj[i].stuId){///换行
                        lineNum+=1;
                        tempHtml_a=tempHtml_a+'<tr><td style="width:100px;">'+lineNum+'</td><td style="width:150px;">'+ScoreStuListObj[i].stuNo+'</td>'+
                        '<td style="width:150px;">'+ScoreStuListObj[i].stuName+'</td><td style="width:150px;">'+ScoreStuListObj[i].majorText+'</td>';
                        tempHtml_a=tempHtml_a+'<td style="width:150px;"><span id="'+ScoreStuListObj[i].stuId+'_3_a"></span></td>'+
                        '<td style="width:150px;"><span id="'+ScoreStuListObj[i].stuId+'_1_a"></span></td>'+'<td style="width:150px;"><span id="'+ScoreStuListObj[i].stuId+'_2_a"></span></td>' +
                        '<td style="width:150px;"><span id="'+ScoreStuListObj[i].stuId+'_zp_a"></span></td></tr>';
                        tempStId=ScoreStuListObj[i].stuId;
                    }else{
                        //tempHtml=tempHtml+'<td style="width:150px;"><span id="'+ScoreStuListObj[i].stuId+'_'+ScoreStuListObj[i].courseId+'"></span></td>';
                    }
                }
                tempHtml_a=tempHtml_a+'</table>';
                $("#content_div").empty();
                $("#content_div").html(tempHtml_a);
                   ////////////////////////////////////打印区内容
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
                $("#content_div_print").html(tempHtml_b);
            }, function() {
                $.ajaxConnSend(this, 'scoExamScores/doGetScoreStuForQueryList.infc', params, function(data) {///////填充数据
                    ScoreStuListObj = data['object'];
                    var tempStId="";
                    for(var  k=0;k<ScoreStuListObj.length;k++){
                        /////////////////////////考试成绩
                        $("#"+ScoreStuListObj[k].stuId+"_"+ScoreStuListObj[k].type+"_a").empty();
                        $("#"+ScoreStuListObj[k].stuId+"_"+ScoreStuListObj[k].type+"_a").html(ScoreStuListObj[k].scoreText);
                        $("#"+ScoreStuListObj[k].stuId+"_"+ScoreStuListObj[k].type+"_b").empty();
                        $("#"+ScoreStuListObj[k].stuId+"_"+ScoreStuListObj[k].type+"_b").html(ScoreStuListObj[k].scoreText);
                        /////////////////////////总评成绩
                        if(ScoreStuListObj[k].subjectScoreText){
                            haveZPscore=true;
                        }
                        $("#"+ScoreStuListObj[k].stuId+"_zp_a").empty();
                        $("#"+ScoreStuListObj[k].stuId+"_zp_a").html(ScoreStuListObj[k].subjectScoreText);
                        $("#"+ScoreStuListObj[k].stuId+"_zp_b").empty();
                        $("#"+ScoreStuListObj[k].stuId+"_zp_b").html(ScoreStuListObj[k].subjectScoreText);
                    }
                }, function() {

                    if(!haveZPscore){//////////////////没有总评分就不显示和打印该列
                       /// var l = $("#scoreQueryTable").length;
                        for (var i = 0; i < $("#scoreQueryTable").find('tr').length; i++) {
                            $("#scoreQueryTable tr").eq(i).find("td").eq(7).remove();
                        }
                        $("table[id^='scoreQueryTablePrint_']").each(function(){
                            var l = $(this).find('tr').length;
                            for (var i = 0; i < l; i++) {
                                $(this).find('tr').eq(i).find("td").eq(7).remove();
                            }
                        });

                    }
                    $.loadingBox.close();
                });
            });
    }

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