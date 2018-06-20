
jQuery(function($){
	
	/**变更申请接口列表*/
	var visible = true;
	if(authType == 'api'){visible = false;}
	$('#changeGrid').bootstrapTable({
		 url: _path + '/manager/api/apply/change/list',uniqueId: 'applyChangeId',
		 method : 'post',contentType : 'application/x-www-form-urlencoded',
		 showToggle:false,showRefresh:false,showColumns:false,pageList : [15,50,100],
		 sidePagination: 'server',queryParamsType : 'limit',pageSize: 15,pagination: true,
		 classes		: 'table table-striped table-hover table-condensed table-api text-center',
		 columns: [{
			 title : '序号',formatter : function(value,row,index){return (index + 1);}
		 },{
			 field: 'applyChangeId', title: '变更申请ID',visible:false
		 },{
			 field: 'appName',title: '应用名称',visible:visible
		 },{
			 field: 'apiName',title: '接口名称'
		 },{
			 field: 'apiStatus',title: '接口状态',formatter : function(value,row,index){
				 if(value == '0'){
					 return '<span class="label label-warning">不可用</span>';
				 }else if(value == '1'){
					 return '<span class="label label-success">可用</span>';
				 }else if(value == '-1'){
					 return '<span class="label label-danger">已废弃</span>';
				 }else{
					 return value;
				 }
			 }
		 },{
			 field: 'apiInfo',title: '接口描述',visible:false
		 },{
			 field: 'changeTime', title: '变更申请时间'
		 },{
			 field: 'auditStatus',title: '变更申请状态',formatter : function(value,row,index){
				 if(value == '0'){
					return '<span class="label label-warning">待审核</span>';
				 }else if(value == '1'){
					 return '<span class="label label-success">审核通过</span>';
				 }else if(value == '2'){
					 return '<span class="label label-danger">审核不通过</span>';
				 }else{
					 return value;
				 }
			 }
		 },{
			 title: '变更明细',formatter : function(value,row,index){
				 return '<a href="javascript:void(0)" class="btn-changeDesc" data-id="'+row["applyChangeId"]+'">查看</a>';
			 }
		 },{
	    	title : '操作',formatter : function(value,row,index){
	    		var content = '<a style="padding:3px;color:#FFF;" class="zf-btn btn-primary" href="'+_path +'/manager/api/index/detail/'+row.applyFor+'" target="_blank">接口详情</a>';
	    		return content;
	    	}
	    }],
	    queryParams : function(obj){
	    	return {
	    		limit : obj.limit,offset : obj.offset,
	    		apiName : $('#c_text_search').val(),
	    		applyTo : $('#c_appName').val()
	    	};
	    }
	});
	
	$(document).off("focus","a.btn-changeDesc").on("focus","a.btn-changeDesc",function(event){
		var id = $(this).data("id");
		var row = $('#changeGrid').getRow(id);
		$(this).popover({
			trigger		: 'manual',
			container	: 'body',
			placement	: 'left',
			html		: true,
			content		: function(){
				var c = '变更前：<br />';
				c += '&nbsp;&nbsp;调用服务IP：' + row['srcInvokeIp'] + '<br />';
				c += '&nbsp;&nbsp;调用频率：' + row['srcInvokeFrequency'] + '万次/天<br />';
				c += '变更后：<br />';
				c += '&nbsp;&nbsp;调用服务IP：' + row['targetInvokeIp'] + '<br />';
				c += '&nbsp;&nbsp;调用频率：' + row['targetInvokeFrequency'] + '万次/天<br />';
				c += '审核意见：' + row['auditOpinion'] + '<br />';
				return c;
			}
		});
		$(this).popover('toggle')
	}).off("blur","a.btn-changeDesc").on("blur","a.btn-changeDesc",function(event){
		$(this).popover('destroy');
	});
	
	/**查询按钮事件*/
	$('#c_btn_search').on('click',function(){
		$('#changeGrid').refreshGrid();
	});
	
	/**查询框回车事件*/
	$('#c_text_search').on('keypress',function(e){
		if(e.keyCode == 13){
			$('#changeGrid').refreshGrid();
		}
	});
	
})