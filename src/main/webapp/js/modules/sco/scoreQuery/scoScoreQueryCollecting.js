/**
 * Created by zl on 2017/6/20.
 */
var examScoreType=0;//期中显示备注
var perPageLineNum=20;/////每页打印行数
var printLineHeight=27;/////打印行高
$(function(){
    $("#content_div_print").hide();
    // 表单验证
    $("#queryForm").validationEngine("attach", {
        promptPosition : "topLeft",
        autoHidePrompt : true,
        autoHideDelay : 3000,
        scroll : false
    });


    $('.examTypeSel').mysel({
        url : 'common/doGetScoresTypeItems.infc',
        text : '考试类型',
        name : 'type',
        isRequired : true
    });

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

    /*
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
        })
*/




    /**
     * 搜索
     */
    $('#queryBtn').click(function(){
        var params = $('#queryForm').getValue();
        params['schoolYearText']=$("#schoolYear").find("option:selected").text();
        params['termText']=$("#term").find("option:selected").text();
        params['classText']=$("#classId").find("option:selected").text();
        examScoreType=params['type'];
        queryForData(params);
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
        var pageHeadForPrint="";
        var ScoreCousesListObj;
        var tempHtml_a;
        var tempHtml_b;
        var tempHtml='<div style="padding:0 20px 20px 20px;font-size:20px;font-weight:700;text-align: center;"><span>武汉音乐学院附属中等音乐学校学生成绩汇总表</span></div>';
        tempHtml=tempHtml+'<div style="padding:0 20px 20px 20px;font-size:16px;text-align: center;"><span>'+params.schoolYearText+'学年</span><span>'+params.termText+'</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
            '<span>'+params.classText+'</span></div>';
        tempHtml=tempHtml+'<table style="width:100%;height: 100%;font-size: 14px;" cellpadding="1" class="tableOut"><tr><td style="width:100px;">序号</td><td style="width:150px;">学号</td>' +
            '<td style="width:150px;">姓名</td><td style="width:150px;">专业</td>';
        pageHeadForPrint=pageHeadForPrint+'<div style="padding:0 20px 20px 20px;font-size:20px;font-weight:700;text-align: center;"><span>武汉音乐学院附属中等音乐学校学生成绩汇总表</span></div>';
        pageHeadForPrint=pageHeadForPrint+'<div style="padding:0 20px 20px 20px;font-size:16px;text-align: center;"><span>'+params.schoolYearText+'学年</span><span>'+params.termText+'</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
        '<span>'+params.classText+'</span></div>'+'<table style="width:100%;height: 100%;font-size: 13px;" cellpadding="1" class="tableOut"><tr><td style="width:100px;">序号</td><td style="width:150px;">学号</td>' +
        '<td style="width:150px;">姓名</td><td style="width:150px;">专业</td>';
        $.loadingBox.show();
        $.ajaxConnSend(this, 'scoExamScores/doGetScoreCousesForQueryList.infc', params, function(data) {////////组建第一行
            ScoreCousesListObj = data['object'];
            for(var i =0;i<ScoreCousesListObj.length;i++){
                tempHtml=tempHtml+'<td style="width:150px;">'+ScoreCousesListObj[i].subjectText+'</td>';
                pageHeadForPrint=pageHeadForPrint+'<td style="width:150px;">'+ScoreCousesListObj[i].subjectText+'</td>';
            }
            tempHtml=tempHtml+'</tr>';
            pageHeadForPrint=pageHeadForPrint+'<td style="width:150px;">总评分</td></tr>';
        }, function() {
            $.ajaxConnSend(this, 'scoExamScores/doGetScoreStuForScoreCollectingQuery.infc', params, function(data) {///////组建空表
                ScoreStuListObj = data['object'];
                /////////////////////显示区内容
                tempHtml_a=tempHtml;
                var tempStId="";
                var lineNum=0;
                for(var i =0;i<ScoreStuListObj.length;i++){
                    if(tempStId!=ScoreStuListObj[i].stuId){  ///换行
                        lineNum+=1;
                        if(lineNum!=1){
                            tempHtml_a=tempHtml_a+'</tr>';
                        }
                        tempHtml_a=tempHtml_a+'<tr style="width:100px;"><td style="width:100px;">'+lineNum+'</td><td style="width:150px;">'+ScoreStuListObj[i].stuNo+'</td>'+
                        '<td style="width:150px;">'+ScoreStuListObj[i].stuName+'</td><td style="width:150px;">'+ScoreStuListObj[i].majorText+'</td>';
                        for(var j=0;j<ScoreCousesListObj.length;j++){
                            tempHtml_a=tempHtml_a+'<td style="width:150px;"><span id="'+ScoreStuListObj[i].stuId+'_'+ScoreCousesListObj[j].subjectId+'"></span></td>';
                        }
                        tempStId=ScoreStuListObj[i].stuId;
                    }else{
                        //tempHtml_a=tempHtml_a+'<td style="width:150px;"><span id="'+ScoreStuListObj[i].stuId+'_'+ScoreStuListObj[i].courseId+'"></span></td>';
                    }
                }
                tempHtml_a=tempHtml_a+"</tr></table>";
                $("#content_div").empty();
                $("#content_div").html(tempHtml_a);
                ////////////////////////打印区内容
                tempHtml_b='<div class="seatsTipPageBreak">'+pageHeadForPrint;
                tempStId="";
                lineNum=0;
                for(var i =0;i<ScoreStuListObj.length;i++){
                    if(tempStId!=ScoreStuListObj[i].stuId){  ///换行
                        lineNum+=1;
                        if(lineNum!=1){
                            tempHtml_b=tempHtml_b+'<td></td></tr>';
                        }
                        if(lineNum%(perPageLineNum)==1 && lineNum>1){
                            tempHtml_b=tempHtml_b+'</table></div><div class="seatsTipPageBreak">'+pageHeadForPrint;
                        }
                        tempHtml_b=tempHtml_b+'<tr style="width:100px;height:'+printLineHeight+'px;"><td style="width:100px;">'+lineNum+'</td><td style="width:150px;">'+ScoreStuListObj[i].stuNo+'</td>'+
                        '<td style="width:150px;">'+ScoreStuListObj[i].stuName+'</td><td style="width:150px;">'+ScoreStuListObj[i].majorText+'</td>';
                        for(var j=0;j<ScoreCousesListObj.length;j++){
                            tempHtml_b=tempHtml_b+'<td style="width:150px;"><span id="'+ScoreStuListObj[i].stuId+'_'+ScoreCousesListObj[j].subjectId+'_p'+'"></span></td>';
                        }
                        tempStId=ScoreStuListObj[i].stuId;
                    }else{
                       // tempHtml_b=tempHtml_b+'<td style="width:150px;"><span id="'+ScoreStuListObj[i].stuId+'_'+ScoreStuListObj[i].courseId+'"></span></td>';
                    }
                }
                tempHtml_b=tempHtml_b+"<td></td></tr></table></div>";
                $("#content_div_print").empty();
                $("#content_div_print").html(tempHtml_b);
            }, function() {
                $.ajaxConnSend(this, 'scoExamScores/doGetScoreStuForScoreCollectingQuery.infc', params, function(data) {///////填充数据
                    ScoreStuListObj = data['object'];
                    var tempStId="";
                    var lineNum=0;
                    for(var  k=0;k<ScoreStuListObj.length;k++){
                        $("#"+ScoreStuListObj[k].stuId+"_"+ScoreStuListObj[k].subjectId).empty();
                        if(examScoreType=='1' && ScoreStuListObj[k].subjectType=='2'){
                            //$("#"+ScoreStuListObj[k].stuId+"_"+ScoreStuListObj[k].subjectId).html(ScoreStuListObj[k].scoreText+"||"+ScoreStuListObj[k].subjectType+"||"+examScoreType);
                            $("#"+ScoreStuListObj[k].stuId+"_"+ScoreStuListObj[k].subjectId).html(ScoreStuListObj[k].scoreText+"（"+ScoreStuListObj[k].remark+"）");
                            $("#"+ScoreStuListObj[k].stuId+"_"+ScoreStuListObj[k].subjectId+'_p').html(ScoreStuListObj[k].scoreText+"（"+ScoreStuListObj[k].remark+"）");
                        }else{
                            $("#"+ScoreStuListObj[k].stuId+"_"+ScoreStuListObj[k].subjectId).html(ScoreStuListObj[k].scoreText);
                            $("#"+ScoreStuListObj[k].stuId+"_"+ScoreStuListObj[k].subjectId+'_p').html(ScoreStuListObj[k].scoreText);
                        }

                    }
                }, function() {
                    $.loadingBox.close();
                });
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