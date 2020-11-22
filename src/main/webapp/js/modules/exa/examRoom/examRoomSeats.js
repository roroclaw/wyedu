/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){
    var id=$('#id').val();
    var seatColNum=$('#seatColNum').val();
    var seatRowNum=$('#seatRowNum').val();
    var seatAb=$('#seatAb').val();
    var resultArry;
    var seatNum=0;
    if(seatAb){
         resultArry = seatAb.split('|');
      //  for(var i=0;i<resultArry.length;i++){
      //      var inputsArry = resultArry[i].split(':');
      //      $('#'+inputsArry[0]).val(inputsArry[1]);
      //      $('#'+inputsArry[0]).attr("readonly","readonly");
      //  }
    }

    var tableHtml='<table style="width:100%" class="examSeatTable" id="examSeatTable">';

  if(seatColNum && seatRowNum){
      for(var i = 1; i <= seatRowNum; i++){
          tableHtml=tableHtml+'<tr>';
          for(var j = 1; j <= seatColNum; j++){
              if(seatAb){
                  var seatAB=resultArry[seatNum].substring(0,1);
                  var seatXY=resultArry[seatNum].substring(2);
                  if(seatAB=="A"){
                      tableHtml=tableHtml+'<td><input name="'+i+'_'+j+'" type="text" value="'+seatAB+'" param="'+seatXY+'" style="background:#0ba742;"/></td>';
                  }else if(seatAB=="B"){
                      tableHtml=tableHtml+'<td><input name="'+i+'_'+j+'" type="text" value="'+seatAB+'" param="'+seatXY+'" style="background:#2279fe;"/></td>';
                  }else{
                      tableHtml=tableHtml+'<td><input name="'+i+'_'+j+'" type="text" value="" param="'+seatXY+'" style="background:#fff;"/></td>';
                  }
              }else{
                  tableHtml=tableHtml+'<td><input name="'+i+'_'+j+'" type="text" value="" /></td>';
              }
              seatNum=seatNum+1;
          }
          tableHtml=tableHtml+'</tr>';
      }
  }
    tableHtml=tableHtml+'</table>';
    $("#content_div").empty();
    $("#content_div").append(tableHtml);

    $('#examSeatTable input').click(function(){
        var seatType=$("input[name='seatType']:checked").val();
        if(seatType=="A"){
            $(this).val(seatType);
            $(this).css("background","#0ba742");
        }else if(seatType=="B"){
            $(this).val(seatType);
            $(this).css("background","#2279fe");
        }else{
            $(this).val("");
            $(this).css("background","#fff");
        }
    });

    /**
     * 新增
     */
    $('#addBtn').click(function(){
        $.loadingBox.show();
        $.ajaxConnSend(this, 'examRoom/doCheckExamRoomForUsed.infc', {id:id}, function(data) {
            var items = data['object'];
            //console.log(items);
            if(items){
                $.alert_confirm('该考场已经被编排了考生，修改其AB座数据将导致相关数据被清除！',function(){
                    SetExamRoomSeats();
                });
            }else{
                SetExamRoomSeats();
            }

        }, function() {
            $.loadingBox.close();
        });
    });

    function SetExamRoomSeats(){
        var seatAb = $('#dataForm').getValue();
        var params="";
        var j=0;
        var seatVal;
        for(var i in seatAb){
            j=j+1;
            if (seatAb.hasOwnProperty(i)) { //filter,只输出私有属性
                if(seatAb[i]=="" || seatAb[i]==undefined){
                    seatVal="0";
                }else{
                    seatVal=seatAb[i];
                }
                if(j<=1){
                    params=params+seatVal+'#'+j;
                }else{
                    params=params+'|'+seatVal+'#'+j;
                }
            }
        }
        $.loadingBox.show();
        $.ajaxConnSend(this, 'examRoom/doSetExamRoomSeats.infc', {id:id,seatAb:params}, function(data) {
            if (data.status == '1' && data.object) {
                $.alert_success('设置成功!');
                setTimeout("self.close()",1200);
            } else {
                $.alert_error('设置失败');
            }
        }, function() {
            $.loadingBox.close();
        });
    }

    $('#closeBtn').click(function(){
        self.opener=null;
        self.close();
    });

});
