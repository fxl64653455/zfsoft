jQuery(function($){
	/*
	<result property="id" column="ID" />
		<result property="bizId" column="BIZ_ID" />
		<result property="bizName" column="BIZ_NAME" />
		<result property="appKey" column="APP_KEY" />
		<result property="addr" column="ADDR" />
		<result property="uri" column="URI"/>
		<result property="deviceName" column="DEVICE_NAME"/>
		<result property="osName" column="OS_NAME" />
		<result property="osVer" column="OS_VER" />
		<result property="osMfr" column="OS_MFR" />
		<result property="browserName" column="BROWSER_NAME" />
		<result property="browserVer" column="BROWSER_VER" />
		<result property="browserType" column="BROWSER_TYPE" />
		<result property="browserKernel" column="BROWSER_KERNEL" />
		<result property="userAgent" column="USER_AGENT" />
		<result property="time" column="TIME24" />
*/		
	$('#tabGrid').loadGrid({
		 url		: _path + '/manager/api/metric/list',
		 uniqueId	: 'id',
		 classes	: 'table table-striped table-hover table-condensed table-api text-center',
		 columns: [{
			 title : '序号',formatter : function(value,row,index){return (index + 1);}
		 },{
			 field: 'bizId', title: '应用ID',visible:false
		 },{
			 field: 'bizName', title: '业务名称',
		 },{
			 field: 'appKey', title: '应用Key',visible:false
		 },{
			 field: 'appName', title: '应用名称'
		 },{
			 field: 'addr',title: '客户端IP地址',
		 },{
			 field: 'uri', title: '接口路径'
		 },{
			 field: 'deviceName', title: '设备名称'
		 },{
			 field: 'osName', title: '操作系统名称'
		 },{
			 field: 'osVer', title: '操作系统版本'
		 },{
			 field: 'osMfr', title: '操作系统制造商'
		 },{
			 field: 'browserName', title: '浏览器名称'
		 },{
			 field: 'browserVer', title: '浏览器版本'
		 },{
			 field: 'browserType', title: '浏览器类型'
		 },{
			 field: 'browserKernel', title: '浏览器内核 '
		 },{
			 field: 'time', title: '请求发送时间 '
		 }],
	    searchParams:function(){
	    	return {
	    		"appKey" 		: $("#query-app-type").val(),
				"bizId" 		: $("#query-biz-type").val(),
				"deviceName" 	: $("#query-device-type").val(),
				"osName" 		: $("#query-os-type").val(),
				"browserName" 	: $("#query-browser-type").val(),
				"beginTime" 	: $("#beginTime").val(),
				"endTime" 		: $("#endTime").val()
	    	};
	    }
	});
	
	/**查询按钮事件*/
	$('#btn_search').on('click',function(){
		$('#tabGrid').refreshGrid();;
	});

	/**查询框回车事件*/
	$('#text_search').on('keypress',function(e){
		if(e.keyCode == 13){
			$('#tabGrid').refreshGrid();;
		}
	});
	
})