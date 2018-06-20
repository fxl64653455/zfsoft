jQuery(function($){
	
	$('#tabGrid').loadGrid({
		 url			: _path + '/manager/api/info/data/list',
		 uniqueId		: "id",
		 toolbar		: '#but_ancd',
		 sortName		: 'API_TIME',
		 sortOrder		: "desc",
		 //striped		: true,
		 classes		:'table table-striped table-hover table-condensed table-api text-center',
		 showToggle		: false,
	 	 showRefresh	: false,
	 	 showColumns	: false,
		 columns		: [
			{checkbox: true }, 
            {field: 'id', title: '接口ID编号',visible:false},
            {field: 'name', title: '接口名称',sortable:false,visible:true,width:'25%', align: "left"}, 
            {field: 'type', title: '接口类型',sortable:false,visible:true,width:'10%'},
            {field: 'deploynum', title: '接口发布次数',sortable:false,visible:true,width:'10%',
                formatter : function(value , row , index){
                	if(value>0){
                		return '<a href="javascript:void(0)" class="label label-primary label-deployed bg-primary text-fff" data-index="'+index+'" data-id="'+row["id"]+'">已发布: '+value+'次</a>';//'已发布:'+value+'次';
                	}else{
                		return '<span class="label label-default bg bg-777 text-fff">未 发 布</span>';
                	}
                }
            },
            {field: 'invokeEnable', title: '连续调用配置',sortable:false,visible:true,width:'10%',
            	formatter : function(value , row , index){
            		if(value > 0){
            			return '<span class="label label-primary label-deployed bg-primary text-fff">已配置</span>';
            		}else{
            			return '<span class="label label-default bg bg-777 text-fff">未配置</span>';
            		}
            	}
            },
            {field: 'info',title: '接口数据描述',sortable:false, width:'35%', align: "left"},
            {field: 'time', title: '创建时间',sortable:false, visible:true,width:'10%'}
		 ],
	     searchParams:function(){
	    	return {
	    		"name" : $("#query-api-name").val()
	    	};
	     },
	     //数据加载成功
	     onLoadSuccess:	function(data){
	    	 $("a.label-deployed").click(function(e){
	    		$('#tabGrid').bootstrapTable("uncheckAll");
	 	    	$('#tabGrid').bootstrapTable("check",$(this).data("index"));
    			$("#nav-tabs a:last").click();
    			e.stopPropagation();
    		});
	     }	
	});
	
	/**查询按钮事件*/
	$('#btn_search').on('click',function(){
		$('#tabGrid').refreshGrid();
	});
	
	//绑定增加点击事件
	$("#btn_zj").click(function () {
		$.showDialog(_path + '/manager/api/info/data/to/new-data-api','新增数据接口信息', $.extend({},modifyConfig,{
			max_height : "400px",
			//height: '600px',
		 	//是否使用模拟滚动条
	    	customScrollbar : true,btnlock : true
		}));
	});
	
	//修改
	$("#btn_xg").click(function () {
		var keys = $('#tabGrid').getKeys();
		if(keys.length != 1){
			$.alert('请先选定一条记录!');
			return;
		}
		//业务判断
		$.showDialog(_path + '/manager/api/info/data/to/edit-data-api?id='+ keys[0] ,'修改数据接口信息', $.extend({},modifyConfig,{
			width : '1000px',
			max_height : "400px",
			//是否使用模拟滚动条
			customScrollbar : true,btnlock : true
		}));
	});
	
	//删除
	$("#btn_sc").click(function () {
		var keys = $("#tabGrid").getKeys();
		if(keys.length == 0){
			$.alert('请至少选定一条记录!');
			return;
		}
		$('#tabGrid').plcz(_path + '/manager/api/info/data/delete?ids=' + keys.join(","),'删除数据接口信息');
	});
	
	//详情
	$("#btn_detail").click(function () {
		var keys = $('#tabGrid').getKeys();
		if(keys.length != 1){
			$.alert('请先选定一条记录!');
			return;
		}
		//业务判断
		$.showDialog(_path + '/manager/api/info/data/to/detail/'+ keys[0] ,'接口详情', $.extend({},viewConfig,{
			width : '1000px',
			max_height : "400px",
			//是否使用模拟滚动条
			customScrollbar : true,btnlock : true
		}));
	});
	
	//调式
	$("#btn_debug").click(function () {
		var keys = $('#tabGrid').getKeys();
		if(keys.length != 1){
			$.alert('请先选定一条记录!');
			return;
		}
		var row = $('#tabGrid').getRow(keys[0]);
		$.showDialog(_path + '/manager/api/info/debug/index?apiId='+ keys[0],row['name'], $.extend({},viewConfig,{
			width : '1000px',max_height : "400px",customScrollbar : true,btnlock : true
		}));
	});
	
	//配置
	$("#btn_config").click(function () {
		var keys = $('#tabGrid').getKeys();
		if(keys.length != 1){
			$.alert('请先选定一条记录!');
			return;
		}
		var row = $('#tabGrid').getRow(keys[0]);
		$.showDialog(_path + '/manager/api/info/config/index?apiId='+ keys[0],'配置'+row['name'], $.extend({},modifyConfig,{
			width : '1000px',max_height : "400px",customScrollbar : true,btnlock : true
		}));
	});
	
	//发布
	$("#btn_fb").click(function () {
		var keys = $('#tabGrid').getKeys();
		if(keys.length != 1){
			$.alert('请先选定一条记录!');
			return;
		}
		//业务判断
		$.showDialog(_path + '/manager/api/deploy/to/new-deploy?id='+ keys[0],'发布接口', $.extend({},modifyConfig,{
			width : '1000px',
			max_height : "400px",
			//是否使用模拟滚动条
			customScrollbar : true//,btnlock : true
		}));
	});
});
