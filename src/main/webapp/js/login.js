/**
 * Created by Administrator on 2015/12/14.
 */
$(function() {
	if (window.top != window.self) {
		window.top.location.href = $.customOpt.url + "login.html";
	}
});

$(function() {
	var jsessionId = '';
	// 表单验证
	$("#loginForm").validationEngine("attach", {
		promptPosition : "topLeft",
		autoHidePrompt : true,
		autoHideDelay : 3000,
		scroll : false
	});

	// 点击刷新验证码
	$('#verifyCode').click(function() {
		$('#verifyCode').attr('src', $.customOpt.url + 'verifyCodeImage?rnd=' + Math.random());
	});

	$('#loginBtn').click(function() {
		var bol = $("#loginForm").validationEngine("validate");
		if (bol) {
			var formData = $("#loginForm").getValue();
			$.cookie('accToken',"",{path: '/' });
			$.ajaxConnSend({
				interfaceUrl : 'user/doValidate.infc',
				params : formData,
				callbackFun: function(data){
					var accToken = data['object'];
					$.cookie('accToken',accToken,{path: '/' });
					window.location.href = $.customOpt.url+'main.html';
				}
			});
		}
	});

	/**
	 * 键盘事件
	 */
	$(document).keyup(function(e) {
		var key = e.which;
		if (key == 13) {
			$('#loginBtn').trigger('click');
		}
	});
});