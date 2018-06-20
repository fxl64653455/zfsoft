<!DOCTYPE html>
<html>
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
				var jsdmArr=[];
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
	<form action="saveYhxx" data-toggle="validation"  data-widget='{"onkeyup": false}' role="form" class="form-horizontal sl_all_form"  id="ajaxForm" method="post" theme="simple">
		<input type="hidden" id="jsdms" name="jsdms" value=""/>  
		
		<div class="row">
			<div class="col-md-12 col-sm-12">
				<div class="form-group form-group-sm">
					<label for="" class="col-sm-2 control-label">
						<span class="red">*</span>用户名
					</label>
					<div class="col-sm-8">
						<input type="text" maxlength="20" name="yhm" id="yhm"  value="" class="form-control input-sm span2"  data-rules='{"required":true,"chrnum":true}'/>
					</div>
				</div>
			</div>
			<div class="col-md-12 col-sm-12">
				<div class="form-group form-group-sm">
					<label for="" class="col-sm-2 control-label">
						<span class="red">*</span>姓名
					</label>
					<div class="col-sm-8">
						<input type="text" maxlength="20" name="xm" id="xm" class="form-control  input-sm span3" data-rules='{"required":true}'/>
					</div>
				</div>
			</div>
			<div class="col-md-12 col-sm-12">
				<div class="form-group form-group-sm">
					<label for="" class="col-sm-2 control-label"  for="dzyx">
						Email
					</label>
					<div class="col-sm-8">
						<input type="text" name="dzyx" id="dzyx" maxlength="40" data-rules='{"email2":true}'  class="form-control input-sm span4"/>
					</div>
				</div>
			</div>
			<div class="col-md-12 col-sm-12">
				<div class="form-group form-group-sm">
					<label class="col-sm-2 control-label" for="sjhm" >
						手机号码	
					</label>
					<div class="col-sm-8">
						<input type="text" name="sjhm" id="sjhm" maxlength="30" data-rules='{"mobile":true}'  class="form-control input-sm span5"/>
					</div>
				</div>
			</div>
			
			<div class="col-md-12 col-sm-12">
				<div class="form-group">
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
				<div class="form-group form-group-sm">
					<label for="" class="col-sm-2 control-label" for="sjhm" >
						初始密码
					</label>
					<div class="col-sm-8">
						${password!}
						<input type="hidden" name="mm" value="${password!}"/>
					</div>
				</div>
			</div>
			<div class="col-md-12 col-sm-12">
				<div class="form-group">
					<label for="" class="col-sm-2 control-label">
						<span class="red">*</span>启用状态
					</label>
					<div class="col-sm-8">
						<input type="radio" id="yhzt_qy" checked="checked" name="yhzt" value="1"  />
						<label for="yhzt_qy" >启 用</label>
						<input type="radio" id="yhzt_ty" name="yhzt" value="0"/>
						<label for="yhzt_ty" >停 用</label>
					</div>
				</div>                                                                                                                                                        
			</div>
		</div>
	</form>
</body>
</html>