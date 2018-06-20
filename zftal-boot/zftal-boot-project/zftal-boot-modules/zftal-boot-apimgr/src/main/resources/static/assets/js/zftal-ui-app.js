function home_help(){
	window.open(_path + '/assets/help.doc','_blank');
}

jQuery(function($) {
	
	//计算tabs高度
	var pageHeight = $(window).height() - $('#tabs').outerHeight(true) - $('#footer').outerHeight(true) - $('#navbar').outerHeight(true);
	var boxHeight = $(window).height() - $('#footer').outerHeight(true);
	$('#boxed').height(boxHeight);
	
	// 监听自定义菜单加载完成事件
	$(document).off('menuReady').on('menuReady',function(){
		
		$("#iframe_home").attr("src", _path + '/manager/api/index/list?layout=default&th='+pageHeight);

		if($.isMobile()) {
			//$(".zf-phone-homePage").load(_path + '/zfsdb/api/index/list');
		} else {
			//$('.row').load(_path + '/zfsdb/api/index/list');
		}
		
	});

});