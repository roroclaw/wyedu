(function($) {
	//这里做的是兼容处理
	if(typeof  BAS_URL == "undefined"){
		var pathName = window.document.location.pathname;
		BAS_URL = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
		BAS_URL = BAS_URL != ''?BAS_URL : '/';
	}else{
		BAS_URL = BAS_URL
	}

	jQuery.extend({
		handleError : function(s, xhr, status, e) {
			// If a local callback was specified, fire it
			if (s.error) {
				s.error.call(s.context || s, xhr, status, e);
			}

			// Fire the global callback
			if (s.global) {
				(s.context ? jQuery(s.context) : jQuery.event).trigger(
						"ajaxError", [ xhr, s, e ]);
			}
		}
	});

	/**
	 * Created by dxzadmin on 2014/9/13.
	 */
	$.extend({
				customOpt : {
					url : BAS_URL,// 本地测试
					btnLoadShow : function(obj) {
						// if (obj != null)
						// $(obj).button('loading');
					},
					btnLoadClose : function(obj) {
						// if (obj != null)
						// $(obj).button('reset');
					}
				},

				/**
				 * @param target
				 *            事件对象
				 * @param interfaceUrl
				 *            访问请求地址(不带ip,端口)
				 * @param params
				 *            请求参数{code:val,code:val}
				 * @param callbackFun
				 *            请求成功回调函数
				 * @param endFun
				 *            请求结束执行函数
				 */
				ajaxConnSend : function(target, interfaceUrl, params,
						callbackFun, endFun, async) {
					// 处理重复提交
					// $target = null;
					// if (target != null) {
					// 	$target = $(target);
					// 	var oldDoTag = $target.attr('TAG_DOING');
					// 	if (oldDoTag == true) {
					// 		alert('重复提交!');
					// 		return;
					// 	}
					// 	$target.attr('TAG_DOING', true);
					// }
					// 固定ip,port设置
					if (params == null) {
						params = {};
					}
					if (async == null || async == undefined) {
						async = true;
					}
					params.interfaceUrl = interfaceUrl;
					var accToken = $.cookie('accToken');
					//console.debug(accToken);
					if(accToken == null || accToken == undefined){
						accToken = $("#basic_accToken").attr("val");
					}
					params['accToken'] = accToken;
					// 这里可以加入提示框
					$.customOpt.btnLoadShow(target);
					/* 实际接口调用 */
					$.ajax({
								type : "POST",
								url : $.customOpt.url + '/'+interfaceUrl,
								data : params,
								async : async,
								dataType:'json',
								contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
								success : function(data) {
									$.customOpt.btnLoadClose(target);
									// 统一处理接口
									var status = data.status;
									if (status == 1) {
										if (callbackFun != null
												&& callbackFun != undefined) {
											callbackFun(data);
										}
									} else if (status == 0) {
										var describe = data.describe;
										$.alert_error(describe);
									} else if (status == 2) {
										if (window.parent != null) {
											window.parent.location.href = $.customOpt.url+'login.html';
										} else {
											window.location.href = $.customOpt.url+'login.html';
										}
									}else{//都不存在就返回原始数据
										if (callbackFun != null
											&& callbackFun != undefined) {
											callbackFun(data);
										}
									}
									if (typeof endFun == 'function') {
										endFun(data);
									}
									$(target).attr('TAG_DOING', false);
								}
							});
				}

			});

})(jQuery);