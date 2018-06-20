<div class="panel">
	<div class="panel-heading">
		<div class="searchbox">
			<div class="input-group custom-search-form">
				<input id="text_search" type="text" class="form-control" placeholder="请输入应用名称...">
				<span class="input-group-btn">
	            	<button id="btn_search" class="text-muted" type="button"><i class="demo-pli-magnifi-glass"></i></button>
	        	</span>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<table id="tabGrid"></table>
	</div>
</div>
[#include "/head/zftal-ui-bsTable.ftl" /]
<script type="text/javascript">
	$('#tabGrid').loadGrid({
		 url: _path + '/manager/api/app/applyTableData',uniqueId: 'appId',classes:'table table-condensed',
		 showToggle:false,showRefresh:false,showColumns:false,pageList : [5,10,15],
		 columns: [{
		 	checkbox: true
		 },{
			 field: 'appId', title: '应用ID',visible:false
		 },{
			 field: 'appName', title: '应用名称'
		 },{
			 field: 'appDesc', title: '应用描述'
		 },{
	    	 field: 'applyStatus', title : '申请状态',formatter : function(value,row,index){
	    		if(value == '0'){
					 return '<font color="orange">待审核</font>';
				 }else if(value == '1'){
					 return '<font color="green">审核通过</font>';
				 }else if(value == '2'){
					 return '<font color="red">审核不通过</font>';
				 }else{
					 return '未申请';
				 }
	    	}
	    }],
	    searchParams:function(){
	    	return {
	    		appName : $('#text_search').val(),
	    		deployId : '${deployId}'
	    	};
	    }
	});
	
	/**查询按钮事件*/
	$('#btn_search').on('click',function(){
		$('#tabGrid').bootstrapTable('refresh');
	});

	/**查询框回车事件*/
	$('#text_search').on('keypress',function(e){
		if(e.keyCode == 13){
			$('#tabGrid').bootstrapTable('refresh');
		}
	});
	
	/**确定事件*/
	$('#btn_success').on('click',function(){
		var keys = $('#tabGrid').getKeys();
		var appIds = '';
		for(var i = 0;i < keys.length;i ++){
			if($('#tabGrid').getRow(keys[i]).applyStatus == null){
				if(appIds == ''){appIds += $('#tabGrid').getRow(keys[i]).appId;}
				else {appIds += ',' + $('#tabGrid').getRow(keys[i]).appId;}
			}
		}
		$.ajax({
			type : 'POST',url : _path + '/manager/api/apply/save',
			data : {
				appIds : appIds,deployId : '${deployId}'
			},
			success : function(obj) {
				$('#btn_cancel').click();
				$.success('操作完成',function(){},{});
			}
		},'json');
	});
</script>