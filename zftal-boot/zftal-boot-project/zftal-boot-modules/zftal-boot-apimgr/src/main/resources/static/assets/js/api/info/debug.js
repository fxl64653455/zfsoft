
jQuery(function($){
	
	/**执行按钮事件*/
	$('#btn_exec').on('click',function(){
		var requestData = getFormJson($('#ajaxForm'));
		$('#input-res').text('执行中...');
		$.post(_path + '/manager/api/info/debug/exec',requestData,function(data){
    		$('#input-res').text(JSON.stringify(data));
    	});
	});
	
})