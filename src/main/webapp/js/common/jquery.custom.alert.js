/**
 * Created by Administrator on 2015/12/10.
 */
/**
 * 封装弹出方法
 */
(function ($) {
    jQuery.extend({
        //信息确认框
        alert_confirm: function (content, callbackFun) {
            var showboxObj = $('<div class="message_bg"></div>');
            var html = '<div class="message_box">';
            html += '<div class="message_title">信息确认</div>';
            html += '<div class="message_text">' + content + '</div>';
            html += '<div class="message_submit">';
            html += '<a class="left alert_confirmBtn"  href="###">确定</a>';
            html += '<a class="right alert_cancelBtn"  href="###">取消</a>';
            html += '</div></div>';
            showboxObj.append(html);
            $('body').append(showboxObj);
            //事件绑定
            $('.alert_confirmBtn', showboxObj).on('click', function () {
                if (callbackFun != null && callbackFun != undefined) {
                    callbackFun();
                }
                showboxObj.remove();
            });
            $('.alert_cancelBtn', showboxObj).on('click', function () {
                showboxObj.remove();
            });

        },
        //信息框
        alert: function (title,content) {
            if(title == null || title == undefined){
                title = '信息';
            }
            if(content == null || content == undefined){
                content = '';
            }
            var dialogBoxObj =  $('<div class="message_bg"></div>');
            var html = '<div class="message_box">';
            html += '<div class="message_title">'+title+'</div>';
            html += '<div class="message_text">'+content+'</div>';
            html += '<div class="message_submit"><a class="submit_ok alert_ok">确定</a></div>';
            html += '</div>';
            dialogBoxObj.append(html);
            $('.alert_ok', dialogBoxObj).on('click', function () {
                dialogBoxObj.remove();
            });
            $('body').append(dialogBoxObj);
        },
        //警告框
        alert_warn: function (content) {
            var dialogBoxObj =  $('<div class="auto_mess message_bg"></div>');
            var html = '<div class="message_toast warn"><i class="iconfont icon-jinggao"></i>'+content+'</div>';
            dialogBoxObj.append(html);
            $('.message_toast', dialogBoxObj).on('click', function () {
                dialogBoxObj.remove();
            });
            $('body').append(dialogBoxObj);
            setTimeout($._closeShowbox,1000);
        },
        //错误框
        alert_error: function (content) {
            var dialogBoxObj =  $('<div class="auto_mess message_bg"></div>');
            var html = '<div class="message_toast error"><i class="iconfont icon-shibai"></i>'+content+'</div>';
            dialogBoxObj.append(html);
            $('.message_toast', dialogBoxObj).on('click', function () {
                dialogBoxObj.remove();
            });
            $('body').append(dialogBoxObj);
            setTimeout($._closeShowbox,1000);
        },
        //成功框
        alert_success: function (content) {
            if(content == null || content == undefined){
                content = '恭喜您，数据操作成功！';
            }
            var dialogBoxObj =  $('<div class="auto_mess message_bg"></div>');
            var html = ' <div class="message_toast suc"><i class="iconfont icon-chenggong"></i>'+content+'</div>';
            dialogBoxObj.append(html);
            $(dialogBoxObj).on('click', function () {
                dialogBoxObj.remove();
            });
            $('body').append(dialogBoxObj);
            setTimeout($._closeShowbox,1500);
        },
        //弹出表单
        showPopupForm: function (formKey,confirmFun,cancelFun) {
            var showBoxId = 'showBox_'+formKey.substr(1,formKey.length);
            var showBox = $('#'+showBoxId);
            if(showBox.length > 0){
                showBox.show();
                return showBox;
            }else {
                var mesbgObj = $('<div id="'+showBoxId+'"  class="auto_mess_showBox message_bg"></div>');
                var dialogBoxObj = $('<div class="message_box"></div>');
                //mesbgObj.append(dialogBoxObj);
                var formObj = $(formKey);
                var btnhtml = '<div class="message_submit"><a class="left confirmBtn">确定</a><a class="right cancelBtn">取消</a></div>';
                formObj.show();
                formObj.append(btnhtml);
                dialogBoxObj.append(formObj);
                mesbgObj.append(dialogBoxObj);
                $('body').append(mesbgObj);
                if (typeof confirmFun == 'function') {
                    $('.confirmBtn', mesbgObj).click(function () {
                        confirmFun();
                    });
                }

                if (typeof cancelFun == 'function') {
                    $('.cancelBtn', mesbgObj).click(function () {
                        cancelFun();
                    });
                } else {
                    $('.cancelBtn', mesbgObj).click(function () {
                        mesbgObj.hide();
                    });
                }
                mesbgObj.close = function(){
                    $(this).hide();
                };
                return mesbgObj;
            }
        },
        //loading
        loadingBox: {
            show: function () {
                var boxs = $('.loadShow');
                if (boxs.length == 0) {
                    var showboxObj = $('<div class="message_bg auto_mess loadbg"></div>');
                    var html = '<div class="message_toast load loadShow"><i class="iconfont icon-dengdai"></i>' + '数据正在加载中，请稍后……</div>';
                    showboxObj.append(html);
                    $('body').append(showboxObj);
                }
            },
            close: function () {
                var mbg = $('.loadbg');
                //var boxs = $('.loadShow');
                //boxs.remove();
                mbg.remove();
            }
        },

        _closeShowbox: function () {
            $('.auto_mess').remove();
        },

        closeShowFormbox: function () {
            $('.auto_mess').remove();
        }
    });
}(jQuery));

