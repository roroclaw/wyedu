/**
 * Created by zl on 2017/6/20.
 */
$(function(){

    // 表单验证
    $("#dataForm").validationEngine("attach", {
        promptPosition : "topLeft",
        autoHidePrompt : true,
        autoHideDelay : 3000,
        scroll : false
    });
  // console.debug();
    $('.gradeSel').mysel({
        url : 'common/getGradeItems.infc',
        text : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年级',
        name : 'grade',
        isRequired : true
    });
    /**
     * 搜索
     */
    $('#queryBtn').click(function(){
        var params = $('#queryForm').getValue();
        queryForData(params);
    });

    $('#closeBtn').click(function(){
        self.opener=null;
        self.close();
    });

    function queryForData(params){
        $("#content_div").empty();
        var tempStuId="";
        var tempenrolYear="";
        var tempTerm="";
        var tempHtml='<table style="width:100%;height: 100%;"  class="tableOut">';
        var stuNum=0;
        $.loadingBox.show();
        $.ajaxConnSend(this, 'tchPlanSubjectRel/doGetTchPlanCourseOpenCheckList.infc', params, function(data) {
            var planCourseOpenListObj = data['object'];
            //console.debug(planCourseOpenListObj.length);
            for(var i =0;i<planCourseOpenListObj.length;i++){
                if(i==0){
                    stuNum=stuNum+1;
                    tempHtml=tempHtml+'<tr><td style="width:50px;">'+stuNum+'</td>'+'<td style="width:150px;">'+planCourseOpenListObj[i].realName+'</td><td style="width:150px;">'+planCourseOpenListObj[i].majorName+'</td><td>'+
                    '<table style="width:100%;height: 100%;"  class="tableIn">'+'<tr><td style="width:100px;">'+planCourseOpenListObj[i].gradeName+'</td>'
                    +'<td style="width:100px;">'+planCourseOpenListObj[i].term+'</td><td>';
                }else if(planCourseOpenListObj[i].stuId==tempStuId){/////////////////同一学生
                    if(planCourseOpenListObj[i].enrolYear==tempenrolYear){////同年级
                        if(planCourseOpenListObj[i].term==tempTerm){////同学期

                        }else{///////////////////////////不同学期
                           // tempHtml=tempHtml+'<td>'+planCourseOpenListObj[i].term+'</td><td>';
                            tempHtml=tempHtml+'</td></tr><tr><td style="width:100px;" >'+planCourseOpenListObj[i].gradeName+'</td>'+'<td style="width:100px;">'+planCourseOpenListObj[i].term+'</td><td>';
                        }
                    }else{///////////////////////////不同年级
                            tempHtml=tempHtml+'</td></tr><tr><td style="width:100px;">'+planCourseOpenListObj[i].gradeName+'</td>'+'<td style="width:100px;">'+planCourseOpenListObj[i].term+'</td><td>';

                    }
                   // tempHtml=tempHtml+'</li></ul><ul><li>'+planCourseOpenListObj[i].realName+'</li><li>';
                }else{////////////////////////////////另一学生
                    stuNum=stuNum+1;
                    tempHtml=tempHtml+'</td></tr></table></td></tr><tr><td style="width:50px;">'+stuNum+'</td>'+'<td style="width:150px;">'+planCourseOpenListObj[i].realName+'</td><td style="width:150px;">'+planCourseOpenListObj[i].majorName+'</td><td>' +
                    '<table style="width:100%;height: 100%;"  class="tableIn">'+'<tr><td style="width:100px;">'+planCourseOpenListObj[i].gradeName+'</td>'
                    +'<td style="width:100px;">'+planCourseOpenListObj[i].term+'</td><td>';
                }
                tempStuId=planCourseOpenListObj[i].stuId;
                tempenrolYear=planCourseOpenListObj[i].enrolYear;
                tempTerm=planCourseOpenListObj[i].term;
                tempHtml=tempHtml+'<span>'+planCourseOpenListObj[i].subjectId+'||'+planCourseOpenListObj[i].subjectName+'</span>';
            }
            tempHtml=tempHtml+'</td></tr></table></td></tr></table>';
            $("#content_div").append(tempHtml);

        }, function() {
            $.loadingBox.close();
        });
    }
});