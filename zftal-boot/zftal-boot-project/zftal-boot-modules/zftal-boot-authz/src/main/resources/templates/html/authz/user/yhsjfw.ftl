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
		<div class="row">
		    <div class="col-sm-12" >
		    	<div class="alert alert-success alert-dismissible" role="alert">
		    		授权用户：${ids}
				</div>
		    </div>
		</div>
		 <ul class="nav nav-tabs">
		 	[#if sjfwList?exists]
		 		[#list sjfwList as sjfw]
		 			<li [#if sjfw_index == 0]class="active"[/#if]>
					  	<a href="#${sjfw.sjdm}" role="tab" data-toggle="tab" > 
				  				${sjfw.sjmc}
					  	</a>
					</li>
		 		[/#list]
		 	[/#if]
		 </ul>
		<form id="ajaxForm" action="saveYhsjfw.zf" method="post" data-toggle="validation" role="form" class="form-horizontal sl_all_form">
			<input type="hidden" value="${ids}" name="ids"/>
			<input type="hidden" value="" name="sjfw" id="sjfw"/>
			
			<div class="row" style="margin-top: 5px;">
				<div class="col-md-12 col-sm-12">
					<!-- Tab panes -->
					<div class="tab-content" id="tabContent">
						[#if sjfwList?exists]
					 		[#list sjfwList as sjfw]
								<div class="tab-pane chose-tag  [#if sjfw_index == 0]active[/#if]" id="${sjfw.sjdm}"  style="overflow: scroll;overflow-x:hidden; height: 300px;">
						 			
									[#assign first=""]
									<ul class="nav nav-tabs" name="pinyinTab">
										[#list sjfw.itemList?sort_by("pinyin") as item]
											[#if item_index==0]
												[#assign first="${item.pinyin}"]
											[/#if]
										
											[#if item_index==0 || c!=item.pinyin]
												[#assign c="${item.pinyin}"]
												<li [#if item_index == 0]class="active"[/#if]>
												  	<a href="#" role="tab" data-toggle="tab" data-pinyin="${c}" data-sjdm="${sjfw.sjdm}"> 
											  			${c}
												  	</a>
												</li>
											[/#if]
										[/#list]
								    </ul>
						  			[#list sjfw.itemList?sort_by("pinyin") as item]
						  				<span class="item [#if first!=item.pinyin]hide[/#if]" sjdm="${sjfw.sjdm}" pinyin="${item.pinyin}" value="${item.key}">${item.value}</span>
						  			[/#list]
						  		</div>
					 		[/#list]
					 	[/#if]
				    </div>
		      	</div>
			</div>
		</form>
		[#if yhsjfwList?exists]
			[#list yhsjfwList as sjfw]
				<input type="hidden" name="defaultItem" data-sjid="${sjfw.SJID}" data-sjdm="${sjfw.SJDM}"/>
			[/#list]
		[/#if]
		
		<script type="text/javascript">
			$(function(){
				$("ul[name=pinyinTab]>li>a").click(function(){
					var pinyin = $(this).data("pinyin");
					var sjdm = $(this).data("sjdm");
					$("span[sjdm="+sjdm+"]").addClass("hide");
					$("span[sjdm="+sjdm+"][pinyin="+pinyin+"]").removeClass("hide");
				});
				
				$("input[name=defaultItem]").each(function(i,n){
					var sjid = $(n).data("sjid");
					var sjdm = $(n).data("sjdm");
					$("span[sjdm="+sjdm+"][value="+sjid+"]").addClass("active");
				});
				
				$(document).off('click','.chose-tag .item').on('click','.chose-tag .item',function(){
					$(this).toggleClass("active");
				}); 
				
				$("#ajaxForm").submit(function(){
					var map = {};
					$(".chose-tag>.active").each(function(){
						var sjdm = $(this).attr("sjdm");
						var value = $(this).attr("value");
						if (map[sjdm]){
							map[sjdm].push(value);
						} else {
							map[sjdm]=[value];
						}
					});
					$("#sjfw").val(JSON.stringify(map));
				});
			});
		</script>
	</body>
</html>
