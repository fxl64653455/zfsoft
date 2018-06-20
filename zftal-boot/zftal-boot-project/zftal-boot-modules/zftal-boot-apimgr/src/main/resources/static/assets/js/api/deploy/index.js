jQuery(function($){
	
	$('#nav-tabs a').click(function (e) {
		 e.preventDefault()
		 var keys = $('#tabGrid').getKeys();
		 if(keys.length != 1){
			$.alert('请先选定一条记录!');
			return;
		 }
		 $('#tabGrid-deploy').refreshGrid();
		 $(this).tab('show');
	}).on("shown.bs.tab",function(){
		//$(window).trigger('resize.bootstrap-table');
		
	});

	//接口发布列页面
	$('#tabGrid-deploy').loadGrid({
		 url			: _path + '/manager/api/deploy/list',
		 method    		: 'POST',       // POST
		 uniqueId		: "id",			// 绑定ID
		// toolbar		:  '#but_ancd',
		 sortName		: 'time',
		 sortOrder		: "desc",
		 //striped: true,
		 classes		: 'table table-striped table-hover table-condensed table-api text-center',
		 showToggle		: false,
	 	 showRefresh	: false,
	 	 showColumns	: false,
	 	 clickToSelect	: true,
		 columns		: [
			{checkbox: true },  // bootstrapTable 显示单选checkbox 列
            {field: 'id', title: '接口ID编号',visible:false,width:'70px'},
            {field: 'time', title: '发布时间',sortable:false, visible:true,width:'180px',cellStyle: function(v,r,i){
            	return {
            		css:{
            			'word-break':'break-all'
            		}
            	}
            }},
            {field: 'addr', title: '接口地址',sortable:false,visible:true,width:'180px'}, 
            {field: 'type', title: '接口类型',sortable:false,visible:true,width:'100px'},
            {field: 'method', title: '请求方式',sortable:false,visible:true,width:'100px'},
            {field: 'status', title: '接口状态',sortable:false,visible:true,width:'100px',
            	formatter : function(value , row , index){
            		if(value=='0'){
            			return '<span class="label label-default text-fff">已下线</span>';
            		}else if(value=='1'){
            			return '<span class="label label-success">在 线</span>';
            		}
            	}
            },
            {field: 'ver', title: '接口版本',sortable:false,visible:true,width:'70px'},
            {field: 'desc',title: '接口数据描述',sortable:false, width:'180px',
            formatter : function(value , row , index){
            	
            }},
            {field: 'op',title: '操作',sortable:false, width:'100px',
                formatter : function(value , row , index){
                	var html = [];
                	if(row.status=='0'){
                		html.push('<a href="javascript:void(0)" class="btn zf-btn btn-xs btn-primary btn-deploy" data-id="'+row["id"]+'">上线</a>');
                	}else{
                		html.push('<a href="javascript:void(0)" class="btn zf-btn btn-xs btn-primary btn-undeploy" data-id="'+row["id"]+'">下线</a>');
                	}
                	html.push('&nbsp;<a href="'+ _path +'/manager/api/index/detail/'+row["id"]+'" target="_blank" class="btn zf-btn btn-xs btn-info btn-detail">详情</a>');
                	return html.join("");
                }
            }
		 ],
	    searchParams:function(){
	    	var keys = $('#tabGrid').getKeys();
	    	if(keys.length == 1){
		    	return {
		    		"id" 	: keys[0],
		    		"type" 	: $("#query-deploy-type").val()
		    	};
	    	}
	    	return {};
	    }
	});
	
	/**查询按钮事件*/
	$('#btn_search_deploy').on('click',function(){
		$('#tabGrid-deploy').refreshGrid();
	});
	
	$(document).off("click","a.btn-deploy").on("click","a.btn-deploy",function(event){
		var id = $(this).data("id");
		$.confirm("您确定要上线选择的记录吗？",function(result){
			if(result){
				$.post(_path + '/manager/api/deploy/online', {"id" : id} ,function(data){
		    		if(data){
		    			if(data["status"] == "success"){
							$.success(data["message"],function(){
								$('#tabGrid-deploy').refreshGrid();
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
	}).off("click","a.btn-delete").on("click","a.btn-delete",function(event){
		var id = $(this).data("id");
		$.confirm("您确定要删除选择的记录吗？",function(result){
			if(result){
				$.post(_path + '/manager/api/deploy/delete', {"id" : id} ,function(data){
		    		if(data){
		    			if(data["status"] == "success"){
							$.success(data["message"],function(){
								$('#tabGrid-deploy').refreshGrid();
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
	}).off("click","a.btn-undeploy").on("click","a.btn-undeploy",function(event){
		var id = $(this).data("id");
		$.confirm("您确定要下线选择的记录吗？",function(result){
			if(result){
				$.post(_path + '/manager/api/deploy/offline', {"id" : id} ,function(data){
		    		if(data){
		    			if(data["status"] == "success"){
							$.success(data["message"],function(){
								$('#tabGrid-deploy').refreshGrid();
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
	
});
