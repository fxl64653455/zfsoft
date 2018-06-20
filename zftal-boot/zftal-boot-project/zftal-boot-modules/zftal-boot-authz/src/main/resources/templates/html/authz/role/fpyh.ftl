[#assign q=JspTaglibs["/zftal-search-tags"] /]
<!doctype html>
<html>
	<head>
		
	</head>
	<body>
		<ul class="nav nav-tabs">
			[@shiro.hasPermission name="jsgl:gnsq"]
		 	<li>
			  	<a href="#" role="tab" data-toggle="tab"   id="gnsq"> 
		  				功能授权
			  	</a>
			</li>
			[/@shiro.hasPermission]
			[@shiro.hasPermission name="jsgl:sjsq"]
		 	<li>
			  	<a href="#" role="tab" data-toggle="tab"   id="sjsq"> 
		  				数据授权
			  	</a>
			</li>
			[/@shiro.hasPermission]
		 	<li  class="active">
			  	<a href="#" role="tab" data-toggle="tab"  > 
		  				分配用户
			  	</a>
			</li>
		</ul>
		<input type="hidden" value="${model.jsdm}" name="jsdm" id="jsdm"/>
		[@q.panel theme="default" id="fpyh"] 
		     [@q.input list="#(zgh:用户名,xm:姓名,sjhm:联系电话)"/] 
		     [@q.radio name="sfqy" text="启用状态" list="#(1:启用,0:停用)" listKey="key" listValue="value"  defaultValue="1"/]
		[/@q.panel]
		<div class="row formbox">
			<div class="col-md-6">
				<form class="form-inline">
				  <div class="checkbox">
				    <label>
				      <input type="checkbox" checked="checked" id="wfpCheck"/> 设置为搜索列表 
				    </label>
				  </div>
				</form>
			</div>
			<div class="col-md-6">
				<form class="form-inline">
				  <div class="checkbox pull-left">
				    <label>
				      <input type="checkbox" checked="checked" id="yfpCheck"> 设置为搜索列表
				    </label>
				  </div>
				</form>
			</div>
			<div class="col-md-6">
				<table id="wfpTabGrid"></table>
			</div>
            <div class="col-md-6">
				<table id="yfpTabGrid"></table>
			</div>
		</div>
		<script type="text/javascript" src="${request.contextPath}/assets/js/authz/role/jsgl_fpyh.js?ver=${messageUtil("zftal.jsVersion")}"></script>
		<script type="text/javascript">
			$(function(){
				$("#gnsq").click(function(){
					var jsdm=$("#jsdm").val();
					$("#modifyModal").reloadDialog({
						href:"gnsq?jsdm="+jsdm,
						onLoaded	:function(){
				  			$("#ajaxForm").validateForm();
				  			$("#modifyModal .modal-title").html("角色【${model.jsmc}】功能授权");
				  			$("#modifyModal #btn_success").show();
					    }
					})
				});
				$("#sjsq").click(function(){
					var jsdm=$("#jsdm").val();
					$("#modifyModal").reloadDialog({
						href:"sjsq?jsdm="+jsdm,
						onLoaded	:function(){
				  			$("#ajaxForm").validateForm();
				  			$("#modifyModal .modal-title").html("角色【${model.jsmc}】数据授权");
				  			$("#modifyModal #btn_success").show();
					    }
					})
				});
			});
		</script>
	</body>
</html>