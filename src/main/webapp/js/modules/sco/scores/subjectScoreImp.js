/**
 * Created by dxz on 2017/6/17.
 */
$(function(){
    //上传按钮
    $("#importBtn").click(function () {
        createUploader(importCallBackFun);
    });

    //日志查询分页查询
    //table
    var table = $('.dataTable').mytable({
        isSeq:true,
        isCheck: false,
        idKeyName:'id',
        header: [{
            code: 'srcFile',
            text: '文件名'
        }, {
            code: 'creatorText',
            text: '操作人'
        }, {
            code: 'createTime',
            text: '上传时间',
            formateFun: $.formateDateTime
        }, {
            code: 'comment',
            text: '日志信息',
            width:45,
            formateFun:function(str){
                if(str.length > 60){
                    str = str.substr(0,60)+'。。。。。。。。';
                }
                return str;
            }
        }],
        operColFun:function(i,rowdata){
            var id =  rowdata['id'];
            var status =  rowdata['status'];
            var operObj = $('<i href="###" class="iconfont icon-search" title="详情" rownum="' + i + '" did="' + id + '"></i>');
            operObj.on('click', function () {
                showLogDetail(rowdata['comment']);
            });
            return operObj;
        },
        url: 'subjectScores/doGetImpLogInfoPageData.infc'
    });

    /**
     * 查看日志详情
     * @param comment
     */
    function showLogDetail(comment){
        $('#logDetail').html(comment);
        $('#logShowBox').show();
    }


    /**
     * 导入回调
     */
    function importCallBackFun(file, ret, hds){
        var data = ret['object'];
        $.alert('导入结果',data);
        table.refreshData();
    }

    /**
     * 模板下载
     */
    $('#fileDownLoad').click(function(){
        window.open(BAS_URL+'/template/subjectScoresImp.xlsx');
    });

});
