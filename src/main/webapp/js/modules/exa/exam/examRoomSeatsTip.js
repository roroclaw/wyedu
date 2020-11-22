/**
 * Created by zhanglei on 2017/1/26.
 */
var examId=queryString("id");
var tempColNum=0;///当前列
var tempRowNum=1;//当前行
var tempRowTotalNum=0;//当前总行
var tempHtml="";
var tempExamRoomId="";
////////////////////////////////////
var rowItemNum=2;////每行列数
var colItemNum=9;////每页行数
var titleTdWidth=65;/////标题格宽度
$("#content_div_print").hide();
$(function(){
    $.loadingBox.show();
    $.ajaxConnSend(this, 'exam/doGetExamRoomStudentsListByParam.infc', {examId:examId}, function(data) {
        var examRoomStudentsListObj = data['object'];
        for(var i=0;i<examRoomStudentsListObj.length;i++){
            if(i==0 && tempExamRoomId==""){
                tempExamRoomId=examRoomStudentsListObj[0].examRoomId;
            }
            tempColNum+=1;
            if(tempColNum<(rowItemNum+1)){///////不换行
            }else{ ///////换行
                tempColNum=1;
                tempRowNum+=1;
                tempRowTotalNum+=1;
                if(tempRowNum>colItemNum){//////////////////下一页
                    tempRowNum=1;
                }
                tempHtml=tempHtml+'<div class="clear"></div>';
            }
            if(tempExamRoomId==examRoomStudentsListObj[i].examRoomId){  //////////同一考场
                tempHtml=tempHtml+'<div class="seatsTipItemDiv"><table><tr><td width="'+titleTdWidth+'">学号</td><td colspan=3>'+examRoomStudentsListObj[i].stuNumber+'</td></tr>' +
                '<tr><td width="'+titleTdWidth+'">姓名</td><td colspan=3>'+examRoomStudentsListObj[i].realName+'</td></tr>' +
                '<tr><td width="'+titleTdWidth+'">考场</td><td>'+examRoomStudentsListObj[i].examRoomName+'</td><td width="'+titleTdWidth+'">年级</td><td>'+examRoomStudentsListObj[i].gradeName+'</td></tr>' +
                '</table></div>';
            }else{ //////////不同考场，空表补齐,换页
                while (tempRowNum<(colItemNum+1)){  /////////空表补齐
                    if(tempColNum<(rowItemNum+1)){ ///////不换行
                    }else{///////换行
                        tempColNum=1;
                        tempRowNum+=1;
                    }
                    if(tempRowNum<(colItemNum+1)) {
                        tempHtml = tempHtml + '<div class="seatsTipItemDiv"><table><tr><td width="' + titleTdWidth + '">学号</td><td colspan=3></td></tr>' +
                        '<tr><td width="' + titleTdWidth + '">姓名</td><td colspan=3>'  + '</td></tr>' +
                        '<tr><td width="' + titleTdWidth + '">考场</td><td></td><td width="' + titleTdWidth + '">年级</td><td></td></tr>' +
                        '</table></div>';
                    }
                    if(tempColNum==rowItemNum ){
                        tempHtml=tempHtml+'<div class="clear"></div>';
                    }
                    if(tempColNum==1 && tempRowNum==1){
                        tempHtml='<div class="seatsTipPageBreak">'+tempHtml+'';
                    }
                    if(tempColNum==rowItemNum && tempRowNum==colItemNum){
                        tempHtml=tempHtml+'</div>';
                       // console.debug(tempHtml);
                        /////////////////////////输出代码到页面
                        $("#content_div").append(tempHtml);
                        tempHtml="";
                    }
                    tempColNum+=1;
                }
                /////////////////////////输出代码到页面
                $("#content_div").append(tempHtml);
                tempHtml="";
                tempColNum=1;
                tempRowNum=1;
                tempHtml=tempHtml+'<div class="seatsTipItemDiv"><table><tr><td width="'+titleTdWidth+'">学号</td><td colspan=3>'+examRoomStudentsListObj[i].stuNumber+'</td></tr>' +
                '<tr><td width="'+titleTdWidth+'">姓名</td><td colspan=3>'+examRoomStudentsListObj[i].realName+'</td></tr>' +
                '<tr><td width="'+titleTdWidth+'">考场</td><td>'+examRoomStudentsListObj[i].examRoomName+'</td><td width="'+titleTdWidth+'">年级</td><td>'+examRoomStudentsListObj[i].gradeName+'</td></tr>' +
                '</table></div>';
            }
            if(tempColNum==rowItemNum ){
                tempHtml=tempHtml+'<div class="clear"></div>';
            }
            if(tempColNum==1 && tempRowNum==1){
                tempHtml='<div class="seatsTipPageBreak">'+tempHtml+'';
            }
            if(tempColNum==rowItemNum && tempRowNum==colItemNum){
                tempHtml=tempHtml+'</div>';
                /////////////////////////输出代码到页面
                $("#content_div").append(tempHtml);
                tempHtml="";
            }
            tempExamRoomId=examRoomStudentsListObj[i].examRoomId;
        }

        ///////////////////
        tempHtml=tempHtml+'<div class="clear"></div>';
        tempColNum=tempColNum+1;
        while (tempRowNum<(colItemNum+1)){  /////////空表补齐
            if(tempColNum<(rowItemNum+1)){ ///////不换行
            }else{///////换行
                tempColNum=1;
                tempRowNum+=1;
            }
            if(tempRowNum<(colItemNum+1)) {
                tempHtml = tempHtml + '<div class="seatsTipItemDiv"><table><tr><td width="' + titleTdWidth + '">学号</td><td colspan=3></td></tr>' +
                '<tr><td width="' + titleTdWidth + '">姓名</td><td colspan=3>'  + '</td></tr>' +
                '<tr><td width="' + titleTdWidth + '">考场</td><td></td><td width="' + titleTdWidth + '">年级</td><td></td></tr>' +
                '</table></div>';
            }
            if(tempColNum==rowItemNum ){
                tempHtml=tempHtml+'<div class="clear"></div>';
            }
            if(tempColNum==1 && tempRowNum==1){
                tempHtml='<div class="seatsTipPageBreak">'+tempHtml+'';
            }
            if(tempColNum==rowItemNum && tempRowNum==colItemNum){
                tempHtml=tempHtml+'</div>';
                // console.debug(tempHtml);
                /////////////////////////输出代码到页面
                $("#content_div").append(tempHtml);
                tempHtml="";
            }
            tempColNum+=1;
        }
        /////////////////////////输出代码到页面
        $("#content_div").append(tempHtml);
        tempHtml="";

    }, function() {
        $.loadingBox.close();
    });

    $('#printBtn').click(function(){
        preview(1);
    });

    $('#previewBtn').click(function(){
        preview(1);
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
        window.location.reload();
        //window.document.body.innerHTML=bdhtml;
    } else {
        window.print();
    }
}