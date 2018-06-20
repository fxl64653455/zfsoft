jQuery(function($){
	
	$('#tabGrid').loadGrid({
		 url: _path + '/manager/api/app/getTableData',uniqueId: 'appId',
		 showToggle:false,showRefresh:false,showColumns:false,pageList : [5,10,15],escape : true,
		 classes		: 'table table-striped table-hover table-condensed table-api text-center',
		 columns: [{
			 title : '序号',formatter : function(value,row,index){return (index + 1);}
		 },{
			 field: 'appId', title: '应用ID',visible:false
		 },{
			 field: 'appName', title: '应用名称'
		 },{
			 field: 'appOwner',title: '应用所属人',visible:false
		 },{
			 field: 'appDesc', title: '应用描述'
		 },{
			 field: 'appKey', title: '应用Key'
		 },{
			 field: 'appSecret', title: '应用appSecret', formatter : function(value,row,index){
				 return '<a href="javascript:void(0)" class="zf-btn btn-primary btn-secret" data-value="'+value+'" style="padding:3px;color:#FFF;">查看密钥</a>'
			 }
		 },{
	    	title : '操作',formatter : function(value,row,index){
	    		var html = [];
            		html.push('<a href="javascript:void(0)" class="zf-btn btn-primary btn-edit" data-id="'+row["appId"]+'" style="padding:3px;color:#FFF;">编辑</a>');
            		html.push('&nbsp;<a href="javascript:void(0)" class="zf-btn btn-primary btn-del" data-id="'+row["appId"]+'" style="padding:3px;color:#FFF;">删除</a>');
            	return html.join();
	    	}
	    }],
	    searchParams:function(){
//	   		map["appName"] = jQuery('#text_search').val();
	    	return {
	    		appName : $('#text_search').val()
	    	};
	    }
	});
	
	/**增加按钮事件*/
	$('#btn_add').on('click',function(){
		$.showDialog(_path + '/manager/api/app/form','新增应用', $.extend({},modifyConfig,{
	    	customScrollbar : false,btnlock : true
		}));
	});
	
	/**查询按钮事件*/
	$('#btn_search').on('click',function(){
		$('#tabGrid').refreshGrid();
	});

	/**查询框回车事件*/
	$('#text_search').on('keypress',function(e){
		if(e.keyCode == 13){
			$('#tabGrid').refreshGrid();
		}
	});
	
	/**查看密钥*/
	$(document).off("click","a.btn-secret").on("click","a.btn-secret",function(event){
		var v = $(this).data("value");
		$.message('密钥','<textarea class="col-xs-12" rows="3">'+v+'</textarea>',function(){},{customScrollbar : true});
	})
	/**编辑应用*/
	.off("click","a.btn-edit").on("click","a.btn-edit",function(event){
		var appId = $(this).data("id");
		var row = $('#tabGrid').getRow(appId);
		$.showDialog(_path + '/manager/api/app/form','编辑应用', $.extend({},modifyConfig,{
	    	customScrollbar : false,btnlock : true,
	    	data	 : row//JSON.stringify(row).replace(/\"/g,"'")
		}));
	})
	/**删除应用*/
	.off("click","a.btn-del").on("click","a.btn-del",function(event){
		var appId = $(this).data("id");
		$.confirm('您确定要删除当前应用吗， 此操作将删除所有相关数据,是否继续?',function(res){
			if(res){
				$.post(_path + '/manager/api/app/delete/' + appId ,function(data){
		    		if(data){
		    			if(data["status"] == "success"){
							$.success(data["message"],function(){
								$('#tabGrid').refreshGrid();
							});
						}else if(data["status"] == "error"){
							$.error(data["message"]);
						}else{
							$.alert(data["message"]);
						}
		    		}
		    	});
			}
		});
	});
	
})