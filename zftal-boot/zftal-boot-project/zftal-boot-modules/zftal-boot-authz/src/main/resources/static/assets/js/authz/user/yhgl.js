jQuery(function($){

	var tempGridOptions = {
		url:  'getYhxxList',
		uniqueId: "yhm",
		columns: [
            {checkbox: true }, 
            {field: 'yhm', title: '用户名',sortable:true,width:"100px"}, 
            {field: 'xm', title: '姓名',sortable:true,width:"150px"}, 
            {field: 'sjhm',title: '联系电话',sortable:true,width:"200px"},
            {field: 'dzyx',title: '邮箱',sortable:true,width:"300px"},
            {field: 'yhzt',title: '启用状态',sortable:true,width:"80px",align:'center',formatter:function(value,row,index){
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
            {field: 'jsxx',title: '角色',sortable:true,width:"300px"}
    	],
	 	toolbar:'#but_ancd',
	 	sortName: 'yhm',
		sortOrder: "desc",
	 	searchParams:function(){
	 		var map = $.search.getSearchMap();
			return map;
	 	}
	};
	$("#tabGrid").loadGrid(tempGridOptions);
	
	
	/*====================================================绑定按钮事件====================================================*/
	
	jQuery("#btn_dc").click(function () {
		customExport("xsxx", function(selectUl){
			
			$.alert("导出");
		});
	});
	
	jQuery("#btn_dr").click(function () {
		importData("testdrmkdm");
	});
	
	//绑定增加点击事件
	jQuery("#btn_zj").click(function () {
		$.showDialog('zjYhxx','增加用户',$.extend({},addConfig,{"width":"700px"}));
	});
	
	//绑定删除事件
	jQuery("#btn_sc").click(function () {
		$("#tabGrid").plcz('scYhxx','删除');
	});
	
	//绑定修改事件
	jQuery("#btn_xg").click(function () {
		var ids = $("#tabGrid").getKeys();
		if(ids.length != 1){
			$.alert('请选定一条记录!');
			return;
		}
		var row = jQuery("#tabGrid").getRow(ids[0]);
		$.showDialog("xgYhxx?yhm="+row["yhm"],'修改用户',$.extend({},modifyConfig,{"width":"700px"}));
	});
	
	//查看
	jQuery("#btn_ck").click(function () {
		var ids = $("#tabGrid").getKeys();
		if(ids.length != 1){
			$.alert('请选定一条记录!');
			return;
		}
		var row = jQuery("#tabGrid").getRow(ids[0]);
		$.showDialog("ckYhxx?yhm="+row.yhm,'查看用户',$.extend({},viewConfig,{"width":"700px"}));
	});
	
	//设置所属角色
	jQuery("#btn_szssjs").click(function () {
		var ids = $("#tabGrid").getKeys();
		if(ids.length != 1){
			$.alert('请选定一条记录!');
			return;
		}
		var row = jQuery("#tabGrid").getRow(ids[0]);
		$.showDialog("szssjsYh?yhm="+row.yhm,'设置所属角色',$.mergeObj(modifyConfig,{"width":"800px"}));
	});
	
	//密码初始化
	jQuery("#btn_mmcsh").click(function () {
		var ids = $("#tabGrid").getKeys();
		if(ids.length == 0){
			$.alert('请选择您要操作的记录!');
			return;
		}
		$.showDialog("mmcsh?ids="+ids.toString(),'密码初始化',$.extend({},modifyConfig,{"width":"700px"}));
	});
	
	//数据资源授权
	jQuery("#btn_sjsq").click(function () {
		var ids = $("#tabGrid").getKeys();
		if(ids.length == 0){
			$.alert('请选择您要操作的记录!');
			return;
		}
		$.showDialog("yhsjfw?ids="+ids.toString(),'用户数据授权',$.extend({},modifyConfig,{"width":"700px"}));
	});
	
	//启用/停用
	jQuery("#btn_qy").click(function () {
		$("#tabGrid").plcz("qyty?yhzt=1",'启用');
	});
	
	jQuery("#btn_ty").click(function () {
		$("#tabGrid").plcz("qyty?yhzt=0",'停用');
	});
	
	$(":button[name=search_button]").bind("click",searchResult);
});

//查询
function searchResult(){
	$("#tabGrid").refreshGrid();
}
