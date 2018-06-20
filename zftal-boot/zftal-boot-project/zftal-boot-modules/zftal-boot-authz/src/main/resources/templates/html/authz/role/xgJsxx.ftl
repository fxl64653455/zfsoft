<!doctype html>
<html>
	<head>
	</head>
	<body>
		<form id="ajaxForm" action="modifyJsxx" data-toggle="validation" method="post" theme="simple" role="form" class="form-horizontal sl_all_form">
			<input type="hidden" name="jsdm" value="${model.jsdm }" />
			
			<div class="row">
				<div class="col-md-12 col-sm-12">
					<div class="form-group form-group-sm">
						<label for="" class="col-sm-2 control-label">
							<span class="red">*</span>角色名称
						</label>
						<div class="col-sm-8">
							<input type="text" value="${model.jsmc}" maxlength="20"  name="jsmc" id="jsmc" class="form-control  input-sm span3" 
								validate="{required:true,stringMinLength:2,stringMaxLength:20}"/>
						</div>
					</div>
				</div>
				<div class="col-md-12 col-sm-12">
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">
								启用状态
							</label>
							<div class="col-sm-8">
								<label class="radio-inline">
									<input type="radio" style="cursor: pointer;" [#if model.jszt==1]checked="checked"[/#if]  name="jszt" value="1" /><span> 启 用 </span>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" style="cursor: pointer;" [#if model.jszt==0]checked="checked"[/#if]  name="jszt" value="0" /><span> 停 用 </span>
								</label>
							</div>
						</div>
					</div>
				<div class="col-md-12 col-sm-12">
					<div class="form-group ">
						<label class="col-sm-2 control-label" >
							角色说明
						</label>
						<div class="col-sm-8">
							<textarea name="jssm" id="jssm" validate="{stringMaxLength:1000}"  style="height: 50px;"  class="form-control" rows="2" >${model.jssm}</textarea>
						</div>
					</div>
				</div>
			</div>
		</form>
	</body>
</html>