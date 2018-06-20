<!doctype html>
<html>
<head>
</head>
<body>
    <form id="ajaxForm" method="post" data-toggle="validation"  data-widget='{"onkeyup": false}'  class="form-horizontal "
    	action="${request.contextPath}/manager/api/info/biz/edit/form" theme="simple">
    	<input type="hidden" id="id" name="id" value="${infoDto.id}"/>
    	<div class="row">
            <div class="col-sm-6">
               	<div class="form-group">
		            <label for="input-name" class="col-sm-2 control-label" style="padding-left:0px;">接口名称</label>
		            <div class="col-sm-9">
		                <input placeholder="请输入接口名称" class="form-control" id="input-name" name="name" type="text" 
		                value="${infoDto.name}" data-rules='{"required":true}' />
		            </div>
		        </div>
            </div>
            <div class="col-sm-6">
               	<div class="form-group">
		            <label for="input-from" class="col-sm-3 control-label">接口类型</label>
		            <div class="col-sm-9">
		            	<select id="input-type" name="type" class="form-control form-select chosen-select" value="${infoDto.type}" data-rules='{"required":true}'>
				    	  	<option value="">--请选择--</option>
				    	  	[#list typeList as type] 
						    <option value="${type.key}" [#if infoDto.type== type.key]selected="selected"[/#if]>${type.desc}</option>
						    [/#list]
				    	</select>
		                <script type="text/javascript">
		                	$("#input-type").trigger("chosen");
		                </script>
		            </div>
		        </div>
            </div>
           <div class="col-sm-12">
                <div class="form-group">
	            <label for="input-url" class="col-sm-1 control-label" style="padding-left:0px;">接口地址</label>
	            <div class="col-sm-11">
	                <input placeholder="请输入接口地址" class="form-control" id="input-url" name="url" type="text" 
	                data-rules='{"required":true,"url":true}' value="${infoDto.url}"/>
	            </div>
	          </div>
            </div>
        	<div class="col-sm-12">
	           <div class="form-group">
	            <label for="input-info" class="col-sm-1 control-label">接口描述</label>
	            <div class="col-sm-11">
	                <textarea name="info" class="form-control" placeholder="请输入接口描述" id="input-info"  validate="{stringMaxLength:512}"
	                cols="60" rows="2">${infoDto.info}</textarea>
	               <br/>
	            </div>
	          </div>
           </div>
        </div>
        <ul class="nav nav-tabs" id="nav-tabs-new">
			<li class="active">
				<a data-toggle="tab" href="#input-lft-tab">输入参数（Input）</a>
			</li>
			<li>
				<a data-toggle="tab" href="#output-lft-tab">返回字段（Output）</a>
			</li>
		</ul>
		<div class="tab-content"  style="min-height:200px;">
			<div id="input-lft-tab" class="tab-pane fade active in">
		        <div class="panel-body p-1">
					<table class="table table-condensed table-hover table-striped m-b-0">
				      	<thead>
					        <tr>
					          <th width="15%">参数名称</th>
					          <th width="15%">参数类型</th>
					          <th width="15%">是否必填</th>
					          <th width="45%">参数说明</th>
					          <th width="10%">操作&nbsp;&nbsp;<a id="input-param" href="javascript:void(0);" class="btn-primary icon-plus">+</a></th>
					        </tr>
				      	</thead>
				      	<tbody id="table-columns-tbody">
				      		[#if infoDto.paramList?exists && infoDto.paramList?size != 0]  
				        	[#list infoDto.paramList as param]
				        	<tr>
				        		<td><input type="text" name="paramList[${param_index}].name" value="${param.name?string}" class="form-control input-sm" placeholder="如:name"/></td>
				        		<td>
				        			<select name="paramList[${param_index}].type" class="form-control input-sm">
								  		<option value="">--请选择--</option>
									  	<option value="String" [#if param.type == 'String']selected="selected"[/#if]>字符串</option>
								  		<option value="int" [#if param.type == 'int']selected="selected"[/#if]>整数</option>
									  	<option value="boolean" [#if param.type == 'boolean']selected="selected"[/#if]>布尔值</option>
									  	<option value="double" [#if param.type == 'double']selected="selected"[/#if]>浮点数</option>
									</select>
								</td>
					    		<td>
						    		<select name="paramList[${param_index}].required" class="form-control input-sm">
							      		<option value="">--请选择--</option>
							    	  	<option value="1" [#if param.required == '1']selected="selected"[/#if]>是</option>
							    	  	<option value="0" [#if param.required == '0']selected="selected"[/#if]>否</option>
						    		</select>
					    		</td>
								<td><input type="text" class="form-control input-sm" name="paramList[${param_index}].desc" value="${param.desc?string}" placeholder="参数描述"/></td>
								<td><a href="javascript:void(0);" class="btn btn-del btn-primary icon-minus">-</a></td>
					        </tr>
							[/#list]  
				        	[#else]
				        	<tr>
					        	<td class="text-center p-10" colspan="4">暂无数据,可添加输出字段!</td>
					        </tr>
				        	[/#if]
				      	</tbody>
				    </table>
				 </div>
			</div>
			<div id="output-lft-tab" class="tab-pane fade ">	
	        	<div class="panel-body p-l-1 p-r-1 p-b-1">
	        		<div class="col-sm-12">
		         		<div class="form-group">
				            <label for="output-type" class="col-sm-2 control-label">返回类型</label>
				            <div class="col-sm-10">
				                <select id="output-type" name="outType" class="form-control form-select chosen-select" value="${infoDto.outputtype}" data-rules='{"required":true}'>
						    	  	<option value="">--请选择--</option>
						    	  	<option value="JSON" [#if infoDto.outType=='JSON']selected="selected"[/#if]>JSON</option>
						    	  	<option value="XML" [#if infoDto.outType=='XML']selected="selected"[/#if]>XML</option>
						    	  	<option value="String" [#if infoDto.outType=='String']selected="selected"[/#if]>String</option>
						    	</select>
				                <script type="text/javascript">
				                	$("#output-type").trigger("chosen");
				                </script>
				            </div>
		          		</div>
		       		</div>
	        		<div class="col-sm-12">
		           		<div class="form-group">
				            <label for="output-case" class="col-sm-2 control-label">返回示例</label>
				            <div class="col-sm-10">
				                <textarea name="outCase" class="form-control" placeholder='例如: {"error_code": 0, "reason": "成功", "result": 2} ' id="output-case"  validate="{stringMaxLength:10240}"
		                			cols="60" rows="2">${infoDto.outCase}</textarea>
				            </div>
			          	</div>
		           	</div>
					<table class="table table-condensed table-hover table-striped m-b-0" style="height:100px; overflow-y:auto;">
				      	<thead>
					        <tr>
					          <th width="15%">字段名称</th>
					          <th width="15%">字段类型</th>
					          <th width="60%">字段说明</th>
					          <th width="10%">操作&nbsp;&nbsp;<a id="output-field" href="javascript:void(0);" class="btn-primary icon-plus">+</a></th>
					        </tr>
				      	</thead>
				      	<tbody id="table-fields-tbody">
				      		[#if infoDto.fieldList?exists && infoDto.fieldList?size != 0]  
				        	[#list infoDto.fieldList as field]
				        	<tr>
				        		<td><input type="text" name="fieldList[${field_index}].name" value="${field.name?string}" class="form-control input-sm" placeholder="如:name"/></td>
				        		<td>
				        			<select name="fieldList[${field_index}].type" class="form-control input-sm">
								  		<option value="">--请选择--</option>
								  		<option value="int" [#if field.type == 'int']selected="selected"[/#if]>整数</option>
									  	<option value="String" [#if field.type == 'String']selected="selected"[/#if]>字符串</option>
									  	<option value="boolean" [#if field.type == 'boolean']selected="selected"[/#if]>布尔值</option>
									  	<option value="double" [#if field.type == 'double']selected="selected"[/#if]>浮点数</option>
									</select>
								</td>
								<td><input type="text" class="form-control input-sm" name="fieldList[${field_index}].desc" value="${field.desc?string}" placeholder="参数描述"/></td>
								<td><a href="javascript:void(0);" class="btn btn-del btn-primary icon-minus">-</a></td>
					        </tr>
							[/#list]  
				        	[#else]
				        	<tr>
					        	<td class="text-center p-10" colspan="4">暂无数据,可添加输出字段!</td>
					        </tr>
				        	[/#if]
				      	</tbody>
			    	</table>
		 		</div>
		 	</div>
		</div>
    </form>
    <script type="text/javascript" src="${request.contextPath}/assets/js/api/info/biz/biz-api.js?ver=${versionUtil()}"></script>
</body>
</html>