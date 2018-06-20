<!doctype html>
	<head>
		<style type="text/css">
			span.item {
				padding : 5px;border-radius : 2px;
			}
			span.active {
				color : #ffffff;background-color : #42a5f5;
			}
		</style>
		<script type="text/javascript">
			$(function(){
				var jsdmArr = $("#jsdms").val().split(",");
				$.each(jsdmArr,function(i,n){
					if (n != ""){
						$("span[name=cbvjsxx][jsdm="+n+"]").addClass("active");
					}
				});
				
				var jsdmArr=$("#jsdms").val()=="" ? [] : $("#jsdms").val().split(",");
				$(document).off('click','.chose-tag .item').on('click','.chose-tag .item',function(){
					$(this).toggleClass("active");
					var jsdm = $(this).attr("jsdm");
					
					if($(this).hasClass('active') && $.inArray(jsdm,jsdmArr) == -1) {
						jsdmArr.push(jsdm);
					} else {
						jsdmArr.splice($.inArray(jsdm,jsdmArr),1);
					}
					$('#jsdms').val(jsdmArr.join(","));
				}); 
			});
		</script>
	</head>
	<body>
		<form  id="ajaxForm" action="modifyYhxx" data-toggle="validation" role="form"  class="form-horizontal sl_all_form" method="post" theme="simple">
		    [#assign jsdms='']
		    [#list yhjsList as list]
		    	[#if list_index != 0]
		    		 [#assign jsdms=jsdms+',']
		    	[/#if]
		    	[#assign jsdms=jsdms+list.jsdm]
		    [/#list]
		    
		    <input type="hidden" id="jsdms" name="jsdms" value="${jsdms}"/>  
		    <input type="hidden" id="yhm" name="yhm" value="${model.yhm}"/>      
		    <div class="row">
				<div class="col-md-12 col-sm-12">
					<div class="form-group form-group-sm">
						<label for="" class="col-sm-2 control-label">
							用户名
						</label>
						<div class="col-sm-8 ">
							<p class="form-control-static">${model.yhm }</p>
						</div>
					</div>
				</div>
				<div class="col-md-12 col-sm-12">
					<div class="form-group form-group-sm">
						<label for="" class="col-sm-2 control-label">
							<span class="red">*</span>姓名
						</label>
						<div class="col-sm-8">
							<input type="text" name="xm" id="xm" maxlength="20" class="form-control input-sm" value="${model.xm}" validate="{required:true}">
						</div>
					</div>
				</div>
				<div class="col-md-12 col-sm-12">
					<div class="form-group form-group-sm">
						<label for="" class="col-sm-2 control-label"  for="dzyx">
							Email
						</label>
						<div class="col-sm-8">
							<input type="text" name="dzyx" id="dzyx" maxlength="40" class="form-control input-sm" value="${model.dzyx}" validate="{email2:true}">
						</div>
					</div>
				</div>
				<div class="col-md-12 col-sm-12">
					<div class="form-group form-group-sm" >
						<label for="" class="col-sm-2 control-label" for="sjhm" >
							联系电话	
						</label>
						<div class="col-sm-8">
							<input type="text" name="sjhm" id="sjhm" maxlength="30" validate="{mobile:true}" value="${model.sjhm}"  class="form-control input-sm"/>
						</div>
					</div>
				</div>
				<div class="col-md-12 col-sm-12">
					<div class="form-group form-group-sm">
						<label for="" class="col-sm-2 control-label">
							所属角色
						</label>
						<div class="col-sm-8">
							<div class="chose-tag">
								[#list jsxxList as s]
									<span class="item" name="cbvjsxx" jsdm="${s.jsdm}">${s.jsmc}</span>
								[/#list] 
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12 col-sm-12">
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">
							是否启用
						</label>
						<div class="col-sm-8">
							<label class="radio-inline">
								<input type="radio" style="cursor: pointer;" [#if model.yhzt==1]checked="checked"[/#if] id="yhzt" name="yhzt" value="1" /><span> 启 用 </span>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" style="cursor: pointer;" [#if model.yhzt==0]checked="checked"[/#if] id="yhzt" name="yhzt" value="0" /><span> 停 用 </span>
							</label>
						</div>
					</div>
				</div>
			</div>
		</form>
	</body>
	
</html>