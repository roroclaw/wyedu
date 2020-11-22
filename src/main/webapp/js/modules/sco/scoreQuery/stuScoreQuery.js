/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){
    //初始化角色信息
    $('.statusSel').mysel({
        url : 'common/doGetUserStatus.infc',
        text : '状态',
        name : 'userStatus',
        isRequired : false
    });
    $('.schoolYearSel').mysel({
        url : 'common/getFromToEduYearItems.infc',
        text : '学年',
        name : 'schoolYear',
        isRequired : false
    });
    $('.termSel').mysel({
        url : 'common/doGetTermItems.infc',
        text : '学期',
        name : 'term',
        isRequired : true,
        id:'term'
    });
    //table
    var table = $('.dataTable').mytable({
        isCheck: false,
        //idKeyName:'userId',
        header: [{
            code: 'subjectName',
            text: '科目'
        }, {
            code: 'schoolYear',
            text: '学年'
        }, {
            code: 'termText',
            text: '学期'
        }, {
            code: 'middleScoreText',
            text: '期中',
            formateFun:function(txt, i, code, rowdata){
                var dateChar="";
                var middleScoreText=rowdata.middleScoreText;
                var remark=rowdata.remark;
                if(!middleScoreText){ // "",null,undefined,NaN
                    middleScoreText=" ";
                }
                if(!remark){ // "",null,undefined,NaN
                    remark=" ";
                }else{
                    remark="("+remark+")";
                }
                dateChar= middleScoreText+remark;
                return dateChar;
            }
        }, {
            code: 'endScoreText',
            text: '期末'
        }, {
            code: 'usualScoreText',
            text: '平时'
        }, {
            code: 'totalScore',
            text: '总成绩'
        }
        ],
        params:{term:'99'},
        url: 'scoExamScores/doStuQueryScorePageData.infc'
    });

  /*  $('.subjectSel').mysel({
        url : 'subject/getSubjectInfoItems.infc',
        text : '科目',
        name : 'subjectId',
        isRequired : true,
        valueKey : 'code',
        textKey : 'text'
    });*/

    /**
     * 搜索
     */
    $('#queryBtn').click(function(){
        var params = $('#queryForm').getValue();
        table.refreshData(params);
    });

});