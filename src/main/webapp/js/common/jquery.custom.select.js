/**
 * Created by Administrator on 2015/12/8.
 */
/**
 * 自定义下拉插件 使用原生的重新封装的select下拉框
 */
(function($) {
	var defaultOption = {
		text : '',
		items : [],
		id : '',
		name : '',
		params : {},
		isRequired : false,
		valueKey : 'code',
		textKey : 'text',
		checkKey : 'isCheck',
		defaultVal : null,
		isLikeQ : false,
		url : null,
		callbackFun: null//ajax调用后置方法
	};

	/**
	 * 构造下拉框
	 * 
	 * @param options
	 */
	$.fn.mysel = function(options) {
		var selObj = null;
		var opts = $.extend({}, defaultOption, options);
		var thisObj = $(this);
		var defalutVal = thisObj.attr('defValue');
		opts['defaultVal'] = defalutVal;
		// ===============构建组件
		selObj = _createWidget(thisObj, opts, opts.items);
		return selObj;
	};

	/**
	 * 刷新下拉框
	 *
	 * @param options
	 */
	$.fn.setSelItems = function(items,opts) {
		var selObj = $(this);
		if(opts = undefined || opts == null){
			opts = {valueKey:'code',textKey:'text'};
		}
		selObj.empty();
		var itemHtml = '<option value="" >--请选择--</option>';
		$.each(items, function(i, item) {
			itemHtml += '<option value="' + item[opts.valueKey] + '" >'
			+ item[opts.textKey] + '</option>';
		});
		selObj.append(itemHtml);
		return selObj;
	};

	/**
	 * 初始化方法(私有)
	 * 
	 * @param jobj
	 * @param text
	 * @param items
	 * @private
	 */
	function _createWidget(jobj, opts, items) {
		jobj.append('<span>' + opts.text + ':</span>');
		var selectBox = $('<div class="select_box"></div>');
		if(opts.id == undefined || opts.id == null || opts.id == '' ){
			opts.id = opts.name;
		}
		var selObj = $('<select name=\"' + opts.name + '\" id="'+ opts.id +'"></select>');
		selectBox.append(selObj);
		if(opts.isRequired){
			selObj.addClass("validate[required]");
		}
		//判断是否ajax加载数据
		var checkVal = null;
		if (opts.items != null && opts.items.length > 0) {
			var itemHtml = '<option value="" >--请选择--</option>';
			$.each(items, function(i, item) {
				itemHtml += '<option value="' + item[opts.valueKey] + '" >'
				+ item[opts.textKey] + '</option>';
			});
			selObj.append(itemHtml);
			_initSelVal(selObj,opts.defaultVal,checkVal);
		} else {
			if (opts.url != null && opts.url != undefined) {
				$.ajaxConnSend(jobj, opts.url, opts.params, function(data) {
					var items = data.object;
					var itemHtml = '<option value="" >--请选择--</option>';
					$.each(items, function(i, item) {
						//var isCheck = item[opts.checkKey]?'selected':'';
						if(item[opts.checkKey]){
							checkVal = item[opts.valueKey];
						}
						itemHtml += '<option value="' + item[opts.valueKey] + '" >'
						+ item[opts.textKey] + '</option>';
					});
					selObj.append(itemHtml);
					_initSelVal(selObj,opts.defaultVal,checkVal);
				},null,false);
			} else {
				$.alert_error('数据获取错误!');
			}
		}
		if(opts.isRequired){
			selectBox.after('<b>*</b>');
		}
		jobj.append(selectBox);
		if (opts.callbackFun != null) {
			opts.callbackFun();
		}
		return selObj;
	}
	function _initSelVal(selObj,defaultVal,checkVal){
		//默认值
		if(defaultVal != undefined && defaultVal != null && defaultVal != ''){
			selObj.val(defaultVal);
		}else{
			if(checkVal != null){
				selObj.val(checkVal);
			}
		}
	}

}(jQuery));