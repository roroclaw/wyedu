/**
 * Created by Administrator on 2015/12/8.
 */
/**
 * Created by Administrator on 2015/12/8.
 */
/**
 *  自定义table插件
 */
(function ($) {
    /**
     * 默认参数
     * @type {{
     *      isCheck: boolean,
     *      header: Array,[{code:属性名(对应items的Code),text:中文,width:列宽比例}]
     *      rowdatas: Array[{code:值,code:值,code:值}]
     * }
     * }
     */
    var accToken = $.cookie('accToken');
    var defaultOption = {
        isCheck: true,
        isRadio: false,
        isSeq: false,//是否序号列
        isPage: true,
        isInitRefresh: true,
        header: [],//[{code:属性名(对应items的Code),text:中文,formateFun:函数,isPosBox:false(气泡)}]
        rowdatas: [],// {checked:true//默认选中}
        url: null,
        params: null,//远程url参数
        delFun: null,//删除按钮
        modFun: null,//修改按钮
        modFunByAuthor:null,//验证权限删除按钮
        changeStatusFun:null,//修改信息状态
        detailFun: null,//详情按钮
        operColFun: null,
        operColFun_2: null,
        rowClickFun: null,//行点击事件
        callbackFun: null,//ajax调用后置方法
        operBarKey: '.operBar',//操作栏class
        _posboxLen: 8,//气泡长度限制
        _recordsPerpage: 10,
        idKeyName:'id'
    };

//    var _rowDatas = null;
//    var _pageObj = null;

    /**
     * 构造table
     * @param options
     */
    $.fn.mytable = function (options) {
        var thisObj = $(this);
        var opts = $.extend({}, defaultOption, options);
        var hArr = opts.header;
        var rowdatas = opts.rowdatas;
        var isCheck = opts.isCheck;
        var isRadio = opts.isRadio;
        var isSeq = opts.isSeq;
        var isPage = opts.isPage;
        var url = opts.url;
        var params = opts.params;
        var callbackFun = opts.callbackFun;
        var delFun = opts.delFun;
        var modFun = opts.modFun;
        var modFunByAuthor = opts.modFunByAuthor;
        var changeStatusFun= opts.changeStatusFun;
        var detailFun = opts.detailFun;
        var operColFun = opts.operColFun;
        var operColFun_2 = opts.operColFun_2;
        var isOper = false;
        var idKeyName = opts.idKeyName;
        if (operColFun || operColFun_2 || delFun || modFun || detailFun || modFunByAuthor || changeStatusFun) {
            opts.isOper = isOper = true;
        }
        //=================共有方法定义=================
        var _rowDatas = null;

        thisObj.getChecked = function () {
            var reArr = [];
            var thisObj = $(this);
            var checkedItems = $('.checkBox', thisObj);
            var _rowDatas = thisObj.data() != null ? thisObj.data()._rowDatas : null;
            $.each(checkedItems, function (i, item) {
                if ($(item).hasClass('i_cur')) {
                    var seq = $(item).attr('seq');
                    reArr.push(_rowDatas[seq]);
                }
            });
            //获取当前页 总共页数
            var curPage = $('.curPage', pageObj).text();
            var totalPage = $('.totalPage', pageObj).text();
            for (i = 1; i < Number(totalPage) + 1; i++) {
                if (curPage != i) {
                    var chkArr = thisObj.data() != null ? thisObj.data()['chkData_obj_' + i] : null;
                    if (chkArr != null && chkArr != undefined) {
                        reArr = $.merge(reArr, chkArr);
                    }
                }
            }
            return reArr;
        };

        thisObj._getChecked = function () {
            var reArr = [];
            var thisObj = $(this);
            var checkedItems = $('.checkBox', thisObj);
            var _rowDatas = thisObj.data() != null ? thisObj.data()._rowDatas : null;
            $.each(checkedItems, function (i, item) {
                if ($(item).hasClass('i_cur')) {
                    var seq = $(item).attr('seq');
                    reArr.push(_rowDatas[seq]);
                }
            });
            return reArr;
        };

        thisObj._getCheckedKeyStr = function () {
            var reStr = "";
            var thisObj = $(this);
            var checkedItems = $('.checkBox', thisObj);
            var _rowDatas = thisObj.data() != null ? thisObj.data()._rowDatas : null;
            $.each(checkedItems, function (i, item) {
                if ($(item).hasClass('i_cur')) {
                    var seq = $(item).attr('seq');
                    reStr += "|" + _rowDatas[seq]['id'];//优化关键字key
                }
            });
            return reStr;
        };

        /**
         * 通过行号获取行数据
         * @param rowNum
         * @returns {*}
         */
        thisObj.getDataByRowNum = function (rowNum) {
            var _rowDatas = thisObj.data() != null ? thisObj.data()._rowDatas : null;
            return _rowDatas[rowNum];
        };

        thisObj.refreshData = function (params, type) {
            $.loadingBox.show();
            var thisObj = $(this);
            var rowdatas = opts['rowdatas'];
            var url = opts.url;
            var defparams = thisObj.data() != null ? thisObj.data().params : null;
            var callbackFun = opts.callbackFun;
            var recordsPerpage = opts._recordsPerpage;


            if (params != undefined) {
                params = $.extend(defparams, params);
            } else {
                params = $.extend({}, defparams);
            }

            //组装分页数据
            if ($.trim(params['curPage']) == '' || params['curPage'] == undefined) {
                params['curPage'] = 1;
            } else {
                if (type == undefined) {
                    params['curPage'] = 1;
                }
            }
            thisObj.data('params', params);
            //绘制数据行
            if (rowdatas != null && rowdatas.length > 0) {
                _setRowDatas(opts, thisObj, rowdatas, recordsPerpage);
            } else if (url != null && url != undefined) {
                _ajaxDatas(opts, thisObj, url, params, callbackFun);
            }
        };
        //=================共有方法定义End=================

        //==============绘制table =================
        var tableObj = $('<table class="tableObj" width="100%"></table>');
        thisObj.append(tableObj);
        var html = '';

        //绘制header
        if (hArr.length > 0) {
            html += '<tr class="title_tr">';
            if (isCheck) {
                html += '<td><input type="checkbox" class="all_ChkBtn"/>全选</td>';
            }
            if (isRadio) {
                html += '<td><span>单选</span></td>';
            }
            if (isSeq) {
                html += '<td><span>序号</span></td>';
            }
            $.each(hArr, function (i, item) {
                html += '<td>' + item['text'] + '</td>';
            });

            if (isOper) {
                html += '<td>操作</td>';
            }
            html += '</tr>';
            tableObj.append(html);
        } else {
            $.alert_error('header参数错误!');
        }

        //绘制数据行
        if(opts.isInitRefresh){
            thisObj.refreshData(params);
        }
        //操作框
        var operbarHtml = $(opts['operBarKey']).html();
        $(opts['operBarKey']).remove();
        //绘制分页栏
        if (isPage) {
            //判断表格宽度
            var oWidth = thisObj.innerWidth();
            var pageObj = $('<div class="UI_page clear"></div>');
            var pageBtn = null;
            var pageInfo = null;
            //if (oWidth > 660) {
                pageInfo = $('<a class="col">第<em class="star_rnum">1</em>-<em class="end_rnum">10</em>条记录/共<em class="totalNum">0</em>条</a>' +
                '<a href="###" class="lastBtn">尾页</a> <a href="###" class="col nextBtn">下一页</a> <a href="###" ><em class="curPage">1</em>/<em class="totalPage"></em></a>' +
                '<a href="###" class="col preBtn">上一页</a> <a href="###" class="firstBtn">首页</a>');
            //}
            pageObj.append(operbarHtml);
            pageObj.append(pageInfo);
            thisObj.after(pageObj);

            //===========分页事件绑定 =============
            pageObj.on('click', '.firstBtn', function (e) {//首页
                if (isCheck) {
                    setChks(thisObj, pageObj);
                }
                var curPage = $('.curPage', pageObj).text();
                $('.numInput', pageObj).val(1);
                thisObj.refreshData({curPage: 1}, '_pri');
            });
            pageObj.on('click', '.preBtn', function (e) {//上一页
                if (isCheck) {
                    setChks(thisObj, pageObj);
                }
                var curPage = $('.curPage', pageObj).text();
                if (Number(curPage) > 1) {
                    curPage = Number(curPage) - 1;
                    $('.numInput', pageObj).val(curPage);
                    thisObj.refreshData({curPage: curPage}, '_pri');
                }
            });
            pageObj.on('click', '.nextBtn', function (e) {//下一页
                if (isCheck) {
                    setChks(thisObj, pageObj);
                }
                var curPage = $('.curPage', pageObj).text();
                var totalPage = $('.totalPage', pageObj).text();
                if (Number(curPage) < Number(totalPage)) {
                    curPage = Number(curPage) + 1;
                    $('.numInput', pageObj).val(curPage);
                    thisObj.refreshData({curPage: curPage}, '_pri');
                }
            });
            pageObj.on('click', '.lastBtn', function (e) {//尾页
                if (isCheck) {
                    setChks(thisObj, pageObj);
                }
                var lastPage = $('.totalPage', pageObj).text();
                $('.numInput', pageObj).val(Number(lastPage));
                thisObj.refreshData({curPage: lastPage}, '_pri');
            });
            //===========分页事件绑定 End =============
        }

        //==============绘制table End=================


        //===========checkbox事件绑定 =============
        thisObj.on('click', '.dataRow', function (e) {//数据行点击事件
            if (opts.rowClickFun) {
                if ($(this).attr('rownum')) {
                    $('.dataRow').removeClass('cur');
                    $(this).addClass('cur');
                }
                opts.rowClickFun($(this).attr('rownum'));
            }
        });
        thisObj.on('click', '.checkBox', function (e) {
            var obj = $(this);
            if (isCheck) {
                obj.toggleClass('i_cur');
                refreshAllChkBtn();
                //var hasLength = 0;
                //var maxLength = $('.checkBox').length;
                //$.each($('.checkBox'), function (i, item) {
                //    if ($(item).hasClass('i_cur')) {
                //        hasLength += 1;
                //    }
                //});
                //if(hasLength==maxLength){
                //    $(".all_ChkBtn").attr('checked','checked');
                //}else{
                //    $(".all_ChkBtn").removeAttr('checked');
                //}
            } else if (isRadio) {
                var hasCheck = obj.hasClass('i_cur');
                $('.checkBox').removeClass('i_cur');
                if (!hasCheck) {
                    obj.addClass('i_cur');
                }
            }

        });

        thisObj.on('click', '.all_ChkBtn', function (e) {
            var objs = $(this).parents('table').find('.checkBox');
            var isCheck = $(this).is(':checked');
            $.each(objs, function (i, item) {
                if (!$(item).hasClass('all_ChkBtn')) {
                    if (isCheck) {
                        $(item).addClass('i_cur');
                        $(item).attr('checked', 'checked');
                    } else {
                        $(item).removeClass('i_cur');
                        $(item).removeAttr('checked');
                    }
                }
            });
            e.stopPropagation();
        });
        //===========checkbox事件绑定 End =============


        //========气泡事件=======
        thisObj.on('hover', '.tab_pos', function (e) {
            $(this).find(".pos_box").show();
        });
        thisObj.on('mouseleave', '.tab_pos', function (e) {
            $(this).find(".pos_box").hide();
        });
        //========气泡事件End=======

        //========列输入框事件Start=====
        thisObj.on('change', '.colInput', function (e) {
            var rownum = $(this).attr('rownum');
            var name = $(this).attr('name');
            var _rowDatas = thisObj.data() != null ? thisObj.data()._rowDatas : null;
            _rowDatas[rownum][name] = $(this).val();
        });
        //========列输入框事件End=======


        return thisObj;
    };


    /**
     * 设置数据
     */
    function _setRowDatas(opts, thisObj, rowdatas, curPage, totalRecords, totalPage, recordsPerpage) {
        var hArr = opts.header;
        var isCheck = opts.isCheck;
        var isRadio = opts.isRadio;
        var isSeq = opts.isSeq;
        var delFun = opts.delFun;
        var modFun = opts.modFun;
        var modFunByAuthor = opts.modFunByAuthor;
        var changeStatusFun = opts.changeStatusFun;
        var detailFun = opts.detailFun;
        var operColFun = opts.operColFun;
        var operColFun_2 = opts.operColFun_2;
        var isOper = opts.isOper;
        var idKeyName=opts.idKeyName;

        var tableObj = $('.tableObj', thisObj);
        //移除原有的rowData
        $('.dataRow', tableObj).remove();
        var _rowDatas = [];
        var _rowDatas_str = "";
        if (rowdatas != null && rowdatas.length > 0) {
            for (i = 0; i < recordsPerpage; i++) {
                var rowdata = rowdatas[i];
                if (rowdata != null && rowdata != undefined) {
                    _rowDatas[i] = rowdata;
                    _rowDatas_str += '|' + rowdata[idKeyName];//需要优化成配置key
                    if (i % 2 != 0) {
                        html = '<tr class="tar_color dataRow" rownum="' + i + '">';
                    } else {
                        html = '<tr class="dataRow" rownum="' + i + '">';
                    }

                    if (isCheck) {
                        var checked = rowdata['checked'];
                        if (checked) {
                            html += '<td><input type="checkbox" class="checkBox i_cur" seq="' + i + '"/></td>';
                        } else {
                            html += '<td><input type="checkbox" class="checkBox"  seq="' + i + '"/></td>';
                        }
                    }
                    if (isRadio) {
                        html += '<td><input type="checkbox"  class="checkBox" seq="' + i + '"/></td>';
                    }
                    if (isSeq) {
                        html += '<td>' + (i + 1) + '</td>';
                    }

                    $.each(hArr, function (k, item) {
                        var code = item['code'];
                        var formateFun = item['formateFun'];
                        var isPosBox = item['isPosBox'];
                        var width = item['width'];
                        var txt = String(rowdata[code]) ? rowdata[code] : "";
                        txt = (txt == '0') ? 0 : txt ? txt : "";//判断是null还是0
                        //格式化数据
                        if (formateFun != null && formateFun != undefined) {
                            if (typeof formateFun == 'function') {
                                txt = formateFun(txt, i, code, rowdata);
                            } else if (typeof formateFun == 'string') {
                                if (formateFun == 'input') {
                                    txt = $.mytable.cInput(txt, i, code, rowdata);
                                } else if (formateFun == 'input_date') {
                                    txt = $.mytable.cDataInput(txt, i, code, rowdata);
                                }
                            }

                        }
                        var tdWith = '';
                        if(width){
                            tdWith = 'width="'+width+'%"';
                        }

                        html += '<td '+tdWith+'>';
                        //气泡设置
                        if (isPosBox) {
                            var subtxt = txt.substring(0, opts._posboxLen) + '...';
                            html += '<div class="tab_pos">' + subtxt + '<div class="pos_box">' + txt + '</div></div>';
                        } else {
                            html += txt;
                        }
                        html += '</td>';
                    });

                    //绘制操作框
                    var operObj = null;
                    if (isOper) {
                        operObj = $('<td class="operCol" style="text-align: center;"></td>');
                        if (detailFun) {
                            var detailObj = $('<i href="###" class="iconfont icon-sousuoleimufill" title="详情" rownum="' + i + '" did="' + rowdata[idKeyName] + '"></i>');
                            detailObj.on('click', function () {
                                detailFun($(this).attr('did'), $(this).attr('rownum'));
                            });
                            operObj.append(detailObj);
                        }
                        if (modFun) {
                            //var modObj = $('<a href="###"  class="a_editor" title="编辑"><img src="../images/operation/editor.png"/></a>');
                            var modObj = $('<i href="###" class="iconfont icon-icon07" title="编辑" rownum="' + i + '" did="' + rowdata[idKeyName] + '"></i>');
                            modObj.on('click', function () {
                                modFun($(this).attr('did'), $(this).attr('rownum'));
                            });
                            operObj.append(modObj);
                        }
                        if (modFunByAuthor) { ///////需要验证作者
                           // console.debug(rowdata["status"]);
                          //  console.debug(accToken);
                           // var status=rowdata["status"];
                            if( rowdata["locked"]==accToken && rowdata["status"]!=1){
                                var modObj = $('<i href="###" class="iconfont icon-icon07" title="编辑" rownum="' + i + '" did="' + rowdata[idKeyName] + '"></i>');
                                modObj.on('click', function () {
                                   // alert("sss");

                                    //console.debug(rowdata["status"]);

                                    modFunByAuthor($(this).attr('did'), $(this).attr('rownum'));
                                });
                                operObj.append(modObj);
                            }
                        }
                        if (changeStatusFun) { ///////修改信息状态
                            var statusObj;
                            if( rowdata["status"]=="1"){
                                statusObj = $('<i href="###" class="iconfont icon-shibai" title="停用" param="0" rownum="' + i + '" did="' + rowdata[idKeyName] + '"></i>');
                            }else if( rowdata["status"]=="3"){
                                statusObj = $('<i href="###" class="iconfont icon-shibai" title="停用" param="0" rownum="' + i + '" did="' + rowdata[idKeyName] + '"></i>');
                            }else if( rowdata["status"]=="2"){
                                statusObj = $('<i href="###" class="iconfont icon-zhengque" title="审核" param="3" rownum="' + i + '" did="' + rowdata[idKeyName] + '"></i>');
                            }else{
                                statusObj = $('<i href="###" class="iconfont icon-zhengque" title="启用" param="1" rownum="' + i + '" did="' + rowdata[idKeyName] + '"></i>');
                            }

                            statusObj.on('click', function () {
                                changeStatusFun($(this).attr('did'), $(this).attr('param'));
                                });
                                operObj.append(statusObj);
                        }
                        if (delFun) {
                            //var delObj = $('<a href="###" rownum="' + i + '" did="' + rowdata['id'] + '" class="a_del" title="删除"><img src="../images/operation/del.png"/></a>');
                            var delObj = $('<i href="###" class="iconfont icon-shanchu" title="删除" rownum="' + i + '" did="' + rowdata[idKeyName] + '" ></i>');
                            delObj.on('click', function () {
                                delFun($(this).attr('did'), $(this).attr('rownum'));
                            });
                            operObj.append(delObj);
                        }
                        if (operColFun) {
                            operObj.append(operColFun(i,rowdata));
                        }
                        if (operColFun_2) {
                            operObj.append(operColFun_2(i,rowdata));
                        }
                    }
                    html += '</tr>';
                    var trObj = $(html);
                    if (operObj != null) {
                        trObj.append(operObj);
                    }
                    tableObj.append(trObj);
                } else {//填充空行
                    var trObj = null;
                    if (i % 2 != 0) {
                        trObj = $('<tr class="tar_color dataRow">');
                    } else {
                        trObj = $('<tr class="dataRow">');
                    }

                    if (isCheck) {
                        trObj.append('<td>&nbsp;</td>');
                    }
                    if (isRadio) {
                        trObj.append('<td>&nbsp;</td>');
                    }
                    if (isSeq) {
                        trObj.append('<td>&nbsp;</td>');
                    }
                    if (isOper) {
                        trObj.append('<td>&nbsp;</td>');
                    }
                    $.each(hArr, function (i, item) {
                        trObj.append('<td>&nbsp;</td>');
                    });
                    tableObj.append(trObj);
                }
            }
            thisObj.data('_rowDatas', _rowDatas);
            thisObj.data('_rowDatas_str', _rowDatas_str);
        } else {
            var colNum = hArr.length;
            if (isCheck) {
                colNum += 1;
            }
            if (isRadio) {
                colNum += 1;
            }
            if (isOper) {
                colNum += 1;
            }
            tableObj.append('<tr class="dataRow"><td colspan="' + colNum + '">无对应数据!</td></tr>');
        }
        //更新分页信息
        var pageObj = thisObj.next();
        $('.totalPage', pageObj).html(totalPage);
        $('.totalNum', pageObj).text(totalRecords);
        $('.curPage', pageObj).text(curPage);
        $('.star_rnum', pageObj).text((curPage - 1) * recordsPerpage + 1);
        $('.end_rnum', pageObj).text(curPage * recordsPerpage);
        $.loadingBox.close();
    }

    /**
     * 获取数据
     */
    function _ajaxDatas(opts, thisObj, url, params) {
        if (url == null || url == undefined) {
            alert('url设置错误!');
        }
        $.ajaxConnSend(null, url, params, function (data) {
            if (data.status == '1') {
                var rowdatas = data.object.data;
                var curPage = data.object.curPage;
                var totalRecords = data.object.totalRecords;
                var totalPage = data.object.totalPage;
                var recordsPerpage = data.object.recordsPerpage;
                //刷新rowDatas数据行
                _setRowDatas(opts, thisObj, rowdatas, curPage, totalRecords, totalPage, recordsPerpage);
                if (opts.callbackFun != null) {
                    opts.callbackFun(rowdatas);
              //  }else{
             //       opts.callbackFun(rowdatas);
                }
                initChks(thisObj, curPage);
            } else {
                alert('数据获取失败!');
            }
        }, null);
    }
    /**
     * 放置选中值
     */
    function setChks(thisObj, pageObj) {
        var curPage = $('.curPage', pageObj).text();
        var chk_str = thisObj._getCheckedKeyStr();
        thisObj.data('chkData_' + curPage, chk_str);
        var chkedDatas = thisObj._getChecked();
        thisObj.data('chkData_obj_' + curPage, chkedDatas);
    }
    /**
     * 设置选中值
     */
    function initChks(thisObj, curPage) {
        //获取原来选中记录
        var chkData = thisObj.data() != null ? thisObj.data()['chkData_' + curPage] : null;
        if (chkData != null) {
            var objs = $('table', thisObj).find('.checkBox');
            var _rowDatas = thisObj.data() != null ? thisObj.data()._rowDatas : null;
            $.each(objs, function (i, item) {
                if (!$(item).hasClass('all_ChkBtn')) {
                    var seq = $(item).attr('seq');
                    if (chkData.indexOf('|' + _rowDatas[seq]['id']) > -1) {
                        $(item).addClass('i_cur');
                        $(item).attr('checked', 'checked');
                    } else {
                        $(item).removeClass('i_cur');
                        $(item).removeAttr('checked');
                    }

                }
            });
        }
        //判定全选按钮
        refreshAllChkBtn();
    }
    /**
     * 刷新全选check
     */
    function refreshAllChkBtn() {
        var hasLength = 0;
        var maxLength = $('.checkBox').length;
        $.each($('.checkBox'), function (i, item) {
            if ($(item).hasClass('i_cur')) {
                hasLength += 1;
            }
        });
        if (hasLength == maxLength) {
            $(".all_ChkBtn").attr('checked', 'checked');
        } else {
            $(".all_ChkBtn").removeAttr('checked');
        }
    }

}(jQuery));

