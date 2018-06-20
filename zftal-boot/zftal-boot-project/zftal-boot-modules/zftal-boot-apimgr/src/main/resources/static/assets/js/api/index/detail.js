jQuery(function($){

	$("#btn-apply").click(function(){
		
		$.showDialog(_path + '/manager/api/apply/new-apply/' + $(this).data("id"),'申请接口',$.extend({},addConfig,{
			customScrollbar : false//,btnlock : true
		}));
		
	});
	
});