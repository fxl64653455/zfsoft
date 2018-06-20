jQuery(function($){
  	
	$('#ajaxForm').validateForm({
       //提交前的回调函数
		beforeValidated:function(){
    	   var status = false;
    	   if($("#ajaxForm").valid()){
    		   $.ajax({
    			   type: "POST",
    			   url: _path + "/manager/api/database/test",
    			   async: false,
    			   data: $("#ajaxForm").serialize(),
    			   success: function(data){
    				   if(data){
    		    			if(data["status"] == "success"){
    		    				status = true;
    						}else if(data["status"] == "error"){
    							status = false;
    							$.error(data["message"]);
    						}else{
    							status = false;
    							$.alert(data["message"]);
    						}
    		    		}
    			   }
        	   });
    	   }
           return status;
       }
	});
	
	
	//循环数据库类型，动态构建路径验证规则
	$("#db-type option").each(function(){
		
	});
	
	$("#db-type").change(function(){
		$("#db-url").attr("placeholder", "请输入数据库连接地址,格式如：" + $(this).getSelected().attr("db-url"));
    });
	
});
 