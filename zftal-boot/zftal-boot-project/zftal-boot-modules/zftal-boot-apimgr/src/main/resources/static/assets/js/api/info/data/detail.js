jQuery(function($){
	
	// 关闭过滤模式，保留所有标签
	KindEditor.options.filterMode = false;
	var ke_container = $("#ke_control").find(".ke-container");
	KindEditor.remove('#input-detail');
	var editor = KindEditor.create('#input-detail', $.extend(true,{},viewOption,{
		afterCreate: function () {
		
			this.sync();
			this.readonly(true);
			this.focus();
			
	    },
	    afterBlur: function () {

	    	this.sync();
	       
	    } 
	}));
	
	window.setTimeout(function(){
		editor.readonly(true);
	}, 1000);
	 

});