<!doctype html>
<html>
<head>
</head>
<body>
	<form id="configForm" class="form-horizontal " theme="simple">
		<input type="hidden" name="apiId" value="${apiInfo.id}" />
		<div class="row">
			<div class="col-sm-12">
	            <div class="col-sm-3" style="padding-left:0px;">&nbsp;</div>
	            <div class="col-sm-9">
	            	<input id="input-pageable" class="default-checkbox" type="checkbox" name="invokeEnable" checked="checked">
	            	<label for="input-pageable">是否启用</label>
	            </div>
	        </div>
		</div>
		<div class="row">
			<div class="col-sm-12">
		       	<div class="form-group">
		            <label for="select-apiInfo" class="col-sm-3 control-label" style="padding-left:0px;">完成后调用接口</label>
		            <div class="col-sm-9">
		                <select id="select-apiInfo" name="invokeDeployId" class="form-control form-select chosen-select" >
		                	<option value="" >--请选择--</option>
				    	  	[#list apiList as api]
				    	  		[#if apiInfo.id != api.apiId]
						    	<option value="${api.id}" pars='${desc.input.params}' desc='${api.desc}' [#if invokeDeployId == api.id]selected="selected"[/#if]>${api.apiName}-${api.type}-${api.ver}</option>
						    	[/#if]
						    [/#list]
				    	</select>
		                <script type="text/javascript">
		                	$("#select-apiInfo").trigger("chosen");
		                </script>
		            </div>
		        </div>
		    </div>
		</div>
		<div class="row">
		[#assign i = 0]
		[#list desc.input.params as par]
			<input type="hidden" name="apiParams['${i}'].key" value="${par.key}" />
			<input id="input-${par.key}" type="hidden" name="apiParams['${i}'].desc" />
			<div class="col-sm-12">
		       	<div class="form-group">
		            <label for="select-${par.key}" class="col-sm-3 control-label" style="padding-left:0px;">${par.name} ----> </label>
		            <div class="col-sm-9">
		                <select id="select-${par.key}" name="apiParams['${i}'].name" class="form-control form-select chosen-select" [#if invokeDeployId ??]disabled[/#if] >
						    <option value="" >--请选择--</option>
						    [#list paramRelation as p]
						    	[#if p.key == par.key]
						    		<option value="${p.name}" selected="selected">${p.desc}</option>
						    	[/#if]
						    [/#list]
				    	</select>
		                <script type="text/javascript">
		                	$("#select-${par.key}").trigger("chosen");
		                </script>
		            </div>
		        </div>
		    </div>
		    [#assign i = i + 1]
		[/#list]
		</div>
	</form>
	<script type="text/javascript" src="${request.contextPath}/assets/js/api/info/formUtil.js?ver=${versionUtil()}"></script>
	<script type="text/javascript" src="${request.contextPath}/assets/js/api/info/config.js?ver=${versionUtil()}"></script>
</body>
</html>