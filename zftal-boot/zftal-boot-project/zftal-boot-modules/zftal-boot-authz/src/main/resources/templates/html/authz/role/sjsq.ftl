<!doctype html>
<html>
	<head>
		<style type="text/css">
			.active{font-weight: bolder;}
			.bootbox-body{padding: 0px !important;}
			#nav_tabs li{ margin-top: 3px;}
			#nav_tabs li a{ border-top: 3px solid transparent;}
			#nav_tabs li.active a{ border-top:2px solid #0770cd;}
		</style>
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
		 	<li class="active">
			  	<a href="#" role="tab" data-toggle="tab"   id="sjsq"> 
		  				数据授权
			  	</a>
			</li>
			[@shiro.hasPermission name="jsgl:fpyh"]
		 	<li>
			  	<a href="#" role="tab" data-toggle="tab"  id="fpyh" > 
		  				分配用户
			  	</a>
			</li>
			[/@shiro.hasPermission]
		 </ul>
	
		<form id="ajaxForm" action="saveSjqx" method="post" data-toggle="validation" role="form" class="form-horizontal sl_all_form">
			<input type="hidden" id="jsdm" name="jsdm" value="${model.jsdm}">
			<input type="hidden" id="gzids" name="gzids" value="[#list jsgzList as jsgz][#if jsgz_index > 0],[/#if]${jsgz}[/#list]">
			<div class="row">
			    <div class="col-sm-12" >
			    	<div class="alert alert-success alert-dismissible" role="alert">
					  	<font color="red">（未设置表示不受数据权限约束）</font>
					</div>
			    </div>
			</div>
			<div class="row" style="margin-top: 5px;">
			   	<div class="col-md-12 col-sm-12">
					<!-- 功能模块Nav tabs -->
					 <ul class="nav nav-tabs " role="tablist" id="nav_tabs">
						[#list sjzyList as sjzy]
					 		<li data-zyid="${sjzy.zyid }" [#if sjzy_index == 0]class="active"[/#if]>
							  	<a href="#sjzy_${sjzy.zyid}" role="tab" data-toggle="tab" > 
									<label>${sjzy.zymc }</label>
							  	</a>
							</li>
					 	[/#list]
				    </ul>
				</div>
				<div class="col-md-12 col-sm-12">
					<!-- Tab panes -->
					<div class="tab-content" id="tabContent">
						[#list sjzyList as sjzy]
						  	<div class="tab-pane [#if sjzy_index == 0]active[/#if] chose-tag" id="sjzy_${sjzy.zyid}" data-zyid="${sjzy.zyid}" style="overflow: scroll;overflow-x:hidden; height: 200px;">
						  		[#if sjzy.zygzList?exists]
						  			[#list sjzy.zygzList as zygz]
						  				<span class="item [#list jsgzList as jsgz][#if jsgz==zygz.gzid]active[/#if][/#list]" name="gzid" gzid="${zygz.gzid}" title="${zygz.gzsm}">${zygz.gzmc }</span>
						  			[/#list]
						  		[/#if]
						  	</div>
					  	[/#list]
				    </div>
		      	</div>
			</div>
		</form>
		<script type="text/javascript">
			$(function(){
				var gzids = $("#gzids").val()=="" ? [] : $("#gzids").val().split(",");
				$(document).off('click','.chose-tag .item').on('click','.chose-tag .item',function(){
					$(this).toggleClass("active");
					if ($(this).hasClass('active')){
						gzids.push($(this).attr("gzid"));
					} else {
						gzids=[];
						$(this).siblings(".active").each(function(i,n){
							gzids.push($(n).attr("gzid"));
						});
					}
					$("#gzids").val(gzids.join());
				}); 
				
				$("#gnsq").click(function(){
					$("#modifyModal").reloadDialog({
						href:"gnsq?jsdm=${model.jsdm}",
						onLoaded	:function(){
				  			$("#ajaxForm").validateForm();
				  			$("#modifyModal .modal-title").html("角色【${model.jsmc}】功能授权");
					    }
					})
					
				})
				$("#fpyh").click(function(){
					$("#modifyModal").reloadDialog({
						href:"fpyh?jsdm=${model.jsdm}",
						onLoaded	:function(){
				  			$("#modifyModal .modal-title").html("角色【${model.jsmc}】分配用户");
				  			$("#modifyModal #btn_success").hide();
					    }
					})
				})
			});
		</script>
	</body>
</html>
