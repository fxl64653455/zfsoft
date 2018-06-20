jQuery(function($){
	
	if(authType == 'app'){
		$('#tabGrid').loadGrid({
			 url		: _path + '/manager/api/app/applyTableData',
			 uniqueId	: 'appId',
			 classes	:'table table-striped table-hover table-condensed table-api',
			 showToggle	: false,
			 showRefresh: false,
			 showColumns: false,
			 pageSize : 5,
			 pageList 	: [5,10,15],
			 columns	: [
				 {checkbox: true,formatter : function(value,row,index){
					 console.log(row['applyStatus']);
					 if(row['applyStatus'] == '1'){
						 this.checkbox = false;
					 }else{
						 this.checkbox = true;
					 }
				 }},
				 {field: 'appId', title: '应用ID',visible:false},
				 {field: 'appName', title: '应用名称'},
				 {field: 'appDesc', title: '应用描述'},
				 {field: 'applyStatus', title : '申请状态',formatter : function(value,row,index){
						 if(value == '0'){
							return '<span class="label label-warning">待审核</span>';
						 }else if(value == '1'){
							 return '<span class="label label-success">审核通过</span>';
						 }else if(value == '2'){
							 return '<span class="label label-danger">审核不通过</span>';
						 }else{
							 return '<span class="label label-info">未申请</span>';
						 }
					 
				 	}
				 }
			],
		    searchParams:function(){
		    	return {
		    		appName : $('#text_search').val(),
		    		deployId : deployId
		    	};
		    }
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
	}
	
	/**确定事件*/
	$('#btn_success').on('click',function(){
		if(authType == 'app'){
			var keys = $('#tabGrid').getKeys();
			var appIds = '';
			for(var i = 0;i < keys.length;i ++){
				var applyStatus = $('#tabGrid').getRow(keys[i]).applyStatus;
				if(applyStatus == null || applyStatus == '2'){
					if(appIds == ''){appIds += $('#tabGrid').getRow(keys[i]).appId;}
					else {appIds += ',' + $('#tabGrid').getRow(keys[i]).appId;}
				}
			}
			$('#input-appIds').val(appIds);
		}
		var frequency = $("input[name='frequency']:checked").val();
		$('#input-frequency').val(frequency);
		var form = new FormData(document.getElementById("applyForm"));
		$.ajax({
		    url:submitPath,
		    type:"post",
		    data:form,
		    processData:false,
		    contentType:false,
		    success:function(data){
			    if(data.status == 'success'){
			    	$('#btn_cancel').click();
			    	$.success(data.message);
			    }else if(data.status == 'error'){
			    	$.error(data.message);
			    }
		    }
		});
	})
	
});