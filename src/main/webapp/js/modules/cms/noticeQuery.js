/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){
    var noticeType=3;
    //table
    var table = $('.dataTable').mytable({
        isCheck: true,
        idKeyName:'article_id',
        header: [{
            code: 'title',
            text: '标题'
        },{
            code: 'keyWord',
            text: '班级'
        }, {
            code: 'author_name',
            text: '发布人'
        }, {
            code: 'update_time',
            text: '发布时间',
            formateFun:function(txt, i, code, rowdata){
                var dateChar="";
                dateChar=  $.formateDateTime(rowdata.update_time);
                return dateChar;
            }
        } ],
        url: 'article/doGetNoticesPageDataByFolderId.infc',
        params:{fType:'3'},//
        detailFun: function(article_id) {
            winOpen=window.open( BAS_URL+"/article/initNoticeDetail.do?id="+article_id,'NoticeDetail');
        }
    });

    $('#allNotice').click(function(){
        if(noticeType!=3){
         //if(noticeType==1){
          //     $('.dataTable').empty();
          //     $('.UI_page').empty();
          //  }
            table.refreshData({fType:'3'});
            noticeType=3;
        }
        $("#allNotice").removeClass();
        $("#allNotice").attr("class", "oper_btn_tijiao");
        $("#classNotice").removeClass();
        $("#classNotice").attr("class", "oper_btn_fanhui");
        $("#teacherNotice").removeClass();
        $("#teacherNotice").attr("class", "oper_btn_fanhui");
    });

    $('#classNotice').click(function(){
        if(noticeType!=1){
            table.refreshData({fType:'1'});
            noticeType=1;
        }
        $("#allNotice").removeClass();
        $("#allNotice").attr("class", "oper_btn_fanhui");
        $("#classNotice").removeClass();
        $("#classNotice").attr("class", "oper_btn_tijiao");
        $("#teacherNotice").removeClass();
        $("#teacherNotice").attr("class", "oper_btn_fanhui");
    });

    $('#teacherNotice').click(function(){
        if(noticeType!=2){
            table.refreshData({fType:'2'});
            noticeType=2;
        }
        $("#allNotice").removeClass();
        $("#allNotice").attr("class", "oper_btn_fanhui");
        $("#classNotice").removeClass();
        $("#classNotice").attr("class", "oper_btn_fanhui");
        $("#teacherNotice").removeClass();
        $("#teacherNotice").attr("class", "oper_btn_tijiao");
    });
});