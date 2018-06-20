<!doctype html>
<html>
<head>
</head>
<body>
    <form id="ajaxForm" method="post" data-widget='{"onkeyup": false}'  class="form-horizontal "
    	action="${request.contextPath}/manager/api/info/data/edit/form" theme="simple">
    	<input type="hidden" id="id" name="id" value="${infoDto.id}"/>
    	<div class="row">
            <div class="col-sm-12">
               	<div class="form-group">
		            <label for="input-from" class="col-sm-1 control-label">数据来源</label>
		            <div class="col-sm-11">
				    	[#list dbList as db] 
			    	  	[#if infoDto.dbid== db.id]
			    	  	<p class="form-control-static">${db.cnname}</p>
					    [/#if]
					    [/#list]
		            </div>
		        </div>
            </div>
            <div class="col-sm-12">
               	<div class="form-group">
		            <label for="input-name" class="col-sm-1 control-label" style="padding-left:0px;">接口名称</label>
		            <div class="col-sm-5">
		                <p class="form-control-static">${infoDto.name}</p>
		            </div>
		        </div>
            </div>
        	<div class="col-sm-12">
	           <div class="form-group">
	            <label for="input-info" class="col-sm-1 control-label">接口简介</label>
	            <div class="col-sm-11">
	                <p class="form-control-static">${infoDto.info}</p>
	            </div>
	          </div>
           </div>
           <div class="col-sm-12" id="ke_control">
	           <div class="form-group">
		           <label for="input-detail" class="col-sm-1 control-label">详细介绍</label>
		           <div class="col-sm-11">
		               <textarea name="detail" class="form-control" placeholder="请输入接口详细介绍信息" id="input-detail"  validate="{stringMaxLength:512}"
		               cols="60" rows="2" style="width:100%;height:200px;display:none;" >${infoDto.detail}</textarea>
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
					        	<th width="15%">参数编码</th>
					          	<th width="25%">参数名称</th>
					          	<th width="15%">参数类型</th>
					          	<th width="15%">是否必填</th>
					          	<th width="20%">参数说明</th>
					        </tr>
				      	</thead>
				      	<tbody id="table-columns-tbody">
				      		[#if infoDto.paramList?exists && infoDto.paramList?size != 0]  
				        	[#list infoDto.paramList as param]
				        	<tr class="trclass">
				        		<td>${param.key?default("")}</td>
				        		<td>${param.name?default("")}</td>
				        		<td>${param.type?default("")}</td>
				        		<td>[#if param.required == '1']是[/#if][#if param.required == '0']否[/#if]</td>
								<td>${param.desc?default("")}</td>
					        </tr>
							[/#list]  
				        	[#else]
				        	<tr>
					        	<td class="text-center p-10" colspan="5">暂无数据!</td>
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
				                <p class="form-control-static">${infoDto.outType}</p>
				            </div>
		          		</div>
		       		</div>
	        		<div class="col-sm-12">
		           		<div class="form-group">
				            <label for="output-case" class="col-sm-2 control-label">返回示例</label>
				            <div class="col-sm-10">
				                <p class="form-control-static">${infoDto.outCase}</p>
				            </div>
			          	</div>
		           	</div>
					<table class="table table-condensed table-hover table-striped m-b-0">
				      	<thead>
					        <tr>
					          	<th width="15%">字段编码</th>
					          	<th width="25%">字段名称</th>
					          	<th width="15%">字段类型</th>
					          	<th width="15%">字段别名</th>
					          	<th width="20%">字段说明</th>
					        </tr>
				      	</thead>
				      	<tbody id="table-fields-tbody">
				      		[#if infoDto.fieldList?exists && infoDto.fieldList?size != 0]  
				        	[#list infoDto.fieldList as field]
				        	<tr class="trclass">
				        		<td>${field.key?default("")}</td>
				        		<td>${field.name?default("")}</td>
								<td>${field.type?default("")}</td>
								<td>${field.alias?default("")}</td>
								<td>${field.desc?default("")}</td>
					        </tr>
							[/#list]  
				        	[#else]
				        	<tr>
					        	<td class="text-center p-10" colspan="5">暂无数据!</td>
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
					          <th width="15%">错误编码</th>
					          <th width="75%">错误描述（占位符{0}表示具体的错误信息）</th>
					        </tr>
				      	</thead>
				      	<tbody id="table-error-tbody">
			      			[#if errorList?exists && errorList?size != 0]  
				        	[#list errorList as error]
				        	<tr>
				        		<td>${error_index + 1}</td>
				        		<td>${error.key?default('')}</td>
								<td>${error.desc?default('')}</td>
					        </tr>
							[/#list]  
				        	[#else]
				        	<tr>
					        	<td class="text-center p-10" colspan="3">暂无数据!</td>
					        </tr>
				        	[/#if]
				      	</tbody>
			    	</table>
		 		</div>
		 	</div>
		</div>
    </form>
    <script type="text/javascript" src="${request.contextPath}/assets/js/api/info/data/detail.js?ver=${versionUtil()}"></script>
</body>
</html>