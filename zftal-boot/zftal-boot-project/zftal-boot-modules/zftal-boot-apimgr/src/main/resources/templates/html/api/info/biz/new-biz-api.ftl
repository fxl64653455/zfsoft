<!doctype html>
<html>
<head>
	<style type="text/css">
		[class^="col-"]:not(.pad-no){
			padding-left: 7.0px;
	    	padding-right: 7.0px;
		}
	</style>
</head>
<body>
    <form id="ajaxForm" method="post" data-widget='{"onkeyup": false}'  class="form-horizontal "
    	action="${request.contextPath}/manager/api/info/biz/new/form" theme="simple" >
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
		                	data-rules='{"required":true}' />
		            </div>
		        </div>
            </div>
            <div class="col-sm-6">
               	<div class="form-group">
		            <label for="input-type" class="col-sm-2 control-label">接口类型</label>
		            <div class="col-sm-10">
				    	<select id="input-type" name="type" class="form-control form-select chosen-select" data-rules='{"required":true}'>
				    	  	<option value="">--请选择--</option>
				    	  	[#list typeList as type] 
						    <option value="${type.key}">${type.desc}</option>
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
						    <option value="${method.key}">${method.desc}</option>
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
						    <option value="${plugin.key}">${plugin.value}</option>
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
						    [#list topics as topic] 
						    <option value="${topic.key}" tags='${topic.tags}'>${topic.desc}</option>
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
		                <input placeholder="请输入接口的方法名称" class="form-control" id="input-operName" name="operName" type="text" />
		            </div>
		        </div>
            </div>
           <div class="col-sm-12">
                <div class="form-group">
		            <label for="input-namespace" class="col-sm-1 control-label" style="padding-left:0px;">命名空间</label>
		            <div class="col-sm-11">
	                    <input placeholder="WS接口需要指定命名空间,通常指您的接口所在域名的倒叙，如：zfsot.com" class="form-control" id="input-namespace" name="namespace" type="text" />
		            </div>
		        </div>
            </div>
           <div class="col-sm-12">
                <div class="form-group">
		            <label for="input-url" class="col-sm-1 control-label" style="padding-left:0px;">接口地址</label>
		            <div class="col-sm-11">
		                <input placeholder="请输入接口地址" class="form-control" id="input-url" name="url" type="text" 
		                data-rules='{"required":true}' />
		            </div>
		        </div>
            </div>
        	<div class="col-sm-12">
	           	<div class="form-group">
	            	<label for="input-info" class="col-sm-1 control-label">接口简介</label>
		            <div class="col-sm-11">
		                <textarea name="info" class="form-control" placeholder="请输入接口简介信息" id="input-info"  validate="{stringMaxLength:512}"
		                cols="60" rows="2"/>
		            </div>
	          	</div>
           	</div>
           	<div class="col-sm-12" id="ke_control">
	           	<div class="form-group">
	            	<label for="input-detail" class="col-sm-1 control-label">详细介绍</label>
	            	<div class="col-sm-11">
	                	<textarea name="detail" class="form-control" placeholder="请输入接口详细介绍信息" id="input-detail"  validate="{stringMaxLength:512}"
	                	cols="60" rows="2" style="width:100%;height:150px;display:none;" >&nbsp;</textarea>
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
				         	<td colspan="7" class="text-center p-10">暂无数据,可添加输入参数!</td>
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
				                <select id="output-type" name="outType" class="form-control form-select chosen-select"  data-rules='{"required":true}'>
						    	  	<option value="">--请选择--</option>
						    	  	<option value="JSON" selected="selected">JSON</option>
						    	  	<option value="XML">XML</option>
						    	  	<option value="String">String</option>
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
				                cols="60" rows="2"/>
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
				      	<tbody id="table-fields-tbody"><td class="text-center p-10" colspan="6">暂无数据,可添加输出字段!</td></tbody>
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
				      		<tr>
				      			<td class="text-center p-10" colspan="4">暂无数据,可添加错误码对照信息!</td>
				      		</tr>
				      	</tbody>
			    	</table>
		 		</div>
		 	</div>
		</div>
    </form>
    <script type="text/javascript" src="${request.contextPath}/assets/js/api/info/biz/biz-api.js?ver=${versionUtil()}"></script>
</body>
</html>