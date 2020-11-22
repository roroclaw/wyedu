/**
 * Created by zhanglei on 2017/1/26.
 */
var examPlanId=queryString("id");
$('#examPlanId').val(examPlanId);
totalLineWidth=parseInt(90/6);
var bdhtml="";
$(function(){
    // 表单验证
    $("#queryForm").validationEngine("attach", {
        promptPosition : "topLeft",
        autoHidePrompt : true,
        autoHideDelay : 3000,
        scroll : false
    });
    //初始化年级信息
    var gradeSel = $('#gradeSel').mysel({
        url : 'common/getGradeItems.infc',
        text : '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年级',
        name : 'grade',
        isRequired : true
    });
    $('.subjectTypeSel').mysel({
        url : 'common/doGetSubjectTypeItems.infc',
        text : '科目类型',
        name : '',
        isRequired : true,
        id:'subjectType'
    });
    ///////////响应下拉框选择
    $('.subjectTypeSel').change(function(){
        var type= $("#subjectType").find("option:selected").val();
        $('.subjectSel').empty();
        $('.subjectSel').mysel({
            url : 'subject/getSubjectInfoItems.infc',
            text : '考试科目',
            name : 'subjectId',
            id : 'subjectId',
            params : {"type":type,"status":'1'},
            isRequired : true,
            valueKey : 'code',
            textKey : 'text'
        });
    });
    /**
     * 搜索
     */
    $('#queryBtn').click(function(){
        var bol = $("#queryForm").validationEngine("validate");
        if (bol) {
            var params = $('#queryForm').getValue();
            queryForData(params);
        }
    });


    $('#printBtn').click(function(){
        window.print();
    });

    $('#previewBtn').click(function(){
       preview(1);
    });
});

function queryForData(params){
    var tempHtml='<table style="width:100%" id="examStudentsDetailTable" class="examStudentsDetailTable"><thead><tr><th>序号</th><th>学号</th><th>姓名</th><th>专业方向</th><th>年级</th><th>考场</th><th>考试地点</th><th>教室位置</th></tr></thead><tbody>';
    $.loadingBox.show();
    $.ajaxConnSend(this, 'exam/doGetExamStudentsDetailList.infc', params, function(data) {
        var examRoomStudentsListObj = data['object'];
        console.debug('length::'+examRoomStudentsListObj.length);
        for(var i=0;i<examRoomStudentsListObj.length;i++){
            tempHtml=tempHtml+'<tr  style="height:60px;" ><td >'+(i+1)+'</td><td >'+examRoomStudentsListObj[i].stuNumber+'</td><td width="'+totalLineWidth+'%">'+examRoomStudentsListObj[i].realName+'</td>' +
            '<td width="'+totalLineWidth+'%">'+examRoomStudentsListObj[i].majorName+'</td><td width="'+totalLineWidth+'%">'+examRoomStudentsListObj[i].gradeName+'</td>' +
            '<td width="'+totalLineWidth+'%">'+examRoomStudentsListObj[i].examRoomName+'</td><td width="'+totalLineWidth+'%">'+examRoomStudentsListObj[i].buildingNoText+'</td><td width="'+totalLineWidth+'%">'+examRoomStudentsListObj[i].classroomText+'</td></tr>';

        }
        tempHtml=tempHtml+'</tbody></table>';
        var grade_sel=$("#gradeSel").find("option:selected").text();
        var subjectId_sel=$("#subjectId").find("option:selected").text();
        $("#excelFileName").val(examRoomStudentsListObj[0].examPlanText+grade_sel+subjectId_sel+'考生名单');///文件名赋值
        $("#content_div").empty();
        $("#content_div").append(tempHtml);
        $.loadingBox.close();
    }, function() {
        /*
        $.loadingBox.close();
        bdhtml=window.document.body.innerHTML;//获取当前页无打印按钮的html代码
        $('#examStudentsDetailTable').DataTable( {
         "searching": false,
         "bPaginate": false,
         "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
         dom: 'Bfrtip',
         buttons: [ {
         extend: 'excelHtml5',
         text: '导出excel文件',//定义导出excel按钮的文字
         customize: function ( xlsx ){

             console.debug(xlsx);
             alert(xlsx);
         var sheet = xlsx.xl.worksheets['eefedfef.xml'];
         // jQuery selector to add a border
         //  $('row c[r*="10"]', sheet).attr( 's', '25' );
          }
         } ,{
             text: '打印',//定义导出excel按钮的文字
             action: function ( e, dt, node, config ) {
                 preview(1);
             }
         }],
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
         });
        */
    });
}
/*
function dataTableBuild(){
    $('#examStudentsDetailTable').DataTable( {
        "searching": false,
        "bPaginate": false,
        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
        dom: 'Bfrtip',
        buttons: [ {
            extend: 'excelHtml5',
            text: '导出excel文件',//定义导出excel按钮的文字
            customize: function ( xlsx ){
                console.debug(xlsx);
                var sheet = xlsx.xl.worksheets['eefedfef.xml'];

                // jQuery selector to add a border
                //  $('row c[r*="10"]', sheet).attr( 's', '25' );
            }
        } ,{
            text: '打印',//定义导出excel按钮的文字
            action: function ( e, dt, node, config ) {
                preview(1);
            }
        }],
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
    });
}
*/
$('#previewBtn').click(function(){
    preview(1);
});
$('#excelBtn').click(function(){

    tableToExcel('examStudentsDetailTable');
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