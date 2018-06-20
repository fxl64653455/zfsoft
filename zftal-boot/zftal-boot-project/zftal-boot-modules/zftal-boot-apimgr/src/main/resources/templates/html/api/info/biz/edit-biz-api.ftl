<!doctype html>
<html>
<head>
</head>
<body>
    <form id="ajaxForm" method="post" data-widget='{"onkeyup": false}'  class="form-horizontal "
    	action="${request.contextPath}/manager/api/info/biz/edit/form" theme="simple">
    	<input type="hidden" id="id" name="id" value="${infoDto.id}"/>
    	<div class="row">
            <div class="col-sm-12">
		        <div class="alert alert-warning alert alert-warning clearfix p-5">
					提醒：给接口关联消息主题下的tag，当接口被调用时，将会向该消息主题指定的tag推送消息！
				</div>
			</div>
            <div class="col-sm-12">
               	<div class="form-group">
		            <label for="input-name" class="col-sm-1 control-label" style="padding-left:0px;">接口名称</label>
		            <div class="col-sm-11">
		                <input placeholder="请输入接口名称" class="form-control" id="input-name" name="name" type="text" 
		                value="${infoDto.name}" data-rules='{"required":true}' />
		            </div>
		        </div>
            </div>
            <div class="col-sm-6">
               	<div class="form-group">
		            <label for="input-from" class="col-sm-2 control-label">接口类型</label>
		            <div class="col-sm-10">
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
            <div class="col-sm-6">
               	<div class="form-group">
		            <label for="input-method" class="col-sm-2 control-label">请求方式</label>
		            <div class="col-sm-10">
		            	<select id="input-method" name="method" class="form-control form-select chosen-select"  data-rules='{"required":true}'>
				    	  	<option value="">--请选择--</option>
				    	  	[#list methodList as method] 
						    <option value="${method.key}" [#if infoDto.method== method.key]selected="selected"[/#if]>${method.desc}</option>
						    [/#list]
				    	</select>
		                <script type="text/javascript">
		                	$("#input-method").trigger("chosen");
		                </script>
		            </div>
		        </div>
            </div>
            
           <div class="col-sm-6">
               	<div class="form-group">
		            <label for="input-plugin" class="col-sm-2 control-label">插件名称</label>
		            <div class="col-sm-10">
				    	<select id="input-plugin" name="pluginId" class="form-control form-select chosen-select">
				    	  	<option value="">--请选择--</option>
				    	  	[#list pluginList as plugin] 
						    <option value="${plugin.key}" [#if infoDto.pluginId == plugin.key]selected="selected"[/#if]>${plugin.value}</option>
						    [/#list]
				    	</select>
		                <script type="text/javascript">
		                	$("#input-plugin").trigger("chosen");
		                </script>
		            </div>
		        </div>
           </div>
           <div class="col-sm-6">
               	<div class="form-group">
		            <label for="input-extension" class="col-sm-2 control-label">插件功能</label>
		            <div class="col-sm-10">
		            	<select id="input-extension" name="extensionId" class="form-control form-select chosen-select"  data-rules='{"extensionCheck":true}'>
				    	  	<option value="">--请选择--</option>
			    	  		[#if extensionList?exists && extensionList.size() > 0 ]
				    	  	[#list extensionList as extension] 
						    <option value="${extension.key}" [#if infoDto.extensionId == extension.key]selected="selected"[/#if]>${extension.value}</option>
						    [/#list]
						    [/#if]
				    	</select>
		                <script type="text/javascript">
		                	$("#input-extension").trigger("chosen");
		                </script>
		            </div>
		        </div>
            </div>
            <div class="col-sm-6">
               	<div class="form-group">
		            <label for="input-topic" class="col-sm-2 control-label">消息主题</label>
		            <div class="col-sm-10">
		            	<select id="input-topic" name="topic" class="form-control form-select chosen-select">
				    	  	<option value="">--请选择--</option>
				    	  	[#list topics as t] 
						    <option value="${t.key}" tags='${t.tags}' [#if infoDto.topic == t.key]selected="selected"[/#if]>${t.desc}</option>
						    [/#list]
				    	</select>
		                <script type="text/javascript">
		                	$("#input-topic").trigger("chosen");
		                </script>
		            </div>
		        </div>
            </div>
            <div class="col-sm-6">
               	<div class="form-group">
		            <label for="input-tags" class="col-sm-2 control-label">主题标记</label>
		            <div class="col-sm-10">
		            	<select id="input-tags" name="tag" class="form-control form-select chosen-select">
		            		<option value="">--请选择--</option>
		            		[#list topics as t]
		            			[#if infoDto.topic == t.key]
		            				[#assign jtags=t.tags?eval ]
			            			[#list jtags as tag]
			            				<option value="${tag.key}" [#if infoDto.tag == tag.key]selected="selected"[/#if]>${tag.desc}</option>
			            			[/#list]
			            		[/#if]
		            		[/#list]
				    	</select>
		                <script type="text/javascript">
		                	$("#input-tags").trigger("chosen");
		                </script>
		            </div>
		        </div>
            </div>
            <div class="col-sm-6">
               	<div class="form-group">
		            <label for="input-operName" class="col-sm-2 control-label" style="padding-left:0px;">方法名称</label>
		            <div class="col-sm-10">
		                <input placeholder="请输入接口的方法名称" class="form-control" id="input-operName" name="operName" type="text" value="${infoDto.operName}" />
		            </div>
		        </div>
            </div>
           <div class="col-sm-12">
                <div class="form-group">
		            <label for="input-namespace" class="col-sm-1 control-label" style="padding-left:0px;">命名空间</label>
		            <div class="col-sm-11">
	                    <input placeholder="WS接口需要指定命名空间,通常指您的接口所在域名的倒叙，如：zfsot.com" class="form-control" id="input-namespace" name="namespace" type="text" value="${infoDto.namespace}"/>
		            </div>
		        </div>
            </div>
           <div class="col-sm-12">
                <div class="form-group">
	            <label for="input-url" class="col-sm-1 control-label" style="padding-left:0px;">接口地址</label>
	            <div class="col-sm-11">
	                <input placeholder="请输入接口地址" class="form-control" id="input-url" name="url" type="text" 
	                data-rules='{"required":true}' value="${infoDto.url}"/>
	            </div>
	          </div>
            </div>
        	<div class="col-sm-12">
	           <div class="form-group">
	            <label for="input-info" class="col-sm-1 control-label">接口简介</label>
	            <div class="col-sm-11">
	                <textarea name="info" class="form-control" placeholder="请输入接口简介信息" id="input-info"  validate="{stringMaxLength:512}"
	                cols="60" rows="2">${infoDto.info}</textarea>
	            </div>
	          </div>
           </div>
           <div class="col-sm-12" id="ke_control">
	           <div class="form-group">
	            <label for="input-detail" class="col-sm-1 control-label">详细介绍</label>
	            <div class="col-sm-11">
	                <textarea name="detail" class="form-control" placeholder="请输入接口详细介绍信息" id="input-detail"  validate="{stringMaxLength:512}"
	                cols="60" rows="2" style="width:100%;height:150px;display:none;" >${infoDto.detail}</textarea>
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
			<li>
				<a data-toggle="tab" href="#error-lft-tab">错误码（Error Code）</a>
			</li>
		</ul>
		<div class="tab-content"  style="min-height:200px;">
			<div id="input-lft-tab" class="tab-pane fade active in">
		        <div class="panel-body p-1">
					<table class="table table-condensed table-hover table-striped m-b-0">
				      	<thead>
					        <tr>
					        	<th width="26px;">#</th>
					        	<th width="15%">参数编码</th>
					          	<th width="25%">参数名称</th>
					          	<th width="15%">参数类型</th>
					          	<th width="15%">是否必填</th>
					          	<th width="20%">参数说明</th>
					          	<th width="10%">操作&nbsp;&nbsp;<a id="input-param" href="javascript:void(0);" class="btn-primary icon-plus">+</a></th>
					        </tr>
				      	</thead>
				      	<tbody id="table-columns-tbody">
				      		[#if infoDto.paramList?exists && infoDto.paramList?size != 0]  
				        	[#list infoDto.paramList as param]
				        	<tr class="trclass">
				        		<th scope="row">${param_index + 1}</th>
				        		<td><input type="text" name="paramList[${param_index}].key" value="${param.key?default("")}" class="form-control input-sm" placeholder="参数编码,如:name"/></td>
				        		<td><input type="text" name="paramList[${param_index}].name" value="${param.name?default("")}" class="form-control input-sm" placeholder="参数名称,如:名称"/></td>
				        		<td>
				        			<select name="paramList[${param_index}].type" class="form-control input-sm">
								  		<option value="">--请选择--</option>
									  	<option value="String" [#if param.type == 'String']selected="selected"[/#if]>字符串</option>
								  		<option value="Number" [#if param.type == 'Number']selected="selected"[/#if]>数字</option>
									  	<option value="Boolean" [#if param.type == 'Boolean']selected="selected"[/#if]>布尔值</option>
									</select>
								</td>
					    		<td>
						    		<select name="paramList[${param_index}].required" class="form-control input-sm">
							      		<option value="">--请选择--</option>
							    	  	<option value="1" [#if param.required == '1']selected="selected"[/#if]>是</option>
							    	  	<option value="0" [#if param.required == '0']selected="selected"[/#if]>否</option>
						    		</select>
					    		</td>
								<td><input type="text" class="form-control input-sm" name="paramList[${param_index}].desc" value="${param.desc?default("")}" placeholder="参数描述"/></td>
								<td><a href="javascript:void(0);" class="btn btn-del btn-primary icon-minus">-</a></td>
					        </tr>
							[/#list]  
				        	[#else]
				        	<tr>
					        	<td class="text-center p-10" colspan="5">暂无数据,可添加输出字段!</td>
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
					<table class="table table-condensed table-hover table-striped m-b-0">
				      	<thead>
					        <tr>
					        	<th width="26px;">#</th>
					        	<th width="15%">字段编码</th>
					          	<th width="35%">字段名称</th>
					          	<th width="15%">字段类型</th>
					          	<th width="25%">字段说明</th>
					          	<th width="10%">操作&nbsp;&nbsp;<a id="output-field" href="javascript:void(0);" class="btn-primary icon-plus">+</a></th>
					        </tr>
				      	</thead>
				      	<tbody id="table-fields-tbody">
				      		[#if infoDto.fieldList?exists && infoDto.fieldList?size != 0]  
				        	[#list infoDto.fieldList as field]
				        	<tr class="trclass">
				        		<th scope="row">${field_index + 1}</th>
				        		<td><input type="text" name="fieldList[${field_index}].key" value="${field.key?default("")}" class="form-control input-sm" placeholder="字段编码,如:name"/></td>
				        		<td><input type="text" name="fieldList[${field_index}].name" value="${field.name?default("")}" class="form-control input-sm" placeholder="字段名称,如:名称"/></td>
				        		<td>
				        			<select name="fieldList[${field_index}].type" class="form-control input-sm">
								  		<option value="">--请选择--</option>
								  		<option value="String" [#if field.type == 'String']selected="selected"[/#if]>字符串</option>
								  		<option value="Number" [#if field.type == 'Number']selected="selected"[/#if]>数字</option>
									  	<option value="Boolean" [#if field.type == 'Boolean']selected="selected"[/#if]>布尔值</option>
									</select>
								</td>
								<td><input type="text" class="form-control input-sm" name="fieldList[${field_index}].desc" value="${field.desc?default("")}" placeholder="字段描述"/></td>
								<td><a href="javascript:void(0);" class="btn btn-del btn-primary icon-minus">-</a></td>
					        </tr>
							[/#list]  
				        	[#else]
				        	<tr>
					        	<td class="text-center p-10" colspan="6">暂无数据,可添加输出字段!</td>
					        </tr>
				        	[/#if]
				      	</tbody>
			    	</table>
		 		</div>
		 	</div>
		 	<div id="error-lft-tab" class="tab-pane fade ">	
	        	<div class="panel-body p-l-1 p-r-1 p-b-1">
					<table class="table table-condensed table-hover table-striped m-b-0">
				      	<thead>
					        <tr>
					          <th width="26px;">#</th>
					          <th width="25%">错误编码</th>
					          <th width="65%">错误描述</th>
					          <th width="10%">操作&nbsp;&nbsp;<a id="error-field" href="javascript:void(0);" class="btn-primary icon-plus">+</a></th>
					        </tr>
				      	</thead>
				      	<tbody id="table-error-tbody">
				      		[#if infoDto.errorList?exists && infoDto.errorList?size != 0]  
				        	[#list infoDto.errorList as error]
				        	<tr class="trclass">
				        		<th scope="row">${error_index + 1}</th>
				        		<td><input type="text" name="errorList[${error_index}].key" value="${error.key?default("")}" class="form-control input-sm" placeholder="错误编码,如:1001"/></td>
								<td><input type="text" class="form-control input-sm" name="errorList[${error_index}].desc" value="${error.desc?default("")}" placeholder="错误描述"/></td>
								<td><a href="javascript:void(0);" class="btn btn-del btn-primary icon-minus">-</a></td>
					        </tr>
							[/#list]  
				        	[#else]
				        	<tr>
					        	<td class="text-center p-10" colspan="4">暂无数据,可添加错误码对照信息!</td>
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
