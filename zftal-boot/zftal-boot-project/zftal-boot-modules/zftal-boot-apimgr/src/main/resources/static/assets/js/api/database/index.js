jQuery(function($){
	
	$('#tabGrid').loadGrid({
		 url: _path + '/manager/api/database/list',
		 uniqueId: "id",
		 toolbar:  '#btn_db',
		 sortName: 'dbtype',
		 sortOrder: "desc",
		 showToggle:false,
	 	 showRefresh:false,
	 	 showColumns:false,
		 classes:'table table-striped table-hover table-condensed table-api text-center',
		 columns: [
           {checkbox: true }, 
           {field: 'id', title: '数据源ID编号',visible:false}, 
           {field: 'dbTypeDesc', title: '数据源类型',sortable:false,width:''},
           {field: 'cnname', title: '数据源名称',sortable:false,width:'10%'},
           {field: 'name', title: '数据源',sortable:false,width:'10%'},
           {field: 'url',title: '数据源连接地址',sortable:false,align:'left'}, 
           {field: 'id',title: '连接状态',width:'10%',formatter : function(value,row,index){
        	   $.post(_path + "/manager/api/database/testConnection", {
        		   id : value,dbtype : row['dbtype'],url : row['url'],username : row['username'],password : row['password']
        	   },function(data){
		    		if(data){
		    			if(data["status"] == "success"){
		    				$('#statusField-' + data["message"]).removeClass('label-warning');
		    				$('#statusField-' + data["message"]).addClass('label-success');
							$('#statusField-' + data["message"]).text('可用');
						}else if(data["status"] == "fail"){
							$('#statusField-' + data["message"]).removeClass('label-warning');
		    				$('#statusField-' + data["message"]).addClass('label-danger');
							$('#statusField-' + data["message"]).text('不可用');
						}else{
							console.log(data["message"]);
						}
		    		}
		    	});
				 return '<span id="statusField-'+value+'" class="label label-warning">测试中...</span>';
			 }},
           {field: 'desc',title: '数据源描述：该数据源相关简述',sortable:false,align: "left",width:'15%'}
	    ],
	    searchParams:function(){
	    	return {"dbtype" : $("#query-db-type").val()};
	    }
	});
	
	/**查询按钮事件*/
	$('#db_btn_search').on('click',function(){
		$('#tabGrid').refreshGrid({"dbtype" : $("#query-db-type").val()});
	});
	
	var dbconfig = $.extend(true, {},modifyConfig,{
    	max_height : "500px",
//    	onHidden : function(){
//    		$('#tabGrid').refreshGrid({"dbtype" : $("#query-db-type").val()});
//    	},
    	buttons : {
    		test : {
    			label : "测试连接",
    			className : "btn-primary",
    			callback : function() {
    				var $this = this;
    				if($("#ajaxForm").valid()){
    					$.post(_path + "/manager/api/database/test", $("#ajaxForm").serialize() ,function(data){
	    		    		if(data){
	    		    			if(data["status"] == "success"){
	    							$.success(data["message"]);
	    						}else if(data["status"] == "error"){
	    							$.error(data["message"]);
	    						}else{
	    							$.alert(data["message"]);
	    						}
	    		    		}
	    		    	});
    				}
    				return false;
    			}
    		},
    		cancel : {
    			label : "关 闭",
    			className : "btn-default"
    		}
    	}
	});
	
	//绑定增加点击事件
	$("#btn_zj_db").click(function () {
		$.showDialog(_path + '/manager/api/database/to/new-db','增加数据源', dbconfig);
	});

	//修改
	$("#btn_xg_db").click(function () {
		var keys = $('#tabGrid').getKeys();
		if(keys.length != 1){
			$.alert('请先选定一条记录!');
			return;
		}
		$.showDialog(_path + '/manager/api/database/to/edit-db?id=' + keys[0],'编辑数据源', dbconfig);
	});

	$("#btn_sc_db").click(function () {
		var keys = $("#tabGrid").getKeys();
		if(keys.length <1 ){
			$.alert('请至少选定一条记录!');
			return;
		}
		$('#tabGrid').plcz(_path + '/manager/api/database/delete?ids=' + keys.join(","),'删除数据源');
	});
	
	
});