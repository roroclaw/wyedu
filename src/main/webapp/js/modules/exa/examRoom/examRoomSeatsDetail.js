/**
 * Created by zhanglei on 2017/1/26.
 */
var examId=queryString("p1");
var examRoomId=queryString("p2");
var examRoomSeatAB="";
var examRoomColNum=0;
var examRoomRowNum=0;
var tempColNum=0;///当前列
var tempRowNum=0;//当前行
var seatABList=["","A","B","C","D"];
var totalLineNum=0;
$(function(){
    $.loadingBox.show();
    $.ajaxConnSend(this, 'examRoom/doGetExamRoomInfoById.infc', {id:examRoomId}, function(data) {
        var exaExamRoomObj = data['object'];
      //  $("#examRoomName").html(exaExamRoomObj.name);
       // $("#classroomText").html(exaExamRoomObj.classroomText);
       // $("#examPlanText").html(exaExamRoomObj.examPlanText);
       // $("#buildingNoText").html(exaExamRoomObj.buildingNoText);
        var tempHtml="";
        var tempRowHtml="";
        var tempStuNum=0;
        var tempABType="";
        var jtHtml="";
        examRoomSeatAB=exaExamRoomObj.seatAb;
        examRoomColNum=parseInt(exaExamRoomObj.seatColNum);
        examRoomRowNum=parseInt(exaExamRoomObj.seatRowNum);
        totalLineWidth=parseInt(60/examRoomColNum);///座位td宽度百分比
//console.debug(examRoomColNum);
        $("#excelFileName").val(exaExamRoomObj.examPlanText+exaExamRoomObj.name+exaExamRoomObj.buildingNoText+exaExamRoomObj.classroomText+'考试位示意图');///文件名赋值
        var tableObj = $(' <table style="width:100%" id="examRoomSeatsDetailTable" class="examRoomSeatsDetailTable"><caption><span>'+exaExamRoomObj.examPlanText+'<br>&nbsp;&nbsp;&nbsp;&nbsp;'+exaExamRoomObj.name+'&nbsp;&nbsp;&nbsp;&nbsp;'
        +exaExamRoomObj.buildingNoText+exaExamRoomObj.classroomText+'考试位示意图</span></caption></table>');
        ////////////////////////////////////////////////////////////////////////标题行
       // tempHtml=tempHtml+'<tr><th style="border:0px;padding: 20px 0px;" colspan="'+(examRoomColNum*2-1)+'" class="examRoomSeatsDetailTitle_1"><span>'+exaExamRoomObj.examPlanText+'&nbsp;&nbsp;&nbsp;&nbsp;'+exaExamRoomObj.name+'&nbsp;&nbsp;&nbsp;&nbsp;'
       // +exaExamRoomObj.buildingNoText+exaExamRoomObj.classroomText+'考试位示意图</span></th></tr>';
       // tempHtml=tempHtml+'<caption><span>'+exaExamRoomObj.examPlanText+'&nbsp;&nbsp;&nbsp;&nbsp;'+exaExamRoomObj.name+'&nbsp;&nbsp;&nbsp;&nbsp;'
        // +exaExamRoomObj.buildingNoText+exaExamRoomObj.classroomText+'考试位示意图</span></caption>';
        //tableObj.append(jtHtml);
        var totalSeatNum=examRoomColNum*examRoomRowNum;
        var tempSeat;
        for(var i=0;i<examRoomColNum*examRoomRowNum;i++){
            tempSeat=totalSeatNum-i;
            tempColNum+=1;
            if(i==0){/////第一格
                tempColNum=1;
               // tempHtml=tempHtml+'<tr  style="height:60px;border:0px;" ><td width="'+totalLineWidth+'%"><div id="'+(i+1)+'"></div></td>';
                tempRowHtml='<td width="'+totalLineWidth+'%"><div id="'+tempSeat+'"></div></td></tr>'+tempRowHtml;
            }
            else if(i<examRoomColNum){/////第一行
                tempRowHtml='<td width="'+totalLineWidth+'%"><div id="'+tempSeat+'"></div></td><td style="border:0px solid #FF0000;" rowspan="'+examRoomRowNum+'"></td>'+tempRowHtml;
            }
            else if(tempColNum>examRoomColNum){/////////换行
                tempColNum=1;
                tempRowNum+=1;
               // tempHtml=tempHtml+'</tr><tr  style="height:60px;border:0px;" ><td><div id="'+(i+1)+'"></div></td>';
                tempRowHtml='<tr  style="height:60px;border:0px;" >'+tempRowHtml+'';
                console.debug(tempRowHtml);
                tempHtml=tempHtml+tempRowHtml;
                tempRowHtml='<td width="'+totalLineWidth+'%"><div id="'+tempSeat+'"></div></td></tr>';
            }else{
                tempRowHtml='<td><div id="'+tempSeat+'"></div></td>'+tempRowHtml;
            }
        }
        tempRowHtml='<tr  style="height:60px;border:0px;" >'+tempRowHtml+'';
        tempHtml=tempHtml+tempRowHtml;
       // tempHtml=tempHtml+'<tr ><td  colspan="'+(examRoomColNum*2-1)+'" style="border:0px solid #FF0000;"  align="center"></td></tr>' +
       //                   '<tr ><td  colspan="'+(examRoomColNum*2-1)+'" align="center">（讲台位置）</td></tr>';
        tableObj.append(tempHtml);

        /*
        for(var i=0;i<examRoomColNum*examRoomRowNum;i++){
                tempColNum+=1;
            if(i==0){/////第一格
                tempColNum=1;
                tempHtml=tempHtml+'<tr  style="height:60px;border:0px;" ><td width="'+totalLineWidth+'%"><div id="'+(i+1)+'"></div></td>';
            }
            else if(i<examRoomColNum){/////第一行
                tempHtml=tempHtml+'<td style="border:0px solid #FF0000;" rowspan="'+examRoomRowNum+'"></td><td width="'+totalLineWidth+'%"><div id="'+(i+1)+'"></div></td>';
            }
            else if(tempColNum>examRoomColNum){/////////换行
                tempColNum=1;
                tempRowNum+=1;
                tempHtml=tempHtml+'</tr><tr  style="height:60px;border:0px;" ><td><div id="'+(i+1)+'"></div></td>';
            }else{
                tempHtml=tempHtml+'<td><div id="'+(i+1)+'"></div></td>';
            }
        }

        tempHtml=tempHtml+'</tr>';
        tempHtml=tempHtml+'<tr ><td  colspan="'+(examRoomColNum*2-1)+'" align="center">（讲台位置）</td></tr>';
        tableObj.append(tempHtml); */
        $.ajaxConnSend(this, 'exam/doGetExamRoomStudentsListByParam.infc', {examId:examId,id:examRoomId}, function(data) {
            var examRoomStudentsListObj = data['object'];
            if(examRoomStudentsListObj.length>0){
               // $("#subjectText").html(examRoomStudentsListObj[0].subjectText);
            }
            if(examRoomSeatAB){
                examRoomSeatABArry = examRoomSeatAB.split('|');
                for(var j=0;j<examRoomStudentsListObj.length;j++){
                    tempABType=examRoomSeatABArry[examRoomStudentsListObj[j].seatNum-1].substr(0,1);

                    if(!seatABList[examRoomStudentsListObj[j].stuSeatAb]==tempABType){///对比考场AB座参数和准考信息AB座参数
                        $.alert_error('数据检验发现错误！');
                    }
                    if(examRoomStudentsListObj[j].seatNum>tempStuNum){
                        tempStuNum=parseInt(examRoomStudentsListObj[j].seatNum);////////////当前定位
                        /*$("#"+tempStuNum).html("<span>"+examRoomStudentsListObj[j].gradeName+examRoomStudentsListObj[j].className+"</span><br><span>"+"姓名："+examRoomStudentsListObj[j].realName+"</span><br><span>"+"学号："+examRoomStudentsListObj[j].stuNumber+
                        "&nbsp;&nbsp;&nbsp;&nbsp;座号："+examRoomStudentsListObj[j].seatNum+'&nbsp;&nbsp;&nbsp;&nbsp;座位类型：'+ seatABList[examRoomStudentsListObj[j].stuSeatAb]+"|"+tempABType+"</span>");*/
                        console.debug("#"+tempStuNum+"::"+examRoomStudentsListObj[j].seatNum);
                        $("#"+tempStuNum).html(examRoomStudentsListObj[j].gradeName+"<br style='mso-data-placement:same-cell;'/>"
                        +"姓名："+examRoomStudentsListObj[j].realName+"<br style='mso-data-placement:same-cell;'/>座号："+examRoomStudentsListObj[j].seatNum);
                    }
                }
            }else{
                $.alert_error('参数缺失！');
            }

        }, function() {
          /*$('#examRoomSeatsDetailTable').DataTable( {
                "searching": false,
                "bPaginate": false,
                "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
                dom: 'Bfrtip',
                buttons: [ {
                    extend: 'excelHtml5',
                    text: '导出excel文件',//定义导出excel按钮的文字
                    customize: function ( xlsx ){
                        var sheet = xlsx.xl.worksheets['eefedfef.xml'];

                        // jQuery selector to add a border
                        //  $('row c[r*="10"]', sheet).attr( 's', '25' );
                    }
                } ,
                    {
                        extend: 'pdfHtml5',
                        message: 'PDF created by PDFMake with Buttons for DataTables.'
                    },'print'],
                'language': {
                    'emptyTable': '没有数据',
                    'loadingRecords': '加载中...',
                    'processing': '查询中...',
                    'search': '检索:',
                    'lengthMenu': '每页 _MENU_ 件',
                    'zeroRecords': '没有数据',
                    'paginate': {
                        'first':      '第一页',
                        'last':       '最后一页',
                        'next':       '后一页',
                        'previous':   '前一页'
                    },
                    'info': '',
                    'bInfo': false,//页脚信息
                    'bSortable': false, //排序功能
                    'infoEmpty': '没有数据',
                    'infoFiltered': '(过滤总件数 _MAX_ 条)'
                }
            } );*/
        });
        $("#content_div").append(tableObj);
        $("#content_div").append(' <div style="width:'+totalLineWidth+'%;height:40px;margin: 20px auto;border: 1px solid #cecece;text-align: center;">' +
        '<span style="text-align: center;line-height: 40px;">（讲台位置）</span></div>');
    }, function() {
        $.loadingBox.close();
    });

    $('#previewBtn').click(function(){
        preview(1);
    });

    $('#excelBtn').click(function(){

        tableToExcel('examRoomSeatsDetailTable');
    });

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
        window.document.body.innerHTML=prnhtml;
        window.print();
        window.document.body.innerHTML=bdhtml;
        //////////////////////////////////////////再次加载按钮，防止失效
        $('#previewBtn').click(function(){
            preview(1);
        });
    } else {
        window.print();
    }
}