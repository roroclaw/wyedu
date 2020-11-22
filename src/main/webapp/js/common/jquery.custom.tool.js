/*!  
 * 工具包
 * @version 0.0.0.0  
 */

(function ($) {
    jQuery.extend({
        // 对Date的扩展，将 Date 转化为指定格式的String
        // 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
        // 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
        // 例子：
        // (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
        // (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
        dateFormate: function (date, format) {
            if (!date) return '';
            if (!format) format = "yyyy-MM-dd HH:mm:ss";
            switch (typeof date) {
                case "string":
                    date = new Date(date.replace(/-/, "/").replace("-", "/"));
                    break;
                case "number":
                    date = new Date(date);
                    break;
            }
            if (!date instanceof Date) return;
            var dict = {
                "yyyy": date.getFullYear(),
                "M": date.getMonth() + 1,
                "d": date.getDate(),
                "H": date.getHours(),
                "m": date.getMinutes(),
                "s": date.getSeconds(),
                "MM": ("" + (date.getMonth() + 101)).substr(1),
                "dd": ("" + (date.getDate() + 100)).substr(1),
                "HH": ("" + (date.getHours() + 100)).substr(1),
                "mm": ("" + (date.getMinutes() + 100)).substr(1),
                "ss": ("" + (date.getSeconds() + 100)).substr(1)
            };
            return format.replace(/(yyyy|MM?|dd?|HH?|ss?|mm?)/g, function () {
                return dict[arguments[0]];
            });
        },

        formate2Day: function (str) {
            var fmt = $.dateFormate(str, 'yyyy-MM-dd');
            return fmt;
        },

        formate2Year: function (str) {
            var fmt = $.dateFormate(str, 'yyyy');
            return fmt;
        },
        formateTime: function (str) {
            var fmtime = $.dateFormate(str, 'HH:mm');
            return fmtime;
        },

        formateDateTime: function (str) {
            var fmtime = $.dateFormate(str, 'yyyy-MM-dd HH:mm:ss');
            return fmtime;
        },

        /**
         * 表格单元格学年格式化控件
         */
        cSchoolYearInput: function (str) {
            var schYearText=str+"---"+(parseInt(str)+1);
            return schYearText;
        },

        genrateOptionsByItems: function (jkey,url,allowEmpty) {
            $.ajaxConnSend(null,url, {}, function (data) {
                var objSel = $(jkey);
                var defaultVal = objSel.attr('dfval');
                var items = data['object'];
                var itemsHtml = '';

                if(allowEmpty){
                    itemsHtml += '<option value="">--请选择--</option>';
                }

                for(var i =0 ; i < items.length;i++){
                    var item = items[i];
                    itemsHtml += '<option value="'+item['code']+'">'+item['text']+'</option>';
                }
                objSel.append(itemsHtml);
                objSel.val(defaultVal);
            });
        },

        /**
         * 获取参数
         */
        splitParam: function (url) {
            var paramIndex = url.indexOf('?');
            if (paramIndex == -1) {
                $.alert_error('数据错误，请重试');
                return;
            }
            var paramArr = [];
            var paramStr = url.substring(paramIndex + 1);
            var params = paramStr.split('&');
            for (var i = 0; i < params.length; i++) {
                var kv = params[i].split('=');
                paramArr[kv[0]] = kv[1];
            }
            return paramArr;
        },

        /**
         * 获取url参数
         * @param name 参数名
         * @returns {string}
         */
        getRequestValue: function (name) {
            var str = window.location.search;
            if (str.indexOf(name) !== -1) {
                var pos_start = str.indexOf(name) + name.length + 1;
                var pos_end = str.indexOf("&", pos_start);
                if (pos_end === -1) {
                    return str.substring(pos_start);
                }
                else {
                    return str.substring(pos_start, pos_end);
                }
            }
            else {

            }
        },
        mytable: {
            /**
             * 表格单元格input控件
             */
            cInput: function (text, i, name) {
                var inputHtml = '<input type="text" name="' + name + '" class="colInput" rowNum="' + i + '"></input>';
                return inputHtml;
            },
            /**
             * 表格单元格input控件
             */
            cDataInput: function (text, i, name) {
                var dataFormate = $.formate2Day(text);
                var inputHtml = '<input type="text" placeholder="' + dataFormate + '" name="' + name + '" class="colInput datePiker" rowNum="' + i + '"></input>';
                return inputHtml;
            },
            /**
             * 表格单元格学年格式化控件
             */
            cSchoolYearInput: function (code,rowdata) {
                var schYearText=rowdata[code]+"-"+(parseInt(rowdata[code])+1);
                return schYearText;
            }
        },
        /**
         * post跳转
         * @param url
         * @param args 参数对象
         */
        postHref : function (url, args)
        {
            var form = $("<form method='post' style='display: none'></form>");
            form.attr({"action": url});
            for (arg in args) {
                var input = $("<input type='hidden'>");
                input.attr({"name": arg});
                input.val(args[arg]);
                form.append(input);
            }
            $(document.body).append(form);
            form.submit();
        }
});
}
(jQuery)
)
;
