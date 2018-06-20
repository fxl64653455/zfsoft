$(function(){
	var grid = {
		uniqueId: "yhm",
	 	showToggle:false,
	 	showRefresh:false,
	 	showColumns:false,
	 	height:500,
	 	pageSize:10,
	 	searchParams:function(){
	 		var map = $("#fpyh").getSearchMap();
			map["jsdm"] = $("#jsdm").val();
			return map;
	 	}
	};
	var wfpGrid = {url:  'getWfpyhList',
		columns: [
            {checkbox: true }, 
            {field: 'yhm', title: '用户名'}, 
            {field: 'xm', title: '姓 名'}, 
//            {field: 'sjhm',title: '联系电话'},
//            {field: 'jgmc',title: '部门'},
            {field: 'yhzt',title: '启用状态',align:'center',formatter:function(value,row,index){
            	var ret;
            	if(value == '1'){
	           		 ret = '<span class="text-success">正常</span>';	
	           	}else if(value == '2'){
	           		 ret = '<span class="text-warning">锁定</span>';
	           	}else {
	           		 ret = '<span class="text-danger">停用</span>';
	           	}
	           	return ret;
            }},
            {field: 'yhm', title: '<button class="btn btn-success btn-xs" onclick="fpyh();">批量分配</button>',align:'center', formatter:function(value,row,index){
            	return "<button class='btn btn-warning btn-xs' onclick='fpyh(\""+value+"\");'> 分配 </span>";
            }}
	]};
	var yfpGrid =  {url:  'getYfpyhList',
		columns: [
            {checkbox: true }, 
            {field: 'yhm', title: '用户名'}, 
            {field: 'xm', title: '姓 名'}, 
//            {field: 'sjhm',title: '联系电话'},
//            {field: 'jgmc',title: '部门'},
            {field: 'yhzt',title: '启用状态',align:'center',formatter:function(value,row,index){
            	var ret;
            	if(value == '1'){
	           		 ret = '<span class="text-success">正常</span>';	
	           	}else if(value == '2'){
	           		 ret = '<span class="text-warning">锁定</span>';
	           	}else {
	           		 ret = '<span class="text-danger">停用</span>';
	           	}
	           	return ret;
            }},
            {field: 'yhm', title: '<button class="btn btn-success btn-xs" onclick="cxfp();">批量取消</button>',align:'center', formatter:function(value,row,index){
            	return "<button class='btn btn-warning btn-xs' onclick='cxfp(\""+value+"\");'> 取消 </span>";
            }}
	]};
	wfpGrid=$.extend(wfpGrid,{},grid);
	yfpGrid=$.extend(yfpGrid,{},grid);
	
	$("#wfpTabGrid").loadGrid(wfpGrid);
	$("#yfpTabGrid").loadGrid(yfpGrid);
	
	$(":button[name=search_button]").bind("click",function(){
		if($('#wfpCheck').is(':checked')){
			$('#wfpTabGrid').refreshGrid();
		}
		if($('#yfpCheck').is(':checked')){
			$('#yfpTabGrid').refreshGrid();
		}
	});
	$("#modifyModal #btn_success").hide();
});

function fpyh(yhm){
	var jsdm =  $("#jsdm").val();
	var ids = yhm == undefined ? $("#wfpTabGrid").getKeys().join(",") : yhm;
	if (ids.length == 0) {
		$.alert("请选择您要分配的用户");
		return false;
	}
	$.post("inertYhfp",{jsdm:jsdm,ids:ids},function(data){
		$('#wfpTabGrid').refreshGrid();
		$('#yfpTabGrid').refreshGrid();
	});
}

function cxfp(yhm){
	var jsdm =  $("#jsdm").val();
	var ids = yhm == undefined ? $("#yfpTabGrid").getKeys().join(",") : yhm;
	if (ids.length == 0) {
		$.alert("请选择您要取消的用户");
		return false;
	}
	$.post("deleteYhfp",{jsdm:jsdm,ids:ids},function(data){
		$('#wfpTabGrid').refreshGrid();
		$('#yfpTabGrid').refreshGrid();
	});
}