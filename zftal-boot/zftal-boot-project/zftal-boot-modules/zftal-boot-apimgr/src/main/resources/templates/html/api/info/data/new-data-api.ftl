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
    <form id="ajaxForm" method="post"  data-widget='{"onkeyup": false}'  class="form-horizontal" action="${request.contextPath}/manager/api/info/data/new/form" theme="simple" >
    	<div class="row">
    		<div class="col-sm-12">
		        <div class="alert alert-warning clearfix p-5">
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
		            <label for="input-exchType" class="col-sm-2 control-label">交换类型</label>
		            <div class="col-sm-10">
		            	<select id="input-exchType" name="exchType" class="form-control form-select chosen-select"  data-rules='{"required":true}'>
				    	  	<option value="">--请选择--</option>
				    	  	[#list exchangeTypeList as type] 
						    <option value="${type.key}" [#if 3 == type.key ]selected="selected"[/#if]>${type.desc}</option>
						    [/#list]
				    	</select>
		                <script type="text/javascript">
		                	$("#input-exchType").trigger("chosen");
		                </script>
		            </div>
		        </div>
            </div>
	        <div class="col-sm-6">
               	<div class="form-group">
		            <label for="input-db" class="col-sm-2 control-label">数据源</label>
		            <div class="col-sm-10">
		            	<select id="input-db" name="dbid" class="form-control form-select chosen-select"  data-rules='{"required":true}'>
				    	  	<option value="current" data-name="current">当前系统数据源</option>
				    	  	[#list dbList as db] 
						    <option value="${db.id}" data-name="${db.name}" >${db.cnname}</option>
						    [/#list]
				    	</select>
		                <script type="text/javascript">
		                	$("#input-db").trigger("chosen");
		                </script>
		            </div>
		        </div>
            </div>
            <div class="col-sm-6">
                <div class="form-group">
		            <label for="input-exchMethod" class="col-sm-2 control-label">交换方式</label>
		            <div class="col-sm-10">
		            	<select id="input-exchMethod" name="exchMethod" class="form-control form-select chosen-select"  data-rules='{"required":true}'>
				    	  	[#list exchangeMethodList as method] 
						    <option value="${method.key}">${method.desc}</option>
						    [/#list]
				    	</select>
		                <script type="text/javascript">
		                	$("#input-exchMethod").trigger("chosen");
		                </script>
		            </div>
		        </div>
            </div>
            <div class="col-sm-6" id="form-group-table" style="display:none">
            	<div class="form-group">
            		<label class="col-sm-2 control-label" for="input-table">数据表</label>
					<div class="col-sm-10">
						<select id="input-table" name="table" class="form-control form-select chosen-select" data-rules='{"tableCheck":true}'>
				    	  	<option value="">--请选择--</option>
				    	</select>
		                <script type="text/javascript">
		                	$("#input-table").trigger("chosen");
		                </script>
					</div>
				</div>
			</div>
			<div class="col-sm-12" style="display:none" id="form-group-sql-error" >
	            <label class="col-sm-1"></label>
	            <div style="color:red;" class="col-sm-11" id="form-group-sql-error-content">
	            	
	            </div>
            </div>
			<div class="col-sm-12" style="display:none" id="form-group-sql" >
               	<div class="form-group">
		            <label for="input-sql" class="col-sm-1 control-label">SQL语句</label>
		            <div class="col-sm-11">
		            	<textarea placeholder="请输入SQL语句（不确定参数可使用Freemarker语法进行动态判断）" class="form-control" id="input-sql" name="sql" cols="60" rows="3"  data-rules='{"sqlCheck":true}'/>
		            	<div class="clearfix p-0">
							<p>注意：</br>1、非必须参数用:jdbcType=VARCHAR; </br>2、动态参数用:&#91;#if field1??&#93; and t.field1  = &#91;#noparse&#93;&#35;{field1}&#91;/#noparse&#93; &#91;/#if&#93; </br>3、必须参数、非必须参数、动态参数共存时使用 #noparse&#93;&#35;{field1}&#91;/#noparse&#93; 包裹参数体. </br>4、请参考如下增、删、改、查四种类型SQL示例.</p>
							<code>新增：[#noparse] insert into table1(field1,field2) values(&#35;{field1},&#35;{field2,jdbcType=VARCHAR})[/#noparse]</code></br>
							<code>删除：[#noparse] delete from table1 t where t.field1 = &#91;#noparse&#93;&#35;{field1}&#91;/#noparse&#93; &#91;#if field2??&#93; and t.field2  = &#91;#noparse&#93;&#35;{field2}&#91;/#noparse&#93;[/#if][/#noparse]</code></br>
							<code>更新：[#noparse] update table1 t set t.field2 = &#91;#noparse&#93;&#35;{field2}&#91;/#noparse&#93; &#91;#if field4??&#93; ,t.field3  = &#91;#noparse&#93;&#35;{field3}&#91;/#noparse&#93;&#91;/#if&#93; where t.field1 = &#91;#noparse&#93;&#35;{field1}&#91;/#noparse&#93;[/#noparse]</code></br>
							<code>查询：[#noparse] select t.field1,t.field2,t.field3,t.field4 from table1 t where t.field1 = &#91;#noparse&#93;&#35;{field1}&#91;/#noparse&#93; &#91;#if field2??&#93; and t.field2  = &#91;#noparse&#93;&#35;{field2}&#91;/#noparse&#93; &#91;/#if&#93;[/#noparse]</code>
						</div>
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
						    <option value="${t.key}" tags='${t.tags}'>${t.desc}</option>
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
        	<div class="col-sm-12">
	           	<div class="form-group">
	            	<label for="input-info" class="col-sm-1 control-label">接口简介</label>
		            <div class="col-sm-11">
		                <textarea name="info" class="form-control" placeholder="请输入接口简介信息" id="input-info" 
		                cols="60" rows="2" data-rules='{"required":true,"stringMaxLength":512}' />
		            </div>
	          	</div>
           	</div>
           	<div class="col-sm-12" id="ke_control">
	           	<div class="form-group">
	            	<label for="input-detail" class="col-sm-1 control-label">详细介绍</label>
	            	<div class="col-sm-11">
	                	<textarea name="detail" class="form-control" placeholder="请输入接口详细介绍信息" id="input-detail"  
	                	cols="60" rows="2" style="width:100%;height:150px;display:none;" >&nbsp;</textarea>
	            	</div>
	          	</div>
          	</div>
        </div>
        <ul class="nav nav-tabs" id="nav-tabs-new">
			<li id="input-tab" class="active">
				<a data-toggle="tab" href="#input-lft-tab">输入参数（Input）</a>
			</li>
			<li id="update-tab" class="hidden">
				<a data-toggle="tab" href="#update-lft-tab">变更字段（Update）</a>
			</li>
			<li id="output-tab">
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
			<div id="update-lft-tab" class="tab-pane fade">
		        <div class="panel-body p-1">
					<table class="table table-condensed table-hover table-striped m-b-0">
				      	<thead>
					        <tr>
					        	<th width="26px;">#</th>
					          	<th width="15%">字段编码</th>
					          	<th width="40%">字段名称</th>
					          	<th width="15%">字段类型</th>
					          	<th width="20%">字段说明</th>
					          	<th width="10%">操作&nbsp;&nbsp;<a id="input-update" href="javascript:void(0);" class="btn-primary icon-plus">+</a></th>
					        </tr>
				      	</thead>
				      	<tbody id="table-update-tbody">
				         	<td colspan="6" class="text-center p-10">暂无数据,可添加变更字段!</td>
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
				                <select id="output-type" name="outType" class="form-control form-select chosen-select"  data-rules='{"outTypeCheck":true}'>
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
					          	<th width="25%">字段名称</th>
					          	<th width="15%">字段类型</th>
					          	<th width="15%">字段别名</th>
					          	<th width="20%">字段说明</th>
					          	<th width="10%">操作</th>
					        </tr>
				      	</thead>
				      	<tbody id="table-fields-tbody">
				      		<tr>
				      			<td class="text-center p-10" colspan="7">暂无数据,可添加输出字段!</td>
				      		</tr>
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
				        		<th scope="row">${error_index + 1}</th>
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
    <script type="text/javascript" src="${request.contextPath}/assets/js/api/info/data/data-api.js?ver=${versionUtil()}"></script>
</body>
</html>