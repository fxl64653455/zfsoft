;(function($) {
	
	/*设置全局Ajax处理*/
	$.ajaxSetup({
		//设置全局过滤参数为true,防止重复请求
		abortOnRetry: true,
		//不启用缓存
		cache		: false,
		contentType	: "application/x-www-form-urlencoded;charset=utf-8",
		statusCode	: {
			//HTTP Status 400（错误请求） 
	        400: function () {
	        	//如果已经异常提醒，不再提示
	        	if($("#statusModal").size() > 0){return;}
	        	/*//提示信息
	        	$.alert($.i18n.zftal["statusCode"]["400"],function(){
	        	},{modalName:"statusModal"});*/
	        },
	        //HTTP Status 401（未授权） 
	        401: function () {
	        	//如果已经异常提醒，不再提示
	        	if($("#statusModal").size() > 0){return;}
	        	//提示信息
	        	$.alert($.i18n.zftal["statusCode"]["401"],function(){
	        		window.close();
	            },{modalName:"statusModal"});
	        },
	        //HTTP Status 404（未找到）
	        404: function () {
				if(console && console.error){
					console.error($.i18n.zftal["statusCode"]["400"]);
				}
	        },
	        //HTTP Status 408（请求超时） 
	        408: function () {
	        	//如果已经异常提醒，不再提示
	        	if($("#statusModal").size() > 0){return;}
	        	//提示信息
	        	$.alert($.i18n.zftal["statusCode"]["408"],function(){
	        	},{modalName:"statusModal"});
	        },
	        //HTTP Status 500（服务器内部错误） 
	        500: function () {
	        	//如果已经异常提醒，不再提示
	        	if($("#statusModal").size() > 0){return;}
	        	//提示信息
	        	$.alert($.i18n.zftal["statusCode"]["500"],function(){
	        	},{modalName:"statusModal"});
	        },
	        //HTTP Status 502（错误网关） 
	        502: function () {
	        	//如果已经异常提醒，不再提示
	        	if($("#statusModal").size() > 0){return;}
	        	//提示信息
	        	$.alert($.i18n.zftal["statusCode"]["502"],function(){
	        	},{modalName:"statusModal"});
	        },
	        //HTTP Status 503（服务不可用）
	        503: function () {
	        	//如果已经异常提醒，不再提示
	        	if($("#statusModal").size() > 0){return;}
	        	//提示信息
	        	$.alert($.i18n.zftal["statusCode"]["503"],function(){
	        	},{modalName:"statusModal"});
	        },
	        //HTTP Status 504（网关超时） 
	        504: function () {
	        	//如果已经异常提醒，不再提示
	        	if($("#statusModal").size() > 0){return;}
	        	//提示信息
	        	$.alert($.i18n.zftal["statusCode"]["504"],function(){
	        	},{modalName:"statusModal"});
	        },
	        /*--------------自定义响应状态码-----------------*/
	        //HTTP Status 901（HTTP 会话过期）    -> 会话过期或者注销。
	        901: function () {
	       		//如果已经异常提醒，不再提示
	        	if($("#statusModal").size() > 0){return;}
	        	//提示信息
	        	$.alert($.i18n.zftal["statusCode"]["901"],function(){
	        		document.location.href = _systemPath + "/xtgl/login_logout.html?language=" + _localeKey + "&login_type=" + $("#login_type").val();
	            },{modalName:"statusModal"});
	        },
	        //HTTP Status 902（未授权） ->请求要求身份验证。 对于需要登录的网页，服务器可能返回此响应。
	        902: function () {
	        	//如果已经异常提醒，不再提示
	        	if($("#statusModal").size() > 0){return;}
	        	//提示信息
	        	$.alert($.i18n.zftal["statusCode"]["902"],function(){
	        		window.close();
	            },{modalName:"statusModal"});
	        },
	        //HTTP Status 903（HTTP 浏览器会话变更）    -> 当前浏览器同一会话被其他用户登录，导致session变化。
	        903: function () {
	        	//如果已经异常提醒，不再提示
	        	if($("#statusModal").size() > 0){return;}
	        	//提示信息
	        	$.alert($.i18n.zftal["statusCode"]["903"],function(){
	        		window.close();
	            },{modalName:"statusModal"});
	        },
	        //HTTP Status 904（HTTP 恶意刷新）    -> 相同的两个请求频率超过限制阀值时的状态提醒。
	        904: function () {
	        	//如果已经异常提醒，不再提示
	        	if($("#statusModal").size() > 0){return;}
	        	//提示信息
	        	$.alert($.i18n.zftal["statusCode"]["904"],function(){
	        		
	            },{modalName:"statusModal"});
	        },
	        //HTTP Status 905（HTTP 不一致）    -> 会话用户与指定参数值一致性校验结果不一致，服务器可能返回此响应。
	        905: function () {
	        	//如果已经异常提醒，不再提示
	        	if($("#statusModal").size() > 0){return;}
	        	//提示信息
	        	$.alert($.i18n.zftal["statusCode"]["905"],function(){
	        		
	            },{modalName:"statusModal"});
	        },
	        //HTTP Status 906（HTTP 非法IP访问）    -> 请求客户端IP地址不在允许的IP白名单内，服务器可能返回此响应。
	        906: function () {
	        	//如果已经异常提醒，不再提示
	        	if($("#statusModal").size() > 0){return;}
	        	//提示信息
	        	$.alert($.i18n.zftal["statusCode"]["906"],function(){
	        		
	            },{modalName:"statusModal"});
	        },
	        //HTTP Status 907（HTTP 非法MAC访问）    -> 请求客户端MAC地址不在允许的MAC白名单内，服务器可能返回此响应。
	        907: function () {
	        	currentRequests  = {};
	        	//如果已经异常提醒，不再提示
	        	if($("#statusModal").size() > 0){return;}
	        	//提示信息
	        	$.alert($.i18n.zftal["statusCode"]["907"],function(){
	        		
	            },{modalName:"statusModal"});
	        },
	        //HTTP Status 908（HTTP 危险来源）    -> 请求来源不明，服务器为了安全会对象范围来源进行逻辑判断，如果不符合要求则服务器可能返回此响应。
	        908: function () {
	        	//如果已经异常提醒，不再提示
	        	if($("#statusModal").size() > 0){return;}
	        	//提示信息
	        	$.alert($.i18n.zftal["statusCode"]["908"],function(){
	        		
	            },{modalName:"statusModal"});
	        },
	        //HTTP Status 909（HTTP Action未定义）    -> 处理请求的Action对象未定义，则服务器可能返回此响应。
	        909: function () {
	        	//如果已经异常提醒，不再提示
	        	if($("#statusModal").size() > 0){return;}
	        	//提示信息
	        	$.alert($.i18n.zftal["statusCode"]["909"],function(){
	        		
	            },{modalName:"statusModal"});
	        },
	        //HTTP Status 910（HTTP 方法未定义）    -> 请求的后台方法未定义，则服务器可能返回此响应。
	        910: function () {
	        	//如果已经异常提醒，不再提示
	        	if($("#statusModal").size() > 0){return;}
	        	//提示信息
	        	$.alert($.i18n.zftal["statusCode"]["910"],function(){
	        		
	            },{modalName:"statusModal"});
	        },
	        //HTTP Status 911（HTTP 运行异常）    -> 应用程序运行期间发生错误，则服务器可能返回此响应。
	        911: function () {
	        	//如果已经异常提醒，不再提示
	        	if($("#statusModal").size() > 0){return;}
	        	//提示信息
	        	$.alert($.i18n.zftal["statusCode"]["911"],function(){
	        		
	            },{modalName:"statusModal"});
	        }
	    }
	});
	
})(jQuery);