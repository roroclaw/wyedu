/**
 * Created by dengxianzhi on 2017/1/26.
 */
$(function(){

    //下拉框设置
    $('.statusSel').mysel({
        url : 'common/doGetArticleStatus.infc',
        text : '状&nbsp;&nbsp;态',
        name : 'status',
        isRequired : false
    });

    $.ajaxConnSend(this, 'folder/doGetFoldersListByRoleIdForAddArti.infc', {}, function (data) {
        var Obj = data['object'];
        var temp_level=1;
        var selectHtml='<span>所属栏目：</span><div class="select_box"><select  class="form-control" name="folder_id" id="folderSel" class="validate[required]">';
        selectHtml=selectHtml+'<option value="">--请选择--</option>';
        for(x in Obj ){

            //x表示是下标，来指定变量，指定的变量可以是数组元素，也可以是对象的属性。
            selectHtml=selectHtml+'<option value="'+Obj[x].folder_id+'">';
            for(var i=1;i<Obj[x].folder_level;i++){
                //selectHtml=selectHtml+'│&nbsp;&nbsp;';
            }
            //selectHtml=selectHtml+'├─'+Obj[x].folder_name+'</option>';
            selectHtml=selectHtml+Obj[x].folder_name+'</option>';
            temp_level=Obj[x].folder_level;
        }
        selectHtml=selectHtml+'</select></div>';
        $("#folder_select_add").empty();
        $("#folder_select_add").append(selectHtml);
    });

    ///////////响应下拉框选择
    $('.folder_select_add').change(function(){
        var folderId= $("#folderSel").find("option:selected").val();
        if(folderId=='2131455968989343029'){  /////学生公告
            $('.classSel').empty();
            $('.classSel').mysel({
                url : 'article/getClassInfoItemsByPowerForArtiAdd.infc',
                text : '对应班级',
                name : 'keyWord',
                isRequired : false,
                valueKey : 'code',
                textKey : 'text'
            });
            $('.classSel').show();
        }else{
            $('.classSel').empty();
            $('.classSel').hide();
        }
    });

    var table = $('.dataTable').mytable({
        isCheck: true,
        _recordsPerpage:1,
        idKeyName:'article_id',
        header: [{
            code: 'title',
            text: '文章标题'
        },{
            code: 'author_name',
            text: '作者'
        },{
            code: 'update_time',
            text: '更新日期',
            formateFun:function(txt, i, code, rowdata){
                var dateChar="";
                dateChar=  $.formateDateTime(rowdata.update_time);
                return dateChar;
            }
        },{
            code: 'statusText',
            text: '状态'
        }],
        url: 'article/doGetArticlesPageDataByAuthorForEdit.infc',
        params:{folder_id:'123'},//产生空返回
        callbackFun:function(rowdatas){
          //  $("#addBtn").attr("param",rowdatas.father_id);
        },

        delFun: function(id) {
                 $.alert_confirm('确定删除此记录?', function() {
                 $.loadingBox.show();
                 $.ajaxConnSend(this, 'article/doDeleteArticleInfo.infc', {
                     article_id: id
                 }, function(data) {
                 if (data.status == '1' && data.object) {
                 $.alert_success('删除成功');
                 table.refreshData();
                 } else {
                 $.alert_error('删除失败');
                 }
                 },function(){
                 $.loadingBox.close();
                 })
                 });
         },
        modFunByAuthor:function(id,rownum,status) {
            //console.debug(status);
           // var status =  rowdata['status'];

            if(status != 1){
                winOpen=window.open( BAS_URL+"article/initEditArticle.do?id="+id,'articleModForm');
            }
            /*
            $.ajaxConnSend(this, 'article/doGetArticleInfosByID.infc', {article_id:id}, function (data) {
                var ArticleObj = data['object'];
                $('#editArticleForm').setValue(ArticleObj);
                $.ajaxConnSend(this, 'folder/doGetFoldersList.infc', {}, function (data) {
                    var fatherObj = data['object'];
                    var temp_level=1;
                    var selectHtml='<select  class="form-control" name="folder_id">';
                    selectHtml=selectHtml+'<option value="'+ArticleObj.folder_id+'"selected>'+ArticleObj.folder_name+'</option>';
                    for(x in fatherObj){
                        //x表示是下标，来指定变量，指定的变量可以是数组元素，也可以是对象的属性。
                        selectHtml=selectHtml+'<option value="'+fatherObj[x].folder_id+'">';
                        for(var i=0;i<fatherObj[x].folder_level;i++){
                            selectHtml=selectHtml+'│&nbsp;&nbsp;';
                        }
                        selectHtml=selectHtml+'├─'+fatherObj[x].folder_name+'</option>';
                        temp_level=fatherObj[x].folder_level;
                    }
                    selectHtml=selectHtml+'</select>';
                    $("#article_id").val(id);
                    $("#folder_select_edit").empty();
                    $("#folder_select_edit").append(selectHtml);

                });
                changeDivShow("_2",2);
            });
            */
        },
        operColFun:function(i,rowdata,id){
            var id =  rowdata['article_id'];
            var status =  rowdata['status'];
              if(status == 1){
                    var operObj = $('<i href="###" class="iconfont icon-shibai" title="禁用" rownum="' + i + '" did="' + id + '"></i>');
                    operObj.on('click', function () {
                        changArtiStatus(id,"2");
                    });
                }
            return operObj;
        }
    });

    /**
     * 搜索
     */
    $('#queryBtn').click(function(){
        var params = $('#queryForm').getValue();
        table.refreshData(params);
    });

    /**
     * 修改
     */
    $("#doEditBtn").click(function(){
        var bol = $("#editArticleForm").validationEngine("validate");
        if (bol) {
            var params = $('#editArticleForm').getValue();
            $.loadingBox.show();
            $.ajaxConnSend(this, 'article/doUpdateArticleInfo.infc', params, function(data) {
                if (data.status == '1' && data.object) {
                    $.alert_success('修改成功!');
                    changeDivShow("_1",2);
                    // var table = $('.dataTable').mytable();

                    var curPage= parseInt($('.curPage').text());
                    table.refreshData({curPage:curPage},"refresh");
                } else {
                    $.alert_error('修改失败');
                }
            }, function() {
                $.loadingBox.close();
            });
        }
    });

    $('.oper_btn_fanhui').click(function(){
        changeDivShow("_1",2);
    });

    function changArtiStatus(id,status){
        $.loadingBox.show();
        $.ajaxConnSend(this, 'article/doUpdateArticleInfoStatus.infc', {
            article_id:id,status:status
        }, function(data) {
            if (data.status == '1' && data.object) {
                $.alert_success('操作成功');
                table.refreshData();
            } else {
                $.alert_error('操作失败');
            }
        },function(){
            $.loadingBox.close();
        })
    }


});


