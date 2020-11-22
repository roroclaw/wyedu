/**
 * Created by dxz on 2017/6/20.
 */
$(function(){

    // 表单验证
    $("#dataForm").validationEngine("attach", {
        promptPosition : "topLeft",
        autoHidePrompt : true,
        autoHideDelay : 3000,
        scroll : false
    });

    //下拉框设置
    //createSel($('#periodSel'),'period','common/doGetPeriodItems.infc',true);
    //createSel($('#typeSel'),'type','common/doGetSubjectTypeItems.infc',true);
    var planId=$("#planId").val();
    var period=$("#period").val();
    var gradeStart=0;
    var name=$("#name").val();

    var table_param_1,table_param_2,table_param_3,table_param_4,max_grade;
    if (period=="1" || period==1){
        max_grade=6;
        table_param_1="14";
        table_param_2='<tr><td colspan="2">第一学年<br>（一年级）</td><td colspan="2">第二学年<br>（二年级）</td><td colspan="2">第三学年<br>（三年级）</td><td colspan="2">第四学年<br>（四年级）</td><td colspan="2">第五学年<br>（五年级）</td><td colspan="2">第六学年<br>（六年级）</td></tr>';
        table_param_3='<tr><td>第一<br>学期</td><td>第二<br>学期</td><td>第一<br>学期</td><td>第二<br>学期</td><td>第一<br>学期</td><td>第二<br>学期</td><td>第一<br>学期</td><td>第二<br>学期</td><td>第一<br>学期</td><td>第二<br>学期</td></tr>';
    }else if(period=="2" || period==2){
        gradeStart=6;
        max_grade=6;
        table_param_1="14";
        table_param_2='<tr><td colspan="2">第一学年<br>（初一）</td><td colspan="2">第二学年<br>（初二）</td><td colspan="2">第三学年<br>（初三）</td><td colspan="2">第一学年<br>（高一）</td><td colspan="2">第二学年<br>（高二）</td><td colspan="2">第三学年<br>（高三）</td></tr>';
        table_param_3='<tr><td>第一<br>学期</td><td>第二<br>学期</td><td>第一<br>学期</td><td>第二<br>学期</td><td>第一<br>学期</td><td>第二<br>学期</td><td>第一<br>学期</td><td>第二<br>学期</td><td>第一<br>学期</td><td>第二<br>学期</td><td>第一<br>学期</td><td>第二<br>学期</td></tr>';
    }
    /*else {
        gradeStart=9;
        max_grade=3;
        table_param_1="8";
        table_param_2='<tr><td colspan="2">第一学年<br>（高一）</td><td colspan="2">第二学年<br>（高二）</td><td colspan="2">第三学年<br>（高三）</td></tr>';
        table_param_3='<tr><td>第一<br>学期</td><td>第二<br>学期</td><td>第一<br>学期</td><td>第二<br>学期</td><td>第一<br>学期</td><td>第二<br>学期</td></tr>';
    }*/
    table_param_4=table_param_1-2;
    $.loadingBox.show();

    $.ajaxConnSend(this, 'tchPlanSubjectRel/doGetTchPlanRelListByTchPlanId.infc', {planId:planId}, function (data) {
        var subjectsObj = data['object'];
        var type_num=1;
        var sub_type_1_num=0;
        var sub_type_2_num=0;
        var sub_type_3_num=0;
        var sub_type_temp="";
        var subject_temp="";
        for(var i =0;i<subjectsObj.length;i++){
            if(subject_temp!=subjectsObj[i].subjectId){
                if(subjectsObj[i].subjectType==0 || subjectsObj[i].subjectType=="0"){
                    sub_type_1_num=sub_type_1_num+1;
                }else if(subjectsObj[i].subjectType==1 || subjectsObj[i].subjectType=="1"){
                    sub_type_2_num=sub_type_2_num+1;
                }else if(subjectsObj[i].subjectType==2 || subjectsObj[i].subjectType=="2"){
                    sub_type_3_num=sub_type_3_num+1;
                }
                subject_temp=subjectsObj[i].subjectId;
            }
        }

        var tableHtml="";
        tableHtml=tableHtml+'<table border="1" cellpadding="0" cellspacing="0" style="text-align:center;border-collapse:collapse;table-layout:fixed;" class="showOutTable"><tbody>' +
        '<tr ><td colspan="'+table_param_1+'">'+name+'</td></tr>' +
        '<tr><td rowspan="3" >课程类别</td><td rowspan="3" >课程名称</td>' +
        '<td colspan="'+table_param_4+'">各学期进程及学期安排</td></tr>';
        tableHtml=tableHtml+table_param_2;
        tableHtml=tableHtml+table_param_3;
        ////////////////////////////////////////文化课
        var first_subject_id="000000";

        for(var i =0;i<subjectsObj.length;i++){
            if(subjectsObj[i].subjectType==0 || subjectsObj[i].subjectType=="0"){///选出文化课
                if(first_subject_id!=subjectsObj[i].subjectId && i==0){
                    tableHtml=tableHtml+'<tr ><td rowspan="'+sub_type_1_num+'" >文<br>化<br>课</td>';
                    tableHtml=tableHtml+'<td>'+subjectsObj[i].subjectName+'</td>';
                    var n=0;
                    for(var j =1;j<=table_param_4;j++){
                        n=n+1;
                        if(n>2){
                            n=1;
                        }
                        tableHtml=tableHtml+'<td id="'+subjectsObj[i].subjectId+'_'+(gradeStart+Math.ceil(j/2))+'_'+n+'"></td>';
                    }
                    tableHtml=tableHtml+'</tr >';
                    first_subject_id=subjectsObj[i].subjectId
                }else  if(first_subject_id!=subjectsObj[i].subjectId && i>0){
                    tableHtml=tableHtml+'<tr ><td>'+subjectsObj[i].subjectName+'</td>';
                    var n=0;
                    for(var j =1;j<=table_param_4;j++){
                        n=n+1;
                        if(n>2){
                            n=1;
                        }
                        tableHtml=tableHtml+'<td id="'+subjectsObj[i].subjectId+'_'+(gradeStart+Math.ceil(j/2))+'_'+n+'"></td>';
                    }
                    tableHtml=tableHtml+'</tr >';
                    first_subject_id=subjectsObj[i].subjectId
                }
              /*  if(subject_temp==subjectsObj[i].subjectId){
                    tableHtml=tableHtml+'<td >'+subjectsObj[i].classHour+'</td>';
                }else if(subject_temp!=subjectsObj[i].subjectId && i>0){
                    tableHtml=tableHtml+'</tr><tr><td>'+subjectsObj[i].subjectName+'</td><td >'+subjectsObj[i].classHour+'</td>';
                    subject_temp=subjectsObj[i].subjectId;
                }else if(subject_temp!=subjectsObj[i].subjectId && i==0){
                    tableHtml=tableHtml+'<td>'+subjectsObj[i].subjectName+'</td><td >'+subjectsObj[i].classHour+'</td>';
                    subject_temp=subjectsObj[i].subjectId;
                }*/
            }
        }

        ////////////////////////////////////////公共课
        var first_subject_id="000000";
        var first_subRow=0;
        for(var i =0;i<subjectsObj.length;i++){

            if(subjectsObj[i].subjectType==1 || subjectsObj[i].subjectType=="1"){///选出公共课
                console.debug(first_subject_id+"||"+subjectsObj[i].subjectId+"||"+i);
                if(first_subject_id!=subjectsObj[i].subjectId && first_subRow==0){
                    tableHtml=tableHtml+'<tr ><td rowspan="'+sub_type_2_num+'" >公<br>共<br>课</td>';
                    tableHtml=tableHtml+'<td>'+subjectsObj[i].subjectName+'</td>';
                    var n=0;
                    for(var j =1;j<=table_param_4;j++){
                            n=n+1;
                            if(n>2){
                                n=1;
                            }
                            tableHtml=tableHtml+'<td id="'+subjectsObj[i].subjectId+'_'+(gradeStart+Math.ceil(j/2))+'_'+n+'"></td>';
                    }
                    tableHtml=tableHtml+'</tr >';
                    first_subject_id=subjectsObj[i].subjectId;
                    first_subRow=i;
                }else  if(first_subject_id!=subjectsObj[i].subjectId && i>0){
                    tableHtml=tableHtml+'<tr ><td>'+subjectsObj[i].subjectName+'</td>';
                    var n=0;
                    for(var j =1;j<=table_param_4;j++){
                        n=n+1;
                        if(n>2){
                            n=1;
                        }
                        tableHtml=tableHtml+'<td id="'+subjectsObj[i].subjectId+'_'+(gradeStart+Math.ceil(j/2))+'_'+n+'"></td>';
                    }
                    tableHtml=tableHtml+'</tr >';
                    first_subject_id=subjectsObj[i].subjectId
                }
            }
        }

        ////////////////////////////////////////专业课
        var first_subject_id="000000";
        var first_subRow=0;
        for(var i =0;i<subjectsObj.length;i++){
            if(subjectsObj[i].subjectType==2 || subjectsObj[i].subjectType=="2"){   ///选出专业课
                if(first_subject_id!=subjectsObj[i].subjectId && first_subRow==0){
                    tableHtml=tableHtml+'<tr ><td rowspan="'+sub_type_3_num+'" >专<br>业<br>课</td>';
                    tableHtml=tableHtml+'<td>'+subjectsObj[i].subjectName+'</td>';
                    var n=0;
                    for(var j =1;j<=table_param_4;j++){
                        n=n+1;
                        if(n>2){
                            n=1;
                        }
                        tableHtml=tableHtml+'<td id="'+subjectsObj[i].subjectId+'_'+(gradeStart+Math.ceil(j/2))+'_'+n+'"></td>';
                    }
                    tableHtml=tableHtml+'</tr >';
                    first_subject_id=subjectsObj[i].subjectId;
                    first_subRow=i;
                }else  if(first_subject_id!=subjectsObj[i].subjectId && i>0){
                    tableHtml=tableHtml+'<tr ><td>'+subjectsObj[i].subjectName+'</td>';
                    var n=0;
                    for(var j =1;j<=table_param_4;j++){
                        n=n+1;
                        if(n>2){
                            n=1;
                        }
                        tableHtml=tableHtml+'<td id="'+subjectsObj[i].subjectId+'_'+(gradeStart+Math.ceil(j/2))+'_'+n+'"></td>';
                    }
                    tableHtml=tableHtml+'</tr >';
                    first_subject_id=subjectsObj[i].subjectId
                }
            }
        }

        ////////////////////////////////////////课时统计
        tableHtml=tableHtml+'<tr ><td></td><td >周学时数</td>';
        var n=0;
        for(var j =1;j<=table_param_4;j++){
            n=n+1;
            if(n>2){
                n=1;
            }
            var totalLineNum=0;
          //  var idEnd="td[id$='_"+Math.ceil(j/2)+"_"+n+"']";
            var idEnd="_"+(gradeStart+Math.ceil(j/2))+"_"+n;

            tableHtml=tableHtml+'<td id="res'+idEnd+'_list"></td>';
        }
        tableHtml=tableHtml+'</tr >';
        tableHtml=tableHtml+'</tbody></table>';
        $("#table_content").append(tableHtml);


///////////////////////////////////////////////填入学时
        for(var i =0;i<subjectsObj.length;i++){
            $("#"+subjectsObj[i].subjectId+"_"+subjectsObj[i].grade+"_"+subjectsObj[i].term).append(subjectsObj[i].classHour);

        }
        ////////////////////////////////////////课时统计结果填入
        var n=0;
        for(var j =1;j<=table_param_4;j++){
            n=n+1;
            if(n>2){
                n=1;
            }
            var totalLineNum=0;
            //  var idEnd="td[id$='_"+Math.ceil(j/2)+"_"+n+"']";
            var idEnd="_"+(gradeStart+Math.ceil(j/2))+"_"+n;
            $("td[id$="+idEnd+"]").each(function(){
                var tempNum=parseInt($(this).html());
                if(tempNum!=null && tempNum!="" && !isNaN(tempNum)){
                    totalLineNum=totalLineNum+tempNum;
                }
            });
            $("#res"+idEnd+"_list").html(totalLineNum);
        }
        $.loadingBox.close();
    });


    $('#closeBtn').click(function(){
        self.opener=null;
        self.close();
    });

});