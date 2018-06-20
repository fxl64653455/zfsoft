/**审核操作*/
function audit(applyId,appId,deployId){
	$.showDialog(_path + '/manager/api/audit/new-audit/'+applyId+'/' + appId + '/' + deployId,'审核', $.extend({},addConfig,{
    	customScrollbar : false,btnlock : true
	}));
}

/**查看密钥*/
function showSecret(apiKey,apiSecret){
	$.message('密钥','<lable class="col-xs-12">apiKey:</lable><input class="col-xs-12" type="text" value="'+apiKey+'" />' +
			'<lable class="col-xs-12">apiSecret:</lable><textarea class="col-xs-12" rows="3">'+apiSecret+'</textarea><div class="col-xs-12">&nbsp;</div>',function(){},{customScrollbar : true});
}

jQuery(function($){
	
	if(authType == 'app'){
		/**应用选择框*/
		$.ajax({
			type : 'GET',url : _path + '/manager/api/app/comboListByOwner',
			success : function(rows) {
				if(rows.length > 0){
					for(var i = 0;i < rows.length;i ++){
						$('#s_appName').append('<option value="'+rows[i].appId+'">'+rows[i].appName+'</option>');
						$('#c_appName').append('<option value="'+rows[i].appId+'">'+rows[i].appName+'</option>');
					}
				}
				
				$('#s_appName').chosen({
					allow_single_deselect: true,no_results_text: "没有匹配结果",search_contains: false,disable_search:false
				}).change(function(e,s){
					$('#applyGrid').refreshGrid();
				});
				
				$('#c_appName').chosen({
					allow_single_deselect: true,no_results_text: "没有匹配结果",search_contains: false,disable_search:false
				}).change(function(e,s){
					$('#changeGrid').refreshGrid();
				});
			}
		},'json');
	}
	
	/**申请接口列表*/
	var visible = true;
	if(authType == 'api'){visible = false;}
	$('#applyGrid').loadGrid({
		 url: _path + '/manager/api/audit/list',uniqueId: 'applyId',
		 showToggle:false,showRefresh:false,showColumns:false,pageList : [5,10,15],
		 classes		: 'table table-striped table-hover table-condensed table-api text-center',
		 columns: [{
			 title : '序号',formatter : function(value,row,index){return (index + 1);}
		 },{
			 field: 'applyId', title: '应用申请ID',visible:false
		 },{
			 field: 'appName',title: '应用名称',visible:visible
		 },{
			 field: 'apiName',title: '接口名称'
		 },{
			 field: 'apiStatus',title: '接口状态',visible:false,formatter : function(value,row,index){
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
			 field: 'applyTime', title: '申请时间'
		 },{
			 field: 'applyStatus',title: '申请状态',formatter : function(value,row,index){
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
			 field: 'auditOpinion',title: '明细',formatter : function(value,row,index){
				 return '<a href="javascript:void(0)" class="btn-auditDesc" data-id="'+row["applyId"]+'">查看</a>';
			 }
		 },{
			 field: 'applyFor',title: '接口ID',visible:false
		 },{
			 field: 'applyTo', title: '应用ID',visible:false
		 },{
			 field: 'applyUser',title: '申请人',visible:false
		 },{
	    	title : '操作',formatter : function(value,row,index){
	    		var res = '';
	    		if(row.applyStatus == '0'){
	    			res += '<a style="padding:3px;color:#FFF;" class="zf-btn btn-primary" href="javascript:audit(\''+row.applyId+'\',\''+row.applyTo+'\',\''+row.applyFor+'\')">审核</a>&nbsp;&nbsp;';
	    		}
	    		res += '<a style="padding:3px;color:#FFF;" class="zf-btn btn-primary" href="'+_path + '/manager/api/apply/applyFile/'+row.applyId+'">申请材料</a>&nbsp;&nbsp;';
	    		res += '<a style="padding:3px;color:#FFF;" class="zf-btn btn-primary" href="'+_path + '/manager/api/index/detail/'+row.applyFor+'" target="_blank">接口详情</a>';
	    		if(authType == 'api' && row.applyStatus == '1'){
	    			res += '&nbsp;&nbsp;<a style="padding:3px;color:#FFF;" class="zf-btn btn-primary" href="javascript:showSecret(\''+row.apiKey+'\',\''+row.apiSecret+'\')">查看密钥</a>';
	    		}
	    		return res;
	    	}
	    }],
	    searchParams:function(){
	    	return {
	    		apiName : $('#text_search').val(),
	    		applyTo : $('#s_appName').val()
	    	};
	    }
	});
	
	$(document).off("focus","a.btn-auditDesc").on("focus","a.btn-auditDesc",function(event){
		var id = $(this).data("id");
		var row = $('#applyGrid').getRow(id);
		$(this).popover({
			trigger		: 'manual',
			container	: 'body',
			placement	: 'left',
			html		: true,
			content		: function(){
				var c = '调用服务IP:' + row['invokeIp'] + '<br />';
				c += '调用频率:' + row['invokeFrequency'] + '万次/天<br />';
				c += '审核意见:' + row['auditOpinion'] + '<br />';
				return c;
			}
		});
		$(this).popover('toggle')
	}).off("blur","a.btn-auditDesc").on("blur","a.btn-auditDesc",function(event){
		$(this).popover('destroy');
	});
	
	/**查询按钮事件*/
	$('#btn_search').on('click',function(){
		$('#applyGrid').refreshGrid();
	});

	/**查询框回车事件*/
	$('#text_search').on('keypress',function(e){
		if(e.keyCode == 13){
			$('#applyGrid').refreshGrid();
		}
	});
	
})